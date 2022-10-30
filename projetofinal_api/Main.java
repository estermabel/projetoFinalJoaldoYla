import java.util.Scanner;

public class Main {

	public static double dividir(int a, int b) {
		return a/b;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int x = sc.nextInt();
		int y = sc.nextInt();
		
		System.out.println(dividir(x, y));
		
	}

}