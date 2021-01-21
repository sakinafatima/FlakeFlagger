@Test public void testCodingBeyondContentLimitFile() throws Exception {
  ReadableByteChannel channel=new ReadableByteChannelMock(new String[]{"stuff;","more stuff; and a lot more stuff"},"US-ASCII");
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,256,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  LengthDelimitedDecoder decoder=new LengthDelimitedDecoder(channel,inbuf,metrics,16);
  createTempFile();
  RandomAccessFile testfile=new RandomAccessFile(this.tmpfile,"rw");
  try {
    FileChannel fchannel=testfile.getChannel();
    long bytesRead=decoder.transfer(fchannel,0,6);
    Assert.assertEquals(6,bytesRead);
    Assert.assertFalse(decoder.isCompleted());
    Assert.assertEquals(6,metrics.getBytesTransferred());
    bytesRead=decoder.transfer(fchannel,0,10);
    Assert.assertEquals(10,bytesRead);
    Assert.assertTrue(decoder.isCompleted());
    Assert.assertEquals(16,metrics.getBytesTransferred());
    bytesRead=decoder.transfer(fchannel,0,1);
    Assert.assertEquals(-1,bytesRead);
    Assert.assertTrue(decoder.isCompleted());
    Assert.assertEquals(16,metrics.getBytesTransferred());
  }
  finally {
    testfile.close();
  }
}
