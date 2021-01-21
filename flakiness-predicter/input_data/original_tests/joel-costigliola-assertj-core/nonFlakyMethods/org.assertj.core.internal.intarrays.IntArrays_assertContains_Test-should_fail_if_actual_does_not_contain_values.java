@Test public void should_fail_if_actual_does_not_contain_values(){
  AssertionInfo info=someInfo();
  int[] expected={6,8,9};
  try {
    arrays.assertContains(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContain(actual,expected,newLinkedHashSet(9)));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
