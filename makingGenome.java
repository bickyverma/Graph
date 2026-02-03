import java.util.*;

public class makingGenome {
public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    int[] next = new int[26];
    Arrays.fill(next, -1);
    int[] indegree = new int[26];
    boolean [] visited = new boolean[26];

    for(int i = 0; i < n; i++){
        String s = sc.next();
        char[] chars = s.toCharArray();
        for(int j = 0; j < chars.length; j++){
            int curr = chars[j] - 'a';
            visited[curr] = true;
            if(j < chars.length-1){
                int nxt = chars[j+1] - 'a';
                next[curr] = nxt;
                indegree[nxt]++;
            }
        }
    }
    StringBuilder result = new StringBuilder();
    for(int i = 0; i < 26; i++){
        if(visited[i] && indegree[i] == 0){
            int curr = i;
            while(curr != -1){
                result.append((char)(curr+'a'));
                curr = next[curr];
            }
        }
    }
    System.out.println(result.toString());
}
}