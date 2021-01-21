@Test public void should_fail_if_actual_contains_value_at_index(){
  AssertionInfo info=someInfo();
  Index index=atIndex(0);
  try {
    arrays.assertDoesNotContain(info,actual,'a',index);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldNotContainAtIndex(actual,'a',index));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
