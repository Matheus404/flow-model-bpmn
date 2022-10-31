package com.gtt.model.flowmodelbpmn.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gtt.model.flowmodelbpmn.bean.Flow;
import com.gtt.model.flowmodelbpmn.bean.Node;
import com.gtt.model.flowmodelbpmn.util.InputJson;
import com.gtt.model.flowmodelbpmn.util.XmlOutput;

@RestController
@RequestMapping
public class WorkFlowController {

	private static final ObjectMapper MAPPER = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	private static final List<String> TASK_SUPPORTED_TYPES = Arrays.asList("STEP", "PAGE", "DECISION");

	@PostMapping("/convert")
	public XmlOutput conrvertJsonToXml(@RequestBody InputJson inputJson) throws IOException {
		
		XmlOutput xmlOutput = new XmlOutput();
		String json = inputJson.getJson();
		
		Flow flow = MAPPER.readValue(json, Flow.class);

		BpmnModel model = new BpmnModel();
		Process process = new Process();

		model.addProcess(process);

		process.setName("teste1");
		process.setId("processId");
		build(process, flow);
		new BpmnAutoLayout(model).execute();

		String xml = new String(new BpmnXMLConverter().convertToXML(model));
		
		xmlOutput.setXml(xml);
		return xmlOutput;
	}

	private static void build(Process process, Flow flow) {
		flow.getNodes().forEach(node -> createElement(process, node));
		flow.getEdges().forEach(edge -> process.addFlowElement(connect(edge.getSourceNode(), edge.getTargetNode())));
	}

	private static void createElement(Process process, Node node) {
		if ("Start".equals(node.getLabel())) {
			StartEvent startEvent = new StartEvent();
			startEvent.setId(node.getId());
			process.addFlowElement(startEvent);
		} else if (TASK_SUPPORTED_TYPES.contains(node.getType())) {
			ServiceTask serviceTask = new ServiceTask();
			serviceTask.setId(node.getId());
			process.addFlowElement(serviceTask);
		}

		if (node.getSubFlow() != null) {
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
