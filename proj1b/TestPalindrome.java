import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    @Test
    public void testIsPalindrome() {
        String a = "a";
        assertTrue(palindrome.isPalindrome(a));
        String b = "";
        assertTrue(palindrome.isPalindrome(b));
        String c = "racecar";
        assertTrue(palindrome.isPalindrome(c));
        String d = "horse";
        assertFalse(palindrome.isPalindrome(d));
        String e = "rancor";
        assertFalse(palindrome.isPalindrome(e));
    }
    @Test
    public void testIsOffByOnePalindrome() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("abcdab", cc));
        assertFalse(palindrome.isPalindrome("fvuhdishubfv", cc));
    }
}
