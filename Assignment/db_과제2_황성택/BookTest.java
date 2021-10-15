package project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class BookTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("*********************도서목록**********************");
		BookTest bt = new BookTest();
		Connection con = null;
		try {
			con = bt.makeConnection();
			bt.printAllBooks();
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
	
	public void printAllBooks() throws SQLException, ClassNotFoundException {

		Connection con = this.makeConnection();
		Statement stmt = con.createStatement();
		String sql = "select * from bookTBL";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" |"+ rs.getString(2)+" |"+rs.getString(3)+" |"+rs.getString(4)
			+" |"+ rs.getInt(5)+" |"+rs.getString(6)+" |"+ rs.getString(7));
		}
		rs.close();
		stmt.close();
		con.close();
		
	}
	public void insertStudent() throws ClassNotFoundException, SQLException {
		ArrayList<BookDTO> list = this.makeList();
		String sql = "INSERT INTO bookTBL VALUES(?,?,?,?,?,?,?)";
		Connection con = this.makeConnection();
		PreparedStatement stmt = con.prepareStatement(sql);
		for(int i=0;i<list.size();i++) {
			BookDTO temp = list.get(i);
			stmt.setInt(1, temp.getIsbn());
			stmt.setString(2, temp.getTitle());
			stmt.setString(3, temp.getAuthor());
			stmt.setString(4, temp.getPublisher());
			stmt.setInt(5, temp.getPrice());
			stmt.setString(6, temp.getDesc());
			stmt.setString(7, temp.getPublish_date());
			int affectedCount = stmt.executeUpdate();
			if(affectedCount>0) {
				System.out.println("삽입 작업이 완료되었습니다.");
			}
		}
		stmt.close();
		con.close();
	}
	
	public ArrayList<BookDTO> makeList(){
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		String book1 = "21424-Java Basic-김하나-Jeen.kr-15000-Java 기본 문법- ";
		String book2 = "33455-JDBC Pro-김철수-Jaen.kr-23000- - ";
		String book3 = "55355-Servlet/JSP-박자바-Jaen.kr-41000-Mode12 기반- ";
		String book4 = "35332-Android App-홍길동-Jaen.kr-25000-Lighrweight Framework- ";
		String book5 = "35355-OOAD 분석,설계-소나무-Jaen.kr-30000- - ";
		String[] bookList = {book1, book2, book3, book4, book5};
		
		for(int i=0;i<bookList.length;i++) {
			String[] temp = bookList[i].split("-");
			int isbn = Integer.parseInt(temp[0]);
			String title = temp[1];
			String author = temp[2];
			String publisher = temp[3];
			int price = Integer.parseInt(temp[4]);
			String desc = temp[5];
			String publish_date = temp[6];
			list.add(new BookDTO(isbn,title,author,publisher,price,desc,publish_date));
		}
		System.out.println(list.get(1));
		return list;		
	} 
	
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "HR";
		String pwd = "1234";
		
		Class.forName(driver);
		con=DriverManager.getConnection(url,id,pwd);
		return con;		
	}

}
