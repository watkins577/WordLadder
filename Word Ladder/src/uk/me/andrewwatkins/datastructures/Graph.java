package uk.me.andrewwatkins.datastructures;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph<Type> {

	private HashMap<Integer, Node<Type>> nodes;
	
	public Graph () {
		nodes = new HashMap<Integer, Node<Type>>();
	}
	
	public void addNode(int key, Node<Type> node) {
		nodes.put(key, node);
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
	
	public boolean depthFirstSearch(Node<Type> n, int i, Stack<Node<Type>> retStack) {
		if (retStack.contains(n)) {
			return false;
		}
		retStack.push(n);
		if (retStack.size() > i) {
			return true;
		}
		
		for (Node<Type> node : n.getConnectedNodes().keySet()) {
			if (depthFirstSearch(node, i, retStack)) {
				return true;
			}
		}
		
		retStack.pop();
		return false;
	}
	
	public boolean depthFirstSearch(Node<Type> n1, Node<Type> n2, Stack<Node<Type>> retStack) {
		if (retStack.contains(n1)) {
			return false;
		}
		retStack.push(n1);
		if (n1 == n2) {
			return true;
		}
		
		for (Node<Type> node : n1.getConnectedNodes().keySet()) {
			
		
			if (depthFirstSearch(node, n2, retStack)) {
				return true;
			}
		}
		
		retStack.pop();
		return false;
	}
	
	public boolean breadthFirstSearch(Node<Type> n1, Node<Type> n2, Stack<Node<Type>> retStack) {
		Queue<Node<Type>> q = new LinkedList<Node<Type>>();
		Stack<Node<Type>> visStack = new Stack<Node<Type>>();
		q.add(n1);
		visStack.add(n1);
		while (!q.isEmpty()) {
			Node<Type> n = q.remove();
			retStack.add(n);
			if (n == n2) {
				return true;
			}
			for (Node<Type> node : n.getConnectedNodes().keySet()) {
				if (!visStack.contains(node)) {
					visStack.add(node);
					q.add(node);
				}
			}
		}
		
		System.out.println();
		System.err.println("Couldn't reach second node.");
		return false;
	}
}