@Test public void should_pass_if_actual_does_not_contain_given_values(){
  arrays.assertDoesNotContain(someInfo(),actual,array("Han"));
}
