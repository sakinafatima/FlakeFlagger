@Test public void should_succeed_since_actual_is_not_zero_whatever_custom_comparison_strategy_is(){
  floatsWithAbsValueComparisonStrategy.assertIsZero(someInfo(),0.0f);
}
