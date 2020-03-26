<%
    session=request.getSession();
    String name=(String)session.getAttribute("username");
    if(name==null){
        response.sendRedirect("index.jsp");
    }
%>
<html>
    <script>
        function checkrent(){
            var rent=document.getElementById("price").value;
            if(isNaN(rent)){
                alert("Rent must be Numeric Value");
                return false;
            }else{
                if(rent<0){
                    alert("Please enter a valid Rent Price\nRent Price cannot be negative");
                    return false;
                }else if(rent.length===0){
                    alert("Enter Rent of your property");
                    return false;
                }else{
                    return true;
                }
            }
        }
    </script>
    <body  style="background: url(serpro.jpg); background-size: 100% 100%;">
    <center><h3><font color="magenta" face="bookman old style"><u><i><b>Paying Guest Portal</b></i></u></font></h3></center>
    <hr>
    <br>
    <font align="right">
    <h3><font face=algerian color=red>Search Property Here</font></h3>
    <form action="SearchPropertyResult" method="post" onsubmit="return checkrent()">
        <pre>
       Property Type    <select name="ptype">
                        <option>room</option>
                        <option>flat</option>
                        <option>apartment</option>
                        </select><br>
         Food Type     <select name="ftype">
                        <option>veg</option>
                        <option>non-veg</option>
                        <option>no food</option>
                        </select><br>
          Rent Range  <input type="text" name="rent" id="price"/><br>
        Customer Type   <select name="ctype"/>
                        <option>student-male</option>
                        <option>student-female</option>
                        <option>worker-male</option>
                        <option>worker-female</option>
                        <option>Family</option>
                        </select><br>
            City      <select name="city">
                      <option>indore</option>
                      <option>bhopal</option>
                      <option>mumbai</option>
                      <option>vadodara</option>
                      <option>dehradun</option>
                      <option>Khargone</option>
                      <option>Dhar</option>
                      <option>Chennai</option>
                      </select><br>
            State     <select name="state">
                      <option>M.P.</option>
                      <option>U.P.</option>
                      <option>M.H.</option>
                      <option>H.P.</option>
                      <option>Gujarat</option>
                      <option>T.N.</option>
                      </select><br><br><br><br><br><br>
            Country   <select name="country">
                      <option>India</option>
                      <option>Nepal</option>
                      <option>China</option>
                      </select><br>
                      <input type="submit" value="Search Property"/>
        </pre>
    </form>  
    </font><br><br><br><br><br><br>
    <hr>
    <a href="customerDashboard.jsp"><center>Customer-Dashboard</center></a>
    </body>
</html>
