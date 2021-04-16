package mall.client.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mall.client.model.CategoryDao;
import mall.client.model.EbookDao;
import mall.client.vo.Category;
import mall.client.vo.Ebook;

//Controller-> Model -> View
@WebServlet("/IndexController")
public class IndexController extends HttpServlet {	
	private EbookDao ebookDao;
	private CategoryDao categoryDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//Dao 호출
		this.ebookDao = new EbookDao();
		this.categoryDao = new CategoryDao();
		
		// request 분석
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 15;
		int beginRow = (currentPage-1)*rowPerPage;
		
		//카테고리 선택시 카테고리별 진행.
		String categoryName = null;
		if(request.getParameter("categoryName") != null) {
			categoryName = request.getParameter("categoryName");
		}

		//검색어가 있을시 진행.
		String searchWord = null;
		if(request.getParameter("searchWord") != null) {
			searchWord = request.getParameter("searchWord");
		}
	
		//총 책의 수
				int totalRow = this.ebookDao.totalCount(searchWord, categoryName);
				//totalRow디버깅
				System.out.println(totalRow +"<---IndexController에서 totalRow");

				//마지막 페이지(나머지가 있으면 다 보여주기 위해서 올림계산)
				int lastPage = (int)Math.ceil((double)totalRow /rowPerPage); 

				////페이징하기위한 범위 (1~10, 11~20, ... 처럼 나누기위한변수)
				int pageRange = (currentPage - 1) / 10;


				//모델호출 카테고리 이름들은 리스트로 받아온다. 리스트안의 하나의 카테고리 객체에 카테고리이름과 가중치가 담겨있음. 
				List<Category> categoryList = this.categoryDao.selectCategoryList();
				// model 호출 category가 null이면 모든 책을 가져오고 카테고리가 null이 아니면 특정 카테고리만 가져온다. 검색에 유무에따라 나눔.
				List<Ebook> ebookList = null;
				//검색어가 없을때
				if(searchWord == null) {
					ebookList = this.ebookDao.selectEbookListByCategory(beginRow, rowPerPage, categoryName);
				} else {//검색어가 있을때
					ebookList = this.ebookDao.selectEbookListByCategory(beginRow, rowPerPage, searchWord);
				}

				// request객체에 리스트 저장 후 View forward
				request.setAttribute("searchWord", searchWord);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("rowPerPage", rowPerPage);
				request.setAttribute("lastPage", lastPage);
				request.setAttribute("pageRange", pageRange);
				request.setAttribute("categoryName", categoryName);
				request.setAttribute("categoryList", categoryList);
				request.setAttribute("ebookList", ebookList);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
				rd.forward(request, response);
			}
		}
