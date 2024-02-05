package code;

import io.netty.handler.codec.http.HttpHeaderNames;
import org.junit.Test;
import org.wxd.boot.agent.io.FileReadUtil;
import org.wxd.boot.agent.io.FileWriteUtil;
import org.wxd.boot.core.collection.ObjMap;
import org.wxd.boot.core.collection.concurrent.ConcurrentHashSet;
import org.wxd.boot.core.str.json.FastJsonUtil;
import org.wxd.boot.core.threading.Executors;
import org.wxd.boot.net.http.client.url.HttpBuilder;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class ReadLog {

    @Test
    public void main() {

        ConcurrentHashSet<String> reportSpOrderIds = new ConcurrentHashSet<>();

        ConcurrentSkipListMap<Long, ObjMap> logs = new ConcurrentSkipListMap<>();

        ConcurrentSkipListMap<Long, Integer> ids = new ConcurrentSkipListMap<>();


        FileReadUtil.readLine(new File("E:\\out\\3.log"), StandardCharsets.UTF_8, line -> {

            int index = line.indexOf("：{");
            String substring = line.substring(index + 1);
            ObjMap objMap = FastJsonUtil.parseObjMap(substring);
            String spOrder = objMap.getString("spOrder");
            if (!reportSpOrderIds.add(spOrder)) return;
            long serid = objMap.getLongValue("serid");
            serid = objMap.getLongValue("time");
            objMap.put("serid", serid);
            Integer merge = ids.merge(serid, 1, Math::addExact);
            if (merge > 1) {
                System.out.println(merge + " - " + spOrder);
            } else {
                logs.put(serid, objMap);
            }
        });
        int sum = ids.values().stream().filter(integer -> integer > 1).mapToInt(integer -> integer).sum();
        System.out.println(logs.size() + " - " + sum);
        ConcurrentHashSet<Long> reportIds = new ConcurrentHashSet<>();
        File file = new File("target/report.json");
        if (file.exists()) {
            String s = FileReadUtil.readString(file);
            List<Long> list = FastJsonUtil.parseArray(s, Long.class);
            reportIds.addAll(list);
        }
        System.out.println("已导入：" + reportIds.size());
        CountDownLatch countDownLatch = new CountDownLatch(logs.size() - reportIds.size());
        logs.values().forEach(value -> {
            long serid = value.getLongValue("serid");
            if (reportIds.contains(serid)) return;
            Executors.getVTExecutor().submit(() -> {
                try {
                    String bodyUnicodeDecodeString = HttpBuilder.postText("http://centerserver.600uu.com:18800/tmpSyncLog")
                            .header(HttpHeaderNames.AUTHORIZATION.toString(), "F19CF2C7DBE174EB94092FD2310EAAC0")
                            .paramJson(value.toJson())
                            .retry(2)
                            .readTimeout(5000)
                            .request().bodyUnicodeDecodeString();
                    reportIds.add(serid);
                    System.out.println("已导入：" + reportIds.size());
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                } finally {
                    countDownLatch.countDown();
                }
            });
        });
        while (countDownLatch.getCount() > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            FileWriteUtil.writeString("target/report.json", FastJsonUtil.toJson(reportIds));
        }
        FileWriteUtil.writeString("target/report.json", FastJsonUtil.toJson(reportIds));
        System.out.println("导入完成");
    }

}
