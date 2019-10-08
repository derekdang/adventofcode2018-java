/**
 * https://adventofcode.com/2018/day/11
 */
public class aoc11 {
    private static final int GRID_SERIAL_NUM = 1788;
    private static int[] coords = new int[]{-1,-1};
    private static int[] coordsP2 = new int[]{-1,-1,-1};
    private static int maxPower = -1;
    private static int maxPowerP2 = -1;

    public static void main(String[] args) {
        int[][] grid = new int[300][300];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int rackID = j + 1 + 10;
                grid[i][j] = rackID;
                grid[i][j] *= i+1;
                grid[i][j] += GRID_SERIAL_NUM;
                grid[i][j] *= rackID;
                grid[i][j] /= 100;
                grid[i][j] %= 10;

                grid[i][j] -= 5;
            }
        }
        findLargestThreeByThree(grid);
        System.out.printf("x: %d\t y: %d\n", coords[0]+1,coords[1]+1); // Answer for part 1 is 235,35

        for (int i = 1; i <= 300; i++) {
            maxSquareAndPower(grid, i);
        }

        // Answer for part 2 is 142,265,7
        System.out.printf("x: %d\t y: %d\t, size: %d\n", coordsP2[0]+1,coordsP2[1]+1, coordsP2[2]);

    }

    private static void findLargestThreeByThree(int[][] grid) {
        for (int i = 0; i < grid.length-2; i++) {
            for (int j = 0; j < grid[i].length-2; j++) {
                int powerForSquare = 0;

                powerForSquare += grid[i][j];
                powerForSquare += grid[i][j+1];
                powerForSquare += grid[i][j+2];
                powerForSquare += grid[i+1][j];
                powerForSquare += grid[i+1][j+1];
                powerForSquare += grid[i+1][j+2];
                powerForSquare += grid[i+2][j];
                powerForSquare += grid[i+2][j+1];
                powerForSquare += grid[i+2][j+2];

                if (powerForSquare > maxPower) {
                    maxPower = powerForSquare;
                    coords[0] = j;
                    coords[1] = i;
                }
            }
        }
    }

    private static void maxSquareAndPower(int[][] grid, int size) {
        for (int i = 0; i < grid.length-size+1; i++) {
            for (int j = 0; j < grid.length-size+1; j++) {
                int powerForSquare = 0;
                for (int y = 0; y < size; y++) {
                    for (int x = 0; x < size; x++) {
                        powerForSquare += grid[i+y][j+x];
                    }
                }

                if (powerForSquare > maxPowerP2) {
                    maxPowerP2 = powerForSquare;
                    coordsP2[0] = j;
                    coordsP2[1] = i;
                    coordsP2[2] = size;
                }
            }
        }
    }
}
