## 파일 연동하기

> JAVA에서 데이터 파일 연습

* Collection.sort(A, B) 에서 B에 해당하는 클래스

* Comparable<T> 인터페이스 사용
* New class 생성 -> interfaces Add -> Comparator 선택 후 `java.util.Comparator<ScoreData>`

```java
import java.util.Comparator;

public class ScoreComparator implements Comparator<ScoreData> {

	@Override
	public int compare(ScoreData o1, ScoreData o2) {
		// TODO Auto-generated method stub
		int result = 0;
        // o2.calcuNo2() - o1.calcuNo2() => 오름차순
        // o1.calcuNo2() - o2.calcuNo2() => 내림차순
		result = o2.calcuNo2() - o1.calcuNo2();
		return result;
	}
}
```

```java
int compare(T o1, T o2)
Compares its two arguments for order. Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
```

* 
* 인터페이스는 한 클래스에서 복수로 적용할 수 있음

