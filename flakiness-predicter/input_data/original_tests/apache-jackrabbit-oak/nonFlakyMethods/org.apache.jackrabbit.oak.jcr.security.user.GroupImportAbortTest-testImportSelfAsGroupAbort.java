@Test public void testImportSelfAsGroupAbort() throws Exception {
  String invalidId="0120a4f9-196a-3f9e-b9f5-23f31f914da7";
  String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<sv:node sv:name=\"gFolder\" xmlns:mix=\"http://www.jcp.org/jcr/mix/1.0\" xmlns:nt=\"http://www.jcp.org/jcr/nt/1.0\" xmlns:fn_old=\"http://www.w3.org/2004/10/xpath-functions\" xmlns:fn=\"http://www.w3.org/2005/xpath-functions\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:sv=\"http://www.jcp.org/jcr/sv/1.0\" xmlns:rep=\"internal\" xmlns:jcr=\"http://www.jcp.org/jcr/1.0\">" + "   <sv:property sv:name=\"jcr:primaryType\" sv:type=\"Name\"><sv:value>rep:AuthorizableFolder</sv:value></sv:property>"+ "<sv:node sv:name=\"g1\"><sv:property sv:name=\"jcr:primaryType\" sv:type=\"Name\"><sv:value>rep:Group</sv:value></sv:property>"+ "   <sv:property sv:name=\"jcr:uuid\" sv:type=\"String\"><sv:value>" + invalidId + "</sv:value></sv:property>"+ "   <sv:property sv:name=\"rep:principalName\" sv:type=\"String\"><sv:value>g1</sv:value></sv:property>"+ "   <sv:property sv:name=\"rep:members\" sv:type=\"WeakReference\"><sv:value>"+ invalidId+ "</sv:value></sv:property>"+ "</sv:node>"+ "</sv:node>";
  try {
    doImport(getTargetPath(),xml);
    fail("Importing self as group with ImportBehavior.ABORT must fail.");
  }
 catch (  RepositoryException e) {
  }
}
