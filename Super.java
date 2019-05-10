import java.util.Scanner;

public class program {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String []granice=sc.nextLine().split(" ");
		String []A=granice[0].split("");
		String []B=granice[1].split("");
		
		long dp[][][]= new long[5001][10][2];

		initMatrix(dp);
		String[] brojAMinusJedan = srediString(A);
		long rA=getDigits(0,0,1,brojAMinusJedan,dp, true);
		
		initMatrix(dp);
		long rB=getDigits(0,0,1,B, dp, true);
		
		long r=rB%10000 - rA%10000;
		if(r<0) r+=10000;
		sc.close();
		System.out.println(r);
	}
	
	private static String[] srediString(String[] a) {
        int i = a.length - 1;
        while(true) {
            int i1 = Integer.parseInt(a[i]);
            if(i1 == 0) {
            	a[i] = "9";
            	i--;
            	
            } else {
            	a[i] = String.valueOf(i1 - 1);
            	break;
            }
        }
        return a;
	}

	private static void initMatrix(long[][][] dp) {
		// TODO Auto-generated method stub
		for(int i=0; i<5001; i++) {
			for(int j=0; j<10; j++) {
				for(int k=0; k<2;k++)
					dp[i][j][k]=-1;
			}
		}
	}

	private static long getDigits(int indexTren, int prethodnaZnamenka, int manji, String[] broj, long[][][] dp, boolean sveNule) {
		if(indexTren>=broj.length && sveNule) return 0;
		// u matrici je vec zapisana vrijednost koliko super brojeva ima
		if(indexTren>0 && indexTren<broj.length) {
			if(dp[indexTren][prethodnaZnamenka][manji]!=-1 && manji != 1) {
				return dp[indexTren][prethodnaZnamenka][manji];
			}
		}
		if(indexTren>=broj.length) return 1;
		long ret=0;
		int k=(manji!=0)? Integer.parseInt(broj[indexTren]) : 9;
		
		for(int i=0; i<=k; i++) {
			int priv=Integer.parseInt(broj[indexTren]);
			int newTight= ((i==priv) ? manji : 0);
			
			if(indexTren!=0 && !sveNule){
				if(prethodnaZnamenka-i==1 || i-prethodnaZnamenka==1 || i==prethodnaZnamenka ) continue;
			}	
			
			boolean nule = sveNule && i == 0;
			ret+=getDigits(indexTren+1, i, newTight, broj, dp, nule)%10000;	
		}
		if (prethodnaZnamenka != 0) { 
			dp[indexTren][prethodnaZnamenka][manji]=ret;
		}
		 return ret%10000; 
	}
}