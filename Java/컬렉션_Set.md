## Set 컬렉션

> 저장 순서가 유지되지 않음. 객체 중복 저장이 불가하고, 하나의 null만 저장할 수 있다.

`Set 컬렉션에서 공통적으로 사용 가능한 메소드`

* boolean add(E e) : 주어진 객체를 저장. 객체가 성공적으로 저장되면 true, 중복 객체면 false 리턴
* boolean contains(Object o) : 주어진 객체가 저장되어 있는지 여부
* boolean isEmpty() : 컬렉션이 비어 있는지 조사
* iterator<E> iterator() : 저장된 객체를 한 번씩 가져오는 반복자 리턴
* int size() : 저장되어 있는 전체 객체 수 리턴
* void clear() : 저장된 모든 객체를 삭제
* boolean remove(Object o) : 주어진 객체를 삭제

```java
Set<String> Set = ...;
Set.add("홍길동"); // 객체 추가
Set.add("고길동");
Set.remove("홍길동"); // 객체 삭제
```

`Iterator(반복자)` : 전체 객체를 대상으로 한번씩 반복해서 가져옴

```java
Set<String> set = ...;
Iterator<String> iterartor = set.iterator();
```

`Iterator 인터페이스에 선언된 메소드`

* boolean hasNext() : 가져올 객체가 있으면 true, 없으면 false를 리턴
* E next() : 컬렉션에서 하나의 객체를 가져옴
* void remove() : Set 컬렉션에서 객체를 제거한다.

```java
Set<String> Set = ...; //HashSet, TreeSet
Iterator<String> iterator = set.iterator();
while(iterator.hasNext()){ // 저장된 객체 수만큼 루핑
    // String 하나를 가져온다
    String str = iterator.next();
}
```

```java
Set<String> set = ...;
for(String str : set){ // 저장된 객체 수만큼 루핑
}
```



### HashSet

> 객체들을 순서 없이 저장하고 동일한 객체는 중복 저장하지 않는다. 여기서 동일한 대상이란 hashCode() 리턴값이 같고 equals() 리턴값이 같은 객체

```java
Set<E> set = new HashSet<E>();
Set<String> set = new HashSet<String>();
```

