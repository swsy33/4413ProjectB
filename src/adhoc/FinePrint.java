package adhoc;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class FinePrint
*/
@WebFilter(
		dispatcherTypes = {DispatcherType.FORWARD }				, 
		urlPatterns =  "/Result.jspx")
 


public class FinePrint implements Filter {

    /**
     * Default constructor. 
     */
    public FinePrint() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		//chain.doFilter(request, response);
		//System.out.println("beforer doFilter");
		String interest = request.getParameter("interest");
		String bank = request.getParameter("bank");
		if(((interest == null) || interest.isEmpty()) && bank.equalsIgnoreCase("CIBC"))
		{
			request.setAttribute("finePrint", true);
			//System.out.println("before dof fineprint true");
		}
		else
			request.setAttribute("finePrint", false);
		chain.doFilter(request, response);
		//System.out.println("afterr doFilter");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("in filter init");
	}

}
