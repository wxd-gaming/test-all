---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by admin.
--- DateTime: 2024/8/19 09:49
---


function t1(str)
    print(str)
    local num = 99999999999999999
    print(num)
    print(1 << 2)
    local time = os.time()
    print(type(time) .. " - " .. tostring(time))
    --print(type(time) + num .. tostring(time))
    local str = "1t"
    print(debug.traceback())
    print(str + num)
end

local success, error = pcall(t1, "d")
if not success then
    print(debug.traceback())
    print("An error occurred: " .. error)
else
    print("Code executed successfully.")
end