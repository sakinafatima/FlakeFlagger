@Test public void testMove2b() throws Exception {
  allow(path,privilegesFromNames(new String[]{Privilege.JCR_ADD_CHILD_NODES,Privilege.JCR_REMOVE_CHILD_NODES,Privilege.JCR_NODE_TYPE_MANAGEMENT}));
  try {
    move(childNPath,destPath);
    fail("Move requires addChildNodes and removeChildNodes privilege.");
  }
 catch (  AccessDeniedException e) {
  }
}
