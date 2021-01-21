@Test public void shouldBeThreadSafe() throws Exception {
  final DustJsProcessor processor=new DustJsProcessor();
  final Callable<Void> task=new Callable<Void>(){
    @Override public Void call(){
      try {
        processor.process(null,new StringReader("Hello {name}!"),new StringWriter());
      }
 catch (      final Exception e) {
        throw new RuntimeException(e);
      }
      return null;
    }
  }
;
  WroTestUtils.runConcurrently(task);
}
