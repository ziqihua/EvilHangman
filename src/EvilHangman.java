import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class EvilHangman {
    private HashSet<Character> validInput;
    private ArrayList<String> wordList;
    private HashSet<Character> previousGuesses;
    private TreeSet<Character> incorrectGuesses; // behaves like a hash set, but orders the entries!
    private Solution solution;
    private Scanner inputScanner;
    private int targetLength;
    private ArrayList<String> proceedWords;

    public EvilHangman() {
        this("engDictionary.txt");
    }

    public EvilHangman(String filename) {
        try {
            wordList = dictionaryToList(filename);
        } catch (IOException e) {
            System.out.printf(
                    "Couldn't read from the file %s. Verify that you have it in the right place and try running again.",
                    filename);
            e.printStackTrace();
            System.exit(0); // stop the program--no point in trying if you don't have a dictionary
        }

        previousGuesses = new HashSet<>();
        incorrectGuesses = new TreeSet<>();
        int randomIndex = new Random().nextInt(wordList.size());
        String target = wordList.get(randomIndex);
        targetLength = target.length();
        proceedWords = new ArrayList<>();

        solution = new Solution(target);
        inputScanner = new Scanner(System.in);
    }

    public void start() {
        defineValidInput();
        proceedWords = getSameLengthWords();
        HashMap<String, ArrayList<String>> guessWordFamily;
        while (!solution.isSolved()) {
            char guess = promptForGuess();
            guessWordFamily = buildFamily(guess, proceedWords);
            String proceedPattern = getMaxFamily(guessWordFamily);
            recordGuess(guess, proceedPattern);
            proceedWords = getProceedWords(proceedPattern, guessWordFamily);
        }
        printVictory();
    }

    private void defineValidInput () {
        validInput = new HashSet<>();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            validInput.add(ch);
        }
    }

    private ArrayList<String> getSameLengthWords () {
        ArrayList<String> words = new ArrayList<>();
        for (String word : wordList) {
            if (word.length() == targetLength) {
                words.add(word);
            }
        }
        return words;
    }
    public ArrayList<String> getProceedWords(String pattern, HashMap<String, ArrayList<String>> map) {
        ArrayList<String> words = new ArrayList<>();
        for (String word : map.get(pattern)) {
            words.add(word);
        }
        return words;
    }

    public String getMaxFamily (HashMap<String, ArrayList<String>> wordFamily) {
        int maxElements = 0;
        String pattern = "";
        for (String partialSolution : wordFamily.keySet()) {
            if (wordFamily.get(partialSolution).size() > maxElements) {
                pattern = partialSolution;
                maxElements = wordFamily.get(partialSolution).size();
            }
        }
        return pattern;
    }

    private char promptForGuess() {
        while (true) {
            System.out.println("Guess a letter.\n");
            solution.printProgress();
            System.out.println("Incorrect guesses:\n" + incorrectGuesses.toString());
            String input = inputScanner.next();
            char guessedChar = input.charAt(0);
            if (input.length() != 1 || !validInput.contains(guessedChar)) {
                System.out.println("Please enter a lowercase character.");
            } else if (previousGuesses.contains(guessedChar)) {
                System.out.println("You've already guessed that. Please enter a new character");
            } else {
                return guessedChar;
            }
        }
    }

    public HashMap<String, ArrayList<String>> buildFamily(char input, ArrayList<String> words) {
        HashMap<String, ArrayList<String>> wordFamilies = new HashMap<>();
        StringBuilder pattern;
        wordFamilies.put("", new ArrayList<>());
        for (String word : words) {
            pattern = new StringBuilder();
            HashSet<Character> uniqueChars = new HashSet<>();
            for (char c : word.toCharArray()) {
                uniqueChars.add(c);
            }
            for (char c : uniqueChars) {
                if (c == input) {
                    for (char character : word.toCharArray()) {
                        if (character == input) {
                            pattern.append(input);
                        } else {
                            pattern.append('_');
                        }
                    }
                    if (!wordFamilies.containsKey(pattern.toString())) {
                        wordFamilies.put(pattern.toString(), new ArrayList<>());
                    }
                    wordFamilies.get(pattern.toString()).add(word);
                }
            }
            if (pattern.toString().equals("")) {
                wordFamilies.get("").add(word);
            }
        }
        return wordFamilies;
    }
    private void recordGuess(char guess, String pattern) {
        previousGuesses.add(guess);
        boolean isCorrect = solution.addGuess(guess, pattern);
        if (!isCorrect) {
            incorrectGuesses.add(guess);
        }
    }

    private void printVictory() {
        System.out.printf("Congrats! The word was %s%n", solution.getTarget());
    }

    private static ArrayList<String> dictionaryToList(String filename) throws IOException {
        FileInputStream fs = new FileInputStream(filename);
        Scanner scnr = new Scanner(fs);

        ArrayList<String> wordList = new ArrayList<>();

        while (scnr.hasNext()) {
            wordList.add(scnr.next());
        }

        fs.close();

        return wordList;
    }

    public ArrayList<String> getWordList(){
        return this.wordList;
    }

}
