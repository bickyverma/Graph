import java.util.*;

public class basketball {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long D = sc.nextLong();

        long[] num = new long[n];
        for(int i = 0; i < n; i++){
            num[i] = sc.nextLong();
        }

        Arrays.sort(num);

        int l = 0;
        int r = num.length-1;

        int count = 0;

        while(l <= r){
            long ans = num[r];
            long need = (D/ ans) + 1;

            if(l + need - 1 <= r){
                count++;
                l += need - 1;
                r --;
            }else{
                break;
            }
        }
        System.out.println(count);
    }
}
