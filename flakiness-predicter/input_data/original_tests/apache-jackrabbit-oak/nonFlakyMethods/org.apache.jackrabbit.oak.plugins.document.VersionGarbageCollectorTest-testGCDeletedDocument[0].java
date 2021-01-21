@Test public void testGCDeletedDocument() throws Exception {
  NodeBuilder b1=store.getRoot().builder();
  b1.child("x").child("y");
  b1.child("z");
  store.merge(b1,EmptyHook.INSTANCE,CommitInfo.EMPTY);
  long maxAge=1;
  long delta=TimeUnit.MINUTES.toMillis(10);
  clock.waitUntil(Revision.getCurrentTimestamp() + maxAge);
  VersionGCStats stats=gc.gc(maxAge,TimeUnit.HOURS);
  assertEquals(0,stats.deletedDocGCCount);
  NodeBuilder b2=store.getRoot().builder();
  b2.child("x").child("y").remove();
  store.merge(b2,EmptyHook.INSTANCE,CommitInfo.EMPTY);
  store.runBackgroundOperations();
  clock.waitUntil(clock.getTime() + delta);
  stats=gc.gc(maxAge * 2,TimeUnit.HOURS);
  assertEquals(0,stats.deletedDocGCCount);
  clock.waitUntil(clock.getTime() + TimeUnit.HOURS.toMillis(maxAge * 2) + delta);
  stats=gc.gc(maxAge * 2,TimeUnit.HOURS);
  assertEquals(1,stats.deletedDocGCCount);
  NodeBuilder b3=store.getRoot().builder();
  b3.child("z").remove();
  store.merge(b3,EmptyHook.INSTANCE,CommitInfo.EMPTY);
  NodeBuilder b4=store.getRoot().builder();
  b4.child("z");
  store.merge(b4,EmptyHook.INSTANCE,CommitInfo.EMPTY);
  clock.waitUntil(clock.getTime() + TimeUnit.HOURS.toMillis(maxAge * 2) + delta);
  stats=gc.gc(maxAge * 2,TimeUnit.HOURS);
  assertEquals(0,stats.deletedDocGCCount);
}
