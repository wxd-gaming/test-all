package wxdgaming.boot.springlua.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.luaj.vm2.LuaValue;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import wxdgaming.boot.springlua.data.repository.UserRepository;
import wxdgaming.boot.springlua.serice.LuaService;

import java.io.IOException;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-07-31 20:18
 **/
@Controller
public class ApiController {

    final LuaService luaService;

    final RedisTemplate<?, ?> redisTemplate;
    final UserRepository userRepository;

    public ApiController(UserRepository userRepository, RedisTemplate<?, ?> redisTemplate, LuaService luaService) {
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
        this.luaService = luaService;
    }

    @RequestMapping("/lua/reload")
    public String reload() throws IOException {
        luaService.init();
        return "ok";
    }

    @RequestMapping("/lua/*")
    public void all(HttpServletRequest request, HttpServletResponse response, @RequestBody(required = false) String body) throws IOException {
        String servletPath = request.getServletPath();
        if (servletPath.startsWith("/"))
            servletPath = servletPath.substring(1);
        servletPath = servletPath.replace("/", "_");
        LuaValue[] luaValues = luaService.parse(request, response, body);
        LuaValue lua_func = luaService.get(servletPath);
        if (lua_func == null || lua_func == LuaValue.NIL) {
            lua_func = luaService.get("root");
        }
        lua_func.invoke(luaValues);
    }

}
