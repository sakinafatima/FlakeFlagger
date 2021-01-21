/** 
 * Test basic puts, gets, scans, and deletes for a single row in a multiple family table.
 */
@Test public void testSingleRowMultipleFamily() throws Exception {
  byte[] TABLE=Bytes.toBytes("testSingleRowMultipleFamily");
  byte[][] ROWS=makeN(ROW,3);
  byte[][] FAMILIES=makeNAscii(FAMILY,10);
  byte[][] QUALIFIERS=makeN(QUALIFIER,10);
  byte[][] VALUES=makeN(VALUE,10);
  HTable ht=TEST_UTIL.createTable(TABLE,FAMILIES);
  Get get;
  Scan scan;
  Delete delete;
  Put put;
  Result result;
  put=new Put(ROWS[0]);
  put.add(FAMILIES[4],QUALIFIERS[0],VALUES[0]);
  ht.put(put);
  getVerifySingleColumn(ht,ROWS,0,FAMILIES,4,QUALIFIERS,0,VALUES,0);
  scanVerifySingleColumn(ht,ROWS,0,FAMILIES,4,QUALIFIERS,0,VALUES,0);
  getVerifySingleEmpty(ht,ROWS,0,FAMILIES,4,QUALIFIERS,0);
  scanVerifySingleEmpty(ht,ROWS,0,FAMILIES,4,QUALIFIERS,0);
  TEST_UTIL.flush();
  getVerifySingleColumn(ht,ROWS,0,FAMILIES,4,QUALIFIERS,0,VALUES,0);
  scanVerifySingleColumn(ht,ROWS,0,FAMILIES,4,QUALIFIERS,0,VALUES,0);
  getVerifySingleEmpty(ht,ROWS,0,FAMILIES,4,QUALIFIERS,0);
  scanVerifySingleEmpty(ht,ROWS,0,FAMILIES,4,QUALIFIERS,0);
  put=new Put(ROWS[0]);
  put.add(FAMILIES[2],QUALIFIERS[2],VALUES[2]);
  put.add(FAMILIES[2],QUALIFIERS[4],VALUES[4]);
  put.add(FAMILIES[4],QUALIFIERS[4],VALUES[4]);
  put.add(FAMILIES[6],QUALIFIERS[6],VALUES[6]);
  put.add(FAMILIES[6],QUALIFIERS[7],VALUES[7]);
  put.add(FAMILIES[7],QUALIFIERS[7],VALUES[7]);
  put.add(FAMILIES[9],QUALIFIERS[0],VALUES[0]);
  ht.put(put);
  singleRowGetTest(ht,ROWS,FAMILIES,QUALIFIERS,VALUES);
  singleRowScanTest(ht,ROWS,FAMILIES,QUALIFIERS,VALUES);
  TEST_UTIL.flush();
  singleRowGetTest(ht,ROWS,FAMILIES,QUALIFIERS,VALUES);
  singleRowScanTest(ht,ROWS,FAMILIES,QUALIFIERS,VALUES);
  put=new Put(ROWS[0]);
  put.add(FAMILIES[6],QUALIFIERS[5],VALUES[5]);
  put.add(FAMILIES[6],QUALIFIERS[8],VALUES[8]);
  put.add(FAMILIES[6],QUALIFIERS[9],VALUES[9]);
  put.add(FAMILIES[4],QUALIFIERS[3],VALUES[3]);
  ht.put(put);
  delete=new Delete(ROWS[0]);
  delete.deleteColumns(FAMILIES[6],QUALIFIERS[7]);
  ht.delete(delete);
  get=new Get(ROWS[0]);
  get.addColumn(FAMILIES[6],QUALIFIERS[7]);
  result=ht.get(get);
  assertEmptyResult(result);
  scan=new Scan();
  scan.addColumn(FAMILIES[6],QUALIFIERS[7]);
  result=getSingleScanResult(ht,scan);
  assertNullResult(result);
  get=new Get(ROWS[0]);
  get.addColumn(FAMILIES[6],QUALIFIERS[6]);
  result=ht.get(get);
  assertSingleResult(result,ROWS[0],FAMILIES[6],QUALIFIERS[6],VALUES[6]);
  get=new Get(ROWS[0]);
  get.addColumn(FAMILIES[6],QUALIFIERS[8]);
  result=ht.get(get);
  assertSingleResult(result,ROWS[0],FAMILIES[6],QUALIFIERS[8],VALUES[8]);
  scan=new Scan();
  scan.addColumn(FAMILIES[6],QUALIFIERS[6]);
  result=getSingleScanResult(ht,scan);
  assertSingleResult(result,ROWS[0],FAMILIES[6],QUALIFIERS[6],VALUES[6]);
  scan=new Scan();
  scan.addColumn(FAMILIES[6],QUALIFIERS[8]);
  result=getSingleScanResult(ht,scan);
  assertSingleResult(result,ROWS[0],FAMILIES[6],QUALIFIERS[8],VALUES[8]);
  delete=new Delete(ROWS[0]);
  delete.deleteColumns(FAMILIES[6],QUALIFIERS[8]);
  ht.delete(delete);
  get=new Get(ROWS[0]);
  get.addColumn(FAMILIES[6],QUALIFIERS[8]);
  result=ht.get(get);
  assertEmptyResult(result);
  scan=new Scan();
  scan.addColumn(FAMILIES[6],QUALIFIERS[8]);
  result=getSingleScanResult(ht,scan);
  assertNullResult(result);
  get=new Get(ROWS[0]);
  get.addColumn(FAMILIES[6],QUALIFIERS[6]);
  result=ht.get(get);
  assertSingleResult(result,ROWS[0],FAMILIES[6],QUALIFIERS[6],VALUES[6]);
  get=new Get(ROWS[0]);
  get.addColumn(FAMILIES[6],QUALIFIERS[9]);
  result=ht.get(get);
  assertSingleResult(result,ROWS[0],FAMILIES[6],QUALIFIERS[9],VALUES[9]);
  scan=new Scan();
  scan.addColumn(FAMILIES[6],QUALIFIERS[6]);
  result=getSingleScanResult(ht,scan);
  assertSingleResult(result,ROWS[0],FAMILIES[6],QUALIFIERS[6],VALUES[6]);
  scan=new Scan();
  scan.addColumn(FAMILIES[6],QUALIFIERS[9]);
  result=getSingleScanResult(ht,scan);
  assertSingleResult(result,ROWS[0],FAMILIES[6],QUALIFIERS[9],VALUES[9]);
  delete=new Delete(ROWS[0]);
  delete.deleteFamily(FAMILIES[4]);
  ht.delete(delete);
  get=new Get(ROWS[0]);
  get.addColumn(FAMILIES[4],QUALIFIERS[4]);
  result=ht.get(get);
  assertEmptyResult(result);
  get=new Get(ROWS[0]);
  get.addColumn(FAMILIES[4],QUALIFIERS[3]);
  result=ht.get(get);
  assertEmptyResult(result);
  get=new Get(ROWS[0]);
  get.addFamily(FAMILIES[4]);
  result=ht.get(get);
  assertEmptyResult(result);
  scan=new Scan();
  scan.addColumn(FAMILIES[4],QUALIFIERS[4]);
  result=getSingleScanResult(ht,scan);
  assertNullResult(result);
  scan=new Scan();
  scan.addColumn(FAMILIES[4],QUALIFIERS[3]);
  result=getSingleScanResult(ht,scan);
  assertNullResult(result);
  scan=new Scan();
  scan.addFamily(FAMILIES[4]);
  result=getSingleScanResult(ht,scan);
  assertNullResult(result);
  get=new Get(ROWS[0]);
  get.addColumn(FAMILIES[2],QUALIFIERS[2]);
  result=ht.get(get);
  assertSingleResult(result,ROWS[0],FAMILIES[2],QUALIFIERS[2],VALUES[2]);
  get=new Get(ROWS[0]);
  get.addColumn(FAMILIES[6],QUALIFIERS[9]);
  result=ht.get(get);
  assertSingleResult(result,ROWS[0],FAMILIES[6],QUALIFIERS[9],VALUES[9]);
  scan=new Scan();
  scan.addColumn(FAMILIES[6],QUALIFIERS[6]);
  result=getSingleScanResult(ht,scan);
  assertSingleResult(result,ROWS[0],FAMILIES[6],QUALIFIERS[6],VALUES[6]);
  scan=new Scan();
  scan.addColumn(FAMILIES[6],QUALIFIERS[9]);
  result=getSingleScanResult(ht,scan);
  assertSingleResult(result,ROWS[0],FAMILIES[6],QUALIFIERS[9],VALUES[9]);
  TEST_UTIL.flush();
  get=new Get(ROWS[0]);
  get.addColumn(FAMILIES[4],QUALIFIERS[4]);
  result=ht.get(get);
  assertEmptyResult(result);
  get=new Get(ROWS[0]);
  get.addColumn(FAMILIES[4],QUALIFIERS[3]);
  result=ht.get(get);
  assertEmptyResult(result);
  get=new Get(ROWS[0]);
  get.addFamily(FAMILIES[4]);
  result=ht.get(get);
  assertEmptyResult(result);
  scan=new Scan();
  scan.addColumn(FAMILIES[4],QUALIFIERS[4]);
  result=getSingleScanResult(ht,scan);
  assertNullResult(result);
  scan=new Scan();
  scan.addColumn(FAMILIES[4],QUALIFIERS[3]);
  result=getSingleScanResult(ht,scan);
  assertNullResult(result);
  scan=new Scan();
  scan.addFamily(FAMILIES[4]);
  result=getSingleScanResult(ht,scan);
  assertNullResult(result);
  get=new Get(ROWS[0]);
  get.addColumn(FAMILIES[2],QUALIFIERS[2]);
  result=ht.get(get);
  assertSingleResult(result,ROWS[0],FAMILIES[2],QUALIFIERS[2],VALUES[2]);
  get=new Get(ROWS[0]);
  get.addColumn(FAMILIES[6],QUALIFIERS[9]);
  result=ht.get(get);
  assertSingleResult(result,ROWS[0],FAMILIES[6],QUALIFIERS[9],VALUES[9]);
  scan=new Scan();
  scan.addColumn(FAMILIES[6],QUALIFIERS[6]);
  result=getSingleScanResult(ht,scan);
  assertSingleResult(result,ROWS[0],FAMILIES[6],QUALIFIERS[6],VALUES[6]);
  scan=new Scan();
  scan.addColumn(FAMILIES[6],QUALIFIERS[9]);
  result=getSingleScanResult(ht,scan);
  assertSingleResult(result,ROWS[0],FAMILIES[6],QUALIFIERS[9],VALUES[9]);
}
