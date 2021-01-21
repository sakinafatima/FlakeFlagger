@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testTaskAssignmentEventInProcess() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNotNull(processInstance);
  listener.clearEventsReceived();
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  taskService.setAssignee(task.getId(),"kermit");
  assertEquals(2,listener.getEventsReceived().size());
  ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.TASK_ASSIGNED,event.getType());
  assertTrue(event.getEntity() instanceof Task);
  Task taskFromEvent=(Task)event.getEntity();
  assertEquals(task.getId(),taskFromEvent.getId());
  assertEquals("kermit",taskFromEvent.getAssignee());
  assertExecutionDetails(event,processInstance);
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ENTITY_UPDATED,event.getType());
  assertTrue(event.getEntity() instanceof Task);
  assertExecutionDetails(event,processInstance);
  listener.clearEventsReceived();
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  task.setAssignee("newAssignee");
  taskService.saveTask(task);
  assertEquals(2,listener.getEventsReceived().size());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.TASK_ASSIGNED,event.getType());
  assertTrue(event.getEntity() instanceof Task);
  taskFromEvent=(Task)event.getEntity();
  assertEquals(task.getId(),taskFromEvent.getId());
  assertEquals("newAssignee",taskFromEvent.getAssignee());
  assertExecutionDetails(event,processInstance);
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ENTITY_UPDATED,event.getType());
  assertTrue(event.getEntity() instanceof Task);
  assertExecutionDetails(event,processInstance);
  listener.clearEventsReceived();
  taskService.unclaim(task.getId());
  assertEquals(2,listener.getEventsReceived().size());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.TASK_ASSIGNED,event.getType());
  assertTrue(event.getEntity() instanceof Task);
  taskFromEvent=(Task)event.getEntity();
  assertEquals(task.getId(),taskFromEvent.getId());
  assertEquals(null,taskFromEvent.getAssignee());
  assertExecutionDetails(event,processInstance);
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ENTITY_UPDATED,event.getType());
  assertTrue(event.getEntity() instanceof Task);
  assertExecutionDetails(event,processInstance);
  listener.clearEventsReceived();
}
