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
 * Servlet implementation class InsertCartController
 */
@WebServlet("/InsertCartController")
public class InsertCartController extends HttpServlet {
	private CartDao cartDao;
	
	//로그인만 가능하도록.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			//로그인이 되어있지 않으면 메인화면
			response.sendRedirect("/IndexController");
			return;
		}
		
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		this.cartDao = new CartDao();
		Cart cart = new Cart();
		cart.setEbookNo(ebookNo);
		cart.setClientMail(((Client)session.getAttribute("loginClient")).getClientMail());
		
		//카트안에 동일한 ebook이 존재하는지 확인 코드 추가
		if(this.cartDao.selectClientMail(cart)) {
			this.cartDao.insertCart(cart);
		} else {
			System.out.println("장바구니에 이미 존재합니다.");
		}

		response.sendRedirect(request.getContextPath()+"/CartListController");		
	}

}
