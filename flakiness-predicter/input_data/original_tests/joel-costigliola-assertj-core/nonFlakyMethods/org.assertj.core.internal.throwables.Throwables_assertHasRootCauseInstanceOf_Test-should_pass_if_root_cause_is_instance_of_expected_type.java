@Test public void should_pass_if_root_cause_is_instance_of_expected_type() throws Exception {
  throwables.assertHasRootCauseInstanceOf(someInfo(),throwableWithCause,RuntimeException.class);
}
