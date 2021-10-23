package mc.sn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mc.sn.dao.MemberDAO;
import mc.sn.service.MemberService;
import mc.sn.vo.MemberVO;

public class MemberController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		String command = req.getParameter("cmd");
		System.out.println(command);
		String url = "./member/result.jsp";
		MemberService service = new MemberService();
		RequestDispatcher rd = null;
		if(command.equals("list")) {
			ArrayList<MemberVO> list = service.readAll();
			req.setAttribute("key", list);
			url = "./member/member_list.jsp";
		} else if(command.equals("create")) {
			//url = url+"?message=";
			String id = req.getParameter("user_id");
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("user_name");
			String email = req.getParameter("email");
			MemberVO vo = new MemberVO(id,pwd,name,email);
			boolean flag = service.createMember(vo);
			if(flag) {
				//url = "/MVCBasic/CmdController?cmd=list";
				url = "./CmdController?cmd=list";	
			} else {
				req.setAttribute("message", "input fail");
			}
		} else if(command.equals("view_input")) {
			url = "./member/member.jsp";
		} else if(command.equals("remove")) {
			//삭제처리
			//url = url +"?message=";
			String userId = req.getParameter("user_id");
			boolean flag = service.removeMember(userId);
			//url 결정
			if(flag) {
				//url = "/MVCBasic/CmdController?cmd=list"; ---> sendRedirect 방식
				url = "./CmdController?cmd=list";			//--> forward 방식
			} else {
				//url = url+"remove fail";	
				req.setAttribute("message", "remove fail");
			}
			
		} else if(command.equals("view_update")) {
			url = "./member/member_update.jsp";
			String memberId = req.getParameter("user_id");
			MemberVO vo = service.searchMember(memberId);
			req.setAttribute("vo", vo);
		} else if(command.equals("update")){
			String id = req.getParameter("user_id");
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("user_name");
			String email = req.getParameter("email");
			MemberVO vo = new MemberVO(id,pwd,name,email);
			
			boolean flag = service.modifyMember(vo);
			if(flag) {
				//수정성공
				url = "./CmdController?cmd=list";
				
			} else {
				//수정실패
				req.setAttribute("message", "update fail");
			}
		}
		rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
		//resp.sendRedirect(url);
	}
		
}
