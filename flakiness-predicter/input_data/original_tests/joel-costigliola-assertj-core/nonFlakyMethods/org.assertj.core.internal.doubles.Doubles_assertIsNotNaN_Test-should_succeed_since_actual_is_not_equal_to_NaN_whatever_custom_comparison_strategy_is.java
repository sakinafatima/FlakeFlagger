@Test public void should_succeed_since_actual_is_not_equal_to_NaN_whatever_custom_comparison_strategy_is(){
  doublesWithAbsValueComparisonStrategy.assertIsNotNaN(someInfo(),6d);
}
