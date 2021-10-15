package project1;

import java.util.ArrayList;

public class BookTest {

	ArrayList<BookDTO> list = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("**********************도서 목록************************");
		// 배열 만들어서 넣기
		// toString 불러와서 출력

		String book1 = "21424-Java Basic   -김하나-Jeen.kr-15000-Java 기본 문법";
		String book2 = "33455-JDBC Pro     -김철수-Jaen.kr-23000- ";
		String book3 = "55355-Servlet/JSP  -박자바-Jaen.kr-41000-Mode12 기반";
		String book4 = "35332-Android App  -홍길동-Jaen.kr-25000-Lighrweight Framework";
		String book5 = "35355-OOAD 분석,설계 -소나무-Jaen.kr-30000- ";
		String[] bookList = {book1, book2, book3, book4, book5};
	
		for(int i=0;i<bookList.length;i++) {
			String[] temp = bookList[i].split("-");
			int isbn = Integer.parseInt(temp[0]);
			String title = temp[1];
			String author = temp[2];
			String publisher = temp[3];
			int price = Integer.parseInt(temp[4]);
			String desc = temp[5];
			
			BookDTO bd = new BookDTO(isbn, title, author, publisher, price, desc);
			System.out.println(bd);
		}
	}
}
