package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.util.WebUtil;

@WebServlet("/main")
public class MainController extends HttpServlet {
	//필드
	private static final long serialVersionUID = 1L;
	
	//생성자(디폴트 생성자)
 
	//메소드gs
	
	//메소드 일반

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post 방식일때 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		//코드 들어갈 곳
		System.out.println("MainController");
		
		//포워드
		WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
