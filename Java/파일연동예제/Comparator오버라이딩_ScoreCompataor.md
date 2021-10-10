## 파일 연동하기

> JAVA에서 데이터 파일 연습

* ScoreData를 Comparator하는 클래스
* New class 생성 -> interfaces Add -> Comparator 선택 후 `java.util.Comparator<ScoreData>`
* 인터페이스는 한 클래스에서 복수로 구현할 수 있음

```java
import java.util.Comparator;

public class ScoreComparator implements Comparator<ScoreData> {

	@Override
	public int compare(ScoreData o1, ScoreData o2) {
		// TODO Auto-generated method stub
		int result = 0;
		result = o2.calcuNo2() - o1.calcuNo2();
		return result;
	}

}
```

