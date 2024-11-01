---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by 無心道(15388152619).
--- DateTime: 2024/11/1 15:06
---

local dispatch_tab = {}

--- 调用函数
--- @param function_name string 函数名
--- @param ... any 参数
function dispatch(function_name, ...)
    --查找函数 通过load字符串的形式 动态编译 返回函数
    local chunk = "return " .. function_name
    if dispatch_tab[chunk] == nil then
        dispatch_tab[chunk] = load(chunk)()
    end
    local loadFunc = dispatch_tab[chunk]
    --调用函数
    local success, result = xpcall(loadFunc, debug.traceback, ...)
    if not success then
        local var = "[Error] dispatch func name [" .. function_name .. "] 参数：" .. gameDebug.toStrings(" ", ...)
        var = var .. "\n" .. result
        _LUA_Error(var)
    end
    return result
end