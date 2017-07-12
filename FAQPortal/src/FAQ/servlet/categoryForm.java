package FAQ.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import FAQ.model.*;



@WebServlet("/categoryForm")
public class categoryForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
     public categoryForm() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String)request.getSession().getAttribute("user");
		if(user != null){
			String CID = request.getParameter("q");
		ArrayList<Category> catlist = (ArrayList<Category>)getServletContext().getAttribute("categories");
		for(Category cat : catlist){
			if(cat.getCID().equals(CID)){
				Category tmpCategory = new Category();
				tmpCategory.setCatName((cat.getCatName()));
				tmpCategory.setCID(cat.getCID());
				getServletContext().setAttribute("tmpCategory", tmpCategory);
			}
		}
	
			request.getRequestDispatcher("/WEB-INF/categoryForm.jsp").forward(
					request,response );
		}
		else
		{
			request.getRequestDispatcher("Home.jsp").forward(
					request,response );
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String)request.getSession().getAttribute("user");
		if(user != null){
			
			String catName = request.getParameter("category");
			String subCatName = request.getParameter("subcategory");
			String newSubCategory = new String();
			newSubCategory = request.getParameter("newsubcategory");
			String CID = request.getParameter("CID");
			String action = request.getParameter("q");
			
			try {
				connectToDB ctd = new connectToDB();
		        Connection c = (Connection)ctd.getC();
				if(action.equals("submit")){	
					
					if(!(newSubCategory.trim().isEmpty())){
						PreparedStatement insert = c.prepareStatement("insert into subcategory(name,CID) values(?,?)");
			            insert.setString(1, newSubCategory);
			            insert.setString(2, CID);
			            insert.executeUpdate();	
			            response.sendRedirect("admin?q=category");
					}
					else{
					PreparedStatement ps = c.prepareStatement("select * from category where categoryName = ? and CID = ?");
			            ps.setString(1, catName);
			            ps.setString(2, CID);
			            ResultSet rs  = ps.executeQuery();
	
			            if(rs.next() == true){
			            	//javascript dialogue to be added
			              	response.sendRedirect("admin?q=category");
			            }
			            else{
			            	PreparedStatement update = c.prepareStatement("UPDATE category SET categoryName = ? where CID = ?");
			            	update.setString(1, catName);
			            	update.setString(2, CID);
			            	update.executeUpdate();		
			            	response.sendRedirect("admin?q=category");
			            }
					}    
				}
				else if(action.equals("delete")){
					PreparedStatement delete = c.prepareStatement("delete from subcategory where name = ?");
		            delete.setString(1, subCatName);
		            delete.executeUpdate();
		            delete = c.prepareStatement("update query set subCategory = ? where subCategory = ?");
		            delete.setString(1, null);
		            delete.setString(2, subCatName);
		            delete.executeUpdate();
		            response.sendRedirect("admin?q=category");
				}
				
		          c.close();
		           

		        
		        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
				}
			
	}
	}

}
