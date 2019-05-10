import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class program{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int INF = 1000000000;
		List<Cvor> edges = new ArrayList<>();
		String[] prva = sc.nextLine().split(" ");

		// PARSIRANJE YASSS
		int brCvorova = Integer.parseInt(prva[0]);
		int brBridova = Integer.parseInt(prva[1]);
		for (int i = 0; i < brBridova; i++) {
			String[] line = sc.nextLine().split(" ");
			int cvor = Integer.parseInt(line[0]);
			int povezanS = Integer.parseInt(line[1]);
			int km = Integer.parseInt(line[2]);
			Cvor cv = new Cvor(cvor, povezanS, km);
			Cvor cv2 = new Cvor(povezanS, cvor, km);
			edges.add(cv);
			edges.add(cv2);
		}

		int brRadnihDana = Integer.parseInt(sc.nextLine());
		int[] gradoviKojeCuPosjetiti = new int[brRadnihDana];
		for (int i = 0; i < brRadnihDana; i++) {
			gradoviKojeCuPosjetiti[i] = (Integer.parseInt(sc.nextLine()));
		}
		int[] distance = new int[brCvorova + 1];
		// AJMO SAD BELLMAN FORD WOO
		init(distance, brCvorova, INF);
		BF(brCvorova, distance, edges);
		for (int i = 0; i < brRadnihDana; i++) {
			int gradKojiPosjetim = gradoviKojeCuPosjetiti[i];
			long kilometara = distance[gradKojiPosjetim];
			System.out.println(kilometara);
		}

	}
	private static void init(int [] distance, int brCvorova, int INF) {
		for (int i = 1; i <= brCvorova; i++)
			distance[i] = INF;
		distance[0] = 0;
		distance[1] = 0;
	}
	private static void BF(int brCvorova, int [] distance, List<Cvor> edges) {
		int flag = 0;
		for (int i = 1; i < brCvorova; i++) {
			boolean any = false;
			flag = 0;
			for (Cvor e : edges) {
				int a, b;
				int w;
				a = e.cvor;
				b = e.povezanS;
				w = e.km;
				int priv = distance[b];
				
				distance[b] = Math.min(distance[b], distance[a] + w);
				if(priv!=distance[b]) any=true;
				if(i==brCvorova-1 && !any) {
					break;
				}
				
			}
			if(!any) break;
		}
	}
	static class Cvor {
		int cvor;
		int povezanS;
		int km;

		Cvor(int cvor, int povezans, int km) {
			this.cvor = cvor;
			this.povezanS = povezans;
			this.km = km;
		}
	}
}