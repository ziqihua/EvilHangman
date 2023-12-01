import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class EvilHangmanTest {
    private EvilHangman hTest;
    private ArrayList<String> words;

    @BeforeEach
    void init() {
        // Runs before each test, makes a new hangman object BEFORE EACH test
        hTest = new EvilHangman("testDict.txt");
        words = hTest.getWordList();
    }

    @Test
    public void testGetWordList(){
        ArrayList<String> expected = new ArrayList<>(
                Arrays.asList("echo", "heal", "belt","peel", "hazy")
        );
        assertEquals(expected, words);
    }

    @Test
    public void testGetProceedWords () {
        char input = 'e';
        HashMap<String, ArrayList<String>> wordFamilies = hTest.buildFamily(input, words);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("heal");
        expected.add("belt");
        assertEquals(expected, hTest.getProceedWords("_e__", wordFamilies));
    }

    @Test
    public void testBuildFamily() {
        char input = 'e';
        HashMap<String, ArrayList<String>> wordFamilies = new HashMap<>();
        ArrayList<String> wordFamily0 = new ArrayList<>();
        ArrayList<String> wordFamily1 = new ArrayList<>();
        ArrayList<String> wordFamily2 = new ArrayList<>();
        ArrayList<String> wordFamily3 = new ArrayList<>();
        wordFamily0.add("echo");
        wordFamily1.add("heal");
        wordFamily1.add("belt");
        wordFamily2.add("peel");
        wordFamily3.add("hazy");

        wordFamilies.put("e___", wordFamily0);
        wordFamilies.put("_e__", wordFamily1);
        wordFamilies.put("_ee_", wordFamily2);
        wordFamilies.put("", wordFamily3);

        assertEquals(wordFamilies.get("e___"), hTest.buildFamily(input, words).get("e___"));
        assertEquals(wordFamilies.get("_e__"), hTest.buildFamily(input, words).get("_e__"));
        assertEquals(wordFamilies.get("_ee_"), hTest.buildFamily(input, words).get("_ee_"));
        assertEquals(wordFamilies.get(""), hTest.buildFamily(input, words).get(""));
    }

    @Test
    public void testGetMaxFamily() {
        char input = 'e';
        HashMap<String, ArrayList<String>> wordFamilies = hTest.buildFamily(input, words);
        assertEquals("_e__", hTest.getMaxFamily(wordFamilies));
    }
}