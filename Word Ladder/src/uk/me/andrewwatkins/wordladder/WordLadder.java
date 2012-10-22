package uk.me.andrewwatkins.wordladder;

import java.util.List;
import java.util.Stack;

import uk.me.andrewwatkins.datastructures.Graph;
import uk.me.andrewwatkins.datastructures.Node;
import uk.me.andrewwatkins.input.FileInput;
import uk.me.andrewwatkins.input.KeyboardInput;

public class WordLadder {
	
	private Graph<String> wordGraph;

	public void start() {
		System.out.println("WELCOME TO WORD LADDER!");
		char menuInput;
		do {
			System.out.println("[G]enerate a word ladder, [D]iscover a word ladder, or [E]xit");
			menuInput = KeyboardInput.getInstance().readLowerCaseChar();
			switch(menuInput) {
				case 'g': generate(); break;
				case 'd': discover();
				case 'e': break;
				default: System.out.println("Please input either G, D or E!");
			}
		} while(menuInput != 'e');
	}
	
	/**
	 * Compares two strings to check if there is only 1 character different
	 * @param s1 The first string to compare
	 * @param s2 The second string to compare
	 * @return false if the length is different, the strings are identical, or they are too different. true otherwise
	 */
	public boolean isSimilar(String s1, String s2) {
		int numDiffs = 0;
		if (s1.length() != s2.length()) {
			numDiffs = -1;
		}
		
		for (int i = 0; i < s1.length() && (numDiffs == 0 || numDiffs == 1); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				numDiffs++;
			}
		}
		
		return numDiffs == 1;
	}
	
	public void generate() {
		System.out.print("Enter the word: ");
		String word = KeyboardInput.getInstance().readString();
		if (!(word.length() >= 2 && word.length() <= 9)) {
			System.err.println("The word is not between 2 and 9 letters.");
			return;
		}
		
		System.out.print("How many steps? ");
		final int steps = KeyboardInput.getInstance().readInt();
		if (steps < 1) {
			System.err.println("Too few steps, minimum of 1");
			return;
		}
		
		List<String> wordList = FileInput.getInstance().readFile("dictionary/dict" + word.length() + ".dat");
		if (!wordList.contains(word)) {
			System.err.println("Word is not in the dictionary");
			return;
		}
		
		wordGraph = generateGraph(wordList);
		int wordAt = -1;
		for (int i = 0; i < wordGraph.size(); i++) {
			if (wordGraph.getNodeAt(i).getValue().equals(word)) {
				wordAt = i;
				break;
			}
		}
		if (wordAt == -1) {
			System.err.print("Something happened, everything broke, arms spaghetti!");
			return;
		}
		Stack<Node<String>> words = new Stack<Node<String>>();
		wordGraph.depthFirstSearch(wordGraph.getNodeAt(wordAt), steps, words);
		if (words.size() == 0) {
			System.err.println("Could not work get a path of length " + steps + " unfortunately. Try a shorter amount.");
		}
		System.out.println(words);
	}
	
	public void discover() {
		System.out.print("Enter the first word: ");
		String word1 = KeyboardInput.getInstance().readString();
		if (!(word1.length() >= 2 && word1.length() <= 9)) {
			System.err.println("The word is not between 2 and 9 letters.");
			return;
		}
		
		System.out.print("Enter the second word: ");
		String word2 = KeyboardInput.getInstance().readString();
		if (word1.length() != word2.length()) {
			System.err.println("The length of the words are not the same.");
			return;
		}
		
		List<String> wordList = FileInput.getInstance().readFile("dictionary/dict" + word1.length() + ".dat");
		if (!wordList.contains(word1) || !wordList.contains(word2)) {
			System.err.println("One of the words are not in the dictionary");
			return;
		}
		
		wordGraph = generateGraph(wordList);
		
		int wordAt1 = -1;
		int wordAt2 = -1;
		for (int i = 0; i < wordGraph.size(); i++) {
			if (wordGraph.getNodeAt(i).getValue().equals(word1)) {
				wordAt1 = i;
				break;
			}
		}
		if (wordAt1 == -1) {
			System.err.print("Something happened, everything broke, arms spaghetti!");
			return;
		}
		
		for (int i = 0; i < wordGraph.size(); i++) {
			if (wordGraph.getNodeAt(i).getValue().equals(word2)) {
				wordAt2 = i;
				break;
			}
		}
		if (wordAt2 == -1) {
			System.err.print("Something happened, everything broke, arms spaghetti!");
			return;
		}
		
		Stack<Node<String>> words = new Stack<Node<String>>();
		wordGraph.breadthFirstSearch(wordGraph.getNodeAt(wordAt1), wordGraph.getNodeAt(wordAt2), words);
		System.out.println(words);
	}
	
	public Graph<String> generateGraph(List<String> dictionary) {
		int i, j;
		Graph<String> g = new Graph<String>();
		
		for (i = 0; i < dictionary.size(); i++) {
			g.addNode(i, new Node<String>(dictionary.get(i)));
		}
		
		for (i = 0; i < dictionary.size(); i++) {
			for (j = i+1; j < dictionary.size(); j++) {
				if (isSimilar(dictionary.get(i), dictionary.get(j))) {
					g.addBidirectionalEdge(g.getNodeAt(i), g.getNodeAt(j));
				}
			}
		}
		
		return g;
	}

}
