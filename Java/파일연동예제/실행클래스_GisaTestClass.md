## 파일 연동하기

> JAVA에서 데이터 파일 연습

* 실행클래스

```java
public class GisaTestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GisaTestClass gisa = new GisaTestClass();
		gisa.startTest();
	}
	
	public void startTest() {
		// Student 클래스의 생성자를 불러와서 파일 경로를 클래스 변수로 사용. Student 클래스 타입의 변수 student에 저장
		Student student = new Student("./data/Abc1115.csv");
        // 변수 ans1 에 stuendt의 solveNo1 메소드 저장
		String ans1 = student.solveNo1();
        // 본 클래스의 메소드 saveFile을 ans1, 1 이라는 변수를 통해 실행
		this.saveFile(ans1,1);
		String ans2 = student.solveNo2();
		this.saveFile(ans2,2);
		String ans3 = student.solveNo3();
		this.saveFile(ans3,3);
		String ans4 = student.solveNo4();
		this.saveFile(ans4,4);
	}
	
	private void saveFile(String ans, int no) {
		// 정답파일 생성
		String filePath = "./data/Ans"+no+".txt";
		File file = new File(filePath);
		
		FileWriter fw = null; 

		PrintWriter pw = null;
	
		try {
            // 파일 연결. 
			fw = new FileWriter(file); // FileNotFoundException
            // 라인 한 줄씩 저장
			pw = new  PrintWriter(fw);
			pw.println(ans);
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			try {
				pw.close();
				fw.close();
			} catch(IOException ie) {
				ie.printStackTrace();
			}
		}
	
		System.out.println(no+"번째 정답을 ans"+no+".txt 파일에 저장했습니다.");
	}

}
```

