@Test public void testGenericInvokeWithNativeJava() throws IOException, ClassNotFoundException {
  HttpServiceImpl server=new HttpServiceImpl();
  Assert.assertFalse(server.isCalled());
  ProxyFactory proxyFactory=ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
  Protocol protocol=ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
  URL url=URL.valueOf("http://127.0.0.1:5342/" + HttpService.class.getName() + "?version=1.0.0&generic=nativejava");
  Exporter<HttpService> exporter=protocol.export(proxyFactory.getInvoker(server,HttpService.class,url));
  Invoker<GenericService> invoker=protocol.refer(GenericService.class,url);
  GenericService client=proxyFactory.getProxy(invoker);
  Serialization serialization=new NativeJavaSerialization();
  ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject("haha");
  objectOutput.flushBuffer();
  Object result=client.$invoke("sayHello",new String[]{"java.lang.String"},new Object[]{byteArrayOutputStream.toByteArray()});
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream((byte[])result);
  ObjectInput objectInput=serialization.deserialize(url,byteArrayInputStream);
  Assert.assertTrue(server.isCalled());
  Assert.assertEquals("Hello, haha",objectInput.readObject());
  invoker.destroy();
  exporter.unexport();
}
