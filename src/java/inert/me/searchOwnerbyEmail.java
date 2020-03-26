package inert.me;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class searchOwnerbyEmail extends HttpServlet {

    Connection con;
    ResultSet rs;
    PreparedStatement ps1;
    
    public void init(){
        try{
            con=inert.me.Util.connect();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void destroy(){
        try{
            con.close();
            ps1.close();
    
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session=request.getSession();
        String id=(String)session.getAttribute("userid");
        if(id==null){
            response.sendRedirect("index.jsp");
        }
        
        PrintWriter out=response.getWriter();
        String email1=request.getParameter("emailofowner");
        String sql="select * from users where utype='owner' and email=? and status='activated'";
        
        try{
        
            ps1=con.prepareStatement(sql);
            ps1.setString(1, email1);
            rs=ps1.executeQuery();
            
            if(rs.next()==false){
                out.println("<h3>No record found</h3>");
                out.println("<hr>");
                out.println("<a href=adminDashboard.jsp>Admin-Dashboard</a>");
            }else{
                rs.beforeFirst();
            out.println("<html>");
            out.println("<body>");
            out.println("<h3>Block the Owners</h3>");
            out.println("<hr>");
            out.println("<table border=2>");
            out.println("<tr>");
            out.println("<th>E-Mail</th>");
            out.println("<th>Name</th>");
            out.println("<th>Address</th>");
            out.println("<th>Mobile</th>");
            out.println("</tr>");
            while(rs.next()){
                String email=rs.getString("email");
                String name=rs.getString("name");
                String address=rs.getString("hno")+","+rs.getString("street")+","+rs.getString("city")+","+rs.getString("state")+","+rs.getString("country");
                String mobile=rs.getString("mobile");
                
                out.println("<tr>");
                out.println("<td>"+email+"</td>");
                out.println("<td>"+name+"</td>");
                out.println("<td>"+address+"</td>");
                out.println("<td>"+mobile+"</td>");
                
                out.println("<td><a href='BlockingTheOwner?id="+email+"'>block</a></td>");
            }
            out.println("</table>");
            out.println("<hr>");
            out.println("<a href=adminDashboard.jsp>Admin-Dashboard</a>");
            out.println("</body>");
            out.println("</html>");
            
            }
            
        }catch(Exception e){
            out.println(e);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
