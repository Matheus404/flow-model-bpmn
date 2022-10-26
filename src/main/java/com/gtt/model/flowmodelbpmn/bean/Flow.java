package com.gtt.model.flowmodelbpmn.bean;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Flow {
	private List<Node> nodes;
	private List<Edge> edges;
}
