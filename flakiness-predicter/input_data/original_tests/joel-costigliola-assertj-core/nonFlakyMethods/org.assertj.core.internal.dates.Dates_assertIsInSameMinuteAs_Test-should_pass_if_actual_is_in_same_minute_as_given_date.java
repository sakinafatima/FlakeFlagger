@Test public void should_pass_if_actual_is_in_same_minute_as_given_date(){
  dates.assertIsInSameMinuteAs(someInfo(),actual,parseDatetime("2011-01-01T03:15:59"));
}
