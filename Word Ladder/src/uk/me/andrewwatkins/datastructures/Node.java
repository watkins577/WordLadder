package uk.me.andrewwatkins.datastructures;

import java.util.HashMap;

public class Node<Type> {

	private HashMap<Node<Type>, Integer> connectedNodes;
	
	private Type value;
	
	public Node (Type val) {
		value = val;
		connectedNodes = new HashMap<Node<Type>, Integer>();
	}
	
	/**
	 * Adds an "unweighted" edge between this node and another
	 * @param node The node to add the edge to
	 */
	public void addEdge(Node<Type> node) {
		addEdge(node, 1);
	}
	
	/**
	 * Adds an edge between this node and another
	 * @param node The node to add the edge to
	 * @param weight The weight of the edge
	 */
	public void addEdge(Node<Type> node, int weight) {
		//Checks for negative weight
		if (weight < 0) {
			System.err.println("Cannot have a negative weight");
			return;
		}
		//Checks for an already connected node
		if (isConnectedTo(node)) {
			System.err.println("This node has already been added");
			return;
		}
		connectedNodes.put(node, weight);
	}
	
	/**
	 * Gets the value of the node
	 * @return The value of the node
	 */
	public Type getValue() {
		return value;
	}
	
	/**
	 * Checks if this node is connected to another. It is not connected if the weight is equal to 0, or it cannot find the node
	 * @param node The node to check
	 * @return True if the node exists in connectedNodes, and the weight is greater than 0, false otherwise
	 */
	public boolean isConnectedTo(Node<Type> node) {
		return (connectedNodes.get(node) != null) && !(connectedNodes.get(node) == 0);
	}
	
	/**
	 * Retrieves a hashmap of the nodes connected to this one
	 * @return Hashmap of all nodes connected to this one with corresponding weight
	 */
	public HashMap<Node<Type>, Integer> getConnectedNodes() {
		return connectedNodes;
	}
	public String toString() {
		return value.toString();
	}

}
