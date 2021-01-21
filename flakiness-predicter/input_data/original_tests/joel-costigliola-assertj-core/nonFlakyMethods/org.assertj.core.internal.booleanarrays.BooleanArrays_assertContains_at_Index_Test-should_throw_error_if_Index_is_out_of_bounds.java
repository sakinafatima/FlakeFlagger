@Test public void should_throw_error_if_Index_is_out_of_bounds(){
  thrown.expectIndexOutOfBoundsException("Index should be between <0> and <1> (inclusive,) but was:\n <6>");
  arrays.assertContains(someInfo(),actual,true,atIndex(6));
}
