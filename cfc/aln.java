import java.util.*;

public class aln{
    public int val;
    public aln next;
    public aln(int x){
        val = x;
    }
    public static void main(String[] args) {
        Scanner op = new Scanner(System.in);
        System.out.print("node of node in LinkedList : ");
        int n = op.nextInt(); // values in ll

        System.out.print("value of 1st node : "); //define itself
        aln head = new aln(op.nextInt()); //1st node
        aln curr = head; // dummy wala jo pura aage tak jayega 
        for(int i = 2; i <= n; i++){
            System.out.print("value of "+i+" node : ");
            curr.next = new aln(op.nextInt()); // value for curr ki next value
            curr = curr.next; // curre update that is i++
        }
        //addition of new node 
        System.out.print("value of new head node : ");
        aln head1 = new aln(op.nextInt());
        head1.next = head;

        //addition of new node
        System.out.print("value of new node at end : ");
        aln end = new aln(op.nextInt());
        if(curr.next == null){
            curr.next = end;
        }

        //addition in middle  

//abhi nhi hua h 

        // System.out.print("value after which you want to add : ");
        // int m = op.nextInt();
        // System.out.println("value to be added in mid : ");
        // aln midnode = new aln(op.nextInt());
        // for(int i = 0; i < n; i++){
        //     if(curr.val == m){
        //         curr.next = midnode;
        //         midnode.next = curr.next;
        //     }
        // }

        aln ptr = head1; //kaha se traversing kerna h 
        while(ptr != null){ //traversing ll with tc o(n);
            System.out.print(ptr.val+", ");
            ptr = ptr.next;
        }
    }
}







// import java.util.*;

// public class aln{
//     public int val;
//     public aln next;
//     public aln(int x){
//         val = x;
//     }
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         System.out.print("no of element in ll : ");
//         int n = sc.nextInt();
//         System.out.print("value of head node : ");
//         aln head = new aln(sc.nextInt());
//         aln curr = head; // first ele if ll
//         for(int i = 2; i <= n; i++){
//             System.out.print("value of "+ i + " node :");
//             curr.next = new aln(sc.nextInt()); // 2nd se aage tak ke element
//             curr = curr.next; // curr head ko i++ karega
//         }
        
//         aln ptr = head;
//         while(ptr != null){
//             System.out.print(ptr.val+", ");
//             ptr=ptr.next;
//         }
//     }
// }