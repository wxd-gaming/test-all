package luajava;

import lombok.Getter;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * lua require 方式加载文件
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-21 19:21
 **/
@Getter
public class LuaFileRequire {

    String luaPath = null;
    ArrayList<String> modules = new ArrayList<>();

    public LuaFileRequire(LuaFileCache luaFileCache) {
        List<ImmutablePair<Path, byte[]>> pathList = luaFileCache.getPathList();
        ArrayList<String> paths = new ArrayList<>();
        for (ImmutablePair<Path, byte[]> pair : pathList) {
            String string = pair.getLeft().getParent().toString().replace("\\", "/") + "/?.lua";
            if (!paths.contains(string))
                paths.add(string);
            modules.add(pair.getLeft().getFileName().toString().replace(".lua", ""));
        }
        luaPath = String.join(";", paths);
    }


}
