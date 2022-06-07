package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 메소드 일반
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// action을 꺼낸다
		String action = request.getParameter("action");
		System.out.println(action);

		if ("joinForm".equals(action)) { // 회원가입폼
			System.out.println("UserController>joinForm");
			// joinForm 포워드 (브라우저 주소가 아닌 이클립스 내부의 파일위치 주소 써야함)
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");

		} else if ("join".equals(action)) { // 회원가입
			System.out.println("UserController>join");

			// 파라미터 꺼내기
			// (이 과정을 거치면 브라우저 주소에 id=aaa&password=1234&name=김수빈 이렇게 쓰면 db에 저장됨)
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");

			/*
			 * System.out.println(id); System.out.println(password);
			 * System.out.println(name); System.out.println(gender);
			 */

			// 0*333 = Vo만들기
			UserVo userVo = new UserVo(id, name, password, gender);
			System.out.println(userVo);

			// Dao를 이용하여 저장하기
			UserDao userDao = new UserDao();
			userDao.insert(userVo);

			// 포워드 => 내부 주소 잘 기입하기
			WebUtil.forward(request, response, "WEB-INF/views/user/joinOk.jsp");

			
			
			
		} else if ("loginForm".equals(action)) { // 로그인폼
			System.out.println("UserController>loginForm");

			// 포워드 => 내부 주소 잘 기입하기
			WebUtil.forward(request, response, "WEB-INF/views/user/loginForm.jsp");

			
			
			
		} else if ("login".equals(action)) { // 로그인
			System.out.println("UserController>login");

			// 파라미터 꺼내기
			String id = request.getParameter("id");
			String password = request.getParameter("password");

			// Vo만들기 => 만들고 set을 이용하여 id,password 저장
			UserVo userVo = new UserVo();
			userVo.setId(id);
			userVo.setPassword(password);

			// Dao를 이용하여 저장하기 => new UserDao 이건 새롭게 저장할 때마다 써야하는게 맞나?
			UserDao userDao = new UserDao();
			UserVo authUser = userDao.getUser(userVo);

			
			//authUser 주소값이 있으면 	로그인 성공
			//authUser null이면 		로그인 실패
			if(authUser == null) {
				System.out.println("로그인 실패");
			} else {
				System.out.println("로그인 성공");
				
			//password 자리는 꺼낼 때 쓰는 키값인데 이게 뭔가..?	
			HttpSession	session = request.getSession();
			session.setAttribute("authUser", authUser);

			
			//메인 리다이렉트 ==> 로그인 성공하면 다시 메인페이지로 돌아가게 함
			WebUtil.redirect(request, response, "/mysite1/main");
			
			}
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
