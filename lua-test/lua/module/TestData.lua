--- 数据测试
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by 無心道(15388152619).
--- DateTime: 2024/11/1 13:38


require('GameDebug')

--print("Current directory: ", gameDebug.getCurrentDirectory())
--print("Current directory: ", paths)
--
--require('ClassTest')
--require('test1')
--
--print(package.path)

TestData = {}
TestData.__index = TestData

function TestData.new()
    local tmp = {
        id    = 1,
        name  = "test1",
        level = 1,
    }
    return tmp
end

function TestData.print(data)
    gameDebug.print(data)
end

function TestData.Lv(data, change)
    if change then
        data.level = (data.level or 0) + change
    end
    return data.level or 0
end

function TestData.onLogin(player)
    print("TestData onLogin", player:toString())
end

function TestData.onEnterMap(map, player)
    print("TestData onEnterMap", map:toString(), player:toString())
end

function TestData.print2(...)
    local string = gameDebug.toStrings0(false, false, " ", "dd", ...)
    print(string)
end

function test33(o1, o2, o3)
    gameDebug.print("o1", o1, "o2", o2, "o3", o3)
end

function testActor(actor)
    gameDebug.printType(actor, actor:getUid(), actor:getName(), actor["lv"])
end

function TestData.onInit()
    --local k1 = "dddd"
    --gameDebug.print({ k1 = "1" })
    --gameDebug.print({ [k1] = "1" })

    --ldebug("debug", 3)
    --linfo("info", 3)
    --lerror("error", 3)

    --gameDebug.printType("getvardata", getvardata("key"))
    --setvardata("key", 1, "1")
    --gameDebug.printType("getvardata", getvardata("key", 1))
    --setvardata("key", "1", "1")
    --gameDebug.printType("getvardata", getvardata("key"))
    --setvardata("key", { ["333"] = 1, ["444"] = 2 })
    --gameDebug.printType("getvardata", getvardata("key"))
end

return TestData