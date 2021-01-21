@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testQueryByDeploymentIdIn() throws Exception {
  org.activiti.engine.repository.Deployment deployment=repositoryService.createDeploymentQuery().singleResult();
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  List<String> deploymentIds=new ArrayList<String>();
  deploymentIds.add(deployment.getId());
  assertNotNull(taskService.createTaskQuery().deploymentIdIn(deploymentIds).singleResult());
  assertEquals(1,taskService.createTaskQuery().deploymentIdIn(deploymentIds).count());
  deploymentIds.add("invalid");
  assertNotNull(taskService.createTaskQuery().deploymentIdIn(deploymentIds).singleResult());
  assertEquals(1,taskService.createTaskQuery().deploymentIdIn(deploymentIds).count());
  deploymentIds=new ArrayList<String>();
  deploymentIds.add("invalid");
  assertNull(taskService.createTaskQuery().deploymentIdIn(deploymentIds).singleResult());
  assertEquals(0,taskService.createTaskQuery().deploymentIdIn(deploymentIds).count());
}
