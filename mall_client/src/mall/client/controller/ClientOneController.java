package mall.client.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.ClientDao;
import mall.client.vo.Client;

@WebServlet("/ClientOneController")
public class ClientOneController extends HttpServlet {
	private ClientDao clientDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client client = new Client();
		// 로그인 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		String clientMail = ((Client)session.getAttribute("loginClient")).getClientMail(); // 세션에서 받아온 값을 문자열타입으로 clientMail에 저장 (세션을 받아 올때 email만 받아옴)
		System.out.println(clientMail+"고객정보세션"); //디버깅코드
		this.clientDao = new ClientDao();
		client = this.clientDao.selectClientOne(clientMail);
		request.setAttribute("client", client);
		request.getRequestDispatcher("/WEB-INF/view/client/clientOne.jsp").forward(request, response);
	}
}