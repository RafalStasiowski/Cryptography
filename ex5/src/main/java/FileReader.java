import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    public static String readFile(String patch) throws IOException {
        return Files.readString(Path.of(patch));
    }
}
