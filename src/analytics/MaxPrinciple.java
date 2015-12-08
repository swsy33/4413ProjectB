package analytics;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class maxPrinciple
 *
 */
@WebListener
public class MaxPrinciple implements  HttpSessionAttributeListener {

	private double maxValue = Double.NEGATIVE_INFINITY;
	/**
	 * Default constructor. 
	 */
	public MaxPrinciple() {

	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent arg0)  { 
		String name = arg0.getName();
		if(name.equalsIgnoreCase("sessionPrinciple"))
		{
			/*String v = (String) arg0.getValue();
			//System.out.println("in listener value: " + v);
			setMaxValue(v);
			//System.out.println("session Attritube added in max11 max:" + this.maxValue);
			setSessionMaxPrinciple(arg0);
			*/
			setSessionMaxPrinciple(arg0);
		}

	}

	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent arg0)  { 

		String name = arg0.getName();
		//		this.get
		if(name.equalsIgnoreCase("sessionPrinciple"))
		{
			/*String v = (String) arg0.getValue();
			//System.out.println("Replacing orignial in listener new value: " + v);
			setMaxValue(v);
			//System.out.println("Replacing value in max22 max:" + this.maxValue);
			setSessionMaxPrinciple(arg0);
			*/
			//maxPrincipleReplace(arg0);
			setSessionMaxPrinciple(arg0);
			setContextMaxPrinciple(arg0);
		}
	}

	private void setContextMaxPrinciple(HttpSessionBindingEvent arg0)
	{
		/*HttpSession hs = arg0.getSession();
		String p = (String) hs.getAttribute("sessionPrinciple");
		setMaxValue(p);
		setSessionMaxPrinciple(arg0);*/
		HttpSession hs = arg0.getSession();
		String sessionMP = getSessionMaxPrinciple();
		String contextMP = getContextMaxPrinciple(arg0);
		String currMax;
		try
		{
			currMax = getMaxValue(sessionMP, contextMP);
			hs.getServletContext().setAttribute("maxPrinciple", currMax);
		}
		catch(Exception e)
		{
			
		}
	}
	
	private String getContextMaxPrinciple(HttpSessionBindingEvent arg0)
	{
		
		HttpSession hs = arg0.getSession();
		String result = (String)hs.getServletContext().getAttribute("maxPrinciple");
		return result;
		
	}
	
	private String getSessionMaxPrinciple()
	{
		String result;
		if(this.maxValue == Double.NEGATIVE_INFINITY)
			result = "Infinity";
		else
			result = String.valueOf(this.maxValue);
		return result;
	}

	private void setSessionMaxPrinciple(HttpSessionBindingEvent arg0)
	{
		HttpSession hs = arg0.getSession();
		String currentMax = (String) hs.getAttribute("sessionPrinciple");
		setMaxValue(currentMax);
		String mp = getSessionMaxPrinciple();
		hs.setAttribute("sessionMaxPrinciple", mp);
	}


	private void setMaxValue(String newValue) 
	{
		try
		{
			double nv = Double.parseDouble(newValue);
			if(this.maxValue < nv)
				this.maxValue = nv;

		}
		catch(Exception e)
		{

		}

	}
	
	//get max value between Value1 and Value2
	private String getMaxValue(String v1, String v2)
	{
		String result = v1;
		try
		{
			double dv1 = Double.parseDouble(v1);
			double dv2 = Double.parseDouble(v2);
			if(dv1 < dv2) result = String.valueOf(dv2);
		}
		catch(Exception e)
		{
			
		}
		return result;
		
	}
	
}