package FAQ.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import FAQ.model.subCategory;
@WebServlet("/Dropdown")
public class Dropdown extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Dropdown() {
    }


	
	public String getCategoryFromCookie(HttpServletRequest request){
		for(Cookie cookie :request.getCookies()){
			if(cookie.getName().equals("catName"))
				return cookie.getValue();
				
		}
		return null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<subCategory> subCategories = (ArrayList<subCategory>)getServletContext().getAttribute("subCategories");
		ArrayList<subCategory> subList =  new ArrayList<subCategory>();
		String valajax = request.getParameter("valajax"); 
		
		for(subCategory sc : subCategories) {
			if(sc.getCID().equals(valajax)){
				subList.add(sc);
			}
		} 
		response.getWriter().write("<select name = \"assignedSUB\">");
		response.getWriter().write("<option value = \"null\"></option>");
			for(subCategory sc : subList){
				response.getWriter().write("<option value = \"" + sc.getSubCatName() + "\">"+ sc.getSubCatName() +"</option>");
			}
			response.getWriter().write("</select>");
		
//		getServletContext().setAttribute("subList", subList);
//		response.sendRedirect(request.getHeader("referer"));
//		///The Cookie Paart
//		
//		String catName = getCategoryFromCookie(request);
//		if(catName == null){
//			
//		}
//		
//		
//		
//		
//		String categoryFromCookie = getCategoryFromCookie(request);
//		if(categoryFromCookie == null){
//			Cookie cookie = new Cookie("catName",catName);
//			response.addCookie(cookie);
//		}
//		getServletContext().setAttribute("categorycookie", categoryFromCookie);
//		
//		
//		
//		if(request.getParameter("dropDownCategory") != null){
//			
//		}
	}

}
