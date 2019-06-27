# ssm-admin

ssm-admin是基于`ssm` 前端采用`vue-cli`组件的快速开发脚手架单体服务。(该项目仅供学习参考)

##1 项目技术栈

* 数据库`mysql6.5`

*  控制层`spring mvc`

* 权限`shiro`

*  持久化`mybatis`

*  日志 `log4j`

*  数据库连接池`c3p0`

*  序列化` jackson`

*  分页 ` pagehelper `

*  数据校验 `JSR303`

*  单元测试` junit`

------

## 2 权限管理

###2.1 使用 `shrio`自定义拦截器完成基于`cookies`和`session`的用户权限管理

## 3 日志收集

###3.1 使用`spring-aop`完成日志收集

------

## 4 功能截图

### 4.1 基本功能
#### 登录页
![image.png](https://upload-images.jianshu.io/upload_images/4157022-be2c05f3cffe4a67.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)





-----
#### 首页

![image.png](https://upload-images.jianshu.io/upload_images/4157022-6b79a7a49a986536.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



-----
#### 系统菜单

![image.png](https://upload-images.jianshu.io/upload_images/4157022-e5e5c7a22cb7a940.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



-----
####学校管理

![image.png](https://upload-images.jianshu.io/upload_images/4157022-bb197cdfe9bf9873.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



-----
####系统用户

![image.png](https://upload-images.jianshu.io/upload_images/4157022-16084aae35dc2635.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



-----
####系统角色

![image.png](https://upload-images.jianshu.io/upload_images/4157022-88831b0368fafb0e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


-----
####字典管理

![image.png](https://upload-images.jianshu.io/upload_images/4157022-e3b3870792f6513c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



## 5 项目说明

 [地址](https://www.jianshu.com/p/ea1fe005848c)
* 启动说明
**1 后端启动**
git clone xx或者Download Zip
打开IDEA --> File --> New --> Open
项目导入后，**配置名为ssm**
**2 前端启动**
git clone xx或者Download Zip
// install dependencies
**npm install**
// serve with hot reload at localhost:8080
**npm run dev**
* 数据库：ssm/src/main/resources/db/loop_admin_v0.sql
* 数据库地址：ssm/src/main/resources/db.properties中修改数据库地址，账号，密码
* shiro权限控制：ssm/src/main/resources/application.xml 配置shiro**不需要登录的路径**，
匹配了四条
```
/user/permlogin=anon   //登录接口
/user/permloginout=anon  //登出接口
/user/signup=anon    //注册接口
/authcode=anon        //验证码接口
```
 - 匹配规则如下
```
    <bean id="shiroFilter" class="cc.aies.web.fifter.MyShiroFilterFactoryBean">
        <property name="securityManager" ref="securityManager"/>
        <property name="loginUrl" value="/loginerror"/>
        <property name="successUrl" value=""/>
        <property name="unauthorizedUrl" value=""/>
        <property name="filterChainDefinitions">
            <!--
           配置哪些页面需要受保护.
           以及访问这些页面需要的权限.
           1). anon 可以被匿名访问
           2). authc 必须认证(即登录)后才可能访问的页面.
           3). logout 登出.
           4). roles 角色过滤器
           /admin?=authc      表示可以请求以admin开头的字符串，如xxx/adminfefe，但无法匹配多个，即xxx/admindf/admin是不行的
            /admin*=authc      表示可以匹配零个或者多个字符，如/admin，/admin1，/admin123，但是不能匹配/admin/abc这种
            /admin/**=authc      表示可以匹配零个或者多个路径，如/admin，/admin/ad/adfdf等
       -->
            <value>
                /user/permlogin=anon  
                /user/permloginout=anon
                /user/signup=anon
                /authcode=anon
            </value>
        </property>
        <property name="filters">
            <map>
                <entry key="roles">
                    <bean class="cc.aies.web.fifter.MyShiroFilter"/>
                </entry>
            </map>
        </property>
    </bean>
```
- 在前端界面系统管理中的系统菜单去添加**需要登录且拦截**（需要登录但不用拦截的接口不用填写）的接口url 填写规则如下：
           /admin?    表示可以请求以admin开头的字符串，如xxx/adminfefe，但无法匹配多个，即xxx/admindf/admin是不行的
            /admin*     表示可以匹配零个或者多个字符，如/admin，/admin1，/admin123，但是不能匹配/admin/abc这种
            /admin/**      表示可以匹配零个或者多个路径，如/admin，/admin/ad/adfdf等
   **注意：不支持restful风格**
![](https://upload-images.jianshu.io/upload_images/4157022-ef45d0d4fe2a9d6d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 然后再进行角色的菜单分配，用户的角色分配，来实现不同的用户动态的访问不同的菜单和按钮权限。
 
------

## 6 项目地址

[后端地址](https://github.com/ayhyh/ssm-admin)

[前端地址](https://github.com/ayhyh/ssm-admin-ui)

------
## 7 项目技术点
- rbac0模式 基于角色的权限模式，只精确到url级别，没有分离菜单和按钮。
- 基于八张表 用户表 角色表 用户角色关联表 菜单表 菜单角色关联表 机构表 字典表 日志表

## License
Apache License Version 2.0