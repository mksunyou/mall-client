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

/**
 * Servlet implementation class UpdateClientController
 */
@WebServlet("/UpdateClientPwController")
public class UpdateClientPwController extends HttpServlet {
	private ClientDao clientDao;
	
	//로그인 상태에서만 들어올 수 있도록
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"IndexController");
			return;
		}
		
		String clientMail = ((Client)(session.getAttribute("loginClient"))).getClientMail();

		//dao호출
		this.clientDao = new ClientDao();
		
		//회원정보 Mail
		Client client = this.clientDao.selectClientOne(clientMail);
		
		request.setAttribute("client", client);
		
		//로그인이 되어있다면 updateClientPw.jsp로 이동
		request.getRequestDispatcher("/WEB-INF/view/client/updateClientPw.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.clientDao = new ClientDao();
		
		//form으로 받은 정보를 client 매개변수로 사용하기 위해 새로운 객체 생성 후 데이터 저장
		String newPw = request.getParameter("newPw");
		Client client = new Client();
		client.setClientMail(request.getParameter("clientMail"));
		client.setClientPw(request.getParameter("clientPw"));
		
		//dao호출
		this.clientDao = new ClientDao();
		
		//비밀번호 확인
		if(this.clientDao.checkIdPw(client) == false) {	//비밀번호가 다르면
			response.sendRedirect(request.getContextPath()+"/ClientOneController");
			System.out.println("비밀번호가 틀렸습니다.");
			return;
		}
		
		//비밀번호가 일치하면
		this.clientDao.updateClientPw(client, newPw);
		System.out.println("비밀번호 변경 완료");
		
		//로그아웃
		HttpSession session = request.getSession();
		session.invalidate();
		
		//다시 메인 페이지
		response.sendRedirect(request.getContextPath()+"/IndexController");
	}

}
