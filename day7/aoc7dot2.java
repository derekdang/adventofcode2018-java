import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class aoc7dot2
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

        Set<Worker> workers = new HashSet<>();
        int elapsedTime = 0;

        /**
         * while workers is < 5 try to poll
         * decrement timeForJob for each worked
         * once worker is finished, remove from set
         * increment elapsedTime
         */
        // start with first step, get the new unblocked steps and add to PQ
        while (!workers.isEmpty() || !availableSteps.isEmpty()) {
            while (workers.size() < 5 && !availableSteps.isEmpty()) {
                String currStep = availableSteps.poll();
                Worker worker = new Worker(currStep.charAt(0));
                workers.add(worker);
            }

            for (Worker w : workers) {
                w.decrementTime();
                if (w.timeForJob == 0) {
                    Queue<String> nextSteps = map.get(String.valueOf(w.step));
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
            }
            workers.removeIf(worker -> worker.timeForJob == 0); // if worker is done remove from set
            elapsedTime++;
        }
        System.out.printf("Time to complete 5 workers: %d\n", elapsedTime); // answer is 864 seconds
    }

    public static class Worker {
        int timeForJob;
        char step;

        public Worker(char step) {
            this.timeForJob = (int) step - 4;
            this.step = step;
        }

        public void decrementTime() {
            timeForJob--;
        }
    }
}
