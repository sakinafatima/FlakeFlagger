@Test public void should_pass_if_actual_contains_given_values_exactly_in_different_order_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  long[] expected={-6L,10L,8L};
  try {
    arraysWithCustomComparisonStrategy.assertContainsExactly(someInfo(),actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,elementsDifferAtIndex(8L,10L,1,absValueComparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
