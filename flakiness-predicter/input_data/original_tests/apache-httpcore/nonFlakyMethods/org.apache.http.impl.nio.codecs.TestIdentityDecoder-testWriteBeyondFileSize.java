@Test public void testWriteBeyondFileSize() throws Exception {
  ReadableByteChannel channel=new ReadableByteChannelMock(new String[]{"a"},"US-ASCII");
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,256,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  IdentityDecoder decoder=new IdentityDecoder(channel,inbuf,metrics);
  File fileHandle=File.createTempFile("testFile",".txt");
  RandomAccessFile testfile=new RandomAccessFile(fileHandle,"rw");
  FileChannel fchannel=testfile.getChannel();
  Assert.assertEquals(0,testfile.length());
  try {
    decoder.transfer(fchannel,5,10);
    Assert.fail("expected IOException");
  }
 catch (  IOException iox) {
  }
  testfile.close();
  deleteWithCheck(fileHandle);
}
