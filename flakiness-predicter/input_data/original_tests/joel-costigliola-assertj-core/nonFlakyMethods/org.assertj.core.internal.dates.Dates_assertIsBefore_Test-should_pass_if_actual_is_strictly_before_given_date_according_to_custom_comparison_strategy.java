@Test public void should_pass_if_actual_is_strictly_before_given_date_according_to_custom_comparison_strategy(){
  datesWithCustomComparisonStrategy.assertIsBefore(someInfo(),actual,parseDate("2020-01-01"));
}
