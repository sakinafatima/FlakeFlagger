@Test public void should_fail_since_actual_is_not_negative_according_to_absolute_value_comparison_strategy2_in_hex_representation(){
  thrown.expectAssertionError("\nExpecting:\n <0x06>\nto be less than:\n <0x00> when comparing values using 'AbsValueComparator'");
  bytesWithAbsValueComparisonStrategy.assertIsNegative(someHexInfo(),(byte)0x06);
}
