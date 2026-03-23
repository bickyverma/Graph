import java.util.Scanner;

public class q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            
            int res = query(sc, 1, 2);
            if (res == 1) { answer(1); continue; }
            
            res = query(sc, 2, 3);
            if (res == 1) { answer(2); continue; }
            
            res = query(sc, 1, 3);
            if (res == 1) { answer(1); continue; }
            
            int idx = 4;
            int pool_size = 2 * n - 3;
            int target_zeros = n - 1;
            boolean found = false;
            
            while (pool_size > target_zeros) {
                res = query(sc, idx, idx + 1);
                
                if (res == 1) {
                    answer(idx);
                    found = true;
                    break;
                }
                
                idx += 2;
                pool_size -= 2;
                target_zeros -= 1;
            }
            
            if (!found) {
                answer(idx);
            }
        }
        sc.close();
    }
    
    static int query(Scanner sc, int i, int j) {
        System.out.println("? " + i + " " + j);
        System.out.flush();
        int res = sc.nextInt();
        if (res == -1) {
            System.exit(0);
        }
        return res;
    }
    
    static void answer(int k) {
        System.out.println("! " + k);
        System.out.flush();
    }
}