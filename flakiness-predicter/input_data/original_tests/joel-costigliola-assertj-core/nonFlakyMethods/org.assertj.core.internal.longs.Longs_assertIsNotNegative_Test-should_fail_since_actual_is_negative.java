@Test public void should_fail_since_actual_is_negative(){
  thrown.expectAssertionError("\nExpecting:\n <-6L>\nto be greater than or equal to:\n <0L>");
  longs.assertIsNotNegative(someInfo(),-6L);
}
