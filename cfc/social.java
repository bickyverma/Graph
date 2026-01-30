import java.io.BufferedReader;
import java.io.InputStreamReader;

public class social {
        public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int answer;
            if (n == 2) {
                answer = 2;
            } else if (n % 2 == 0) {
                answer = 0;
            } else if (n % 3 == 0) {
                answer = 3;
            } else {
                answer = 1;
            }

            out.append(answer).append('\n');
        }

        System.out.print(out.toString());
    }
}
