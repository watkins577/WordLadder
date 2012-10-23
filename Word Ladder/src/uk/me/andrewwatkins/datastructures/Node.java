package uk.me.andrewwatkins.datastructures;

import java.util.HashMap;

public class Node<Type> {

	private HashMap<Node<Type>, Integer> connectedNodes;
	
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
		if (connectedNodes.get(node) != null) {
			System.err.println("This node has already been added");
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
	
	public HashMap<Node<Type>, Integer> getConnectedNodes() {
		return connectedNodes;
	}
	
	public String toString() {
		return value.toString();
	}

}
