import java.util.ArrayList;

public class Solution {

	private String target;
	private ArrayList<Character> partialSolution;
	private int missingChars;

	public Solution(String target) {
		this.target = target;
		missingChars = target.length();
		partialSolution = new ArrayList<>(missingChars);
		for (int i = 0; i < target.length(); i++) {
			partialSolution.add('_');
		}
	}

	public boolean isSolved() {
		return missingChars == 0;
	}

	public void printProgress() {
		for (char c : partialSolution) {
			System.out.print(c + " ");
		}
		System.out.println();
	}

	public boolean addGuess(char guess, String pattern) {
		boolean guessCorrect = false;
		if (!pattern.equals("")) {
			for (int i = 0; i < pattern.length(); i++) {
				if (pattern.toCharArray()[i] != '_') {
					partialSolution.set(i, pattern.toCharArray()[i]);
					missingChars--;
				}
			}
			guessCorrect = true;
		}
		return guessCorrect;
	}

	public String getTarget() {
		StringBuilder sb = new StringBuilder(target.length());
		for (Character ch : partialSolution) {
			sb.append(ch);
		}
		return sb.toString();
	}
}
