function t2(key, map)
    print(key)
    print(tostring(map))
    print(map[1])
    print(map[2])
    for i, fruit in pairs(map) do
        print(i, type(fruit), fruit)
    end

    local array = { "apple", "banana" }

    for i, fruit in ipairs(array) do
        print(i, fruit)
    end

    return map
end

function t3(str)
    print("lua 接收 java 传递参数 - " .. tostring(str))
    javaT3("我是 lua")
    return str
end

--t2()