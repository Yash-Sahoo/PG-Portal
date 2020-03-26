<html>
    <script>
        function checkAll(){
            
            var v1=document.getElementById("msgto").value;
            var v2=document.getElementById("ms").value;
            var v3=document.getElementById("cod").value;
            if(v3.NaN()){
                alert("Invalid Property Code");
                return false;
            }else if(v1.length===0 && v2.length===0 && v3.length===0){
                alert("Please fill the form completely");
                return false;
            }else{
                return true;
            }
        }
    </script>
    <body>
        <h2><center><font face="bookman old style" color="green"><u><i>Paying Guest Portal</i></u></font></center></h2>
        <h3><font color=red>Inbox</font></h3>
        <hr>
        <a href="MessageSentByCustomer.jsp">Message-Sent</a><br>
        <a href="ShowInboxOfCustomer.jsp">Inbox</a>
        <hr>
        <h3><center>Compose Mail</center></h3>
        <form action="sendMessagebyCust" method="post" onsubmit="return checkAll()">
            <pre>
                For Property Code <input type="number" id="cod" name="pcode" required/><br>
                To      <input type="email" name="to" id="msgto" required/><br>
                Message <textarea rows="4" cols="30" name="msg" id="ms" required></textarea><br>
                    <input type="submit" value="Send"/>
            </pre>
        </form>
        <hr>
    </body>
</html>
