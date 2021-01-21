@Test public void testCopy() throws Exception {
  Workspace wsp=testSession.getWorkspace();
  String parentPath=childNode.getParent().getPath();
  String srcPath=childNode.getPath();
  String destPath=parentPath + "/destination";
  try {
    wsp.copy(srcPath,destPath);
    fail("Missing write privilege.");
  }
 catch (  AccessDeniedException e) {
  }
  modify(parentPath,Privilege.JCR_WRITE,true);
  try {
    wsp.copy(srcPath,destPath);
    fail("Missing privilege jcr:nodeTypeManagement.");
  }
 catch (  AccessDeniedException e) {
  }
  modify(parentPath,REP_WRITE,true);
  wsp.copy(srcPath,destPath);
}
