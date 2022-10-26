package com.gtt.model.flowmodelbpmn.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Node {
	private String label;
	private String id;
	private String type;
	private Flow subFlow;
}
