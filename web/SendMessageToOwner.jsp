<%
    String email=request.getParameter("email");
    int pcode=Integer.parseInt(request.getParameter("pcode"));
    session=request.getSession();
    String name=(String)session.getAttribute("username");
    if(name==null){
        response.sendRedirect("index.jsp");
    }
    
%>
<html>
    <body>
    <center>
        <h3>Send Message</h3>
        <hr>
        <form action="SendMessageToOwner" method="post">
            <pre>
        To      <input type="text" value="<%=email%>" name="msgTo"/><br>
        Message <textarea name="msg" cols="50" rows="4"></textarea><br>
                <input type="hidden" name="pcode" value="<%=pcode%>"/>
            <input type="submit" value="Send"/>
            </pre>
        </form>
        <hr>
        <a href=ShowOwnerDetails?id=<%=pcode%>>Back</a><br>
        <a href="customerDashboard.jsp">Customer-Dashboard</a>
    </body>
</html>
