@Test public void test_removeParameters() throws Exception {
  URL url=URL.valueOf("dubbo://admin:hello1234@10.20.130.230:20880/context/path?version=1.0.0&application=morgan&k1=v1&k2=v2");
  url=url.removeParameter("version");
  assertEquals("dubbo",url.getProtocol());
  assertEquals("admin",url.getUsername());
  assertEquals("hello1234",url.getPassword());
  assertEquals("10.20.130.230",url.getHost());
  assertEquals(20880,url.getPort());
  assertEquals("context/path",url.getPath());
  assertEquals(3,url.getParameters().size());
  assertEquals("morgan",url.getParameter("application"));
  assertEquals("v1",url.getParameter("k1"));
  assertEquals("v2",url.getParameter("k2"));
  assertNull(url.getParameter("version"));
  url=URL.valueOf("dubbo://admin:hello1234@10.20.130.230:20880/context/path?version=1.0.0&application=morgan&k1=v1&k2=v2");
  url=url.removeParameters("version","application","NotExistedKey");
  assertEquals("dubbo",url.getProtocol());
  assertEquals("admin",url.getUsername());
  assertEquals("hello1234",url.getPassword());
  assertEquals("10.20.130.230",url.getHost());
  assertEquals(20880,url.getPort());
  assertEquals("context/path",url.getPath());
  assertEquals(2,url.getParameters().size());
  assertEquals("v1",url.getParameter("k1"));
  assertEquals("v2",url.getParameter("k2"));
  assertNull(url.getParameter("version"));
  assertNull(url.getParameter("application"));
  url=URL.valueOf("dubbo://admin:hello1234@10.20.130.230:20880/context/path?version=1.0.0&application=morgan&k1=v1&k2=v2");
  url=url.removeParameters(Arrays.asList("version","application"));
  assertEquals("dubbo",url.getProtocol());
  assertEquals("admin",url.getUsername());
  assertEquals("hello1234",url.getPassword());
  assertEquals("10.20.130.230",url.getHost());
  assertEquals(20880,url.getPort());
  assertEquals("context/path",url.getPath());
  assertEquals(2,url.getParameters().size());
  assertEquals("v1",url.getParameter("k1"));
  assertEquals("v2",url.getParameter("k2"));
  assertNull(url.getParameter("version"));
  assertNull(url.getParameter("application"));
}
