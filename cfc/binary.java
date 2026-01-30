import java.util.*;

public class binary {
public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            String s = sc.next();
            String t = sc.next();

            int first = -1, last = -1;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    if (first == -1) first = i + 1;
                    last = i + 1;
                }
            }

            if (first == -1) {
                System.out.println(0);
                continue;
            }

            if (first == last) {
                System.out.println(1);
                System.out.println("1 " + first);
            } else {
                System.out.println(2);
                int mid = (first + last) / 2;
                System.out.println("1 " + mid);
                System.out.println(mid + " " + last);
            }
        }
    }
}
