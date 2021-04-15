package cipher;

import Static.FileReader;
import Static.FileWritter;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Cipher {
    private String text;
    private final String inputPatch;
    private final String outputPatch;
    private final String decryptedPatch;
    private final Coder coder;
    private final Decoder decoder;

    public Cipher(String inputPatch, String outputPatch, String decryptedPatch, Coder coder, Decoder decoder) {
        this.text = text;
        this.inputPatch = inputPatch;
        this.outputPatch = outputPatch;
        this.decoder = decoder;
        this.coder = coder;
        this.decryptedPatch = decryptedPatch;

    }

    public void encode() {
        try {
            text = FileReader.readFile(inputPatch);
            FileWritter.writeFile(outputPatch,coder.encode(text));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decode() {
        try {
            text = FileReader.readFile(outputPatch);
            FileWritter.writeFile(decryptedPatch,decoder.decode(text));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void askForText() {
        System.out.println("Insert encrypted text: ");
        Scanner scanner = new Scanner(System.in);
        text = scanner.nextLine();
        System.out.println(decoder.decode(text));

    }

}
