import java.util.*;

public class aoc9 {
    public static void main(String[] args) {
        int[] input = new int[]{468, 71843};

        Map<Integer, Long> scoreboard = new HashMap<>();
        List<Integer> deque = new LinkedList<>();
        deque.add(0);

        int elfNum = 1;
        for (int marbleNum = 1, currIndex = 0; marbleNum <= input[1]; marbleNum++, elfNum++) {
            if (marbleNum % 23 == 0) {
                if (currIndex - 7 < 0)  currIndex = currIndex - 7 + deque.size(); // overflow wrap to end. mod % works too
                else currIndex = currIndex - 7;

                scoreboard.put(elfNum, scoreboard.getOrDefault(elfNum, (long) 0) + marbleNum + deque.remove(currIndex).longValue());
            } else {
                currIndex +=2;
                if (currIndex > deque.size()) currIndex = currIndex - deque.size(); // overflow wrap to beg. mod % works too
                deque.add(currIndex, marbleNum);
            }

            if (elfNum == input[0]) elfNum = 0;
            System.out.println(marbleNum);
        }

        long max = 0;
        for (Long score :scoreboard.values()) {
            max = Math.max(max, score);
        }
        /**
         * 9-1 answer = 385820
         * 9-2 answer = 3156297594
         */
        System.out.printf("Winning elf score: %d\n", max);
    }
}