<!DOCTYPE html>
<!--
Authors: 
Alyssa Grove | agrove9@ycp.edu
Dennis Chism | dchism@ycp.edu
Josh Gross   | jgross11@ycp.edu
Bill Abram   | wabram@ycp.edu
-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
    <head>
        <title>Personalized Commencement Login</title>

        <style type="text/css">
		#loginBox{
    		float: left;
		}
		#loginBoxText{
    
		}
		#documentHeading{
    		font-size: 200%;
		}
		#error{ 
    		color:darkred;
    		background-color: lightcoral;
    		border-color: darkred;
    		border: solid 3px; 
		}
		</style>
    </head>
    <body>
        <div id="documentHeading">
            <b> Personalized Commencement Login </b>
        </div>
        <div id="loginBox">
            <div id="loginBoxText">
                <form action="${pageContext.servletContext.contextPath}/PCP_Index" method="post">
	                <c:if test="${! empty errorMessage}">
						<div id="error">${errorMessage}</div>
					</c:if>
                    <p>MyYCP Username <br> <input type="text" name="username" value="${model.username}"> </p>
                    <p>MyYCP Password <br> <input type="password" name="password" value="${model.password}"> </p>
                    <input type="Submit" name="submit" value="Log in"></button>
                </form>
            </div>
        </div>
    </body>
</html>