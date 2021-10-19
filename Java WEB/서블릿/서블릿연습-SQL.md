### 서블릿연습-SQL

> src -> sql 폴더 생성 후 파일 만들기

```sql
use kdt13;

create table memberTBL(
	member_seq integer auto_increment primary key,
	member_id varchar(10) not null,
	member_pwd varchar(10) not null,
	member_name varchar(10) not null,
	member_email varchar(30) not null,
	join_date timestamp default now()
) default character set utf8 collate utf8_general_ci;
```

