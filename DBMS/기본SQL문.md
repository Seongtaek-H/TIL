## 기본쿼리문

### Select문

`기본형`

```
SELECT 열 이름
FROM 테이블 이름
WHERE 조건
```

* 열 이름에 `*` 삽입시 모든 열을 불러온다.



#### WHERE절

> 특정한 조건의 데이터만 조회

`관계 연산자 사용`

* 조건 연산자 (=, <, >, <=, >=, <>, != )
* 관계 연산자 (NOT, AND, OR 등)

`BETWEEN...AND와 IN(), LIKE`

* BEETWEEN ... AND : 범위 설정

  ```sql
  // height 가 180 ~ 183 인 사람 조회
  SELECT userName, height FORM userTBL WHERE height BETWEEN 180 AND 183;
  ```

* IN : 연속된 값이 아닌 이산적인 값 설정 

  ```sql
  SELECT userName, addr FORM userTBL WHERE addr in ('경남','전남','경북');
  ```

* LIKE : 문자열의 내용 검색

  ```sql
  SELECT userName, height FORM userTBL WHERE userName LIKE '김%'; // 김으로 시작하는 문자열 검색
  ```

  

`ANY/ALL`

* ANY : 여러 개의 결과 중 한가지만 만족 ex) 173이상과 170이상인 조건일 때 170이상인 값이 들어와야함
* ALL : 여러 개의 결과 모두 만족 ex) 173이상과 170이상인 조건일 때 173이상인 값이 들어오면 됨.

`ORDER BY : 원하는 순서대로 정렬하여 출력`

* 기본적으로 오름차순으로 정렬(ASC : 생략가능)

* 내림차순으로 정렬하기 위해서는 열 이름 뒤 DESC 

  ```
  SELECT userName, mDATE FROM userTBL ORDER BY DESC;
  ```



#### GROUP BY / HAVING 절

`GROUP BY절 : 데이터를 그룹화`

````
SELECT userID, `SUM(amount)` FROM buyTBL `GROUP BY` userID;
````

* buyTBL 테이블의 userID 별 총합을 구함.

* 집계 함수 : GROUP BY절과 함께 쓰이며 데이터를 그룹화해주는 기능

  ```
  SUM() : 총합을 구함
  AVG() : 평균을 구함
  MIN() : 최소값을 구함
  MAX() : 최대값을 구함
  COUNT : 행의 갯수를 구함
  COUNT(DISTINCT) : 행의 갯수를 센다 (중복은 1개만 인정)
  STDEV : 표준편차를 구함
  VARIANCE() : 분산을 구함
  ```

`HAVING절 : 조건을 제한하는 것이지만, WHERE과 다르게 집계함수에 대해서 조건을 제한함. 반드시 GROUP BY절 다음에 나와야 한다.`

```
SELECT userID AS "사용자", SUM(price*amount) AS "총 구매액"
	FROM buyTBL
	GROUP BY userID
	HAVING SUM(price*amount)>1000;
```



#### 서브쿼리

> 쿼리문 안에 또 쿼리문이 들어있는 것.

