@Test public void testWriteNegative() throws IOException {
  ByteStoreFileChannel channel=channel(store(0),READ,WRITE);
  try {
    channel.write(buffer("111"),-1);
    fail();
  }
 catch (  IllegalArgumentException expected) {
  }
  ByteBuffer[] bufs={buffer("111"),buffer("111")};
  try {
    channel.write(bufs,-1,10);
    fail();
  }
 catch (  IndexOutOfBoundsException expected) {
  }
  try {
    channel.write(bufs,0,-1);
    fail();
  }
 catch (  IndexOutOfBoundsException expected) {
  }
}
