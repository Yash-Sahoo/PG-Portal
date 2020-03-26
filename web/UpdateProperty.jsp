<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%
    session=request.getSession();
    String name=(String)session.getAttribute("username");
    if(name==null){
        response.sendRedirect("index.jsp");
    }
    int pcode=Integer.parseInt(request.getParameter("id"));
    try{
        Connection con=inert.me.Util.connect();
        String sql="select * from property where pcode=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setInt(1,pcode);
        ResultSet rs=ps.executeQuery();
        rs.next();
        String ftype=rs.getString("ftype");
        String fdesc=rs.getString("fdesc");
        int rent=rs.getInt("rent");
        String ctype=rs.getString("ctype");
        String hno=rs.getString("hno");
        String street=rs.getString("street");
        String city=rs.getString("city");
        String state=rs.getString("state");
        String country=rs.getString("country");
%>
<html>
    
    <script>
        function checkAll(){
            var b1=checkFacilityDescription();
            var b2=hno();
            var b3=street();
            var b4=checkrent();
            var b5=ctypedesc();
            return (b1 && b2 && b3 && b4 && b5);
        }
        
        function ftype(){
            var ft=document.getElementById("fty").value;
            if(ft.length===0){
                alert("Food Type? (veg/non-veg/no food)");
                return false;
            }else{
                return true;
            }
        }
        function checkFacilityDescription(){
            var fdesc=document.getElementById("fdes").value;
            if(fdesc.length!==0){
                return true;
            }else{
                alert("Enter Facility Description\nAdd Nothing if you don't have any description of your property");
                return false;
            }
        }
        
        function ctypedesc(){
            var ctype=document.getElementById("cty").value;
            if(ctype.length!==0){
                return true;
            }else{
                alert("Enter Customer Type\n(working male/female, student male/female)");
                return false;
            }
        }
        
        function hno(){
            var hn=document.getElementById("hn").value;
            if(hn.length===0){
                alert("Enter Your House Number!");
                return false;
            }else{
                return true;
            }
        }
        function street(){
            var str=document.getElementById("str").value;
            if(str.length===0){
                alert("Enter Your Street Details!");
                return false;
            }else{
                return true;
            }
        }
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
                }
                else{
                    return true;
                }
            }
        }
    </script>    
    
    <body style="background: url(regpro.jpg); background-size: 100% 100%;">
    <center><h3><font color="red" face="bookman old style"><i><b><u>Paying Guest Portal</u></b></i></font></h3></center>
    <hr>
    <br>
    <font align="left" color="iron">
    <h3>&emsp;&emsp;<font face=algerian color=blue>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Update Property Details</font></h3>
    <form action="updateproperty?pcode=<%=pcode%>" method="post" onsubmit="return checkAll()">
        <pre>
        
            
 
        <b>Food Type</b>     <input type="text" id="fty" name="ftyp" value="<%=ftype%>"/><br>
 <b>Facility Description</b> <input type="text" id="fdes" name="fdes" value="<%=fdesc%>"/><br>
          <b>Rent Price</b>  <input type="text" id="price" name="ren" value="<%=rent%>"/><br>
      <b>Customer Type</b>   <input type="text" id="cty" name="ctyp" value="<%=ctype%>"/><br>
            
            <b>home no</b>   <input type="text" id="hn" name="hnoo" value="<%=hno%>"/><br>
            <b>Street</b>    <input type="text" id="str" name="stree" value="<%=street%>"/><br>

            <b>City</b>      <input type="text" id="cit" name="cityy" value="<%=city%>"/><br>
                      

            <b>State</b>     <input type="text" id="stat" name="statee" value="<%=state%>"/><br>

            <b>Country</b>   <input type="text" id="contry" name="countr" value="<%=country%>"/><br>
                
        
                <input type="submit" value="Update Property"/>
        </pre>
    </form>  
    </font>
    <hr>
    <a href="ViewOwnPropertyforStatus"><center>Back</center></a>
    <a href="ownerDashboard.jsp"><font color=blue><center>Owner-Dashboard</center></font></a>
    </body>
</html>
<%
        }catch(Exception e){
        e.printStackTrace();
    }

%>
