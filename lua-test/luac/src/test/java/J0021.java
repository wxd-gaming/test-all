import java.util.Scanner;

public class J0021 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s1 = scan.nextLine();
        String[] split = s1.split(" ");
        int n1 = Integer.parseInt(split[0]);
        int n2 = Integer.parseInt(split[1]);
        int n3 = Integer.parseInt(split[2]);

        int n = n1 * 2 / 10 + n2 * 3 / 10 + n3 * 5 / 10;

        System.out.println(n);

    }

}
