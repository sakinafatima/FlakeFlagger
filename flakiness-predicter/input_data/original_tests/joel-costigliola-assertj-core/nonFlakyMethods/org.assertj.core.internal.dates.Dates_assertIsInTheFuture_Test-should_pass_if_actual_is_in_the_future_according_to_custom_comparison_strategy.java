@Test public void should_pass_if_actual_is_in_the_future_according_to_custom_comparison_strategy(){
  actual=parseDate("2111-01-01");
  datesWithCustomComparisonStrategy.assertIsInTheFuture(someInfo(),actual);
}
