import java.util.*;
public class yesyes{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//solution
        int t = sc.nextInt();
        while(t-- > 0){
            String s = sc.next().toUpperCase();
            int ycnt = 0;

            for(char c : s.toCharArray()){
                if(c == 'Y'){
                    ycnt++;
                }
            }
            if(ycnt <= 1){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }
}
