import java.util.Scanner; 
 public class Main {
 public static void main(String[] args) {
 Scanner scanner = new Scanner(System.in); 
 String[] soma = scanner.nextLine().split(" "); 
 int total = 0; 
 for (int i=0; i < soma.length; i++ ) { 
 total += Integer.parseInt(soma[i]); 
  } 
 System.out.println(total); 
 scanner.close(); 
 }
}