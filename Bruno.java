import java.util.Scanner;

public class program {
	static int counter;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] line = sc.nextLine().split(" ");
		int brGradova = Integer.parseInt(line[0]);
		int brCesta = Integer.parseInt(line[1]);
		counter = brGradova;
		int privatniBrojac = brCesta;
		int[] ispis = new int[brCesta + 1];
		ispis[privatniBrojac] = counter;
		privatniBrojac--;

		int[] link = new int[brGradova + 1];
		int[] size = new int[brGradova + 1];
		
		for (int i = 1; i <= brGradova; i++) {
			link[i] = i;
			size[i] = 1;
		}
	
		Brid[] bd = new Brid[brCesta + 1];
		for (int i = 1; i <= brCesta; i++) {
			String[] lin = sc.nextLine().split(" ");
			Brid bid = new Brid(Integer.parseInt(lin[0]), Integer.parseInt(lin[1]));
			bd[i] = bid;
		}

		int[] edges = new int[brCesta];

		for (int i = 0; i < brCesta; i++) {
			int br = Integer.parseInt(sc.nextLine());
			edges[i] = br;
		}

		for (int i = brCesta - 1; i > -1; i--) {

			int edge = edges[i];
			Brid dvaCvora = bd[edge];

			if (!same(dvaCvora.cvor1, dvaCvora.cvor2, link)) {
				unite(dvaCvora.cvor1, dvaCvora.cvor2, link, size);
				counter--;
				ispis[privatniBrojac] = counter;
				privatniBrojac--;
			} else {
				ispis[privatniBrojac] = counter;
				privatniBrojac--;
				continue;
			}
		}
		outputIspis(ispis);
		sc.close();
	}

	static void unite(int a, int b, int[] link, int[] size) {
		a = find(a, link);
		b = find(b, link);
		if (size[a] < size[b]) {
			int pom = a;
			a = b;
			b = pom;

		}
		size[a] += size[b];
		link[b] = a;
	}

	static boolean same(int a, int b, int[] link) {
		return find(a, link) == find(b, link);
	}

	static int find(int x, int[] link) {
		
		while (x != link[x]) {
			link[x]=link[link[x]];
			x = link[x];
		}
		return x;
	}
	
	private static void outputIspis(int[] ispis) {
		for (int i = 1; i < ispis.length; ++i)
			System.out.println(ispis[i]);
	}

	static class Brid {
		int cvor1;
		int cvor2;

		Brid(int cv1, int cv2) {
			cvor1 = cv1;
			cvor2 = cv2;
		}
	}
}