## CASE문



### IF-ELSE

> 조건에 따라 분기. 한 문장 이상이 처리되어야 할 때는 BEGIN ... END 와 함께 묶어줘야 한다.

```sql
BEGIN
	var1 := 값;
	IF<부울 표현식> THEN
		사용할 명령들1...
	ELSE
		사용할 명령들2...
	END IF;
END
```



### CASE

> IF-ELSEIF-ELSE 문 간략화

```sql
BEGIN
	var1 := 값;
	CASE
		WHEN var1 >=90 THEN
			credit := 'A';
		WHEN var1 >=80 THEN
			credit := 'B';
		WHEN var1 >=70 THEN
			credit := 'C';
		WHEN var1 >=60 THEN
			credit := 'D';
		ELSE 
			pNumber := 'F';
	END CASE;
END ; 
```

* 조건이 맞는 WHEN 이 여러 개더라도 먼저 조건이 만족하는 WHEN이 처리되고 CASE 종료
* SELECT문에서 많이 사용됨.
