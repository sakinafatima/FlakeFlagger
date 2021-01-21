@Test public void deviceHandlerMethodArgumentResolverCreated() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.register(DeviceResolverAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(DeviceHandlerMethodArgumentResolver.class));
}
