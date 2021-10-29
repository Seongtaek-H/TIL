### 서블릿연습-VO클래스

* VO클래스

```java
package mc.sn.vo;

import java.sql.Timestamp;

public class MemberVO {
 // 데이터 클래슬 생성 --> member.sql 참조
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String memberEmail;
	private Timestamp joinDate;
	
	public MemberVO(String id,String pwd,String name,String email) {
		this.memberId = id;
		this.memberPwd = pwd;
		this.memberName = name;
		this.memberEmail = email;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public Timestamp getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName=" + memberName
				+ ", memberEmail=" + memberEmail + "]";
	}	
}
```

