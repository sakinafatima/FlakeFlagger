@Test public void should_fail_if_actual_does_not_contain_given_values_only(){
  AssertionInfo info=someInfo();
  float[] expected={6f,8f,20f};
  try {
    arrays.assertContainsOnly(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainOnly(actual,expected,newLinkedHashSet(20f),newLinkedHashSet(10f)));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
