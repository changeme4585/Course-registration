package mvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import javax.swing.ActionMap;

import com.mysql.cj.xdevapi.Session;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main/*")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      StudentDAO studentDAO ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
    	studentDAO = new StudentDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}
	 private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String nextPage = null; 
		 request.setCharacterEncoding("utf-8");
		 response.setContentType("text/html;charset=utf-8"); 
		 String action = request.getPathInfo();
		 if(action ==null || action.equals("/login.do")) {
			 req(request,response,"/mvc/main.jsp");
		 }
		 else if(action.equals("/loginCheck.do")) {
			int sid = Integer.valueOf(request.getParameter("sid"));
			String password = request.getParameter("password");
			int flag = studentDAO.getStu(sid,password);
			System.out.println(flag);
			if (flag == 1) { //로그인 성공 
				HttpSession session = request.getSession();
				session.setAttribute("sid", sid);// 세션부여 
				//session.setAttribute("password", password);// 세션부여 
				String name =  studentDAO.getName(Integer.valueOf(request.getParameter("sid")),request.getParameter("password"));
				session.setAttribute("name", name);// 세션부여 
				response.sendRedirect("logOn.do");
				
			}else {
				response.sendRedirect("login.do");
			}
		 }
		 else if(action.equals("/logOn.do")) {
//			 HttpSession session = request.getSession();
//			
//			 int sid =Integer.valueOf( (String) session.getAttribute("sid"));
//			 String password = (String)session.getAttribute("password");
// 			
				String sql ="SELECT \n"
						+ "    course.cid,\n"
						+ "    course.cname,\n"
						+ "    course.degree,\n"
						+ "    course.lecturer,\n"
						+ "    course.slot\n"
						+ "FROM \n"
						+ "    mycourse\n"
						+ "JOIN \n"
						+ "    course \n"
						+ "ON \n"
						+ "    enroll.cid = course.cid\n"
						+ "WHERE \n"
						+ "    enroll.sid = ?";
				HttpSession session = request.getSession();
				int sid = (Integer) session.getAttribute("sid");
				List<CourseVO> myCourseList =studentDAO.courseList(sid,sql); //고치기 
				request.setAttribute("myCourseList", myCourseList); //내가 수강중인 과목 
				//request.setAttribute("name", name);
			 req(request,response,"/mvc/logOn.jsp");
		 }
		 else if(action.equals("/apply.do")) {
			 
			 String sql = "SELECT \n"
			 		+ "    course.cid,\n"
			 		+ "    course.cname,\n"
			 		+ "    course.degree,\n"
			 		+ "    course.lecturer,\n"
			 		+ "    course.slot\n"
			 		+ "FROM \n"
			 		+ "    course\n"
			 		+ "LEFT JOIN \n"
			 		+ "    enroll \n"
			 		+ "ON \n"
			 		+ "    course.cid = enroll.cid AND enroll.sid = ?\n"
			 		+ "WHERE \n"
			 		+ "    enroll.cid IS NULL;\n";
			 HttpSession session = request.getSession();
			 int sid = (Integer) session.getAttribute("sid");
			 List<CourseVO> courseList =studentDAO.courseList(sid,sql); //고치기 
			 request.setAttribute("courseList", courseList); // 내가 수강신청하지 않은 과목
			 for(CourseVO courseVO : courseList) {
				 System.out.println(courseVO.getCname());
			 }
			
			 req(request,response,"/mvc/regist.jsp");
		 }
		 else if(action.equals("/registForm.do")) {
			 int cid = Integer.valueOf(request.getParameter("cid"));
			 CourseVO courseVO = studentDAO.courseDetail(cid);
			 
 			 request.setAttribute("courseVO",courseVO);
 			 req(request,response,"/mvc/registForm.jsp");
		 }
		 else if(action.equals("/insert_course.do")) {
			 HttpSession session = request.getSession();
			 int sid = (Integer) session.getAttribute("sid");
			 int cid = Integer.valueOf(request.getParameter("cid"));
			 studentDAO.saveCourse(sid, cid);
			 response.sendRedirect("logOn.do");
		 }
		 else if(action.equals("/delete.do")) {
			 int cid = Integer.valueOf(request.getParameter("cid"));
			 CourseVO courseVO = studentDAO.courseDetail(cid);
 			 request.setAttribute("courseVO",courseVO);
			 req(request,response,"/mvc/dropForm.jsp");
		 }
		 else if (action.equals("/delete_course.do")) {
			 HttpSession session = request.getSession();
			 int sid = (Integer) session.getAttribute("sid");
			 int cid = Integer.valueOf(request.getParameter("cid"));
			 studentDAO.dropCourse(sid, cid);
			 response.sendRedirect("logOn.do");
		 
		 }
	 }
	 private void req(HttpServletRequest request, HttpServletResponse response,String url) throws ServletException, IOException {
		 RequestDispatcher dispatch = request.getRequestDispatcher(url);
		 dispatch.forward(request, response); // view를 호출
	}

}
