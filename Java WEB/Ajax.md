## Ajax

> 페이지 이동 없이 데이터 처리를 하는 자바스크립트를 사용한 비동기 통신
>
> 요청 페이지의 결과를 서버에서 처리한 후 다시 XML로 원래 요청 페이지로 전송

* 제이쿼리를 이용한 Ajax - 자바스크립트 파일

```javascript
	$(document).ready(function(){
		$('#validate').click(function(){
			alert('jquery event');
			var id = $('#user_id').val();
			var pwd = $('#user_pwd').val();
			alert(id+":"+pwd);
			//Ajax연결 
			$.ajax({
				data :  {user_id : id, user_pwd : pwd }, // 서버에 요청할 때 보낼 매개 변수
				type : "post", // 통신타입 설정(get/post)
				dataType: "text", // 응답 받을 데이터 타입 설정
				url : '/LoginMVC/LoginController?cmd=login_check2', // 요청할 url
				success : function(data, textStatus){ // 요청 및 응답에 성공했을 때 처리되는 구문
					alert(data+"님 반갑습니다.\n"+textStatus);
				},
				error : function(data, textStatus){ // 요청 및 응답에 실패했을 때 처리 구문
					$('#message').text('error');
				},
				complete : function(data,textStatus){ // 모든 작업을 마친 후 처리 구문
				}
			});
			return flag;
		})
```

* 컨트롤러

```java
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("Controller");
		String command = request.getParameter("cmd");
		String url = "./login/result.jsp";
		LoginService service = new LoginService();
		boolean isAjax = false; // Ajax에 사용할 변수
		if(command.equals("login_check")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			boolean flag = service.checkLogin(id, pwd);
			if(flag) {
				//사용자가 있으므로 인증성공-->list로 이동
				url = "./member/member_list.jsp";
			}
		} else if(command.equals("login_check2")) {
			
			String id = request.getParameter("user_id");
			String pwd = request.getParameter("user_pwd");
			System.out.println(id+" check2 "+pwd);
			boolean flag = true;//service.checkLogin(id, pwd);
			//전체를 구현하지 않고 ajax를 테스트 함으로 flag가 참인 것으로 고정하고 테스트
			if(flag) {
				//사용자가 있으므로 인증성공-->list로 이동
				isAjax = true;
				response.getWriter().print(id);
			}
		}
		if(!isAjax) { // Ajax => 페이지 이동 없음. Ajax 가 false 일 경우 포워드하여 페이지 이동
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}
```

