package mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	String jdbc_driver = "com.mysql.jdbc.Driver"; String jdbc_url =
	"jdbc:mysql://localhost/spring4fs";
	void connect() { try {
	Class.forName(jdbc_driver); conn =
	DriverManager.getConnection(jdbc_url, "spring4", "spring4");
	} catch (Exception e) { System.out.println(e);
	} }
	void disconnect() {
		if (pstmt != null) {
		try { pstmt.close();
		} catch (Exception e) { System.out.println(e);
		} }
		if (conn != null) { try {
		conn.close();
		} catch (Exception e) {
		e.printStackTrace(); }
		}
	}
	int getStu(int sid,String password) {
		connect();
		String sql = "select * from student where sid = ? and password = ?";
		StudentVO studentVO = new StudentVO();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sid);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					return 1;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				
			}
		return 0;
	}
	
	
	
	String getName(int sid,String password) {
		connect();
		String sql = "select * from student where sid = ? and password = ?";
		StudentVO studentVO = new StudentVO();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sid);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					return rs.getString("sname");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				
			}
		return "null";
	}
	CourseVO courseDetail (int cid) {
		CourseVO courseVO  = new CourseVO();
		connect();
		String sql = "select * from course where cid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				courseVO.setCid(cid);
				courseVO.setCname(sql);
				courseVO.setDegree(cid);
				courseVO.setLecturer(sql);
				courseVO.setSlot(sql);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			disconnect();
		}
		return courseVO;
	}
	List<CourseVO> courseList(int sid,String sql){
		connect();
		
		List<CourseVO> myCourse = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				CourseVO courseVO = new CourseVO();
				courseVO.setCid(rs.getInt("cid"));
				courseVO.setCname(rs.getString("cname"));
				courseVO.setDegree(rs.getInt("degree"));
				courseVO.setLecturer(rs.getString("lecturer"));
				courseVO.setSlot(rs.getInt("slot"));
				myCourse.add(courseVO);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			disconnect();
		}
		
		return myCourse;
 	}
}
