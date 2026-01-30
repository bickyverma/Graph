import java.util.Scanner;

public class game {
public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0){
            int n=sc.nextInt();
            int k=sc.nextInt();
            long[] a=new long[n];
            long[] b=new long[n];
            for(int i=0;i<n;i++) a[i]=sc.nextLong();
            for(int i=0;i<n;i++) b[i]=sc.nextLong();
            
            long maxSum=Long.MIN_VALUE;
            long curr=0;
            for(long val:a){
                curr=Math.max(val,curr+val);
                maxSum=Math.max(maxSum,curr);
            }

            long maxB=0;
            for(int i=0;i<n;i++){
                if(a[i]>0) maxB=Math.max(maxB,b[i]);
            }

            long aliceMoves=(k+1)/2;
            long bobMoves=k/2;

            maxSum+= (aliceMoves-bobMoves)*maxB;
            System.out.println(maxSum);
        }
    }
}
