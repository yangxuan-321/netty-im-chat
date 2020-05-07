# 即时聊天 netty-im-chat<br>
**即时通信im实战 主要 使用了 Netty  测试账号为: admin 密码为: 123**

 1. 以下是几个组件工程作用:<br>
    netty-test 学习测试使用netty的api项目<br>
    netty-im-chat-server im服务端<br>
    netty-im-chat-client im客户端<br>
    netty-im-chat-client-ui im客户端逻辑<br>
    netty-im-chat-common im通用组件<br>
    netty-im-chat-util im通用工具类<br>

 2. 已实现功能：<br>
    1.基础架构设计<br>
    2.登录功能【不含数据库】,登录状态以及会话的保持<br>
    3.单人消息聊天<br>
    4.使用责任链模式设计Filter过滤器来实现其他操作时的权限校验<br>
    5.服务端采用Disruptor高性能并发框架,来避免因 业务 处理链过长 导致的WorkGroup线程组性能损耗。<br>

 3. 待实现功能：<br>
    1.群聊<br>
    2.登出功能<br>
    3.权限认证<br>
    4.服务启动--各组件的生命周期管理(实现中--参考tomcat源码)

 4. 需要注意的地方：<br>
    1.消息接受和发送的代码模型应该简化，采用观察者模式(事件驱动)。【已实现】<br>
    2.客户端第一次启动应用连接到服务端，为保证私密信息（如登录账户、密码等）、需要服务器返回给客户端公钥，用于加密。【已实现】<br>
    3.登录功能需要加密信息。【已实现】<br>
    4.客户端和服务器可以相互收发消息。<br>
    5.解决了netty存在的粘包、半包问题。<br>
    6.使用责任链模式设计Filter过滤器来实现其他操作时的权限校验<br>
    
    

**欢迎交流qq:1415642719 , 邮箱:yangxuan_321@163.com**
