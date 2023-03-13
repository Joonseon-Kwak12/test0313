import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String command = sc.nextLine();
		String[] cmdDiv = command.split(" ");
		int numOfColumn = Integer.parseInt(cmdDiv[0]);
		int numOfRow = Integer.parseInt(cmdDiv[1]);

		for (int i = 0; i < numOfRow; i++) {
			for (int j = 0; j < numOfColumn; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		sc.close();
	}
}
