@Test public void betweenIncludeBoth(){
  List<ValuePathTuple> data=ImmutableList.of(new ValuePathTuple("a","foobar"),new ValuePathTuple("b","foobar"),new ValuePathTuple("c","foobar"),new ValuePathTuple("d","foobar"),new ValuePathTuple("e","foobar"),new ValuePathTuple("f","foobar"));
  Iterator<ValuePathTuple> filtered=Iterables.filter(data,new ValuePathTuple.BetweenPredicate("b","d",true,true)).iterator();
  assertTrue(filtered.hasNext());
  assertEquals("b",filtered.next().getValue());
  assertEquals("c",filtered.next().getValue());
  assertEquals("d",filtered.next().getValue());
  assertFalse(filtered.hasNext());
}
