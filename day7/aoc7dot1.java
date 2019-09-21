import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class aoc7dot1
{
    public static void main(String[] args) {
        String filename = args[0];

        Map<String, Queue<String>> map = new HashMap<>();
        Map<String, Integer> prerequisites = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            for (String line; (line = br.readLine()) != null;) {
                String[] split = line.split(" ");
                Queue<String> pq = map.getOrDefault(split[1], new PriorityQueue<>());
                pq.add(split[7]);
                map.put(split[1], pq);

                prerequisites.put(split[1], prerequisites.getOrDefault(split[1],0));
                prerequisites.put(split[7], prerequisites.getOrDefault(split[7], 0)+1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        PriorityQueue<String> availableSteps = new PriorityQueue<>();
        // find available steps
        prerequisites.forEach((k,v) -> {
            if(v == 0) {
                availableSteps.add(k);
            }
        });

        StringBuilder sb = new StringBuilder();
        while(!availableSteps.isEmpty()) {
            String currStep = availableSteps.poll();
            Queue<String> nextSteps = map.get(currStep);
            sb.append(currStep);

            if (nextSteps == null) break;
            for (String nextStep : nextSteps) {
                if (prerequisites.get(nextStep).intValue() == 1) {
                    availableSteps.add(nextStep);
                    prerequisites.remove(nextStep);
                } else {
                    prerequisites.put(nextStep, prerequisites.get(nextStep) - 1);
                }
            }
        }

        System.out.printf("Order of steps: %s", sb.toString());
    }
}
