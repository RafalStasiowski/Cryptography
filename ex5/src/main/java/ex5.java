import cipher.Cipher;
import cipher.Coder;
import cipher.Decoder;

import java.io.IOException;
import java.util.Scanner;

public class ex5 {
    public static void main(String[] args) throws IOException {
        Coder coder = new Coder();
        Decoder decoder = new Decoder();
        Cipher cipher = new Cipher("tocrypt.txt","encrypted.txt","decrypted.txt",coder,decoder);
        cipher.encode();
        cipher.decode();
        cipher.askForText();

    }
}
