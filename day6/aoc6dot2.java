import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class aoc6dot2
{
    private static final int SAFE_DISTANCE = 10000;

    public static void main(String[] args) {
        String filename = args[0];

        Set<Point> set = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int i = 1;
            for (String line; (line = br.readLine()) != null; i++) {
                String[] split = line.split(",");
                Point p = new Point(i, Integer.valueOf(split[0].trim()), Integer.valueOf(split[1].trim()));
                set.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object[][] grid = new Object[400][400];
        for (int i = 0; i<grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = safeManhattanDistanceForCoords(j, i, set);
            }
        }

        int area = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Point p = (Point) grid[i][j];
                if(p.id == 0) {
                    area++;
                }
            }
        }
        System.out.printf("Size of region with total manhattan dist < 10000: %d", area); // Answer for 6-2 = 40495
    }

    private static Point safeManhattanDistanceForCoords(int j, int i, Set<Point> set) {
        int sum = 0;
        for(Point p : set) {
            sum += Math.abs(p.x-j) + Math.abs(p.y-i);
        }

        if (sum < SAFE_DISTANCE) {
            return new Point(0, 0, 0);
        } else {
            return new Point(-1, 0, 0);
        }
    }

    static class Point extends Object {
        int x;
        int y;
        int id;

        public Point(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y =y;
        }

        public String toString() {
            return String.valueOf(this.id);
        }
    }
}
