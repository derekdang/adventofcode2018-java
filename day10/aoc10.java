import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class aoc10 {

    public static void main(String[] args) {
        String filename = args[0];

        Map<int[], int[]> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            for (String line; (line = br.readLine()) != null;) {
                int p1 = line.indexOf('<');
                int p2 = line.indexOf('v') - 2;
                String position = line.substring(p1+1,p2);
                String[] pArr = position.split(",");
                int[] pos = new int[]{Integer.valueOf(pArr[0].trim()), Integer.valueOf(pArr[1].trim())};

                int v1 = line.lastIndexOf('<');
                int v2 = line.lastIndexOf('>');
                String velocity = line.substring(v1+1, v2);
                String[] vArr = velocity.split(",");
                int[] vel = new int[]{Integer.valueOf(vArr[0].trim()), Integer.valueOf(vArr[1].trim())};

                map.put(pos, vel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        int xDiff = Integer.MAX_VALUE;
        int yDiff = Integer.MAX_VALUE;
        int seconds = 0;
        boolean first = true;
        while((maxX-minX) < xDiff && (maxY-minY) < yDiff) {
            seconds++;

            if (first) {
                first = false;
            } else {
                xDiff = maxX - minX;
                yDiff = maxY - minY;
            }

            minX = Integer.MAX_VALUE;
            minY = Integer.MAX_VALUE;
            maxX = Integer.MIN_VALUE;
            maxY = Integer.MIN_VALUE;

            for (Map.Entry<int[], int[]> entry : map.entrySet())
            {
                int[] k = entry.getKey();
                int[] v = entry.getValue();
                k[0] += v[0];
                k[1] += v[1];

                maxX = Math.max(k[0], maxX);
                maxY = Math.max(k[1], maxY);
                minX = Math.min(k[0], minX);
                minY = Math.min(k[1], minY);
            }
        }

        for (Map.Entry<int[], int[]> entry : map.entrySet())
        {
            int[] k = entry.getKey();
            int[] v = entry.getValue();
            k[0] -= v[0];
            k[1] -= v[1];
        }
        seconds--;

        /**
         * Answer is RPNNXFZR
         */
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                boolean match = false;
                for (int[] arr : map.keySet()) {
                    if (arr[0] == x && arr[1] == y) match = true;
                }

                if (match) System.out.print("#");
                else System.out.print(".");
            }
            System.out.println();
        }

        System.out.printf("Seconds ellapsed %d", seconds); // answer is 10946
    }
}
