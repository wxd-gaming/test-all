package wxdgaming.boot.spring.starter.core;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wxdgaming.boot.spring.data.entity.log.ApiLog;
import wxdgaming.boot.spring.data.repository.ApiLogRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 所有的日志记录
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-02 17:20
 */
@Slf4j
@Aspect
@Order(20)
@Component
public class ApiLogAspect {

     // @Autowired ApiLogRepository apiLogRepository;

    /**
     * 切入连接点，使用固定 controller层下的所有文件
     */
    @Pointcut(value = "execution(* wxdgaming.boot..*(..)))")
    public void logPointcut() {
    }

    // Before表示 advice() 将在目标方法执行前执行
    @Before("logPointcut()")
    public void advice(JoinPoint joinPoint) {
        // 获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            StringBuilder stringBuilder = new StringBuilder("\n-------------------- 接口日志，开始 --------------------\n");
            stringBuilder.append(request.getMethod()).append(" ").append(request.getRequestURL().toString()).append("\n");
            // 用户IP
            String clientIp = request.getRemoteAddr();
            stringBuilder.append("ip：").append(clientIp).append("\n");
            // 接口路径
            String path = request.getServletPath();
            stringBuilder.append("path：").append(path).append("\n");
            stringBuilder.append("content-type：").append(request.getHeader("content-type")).append("\n");

            // 控制器方法参数列表
            Object[] args = joinPoint.getArgs();
            if (args != null && args.length > 0) {
                // 获取有效的控制器方法参数列表
                List<Object> validArgs = getValidArguments(args);
                if (validArgs != null && !validArgs.isEmpty()) {
                    stringBuilder.append("data：").append(validArgs.getFirst()).append("\n");
                }
            }
            stringBuilder.append("-------------------- 接口日志，结束 --------------------\n");
            log.info(stringBuilder.toString());
        }
    }

    /**
     * 获取有效的控制器方法参数列表
     * <p>
     * 排除 HttpServletRequest 和 HttpServletResponse 参数。
     * <p>
     * HttpServletRequest 参数，会阻塞线程，抛出异常 NestedServletException-OutOfMemoryError。
     * <p>
     * HttpServletResponse 参数，会抛出异常 NestedServletException-StackOverflowError。
     */
    private List<Object> getValidArguments(Object[] args) {
        return Stream.of(args).filter(this::isValidArgument).collect(Collectors.toList());
    }

    private Boolean isValidArgument(Object arg) {
        return isNotHttpServletRequest(arg) && isNotHttpServletResponse(arg);
    }

    /**
     * 不是 HttpServletRequest
     * <p>
     * HttpServletRequest 参数，会阻塞线程，会抛出如下异常：
     * org.springframework.web.util.NestedServletException:
     * Handler dispatch failed; nested exception is java.lang.OutOfMemoryError: Java heap space
     */
    private Boolean isNotHttpServletRequest(Object arg) {
        return !(arg instanceof HttpServletRequest);
    }

    /**
     * 不是 HttpServletResponse
     * <p>
     * HttpServletResponse 参数，会抛出如下异常：
     * org.springframework.web.util.NestedServletException:
     * Handler dispatch failed; nested exception is java.lang.StackOverflowError
     */
    private Boolean isNotHttpServletResponse(Object arg) {
        return !(arg instanceof HttpServletResponse);
    }

}

