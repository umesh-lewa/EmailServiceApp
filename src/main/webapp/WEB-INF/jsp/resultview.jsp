<%@ page import="java.util.*,com.emailserviceapp.springboot.service.ResultDAO,com.emailserviceapp.springboot.service.EmailResults" %>  
<%  
String spageid=request.getParameter("page");  
int pageid=Integer.parseInt(spageid);  
int total=5;  
if(pageid==1){}  
else{  
    pageid=pageid-1;  
    pageid=pageid*total+1;  
}  
List<EmailResults> list=ResultDAO.getRecords(pageid,total);  
  
out.print("<h1>Page No: "+spageid+"</h1>");  
out.print("<table border='1' cellpadding='4' width='60%'>");  
out.print("<tr><th>ToAddress</th><th>SentTime</th><th>SentStatus</th>");  
for(EmailResults e:list){  
    out.print("<tr><td>"+e.getToAddress()+"</td><td>"+e.getSentTime()+"</td><td>"+e.isSentStatus()+"</td></tr>");  
}  
out.print("</table>");  
%>  
<a href="resultview?page=1">1</a>  
<a href="resultview?page=2">2</a>
<a href="resultview?page=3">3</a>
<a href="resultview?page=4">4</a>
<a href="resultview?page=5">5</a>
<a href="resultview?page=6">6</a>
<a href="resultview?page=7">7</a> 
<a href="resultview?page=8">8</a> 
<a href="resultview?page=9">9</a> 
<a href="resultview?page=10">10</a>  