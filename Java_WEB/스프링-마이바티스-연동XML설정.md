## 스프링-마이바티스 연동

#### 스프링-마이바티스 연동 관련 XML 설정

* web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://Java.sun.com/xml/ns/javaee"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	id="WebApp_ID" version="3.0">
	<listener>
      <listener-class>
         org.springframework.web.context.ContextLoaderListener
     </listener-class>
   </listener>
	<context-param>
		<!-- 마이바티스 설정 파일 읽어옴 -->
      <param-name>contextConfigLocation</param-name>
      <param-value>
          /WEB-INF/config/action-mybatis.xml
          /WEB-INF/config/action-service.xml
      </param-value>
   </context-param>
	
	<servlet>
		<servlet-name>action</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>action</servlet-name>
		<url-pattern>*.do</url-pattern>
	</servlet-mapping>
</web-app>
```

* action-servlet.xml : 뷰 관련 빈과 각 URL 요청명에 대해 호출할 메서드 설정

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
		
	<bean id="viewResolver"   
       class="org.springframework.web.servlet.view.InternalResourceViewResolver">
      <property name="viewClass" 
                          value="org.springframework.web.servlet.view.JstlView"/>
      <property name="prefix" value="/WEB-INF/views/member/" /> 
      <property name="suffix" value=".jsp"/>
   </bean>
   
   <bean id="memberController" 
                            class="com.spring.member.controller.MemberControllerImpl">
      <property name="methodNameResolver">
         <ref local="memberMethodResolver"/>
      </property>
      <property name="memberService" ref="memberService"/>
   </bean>

   <bean  id="memberMethodResolver"
   class="org.springframework.web.servlet.mvc.multiaction.PropertiesMethodNameResolver" >
      <property  name="mappings" >
        <props>
          <prop key="/member/listMembers.do" >listMembers</prop>
          <prop key="/member/addMember.do" >addMember</prop>
          <prop key="/member/removeMember.do">removeMember</prop>
          <prop key="/member/memberForm.do" >form</prop>
       </props>
      </property>
   </bean>
   
   <bean id="memberUrlMapping"
       class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
      <property name="mappings">
         <props>
            <prop key="/member/*.do">memberController</prop>
         </props>
      </property>
   </bean>
</beans>
```

* action-mybatis.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans
 xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd"
 xmlns:tx="http://www.springframework.org/schema/tx" xmlns:context="http://www.springframework.org/schema/context"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://www.springframework.org/schema/beans">
	
	<!-- propertyPlaceholderConfigurer 클래스를 이용해 데이터베이스 설정 정보를 jdbc.properties 에서 읽어옴 -->
	<bean id="propertyPlaceholderConfigurer"
	class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
		<property name="locations">
			<value>/WEB-INF/config/jdbc.properties</value>
		</property>
	</bean>

	<!-- PooledDataSource 클래스를 이용해서 dataSource bean 생성 -->
 	<bean id="dataSource" class="org.apache.ibatis.datasource.pooled.PooledDataSource">
		<property name="driver" value="${jdbc.driverClassName}" />
		<property name="url" value="${jdbc.url}" />
		<property name="username" value="${jdbc.username}" />
		<property name="password" value="${jdbc.password}" />
	</bean>
	
	<bean id="sqlSessionFactory"
		class="org.mybatis.spring.SqlSessionFactoryBean">
		<!-- sqlSessionFactory 클래스를 이용해 dataSource bean 생성 -->
		<property name="dataSource" ref="dataSource" />
		<!-- configLocation 속성에 modelConfig.xml 설정 -->
		<property name="configLocation"
			value="classpath:mybatis/model/modelConfig.xml" />
		<!-- mapperLocations 속성에 mybatis/mappers/ 의 모든 매퍼 파일을 읽어와 설정 -->
		<property name="mapperLocations" value="classpath:mybatis/mappers/*.xml" />
	</bean>
	
	<!-- SqlSessionTemplat를 이용해 sqlSession bean 생성 -->
	<bean id="sqlSession"
		class="org.mybatis.spring.SqlSessionTemplate">
		<constructor-arg index="0" ref="sqlSessionFactory"></constructor-arg>
	</bean>

	<!-- sqlSession bean을 memberDAO bean 속성에 주입 -->
	<bean id="memberDAO"
		class="com.spring.member.dao.MemberDAOImpl">
		<property name="sqlSession" ref="sqlSession"></property>
	</bean>
</beans>
```

* action-service.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns:aop="http://www.springframework.org/schema/aop"
xmlns:tx="http://www.springframework.org/schema/tx"
xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd
http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-2.0.xsd
http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-2.0.xsd">

	<!-- memberDAO bean을 사용할 memberService bean 설정 -->
   <bean id="memberService" class="com.spring.member.service.MemberServiceImpl">
      <property name="memberDAO" ref="memberDAO"/>
   </bean>
</beans>
```



#### 마이바티스 관련 XML 설정

* modelConfig.xml : <typeAliases> 태그로 마이바티스에서 데이터 전달에 사용될 MemberVO bean 설정

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration 	PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
	"http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
	<typeAliases>
		<typeAlias type="com.spring.member.vo.MemberVO"  alias="memberVO" />
	</typeAliases>
</configuration>
```



* member.xml : SQL문 설정

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
      PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
   "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="mapper.member">
	<resultMap id="memResult" type="memberVO">
		<result property="id" column="id" />
        <result property="pwd" column="pwd" />
        <result property="name" column="name" />
        <result property="email" column="email" />
        <result property="joinDate" column="joinDate" />
	</resultMap> 

	<select id="selectAllMemberList" resultMap="memResult">
      <![CDATA[
         select * from t_member	order by joinDate desc	 	
      ]]>
	</select>

	<select id="selectName" resultType="String">
    <![CDATA[
	select name from t_member
	where id = 'hong'			
    ]]>
	</select>
	
	<select id="selectPwd" resultType="int" >
	  <![CDATA[ 
	    select pwd from t_member 
	    where id = 'hong'
	 ]]>
	 </select> 
	 
	<select id="selectMemberById" resultType="memberVO"  parameterType="String" >
      <![CDATA[
         select * from t_member
         where
         id=#{id}			
      ]]>
	 </select>	
	
	<select id="selectMemberByPwd" resultMap="memResult"  parameterType="int" >
      <![CDATA[
         select * from t_member
         where
         pwd = #{pwd}			
      ]]>
    </select>
    
     <insert id="insertMember"  parameterType="memberVO">
		<![CDATA[
		 insert into t_member(id,pwd, name, email)
		 values(#{id}, #{pwd}, #{name}, #{email})
		]]>      
	</insert>
	
	<insert id="insertMember2"  parameterType="java.util.HashMap">
		<![CDATA[
			 insert into t_member(id,pwd, name, email)
			 values(#{id}, #{pwd}, #{name}, #{email})
		]]>      
   </insert>
   
   <update id="updateMember"  parameterType="memberVO">
     <![CDATA[
	     update t_member
	     set pwd=#{pwd}, name=#{name}, email=#{email}
	     where
	     id=#{id}
      ]]>      
   </update> 
     
   <delete id="deleteMember"  parameterType="String">
	<![CDATA[
	   delete from  t_member
	   where
	   id=#{id}
	]]>      
  </delete>
</mapper>
```

