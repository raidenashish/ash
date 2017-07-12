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



@WebServlet("/AnswerForm")
public class AnswerForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String QID;
  
    public AnswerForm() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String)request.getSession().getAttribute("user");
		
		if(user != null){
			ArrayList<Category> categories = new ArrayList<Category>();
			ArrayList<subCategory> subCategories = new ArrayList<subCategory>();
			try {
				  connectToDB ctd = new connectToDB();
		          Connection c = (Connection)ctd.getC();
		          Statement stmt = c.createStatement();
		          ResultSet rs = stmt.executeQuery( "select * from category" );
		          while(rs.next()){
					Category tmpCat = new Category();
					tmpCat.setCatName(rs.getString("categoryName"));
					tmpCat.setCID(rs.getString("CID"));
					categories.add(tmpCat);
		          }
		          stmt = c.createStatement();
		          rs = stmt.executeQuery( "select * from subcategory" );
		          while(rs.next()){
					subCategory tmpSCat = new subCategory();
					tmpSCat.setSubCatName(rs.getString("name"));
					tmpSCat.setCID(rs.getString("CID"));
					tmpSCat.setUID(rs.getString("UID"));
					subCategories.add(tmpSCat);
				  }
		          getServletContext().setAttribute("categories", categories);
		          getServletContext().setAttribute("subCategories", subCategories);
		          c.close();
		           
		          
			} catch (SQLException e) {
				 e.printStackTrace();
				}
		
		this.QID = request.getParameter("q");
		ArrayList<Query> query = (ArrayList<Query>)getServletContext().getAttribute("queries");
		for(Query q : query){
			if(q.getQID().equals(QID)){
				Query tmpQuery = new Query();
				tmpQuery.setQuery(q.getQuery());
				tmpQuery.setAnswer(q.getAnswer());
				tmpQuery.setEmailUser(q.getEmailUser());
				tmpQuery.setCategory(q.getCategory());
				tmpQuery.setSubCategory(q.getSubCategory());
				tmpQuery.setAssignedUID(q.getAssignedUID());
				tmpQuery.setQID(q.getQID());
				
				getServletContext().setAttribute("tmpQuery", tmpQuery);
			}
		}
	
			request.getRequestDispatcher("/WEB-INF/answerForm.jsp").forward(
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
			//Establish connection
			try{
				connectToDB ctd = new connectToDB();
				Connection c = (Connection)ctd.getC(); 
				
		         String parameter = request.getParameter("q");
		         
			
				if(parameter.equals("addQuery")){
					 request.getRequestDispatcher("WEB-INF/addQueryForm.jsp").forward(
			            		request,response );
				}
				else if(parameter.equals("delete")){
					String QID = request.getParameter("QID");
					PreparedStatement ps = c.prepareStatement("delete from query where QID = ?");
					ps.setString(1, QID);
					ps.executeUpdate();        
			
				    response.sendRedirect("admin?q=Home");
				    
				}
			
			//For addQueryForm.jsp adding new query
				else if(parameter.equals("submit")){
					
					String question = request.getParameter("question");
					String answer = request.getParameter("answer");
					int CID = Integer.parseInt(request.getParameter("category"));
					String subcategory = request.getParameter("assignedSUB");
					String email = request.getParameter("email");
					//String smeUID = request.getParameter("assignSME");
 
					int flag = Integer.parseInt(request.getParameter("publish"));
					/**
					 * To implement:
					 * Chceck to see if same query exists					 * 
					 */
					
					PreparedStatement ps = c.prepareStatement("select * from category where CID =?");
					ps.setInt(1,CID);
					ResultSet rs = ps.executeQuery();
					rs.next();
					String category = rs.getString("categoryName");
					ps = c.prepareStatement("select * from subcategory sc, category c where sc.CID=c.CID and c.categoryName=? and sc.name=?");
					ps.setString(1, category);
					ps.setString(2, subcategory);
					rs = ps.executeQuery();
					rs.next();
					String smeUID = rs.getString("UID");
					
					ps = c.prepareStatement("insert into query(query,email_user,category,subCategory,publishFlag,answer,assignedUID) values(?,?,?,?,?,?,?)");
					ps.setString(1, question);
					ps.setString(2, email);
					ps.setString(3,  category);
					ps.setString(4, subcategory);
					ps.setInt(5, flag);
					ps.setString(6, answer);
					ps.setString(7,smeUID);
					ps.executeUpdate();
					
					PreparedStatement getEmail = c.prepareStatement("select email from user u, subcategory sc, category c where sc.name = ? and c.CID=sc.CID and c.categoryName = ? and sc.UID = u.UID");
					getEmail.setString(1, subcategory);
					getEmail.setString(2, category);
	            	ResultSet rs_Email = getEmail.executeQuery();
	            	rs_Email.next();
	            	String to = rs_Email.getString("email");
	            	String sub = "Unanswered Query";
	            	String msg = question;
					Mailer m = new Mailer();
					m.send(m.getQueryEmail(), m.getQueryEmailPassword(), to, sub, msg);

					
					
					response.sendRedirect("admin?q=Home");
					
				}
				else if(parameter.equals("edit")){
					String question = request.getParameter("question");
					String answer = request.getParameter("answer");
					if(answer.trim().isEmpty())
						answer = null;
					int CID = Integer.parseInt(request.getParameter("category"));
					String subcategory = request.getParameter("assignedSUB");
					String email = request.getParameter("email");
					String QID = request.getParameter("QID");
				//	String smeUID = request.getParameter("assignSME");
					int flag = Integer.parseInt(request.getParameter("publish"));
					PreparedStatement ps = c.prepareStatement("select * from category where CID =?");
					ps.setInt(1,CID);
					ResultSet rs = ps.executeQuery();
					rs.next();
					String category = rs.getString("categoryName");
					ps = c.prepareStatement("select * from subcategory sc, category c where sc.CID=c.CID and c.categoryName=? and sc.name=?");
					ps.setString(1, category);
					ps.setString(2, subcategory);
					rs = ps.executeQuery();
					rs.next();
					String smeUID = rs.getString("UID");
					
					ps = c.prepareStatement("select * from query where query = ? and answer = ? and assignedUID = ? and category = ? and subCategory=?");
					ps.setString(1, question);
					ps.setString(2, answer);
					ps.setString(3, smeUID);
					ps.setString(4,category);
					ps.setString(5,subcategory);
					rs  = ps.executeQuery();
					
					System.out.println(category +  subcategory);
					if(rs.next() == true){
						response.sendRedirect("admin?q=Home");
					}
					
					else{
						PreparedStatement update = c.prepareStatement("UPDATE query SET query = ? ,answer = ?,category = ?,subCategory = ?,email_user = ?,publishFlag=?,assignedUID=? where QID = ?");
			            
						update.setString(1, question);
			            update.setString(2, answer);
			            update.setString(3, category);
			            update.setString(4,subcategory);
			            update.setString(5, email);
			            update.setInt(6, flag);
			            update.setString(7, smeUID);
//			            System.out.println(smeUID + QID);
			            update.setString(8, QID);
			            update.executeUpdate();			
			     
			            response.sendRedirect("admin?q=Home");	
			            }
				//Mailer
					
					PreparedStatement getEmail = c.prepareStatement("select email from user u, subcategory sc, category c where sc.name = ? and c.CID=sc.CID and c.categoryName = ? and sc.UID = u.UID");
					getEmail.setString(1, subcategory);
					getEmail.setString(2, category);
	            	ResultSet rs_Email = getEmail.executeQuery();
	            	rs_Email.next();
	            	String to = rs_Email.getString("email");
	            	String sub = "Unanswered Query";
	            	String msg = question;
					Mailer m = new Mailer();
					m.send(m.getQueryEmail(), m.getQueryEmailPassword(), to, sub, msg);
			}	
		
				c.close();	
				 
		}
		catch(SQLException e){
			System.out.println(e);
			}
			
		}
		
	}

}
