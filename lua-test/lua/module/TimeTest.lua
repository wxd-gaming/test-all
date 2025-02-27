---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by 無心道(15388152619).
--- DateTime: 2024/12/27 16:32
---

DiffTimeDayTest = {}

function DiffTimeDayTest.diffTimeDay()
    local nowSec = os.time()
    local openServerTime = 1730390400
    print(TimeUtil.timeFormat(openServerTime))
    print(TimeUtil.timeFormat(TimeUtil.addDay(openServerTime, 30) - 1))
    print(TimeUtil.timeFormat(TimeUtil.addDay(openServerTime, 60) - 1))
    print(TimeUtil.timeFormat(TimeUtil.addDay(openServerTime, 90) - 1))
    print(TimeUtil.timeFormat(TimeUtil.addDay(openServerTime, 120) - 1))
    print(TimeUtil.timeFormat(TimeUtil.addDay(openServerTime, 150) - 1))
    -- 开服天数
    local serverOpenDays = TimeUtil.diffDays(nowSec, openServerTime) + 1
    --通过开服时间计算当前最大购买次数
    local resetDay = 30
    local maxFirstCount = math.ceil(serverOpenDays / resetDay)
    --计算到本轮结束还有多少天
    local endDay = maxFirstCount * resetDay - serverOpenDays + 1;
    local nowStartSec = TimeUtil.creteTimeDayStart4Sec(nowSec)
    --计算出结束时间
    local endDayTime = TimeUtil.addDay(nowStartSec, endDay) - 1
    --往前推计算出开始时间
    local startDayTime = TimeUtil.creteTimeDayStart4Sec(TimeUtil.addDay(endDayTime, -(resetDay - 1)))
    --获取开始时间那一天的00:00:00
    startDayTime = TimeUtil.creteTimeDayStart4Sec(startDayTime)
    print("充值首充判定", TimeUtil.timeFormat(startDayTime), TimeUtil.timeFormat(nowSec), TimeUtil.timeFormat(endDayTime),
          "openday", serverOpenDays, maxFirstCount,
          endDay, endDay
    )
    return startDayTime, endDayTime, serverOpenDays <= resetDay
end

function DiffTimeDayTest.onInit()
    --DiffTimeDayTest.diffTimeDay()
    --
    --local tableString = "{key1 = 'value1', key2 = 'value2'}"
    --local t = load("return " .. tableString)()
    ----字符串通过load形式加载成table
    --gameDebug.print("load string", type(t), t)
end
