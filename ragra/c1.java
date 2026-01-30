import java.util.*;

public class c1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] size = new int[n];

        for(int i = 0; i < n; i++){
            size[i] = sc.nextInt();
        }

        for(int j = 0; j < n; j++){
            int m = size[j];

            for(int k = 2; k <= m; k++){
                System.out.print(k + " ");
            }
            System.out.println(1);
        }
    }
}
