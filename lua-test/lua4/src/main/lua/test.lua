---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by admin.
--- DateTime: 2024/8/19 09:49
---

function t1()
    local num = 99999999999999999
    print(num)
    local time = os.time()
    print(1)
    print(type(time) .. tostring(time))
    print(1<<2)
    print(type(time) + num .. tostring(time))
end

function t2()
    t1()
end