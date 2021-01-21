@Test public void should_create_error_message_for_File(){
  FakeFile file=new FakeFile("xyz");
  ErrorMessageFactory factory=shouldBeRelativePath(file);
  String message=factory.create(TEST_DESCRIPTION,STANDARD_REPRESENTATION);
  assertThat(message).isEqualTo(format("[Test] " + SHOULD_BE_RELATIVE_PATH,file));
}
