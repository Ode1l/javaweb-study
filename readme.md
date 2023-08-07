# JavaWeb

Web技术宽泛理解就是有的人提供了别人可以访问到的东西。可以是文件服务器，这种只要称之为访问的东西。

Web技术狭义来讲就指现在通过HTTP，HTTPS，RPC协议访问的。可以是Browser/Server 可以是Client/Server。

### 静态web
  * 写死的html，无法进行数据交互
### 动态web
  * 可以登录，可以进行数据交互，可以提供给不同人不同的页面
  * 现在动态web技术有Servlet/JSP(Java), ASP.NET(Microsoft), PHP, Django(Python Web), Flask(Python Web)

## Web服务器

实现整个web访问行为，其实是可以做到完全纯java来做，用socket编程即可，但是仅仅是处理物理机接口，并发管理，这些复杂情况就要耗费很多代码，因此一般抽象出web服务器软件，软件再封装提供成例如servlet的接口给开发者。会方便很多。

### IIS

微软开发，提供ASP接口，在windows操作系统中自带并捆绑

### Tomcat

Apache项目，提供Servlet和JSP接口，后续改名Jakarta。

#### tomcat使用

安装推荐解压缩方式，干净整洁。流程与linux保持一直。

需要配置JAVA环境变量。

- 可以配置启动的端口号
    - Tomcat的默认端口号为：8080
    - mysql：3306
    - http：80
    - https：443

```xml
<Connector port="8081" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />
```

- 可以配置主机的名称
    - 默认的主机名为：localhost->127.0.0.1
    - 默认网站应用存放的位置为：webapps

```xml
<Host name="www.iris.com"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">
```

#### webapps文件夹

写好的服务器放置在webapps文件夹下，开启tomcat可自动启动。

- 网站应该有的结构

```
--webapps ：Tomcat服务器的web目录
	-ROOT
	-books ：网站的目录名
		- WEB-INF
			-classes : java程序
			-lib：web应用所依赖的jar包
			-web.xml ：网站配置文件
		- index.html 默认的首页
		- static 
            -css
            	-style.css
            -js
            -img
         -.....
```

## Maven

Maven是一种项目架构管理工具。方便导入包。类似功能其他产品还有Gradle，常用于谷歌安卓开发。

Maven不需要环境变量可以使用，但是建议配置。配置后可以让IDEA等工具自动识别。path中有maven\bin即可

可以在terminal中`mvn -version`测试

普通maven项目结构是
```
--root : 项目根目录
    -src
        -main : pom配置文件，管理导包，编译war还是jar。
            -java
                -com.iris
            -resources : spring mybatis等配置文件，以及一切配置文件
        -test : 测试包
            -java
                -com.iris : 专业项目的测试类与原类一一对应
    pom.xml : pom配置文件，管理导包，编译war还是jar。
```

maven web
```
--root : 项目根目录
    -src
        -main : pom配置文件，管理导包，编译war还是jar。
            -java
                -com.iris
            -webapp : maven web特有的文件夹。
                -WEB-INF
                    web.xml  : 存放前端文件和前后端配置绑定文件
                index.jsp
        -test : 测试包
            -java
                -com.iris : 专业项目的测试类与原类一一对应
    pom.xml : pom配置文件，管理导包，编译war还是jar。maven web自动生成的jar也不同，有<url></url>等独特的标记
```

#### IDEA中绑定Tomcat

1. 在application server中绑定文件根地址，不是bin地址，是bin的上一级
2. 在同一地方，找到Deployment，添加组件。可以选择explored热插拔模式(有局限)。
3. 在Application context配置url

#### pom文件

> pom.xml是Maven的核心配置文件。

```xml
<?xml version="1.0" encoding="UTF-8"?>

<!--Maven版本和头文件-->
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <!--这里就是我们刚才配置的GAV-->
  <groupId>com.github</groupId>
  <artifactId>Javaweb-01-maven</artifactId>
  <version>1.0-SNAPSHOT</version>
  <!--Package：项目的打包方式
  jar：java应用
  war：JavaWeb应用
  -->
  <packaging>war</packaging>

  <!--配置-->
  <properties>
    <!--项目的默认构建编码-->
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <!--编码版本-->
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <!--项目依赖-->
  <dependencies>
    <!--具体依赖的jar包配置文件-->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
      <scope>provided</scope>
    </dependency>
  </dependencies>

  <!--项目构建用的东西-->
  <build>
    <finalName>Javaweb-01-maven</finalName>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-war-plugin</artifactId>
          <version>3.2.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>
```

> maven由于他的约定大于配置，我们之后可以能遇到我们写的配置文件，无法被导出或者生效的问题，解决方案：

```xml
<!--在build中配置resources，来防止资源导出失败的问题-->
<build>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
    </resources>
</build>
```

### maven默认web项目中的web.xml版本问题。

```xml
<!DOCTYPE web-app PUBLIC
        "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
        "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>
</web-app>
```

建议替换为webapp4.0版本和tomcat一致。

   ```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                        http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0"
         metadata-complete="true">
   
</web-app>
   ```

## Servlet

sun公司开发的Tomcat接口叫Servlet。

**把实现了Servlet接口的Java程序叫做，Servlet**。

> Servlet接口Sun公司有两个默认的实现类：HttpServlet，GenericServlet。

写一个类继承HttpServlet即可

```java
package com.iris.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
    // 由于get或post只是请求实现的不同的方式，可以相互调用，业务逻辑都是一样；
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, IOException {
        // ServletOutputStream outputStream = resp.getOutputStream();
        PrintWriter writer = resp.getWriter();  // 响应流
        writer.print("Hello, Servlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, ServletException {
        super.doPost(req, resp);
    }
}
```

编写Servlet的映射在web.xml中

```xml
 <!--注册Servlet-->
    <servlet>
        <servlet-name>hello</servlet-name>
        <servlet-class>com.iris.servlet.HelloServlet</servlet-class>
    </servlet>
    <!--Servlet的请求路径-->
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
```

### Mapping映射

1. 一个Servlet可以指定一个映射路径。

   ```xml
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/hello</url-pattern>
       </servlet-mapping>
   ```

2. 一个Servlet可以指定多个映射路径。

   ```xml
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/hello</url-pattern>
       </servlet-mapping>
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/hello2</url-pattern>
       </servlet-mapping>
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/hello3</url-pattern>
       </servlet-mapping>
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/hello4</url-pattern>
       </servlet-mapping>
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/hello5</url-pattern>
       </servlet-mapping>
   ```

3. 一个Servlet可以指定通用映射路径。

   ```xml
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/hello/*</url-pattern>
       </servlet-mapping>
   ```

4. 默认请求路径。

   ```xml
       <!--默认请求路径-->
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/*</url-pattern>
       </servlet-mapping>
   ```

5. 指定一些后缀或者前缀等等….

   ```xml
       <!-- 可以自定义后缀实现请求映射
            注意点，*前面不能加项目映射的路径
            hello/subei.github
           -->
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>*.github</url-pattern>
       </servlet-mapping>
   ```

6. 优先级问题。

  - 指定了固有的映射路径优先级最高，如果找不到就会走默认的处理请求；

   ```java
   package com.github.servlet;
   
   import javax.servlet.ServletException;
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   import java.io.IOException;
   import java.io.PrintWriter;
   
   public class ErrorServelt extends HelloServlet{
       @Override
       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           resp.setContentType("text/html");
           resp.setCharacterEncoding("utf-8");
   
           PrintWriter writer = resp.getWriter();
           writer.println("<h1>404</h1>");
       }
   
       @Override
       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           super.doPost(req, resp);
       }
   }
   ```

   ```xml
   <!--  404  -->
       <servlet>
           <servlet-name>error</servlet-name>
           <servlet-class>com.github.servlet.ErrorServlet</servlet-class>
       </servlet>
       <servlet-mapping>
           <servlet-name>error</servlet-name>
           <url-pattern>/*</url-pattern>
       </servlet-mapping>
   ```

### ServletContext

web容器在启动的时候，它会为每个web程序都创建一个对应的ServletContext对象，ServletContext是服务器，每个服务器只会有一个ServletContext。

- 共享数据

ServletContext可以存储数据，在这个Servlet中保存的数据，可以在另外一个servlet中拿到,例子GetServlet，SetServlet体现

- 获取初始化参数

web.xml中可以配置context-param。作为初始化参数。

```xml
<!-- 配置一些Web应用初始化参数 -->
    <context-param>
        <param-name>url</param-name>
        <param-value>jdbc:mysql://localhost:3306/mybatis</param-value>
    </context-param>
    <context-param>
        <param-name>Name</param-name>
        <param-value>NMSL</param-value>
    </context-param>

```

类中获取

```java
        ServletContext context = this.getServletContext();
        String url = context.getInitParameter("url");
        resp.getWriter().print(url);
```

- 请求转发

```java
        ServletContext context = this.getServletContext();
        // 转发的请求路径
        // RequestDispatcher requestDispatcher = context.getRequestDispatcher("/gp");
        // 调用forward实现请求转发;
        // requestDispatcher.forward(req,resp);
        context.getRequestDispatcher("/getInit").forward(req,resp);
```

- 读取资源文件

```java
        InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/classes/com/github/servlet/aa.properties");

        Properties prop = new Properties();
        prop.load(is);
        String user = prop.getProperty("username");
        String pwd = prop.getProperty("password");

        resp.getWriter().print(user+":"+pwd);
```

这里需要注意文件的地址，可以在target文件夹(编译后的地方)里找，该文件在项目中的resources里，编译后在/WEB-INF/classes/内

### HttpServletResponse

- web服务器接收到客户端的http请求，针对这个请求，分别创建一个代表请求的HttpServletRequest对象，代表响应的一个HttpServletResponse；

  - 如果要获取客户端请求过来的参数：找HttpServletRequest
  - 如果要给客户端响应一些信息：找HttpServletResponse

- 响应的状态码。

```java
    int SC_CONTINUE = 100;
    int SC_SWITCHING_PROTOCOLS = 101;
    int SC_OK = 200;
    int SC_CREATED = 201;
    int SC_ACCEPTED = 202;
    int SC_NON_AUTHORITATIVE_INFORMATION = 203;
    int SC_NO_CONTENT = 204;
    int SC_RESET_CONTENT = 205;
    int SC_PARTIAL_CONTENT = 206;
    int SC_MULTIPLE_CHOICES = 300;
    int SC_MOVED_PERMANENTLY = 301;
    int SC_MOVED_TEMPORARILY = 302;
    int SC_FOUND = 302;
    int SC_SEE_OTHER = 303;
    int SC_NOT_MODIFIED = 304;
    int SC_USE_PROXY = 305;
    int SC_TEMPORARY_REDIRECT = 307;
    int SC_BAD_REQUEST = 400;
    int SC_UNAUTHORIZED = 401;
    int SC_PAYMENT_REQUIRED = 402;
    int SC_FORBIDDEN = 403;
    int SC_NOT_FOUND = 404;
    int SC_METHOD_NOT_ALLOWED = 405;
    int SC_NOT_ACCEPTABLE = 406;
    int SC_PROXY_AUTHENTICATION_REQUIRED = 407;
    int SC_REQUEST_TIMEOUT = 408;
    int SC_CONFLICT = 409;
    int SC_GONE = 410;
    int SC_LENGTH_REQUIRED = 411;
    int SC_PRECONDITION_FAILED = 412;
    int SC_REQUEST_ENTITY_TOO_LARGE = 413;
    int SC_REQUEST_URI_TOO_LONG = 414;
    int SC_UNSUPPORTED_MEDIA_TYPE = 415;
    int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
    int SC_EXPECTATION_FAILED = 417;
    int SC_INTERNAL_SERVER_ERROR = 500;
    int SC_NOT_IMPLEMENTED = 501;
    int SC_BAD_GATEWAY = 502;
    int SC_SERVICE_UNAVAILABLE = 503;
    int SC_GATEWAY_TIMEOUT = 504;
    int SC_HTTP_VERSION_NOT_SUPPORTED = 505;
```

#### 下载文件

1. 向浏览器输出消息；
2. 下载文件：
    1. 要获取下载文件的路径
    2. 下载的文件名是啥
    3. 设置想办法让浏览器能够支持下载我们需要的东西
    4. 获取下载文件的输入流
    5. 创建缓冲区
    6. 获取OutputStream对象
    7. 将FileOutputStream流写入到buffer缓冲区
    8. 使用OutputStream将缓冲区中的数据输出到客户端

#### 3.验证码功能

- 验证怎么生成:
    - 前端实现
    - 后端实现，需要用到 Java 的图片类，生产一个图片

```java
package com.iris.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 如何让浏览器3秒自动刷新一次;
        resp.setHeader("refresh", "3");

        // 在内存中创建一个图片
        BufferedImage image = new BufferedImage(90, 40, BufferedImage.TYPE_INT_RGB);
        // 得到图片,笔
        Graphics2D g = (Graphics2D) image.getGraphics();
        // 设置图片的背景颜色
        g.setColor(Color.white);
        g.fillRect(0, 0, 90, 40);
        // 给图片写数据
        g.setColor(Color.RED);
        g.setFont(new Font(null, Font.BOLD, 20));
        g.drawString(makeNum(), 8, 30);

        // 告诉浏览器，这个请求用图片的方式打开
        resp.setContentType("image/jpeg");
        // 网站存在缓存，不让浏览器缓存
        resp.setDateHeader("expires", -1);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");

        // 把图片写给浏览器
        ImageIO.write(image, "jpg", resp.getOutputStream());

    }

    // 生成随机数
    private String makeNum() {
        Random random = new Random();
        String num = String.valueOf(random.nextInt(9999999));
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 7 - num.length(); i++) {
            sb.append("0");
        }
        num = sb + num;
        return num;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

```xml
    <servlet>
        <servlet-name>img</servlet-name>
        <servlet-class>com.iris.servlet.ImageServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>img</servlet-name>
        <url-pattern>/img</url-pattern>
    </servlet-mapping>
```

#### 实现重定向

- B一个web资源收到客户端A请求后，B他会通知A客户端去访问另外一个web资源C，这个过程叫`重定向`。

重定向应用于
- 用户登录



> 面试题：请你聊聊重定向和转发的区别？

- 相同点：页面都会实现跳转；
- 不同点：
    - 请求转发的时候，url不会产生变化；
    - 重定向时候，url地址栏会发生变化；

![image-20210726214558482](img/image-20210726214558482.png)

#### 5.简单实现登录重定向


```jsp
<%--
    这里提交的路径需要寻找到项目的路径
    ${pageContext.request.contextPath} : 代表当前项目
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录</title>
  </head>
  <body>
  <form action="${pageContext.request.contextPath}/login" method="get">
    用户名:<input type="text" name="username"><br>
    密  码:<input type="password" name="password"><br>
    <input type="submit">
  </form>
  </body>
</html>
```

```JAVA
package com.github.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理请求
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(username+":"+password);

        // 重定向时需注意路径问题，否则为404;
        // /response/success.jsp
        resp.sendRedirect("/response/success.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

```xml
    <servlet>
        <servlet-name>/request</servlet-name>
        <servlet-class>com.github.servlet.RequestTest</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>/request</servlet-name>
        <url-pattern>/login</url-pattern>
    </servlet-mapping>
```

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成功</title>
</head>
<body>

<h1>登录成功！！！</h1>

</body>
</html>
```

### HttpServletRequest

- HttpServletRequest代表客户端的请求，用户通过Http协议访问服务器，HTTP请求中的所有信息会被封装到HttpServletRequest，通过这个HttpServletRequest的方法，获得客户端的所有信息；

request需要配合前端使用，让前端把参数传过来才能接受处理

```xml
    <form action="${pageContext.request.contextPath}/login" method="post">
        用户名：<input type="text" name="username"><br>
        密码：<input type="text" name="password"><br>
        爱好：
        <input type="checkbox" name="hobby" value="女孩">女孩
        <input type="checkbox" name="hobby" value="代码">代码
        <input type="checkbox" name="hobby" value="游戏">游戏
        <input type="checkbox" name="hobby" value="唱歌">唱歌
        <br>
        <input type="submit">
    </form>
```

前端有input，后端才能取到

```java
package com.iris.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String[] hobbys = req.getParameterValues("hobbies");
        System.out.println("=============================");
        // 后台接收中文乱码问题
        System.out.println(username);
        System.out.println(password);
        System.out.println(Arrays.toString(hobbys));
        System.out.println("=============================");

        System.out.println(req.getContextPath());
        // 通过请求转发
        // 这里的 / 代表当前的web应用
        req.getRequestDispatcher("/success.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

