@Test public void should_fail_if_big_decimals_are_equal_by_comparison_whatever_custom_comparison_strategy_is(){
  AssertionInfo info=someInfo();
  try {
    bigDecimalsWithAbsValueComparisonStrategy.assertNotEqualByComparison(info,ONE_WITH_3_DECIMALS,ONE);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldNotBeEqual(ONE_WITH_3_DECIMALS,ONE));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
