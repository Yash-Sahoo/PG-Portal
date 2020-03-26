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

public class ShowOwnerDetails extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session=request.getSession();
        String id=(String)session.getAttribute("userid");
        if(id==null){
            response.sendRedirect("index.jsp");
        }
        
        PrintWriter out=response.getWriter();
        int pcode=Integer.parseInt(request.getParameter("id"));
        try{
            Connection con=inert.me.Util.connect();
            String sql="select name,hno,street,city,state,country,email,mobile from users where email=(select email from property where pcode=?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, pcode);
            ResultSet rs=ps.executeQuery();
            rs.next();
            String name=rs.getString("name");
            String address=rs.getString("hno")+","+rs.getString("street")+","+rs.getString("city")+","+rs.getString("state")+","+rs.getString("country");
            String email=rs.getString("email");
            String mobile=rs.getString("mobile");
            out.println("<html>");
            out.println("<body>");
            out.println("<h3> Owner of Property Code "+pcode+"");
            out.println("<hr>");
            out.println("<table border=2>");
            out.println("<tr>");
            out.println("<td>Name</td>");
            out.println("<td>"+name+"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Address</td>");
            out.println("<td>"+address+"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Email</td>");
            out.println("<td>"+email+"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Mobile No.</td>");
            out.println("<td>"+mobile+"</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<hr>");
            out.println("<a href='SendMessageToOwner.jsp?email="+email+"&pcode="+pcode+"'>Interested in this Property?</a><br>");
            out.println("<a href=SearchForProperty.jsp>Back-To-Search</a><br>");
            out.println("<a href=customerDashboard.jsp>Customer-Dashboard</a>");
            out.println("</body></html>");
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
