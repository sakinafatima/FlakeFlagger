@Test public void nonConflictingRemoveProperty(){
  String rev=mk.commit("/","+\"foo\":{\"prop1\":\"value\", \"prop2\":\"value\"}",null,null);
  mk.commit("/foo","^\"prop1\":\"bar\"",rev,null);
  mk.commit("/foo","^\"prop2\":null",rev,null);
}
