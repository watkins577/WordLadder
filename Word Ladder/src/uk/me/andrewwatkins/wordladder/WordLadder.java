package uk.me.andrewwatkins.wordladder;



import java.util.ArrayList;
import java.util.List;

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
		int steps = KeyboardInput.getInstance().readInt();
		if (steps < 1) {
			System.err.println("Too few steps, minimum of 1");
			return;
		}
		
		List<String> wordList = FileInput.getInstance().readFile("dictionary/dict" + word.length() + ".dat");
		if (!wordList.contains(word)) {
			System.err.println("Word is not in the dictionary");
			return;
		}
		
		generateGraph(wordList);
		System.out.println(wordGraph);
		
		int i = 0, curSteps = 0, wordIndex = 0;
		String curString;
		List<String> curList = new ArrayList<String>();
		curList.add(word);
		
		for (i = 0; i < wordGraph.size(); i++) {
			if (wordList.get(i).equals(word)) {
				wordIndex = i;
				break;
			}
		}
		
		for (i = 0; i < steps; i++) {
			
		}
		/*while (i < wordGraph.size() || curSteps < steps) {
			curString = wordGraph.get
			if (isSimilar(curString, word) && !curList.contains(curString)) {
				curSteps ++;
				curList.add(curString);
				word = curString;
				i = -1;
			}
			
			i++;
		}*/
		
		System.out.println(curList);
		//System.out.println("GENERATE");
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
		
		
		System.out.println("DISCOVER");
	}
	
	public void generateGraph(List<String> dictionary) {
		int i, j;
		wordGraph = new Graph<String>();
		
		for (i = 0; i < dictionary.size(); i++) {
			wordGraph.addNode(new Node<String>(dictionary.get(i)));
		}
		
		for (i = 0; i < dictionary.size(); i++) {
			for (j = i+1; j < dictionary.size(); j++) {
				if (isSimilar(dictionary.get(i), dictionary.get(j))) {
					wordGraph.addBidirectionalEdge(wordGraph.getNodeAt(i), wordGraph.getNodeAt(j));
				}
			}
		}
	}

}
