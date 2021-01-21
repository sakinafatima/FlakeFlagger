public void testQuery(){
  ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().includeProcessVariables().variableValueEquals("anothertest",123).singleResult();
  Map<String,Object> variableMap=processInstance.getProcessVariables();
  assertEquals(1,variableMap.size());
  assertEquals(123,variableMap.get("anothertest"));
  List<ProcessInstance> instanceList=runtimeService.createProcessInstanceQuery().includeProcessVariables().list();
  assertEquals(6,instanceList.size());
  processInstance=runtimeService.createProcessInstanceQuery().includeProcessVariables().variableValueLike("casetest","MyCase%").singleResult();
  variableMap=processInstance.getProcessVariables();
  assertEquals(1,variableMap.size());
  assertEquals("MyCaseTest",variableMap.get("casetest"));
  processInstance=runtimeService.createProcessInstanceQuery().includeProcessVariables().variableValueLikeIgnoreCase("casetest","mycase%").singleResult();
  variableMap=processInstance.getProcessVariables();
  assertEquals(1,variableMap.size());
  assertEquals("MyCaseTest",variableMap.get("casetest"));
  processInstance=runtimeService.createProcessInstanceQuery().includeProcessVariables().variableValueLikeIgnoreCase("casetest","mycase2%").singleResult();
  assertNull(processInstance);
  instanceList=runtimeService.createProcessInstanceQuery().includeProcessVariables().processDefinitionKey(PROCESS_DEFINITION_KEY).list();
  assertEquals(4,instanceList.size());
  processInstance=instanceList.get(0);
  variableMap=processInstance.getProcessVariables();
  assertEquals(2,variableMap.size());
  assertEquals("test",variableMap.get("test"));
  assertEquals("test2",variableMap.get("test2"));
  processInstance=runtimeService.createProcessInstanceQuery().includeProcessVariables().processDefinitionKey(PROCESS_DEFINITION_KEY_2).singleResult();
  variableMap=processInstance.getProcessVariables();
  assertEquals(1,variableMap.size());
  assertEquals(123,variableMap.get("anothertest"));
  instanceList=runtimeService.createProcessInstanceQuery().includeProcessVariables().processDefinitionKey(PROCESS_DEFINITION_KEY).listPage(0,5);
  assertEquals(4,instanceList.size());
  processInstance=instanceList.get(0);
  variableMap=processInstance.getProcessVariables();
  assertEquals(2,variableMap.size());
  assertEquals("test",variableMap.get("test"));
  assertEquals("test2",variableMap.get("test2"));
  instanceList=runtimeService.createProcessInstanceQuery().includeProcessVariables().processDefinitionKey(PROCESS_DEFINITION_KEY).listPage(0,1);
  assertEquals(1,instanceList.size());
  processInstance=instanceList.get(0);
  variableMap=processInstance.getProcessVariables();
  assertEquals(2,variableMap.size());
  assertEquals("test",variableMap.get("test"));
  assertEquals("test2",variableMap.get("test2"));
  instanceList=runtimeService.createProcessInstanceQuery().includeProcessVariables().processDefinitionKey(PROCESS_DEFINITION_KEY).orderByProcessDefinitionKey().asc().listPage(2,4);
  assertEquals(2,instanceList.size());
  processInstance=instanceList.get(0);
  variableMap=processInstance.getProcessVariables();
  assertEquals(2,variableMap.size());
  assertEquals("test",variableMap.get("test"));
  assertEquals("test2",variableMap.get("test2"));
  instanceList=runtimeService.createProcessInstanceQuery().includeProcessVariables().processDefinitionKey(PROCESS_DEFINITION_KEY).orderByProcessDefinitionKey().asc().listPage(4,5);
  assertEquals(0,instanceList.size());
}
