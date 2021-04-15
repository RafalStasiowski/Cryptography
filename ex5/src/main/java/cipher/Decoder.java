package cipher;

import java.util.Map;

    public class Decoder {
        private Map<String, String> alphabet;

        public Decoder() {
            Alphabet alphabet1 = new Alphabet();
            alphabet = alphabet1.getAlphabet();
        }
        private <K, V> K getKey(Map<K, V> map, V value)
        {
            return map.keySet()
                    .stream()
                    .filter(key -> value.equals(map.get(key)))
                    .findFirst().get();
        }

        public String decode(String input) {
            String output = "";
            for(int i=0;i<input.length();i++) {
                for(String value : alphabet.values()) {
                    if(value.contains(String.valueOf(input.charAt(i)))) {
                        output+=getKey(alphabet, value);
                    }
                }
            }
            return output;
        }

}
