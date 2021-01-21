@Test public void testCreateGroupWithRelativePath() throws RepositoryException, NotExecutableException {
  Principal p=getTestPrincipal();
  Group gr=createGroup(p,"any/path");
  createdGroups.add(gr);
  assertNotNull(gr.getID());
  assertTrue(gr.getPath().contains("any/path"));
}
