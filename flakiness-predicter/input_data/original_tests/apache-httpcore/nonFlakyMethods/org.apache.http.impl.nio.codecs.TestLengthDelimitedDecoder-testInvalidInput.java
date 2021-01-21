@Test public void testInvalidInput() throws Exception {
  String s="stuff";
  ReadableByteChannel channel=new ReadableByteChannelMock(new String[]{s},"US-ASCII");
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,256,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  LengthDelimitedDecoder decoder=new LengthDelimitedDecoder(channel,inbuf,metrics,3);
  try {
    decoder.read(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
