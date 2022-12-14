package com.gtt.model.flowmodelbpmn;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.ServiceTask;
import org.activiti.bpmn.model.StartEvent;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gtt.model.flowmodelbpmn.bean.Flow;
import com.gtt.model.flowmodelbpmn.bean.Node;

public class BpmnBuilder {
	
	private static final ObjectMapper MAPPER = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	private static final List<String> TASK_SUPPORTED_TYPES = Arrays.asList("STEP", "PAGE", "DECISION");
	
	public static void main(String[] args) throws IOException {
        String json = "{\n" + "    \"id\":5,\n" + "    \"flow\":{\n" + "        \"id\":5\n" + "    },\n" + "    \"nodes\":[\n" + "        {\n" + "            \"id\":\"node-e4928847-d797-43d0-b8f4-95bb0eea6ae3\",\n" + "            \"label\":\"Start\",\n" + "            \"width\":150,\n" + "            \"height\":50,\n" + "            \"x\":230,\n" + "            \"y\":170,\n" + "            \"type\":\"STEP\",\n" + "            \"page\":null,\n" + "            \"subflow\":null\n" + "        },\n" + "        {\n" + "            \"id\":\"node-f05014d6-fe54-4a88-8941-c80eab5fea1c\",\n" + "            \"label\":null,\n" + "            \"width\":150,\n" + "            \"height\":50,\n" + "            \"x\":426,\n" + "            \"y\":170,\n" + "            \"type\":\"PAGE\",\n" + "            \"page\":{\n" + "                \"id\":1,\n" + "                \"name\":\"Test page 1\",\n" + "                \"content\":null\n" + "            },\n" + "            \"subflow\":null\n" + "        },\n" + "        {\n" + "            \"id\":\"node-f5581df2-ab8f-4a25-a50c-faac192dc066\",\n" + "            \"label\":null,\n" + "            \"width\":150,\n" + "            \"height\":50,\n" + "            \"x\":628,\n" + "            \"y\":170,\n" + "            \"type\":\"PAGE\",\n" + "            \"page\":{\n" + "                \"id\":2,\n" + "                \"name\":\"Test page 2\",\n" + "                \"content\":null\n" + "            },\n" + "            \"subflow\":null\n" + "        },\n" + "        {\n" + "            \"id\":\"node-0be2862d-85a6-427e-bf14-7ac25b2cb027\",\n" + "            \"label\":\"3\",\n" + "            \"width\":150,\n" + "            \"height\":50,\n" + "            \"x\":628,\n" + "            \"y\":67,\n" + "            \"type\":\"STEP\",\n" + "            \"page\":null,\n" + "            \"subflow\":null\n" + "        },\n" + "        {\n" + "            \"id\":\"node-cdf8eba0-11a7-41b5-a1c4-297b20cadc2f\",\n" + "            \"label\":\"4\",\n" + "            \"width\":150,\n" + "            \"height\":50,\n" + "            \"x\":628,\n" + "            \"y\":281,\n" + "            \"type\":\"STEP\",\n" + "            \"page\":null,\n" + "            \"subflow\":null\n" + "        },\n" + "        {\n" + "            \"id\":\"node-1176d027-674a-4c55-a37f-c73d077771c0\",\n" + "            \"label\":\"If\",\n" + "            \"width\":150,\n" + "            \"height\":50,\n" + "            \"x\":829,\n" + "            \"y\":170,\n" + "            \"type\":\"DECISION\",\n" + "            \"page\":null,\n" + "            \"subflow\":null\n" + "        },\n" + "        {\n" + "            \"id\":\"node-fa4f163d-7496-470f-a476-f1c99bb18f23\",\n" + "            \"label\":\"5\",\n" + "            \"width\":150,\n" + "            \"height\":50,\n" + "            \"x\":829,\n" + "            \"y\":281,\n" + "            \"type\":\"STEP\",\n" + "            \"page\":null,\n" + "            \"subflow\":null\n" + "        },\n" + "        {\n" + "            \"id\":\"node-023a4005-027c-4b1c-b424-01d7089f3ad2\",\n" + "            \"label\":\"6\",\n" + "            \"width\":150,\n" + "            \"height\":50,\n" + "            \"x\":1040,\n" + "            \"y\":170,\n" + "            \"type\":\"STEP\",\n" + "            \"page\":null,\n" + "            \"subflow\":null\n" + "        },\n" + "        {\n" + "            \"id\":\"node-ecb8b4e7-f1d4-45c7-bf3c-0ad066e0349f\",\n" + "            \"label\":\"End\",\n" + "            \"width\":150,\n" + "            \"height\":50,\n" + "            \"x\":1238,\n" + "            \"y\":170,\n" + "            \"type\":\"STEP\",\n" + "            \"page\":null,\n" + "            \"subflow\":null\n" + "        }\n" + "    ],\n" + "    \"edges\":[\n" + "        {\n" + "            \"id\":\"node-f05014d6-fe54-4a88-8941-c80eab5fea1c:node-f05014d6-fe54-4a88-8941-c80eab5fea1c-right-node-f5581df2-ab8f-4a25-a50c-faac192dc066:node-f5581df2-ab8f-4a25-a50c-faac192dc066-left\",\n" + "            \"targetPort\":\"node-f5581df2-ab8f-4a25-a50c-faac192dc066-left\",\n" + "            \"sourcePort\":\"node-f05014d6-fe54-4a88-8941-c80eab5fea1c-right\",\n" + "            \"targetNode\":\"node-f5581df2-ab8f-4a25-a50c-faac192dc066\",\n" + "            \"sourceNode\":\"node-f05014d6-fe54-4a88-8941-c80eab5fea1c\"\n" + "        },\n" + "        {\n" + "            \"id\":\"node-e4928847-d797-43d0-b8f4-95bb0eea6ae3:node-e4928847-d797-43d0-b8f4-95bb0eea6ae3-right-node-f05014d6-fe54-4a88-8941-c80eab5fea1c:node-f05014d6-fe54-4a88-8941-c80eab5fea1c-left\",\n" + "            \"targetPort\":\"node-f05014d6-fe54-4a88-8941-c80eab5fea1c-left\",\n" + "            \"sourcePort\":\"node-e4928847-d797-43d0-b8f4-95bb0eea6ae3-right\",\n" + "            \"targetNode\":\"node-f05014d6-fe54-4a88-8941-c80eab5fea1c\",\n" + "            \"sourceNode\":\"node-e4928847-d797-43d0-b8f4-95bb0eea6ae3\"\n" + "        },\n" + "        {\n" + "            \"id\":\"node-f5581df2-ab8f-4a25-a50c-faac192dc066:node-f5581df2-ab8f-4a25-a50c-faac192dc066-top-node-0be2862d-85a6-427e-bf14-7ac25b2cb027:node-0be2862d-85a6-427e-bf14-7ac25b2cb027-bottom\",\n" + "            \"targetPort\":\"node-0be2862d-85a6-427e-bf14-7ac25b2cb027-bottom\",\n" + "            \"sourcePort\":\"node-f5581df2-ab8f-4a25-a50c-faac192dc066-top\",\n" + "            \"targetNode\":\"node-0be2862d-85a6-427e-bf14-7ac25b2cb027\",\n" + "            \"sourceNode\":\"node-f5581df2-ab8f-4a25-a50c-faac192dc066\"\n" + "        },\n" + "        {\n" + "            \"id\":\"node-f5581df2-ab8f-4a25-a50c-faac192dc066:node-f5581df2-ab8f-4a25-a50c-faac192dc066-bottom-node-cdf8eba0-11a7-41b5-a1c4-297b20cadc2f:node-cdf8eba0-11a7-41b5-a1c4-297b20cadc2f-top\",\n" + "            \"targetPort\":\"node-cdf8eba0-11a7-41b5-a1c4-297b20cadc2f-top\",\n" + "            \"sourcePort\":\"node-f5581df2-ab8f-4a25-a50c-faac192dc066-bottom\",\n" + "            \"targetNode\":\"node-cdf8eba0-11a7-41b5-a1c4-297b20cadc2f\",\n" + "            \"sourceNode\":\"node-f5581df2-ab8f-4a25-a50c-faac192dc066\"\n" + "        },\n" + "        {\n" + "            \"id\":\"node-f5581df2-ab8f-4a25-a50c-faac192dc066:node-f5581df2-ab8f-4a25-a50c-faac192dc066-right-node-1176d027-674a-4c55-a37f-c73d077771c0:node-1176d027-674a-4c55-a37f-c73d077771c0-left\",\n" + "            \"targetPort\":\"node-1176d027-674a-4c55-a37f-c73d077771c0-left\",\n" + "            \"sourcePort\":\"node-f5581df2-ab8f-4a25-a50c-faac192dc066-right\",\n" + "            \"targetNode\":\"node-1176d027-674a-4c55-a37f-c73d077771c0\",\n" + "            \"sourceNode\":\"node-f5581df2-ab8f-4a25-a50c-faac192dc066\"\n" + "        },\n" + "        {\n" + "            \"id\":\"node-1176d027-674a-4c55-a37f-c73d077771c0:node-1176d027-674a-4c55-a37f-c73d077771c0-bottom-node-fa4f163d-7496-470f-a476-f1c99bb18f23:node-fa4f163d-7496-470f-a476-f1c99bb18f23-top\",\n" + "            \"targetPort\":\"node-fa4f163d-7496-470f-a476-f1c99bb18f23-top\",\n" + "            \"sourcePort\":\"node-1176d027-674a-4c55-a37f-c73d077771c0-bottom\",\n" + "            \"targetNode\":\"node-fa4f163d-7496-470f-a476-f1c99bb18f23\",\n" + "            \"sourceNode\":\"node-1176d027-674a-4c55-a37f-c73d077771c0\"\n" + "        },\n" + "        {\n" + "            \"id\":\"node-1176d027-674a-4c55-a37f-c73d077771c0:node-1176d027-674a-4c55-a37f-c73d077771c0-right-node-023a4005-027c-4b1c-b424-01d7089f3ad2:node-023a4005-027c-4b1c-b424-01d7089f3ad2-left\",\n" + "            \"targetPort\":\"node-023a4005-027c-4b1c-b424-01d7089f3ad2-left\",\n" + "            \"sourcePort\":\"node-1176d027-674a-4c55-a37f-c73d077771c0-right\",\n" + "            \"targetNode\":\"node-023a4005-027c-4b1c-b424-01d7089f3ad2\",\n" + "            \"sourceNode\":\"node-1176d027-674a-4c55-a37f-c73d077771c0\"\n" + "        },\n" + "        {\n" + "            \"id\":\"node-023a4005-027c-4b1c-b424-01d7089f3ad2:node-023a4005-027c-4b1c-b424-01d7089f3ad2-right-node-ecb8b4e7-f1d4-45c7-bf3c-0ad066e0349f:node-ecb8b4e7-f1d4-45c7-bf3c-0ad066e0349f-left\",\n" + "            \"targetPort\":\"node-ecb8b4e7-f1d4-45c7-bf3c-0ad066e0349f-left\",\n" + "            \"sourcePort\":\"node-023a4005-027c-4b1c-b424-01d7089f3ad2-right\",\n" + "            \"targetNode\":\"node-ecb8b4e7-f1d4-45c7-bf3c-0ad066e0349f\",\n" + "            \"sourceNode\":\"node-023a4005-027c-4b1c-b424-01d7089f3ad2\"\n" + "        }\n" + "    ]\n" + "}";
        
        Flow flow = MAPPER.readValue(json, Flow.class);
        
        BpmnModel model = new BpmnModel();
        Process process = new Process();
        
        model.addProcess(process);
        
        process.setName("teste1");
        process.setId("processId");
        build(process, flow);
        new BpmnAutoLayout(model).execute();
        
        String xml = new String(new BpmnXMLConverter().convertToXML(model));
        
        System.out.println(xml);
	}
	
	private static void build(Process process, Flow flow) {
		flow.getNodes().forEach(node -> createElement(process, node));
		flow.getEdges().forEach(edge -> process.addFlowElement(connect(edge.getSourceNode(), edge.getTargetNode())));
	}
	
	private static void createElement(Process process, Node node) {
		if("Start".equals(node.getLabel())) {
			StartEvent startEvent = new StartEvent();
			startEvent.setId(node.getId());
			process.addFlowElement(startEvent);
		} else if(TASK_SUPPORTED_TYPES.contains(node.getType())) {
			ServiceTask serviceTask = new ServiceTask();
			serviceTask.setId(node.getId());
			process.addFlowElement(serviceTask);
		}
		
		if(node.getSubFlow() != null) {
			build(process, node.getSubFlow());
		}
	}
	
	protected static SequenceFlow connect(String from, String to) {
		SequenceFlow flow = new SequenceFlow();
		flow.setId("sequence" + "_" + UUID.randomUUID().toString().replace("-", "").toLowerCase());
		flow.setSourceRef(from);
		flow.setTargetRef(to);
		return flow;
	}
	
}
