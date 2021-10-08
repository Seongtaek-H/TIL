# 컬렉션

>객체를 수집해서 저장

| 인터페이스 분류 | 특징                                               | 구현클래스                              |
| :-------------: | -------------------------------------------------- | --------------------------------------- |
|      List       | - 순서를 유지하고 저장<br />- 중복 저장 가능       | ArrayList, Vector, LinkdList            |
|       Set       | - 순서를 유지하지 않고 저장<br />- 중복 저장 불가  | Hashset, TreeSet                        |
|       Map       | - 키와 값의 쌍으로 저장<br />- 키는 중복 저장 불가 | HashMap, Hashtable, TreeMap, Properties |



## List 컬렉션

> 객체를 저장하면 자동 인덱스가 부여되고, 인덱스로 객체를 검색, 삭제할 수 있음

```java
List<String> list = ...;
list.add("홍길동"); // 맨끝에 객체 추가
list.add(1,"고길동"); // 지정된 인덱스에 객체 삽입
String str = list.get(1); // 인덱스로 객체 찾기
list.remove(0); // 인덱스로 객체 삭제
list.remove("신용권"); //객체 삭제
list.size // => 저장된 총 객체 수
list.get(i); // i번째 인덱스에 저장된 객체 얻기
```



### ArrayList

> List 인터페이스의 구현 클래스로, ArrayList에 객체를 추가하면 객체가 인덱스로 관리됨
>
> 저장용량(capacity)를 초과한 객체들이 들어오면 자동적으로 저장용량(capacity)이 늘어남



`Arraylist 생성` : 저장할 객체 타입을 타입 파라미터로 표기하고 기본 생성자 호출 

```java
List<String> list = new ArrayList<String>(); // 초기 용량 10으로 설정
List<String> list = new ArrayList<String>(30); // String 객체 30개 저장할 수 있는 용량
```

* ArrayList에 객체를 추가하면 인덱스 0부터 차례대로 저장
* 특정 인덱스의 객체를 제거하면 바로 뒤 인덱스부터 마지막 인덱스까지 모두 앞으로 1씩 당겨짐
* 특정 인덱스에 객체를 삽입하면 해당 인덱스부터 마지막 인덱스까지 모두 1씩 밀려남
* 따라서, 빈번한 객체 삭제와 삽입이 일어나는 곳에는 ArrayList가 부적합



### Vector

> ArrayList와 동일한 내부 구조. 동기화된 메소드로 구성되어 있기 때문에 멀티스레드가 동시에 메소드들을 실행할 수 없다.
>
> 하나의 스레드가 실행을 완료해야만 다른 스레드를 실행할 수 있기때문에 멀티 스레드 환경에서 안전하게 객체를 추가, 삭제 가능

```java
List<E> list = new Vector<E>();
```



### LinkedList

> 인접 참조를 링크해서 체인처럼 관리. 특정 인덱스의 객체를 삽입/제거 하여도 앞뒤 링크만 변경되고 나머지 링크는 변경되지 않음

```java
List<E> list = new LinkedList<E>();
```

* 빈번한 객체 삭제와 삽입이 일어나는 곳에서 ArrayList보다 좋은 성능 발휘
* 순차적으로 추가/삭제하는 경우는 ArrayList가 더 빠르지만, 중간에 추가 또는 삭제할 경우는 LinkedList가 더 빠름
* 검색 속도는 느림



















