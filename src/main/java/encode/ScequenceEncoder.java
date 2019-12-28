package encode;

public class ScequenceEncoder {

    private static final String passphrase = "apple";

    public static final char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) {
        System.out.println(encode(passphrase));
    }

    public static String encode(String passphrase) {
        String encoded = "";
        for (int i = 0; i < passphrase.length(); i++) {
            char[] chars = passphrase.toCharArray();
            for (int j = 0; j < alphabet.length; j++) {
                if (chars[i] == alphabet[j]) {
                    encoded += (j + 1);
                }
            }
        }
        return encoded;
    }
}
