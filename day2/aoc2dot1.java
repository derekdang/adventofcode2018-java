import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class aoc2dot1 {

	public static void main(String[] args) {
		String filename = args[0];
		int twoCount = 0;
		int threeCount = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			for (String line; (line = br.readLine()) != null;) {
				Map<Character,Integer> map = new HashMap();
				for (char c : line.toCharArray()) {
					map.put(c, map.getOrDefault(c,0)+1);
				}
				if (map.containsValue(2)) {
					twoCount++;
				}
				if (map.containsValue(3)) {
					threeCount++;
				}
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(twoCount * threeCount);

	}
}
