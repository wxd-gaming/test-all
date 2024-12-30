--- 测试类和方法调用关系
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by 無心道(15388152619).
--- DateTime: 2024/11/4 10:55
---

TestClass = {
    new = function()
        local var = setmetatable({}, TestClass)
        return var
    end
}
TestClass.__index = TestClass

function TestClass:Name(newName)
    if newName ~= nil then
        self["name"] = newName
    end
    TestClass.Level(self, 2)
    return self["name"]
end

function TestClass:Level(newLv)
    if newLv ~= nil then
        self["level"] = newLv
    end
    return self["level"]
end

function TestClass.onLogin(player)
    print("TestClass onLogin", player:toString())
    error("test")
end

function TestClass.onEnterMap(map, player)
    print("TestClass onEnterMap", map:toString(), player:toString())
end

function TestClass:printName2()
    print("printName2", self)
end

function ddd()
    print("ddddddd")
end

local test = TestClass.new()
local test2 = TestClass.new()
test:Name("t1")
test:Level(2)

test2:Name("t2")
test2:Level(2)

print("230 - 220 * 0.5", 230 - 220 * 0.5)

--local tmp_number = {}
--print("测试 tonumber ", tonumber(tmp_number["t1"]))
--tmp_number["1"] = 1
--tmp_number["1"] = tmp_number["1"] + "2"
--print("测试 tonumber ", tmp_number["1"])

--TestClass.printName2(test)
--TestClass.printName2(test2)
--TestClass.printName2({ level = 1 })