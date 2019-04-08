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
        <form name="adminForm" action="${pageContext.servletContext.contextPath}/PCP_AdminPage" method="post">
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
	    	<button type="submit" name = "submit"> Event Page</button>  
	        <!--  sneaky references to fields accessed by the servlet--> 
	    	<!--  
	    	NOTE: *every* attribute that is submitted to the JSP needs to be represented here,
	    	NOTE: regardless of whether or not it is visible / modifiable by the user  
	    	-->
	
	    	<input type="hidden" name="adminName" value="${adminName}">
	    	<input type="hidden" name="eventDate" value="${eventDate}">
	    	<input type="hidden" name="adminUsername" value="${adminUsername}"> 
=======
TODO: Needs table generation for pending / approved graduates that navigates to 
TODO: Advisor's edit mode of graduate's pending infostate when name in table is clicked

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
        <form name="adminForm" action="${pageContext.servletContext.contextPath}/PCP_AdminPage" method="post">
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
	                        <td id = "academicInformation">
	                        	${academicInformation}
	                        </td>
	                    </tr>
	                    <tr>
	                        <td id = "eventStatus"> 
	                        	<c:if test="${advisorStatus == 'true'}">
									<b style="font-size: 30px"> Status: <b style = "color: green;"> All students approved </b> </b>
								</c:if>
								<c:if test="${advisorStatus == 'false'}">
									<b style="font-size: 30px"> Status: <b style = "color: red;"> Student changes pending </b> </b>
								</c:if>
	                        </td>
	                    </tr>
	                </table>
	            </div>
	        </div>
	        <div id="studentStatusBox">
	            <table style="width:100%">
	                <tr>
	                    <th>Approved Students</th>
	                    <th>Pending Students </th>
	                </tr>
	                <tr><!-- STUDENT TABLE GENERATION  -->
	                	
	                	<!-- 
	                	TODO: iterate through the Advisor's Graduate array
	                	TODO: and generate a cell for every dis/approved Graduate
	                	TODO: and assign a click event that loads that Graduate's
	                	TODO: new InfoState information in Advisor Edit mode
	                	
	                	 -->
	                </tr>
	                
	                <!-- 
	                The following will be removed once table generation is functional, but
	                it can be uncommented to preview what a generated table will look like
	                 -->
	                 
	                <!-- 
	                <tr>
	                    <td> Dennis Chism</td>
	                    <td> </td>
	                </tr>
	                <tr>
	                    <td> Alyssa Grove</td>
	                    <td> </td>
	                </tr>
	                -->
	            </table>
	        </div>
	        <!--  sneaky references to fields accessed by the servlet-->
	    	<!--  
	    	NOTE: *every* attribute that is submitted to the JSP needs to be represented here,
	    	NOTE: regardless of whether or not it is visible / modifiable by the user
	    	-->
	    	<input type="submit" name="Event Page" href="${pageContext.servletContext.contextPath}/PCP_EventPage">
	    	<input type="hidden" name="adminName" value="${adminName}">
	    	<input type="hidden" name="academicInformation" value="${academicInformation}">
	    	<input type="hidden" name="advisorStatus" value="${advisorStatus}">
	    	<input type="hidden" name="studentToView" value="${studentToView}">
	    	<input type="hidden" name="adminUsername" value="${adminUsername}">
>>>>>>> refs/heads/Local_Project
        </form>
    </body>
</html>