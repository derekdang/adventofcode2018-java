import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * https://adventofcode.com/2018/day/12
 */
public class aoc12 {
    public static void main(String[] args) {
        String filename = args[0];
        String initialState = ".......##..#.#..##..##..##...#####.#.....#..#..##.###.#.####......#.......#..###.#.#.##.#.#.###...##.###.#....................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................";

        Map<String, String> rules = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            for (String line; (line = br.readLine()) != null;) {
                if (line.contains("=>")) {
                    String[] split = line.split("=>");
                    rules.put(split[0].trim(), split[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder(initialState);

        System.out.printf("%d:\t%s\n", 0, sb.toString());
        for (int i = 1; i <= 500; i++) {
            String state = sb.toString();
            for (int start = 2; start < sb.length()-2; start++) {
                String key = state.substring(start-2, start+3);
                if (rules.containsKey(key)) {
                    sb.setCharAt(start, rules.get(key).charAt(0));
                } else {
                    sb.setCharAt(start, '.');
                }
            }
            System.out.printf("%d:\t%s\n", i, sb.toString()); // Ans for part 1 is 3738

            int sum = 0;
            for (int j = 0; j < sb.length(); j++) {
                if (sb.charAt(j) != '.') {
                    sum += j-6;
                }
            }
            System.out.println(sum); // Part 2, at gen 100 we are adding +78 with each generation, ans is 3900000002467
        }
    }
}
