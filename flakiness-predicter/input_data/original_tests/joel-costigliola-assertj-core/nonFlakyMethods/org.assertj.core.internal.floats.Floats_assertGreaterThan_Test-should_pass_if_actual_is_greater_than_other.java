@Test public void should_pass_if_actual_is_greater_than_other(){
  floats.assertGreaterThan(someInfo(),8f,6f);
}
