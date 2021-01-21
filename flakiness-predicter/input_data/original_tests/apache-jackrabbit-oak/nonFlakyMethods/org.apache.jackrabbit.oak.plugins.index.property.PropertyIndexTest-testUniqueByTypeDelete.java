@Test public void testUniqueByTypeDelete() throws Exception {
  NodeState root=EMPTY_NODE;
  NodeBuilder builder=root.builder();
  createIndexDefinition(builder.child(INDEX_DEFINITIONS_NAME),"fooIndex",true,true,ImmutableSet.of("foo"),ImmutableSet.of("typeFoo"));
  builder.child("a").setProperty(JCR_PRIMARYTYPE,"typeFoo",Type.NAME).setProperty("foo","abc");
  builder.child("b").setProperty(JCR_PRIMARYTYPE,"typeBar",Type.NAME).setProperty("foo","abc");
  NodeState before=builder.getNodeState();
  builder.getChildNode("b").remove();
  NodeState after=builder.getNodeState();
  HOOK.processCommit(before,after,CommitInfo.EMPTY);
}
