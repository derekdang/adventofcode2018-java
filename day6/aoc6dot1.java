import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class aoc6dot1
{
    public static void main(String[] args) {
        String filename = args[0];

        Map<Point, Integer> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int i = 1;
            for (String line; (line = br.readLine()) != null; i++) {
                String[] split = line.split(",");
                Point p = new Point(i, Integer.valueOf(split[0].trim()), Integer.valueOf(split[1].trim()));
                map.put(p, 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object[][] grid = new Object[400][400];
        for (int i = 0; i<grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = minManhattanDistanceForCoords(j, i, map.keySet()); // uncomment for answer to 6-1
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i <grid.length; i++) {
            for (int j = 0; j <grid[i].length; j++){
                Point p = (Point) grid[i][j];
                System.out.printf("%s\t", grid[i][j].toString());

                if(i == 0 || j ==0 || i == grid.length-1 || j == grid[i].length-1) {
                    set.add(Integer.valueOf(grid[i][j].toString()));
                }

                if(!set.contains(p.id)) {
                    map.put(p, map.getOrDefault(p, 0)+1);
                }
            }
            System.out.println();
        }

        // answer is 3223
        map.forEach((k,v)-> {
            if(!set.contains(k.id)) {
                System.out.printf("%s=%s\n", k.id,v);
            }
        });
    }

    private static Point minManhattanDistanceForCoords(int j, int i, Set<Point> set) {
        int minDist = Integer.MAX_VALUE;
        Point minPoint = new Point(0, Integer.MAX_VALUE, Integer.MAX_VALUE);
        boolean tie = false;
        for (Point p : set) {
            int dist = Math.abs(p.x-j) + Math.abs(p.y-i);
            if (dist < minDist) {
                minDist = dist;
                minPoint = p;
                tie = false;
            } else if (dist == minDist) {
                tie = true;
            }
        }
        if (tie) return new Point(0, Integer.MAX_VALUE, Integer.MAX_VALUE);

        return minPoint;
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
