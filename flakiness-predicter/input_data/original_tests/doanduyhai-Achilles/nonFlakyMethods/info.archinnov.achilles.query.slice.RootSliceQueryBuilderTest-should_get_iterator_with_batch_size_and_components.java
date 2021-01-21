@Test public void should_get_iterator_with_batch_size_and_components() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  Object[] clusteringComponents=new Object[]{1,"name"};
  when(sliceQueryExecutor.iterator(anySliceQuery())).thenReturn(iterator);
  Iterator<ClusteredEntity> actual=builder.partitionComponentsInternal(partitionKey).iteratorWithComponents(7,clusteringComponents);
  assertThat(Whitebox.getInternalState(builder,"batchSize")).isEqualTo(7);
  assertThat(actual).isSameAs(iterator);
  assertThat(Whitebox.<List<Object>>getInternalState(builder,"fromClusterings")).containsExactly(clusteringComponents);
  assertThat(Whitebox.<List<Object>>getInternalState(builder,"toClusterings")).containsExactly(clusteringComponents);
}
