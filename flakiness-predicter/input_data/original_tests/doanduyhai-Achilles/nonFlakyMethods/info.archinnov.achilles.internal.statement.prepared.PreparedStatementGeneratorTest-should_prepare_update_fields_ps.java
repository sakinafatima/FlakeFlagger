@Test public void should_prepare_update_fields_ps() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(PropertyType.SIMPLE).build();
  PropertyMeta nameMeta=completeBean(Void.class,String.class).field("name").type(PropertyType.SIMPLE).build();
  PropertyMeta ageMeta=completeBean(Void.class,String.class).field("age").type(PropertyType.SIMPLE).build();
  EntityMeta meta=new EntityMeta();
  meta.setTableName("table");
  meta.setIdMeta(idMeta);
  when(session.prepare(queryCaptor.capture())).thenReturn(ps);
  PreparedStatement actual=generator.prepareUpdateFields(session,meta,asList(nameMeta,ageMeta),ifConditions(new CASCondition("name","John")).withTimestamp(100L));
  assertThat(actual).isSameAs(ps);
  assertThat(queryCaptor.getValue()).isEqualTo("UPDATE table USING TTL :ttl AND TIMESTAMP :timestamp SET name=:name,age=:age WHERE id=:id IF name=:name;");
}
