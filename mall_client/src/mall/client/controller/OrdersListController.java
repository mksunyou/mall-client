package mall.client.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.OrdersDao;
import mall.client.vo.Client;


@WebServlet("/OrdersListController")
public class OrdersListController extends HttpServlet {
	private OrdersDao odersDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 되어 있는 사람만 볼 수 있기 때문에 로그인 or redirect
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		//인코딩
		request.setCharacterEncoding("utf-8");
		
		//의존객체 생성 후 주입
		this.odersDao = new OrdersDao();
		
		//로그인 정보 가져오기.
		Client client = (Client)session.getAttribute("loginClient");
		
		List<Map<String, Object>> ordersList = this.odersDao.selectOrderListByClient(client.getClientNo());
		request.setAttribute("ordersList", ordersList);
		
		request.getRequestDispatcher("/WEB-INF/view/orders/ordersList.jsp").forward(request, response);
	}
}
