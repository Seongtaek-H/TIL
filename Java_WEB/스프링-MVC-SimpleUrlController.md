## 스프링 - MVC - SimpleUrlController

> 스프링에서 제공하는 MVC 기능 

**`SimpleUrlController 이용한 스프링 MVC 실습`**

* web.xml

  > 브라우저에서 *.do 로 요청시 스프링의 DispatcherServlet 클래스가 요청을 받을 수 있게 서플릿 매핑 설정
  >
  > <load-on-startup> 태그를 이용해 톰캣 실행시 미리 스프링의 DispatcherServlet을 메모리에 로드

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
   <servlet>
      <servlet-name>action</servlet-name>
      <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
       <!-- 값이 1이상이면 톰캣 실행시 DispatcherServlet을 미리 메모리에 로드(서블릿 초기화, init 호출) -->
       <!-- 지정하지 않거나 음수로 지정하면 브라우저에서 요청시 메모리에 로드 -->
      <load-on-startup>1</load-on-startup> 
   </servlet>

   <servlet-mapping>
      <servlet-name>action</servlet-name>
      <url-pattern>*.do</url-pattern>
   </servlet-mapping>
</web-app>
```

* action-servlet.xml : 설정파일 이름은 반드시 web.xml 서블릿 매핑시 사용한 servlet-name 태그 값인 action으로 시작

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:p="http://www.springframework.org/schema/p"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans   
http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
http://www.springframework.org/schema/aop
http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context-3.0.xsd">
    
    <!-- simpleUrlController 빈 생성 => "com.spring.ex01.SimpleUrlController" -->
    <bean id="simpleUrlController" class="com.spring.ex01.SimpleUrlController"/>
    <bean id="urlMapping"
      class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
      <property  name="mappings">
        <props>
            <!-- /test/index.do 요청 => sumpleUrlController 호출 -->
          <prop key="/test/index.do">simpleUrlController</prop>
        </props>
      </property>  
    </bean>
</beans>
```

* SimpleUrlController

``` java
package com.spring.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SimpleUrlController implements Controller { // 스프링에서 제공하는 Controller 인터페이스 구현
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 뷰이름을 ModelAndView에 index.jsp로 설정하여 반환
		return new ModelAndView("index.jsp");
	}
}
```

* index.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta  charset="UTF-8">
<title>spring 테스트입니다.</title>
</head>
<body>
   <h1>index.jsp파일입니다.</h1>
   <p>Hello Spring!!</p>
</body>
</html>
```

