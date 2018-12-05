import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class aoc2dot2 {

        // TODO: Very inefficient n^2 solution. ATP investigate better solution
	public static void main(String[] args) {
		String filename = args[0];
		List<String> list = new ArrayList();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			for (String line; (line = br.readLine()) != null;) {
				list.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			Map<Character, Integer> map = new HashMap();
			for (char c : s.toCharArray()) {
				map.put(c, map.getOrDefault(c, 0) + 1);
			}

			for (int j = i + 1; j < list.size(); j++) {
				Map<Character, Integer> m2 = new HashMap<>(map);
				String t = list.get(j);
				for (char c : t.toCharArray()) {
					m2.put(c, m2.getOrDefault(c, 0) - 1);
				}
				int countDiff = 0;
				String tmp1 = "";
				String tmp2 = "test";
				for (Character c : m2.keySet()) {
					if (countDiff > 2) {
						break;
					}
					if (m2.get(c) >= 1) {
						tmp1 = s.replaceFirst(String.valueOf(c), "");
						countDiff+= Math.abs(m2.get(c));	
					} else if (m2.get(c) <= -1) {
						tmp2 = t.replaceFirst(String.valueOf(c), "");
						countDiff+= Math.abs(m2.get(c));
					}
 				}
				if (tmp1.equals(tmp2)) {
					System.out.println(tmp1);
				}
			}
		}

	}
}

