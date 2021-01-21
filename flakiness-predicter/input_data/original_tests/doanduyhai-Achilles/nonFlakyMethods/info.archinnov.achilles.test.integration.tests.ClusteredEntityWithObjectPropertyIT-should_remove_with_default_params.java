@Test public void should_remove_with_default_params() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  insertValues(partitionKey,3);
  manager.sliceQuery(ClusteredEntityWithObjectValue.class).partitionComponents(partitionKey).fromClusterings("name2").toClusterings("name2").remove();
  List<ClusteredEntityWithObjectValue> entities=manager.sliceQuery(ClusteredEntityWithObjectValue.class).partitionComponents(partitionKey).get(100);
  assertThat(entities).hasSize(2);
  assertThat(entities.get(0).getValue().getContent()).isEqualTo("name1");
  assertThat(entities.get(1).getValue().getContent()).isEqualTo("name3");
}
