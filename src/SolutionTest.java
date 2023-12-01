import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {
    private Solution s;

    @BeforeEach
    void init() {
        s = new Solution("heal");
    }

    @Test
    public void testIsSolved() {
        assertFalse(s.isSolved());

    }

    @Test
    public void testAddGuess() {
        assertTrue(s.addGuess('e', "_e__"));
    }

    @Test
    public void testGetTarget() {
        String expected = "____";
        assertEquals(expected, s.getTarget());
    }
}