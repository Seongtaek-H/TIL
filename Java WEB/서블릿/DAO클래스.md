### 서블릿연습-DAO클래스

* DAO 클래스

```java
package mc.sn.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mc.sn.vo.MemberVO;

public class MemberService {
	public boolean createMember(MemberVO vo) {
		boolean flag = false;
		//memberTBL에 삽입하는 코드
		String sql = "insert into memberTBL(member_id,member_pwd,member_name,member_email) "
				+ "values (?,?,?,?);";

		try {
			Connection con = this.makeConnection();
			PreparedStatement stmt = con.prepareStatement(sql);	
			stmt.setString(1, vo.getMemberId());
			stmt.setString(2, vo.getMemberPwd());
			stmt.setString(3, vo.getMemberName());
			stmt.setString(4, vo.getMemberEmail());
			
			int affectedCount = stmt.executeUpdate();
			if(affectedCount>0) {
				System.out.println("삽입 작업이 완료되었습니다.");
				flag = true;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/kdt13";
		String id = "root";
		String pwd = "1234";
		
		Class.forName(driver);
		con = DriverManager.getConnection(url,id,pwd);
		return con;
	}
}
```

