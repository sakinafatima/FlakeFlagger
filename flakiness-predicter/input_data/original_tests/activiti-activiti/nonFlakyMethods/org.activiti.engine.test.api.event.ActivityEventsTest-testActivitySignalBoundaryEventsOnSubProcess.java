@Deployment public void testActivitySignalBoundaryEventsOnSubProcess() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("signalOnSubProcess");
  assertNotNull(processInstance);
  Execution executionWithSignal=runtimeService.createExecutionQuery().activityId("userTaskInsideProcess").singleResult();
  assertNotNull(executionWithSignal);
  runtimeService.signalEventReceived("signalName");
  assertEquals(3,listener.getEventsReceived().size());
  assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiSignalEventImpl);
  ActivitiSignalEventImpl signalEvent=(ActivitiSignalEventImpl)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ACTIVITY_SIGNALED,signalEvent.getType());
  assertEquals("boundarySignalEventCatching",signalEvent.getActivityId());
  assertEquals(executionWithSignal.getProcessInstanceId(),signalEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),signalEvent.getProcessDefinitionId());
  assertTrue(listener.getEventsReceived().get(1) instanceof ActivitiActivityCancelledEvent);
  ActivitiActivityCancelledEvent cancelEvent=(ActivitiActivityCancelledEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ACTIVITY_CANCELLED,cancelEvent.getType());
  assertEquals("subProcess",cancelEvent.getActivityId());
  assertEquals(executionWithSignal.getProcessInstanceId(),cancelEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),cancelEvent.getProcessDefinitionId());
  assertNotNull(cancelEvent.getCause());
  assertTrue(cancelEvent.getCause() instanceof SignalEventSubscriptionEntity);
  SignalEventSubscriptionEntity cause=(SignalEventSubscriptionEntity)cancelEvent.getCause();
  assertEquals("signalName",cause.getEventName());
  assertTrue(listener.getEventsReceived().get(2) instanceof ActivitiActivityCancelledEvent);
  cancelEvent=(ActivitiActivityCancelledEvent)listener.getEventsReceived().get(2);
  assertEquals(ActivitiEventType.ACTIVITY_CANCELLED,cancelEvent.getType());
  assertEquals("userTaskInsideProcess",cancelEvent.getActivityId());
  assertEquals(executionWithSignal.getId(),cancelEvent.getExecutionId());
  assertEquals(executionWithSignal.getProcessInstanceId(),cancelEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),cancelEvent.getProcessDefinitionId());
  assertNotNull(cancelEvent.getCause());
  assertTrue(cancelEvent.getCause() instanceof SignalEventSubscriptionEntity);
  cause=(SignalEventSubscriptionEntity)cancelEvent.getCause();
  assertEquals("signalName",cause.getEventName());
}
