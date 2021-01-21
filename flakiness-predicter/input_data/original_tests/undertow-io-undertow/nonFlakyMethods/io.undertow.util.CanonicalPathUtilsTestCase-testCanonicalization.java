@Test public void testCanonicalization(){
  Assert.assertSame("a/b/c",CanonicalPathUtils.canonicalize("a/b/c"));
  Assert.assertSame("aaaaa",CanonicalPathUtils.canonicalize("aaaaa"));
  Assert.assertEquals("a./b",CanonicalPathUtils.canonicalize("a./b"));
  Assert.assertEquals("a./.b",CanonicalPathUtils.canonicalize("a./.b"));
  Assert.assertEquals("a/b",CanonicalPathUtils.canonicalize("a//b"));
  Assert.assertEquals("a/b",CanonicalPathUtils.canonicalize("a///b"));
  Assert.assertEquals("a/b",CanonicalPathUtils.canonicalize("a////b"));
  Assert.assertEquals("a/b",CanonicalPathUtils.canonicalize("a/./b"));
  Assert.assertEquals("a/b",CanonicalPathUtils.canonicalize("a/././b"));
  Assert.assertEquals("a/b/c",CanonicalPathUtils.canonicalize("a/./b/./c"));
  Assert.assertEquals("a/b",CanonicalPathUtils.canonicalize("a/./././b"));
  Assert.assertEquals("a/b/",CanonicalPathUtils.canonicalize("a/./././b/./"));
  Assert.assertEquals("a/b",CanonicalPathUtils.canonicalize("a/./././b/."));
  Assert.assertEquals("/b",CanonicalPathUtils.canonicalize("/a/../b"));
  Assert.assertEquals("/b",CanonicalPathUtils.canonicalize("/a/../c/../e/../b"));
  Assert.assertEquals("/b",CanonicalPathUtils.canonicalize("/a/c/../../b"));
  Assert.assertEquals("/",CanonicalPathUtils.canonicalize("/a/../.."));
  Assert.assertEquals("/a/",CanonicalPathUtils.canonicalize("/a/"));
  Assert.assertEquals("/",CanonicalPathUtils.canonicalize("/"));
  Assert.assertEquals("/bbb/a",CanonicalPathUtils.canonicalize("/cc/../bbb/a/."));
}
