import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class program {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		;
		int[] arr = new int[n];
		int[] len = new int[n];
		for (int j = 0; j < n; j++) {
			arr[j] = sc.nextInt();
		}
		List<Integer> list = new ArrayList<>();
		int i;
		for (int k = 0; k < n; k++) {
			len[k] = 1;

			for (i = 0; i < k; i++) {

				if (arr[i] < arr[k] && ((arr[i] ^ arr[k]) & 1) == 1) {

					len[k] = Integer.max(len[k], len[i] + 1);
				}
			}

		}
		for (int a = 0; a < n; a++) {
			list.add(len[a]);
		}
		int max = Collections.max(list);
		System.out.println(max);
	}
}