mport java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class aoc1dot2 {

	public static void main(String[] args) {
		String filename = args[0];
		Set<Integer> freqs = new HashSet();
		int sum = 0;
		while (true) {
			try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
				for (String line; (line = br.readLine()) != null;) {
					sum += Integer.parseInt(line);
					if (freqs.contains(sum)) {
						System.out.println(sum);
						return;
					}
					freqs.add(sum);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
