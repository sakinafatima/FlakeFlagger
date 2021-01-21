@Test public void should_fail_if_actual_is_greater_than_other(){
  AssertionInfo info=someInfo();
  try {
    bytes.assertLessThan(info,(byte)8,(byte)6);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeLess((byte)8,(byte)6));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
