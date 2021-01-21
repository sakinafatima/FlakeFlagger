@Test public void testLucene2() throws Exception {
  NodeBuilder index=builder.child(INDEX_DEFINITIONS_NAME);
  newLuceneIndexDefinition(index,"lucene",ImmutableSet.of(TYPENAME_STRING));
  NodeState before=builder.getNodeState();
  builder.setProperty("foo","bar");
  builder.child("a").setProperty("foo","bar");
  builder.child("a").child("b").setProperty("foo","bar");
  builder.child("a").child("b").child("c").setProperty("foo","bar");
  NodeState after=builder.getNodeState();
  NodeState indexed=HOOK.processCommit(before,after,CommitInfo.EMPTY);
  IndexTracker tracker=new IndexTracker();
  tracker.update(indexed);
  QueryIndex queryIndex=new LuceneIndex(tracker,analyzer,null);
  FilterImpl filter=createFilter(NT_BASE);
  filter.restrictProperty("foo",Operator.EQUAL,PropertyValues.newString("bar"));
  Cursor cursor=queryIndex.query(filter,indexed);
  assertTrue(cursor.hasNext());
  assertEquals("/a/b/c",cursor.next().getPath());
  assertEquals("/a/b",cursor.next().getPath());
  assertEquals("/a",cursor.next().getPath());
  assertEquals("/",cursor.next().getPath());
  assertFalse(cursor.hasNext());
}
