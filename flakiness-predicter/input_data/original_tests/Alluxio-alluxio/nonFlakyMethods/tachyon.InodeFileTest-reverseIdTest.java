@Test public void reverseIdTest(){
  InodeFile inode1=new InodeFile("test1",1,0,1000,System.currentTimeMillis());
  inode1.reverseId();
  Assert.assertEquals(-1,inode1.getId());
}
