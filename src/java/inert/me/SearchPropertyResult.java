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

public class SearchPropertyResult extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //reads the request
        HttpSession session=request.getSession();
        String email=(String)session.getAttribute("userid");
        String ptype=request.getParameter("ptype");
        String ftype=request.getParameter("ftype");
        String rent=request.getParameter("rent");
        String ctype=request.getParameter("ctype");
        String city=request.getParameter("city");
        String state=request.getParameter("state");
        String country=request.getParameter("country");
        PrintWriter out=response.getWriter();
        
        session=request.getSession();
        String id=(String)session.getAttribute("userid");
        if(id==null){
            response.sendRedirect("index.jsp");
        }
        
        try{
            Connection con=Util.connect();
            String qr1="select * from property where ptype=? and ftype=? and rent<=? and ctype=? and city=? and state=? and country=? and status='vacant' and permission='granted'";
            PreparedStatement ps=con.prepareStatement(qr1);
            ps.setString(1, ptype);
            ps.setString(2, ftype);
            ps.setInt(3, Integer.parseInt(rent));
            ps.setString(4, ctype);
            ps.setString(5, city);
            ps.setString(6, state);
            ps.setString(7, country);
            ResultSet rs=ps.executeQuery();
            
            boolean b=rs.next();
            if(b){
                rs.beforeFirst();
                out.println("<html>");
                out.println("<body>");
                out.println("<h2><font face=algerian>Search Results For Property Details...</font></h2>");
                out.println("<hr>");
                out.println("<center><table border=2>");
                out.println("<tr>");
                out.println("<th width=70>Code</th>");
        //        out.println("<th>email</th>");
                out.println("<th width=80>ptype</th>");
                out.println("<th width=90>ftype</th>");
                out.println("<th width=170>fdesc</th>");
                out.println("<th width=80>rent</th>");
                out.println("<th width=90>ctype</th>");
                out.println("<th width=270>address</th>");
                out.println("<th width=70>status</th>");
                out.println("<th width=100>photo</th>");
                out.println("</tr>");
                while(rs.next()){
                    out.println("<tr>");
                    out.println("<td><center>"+rs.getString("pcode")+"</center></td>");
        //            out.println("<td>"+rs.getString("email")+"</td>");
                    out.println("<td><center>"+rs.getString("ptype")+"</center></td>");
                    out.println("<td><center>"+rs.getString("ftype")+"</center></td>");
                    out.println("<td><center>"+rs.getString("fdesc")+"</center></td>");
                    out.println("<td><center>"+rs.getString("rent")+"</center></td>");
                    out.println("<td><center>"+rs.getString("ctype")+"</center></td>");
                    out.println("<td><center>"+rs.getString("hno")+","+rs.getString("street")+","+rs.getString("city")+","+rs.getString("state")+","+rs.getString("country")+"</center></td>");
                    out.println("<td><center>"+rs.getString("status")+"</center></td>");
                    out.println("<td><img height=100 width=150 src=\"imageloader?id="+rs.getString("pcode")+"\"></td>");
                    out.println("<td><center><a href='ShowOwnerDetails?id="+rs.getString("pcode")+"'>owner</a></center></td>");
                    out.println("</tr>");
                }
                out.println("</table></center>");
                out.println("<hr>");
                out.println("<center><a href=SearchForProperty.jsp>Search-Again</a></center><br>");
                out.println("<center><a href=customerDashboard.jsp>Customer-Dashboard</a></center>");
                out.println("</body>");
                out.println("</html>");
                ps.close();
                rs.close();
                con.close();
            }else{
                out.println("<html><body><h2><center>Paying Guest Portal</center></h2><hr><h4>No such Property Exists according to your searching credentials!!!</h4><hr>");
                out.println("<a href=SearchForProperty.jsp>Search-Again</a><br>");
                out.println("<a href=customerDashboard.jsp>Customer-Dashboard</a>");
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
