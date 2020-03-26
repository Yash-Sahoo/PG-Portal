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

public class ShowAllProperty extends HttpServlet {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session=request.getSession();
        String id=(String)session.getAttribute("userid");
        if(id==null){
            response.sendRedirect("index.jsp");
        }
        
        PrintWriter out=response.getWriter();
        try{
            con=inert.me.Util.connect();
            String qr="select * from property where permission='granted'";
            ps=con.prepareStatement(qr);
            rs=ps.executeQuery();
            boolean b=rs.next();
            rs.beforeFirst();
            if(b==false){
                out.println("<html><body>");
                out.println("<center><font size=7>Paying Guest Portal</font></center>");
                out.println("<hr>");
                out.println("<h2>No Such Property Found!!!</h2>");
                out.println("<hr>");
                out.println("<a href=adminDashboard.jsp>Home</a>");
                out.println("</body></html>");
            }else{
                out.println("<html>");
                out.println("<body>");
                out.println("<h2><font face=algerian>Property Details</font></h2>");
                out.println("<hr>");
                out.println("<table border=2>");
                out.println("<tr>");
                out.println("<th>pcode</th>");
                out.println("<th>email</th>");
                out.println("<th>ptype</th>");
                out.println("<th>ftype</th>");
                out.println("<th>fdesc</th>");
                out.println("<th>rent</th>");
                out.println("<th>ctype</th>");
                out.println("<th>Address</th>");
                out.println("<th>Status</th>");
                out.println("<th>Photo</th>");
                out.println("<th>IVRS No.</th>");
                out.println("<th>Board Type</th>");
                out.println("</tr>");
                while(rs.next()){
                    out.println("<tr>");
                    out.println("<td>"+rs.getString("pcode")+"</td>");
                    out.println("<td>"+rs.getString("email")+"</td>");
                    out.println("<td>"+rs.getString("ptype")+"</td>");
                    out.println("<td>"+rs.getString("ftype")+"</td>");
                    out.println("<td>"+rs.getString("fdesc")+"</td>");
                    out.println("<td>"+rs.getString("rent")+"</td>");
                    out.println("<td>"+rs.getString("ctype")+"</td>");
                    out.println("<td>"+rs.getString("hno")+","+rs.getString("street")+","+rs.getString("city")+","+rs.getString("state")+","+rs.getString("country")+"</td>");
                    out.println("<td>"+rs.getString("status")+"</td>");
                    out.println("<td><img height=100 width=150 src=\"imageloader?id="+rs.getString("pcode")+"\"></td>");
                    out.println("<td>"+rs.getString("ivrs")+"</td>");
                    out.println("<td>"+rs.getString("board")+"</td>");
                    out.println("</tr>");
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
