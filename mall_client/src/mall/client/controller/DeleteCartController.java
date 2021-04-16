package mall.client.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.vo.Cart;
import mall.client.vo.Client;

/**
 * Servlet implementation class OrderCartController
 */
@WebServlet("/DeleteCartController")
public class DeleteCartController extends HttpServlet {
	private CartDao cartDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//session안에 loginClient가 없으면(로그인이 되어있지 않으면) 메인화면으로. 
		if(session.getAttribute("loginClient")==null) {
			response.sendRedirect("/IndexController");
			return;
		}
		
		//ebookNo, clientMail 호출
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		String clientMail =  ((Client)(session.getAttribute("loginClient"))).getClientMail();
		
		//dao 호출
		this.cartDao = new CartDao();
		
		//삭제 실행
		this.cartDao.deleteCart(clientMail, ebookNo);
		
		response.sendRedirect(request.getContextPath()+"/CartListController");
		
	}

}
