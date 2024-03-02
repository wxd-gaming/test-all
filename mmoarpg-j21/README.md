# 应用软件项目框架落地

## 更新仓库地址

github https://github.com/orgs/wxd-gaming/repositories<br>
gitee  &nbsp;&nbsp;&nbsp;https://gitee.com/wxd-gaming<br>
博客首页 https://www.cnblogs.com/wxd-gameing<br>

## 简介

本模块是应用框架的落地项目，是可以启动的具体实现功能；

| 项目模块           | 描述                                      |
|----------------|-----------------------------------------|
| core           | 基础模块                                    |
| gamesr         | 是game模块的启动项目                            |
| gamesr-script  | 是game模块的具体逻辑实现,是通过gamesr启动后作为子容器隔离加载进容器 |
| loginsr        | 是登录模块，用于处理登录，账户体系以及一些对外http服务           |
| loginsr-script | 是登录模块的逻辑实现，是通过loginsr启动后作为子容器隔离加载进容器    |
| message        | 本模块是存放通信消息模块，本项目采用protobuf形式进行通信        |
| robotsr        | 考虑机器人，压测，测试逻辑                           |
| robotsr-script | 是压测工具具体业务逻辑实现                           |
|                | 其余几个项目只是简单测试忽略不计                        |

## 说明

pom.xml 文件 profile 数据库相关配置
