package mall.client.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jsp 파일은 session이
		HttpSession session =request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/IndexController");//sendRedirect 상대는 controller
	}

}
