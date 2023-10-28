package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;




public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> type = new TypeReference<>() { };

        Path file1 = Paths.get(filepath1).toAbsolutePath();
        Path file2 = Paths.get(filepath2).toAbsolutePath();

        Map<String, Object> before = mapper.readValue(Files.readString(file1), type);
        Map<String, Object> after = mapper.readValue(Files.readString(file2), type);

        StringBuilder result = new StringBuilder("{\n");

        Set<String> allKeys = new TreeSet<>(before.keySet());
        allKeys.addAll(after.keySet());

        for (String key : allKeys) {
            Object beforeValue = before.get(key);
            Object afterValue = after.get(key);

            if (beforeValue == null) {
                result.append(" + " + key + ": " + afterValue + "\n");
            } else if (afterValue == null) {
                result.append(" - " + key + ": " + beforeValue + "\n");
            } else if (beforeValue.equals(afterValue)) {
                result.append("   " + key + ": " + beforeValue + "\n");
            } else {
                result.append(" - " + key + ": " + beforeValue + "\n");
                result.append(" + " + key + ": " + afterValue + "\n");
            }
        }

        result.append("}\n");

        return result.toString();
    }
}
