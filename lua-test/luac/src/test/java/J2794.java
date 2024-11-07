
import java.util.Scanner;

public class J2794 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] split = scan.nextLine().split(" ");

        int n = Integer.parseInt(split[0]);
        int t = Integer.parseInt(split[1]);
        int n1 = (n - 2) / 4 * 2 + 1;
        for (int i = 0; i < t; i++) {
            String nextLine = scan.nextLine();
            nextLine = nextLine + nextLine.substring(0, n1);
            int out = 0;
            for (int j = 0; j < n; j++) {
                int m = 0;
                for (int k = 0; k < n1; k++) {
                    if (nextLine.charAt(j + k) == '1') {
                        m++;
                    }
                }
                out = Math.max(out, m);
            }
            System.out.println(out);
        }
    }

}
