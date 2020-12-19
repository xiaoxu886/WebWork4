package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*") 

public class LoginFilter implements Filter {


	public void destroy() {
		// TODO Auto-generated method stub

	}

	
	public void doFilter(ServletRequest req, ServletResponse reqs,
			FilterChain chain) throws IOException, ServletException {
		//0.ǿ��ת�� 
			HttpServletRequest request = (HttpServletRequest) req;
		
		//1.��ȡ��Դ����
			String uri = request.getRequestURI();
		//2.�ж��Ƿ������¼�����Դ·��
			if(uri.contains("/login.html") || uri.contains("/creatVerifyImage.do")  || uri.contains("/css/") 
					|| uri.contains("/js/") || uri.contains("/images/") || uri.contains("/login.do"))
			{
				//����,�û��������¼������
				chain.doFilter(req, reqs);
			}else{
				//����������Ҫ��֤�û��Ƿ��¼
				//3.�ӻ�ȡsession�л�ȡuser
		  		Object user = request.getSession().getAttribute("user");
		  		if(user != null){
		  			//��¼�ˣ�����
		  			chain.doFilter(req, reqs);
		  		}else{
		  			//û�е�¼����ת��¼ҳ��
		  			
		  			request.setAttribute("info","����δ��¼�����¼");
		  			request.getRequestDispatcher("/error.jsp").forward(request, reqs);
		  		}
		  		
			}
			

	}

	
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

}
