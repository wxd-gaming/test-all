package example.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {


    static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        log.info("s");
        DBFactory.getIns().init();

        WebService.getIns().start();

        System.out.println("====================================================");
        System.out.println("");
        System.out.println("���ݿ�������������  - " + "127.0.0.1:" + DBFactory.getIns().getMyDB().props.getProperty("port") + "/" + DBFactory.getIns().getMyDB().props.getProperty("database"));
        System.out.println("�ûس������Թرշ���");
        System.out.println("");
        System.out.println("====================================================");
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("�ûس����ȴ������˳�");
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        thread.start();

        Thread hook = new Thread(() -> {
            DBFactory.getIns().stop();
        });
        Runtime.getRuntime().addShutdownHook(hook);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        try {
            thread.interrupt();
            thread.join();
        } catch (Exception ignore) {}

        hook.run();
    }


}