## JAVA와 DBMS 연동

> JAVA와 DBMS (Oracle xe) 연동 연습

* 가장 먼저 오라클 설치 디렉토리에서 ojdbc6.jar 파일 복사하여 임의의 폴더에 저장
* 파일 경로 : \oracle\product\11.2.0\server\jdbc\lib

* 연동할 JAVA 패키지 우클릭 -> Build path -> Add External Archives -> ojdbc6.jar 열기 -> Referenced Libraries 내 ojdbc 확인

  

#### 데이터베이스와 연동하여 결과값 리턴하는 메소드 만들기

```java
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
        // Connection 클래스 인스턴스 생성. java.sql 하위 클래스로 Import
		Connection con = null;
        // OracleDriver 클래스. 데이터베이스와 연동 시 도움을 주는 클래스
		String driver = "oracle.jdbc.OracleDriver";
        // JDBC 라이브러리 : 오라클로 연결 : thin이라는 프로토콜 @ IP(localhost : 내부IP주소) : 포트번호 : 리스너
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
        // 우리가 사용할 DBMS 스키마와 패스워드
		String id = "HR";
		String pwd = "1234";
		
        //driver 불러오기
		Class.forName(driver); // ClassNotFountException 발생
        //DriverManager : driver를 관리해주는 클래스로 java.sql 하위 클래스 
		con = DriverManager.getConnection(url,id,pwd); // SQLException 발생
        // 연결된 정보 return
		return con;
	}
```

> Class.forName : Returns the `Class` object associated with the class or interface with the given string name.
>
> 해당 메소드에서는 OracleDriver Class의 인스턴스를 불러와서 메모리에 저장한다고 이해하면 될 것 같다.



#### 메인 메소드에서 makeConnection 메소드 호출

```java
	public static void main(String[] args) {
		// 객체 생성
		DatabaseTest dt = new DatabaseTest();
        // con 호출시 try 내부에 있으면 실행되지 않을 수 있어 바깥으로 빼줌.
        Connection con = null;
		try {
            // makeConnection 호출
            con = dt.makeConnection(); // throw 한 메소드를 호출했기 때문에 try - catch 블록으로 예외처리
            if(con!=null){
                System.out.println("connected");
            } else {
                System.out.println("not connected");
            }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace()
            }
        }
		
	}
```

> con.close(); 에 SQLException 이 발생할 수 있어 try-catch 로 예외처리했는데, 왜 close에 Exeption이 발생하는지는 잘모르겠다..
