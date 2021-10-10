## 파일 연동하기

> JAVA에서 데이터 파일 연습

* 데이터클래스
* 문제를 풀기위한 데이터 클래스로 필요한 필드, 생성자를 생성하고 getter, setter로 데이터 무결성 유지

```java
public class ScoreData {
	//문제를 풀기 위한 데이터 클래스
	private int sno;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private String accCode;
	private String localCode;
	
	public ScoreData() {
		
	}
	public ScoreData(int sno, int kor, int eng, int math, int total, String accCode, String localCode) {
		this.sno = sno;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.total = total;
		this.accCode = accCode;
		this.localCode = localCode;
	}
	
	public int calcuNo2() {
		return kor+eng;
	}
	public int calcuNo3() {
		return eng+math;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getAccCode() {
		return accCode;
	}
	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}
	public String getLocalCode() {
		return localCode;
	}
	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}
}
```

