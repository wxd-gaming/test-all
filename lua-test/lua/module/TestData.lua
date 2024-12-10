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

function TestData.print2(...)
    local string = gameDebug.toStrings0(false, false, " ", "dd", ...)
    print(string)
end

function test33(o1, o2, o3)
    gameDebug.print("o1", o1, "o2", o2, "o3", o3)
end

function testActor(actor)
    gameDebug.print(actor, actor:getUid(), actor:getName(), actor["lv"])
end

--test33(1, nil, 3)

--local var = TestData.new()
--print(TestData.Lv(var, 33))
--TestData.print(var)
--TestData.print2(var)
--gameDebug.assertPrintTrace(var.level == 33, "TestData.Lv(data, change) 测试失败")
--gameDebug.assertTrue(var.level == 33, "TestData.Lv(data, change) 测试失败")


return TestData