package project2;

public class BookDTO {
	private int isbn;
	private String title;
	private String author;
	private String publisher;
	private int price;
	private String desc;
	private String publish_date;
	
	public BookDTO() {
		
	}
	
	public BookDTO(int isbn, String title, String author, String publisher, int price, String desc,String publish_date) {
		this.isbn = isbn;
		this.title=title;
		this.author=author;
		this.publisher=publisher;
		this.price=price;
		this.desc=desc;
		this.publish_date=publish_date;
	}

	public String getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return this.isbn+","+this.title+","+this.author+","+this.publisher+","+this.price+","+this.desc;
//	}
}
