<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="Bean.AlchemyConnector" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="mystyle.css"> 
        <title>AlchemyAPI - Feature Extraction and Image Analysis</title>
    </head>
    <body>
        <div class ="main">
            
           <div> Image Analysis </div>

                <div id="content2">
                    <form action="IServlet" method="GET">
                        <input type="text" name="gurl" size="80">
                        <input type="submit" value="Extract Information">
                    </form> <br/>
					<%
						if (request.getAttribute("face") != null){
							out.println("<h3>" + request.getAttribute("face") + "</h3>");
							out.println("<h3>" + request.getAttribute("age") + "</h3> <br/> <br/>");
							out.println("<h3>" + request.getAttribute("gender") + "</h3> <br/> <br/>");
						}
					%>
                </div>
            </div>
			 
      
    </body>
</html>