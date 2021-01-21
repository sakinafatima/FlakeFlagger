@Test public void testBasicDecoding() throws Exception {
  ReadableByteChannel channel=new ReadableByteChannelMock(new String[]{"stuff;","more stuff"},"US-ASCII");
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,256,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  IdentityDecoder decoder=new IdentityDecoder(channel,inbuf,metrics);
  ByteBuffer dst=ByteBuffer.allocate(1024);
  int bytesRead=decoder.read(dst);
  Assert.assertEquals(6,bytesRead);
  Assert.assertEquals("stuff;",convert(dst));
  Assert.assertFalse(decoder.isCompleted());
  Assert.assertEquals(6,metrics.getBytesTransferred());
  dst.clear();
  bytesRead=decoder.read(dst);
  Assert.assertEquals(10,bytesRead);
  Assert.assertEquals("more stuff",convert(dst));
  Assert.assertFalse(decoder.isCompleted());
  Assert.assertEquals(16,metrics.getBytesTransferred());
  dst.clear();
  bytesRead=decoder.read(dst);
  Assert.assertEquals(-1,bytesRead);
  Assert.assertTrue(decoder.isCompleted());
  Assert.assertEquals(16,metrics.getBytesTransferred());
  dst.clear();
  bytesRead=decoder.read(dst);
  Assert.assertEquals(-1,bytesRead);
  Assert.assertTrue(decoder.isCompleted());
  Assert.assertEquals(16,metrics.getBytesTransferred());
}
