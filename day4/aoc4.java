import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class aoc4 {

	public static void main(String[] args) {
		String filename = args[0];
		Map<String,String> m = new HashMap();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			for (String line; (line = br.readLine()) != null;) {
				int index = line.indexOf(']');
				String[] arr = {line.substring(1, index), line.substring(index+2)};
				m.put(arr[0], arr[1]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> l = new ArrayList<String>(m.keySet());
		Collections.sort(l);
		Map<Integer,Guard> guards = new HashMap<>();
		int guardsIndex = 0;
		int fallAsleep = 0;
		int wakes = 0;
		Guard g = new Guard(0);
		for (String timeStamp : l) {
			if (m.get(timeStamp).contains("Guard")) {
				String[] s = m.get(timeStamp).split(" ");
				guardsIndex = Integer.parseInt(s[1].substring(1));
				g = guards.getOrDefault(guardsIndex, new Guard(guardsIndex));
			} else if (m.get(timeStamp).contains("falls asleep")) {
				// Probably best to use actual Date objects in the future... but for time's sake we'll just substring the timeStamp
				fallAsleep = Integer.parseInt(timeStamp.substring(timeStamp.length()-2));
			} else {
				wakes = Integer.parseInt(timeStamp.substring(timeStamp.length()-2));
				g.incrementRange(fallAsleep, wakes);
				guards.put(guardsIndex, g);
			}
		}
		
		int mostMinutesAsleep = -1;
		int guardNum = 0;
		int bestMin = 0;
		for (Guard guard : guards.values()) {
			// For 4-2
			System.out.printf("Guard #: %s \tBest Min: %s \tTimes Asleep on Same Min: %s%n", guard.getId(), guard.bestMinute(), guard.mostFrequentMinuteForGuard());
			if (guard.totalMinutesAsleep() > mostMinutesAsleep) {
				guardNum = guard.getId();
				mostMinutesAsleep = guard.totalMinutesAsleep();
			}	
		}
		bestMin = guards.get(guardNum).bestMinute();
		System.out.printf("Guard #: %s Best Minute: %s", guardNum, bestMin);
	}
	
	static class Guard {
		
		public Guard (int id) {
			this.id = id;
		}
		
		private int id;
		
		private int[] sleepIndex = new int[60];
		
		public int totalMinutesAsleep() {
			int total = 0;
			for (int i : sleepIndex) {
				total += i;
			}
			return total;
		}
		
		public void incrementRange(int start, int end) {
			for (int i = start; i < end; i++) {
				sleepIndex[i]++;
			}
		}
		
		public int bestMinute() {
			int bestMinute = -1;
			int minutesAsleep = -1;
			for (int i = 0; i < sleepIndex.length; i++) {
				if (sleepIndex[i] > minutesAsleep) {
					minutesAsleep = sleepIndex[i];
					bestMinute = i;
				}
			}
			return bestMinute;
		}
		
		// For 4-2
		public int mostFrequentMinuteForGuard() {
			int freq = 0;
			for (int i = 0; i < sleepIndex.length; i++) {
				if (sleepIndex[i] > freq) {
					freq = sleepIndex[i];
				}
			}
			return freq;
		}
		
		public int getId() {
			return id;
		}
	}

}

