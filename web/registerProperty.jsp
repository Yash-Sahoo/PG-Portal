<%
    session=request.getSession();
    String name=(String)session.getAttribute("username");
    if(name==null){
        response.sendRedirect("index.jsp");
    }
%>
<html>
    
    <script>
        function checkAll(){
            var b1=checkFacilityDescription();
            var b2=hno();
            var b3=street();
            var b4=checkrent();
            return (b1 && b2 && b3 && b4);
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
    <h3>&emsp;&emsp;<font face=algerian color=blue>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Property Registration Form</font></h3>
    <form action="saveProperty" method="post" onsubmit="return checkAll()" enctype="multipart/form-data">
        <pre>
        
            
    <b>Property Type</b>   <select name="ptype">
                        <option>room</option>
                        <option>flat</option>
                        <option>apartment</option>
                    </select><br>
      <b>Food Type</b>     <select name="ftype">
                        <option>veg</option>
                        <option>non-veg</option>
                        <option>no food</option>
                        </select><br>
 <b>Facility Description</b> <input type="text" id="fdes" name="fdesc"/><br>
          <b>Rent Price</b>  <input type="text" id="price" name="rent"/><br>
      <b>Customer Type</b>   <select name="ctype"/>
                        <option>student-male</option>
                        <option>student-female</option>
                        <option>worker-male</option>
                        <option>worker-female</option>
                        <option>Family</option>
                      </select><br>
            
            <b>home no</b>   <input type="text" id="hn" name="hno"/><br>
            <b>Street</b>    <input type="text" id="str" name="street"/><br>

            <b>City</b>      <select name="city">
                      <option>indore</option>
                      <option>bhopal</option>
                      <option>mumbai</option>
                      <option>vadodara</option>
                      <option>dehradun</option>
                      <option>Khargone</option>
                      <option>Dhar</option>
                      <option>Chennai</option>
                      </select>

            <b>State</b>     <select name="state">
                      <option>M.P.</option>
                      <option>U.P.</option>
                      <option>M.H.</option>
                      <option>H.P.</option>
                      <option>Gujarat</option>
                      <option>T.N.</option>
                      </select><br>

            <b>Country</b>   <select name="country">
                      <option>India</option>
                      <option>Nepal</option>
                      <option>China</option>
                      </select><br>
        <b>Property Image</b><input type="file" name="pimg" id="pim"/><br>
        <b>IVRS Number</b>  <input type="text" name="ivrs" id="pim"/><br>
        <b>Board</b>        <input type="text" name="board" id="pim"/><br>
                <input type="submit" value="Save Property"/>
        </pre>
    </form>  
    </font>
    <hr>
    <a href="ownerDashboard.jsp"><font color=blue><center>Home</center></font></a>
    </body>
</html>
