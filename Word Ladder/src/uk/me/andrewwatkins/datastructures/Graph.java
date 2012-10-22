package uk.me.andrewwatkins.datastructures;

import java.util.ArrayList;
import java.util.List;

public class Graph<Type> {

	private List<Node<Type>> nodes;
	
	public Graph () {
		nodes = new ArrayList<Node<Type>>();
	}
	
	public void addNode(Node<Type> node) {
		nodes.add(node);
	}
	
	public void addEdge(Node<Type> n1, Node<Type> n2) {
		addEdge(n1, n2, 1);
	}
	
	public void addEdge(Node<Type> n1, Node<Type> n2, int weight) {
		n1.addEdge(n2, weight);
	}
	
	public void addBidirectionalEdge(Node<Type> n1, Node<Type> n2) {
		addBidirectionalEdge(n1, n2, 1);
	}
	
	public void addBidirectionalEdge(Node<Type> n1, Node<Type> n2, int weight) {
		n1.addEdge(n2, weight);
		n2.addEdge(n1, weight);
	}
	
	public Node<Type> getNodeAt(int i) {
		return nodes.get(i);
	}
	
	public String toString() {
		return nodes.toString();
	}
	
	public int size() {
		return nodes.size();
	}
}
