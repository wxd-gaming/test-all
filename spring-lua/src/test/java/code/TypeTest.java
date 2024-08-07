package code;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

@Slf4j
public class TypeTest {

    Logger logger = LoggerFactory.getLogger("tmp");

    @Test
    public void t1() {
        System.out.println(MediaType.TEXT_PLAIN.toString());
        log.info("abc");
        logger.info("tmp abc");
    }

}
