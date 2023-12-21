package org.wxd.boot.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 测试类
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-12-20 11:33
 **/
@Slf4j
public class ReactorUserService {

    public static void main(String[] args) {
        ReactorUserService reactorUserService = new ReactorUserService();
        reactorUserService.getFavorites(3L).subscribe(v -> {
            log.debug("{}", v);
        });
        reactorUserService.getDetail(1L)
                .filter(v -> Objects.equals(v, "1"))
                .subscribe(string -> System.out.println(string));
        System.out.println("exit");
    }

    /** 返回的是多个，即Flux */
    public Flux<Long> getFavorites(Long userId) {
        // 创建Flux并返回
        return Flux.create(sink -> {
            // 模拟数据库访问时间
            try {
                Thread.sleep(1000);
                // 发布数据 1,2,3
                sink.next(1L);
                sink.next(2L);
                sink.next(3L);
                // 标识发布结束
                sink.complete();
            } catch (Exception e) {
                e.printStackTrace();
                sink.error(new Exception("读取出现错误"));
            }
        });
    }

    private Map<Long, String> names = new HashMap<Long, String>() {{
        put(1L, "football");
        put(2L, "movie");
        put(3L, "film");
    }};

    /** 返回单个数据，所以是Mono */
    public Mono<String> getDetail(Long id) {
        return Mono.create(sink -> {
            // 模拟数据库访问时间
            try {
                Thread.sleep(1000);
                // 发布并完成
                sink.success(names.get(id));
            } catch (Exception e) {
                e.printStackTrace();
                sink.error(new Exception("读取出现错误"));
            }
        });
    }
}
