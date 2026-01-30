import java.util.*;

public class Suspension {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-- > 0){
            int n = sc.nextInt();
            int yellow = sc.nextInt();
            int red = sc.nextInt();

            int ysus = yellow/2;
            int totalsus = red + ysus;

            int ans = Math.min(n, totalsus);
            System.out.println(ans);
        }
    }
}