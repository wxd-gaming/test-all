---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by 無心道(15388152619).
--- DateTime: 2024/12/26 16:47
---
Bootstrap = {}

require("LuaScan")
require("EventLister")

local this = {}
local LoginEventListerTable = EventListerTable:new("测试扫描", 9999)

function onStart()

end

function Bootstrap.onLogin(player)
    print("onLogin", player)
end

function Bootstrap.onInit()
    LoginEventListerTable:eventLister("0", "onLogin", Bootstrap.onLogin)
end

function onInit()
    local funs = LuaScan.findTableFunc("onInit")
    for i, f in pairs(funs) do
        f.fun()
        --local s, e = xpcall(load(f.fun), debug.traceback)
    end
end