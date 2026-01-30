import java.util.*;

public class GCD2 {

  public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Long> primes = generatePrimes(5000);
        
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                long v = primes.get(i) * primes.get(i + 1);
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
    static List<Long> generatePrimes(int limit) {
        boolean[] sieve = new boolean[limit + 1];
        List<Long> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (!sieve[i]) {
                primes.add((long) i);
                for (int j = i + i; j <= limit; j += i) sieve[j] = true;
            }
        }
        return primes;
    }
}
