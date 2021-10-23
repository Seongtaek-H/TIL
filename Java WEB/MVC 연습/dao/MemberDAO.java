package mc.sn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mc.sn.manager.ConnectionManager;
import mc.sn.vo.MemberVO;

public class MemberDAO {
	
	public boolean insertMember(MemberVO vo) {
		boolean flag = false;
		//memberTBL에 삽입하는 코드
		String sql = "insert into memberTBL(member_id,member_pwd,member_name,member_email) "
				+ "values (?,?,?,?);";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionManager.getConnection();
			stmt = con.prepareStatement(sql);
					
			stmt.setString(1, vo.getMemberId());
			stmt.setString(2, vo.getMemberPwd());
			stmt.setString(3, vo.getMemberName());
			stmt.setString(4, vo.getMemberEmail());
			
			int affectedCount = stmt.executeUpdate();
			if(affectedCount>0) {
				System.out.println("삽입 작업이 완료되었습니다.");
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.closeAll(con, stmt, null);
		}
		
		return flag;
	}

	//전체조회
	public ArrayList<MemberVO> selectAll(){
		ArrayList<MemberVO> list = null;
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from memberTBL";
		Statement stmt = null;
		ResultSet rs = null;
		list = new ArrayList<MemberVO>();
		MemberVO vo = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String id = rs.getString(2);
				String pwd = rs.getString(3);
				String name = rs.getString(4);
				String email = rs.getString(5);
				vo = new MemberVO(id,pwd,name,email);
				vo.setJoinDate(rs.getTimestamp(6));
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return list;
	}

	//조건조회
	public MemberVO selectOne(String memberId) {
		System.out.println(memberId);
		MemberVO vo = null;
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from memberTBL where member_id = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString(2);
				String pwd = rs.getString(3);
				String name = rs.getString(4);
				String email = rs.getString(5);
				vo = new MemberVO(id,pwd,name,email);
				vo.setJoinDate(rs.getTimestamp(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public boolean update(MemberVO vo) {
		boolean flag = false;
		String sql = "update memberTBL set member_pwd = ?, member_name = ?, member_email=? where member_id = ?";
		Connection con = ConnectionManager.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, vo.getMemberPwd());
			stmt.setString(2, vo.getMemberName());
			stmt.setString(3, vo.getMemberEmail());
			stmt.setString(4, vo.getMemberId());
			int affectedCount = stmt.executeUpdate();
			if(affectedCount>0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.closeAll(con, stmt, null);
		}
		return flag;
	}
	
	public boolean deleteMember(String userId) {
		boolean flag = false;
		String sql = "delete from memberTBL where member_id = ?";
		Connection con = ConnectionManager.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, userId);
			int affectedCount = stmt.executeUpdate();
			if(affectedCount>0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.closeAll(con, stmt, null);
		}
		
		return flag;
	}

}
