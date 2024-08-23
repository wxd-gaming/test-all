function t2(key)
    t1()
    print(key)
    return { a = "s", b = "1" }
end

function t3(str)
    print("lua 接收 java 传递参数 - " .. tostring(str))
    javaT3("我是 lua")
    return str
end

--t2()