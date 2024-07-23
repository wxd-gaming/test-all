package example.db;

import ch.vorburger.exec.ManagedProcess;
import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfiguration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MyDB extends DB {

    final Properties props;
    final Timer timer = new Timer();

    public MyDB(Properties props, DBConfiguration config) {
        super(config);
        this.props = props;
        try {
            this.prepareDirectories();
        } catch (Exception e) {
            if (!check(e))
                e.printStackTrace(System.err);
        }
        try {
            this.unpackEmbeddedDb();
        } catch (Exception ignore) {}
        try {
            this.install();
        } catch (Exception e) {
            if (!check(e))
                e.printStackTrace(System.err);
        }
    }

    private boolean check(Throwable e) {
        if (e != null) {
            if (e.getMessage().endsWith("is not empty. Only new or empty existing directories are accepted for --datadir"))
                return true;
            if (e.getCause() != null && check(e.getCause())) {
                return true;
            }
        }
        return false;
    }

    public void initDb() {
        try {
            String database = props.getProperty("database");
            String[] split = database.split(",|£¬");
            for (String dbName : split) {
                createDB(dbName, props.getProperty("user"), props.getProperty("pwd"));
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        int bak_Time = Integer.parseInt(props.getProperty("bak"));
        initBakTimer(bak_Time);
    }

    public void initBakTimer(int bak_Time) {
        if (bak_Time > 0) {

            timer.scheduleAtFixedRate(
                    new TimerTask() {
                        @Override public void run() {
                            bakSql();
                        }
                    },
                    TimeUnit.MINUTES.toMillis(bak_Time),
                    TimeUnit.MINUTES.toMillis(bak_Time)
            );

        }
    }

    public void bakSql() {
        try {
            String format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS").format(new Date());

            String database = props.getProperty("database");
            String[] split = database.split(",|£¬");
            for (String dbName : split) {
                File outputFile = new File("data-base/bak/" + dbName + "/" + format + ".sql");
                outputFile.getParentFile().mkdirs();
                ManagedProcess managedProcess = dumpSQL(
                        outputFile,
                        dbName,
                        props.getProperty("user"),
                        props.getProperty("pwd")
                );
                try {
                    managedProcess.start();
                    managedProcess.waitForExit();
                } catch (Exception ignore) {
                }
                try {
                    managedProcess.destroy();
                } catch (Exception ignore) {}
                System.out.println("±¸·Ý£º" + outputFile);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    @Override public synchronized void start() throws ManagedProcessException {
        super.start();
        initDb();
    }

    @Override public synchronized void stop() throws ManagedProcessException {
        timer.cancel();
        timer.purge();
        bakSql();
        super.stop();
    }

}
