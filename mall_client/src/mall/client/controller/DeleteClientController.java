package mall.client.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.model.ClientDao;
import mall.client.vo.Client;

@WebServlet("/DeleteClientController")
public class DeleteClientController extends HttpServlet {
	private ClientDao clientDao;
	private CartDao cartDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 아니면 redirect
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		//세션에서 메일주소 가져오기
		String clientMail = ((Client)(session.getAttribute("loginClient"))).getClientMail();


		//장바구니 비운 후 고객 삭제실행
		this.cartDao = new CartDao();
		this.cartDao.deleteCartByClient(clientMail);
		this.clientDao =new ClientDao(); 
		this.clientDao.deleteClient(clientMail);

		//세션 초기화
		session.invalidate();

		//완료후 다시 index페이지로
		response.sendRedirect(request.getContextPath()+"/IndexController");

	}

}