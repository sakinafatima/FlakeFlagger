@Test(expected=NullPointerException.class) public void should_fail_if_start_is_null(){
  floats.assertIsStrictlyBetween(someInfo(),ONE,null,ONE);
}
