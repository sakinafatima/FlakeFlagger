@Test public void testHEFringeCase2() throws Exception {
  String headerValue="name1 = value1, ";
  HeaderElement[] elements=BasicHeaderValueParser.parseElements(headerValue,null);
  Assert.assertEquals("Number of elements",1,elements.length);
}
