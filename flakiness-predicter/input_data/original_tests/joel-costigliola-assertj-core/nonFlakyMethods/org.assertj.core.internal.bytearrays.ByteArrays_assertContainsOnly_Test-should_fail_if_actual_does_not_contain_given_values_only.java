@Test public void should_fail_if_actual_does_not_contain_given_values_only(){
  AssertionInfo info=someInfo();
  byte[] expected={6,8,20};
  try {
    arrays.assertContainsOnly(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainOnly(actual,expected,newLinkedHashSet((byte)20),newLinkedHashSet((byte)10)));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
