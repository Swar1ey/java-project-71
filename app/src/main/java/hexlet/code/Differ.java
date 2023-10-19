package hexlet.code;

import java.io.IOException;
import java.nio.file.*;
import java.lang.Throwable;
public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Path firstFilePath = createPath(filePath1);
        Path secondFilePath = createPath(filePath2);
        String contentFile1 = Files.readString(firstFilePath);
        String contentFile2 = Files.readString(secondFilePath);

        Parser.parse(contentFile1);
        System.out.println(contentFile1);
        return contentFile1;
    }

    private static Path createPath(String pathString) throws Exception {
        try {
            return Paths.get(pathString);
        } catch (InvalidPathException e) {
            System.out.println("wrong!");
            String pathMessage = e.getMessage();
            if(pathMessage == null) {
                pathMessage = e.toString();
                System.out.println("Это pathMessage - " + pathMessage);
            }
            throw new Exception("Path " + pathString + " is wrong!");
        }
    }

}