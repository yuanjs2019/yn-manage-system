





# YN后台管理系统：Yn-manage-system

[![spring-boot](https://img.shields.io/badge/spring--boot-2.0.4-green.svg) ](http://spring.io/projects/spring-boot) [ ](http://mp.baomidou.com/)[![thymeleaf](https://img.shields.io/badge/thymeleaf-3.0.1-yellow.svg) ](https://www.thymeleaf.org/) [![lombok](https://img.shields.io/badge/shiro-1.4.0-blue.svg) ](https://projectlombok.org/) [![freemarker](https://img.shields.io/badge/jedis-2.9.0-blue.svg) ](http://freemarker.foofun.cn/) [![bootstrap](https://img.shields.io/badge/bootstrap-4.1.3-blue.svg) ](https://www.bootcss.com/)

 **如果对您有帮助，您可以点右上角 "`Star`" 支持一下 谢谢！**

 **如果您想获悉项目实时更新信息，您可以点右上角 [![Fork me on Gitee](./outimg/forkme.svg)](https://gitee.com/yuan625/yn-manage-system)感谢您的支持！**

 **本项目还在不断开发完善中,如有建议或问题请通`Issues`反馈！**

### 项目介绍

​     Yn-manage-system是基于SpringBoot+Thymeleaf+Jedis+Bootstrap 研发的后台管理项目，是基于[FEBS-Shiro](https://github.com/wuyouzhuguli/FEBS-Shiro/tree/mysql)项目进行二次开发的。项目基础框架采用全新的Java Web开发框架 —— Spring Boot2.0.4，消除了繁杂的XML配置，使得二次开发更为简单；数据访问层采用Mybatis，同时引入了通用Mapper和PageHelper插件，可快速高效的对单表进行增删改查操作，消除了大量传统XML配置SQL的代码；安全框架采用时下流行的Apache Shiro，可实现对按钮级别的权限控制。项目可以PC端及移动端的能自适应，提高读者的体验。操作体验流畅，使用非常优化，欢迎大家使用及进行二次开发。

### 浏览器兼容

| [![Edge](https://camo.githubusercontent.com/d0739e3928b4c84f6c2cd9902bcc379f18c645ffce6089e2ca2a1ecf7a2965cb/68747470733a2f2f7261772e6769746875622e636f6d2f616c7272612f62726f777365722d6c6f676f732f6d61737465722f7372632f617263686976652f696e7465726e65742d6578706c6f7265725f392d31312f696e7465726e65742d6578706c6f7265725f392d31315f34387834382e706e67)](http://godban.github.io/browsers-support-badges/) IE | [![Edge](https://raw.githubusercontent.com/alrra/browser-logos/master/src/edge/edge_48x48.png)](http://godban.github.io/browsers-support-badges/) Edge | [![Firefox](https://raw.githubusercontent.com/alrra/browser-logos/master/src/firefox/firefox_48x48.png)](http://godban.github.io/browsers-support-badges/) Firefox | [![Chrome](https://raw.githubusercontent.com/alrra/browser-logos/master/src/chrome/chrome_48x48.png)](http://godban.github.io/browsers-support-badges/) Chrome | [![Safari](https://raw.githubusercontent.com/alrra/browser-logos/master/src/safari/safari_48x48.png)](http://godban.github.io/browsers-support-badges/) Safari | [![Edge](https://camo.githubusercontent.com/8663fa4d6a0533eac6da67e2bbfaee3cc1ee6644454a88b21bf31f8196bb0d2f/68747470733a2f2f7261772e6769746875622e636f6d2f616c7272612f62726f777365722d6c6f676f732f6d61737465722f7372632f6f706572612f6f706572615f34387834382e706e67)](http://godban.github.io/browsers-support-badges/) Opera |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| IE 10+                                                       | Edge                                                         | last 15 versions                                             | last 15 versions                                             | last 10 versions                                             | last 15 versions                                             |

### 功能模块

系统功能模块组成如下所示：

```
 ├─系统管理
│  ├─字典管理
│  ├─用户管理
│  ├─菜单管理
│  ├─角色管理
│  └─部门管理
├─系统监控
│  ├─在线用户
│  ├─系统日志
│  ├─Redis监控
│  └─Redis终端
│─博客管理
│  ├─标签管理
│  ├─分类管理
│  ├─系列管理
│  ├─文档管理
│ -├─文章管理
```

### 系统特点

1. 前后端请求参数校验
2. 支持Excel导入导出
3. 前端页面布局多样化，主题多样化
4. 浏览器兼容性好，页面支持PC，Pad和移动端。
5. 代码简单，结构清晰

### 开发者信息

- 系统名称：yn-blog博客系统
- 作者：softbabet
- 邮箱：yuanjs625@163.com

### 页面演示

![登录页](./outimg/1.png)

![标签管理](./outimg/2.png)

![文档管理](./outimg/3.png)

![菜单管理](./outimg/4.png)

![Redis监控](./outimg/5.png)

![密码修改](./outimg/6.png)

![换头像](./outimg/7.png)

![删除](./outimg/8.png)

### 数据库表

![数据库表](./outimg/10.png)
