package uk.me.andrewwatkins.datastructures;

import java.util.HashMap;
import java.util.Map;

public class Node<Type> {

	private Map<Node<Type>, Integer> connectedNodes;
	
	private Type value;
	
	public Node (Type val) {
		value = val;
		connectedNodes = new HashMap<Node<Type>, Integer>();
	}
	
	public void addEdge(Node<Type> node) {
		addEdge(node, 1);
	}
	
	public void addEdge(Node<Type> node, int weight) {
		if (weight <= 0) {
			System.err.println("Cannot have a negative weight");
			return;
		}
		connectedNodes.put(node, weight);
	}
	
	public Type getValue() {
		return value;
	}
	
	public boolean isConnectedTo(Node<Type> node) {
		return (connectedNodes.get(node) != null) && (connectedNodes.get(node) != 0);
	}
	
	public Map<Node<Type>, Integer> getConnectedNodes() {
		return connectedNodes;
	}
	
	public String toString() {
		return value.toString();
	}

}
