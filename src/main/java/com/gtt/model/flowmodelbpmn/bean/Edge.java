package com.gtt.model.flowmodelbpmn.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Edge {
	private String sourceNode;
	private String targetNode;
}
