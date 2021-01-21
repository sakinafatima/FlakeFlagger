@Test public void daylightSavingFallSecondHour() throws Exception {
  Clock testingClock=new DefaultClockImpl();
  testingClock.setCurrentCalendar(parseCalendar("20131103-06:45:00",TimeZone.getTimeZone("UTC")));
  Calendar easternTime=testingClock.getCurrentCalendar(TimeZone.getTimeZone("US/Eastern"));
  DurationHelper dh=new DurationHelper("R2/2013-11-03T01:45:00-05:00/PT1H",testingClock);
  assertEquals(parseCalendar("20131103-07:45:00",TimeZone.getTimeZone("UTC")),dh.getCalendarAfter(easternTime));
}
