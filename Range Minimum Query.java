import java.util.Scanner;

public class program{
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int sizeOfArr= Integer.parseInt(sc.nextLine());
		int[] input=new int[sizeOfArr];
		//String [] linija=sc.nextLine().split(" ");
		for(int i=0; i<sizeOfArr; i++) {
			input[i]=sc.nextInt();
		}
		sc.nextLine();
		/*for(int i=0; i<sizeOfArr; ++i) {
			input[i]=Integer.parseInt(linija[i]);
		}*/		
		int numOfQueries= Integer.parseInt(sc.nextLine());
		Query [] queries=new Query[numOfQueries];
		for(int i=0; i<numOfQueries; i++) {
			String [] q=sc.nextLine().split(" ");
			Query qu=new Query( Integer.parseInt(q[0]), Integer.parseInt(q[1]));
			queries[i]=qu;
		}
		
		int segTreeSize= findSizeSegTree(sizeOfArr);
		int[] segTree=new int[segTreeSize*2-1];
		for(int i=0; i<segTree.length; i++) {
			segTree[i]=Integer.MAX_VALUE;
		}
		
		initTree(input, segTree,0, input.length-1, 0);
		
		for(int i=0; i<queries.length; ++i) {
			Query q=queries[i];
			int min=findMinInInterval(segTree, q.low, q.high, 0, input.length-1, 0 );
			System.out.println(min);
		}
		sc.close();
	}
	static void initTree(int [] input, int [] segTree, int low, int high, int pos) {
		if(low==high) {
			segTree[pos]=input[low];
			return;
		}
		int mid=(low+high)/2;
		initTree(input, segTree, low, mid, 2*pos +1);
		initTree(input, segTree, mid+1, high, 2*pos +2);
		segTree[pos]=Math.min(segTree[2*pos+1], segTree[2*pos+2]);
	}
	static int findMinInInterval(int[] segTree, int qLow, int qHigh, int low, int high, int pos) {
		if(qLow<=low && qHigh>=high) {
			return segTree[pos];
		}
		if(qLow>high || qHigh<low) {
			return Integer.MAX_VALUE;
		}
		int mid=(low+high)/2;
		return Integer.min(findMinInInterval(segTree, qLow, qHigh, low, mid, 2*pos+1),
				findMinInInterval(segTree, qLow, qHigh, mid+1, high, 2*pos+2));
	}
	
	static int findSizeSegTree(int len) {
		int pw=1, i=1;
		while(true) {
			if(pw<len) {
				pw=(int) Math.pow(2, i);
				i++;
			} else 
				break;
		}
		return pw;
	}
	static class Query{
		int low;
		int high;
		Query(int l, int h){
			low=l;
			high=h;
		}
	}
}