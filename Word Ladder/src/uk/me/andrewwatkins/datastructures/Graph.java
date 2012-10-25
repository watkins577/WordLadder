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
		if (nodes.values().contains(node)) {
			System.err.println("Graph already contains this node");
			return;
		}
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
	
	/**
	 * Searches a queue for a certain node. Searches backwards so the provided returned stack is forward
	 * @param node1 The initial node - 
	 * @param node2
	 * @param retStack
	 * @return
	 */
	public boolean breadthFirstSearch(Node<Type> node1, Node<Type> node2, Stack<Node<Type>> retStack) {
		Queue<Node<Type>> q = new LinkedList<Node<Type>>(); //The queue on which the breadthfirstsearch works
		Stack<Node<Type>> visStack = new Stack<Node<Type>>(); //A stack of all visited nodes
		HashMap<Node<Type>, Node<Type>> parentList = new HashMap<Node<Type>, Node<Type>>(); //A map of all nodes and their parents. Used as parentList.put(child, parent)
		boolean found = false; //A boolean of if node1 has been found
		
		//Adds the initial node
		q.add(node2);
		visStack.add(node2);
		parentList.put(node2, null);
		
		//Iterates the queue while it has at least one element
		while (!q.isEmpty()) {
			
			//Removes the current object from the queue
			Node<Type> n = q.remove();
			
			//Breaks from the loop if the node is found
			if (n == node1) {
				found = true;
				break;
			}
			
			//Iterates through the nodes "children"
			for (Node<Type> node : n.getConnectedNodes().keySet()) {
				
				//If the child node hasn't been visited, add it to the visited list, queue, and set it's parent
				if (!visStack.contains(node)) {
					visStack.add(node);
					q.add(node);
					parentList.put(node, n);
				}
			}
		}
		
		//If the node has been found, iterate back up the parents to get the path
		if (found) {
			Node<Type> n = node1;
			retStack.add(n);
			
			//While there is a parent node available, add it to the returning stack
			while (parentList.get(n) != null) {
				n = parentList.get(n);
				retStack.add(n);
			}
			return true;
		}
		return false;
	}
}