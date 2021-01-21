@Test public void testAccessControlNodes(){
  NodeBuilder builder=EMPTY_NODE.builder();
  builder.setProperty("jcr:primaryType","rep:ACL",Type.NAME);
  assertEquals(48,getSize(builder));
  assertEquals(4,getAmortizedSize(builder));
  NodeBuilder deny=builder.child("deny");
  deny.setProperty("jcr:primaryType","rep:DenyACE",Type.NAME);
  deny.setProperty("rep:principalName","everyone");
  deny.setProperty(PropertyStates.createProperty("rep:privileges",ImmutableList.of("jcr:read"),Type.NAMES));
  assertEquals(176,getSize(builder));
  assertEquals(28,getAmortizedSize(builder));
  NodeBuilder allow=builder.child("allow");
  allow.setProperty("jcr:primaryType","rep:GrantACE");
  allow.setProperty("rep:principalName","administrators");
  allow.setProperty(PropertyStates.createProperty("rep:privileges",ImmutableList.of("jcr:all"),Type.NAMES));
  assertEquals(288,getSize(builder));
  assertEquals(72,getAmortizedSize(builder));
  NodeBuilder deny0=builder.child("deny0");
  deny0.setProperty("jcr:primaryType","rep:DenyACE",Type.NAME);
  deny0.setProperty("rep:principalName","everyone");
  deny0.setProperty("rep:glob","*/activities/*");
  builder.setProperty(PropertyStates.createProperty("rep:privileges",ImmutableList.of("jcr:read"),Type.NAMES));
  assertEquals(384,getSize(builder));
  assertEquals(108,getAmortizedSize(builder));
  NodeBuilder allow0=builder.child("allow0");
  allow0.setProperty("jcr:primaryType","rep:GrantACE");
  allow0.setProperty("rep:principalName","user-administrators");
  allow0.setProperty(PropertyStates.createProperty("rep:privileges",ImmutableList.of("jcr:all"),Type.NAMES));
  assertEquals(432,getSize(builder));
  assertEquals(136,getAmortizedSize(builder));
}
