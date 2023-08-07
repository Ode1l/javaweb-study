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

5. 