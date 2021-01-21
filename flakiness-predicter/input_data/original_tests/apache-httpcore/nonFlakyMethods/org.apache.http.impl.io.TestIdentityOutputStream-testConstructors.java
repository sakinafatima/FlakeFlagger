@Test public void testConstructors() throws Exception {
  new IdentityOutputStream(new SessionOutputBufferMock());
  try {
    new IdentityOutputStream(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
