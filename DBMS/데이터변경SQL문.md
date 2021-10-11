## 데이터 변경 SQL문

### INSERT 

> 테이블에 데이터를 삽입

```sql
INSERT INTO 테이블[{열1, 열2, ...}] VALUES (값1, 값2, ....)
```

* 테이블열 생략할 경우  VALUES 값에 테이블의 모든 열에 대해서 순서 및 갯수가 일치해야함

```SQL
CREATE TBL testTBL1 (id NUMBER(4), userName NCHAR(3), age NUMBER(2));
INSERT INTO testTBL1 VALUES (1, '홍길동', 25);
```

* 모든 값을 입력하고 싶지 않다면 테이블 이름 뒤에 입력할 열의 목록을 나열. 생략한 열에는 NULL 값이 들어감

```SQL
INSERT INTO testTBL1(id, userName) VALUES (2, '고길동');
```



### UPDATE

> 기존에 입력되어 있는 값을 변경

```SQL
UPDATE 테이블이름
	SET 열1 = 값1, 열2 = 값2, ...
	WHERE 조건 ;
```

* WHERE  생략할 경우 선택한 열의 모든 값이 변경됨



### DELETE FORM

> 데이터의 삭제

```SQL
DELETE FROM 테이블이름 WHERE 조건 ;
```

* WHERE 생략되면 전체 데이터를 삭제

```SQL
DELETE FROM testTBL4 WHERE FirstName = 'Peter'; // 'Peter'라는 이름이 3개 있을 경우 3개의 행 삭제
RALLBACK ; // 삭제된 'Peter'를 되돌림
DELETE FROM testTBL4 WHERE FirstName = 'Peter' AND ROWNUM <= 2; // 'Peter' 중 상위 2건만 삭제
```



