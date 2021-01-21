public void testExlusiveGateway(){
  deployStartProcessInstanceAndProfile("process05.bpmn20.xml","process05");
  assertDatabaseSelects("StartProcessInstanceCmd","selectLatestProcessDefinitionByKey",1L);
  assertDatabaseInserts("StartProcessInstanceCmd","HistoricActivityInstanceEntityImpl-bulk-with-5",1L,"HistoricProcessInstanceEntityImpl",1L,"HistoricVariableInstanceEntityImpl",1L);
  assertNoUpdatesAndDeletes("StartProcessInstanceCmd");
  Assert.assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  Assert.assertEquals(1,historyService.createHistoricProcessInstanceQuery().finished().count());
}
