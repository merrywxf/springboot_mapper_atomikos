# spirngboot_mapper_atomikos
本项目在其他项目上改造,原项目为一个含有逆向工程,集成了mybatis和通用mapper,
很抱歉没有存原项目git地址,反正都是github的也就无所谓了哈,//
  我对原项目改造:
    1,集成多数据源,使用atomikos控制事务
    2,使用spring扫描器,扫描类注入容器中,不使用controller注解
    3,修改controller返回类型,spring默认返回视图,如果添加responseBody则返回json字符串
      我只是将其他类型干掉,复制responsebody的适配器,修改判断条件,当然也可以保留原来的适配器,增加自定义的
    4,自定义注入路由信息到spring中,不写@RequestMapping注解
    5,简单测试了一下使用rabbitmq作为消息中间件,和radise作为缓存(代码中已注释掉)
 总之:修改项目只是为了更深入理解springmvc启动过程,对spring源码有所了解,目前可以说为我深入探索spring源码打开了大门!!!
 希望自己能坚持下去,可以更好的欣赏spring.
