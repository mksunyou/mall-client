package mall.client.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class EncodingFilter implements Filter { //Filter는 interface
	
	//ServletRequest는 HttpSevletRequest의 부모. 따라서 형변환을 이용해 사용 가능.
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding("utf-8");
		System.out.println("EncodingFilter 실행");
		
		chain.doFilter(request, response); // 이 행 아래에 적으면 response를 필터, 이 행 위에 적으면 request를 필터
	}

}
