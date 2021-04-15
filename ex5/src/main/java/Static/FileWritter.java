package Static;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWritter {
    public static void writeFile(String patch, String text) throws IOException {
        Files.writeString(Path.of(patch),text);
    }
}
