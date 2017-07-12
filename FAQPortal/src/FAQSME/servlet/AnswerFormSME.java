package FAQSME.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import FAQ.model.*;



@WebServlet("/AnswerFormSME")
public class AnswerFormSME extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
  
    public AnswerFormSME() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String)request.getSession().getAttribute("user");
		
		if(user != null){
			connectToDB ctd = new connectToDB();
			Connection c = (Connection)ctd.getC();
			String QID = request.getParameter("q");
			ArrayList<Query> query = (ArrayList<Query>)getServletContext().getAttribute("smeQueries");
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
					getServletContext().setAttribute("tmpSMEQuery", tmpQuery);
				}
			}
	
			request.getRequestDispatcher("WEB-INF/SMEpages/answerFormSME.jsp").forward(
					request,response );
		}
		else
		{
			response.sendRedirect("Home.jsp");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String)request.getSession().getAttribute("user");
		if(user != null){
			//Establish connection
			String q = request.getParameter("q");
			
			if(q.equals("edit")){
				
				connectToDB ctd = new connectToDB();
				Connection c = ctd.getC();
				String question = request.getParameter("question");
				String answer = request.getParameter("answer");
				String email = request.getParameter("email");
				String QID = request.getParameter("QID");
				String UID = request.getParameter("UID");
				int flag = Integer.parseInt(request.getParameter("publish"));
				System.out.println(email);
				try{
				PreparedStatement ps = c.prepareStatement("update query set answer=? , email_user = ? , publishFlag = ? where QID = ?");
				ps.setString(1,answer);
				ps.setString(2, email);
				ps.setInt(3, flag);
				ps.setInt(4, Integer.parseInt(QID));
				
				ps.executeUpdate();
				Mailer m = new Mailer();
				m.send(m.getQueryEmail(), m.getQueryEmailPassword(), email, "Solution To Query", answer);
				response.sendRedirect("sme?q=home&UID="+UID);	
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			
				
				
				
			}
			
		}
	}
}