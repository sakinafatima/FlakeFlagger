@Test public void should_fail_if_actual_is_not_sorted_in_ascending_order(){
  AssertionInfo info=someInfo();
  actual=arrayOf(1.0f,3.0f,2.0f);
  try {
    arrays.assertIsSorted(info,actual);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeSorted(1,actual));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
