package inert.me;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SendMessageToOwner extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        PrintWriter out=response.getWriter();
        
        session=request.getSession();
        String id=(String)session.getAttribute("userid");
        if(id==null){
            response.sendRedirect("index.jsp");
        }
        
        String msgTo=request.getParameter("msgTo");
        String msgBy=(String)session.getAttribute("userid");
        String msg=request.getParameter("msg");
        java.util.Date dt=new java.util.Date();
        java.sql.Date sqldate=new java.sql.Date(dt.getTime());
        String pcode=request.getParameter("pcode");
        try{
            Connection con=inert.me.Util.connect();
            String sql="insert into messages(sender,receipt,message,mdate,pcode) values(?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, msgBy);
            ps.setString(2, msgTo);
            ps.setString(3, msg);
            ps.setDate(4, sqldate);
            ps.setInt(5, Integer.parseInt(pcode));
            ps.executeUpdate();
            ps.close();
            con.close();
            out.println("<html>");
            out.println("<body>");
            out.println("<h3>Message Sent Successfully!!!</h3>");
            out.println("<hr>");
            
            out.println("<a href=customerDashboard.jsp>Customer-Dashboard</a>");
            out.println("</body>");
            out.println("</html>");
            
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
