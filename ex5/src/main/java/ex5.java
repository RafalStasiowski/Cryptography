import java.io.IOException;

public class ex5 {
    public static void main(String[] args) throws IOException {
        Coder coder = new Coder();
        Decoder decoder = new Decoder();
        Cipher cipher = new Cipher("tocrypt.txt","encrypted.txt","decrypted.txt",coder,decoder);
        cipher.encode();
        cipher.decode();

    }
}
