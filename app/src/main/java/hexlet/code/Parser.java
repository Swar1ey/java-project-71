package hexlet.code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
public class Parser {
    public static Map<String, String> parse(String str) {
        Map<String, String> result = new HashMap<>();
        String[] bufer = str.split(",");
        List<String> rs = Arrays.stream(bufer)
//                .filter(x -> x.equals("}"))
                .toList();
        System.out.println(rs);
        return result;
    }
}
