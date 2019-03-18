<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
    <head>
        <title>Personalized Commencement Login</title>
        <style><%@include file="css/loginStylesheet.css"%></style>
        <style><%@include file="css/siteStylesheet.css"%></style>
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
                    <input type="Submit" name="submit" value="Log in">
                </form>
            </div>
        </div>
    </body>
</html>