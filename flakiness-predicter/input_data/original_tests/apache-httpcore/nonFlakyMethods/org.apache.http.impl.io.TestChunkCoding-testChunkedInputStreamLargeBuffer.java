@Test public void testChunkedInputStreamLargeBuffer() throws IOException {
  ChunkedInputStream in=new ChunkedInputStream(new SessionInputBufferMock(EncodingUtils.getBytes(CHUNKED_INPUT,CONTENT_CHARSET)));
  byte[] buffer=new byte[300];
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  int len;
  while ((len=in.read(buffer)) > 0) {
    out.write(buffer,0,len);
  }
  Assert.assertEquals(-1,in.read(buffer));
  Assert.assertEquals(-1,in.read(buffer));
  in.close();
  String result=EncodingUtils.getString(out.toByteArray(),CONTENT_CHARSET);
  Assert.assertEquals(result,CHUNKED_RESULT);
  Header[] footers=in.getFooters();
  Assert.assertNotNull(footers);
  Assert.assertEquals(2,footers.length);
  Assert.assertEquals("Footer1",footers[0].getName());
  Assert.assertEquals("abcde",footers[0].getValue());
  Assert.assertEquals("Footer2",footers[1].getName());
  Assert.assertEquals("fghij",footers[1].getValue());
}
