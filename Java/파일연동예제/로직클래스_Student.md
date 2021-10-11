## 파일 연동하기

> JAVA에서 데이터 파일 연습

* 로직클래스

```java
public class Student {
	//문제 푸는 클래스
    // ScoreData 클래스 타입만을 받는 ArrayList인 list를 필드로 설정
	private ArrayList<ScoreData> list;
	
	public Student(String filePath) {
		//list 구성. 
		list = new ArrayList<ScoreData>();
		// 파일에 접속하기 위해 생성자에 파일의 위치 입력. 
		File file = new File(filePath);
        // 에러발생시 try 미실행되므로 fr, br 변수선언을 try 밖에서 선언함
		FileReader fr = null;
		BufferedReader br = null; 
		try {
            // 파일객체에 접속해서 컨텐츠를 꺼낼 수 있게 준비
			fr = new FileReader(file); // FileNotFoundException
            // 파일의 컨텐츠를 한 줄씩 읽는 BufferedReader 사용. java.io.BufferedReader Import
			br = new BufferedReader(fr);
            // 한 줄씩 읽기 위한 변수 선언
			String line = null;
			while((line=br.readLine())!=null) { // IOException
                // split 메소드로 line을 문자열대로 나눠서 temp 배열에 저장
				String[] temp = line.split(",");
                //나눈 문자열을 각 변수에 저장. .trim() 함수로 공백 제거
				int sno = Integer.parseInt(temp[0].trim());
				int kor = Integer.parseInt(temp[2].trim());
				int eng = Integer.parseInt(temp[3].trim());
				int math = Integer.parseInt(temp[4].trim());
				int total = Integer.parseInt(temp[7].trim());
				String acc = temp[9];
				String local = temp[10];
				list.add(new ScoreData(sno,kor,eng,math,total,acc,local));	
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
		//한라인씩 읽어서 인스턴스 만들고
		//인스턴스를 리스트에 저장
	}
	
	
	public String solveNo1() {
		String result = null;
        // B인 자료만 리스트 새로 구성
		ArrayList<ScoreData> sortedList = new ArrayList<ScoreData>();
		for(ScoreData data : list) {
			if(data.getLocalCode().equals("B")) {
				sortedList.add(data);
			}
		}
		// java.util.Collections 클래스 사용
        // Collections.sort(A, B) : 정렬방법. 
        // A : 내가 정렬하고자 하는 데이터, B : A를 어떤식으로 정렬할지 정의한 클래스
        // B에 대해서는 Comparator 파일에서 설명.
		Collections.sort(sortedList, new ScoreComparator());
		for(int i=0;i<10;i++) {
			ScoreData temp = sortedList.get(i);
			System.out.println(temp.getSno()+": "+temp.calcuNo2());
		}	
		result=String.valueOf(sortedList.get(4).getSno());
		return result;
	}
        
	public String solveNo2() {
		String result = null;   
		int max = 0 ;
		for(ScoreData data : list) {
			if(data.getLocalCode().equals("B")) { // 지역코드가 B이면서
				if(max<data.calcuNo2()) {
					max = data.calcuNo2(); // 국어+영어 점수가 가장 큰 값을 구하고
				}
			}
		}
		result = String.valueOf(max); //리턴값 결정
		return result;
	}
	
	public String solveNo3() {
		String result = null;
		int sum = 0;
		for(ScoreData data : list) {
			if(data.calcuNo3()>=120) {
				int point = 0;
				if(data.getAccCode().equals("A")) {
					point = 5;
				} else if(data.getAccCode().equals("B")){
					point = 15;
				} else{
					point = 20;
				}
				sum = sum + data.getTotal()+point;
			}
		}
		result=String.valueOf(sum);
		return result;
	}
	
	public String solveNo4() {
		String result = null;
		int count = 0;
		for(ScoreData data : list) {
			if(data.getAccCode().equals("A")||data.getAccCode().equals("B")) {
				int point = 15;
				if(data.getLocalCode().equals("A")) {
					point=5;
				} else if(data.getLocalCode().equals("B")) {
					point = 10;
				}
				if(data.getKor()+point>=50) {
					count++;
				}
			}
		}
		result = String.valueOf(count);
		return result;
	}
}
```

