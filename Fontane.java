import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class program{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] line1=sc.nextLine().split(" ");
		int brFontana=Integer.parseInt(line1[0]);
		int brQuery=Integer.parseInt(line1[1]);
		int sizeOfSegTree=findSizeSegTree(brFontana);
		long[] segTree=new long[sizeOfSegTree*2-1];
		
		List<Query> listQ=new ArrayList<>();
		//Query [] queries=new Query[brQuery];
		int i=brQuery;
		//parse
		while(i>0) {
			String [] line=sc.nextLine().split(" ");
			String operation=line[0];
			int first=Integer.parseInt(line[1]);
			long second;
			if(operation.equals("?")) {
				second=Integer.parseInt(line[2])-1;
			} else {
				second=Long.parseLong(line[2]);
			}
			
			Query q=new Query(operation, first, second);
			listQ.add(q);
			i--;
		}
		
		for(Query q: listQ) {
			if(q.op.equals("?")) {
				//long suma=sum(q.first, q.second, sizeOfSegTree, segTree);
				long suma=sum(q.first,(int) q.second, brFontana, segTree);
				System.out.println(suma);
			} else {
				add(q.first, q.second, brFontana, segTree);
			}
		}
	}
	
	static long sum(int a, int b,int sizeOfTree, long[] segTree) {
		a+=sizeOfTree;
		b+=sizeOfTree;
		long s=0;
		while(a<=b) {
			if(a%2==1)
				s+=segTree[a++];
			if(b%2==0)
				s+=segTree[b--];
			a/=2;
			b/=2;
		}
		return s;
	}
	static void add(int k, long x, int sizeOfTree, long []segTree) {
		k+=sizeOfTree;
		segTree[k]=x;
		for(k/=2; k>=1; k/=2) {
			segTree[k]=segTree[2*k]+segTree[2*k+1];
		}
	}
	static class Query{
		String op=null;
		int first;
		long second;
		Query(String operation,int first, long second) {
			op=operation;
			this.first=first;
			this.second=second;
		}
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
}