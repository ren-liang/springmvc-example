# 基于注解的Controller开发
Spring MVC 2.5以上版本的Controller是一个普通的Java类不需要继承某个父类或者实现某个接口，在一定程度上简化了Controller的开发，使Controller的开发更加灵活
## 1.@Controller
spring mvc中使用@Controller注解声明某个类是一个Controller在springmvc的核心配置文件中需要配置注解的扫描路径并开启注解
DispatcherServlet初始化WebAppcationContext上下文时会扫描并初始化这些Controller将这些Controller初始化到Bean容器
## 2.@ResqustMapping
用于为控制器指定可以处理哪些 URL请求以及对应的http请求方式
## 3.访问地址：
http://localhost:8080/springmvc-example-03/product/inputproduct



