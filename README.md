# netty-im-chat<br>
**主要 使用了 Netty 实战im即时通信**

 1. 以下是几个组件工程作用:<br>
    netty-test 学习测试使用netty的api项目<br>
    netty-im-chat-server im服务端<br>
    netty-im-chat-client im客户端<br>
    netty-im-chat-client-ui im客户端逻辑<br>
    netty-im-chat-common im通用组件<br>
    netty-im-chat-util im通用工具类<br>

 2. 已实现功能：<br>
    1.基础架构设计<br>
    2.登录功能【不含数据库】<br>

 3. 待实现功能：<br>
    1.单人消息聊天<br>
    2.群聊<br>
    3.登出功能<br>
    4.权限认证<br>

 4. 需要注意的地方：<br>
    1.消息接受和发送的代码模型应该简化，采用装饰者模式。【已实现】<br>
    2.客户端第一次启动应用连接到服务端，为保证私密信息（如登录账户、密码等）、需要服务器返回给客户端公钥，用于加密。【已实现】<br>
    3.登录功能需要加密信息。【已实现】<br>
  
