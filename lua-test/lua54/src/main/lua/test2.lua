table = {}

function table.print(t)
    local json = ""
    for k, v in pairs(t) do
        if not (json == nil or json == "") then
            json = json .. ", "
        end
        if type(k) == "number" then
            json = json .. tostring(k)
        else
            json = json .. tostring(v)
        end
        json = json .. ":"
        if type(v) == "number" then
            json = json .. tostring(v)
        else
            json = json .. "\"" .. tostring(v) .. "\""
        end
    end
    local var = "{" .. json .. "}"
    print(var)
    return var
end

function t2(key, map)
    print(key)
    print(tostring(map))
    print(map[1])
    print(map[2])

    pcall(function()
        for i, fruit in ipairs(map) do
            print(i, type(fruit), fruit)
        end
    end)

    local array = { "apple", "banana" }

    for i, fruit in ipairs(array) do
        print(i, type(fruit), fruit)
    end

    return map
end

function t3(str)
    print("lua 接收 java 传递参数 - " .. tostring(str))
    javaT3("我是 lua")
    return str
end

--t2()

