import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayDeque;

public class AOC {
    private static final String INPUT = AOC.stringWriter();
    private static final Map<Character, Integer> score = Map.ofEntries(
        Map.entry('(', 3),
        Map.entry('[', 57),
        Map.entry('{', 1197),
        Map.entry('<', 25137)
    );
        
    public static void main(String[] args) {
        System.out.println(INPUT.lines().mapToLong(s -> countScores(s)).sum());
    }
    
    private static long countScores(String line) {
        Queue<Character> stack = Collections.asLifoQueue(new ArrayDeque<>());
        
        for (int i = 0; i < line.length(); ++i) {
            char c = line.charAt(i);
            if (score.containsKey(c)) {
                stack.add(c);
            }
            else {
                Character expected = stack.poll();
                if ( expected == null ) {
                    return 0;
                }
                char openingChar = (char)(c == 41 ? c - 1 : c - 2);  // Difference between opening & closing chars
                if (expected != openingChar) {
                    return score.get(openingChar);
                }
            }
        }
        return 0;
    }

    private static String stringWriter() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.println("[({(<(())[]>[[{[]{<()<>>");
        printWriter.println("[(()[<>])]({[<{<<[]>>");
        printWriter.println("{([(<{}[<>[]}>{[]{[(<()>");
        printWriter.println("(((({<>}<{<{<>}{[]{[]{}");
        printWriter.println("[[<[([]))<([[{}[[()]]]");
        printWriter.println("[{[{({}]{}}([{[{{{}}([]");
        printWriter.println("{<[[]]>}<{[{[{[]{()[[[]");
        printWriter.println("[<(<(<(<{}))><([]([]()");
        printWriter.println("<{([([[(<>()){}]>(<<{{");
        printWriter.println("<{([{{}}[<[[[<>{}]]]>[]]");
        return stringWriter.toString();
    }
}
