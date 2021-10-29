package mc.sn.service;

import java.util.ArrayList;

import mc.sn.dao.MemberDAO;
import mc.sn.vo.MemberVO;
//crtl+shift+O
public class MemberService {
	public boolean createMember(MemberVO vo) {
		boolean flag = false;
		flag = new MemberDAO().insertMember(vo);
		return flag;
	}
	
	//전체조회
	public ArrayList<MemberVO> readAll(){
		ArrayList<MemberVO> list = null;
		list = new MemberDAO().selectAll();
		
		return list;
	}
	
	public MemberVO searchMember(String memberId) {
		MemberVO vo = null;
		vo = new MemberDAO().selectOne(memberId);
		
		return vo;
	}
	
	public boolean modifyMember(MemberVO vo) {
		boolean flag = false;
		flag = new MemberDAO().update(vo);
		return flag;
	}
	public boolean removeMember(String userId) {
		boolean flag = false;
		flag = new MemberDAO().deleteMember(userId);
		return flag;
	}
	
	
	
}
