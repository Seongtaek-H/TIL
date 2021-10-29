## 서블릿-WEB.xml

* WEB.xml 파일
* 경로 : 프로젝트/src/main/webapp/WEB-INF/WEB.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" id="WebApp_ID" version="4.0">
  <display-name>MVCBasic</display-name>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
    <!-- 클라이언트 요청을 서블릿에 요청 -->
  <servlet>
  	<servlet-name>controller</servlet-name> //서블릿 이름 
  	<servlet-class>mc.sn.controller.MemberController</servlet-class> //서블릿 사용하는 클래스의 풀네임
  </servlet>	
  <servlet-mapping>
  	<servlet-name>controller</servlet-name> //서블릿 이름 맞춰줌
  	<url-pattern>/CmdController</url-pattern> //사용할 url 패턴 
  </servlet-mapping>
    
</web-app>
```



