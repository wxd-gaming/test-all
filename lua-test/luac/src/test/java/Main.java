import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * c
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-25 16:23
 **/
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main codeTest = new Main();
        while (scanner.hasNext()) {
            long l = scanner.nextLong();
            if (l == 0) break;
            codeTest.action(l);
        }
    }

    public void test() {
        action(3111989);
        action(13091989);
        action(13091989234235L);
    }

    public void action(long l) {
        if (l == 0) return;
        long z = 2;
        List<Long> list = new ArrayList<>();
        while (true) {
            if (l % z == 0) {
                l = l / z;
                list.add(z);
            } else {
                z++;
            }
            if (l == 1 || z > l) {
                if (list.isEmpty()) list.add(l);
                break;
            }
        }
        int c = 1;
        long up = list.get(0);
        StringBuilder str = new StringBuilder();
        for (int i = 1; i < list.size(); i++) {
            if (up != list.get(i) || i == list.size() - 1) {
                actionString(str, up, c);
                up = list.get(i);
                c = 1;
                if (i == list.size() - 1) {
                    actionString(str, up, c);
                }
                continue;
            }
            c++;
        }
        if (list.size() == 1) {
            str.append(list.get(0)).append("^1");
        }
        System.out.println(str);
    }

    public void actionString(StringBuilder str, long x, long y) {
        if (str.length() > 0) str.append(" ");
        str.append(x).append("^").append(y);
    }

}
