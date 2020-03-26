<%
    String to=request.getParameter("id");
    
    session=request.getSession();
    String by=(String)session.getAttribute("userid");
    String name=(String)session.getAttribute("username");
    if(name==null){
        response.sendRedirect("index.jsp");
    }
    int pcode=Integer.parseInt(request.getParameter("pcode"));
%>
<html>
    <body>
    <center>
        <h3>Send Message</h3>
        <hr>
        <form action="ReplyByCustomer" method="post">
            <pre>
        To      <input type="text" value="<%=to%>" name="msgTo"/><br>
        Message <textarea name="msg" cols="50" rows="4"></textarea><br>
        <input type="hidden" name="pcode" value="<%=pcode%>"/>
            <input type="submit" value="Send"/>
            </pre>
        </form>
        <hr>
        <a href=ShowInboxOfCustomer.jsp>Back</a><br>
        <a href="customerDashboard.jsp">Customer-Dashboard</a>
    </body>
</html>
