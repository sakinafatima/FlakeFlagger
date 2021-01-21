@Test public void should_create_table_with_clustering_order() throws Exception {
  final String built=SchemaBuilder.createTable("test").addPartitionKey("id",DataType.bigint()).addClusteringKey("col1",DataType.uuid()).addClusteringKey("col2",DataType.uuid()).addColumn("name",DataType.text()).withOptions().clusteringOrder(new Create.Options.ClusteringOrder("col1",ASC),new Create.Options.ClusteringOrder("col2",DESC)).build();
  assertThat(built).isEqualTo("\n\tCREATE TABLE test(\n\t\t" + "id bigint,\n\t\t" + "col1 uuid,\n\t\t"+ "col2 uuid,\n\t\t"+ "name text,\n\t\t"+ "PRIMARY KEY(id, col1, col2))\n\t"+ "WITH CLUSTERING ORDER BY(col1 ASC, col2 DESC)");
}
