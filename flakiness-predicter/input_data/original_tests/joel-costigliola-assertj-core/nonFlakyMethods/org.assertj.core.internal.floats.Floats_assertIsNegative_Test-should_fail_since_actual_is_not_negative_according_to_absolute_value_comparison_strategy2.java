@Test public void should_fail_since_actual_is_not_negative_according_to_absolute_value_comparison_strategy2(){
  thrown.expectAssertionError("\nExpecting:\n <6.0f>\nto be less than:\n <0.0f>");
  floatsWithAbsValueComparisonStrategy.assertIsNegative(someInfo(),6.0f);
}
