## JAVA와 DBMS 연동

> JAVA와 DBMS (Oracle xe) 연동 연습

```java
package mc.sn.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//makeConnection 호출
		DatabaseTest dt = new DatabaseTest();
		try {
			dt.insertDataFromFile();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertDataFromFile() throws ClassNotFoundException, SQLException {
		// 1. 파일에서 한라인 읽어서 데이터베이스에 삽입 --> 반복, 좋지 않은 방법
		
		// 2. 파일에서 모든 라인을 읽어서 정리하고 한번에 데이터베이스 삽입 --> 좋은 방법
		// 2-1. 하나의 라인을 어떤 방법으로 데이터베이스에 삽입 --> 인스턴스로 만들어서 처리하는 방법
		// 1000개의 데이터 인스턴스를 만들어서 처리한다.
		ArrayList<ScoreData> list = this.makeList();
		String sql = "INSERT INTO studentTBL VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = this.makeConnection();
		PreparedStatement stmt = con.prepareStatement(sql);
		for(int i=0;i<list.size();i++) {
		// 3. 데이터베이스에 삽입을 어떻게 할 것인가?
			ScoreData temp = list.get(i);
			// ?자리에 알맞은 데이터 셋팅하는 작업 --> 11개 작업을 해야함.
			stmt.setInt(1, temp.getSno());
			stmt.setString(2, temp.getEmail());
			stmt.setInt(3, temp.getKor());
			stmt.setInt(4, temp.getEng());
			stmt.setInt(5, temp.getMath());
			stmt.setInt(6, temp.getSci());
			stmt.setInt(7, temp.getHist());
			stmt.setInt(8, temp.getTotal());
			stmt.setString(9, temp.getMgrCode());
			stmt.setString(10, temp.getAccCode());
			stmt.setString(11, temp.getLocalCode());
			
			int affectedCount = stmt.executeUpdate();
			if(affectedCount>0) {
				System.out.println("삽입 작업이 완료되었습니다.");
			}
		}
		stmt.close();
		con.close();
	}
	
	private ArrayList<ScoreData> makeList(){
		//파일에 접속해서 한라인 씩 읽어서 인스턴스 만드는 작업
		ArrayList<ScoreData> list = new ArrayList<ScoreData>();
		File file = new File("./data/Abc1115.csv");
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine())!=null) {
				String[] temp = line.split(",");
				int sno = Integer.parseInt(temp[0].trim());
				String email = temp[1];
				int kor = Integer.parseInt(temp[2].trim());
				int eng = Integer.parseInt(temp[3].trim());
				int math = Integer.parseInt(temp[4].trim());
				int sci = Integer.parseInt(temp[5].trim());
				int hist = Integer.parseInt(temp[6].trim());
				int total = Integer.parseInt(temp[7].trim());
				String mgr = temp[8];
				String acc = temp[9];
				String local = temp[10];
				list.add(new ScoreData(sno,email,kor,eng,math,sci,hist,total,mgr,acc,local));	
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return list;
	}
	
 	public void testConnection() {
		Connection con = null;
		try {
			con = this.makeConnection();
			if(con!=null) {
				System.out.println("connected");
			} else {
				System.out.println("not connected");
			}
	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
 	
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "HR";
		String pwd = "1234";
		
		Class.forName(driver);
		con = DriverManager.getConnection(url,id,pwd);
		return con;
	}
	
	public void insertData() throws ClassNotFoundException, SQLException {
		//StudentTBL에 Abc1115.csv파일안의 데이터를 삽입
		String sql = "INSERT INTO studentTBL "
				+ "VALUES(990001,'addx', 17, 29, 16, 49, 43,154,'C','A','C')";
		Connection con = this.makeConnection();
		Statement stmt = con.createStatement();
		int affectedCount = stmt.executeUpdate(sql);
		if(affectedCount>0) {
			System.out.println("삽입 작업이 완료되었습니다.");
		}
		stmt.close();
		con.close();
	}
	
	public void updateData() throws ClassNotFoundException, SQLException {
		//addx인 것을 mult로 수정하시오. 최대크기가 4자 이기때문에 mult로 변경합니다.
		String sql = "UPDATE studentTBL set email='mult' WHERE stdno=990001";
		Connection con = this.makeConnection();
		Statement stmt = con.createStatement();
		int affectedCount = stmt.executeUpdate(sql);
		if(affectedCount>0) {
			System.out.println("수정 작업이 완료되었습니다.");
		}
		stmt.close();
		con.close();
	}
	
	public void deleteData() throws ClassNotFoundException, SQLException {
		//stdno가 990001인 데이터를 삭제하시오
		String sql = "DELETE FROM studentTBL WHERE stdno=990001";
		Connection con = this.makeConnection();
		Statement stmt = con.createStatement();
		int affectedCount = stmt.executeUpdate(sql);
		if(affectedCount>0) {
			System.out.println("삭제 작업이 완료되었습니다.");
		}
		stmt.close();
		con.close();
	}
}
```

