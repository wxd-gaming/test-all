LUA_Error = load("return _G.error")()
LUA_Print = load("return _G.print")()

function dispatch(function_name, ...)
    --gameDebug.print(function_name, ...)
    --查找函数 通过load字符串的形式 动态编译 返回函数
    local loadFunc = load("return " .. function_name)()
    --调用函数
    local success, result = xpcall(loadFunc, debug.traceback, ...)
    if not success then
        --local trace = debug.traceback(result)
        local var = "[Error] dispatch func name [" .. function_name .. "] 参数：" .. gameDebug.toStrings(" ", ...)
        var = var .. "\n" .. result
        LUA_Error(var)
    end
    return result
end

function memory0()
    return collectgarbage("count")
end