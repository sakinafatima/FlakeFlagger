/** 
 * a/*&#47c should match a/b/c
 */
@Test public void starGlob(){
  EventFilter rootFilter=new GlobbingPathFilter("a/*/c");
  NodeState a=tree.getChild("a").getNodeState();
  assertFalse(rootFilter.includeAdd("a",a));
  EventFilter aFilter=rootFilter.create("a",a,a);
  assertNotNull(aFilter);
  NodeState b=a.getChildNode("b");
  assertFalse(aFilter.includeAdd("b",b));
  EventFilter bFilter=aFilter.create("b",b,b);
  assertNotNull(bFilter);
  NodeState c=b.getChildNode("c");
  assertTrue(bFilter.includeAdd("c",b));
  assertFalse(bFilter.includeAdd("x",b));
  assertNull(bFilter.create("c",c,c));
}
