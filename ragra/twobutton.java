import java.util.*;

public class twobutton {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int step = 0;

        while(m > n){
            if(m%2 == 0){
                m /= 2;
                step++;
            }else{
                m += 1;
                step++;
            }
        }
        step+= (n-m);
        System.out.println(step);
    }
}
