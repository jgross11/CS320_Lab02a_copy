<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- 
TODO: Needs table generation for pending / approved graduates that navigates to 
TODO: Advisor's edit mode of graduate's pending infostate when name in table is clicked
TODO: Needs to store (approximately) a ton of hidden attributes that will be passed
TODO: back and forth between JSP and servlet
 -->
<html>
    <head>
        <title>Personalized Commencement - Advisor Page</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/advisorPageStylesheet.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/siteStylesheet.css" />
    </head>
    <body>
		<script>
			function selectStudent(username) {
			    document.getElementById("studentToView").value = username;
			    document.getElementById("advisorForm").submit();
			}
		</script>
        <div id="documentHeading">
            <b> Personalized Commencement - Advisor Page </b>
        </div>
        <form id = "advisorForm" name="advisorForm" action="${pageContext.servletContext.contextPath}/PCP_AdvisorPage" method="post">
	        <div id="advisorBox">
	            <div id="pictureBox">
	                <img src="${advisor.image}" alt="Advisor Photo" width="150px" height="150px"/> 
	            </div>
	            <div id="infoBox">
	                <table id="infoTable">
	                    <tr>
	                        <td id = "advisorName">
	                        	${advisor.name}
	                        </td>
	                    </tr>
	                    <tr>
	                        <td id = "academicInformation">
	                        	${advisor.academicInformation}
	                        </td>
	                    </tr>
	                    <tr>
	                        <td id = "eventStatus"> 
	                        	<c:if test="${advisor.status == 'true'}">
									<b style="font-size: 30px"> Status: <b style = "color: green;"> All students approved </b> </b>
								</c:if>
								<c:if test="${advisor.status == 'false'}">
									<b style="font-size: 30px"> Status: <b style = "color: red;"> Student changes pending </b> </b>
								</c:if>
	                        </td>
	                    </tr>
	                </table>
	            </div>
	        </div>
	
	            <table style="float: left;">
	                <tr>
	                    <th>Approved Students</th>
	                </tr>
	                
	                    <c:forEach items="${advisor.completedGraduates}" var="cg"> 
  						<tr>
    					<td onclick="selectStudent('${cg.username}')">${cg.name}</td>
 						 </tr>
						</c:forEach>
	                
	            </table>
	             <table style="float: left;">
	                <tr>
	                    <th>Pending Students</th>
	                </tr>
	                
	                    <c:forEach items="${advisor.pendingGraduates}" var="pg"> 
  						<tr>
    					<td onclick="selectStudent('${pg.username}')">${pg.name}</td>
 						 </tr>
						</c:forEach>
	                
	            </table>

	        <!--  sneaky references to fields accessed by the servlet-->
	    	<!--  
	    	NOTE: *every* attribute that is submitted to the JSP needs to be represented here,
	    	NOTE: regardless of whether or not it is visible / modifiable by the user
	    	-->
	    	
	    	<input type="hidden" id = "studentToView" name="studentToView" value="${studentToView}">
	    	<input type="hidden" name="advisorUsername" value="${advisorUsername}">
        </form>
    </body>
</html>