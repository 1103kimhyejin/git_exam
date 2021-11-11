package board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import board.bean.BoardDTO;

public class BoardDAO {
	private Connection conn;// 오라클접근
	private PreparedStatement pstmt;// 오라클명령처리(여기는자바니까)
	private ResultSet rs;

	private DataSource ds;// 커넥션풀(창고같은거)사용하려고

	private static BoardDAO instance = null;

	public static BoardDAO getInstance() {
		if (instance == null) {
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
			}
		}

		return instance;
	}

	public BoardDAO() {
		try {
			Context ctx = new InitialContext();//context.xml 뺐으니까 처리한것같다,
			//Context는 인터페이스라서 자식을 이용해서 생성, 자식이 바로 이니셜....
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
			// lookup: 네임을 넣으면 꺼내준다. => 꺼내서 데이터 소스 전달
			// Tomcat의 경우 java:comp/env/적어줌

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
//public void write(BoardDTO boardDTO){
	public void write(Map<String, String> map) {// 디비 테이블에 디폴트 설정한 것은 값을 안줘도 된다!
		String sql = "insert into board(seq, id, name, email, subject, content, ref) "
				+ "values(seq_board.nextval,?,?,?,?,?,seq_board.currval)";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, boardDTO.getId());
//			pstmt.setString(2, boardDTO.getName());
//			pstmt.setString(3, boardDTO.getEmail());
//			pstmt.setString(4, boardDTO.getSubject());
//			pstmt.setString(5, boardDTO.getContent());
//방법2 map 스프링에서 많이쓴다
			pstmt.setString(1, map.get("id"));
			pstmt.setString(2, map.get("name"));
			pstmt.setString(3, map.get("email"));
			pstmt.setString(4, map.get("subject"));
			pstmt.setString(5, map.get("content"));
			pstmt.executeUpdate();

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

	
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd"); // 자바에서는 mm이 분 주의 , MM이 월

	public List<BoardDTO> getBoardList(int startNum, int endNum) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();

		String sql = "select * from " + "(select rownum rn, tt.* from "
				+ "(select * from board order by ref desc, step asc) tt) " + "where rn >=?  and rn<=?";

		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);

			rs = pstmt.executeQuery();

			while (rs.next()) {// 꺼내온다 근데 다섯개만 꺼내지말고 다꺼내와야한다 *니까
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setSeq(rs.getInt("seq"));
				boardDTO.setId(rs.getString("id"));
				boardDTO.setName(rs.getString("name"));
				boardDTO.setEmail(rs.getString("email"));
				boardDTO.setSubject(rs.getString("subject"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRef(rs.getInt("ref"));
				boardDTO.setLev(rs.getInt("lev"));
				boardDTO.setStep(rs.getInt("step"));
				boardDTO.setPseq(rs.getInt("pseq"));
				boardDTO.setReply(rs.getInt("reply"));
				boardDTO.setHit(rs.getInt("hit"));
				boardDTO.setLogtime(sdf.format(rs.getDate("logtime"))); // 데이트로 꺼내온것을 sdf 포맷설정(스트링) 해준다

				list.add(boardDTO);
			} // while

		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
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

		return list;
	}

	public int getTotalA() {
		int totalA = 0;
		String sql = "select count(*) from board";

		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();// 실행

			rs.next();
			totalA = rs.getInt(1); //1번 컬럼의 값을 꺼내와라
 
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

		return totalA;
	}

	public BoardDTO boardView(int seq) {
		BoardDTO boardDTO = null; // 생성안하고 널 없으면 널값꺼낼라고
		String sql = "select * from board where seq=?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				boardDTO = new BoardDTO();
				boardDTO.setSeq(rs.getInt("seq"));
				boardDTO.setId(rs.getString("id"));
				boardDTO.setName(rs.getString("name"));
				boardDTO.setEmail(rs.getString("email"));
				boardDTO.setSubject(rs.getString("subject"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRef(rs.getInt("ref"));
				boardDTO.setLev(rs.getInt("lev"));
				boardDTO.setStep(rs.getInt("step"));
				boardDTO.setPseq(rs.getInt("pseq"));
				boardDTO.setReply(rs.getInt("reply"));
				boardDTO.setHit(rs.getInt("hit"));
				boardDTO.setLogtime(sdf.format(rs.getDate("logtime")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardDTO;
	}

}
