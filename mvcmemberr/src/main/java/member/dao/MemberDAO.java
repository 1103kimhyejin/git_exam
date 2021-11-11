package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;

public class MemberDAO {
	private Connection conn = null;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private DataSource ds;
	private static MemberDAO instance = null;

	public static MemberDAO getInstance() {
		if (instance == null) {
			synchronized (MemberDAO.class) {
				instance = new MemberDAO();// 생성
			}
		}

		return instance;
	}

	public MemberDAO() {
		try {
			Context ctx = new InitialContext(); //커넥션풀때문에
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");//커넥션풀에있는것 몽땅 꺼내온다 
			//(DataSource)로 캐스팅 (오브젝트로가져오니까)
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	public void write(MemberDTO memberDTO) {
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);// 생성
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getId());
			pstmt.setString(3, memberDTO.getPwd());
			pstmt.setString(4, memberDTO.getGender());
			pstmt.setString(5, memberDTO.getEmail1());
			pstmt.setString(6, memberDTO.getEmail2());
			pstmt.setString(7, memberDTO.getTel1());
			pstmt.setString(8, memberDTO.getTel2());
			pstmt.setString(9, memberDTO.getTel3());
			pstmt.setString(10, memberDTO.getZipcode());
			pstmt.setString(11, memberDTO.getAddr1());
			pstmt.setString(12, memberDTO.getAddr2());

			pstmt.executeUpdate();// 실행

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public MemberDTO login(String id, String pwd) {
		MemberDTO memberDTO=null;
		String sql = "select * from member where id=? and pwd=?";
	

		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);// 생성
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				//일부만 쓰지만 다 가져온다
				memberDTO = new MemberDTO();
				memberDTO.setName(rs.getString("name"));
				memberDTO.setId(rs.getString("id"));
				memberDTO.setGender(rs.getString("gender"));
				memberDTO.setEmail1(rs.getString("email1"));
				memberDTO.setEmail2(rs.getString("email2"));
				memberDTO.setTel1(rs.getString("tel1"));
				memberDTO.setTel2(rs.getString("tel2"));
				memberDTO.setTel3(rs.getString("tel3"));
				memberDTO.setZipcode(rs.getString("zipcode"));
				memberDTO.setAddr1(rs.getString("addr1"));
				memberDTO.setAddr2(rs.getString("addr2"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return memberDTO;
	}

	public boolean isCheckedId(String id) {
		boolean exist = false;
		String sql = "select * from member where id=?";
		
		
		try {
			conn = ds.getConnection();

			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) exist =true;
			
			//파이널추가하기 파이널없으먀ㅕㄴ 중복체크많이하면 죽어버린다!?
		} catch (SQLException e) {
			e.printStackTrace();
		
	} finally {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


		return exist;
	}
	public List<ZipcodeDTO> getZipcoeList(String sido, String sigungu, String roadname){
		List<ZipcodeDTO> list = new ArrayList<ZipcodeDTO>();
		String sql="select * from newzipcode where sido like ? and sigungu like ? and roadname like ?";
		try {
			conn = ds.getConnection();
			pstmt =conn.prepareStatement(sql);//생성
			pstmt.setString(1, "%"+sido+"%");
			pstmt.setString(2, "%"+sigungu+"%");
			pstmt.setString(3, "%"+roadname+"%");
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
			      
			          ZipcodeDTO zipcodeDTO = new ZipcodeDTO();
			          zipcodeDTO.setZipcode(rs.getString("zipcode"));
			          zipcodeDTO.setSido(rs.getString("sido"));
			          zipcodeDTO.setSigungu(rs.getString("sigungu"));
			          zipcodeDTO.setYubmyundong(rs.getString("yubmyundong"));
			          zipcodeDTO.setRi(rs.getString("ri"));
			          zipcodeDTO.setRoadname(rs.getString("roadname"));
			          zipcodeDTO.setBuildingname(rs.getString("buildingname"));
			          list.add(zipcodeDTO);
			       
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list=null;//오류나면 널포인트리셉션
		}finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public void modify(MemberDTO memberDTO) {
		String sql = "update member set name = ?, id = ?, pwd = ?, gender = ?, email1 = ?, email2 = ?, tel1 = ?, tel2= ?, tel3=?, zipcode = ?,addr1 = ?,addr2 = ?,sysdate)";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);// 생성
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getId());
			pstmt.setString(3, memberDTO.getPwd());
			pstmt.setString(4, memberDTO.getGender());
			pstmt.setString(5, memberDTO.getEmail1());
			pstmt.setString(6, memberDTO.getEmail2());
			pstmt.setString(7, memberDTO.getTel1());
			pstmt.setString(8, memberDTO.getTel2());
			pstmt.setString(9, memberDTO.getTel3());
			pstmt.setString(10, memberDTO.getZipcode());
			pstmt.setString(11, memberDTO.getAddr1());
			pstmt.setString(12, memberDTO.getAddr2());

			pstmt.executeUpdate();// 실행

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}


}

	