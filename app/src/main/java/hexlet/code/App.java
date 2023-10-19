package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;
@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer>{
    @Option(names = { "-f", "--format" },
            paramLabel = "format",
            defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;
    @Parameters(index = "0", description = "path to first file")
    private String filePath1;
    @Parameters(index = "1", description = "path to second file")
    private String filePath2;
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println("hello");
            System.out.println(format);
            String result = Differ.generate(filePath1, filePath2, format);
            System.out.println(result);
        } catch(Exception e) {
            System.out.println("Something wrong");
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.err.println(e.getStackTrace().getClass());
            return 1;
        }
        return 0;
    }
}
