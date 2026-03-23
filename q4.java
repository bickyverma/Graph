import java.util.*;
import java.io.*;

public class q4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder out = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long[] cnt = {
                Long.parseLong(st.nextToken()),
                Long.parseLong(st.nextToken()),
                Long.parseLong(st.nextToken())
            };
            char[] col = {'R', 'G', 'B'};

            for (int i = 0; i < 3; i++)
                for (int j = i + 1; j < 3; j++)
                    if (cnt[i] < cnt[j]) {
                        long tmp = cnt[i]; cnt[i] = cnt[j]; cnt[j] = tmp;
                        char tc = col[i]; col[i] = col[j]; col[j] = tc;
                    }

            long A = cnt[0], B = cnt[1], C = cnt[2];
            char cA = col[0], cB = col[1], cC = col[2];

            int maxLen = (int) ((A >= B + C + 1) ? 2 * (B + C) + 1 : A + B + C);
            char[] res = new char[maxLen];

            if (A >= B + C + 1) {
                long bLeft = B, cLeft = C;
                for (int i = 0; i < maxLen; i++) {
                    if (i % 2 == 0) {
                        res[i] = cA;
                    } else {
                        if (bLeft > 0) { res[i] = cB; bLeft--; }
                        else { res[i] = cC; cLeft--; }
                    }
                }
            } else {
                long[] rem = {A, B, C};
                char[] ch = {cA, cB, cC};
                int len = 0;
                for (int i = 0; i < maxLen; i++) {
                    for (int x = 0; x < 3; x++)
                        for (int y = x + 1; y < 3; y++)
                            if (rem[x] < rem[y]) {
                                long tmp = rem[x]; rem[x] = rem[y]; rem[y] = tmp;
                                char tc = ch[x]; ch[x] = ch[y]; ch[y] = tc;
                            }
                    for (int x = 0; x < 3; x++) {
                        if (rem[x] == 0) continue;
                        char c = ch[x];
                        if (len >= 1 && res[len - 1] == c) continue;
                        if (len >= 3 && res[len - 3] == c) continue;
                        res[len++] = c;
                        rem[x]--;
                        break;
                    }
                }
            }

            out.append(res, 0, maxLen).append('\n');
        }

        System.out.print(out);
    }
}