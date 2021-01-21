public void testCheckColumn_EnforceVersions(){
  ScanWildcardColumnTracker tracker=new ScanWildcardColumnTracker(VERSIONS);
  List<byte[]> qualifiers=new ArrayList<byte[]>();
  qualifiers.add(Bytes.toBytes("qualifer1"));
  qualifiers.add(Bytes.toBytes("qualifer1"));
  qualifiers.add(Bytes.toBytes("qualifer1"));
  qualifiers.add(Bytes.toBytes("qualifer2"));
  List<MatchCode> expected=new ArrayList<MatchCode>();
  expected.add(MatchCode.INCLUDE);
  expected.add(MatchCode.INCLUDE);
  expected.add(MatchCode.SKIP);
  expected.add(MatchCode.INCLUDE);
  List<MatchCode> actual=new ArrayList<MatchCode>();
  for (  byte[] qualifier : qualifiers) {
    MatchCode mc=tracker.checkColumn(qualifier,0,qualifier.length);
    actual.add(mc);
  }
  for (int i=0; i < expected.size(); i++) {
    assertEquals(expected.get(i),actual.get(i));
  }
}
