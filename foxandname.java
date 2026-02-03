// import java.util.*;



// public class foxandname {

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt();

//         String[] names = new String[n];
//         for (int i = 0; i < n; i++) {
//             names[i] = sc.next();
//         }

//         ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
//         for (int i = 0; i < 26; i++) adj.add(new ArrayList<>());

//         int[] indeg = new int[26];

//         for (int i = 0; i < n - 1; i++) {
//             String s = names[i];
//             String t = names[i + 1];

//             int len = Math.min(s.length(), t.length());
//             boolean diff = false;

//             for (int j = 0; j < len; j++) {
//                 if (s.charAt(j) != t.charAt(j)) {
//                     int u = s.charAt(j) - 'a';
//                     int v = t.charAt(j) - 'a';
//                     adj.get(u).add(v);
//                     indeg[v]++;
//                     diff = true;
//                     break;
//                 }
//             }
//             if (!diff && s.length() > t.length()) {
//                 System.out.println("Impossible");
//                 return;
//             }
//         }

//         PriorityQueue<Integer> pq = new PriorityQueue<>();
//         for (int i = 0; i < 26; i++) {
//             if (indeg[i] == 0) pq.add(i);
//         }

//         StringBuilder ans = new StringBuilder();

//         while (!pq.isEmpty()) {
//             int u = pq.poll();
//             ans.append((char) ('a' + u));

//             for (int v : adj.get(u)) {
//                 indeg[v]--;
//                 if (indeg[v] == 0) {
//                     pq.add(v);
//                 }
//             }
//         }

//         if (ans.length() != 26) {
//             System.out.println("Impossible");
//         } else {
//             System.out.println(ans.toString());
//         }
//     }
// }


import java.util.*;

public class foxandname{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] word = new String[n];
        for(int i = 0; i < n; i++){
            word[i] = sc.next();
        }

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < 26; i++){
            adj.add(new ArrayList<>());
        }

        int[] id = new int[26];
        for(int i = 0; i < n-1; i++){
            String w1 = word[i];
            String w2 = word[i+1];

            int len = Math.min(w1.length(), w2.length());
            boolean flag = false;

            for(int j = 0; j < len; j++){
                if(w1.charAt(j) != w2.charAt(j)){
                    int from = w1.charAt(j) - 'a'; //-a islye kyunki a=97 in ascii se 0 to 26 ke range me lane ke liye
                    int to = w2.charAt(j) - 'a'; 

                    adj.get(from).add(to);
                    id[to]++;
                    flag = true;
                    break;
                }
            }
            if(!flag && w1.length() > w2.length()){
                System.out.println("Impossible");
                return;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < id.length; i++){
            if(id[i] == 0) q.add(i);
        }

        StringBuilder sb = new StringBuilder();

        while(!q.isEmpty()){
            int curr = q.poll();
            sb.append((char)('a' + curr));

            for(int i : adj.get(curr)){
                id[i]--;
                if(id[i] == 0) q.add(i);
            }
        }

        if(sb.length() != 26) System.out.println("Impossible");
        else{
            System.out.println(sb.toString());
        }
    }
}