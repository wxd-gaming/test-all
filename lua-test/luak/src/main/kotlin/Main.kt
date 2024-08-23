import org.luaj.vm2.lib.jse.JsePlatform

fun main() {
    /* TODO 测试下来还是5.1.x */
    println("test");
    var debugGlobals = JsePlatform.debugGlobals()
    val file = "luak/src/main/lua/test.lua"
    debugGlobals.loadfile(file).call()
    println(Long.MAX_VALUE)
    debugGlobals.get("t2").call()
}