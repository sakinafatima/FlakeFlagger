@Test public void should_pass_if_actual_starts_with_sequence(){
  arrays.assertStartsWith(someInfo(),actual,arrayOf(6L,8L,10L));
}
