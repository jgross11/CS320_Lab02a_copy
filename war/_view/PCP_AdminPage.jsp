<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- 
<<<<<<< HEAD
TODO: Needs to store (approximately) a ton of hidden attributes that will be passed
TODO: back and forth between JSP and servlet
 -->
<html>
    <head>
        <title>Personalized Commencement - Admin Page</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/adminPageStylesheet.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/siteStylesheet.css" />
    </head>
    <body>
        <div id="documentHeading">
            <b> Personalized Commencement - Admin Page </b>
        </div>
        <form id="adminForm" name="adminForm" action="${pageContext.servletContext.contextPath}/PCP_AdminPage" method="post">
	        <div id="adminBox">
	            <div id="pictureBox">
	                <img src="${pageContext.servletContext.contextPath}/_view/assets/mocha.png" alt="Admin Photo" width="150px" height="150px"/> 
	            </div>
	            <div id="infoBox">
	                <table id="infoTable">
	                    <tr>
	                        <td id = "adminName">
	                        	${adminName}
	                        </td>
	                    </tr>
	                    <tr>
	                        <td id = "deadlineInformation">
	                        	 Deadline date: <b style="color: green"> ${eventDate} </b>
	                        </td> 
	                    </tr>
	                    <tr>
	                        <td id = "dateChange"> 
	                        	Change deadline date: <input type="date" name = "changeDateButton"/>
	                        </td>
	                    </tr>
	                </table>
	            </div>
	        </div>
	        <p></p>
	    	<input type="button" name = "input" onclick="goToEventPage()" value="Event page">  
	    	<script>
	    		function goToEventPage(){
	    			document.getElementById("startEvent").value = "true";
	    			document.getElementById("adminForm").submit();
	    		}
	    	</script>
	        <!--  sneaky references to fields accessed by the servlet--> 
	    	<!--  
	    	NOTE: *every* attribute that is submitted to the JSP needs to be represented here,
	    	NOTE: regardless of whether or not it is visible / modifiable by the user  
	    	-->
	
	    	<input type="hidden" name="eventDate" value="${eventDate}">
	    	<input type="hidden" name="adminUsername" value="${adminUsername}"> 
	    	<input type="hidden" name="adminName" value="${adminName}">
	    	<input type="hidden" name="academicInformation" value="${academicInformation}">
	    	<input type="hidden" name="adminUsername" value="${adminUsername}">
	    	<input type="hidden" id="startEvent" name="startEvent" value="${startEvent}">
        </form>
    </body>
</html>