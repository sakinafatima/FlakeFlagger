@Test public void should_pass_if_actual_does_not_contain_any_elements_of_given_iterable(){
  arrays.assertDoesNotContainAnyElementsOf(someInfo(),actual,newArrayList("Han"));
}
