@Test public void should_pass_if_actual_does_not_contain_value_at_Index(){
  arrays.assertDoesNotContain(someInfo(),actual,'a',atIndex(1));
}
