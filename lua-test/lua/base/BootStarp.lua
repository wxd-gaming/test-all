---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by 無心道(15388152619).
--- DateTime: 2024/12/26 16:47
---
Bootstrap = {}

function onStart()

end

function onInit()
    local funs = LuaScan.findTwoFunc("onInit")
    for i, f in pairs(funs) do
        LuaScan.print("扫描初始化调用：", f.info)
        f.fun()
    end
end

function onLogin(player)
    LuaScan.triggerTwoFunc("onLogin", player)
end

function onEnterMap(...)
    LuaScan.triggerTwoFunc("onEnterMap", ...)
end