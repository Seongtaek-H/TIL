## 스프링 - DI

> 자바 웹 애플리케이션 개발을 위한 프레임워크로, 기존 프레임워크보다 가벼운 경량 프레임워크
>
> 여러 가지 빈(클래스 객체)을 스프링이 권한을 가지고 직접 관리하므로 경량 컨테이너라고도 부름

`주요 특징`

* 경량 컨테이너
* 제어 역행(IoC)
* 의존성 주입(DI)
* 관점 지향(AOP)



### 의존성 주입(DI)

> 클래스와 클래스의 관계를 개발자가 아니라 컨테이너가 연관 관계를 직접 규정(약한 결합)
>
> XML이나 애너테이션을 이용해 객체를 주입하여 객체들의 의존 관계 형성 -> 스프링 프레임워크가 제어(=제어 역행,IoC)
>
> 스프링에서는 DI로 IoC 기능을 구현
>
> 각 클래스 객체(bean)을 외부 xml 혹은 애너테이션을 이용해 컨테이너 실행시 주입 



`<bean> 태그의 속성`

| 속성 이름       | 설명                                                    |
| --------------- | ------------------------------------------------------- |
| id              | bean 객체의 고유 이름으로, bean id를 이용해 bean에 접근 |
| name            | 객체의 별칭                                             |
| class           | 생성할 클래스명, 패키지 이름까지 입력                   |
| constructor-arg | 생성자를 이용해 값을 주입할 때 사용                     |
| property        | setter를 이용해 값을 주입할 때 사용                     |



**`setter를 이용한 DI`**

* xml 파일에서 스프링 실행 시 생성할 bean 설정

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN"
                             "http://www.springframework.org/dtd/spring-beans-2.0.dtd">
<beans>
    <!-- //PersonServiceImpl 객체(bean) 생성 후 id를 PersonService 로 지정 -->
   <bean id="personService" class="com.spring.ex01.PersonServiceImpl"> 
       <!-- //PersonServiceImpl 객체(bean) 속성 name 값을 value 태그로 '홍길동'으로 초기화 -->
      <property name="name"> 
         <value>홍길동</value>
      </property>
   </bean>
</beans>
```

* PersonService 인터페이스 설정

```java
public interface PersonService {
	public void sayHello();
}
```

* 구현클래스 PersonServiceImpl 

```java
package com.spring.ex01;

public class PersonServiceImpl implements PersonService {
	private String name;
	private int age;

    // value 값 setter 이용하여 설정
	public void setName(String name) { 
		this.name = name;
	}

    // 오버라이드로 sayHello 메서드 변경
	@Override
	public void sayHello() {
		System.out.println("이름: " + name);
		System.out.println("나이: " + age);
	}
}
```

* 실행클래스 PersonTest 

```java
package com.spring.ex01;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class PersonTest {

	public static void main(String[] args) {
        // person.xml을 읽어 bean 생성
        // new FileSystemResource("person.xml") => person.xml 의 인스턴스화
        // new XmlBeanFactory(new FileSystemResource("person.xml")) => 인스턴스를 다 분석하여 변수 factory에 저장
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("person.xml"));
        // 아이디가 personService 인 빈 가져옴
		PersonService person = (PersonService) factory.getBean("personService");
		// PersonService person = new PersonServiceImpl();  -> New 연산자로 객체 생성할 필요없음
        // 생성된 빈으로 name 값 출력
		person.sayHello();
	}
    
}
```



**`생성자를 이용한 DI`**

* person.xml에서 <constuctor-arg> 태그를 이용해 생성자 호출 시 생성자 매개 변수로 <value> 태그 값 전달하여 속성 초기화

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN"
                             "http://www.springframework.org/dtd/spring-beans-2.0.dtd">
<beans>
   <bean id="personService1" class="com.spring.ex02.PersonServiceImpl">
       <!-- //인자가 1개인 id=personService1인 bean 생성. 생성자 호출시 속성값 '이순신' 으로 초기화 -->
        <constructor-arg   value="이순신" />
   </bean>
   
   <bean id="personService2" class="com.spring.ex02.PersonServiceImpl">
       <!-- //인자가 2개인 id=personService2인 bean 생성. 생성자 호출시 속성값 '손흥민','23' 으로 초기화 -->
       <constructor-arg   value="손흥민"  />
       <constructor-arg   value="23"  />
   </bean>
</beans>
```

* PersonService 인터페이스 설정

```java
package com.spring.ex02;

public interface PersonService {
	public void sayHello();
}
```

* PersonServiceImpl 구현클래스에서 인자가 1개인 생성자, 2개인 생성자 구현

```java
package com.spring.ex02;

public class PersonServiceImpl implements PersonService {
	private String name;
	private int age;

	public PersonServiceImpl(String name) { // person.xml에서 인자가 1개인 생성자 설정시 사용
		this.name = name;
	}

	public PersonServiceImpl(String name, int age) { // person.xml에서 인자가 2개인 생성자 설정시 사용
		this.name = name;
		this.age = age;
	}

	@Override
	public void sayHello() {
		System.out.println("이름: " + name);
		System.out.println("나이: " + age + "살"); 
	}
}
```

* 실행 클래스 personTest2

```java
package com.spring.ex02;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class PersonTest2 {
	public static void main(String[] args) {
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("person.xml"));
		PersonService person1 = (PersonService) factory.getBean("personService1");
		person1.sayHello();
        // personService1 은 인자가 1개라 이름 : 이순신, 나이: 0살 로 출력됨
		System.out.println();
		// 이름 : 손흥민, 나이 : 23살 로 출력
		PersonService person2 = (PersonService) factory.getBean("personService2");
		person2.sayHello();
	}
}
```



`회원 기능 DI 실습`

* xml

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN"
                             "http://www.springframework.org/dtd/spring-beans-2.0.dtd">
<beans>
    <!-- memberService 객체 생성 -->
	<bean id="memberService" class="com.spring.ex03.MemberServiceImpl">
        <!-- memberDAO bean 주입, 주입되는 데이터가 참조형일 경우 ref속성을 이용해 주입 -->
	   <property name="memberDAO" ref="memberDAO" /> 
	</bean>
    <!-- memberDAO 객체 생성 -->
	<bean id="memberDAO" class="com.spring.ex03.MemberDAOImpl" /> 
</beans>
```

* MemberServiceImpl : 주입되는 bean을 받는 클래스

```java
package com.spring.ex03;

public class MemberServiceImpl implements MemberService {
    // 주입되는 bean을 저장할 MemberDAO 타입의 속성 선언
	private MemberDAO memberDAO;
	// 설정파일에서 memberDAO bean 생성 후 setter로 속성 memberDAO에 주입
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	// 주입된 bean으로 listMembers() 메서드 호출
	@Override
	public void listMembers() {
		memberDAO.listMembers();
	}
}
```

* MemberDAO : MemberServiceImpl로 주입되는 클래스

``` java
package com.spring.ex03;

public class MemberDAOImpl implements MemberDAO {
	@Override
	public void listMembers() {
		System.out.println("listMembers 메서드 호출");
		System.out.println("회원정보를 조회합니다.");
	}
}
```

* MemberTest1 : 실행클래스

```java
package com.spring.ex03;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class MemberTest1 {
	public static void main(String[] args) {
        // member.xml에서 설정한 bean 생성 후 주입
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("member.xml"));
        // id가 memberService 인 bean 가져옴
		MemberService service = (MemberService) factory.getBean("memberService");
		service.listMembers();
	}
}
```

