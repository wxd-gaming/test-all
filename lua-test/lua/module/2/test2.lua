--- table 操作
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by wxdgaming.
--- DateTime: 2024/8/28 17:30
---

function t3(str)
    local num = 9199999999999999989
    print("lua long - " .. tostring(num))
    print("lua 接收 java 传递参数 - " .. tostring(str))
    return str
end

function testNow(map)
    argsTest(1, "3")
    print("      lua now - " .. ret(map))
    print("      lua now - " .. ret(map["now"]))
    print("   lua nowsec - " .. ret(map["nowsec"]))
    print("lua math.ceil - " .. ret(math.ceil(map["now"] / 1000)))
end

function argsTest(...)
    gameDebug.assertTrue(1 == 2, "参数异常", ...)
end

function ret(obj)
    local success, ret = pcall(function()
        return "dd - " .. obj
    end)
    if not success then
        local var = gameDebug.toStrings(", ", obj, true)
        print(debug.traceback(var .. ret), 1)
    end
    return ret
end

local mem = {  }
function cache_memory()
    for i = 1, 10 do
        local var = {}
        for j = 1, 2000 do
            var[j] = tostring(i) .. "-" .. tostring(j) .. "-gddddd"
        end
        mem[#mem + 1] = var
    end
    return "ok"
end

function cleancache()
    mem = {}
end