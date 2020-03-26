<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%
    session=request.getSession();
    String name=(String)session.getAttribute("userid");
    if(name==null){
        response.sendRedirect("index.jsp");
    }else{
        Connection con=inert.me.Util.connect();
        String loc=request.getParameter("loc");
        int price=Integer.parseInt(request.getParameter("price"));
        String qbq="select * from property where status='vacant' and rent<=? and city=? or state=? or country=? or street=?";
        String sql="select utype from users where email=?";
        PreparedStatement ps=con.prepareStatement(qbq);
        PreparedStatement ps1=con.prepareStatement(sql);
        ps.setInt(1, price);
        ps.setString(2, loc);
        ps.setString(3, loc);
        ps.setString(4, loc);
        ps.setString(5, loc);
        ps1.setString(1, name);
        ResultSet rs=ps.executeQuery();
        ResultSet rs1=ps1.executeQuery();
        rs1.next();
        String utype=rs1.getString("utype");
        boolean b=rs.next();
            rs.beforeFirst();
            if(b==false){
                out.println("<html><body>");
                out.println("<h2>No Such Property Found!!!</h2>");
                out.println("<hr>");
                out.println("<a href=home.jsp>Home</a>");
                out.println("</body></html>");
            }else{
                out.println("<html>");
                out.println("<body>");
                out.println("<center><h2><font face=\"algerian\" color=\"red\"><u>Paying Guest Portal</u></font></h2></center>");
                out.println("<hr>");
                out.println("<h2><font face=algerian>All Property Details</font></h2>");
                out.println("<hr>");
                out.println("<table border=2>");
                out.println("<tr>");
                out.println("<th>pcode</th>");
            //    out.println("<th>email</th>");
                out.println("<th>ptype</th>");
                out.println("<th>ftype</th>");
                out.println("<th>fdesc</th>");
                out.println("<th>rent</th>");
                out.println("<th>ctype</th>");
                out.println("<th>address</th>");
                out.println("<th>status</th>");
                out.println("<th>photo</th>");
                out.println("</tr>");
                while(rs.next()){
                    out.println("<tr>");
                    out.println("<td>"+rs.getString("pcode")+"</td>");
            //        out.println("<td>"+rs.getString("email")+"</td>");
                    out.println("<td>"+rs.getString("ptype")+"</td>");
                    out.println("<td>"+rs.getString("ftype")+"</td>");
                    out.println("<td>"+rs.getString("fdesc")+"</td>");
                    out.println("<td>"+rs.getString("rent")+"</td>");
                    out.println("<td>"+rs.getString("ctype")+"</td>");
                    out.println("<td>"+rs.getString("hno")+","+rs.getString("street")+","+rs.getString("city")+","+rs.getString("state")+","+rs.getString("country")+"</td>");
                    out.println("<td>"+rs.getString("status")+"</td>");
                    out.println("<td><img height=100 width=150 src=\"imageloader?id="+rs.getString("pcode")+"\"></td>");
                    out.println("<td><a href='ShowOwnerDetails?id="+rs.getString("pcode")+"'>owner</a></td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<hr>");
                if(utype.equals("customer")){
                    out.println("<a href=customerDashboard.jsp>Customer-Dashboard</a>");
                }else if(utype.equals("owner")){
                    out.println("<a href=ownerDashboard.jsp>Owner-Dashboard</a>");
                }
                
                out.println("</body>");
                out.println("</html>");
                
            }
    }
%>
