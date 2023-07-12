public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }
    public boolean isPalindrome(String word) {
        Palindrome palindrome = new Palindrome();
        Deque<Character> d = palindrome.wordToDeque(word);
        while (d.size() > 1) {
            char a = d.removeFirst();
            char b = d.removeLast();
            if (a != b) {
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Palindrome palindrome = new Palindrome();
        Deque<Character> d = palindrome.wordToDeque(word);
        while (d.size() > 1) {
            char a = d.removeFirst();
            char b = d.removeLast();
            if (!cc.equalChars(a, b)) {
                return false;
            }
        }
        return true;
    }
}
