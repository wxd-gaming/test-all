---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by admin.
--- DateTime: 2024/8/19 09:49
---
---
function errFunc(error)
    print("\n==============\nerrFunc - " .. debug.traceback(error) .. "\n==============\n")
end

function t1(str)
    print(str, type(str))
    local num = 99999999999999999
    print(num)
    --print(1 << 2)
    local time = os.time()
    print(type(time) .. " - " .. tostring(time))
    --print(type(time) + num .. tostring(time))
    --print(debug.traceback())
    --print(str + num)
    return str
end



--local success, error = pcall(t1, "d")
--if not success then
--    errFunc(error)
--else
--    print("Code executed successfully.")
--end

