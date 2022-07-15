import java.util.Scanner; 
 public class Main {
 public static void main(String[] args) {
 Scanner scanner = new Scanner(System.in); 
 String[] soma = scanner.nextLine().split(" "); 
 int total = 2; 
 for (String s: soma ) { 
 total += Integer.parseInt(s); 
  } 
 System.out.println(total); 
}
}