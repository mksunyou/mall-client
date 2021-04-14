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


@WebServlet("/InsertClientController")
public class InsertClientController extends HttpServlet {
	private ClientDao clientDao;
	
	//로그인상태에서 들어올 수 없도록
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") != null) { //로그인 되어있으면 인덱스로 보냄
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		//로그인이 되어있지 않으면 insertClient.jsp로 보냄
		request.getRequestDispatcher("/WEB-INF/view/client/insertClient.jsp").forward(request, response);
	}
	//action c-> m-> redirect
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.clientDao = new ClientDao();
		
		//form으로 받은 정보를 client매개변수로 사용하기 위해서 새로운 객체 생성 후 데이더를 저장한다.
		Client client = new Client();
		client.setClientMail(request.getParameter("clientMail"));
		client.setClientPw(request.getParameter("clientPw"));
		
		//ClientMail 중복확인
		String checkMail = this.clientDao.selectClientMail(client.getClientMail());
		System.out.println(checkMail);
		if(checkMail != null) { //중복된 Mail 이라면
			request.getRequestDispatcher("/WEB-INF/view/client/insertClient.jsp").forward(request, response);
			return;
		}
		
		//중복된 Mail이 아니라면 insertClient 진행
		this.clientDao.insertClient(client);
		
		response.sendRedirect(request.getContextPath()+"/IndexController");
	}

}