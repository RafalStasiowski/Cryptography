import javax.management.relation.RelationNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Coder {
    private Map<String, String> alphabet;

    public Coder() {
        Alphabet alphabet1 = new Alphabet();
        alphabet = alphabet1.getAlphabet();

    }
    private char getRandomLetter(String input) {
        Random random = new Random();
        int rand = Math.abs(random.nextInt()%input.length());
        return input.charAt(rand);
    }

    public String encode(String input) {
        String getInput = input.toLowerCase(Locale.ROOT);
        String output = "";
        for(int i=0;i<getInput.length();i++) {
            String tmp = ""+getInput.charAt(i);
            output += getRandomLetter(this.alphabet.get(tmp));
        }
        return output;
    }
}
