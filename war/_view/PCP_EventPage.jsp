<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
    <head>
        <title>Personalized Commencement - Event Page</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/eventPageStylesheet.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/siteStylesheet.css" />
    </head>
    <body>
        <div id="documentHeading">
            <b> Personalized Commencement - Event Page </b>
        </div>
		<form name="eventForm" action="${pageContext.servletContext.contextPath}/PCP_EventPage" method="post">
		
		<div style="float: left"> 		
			<div id="studentBox">
				<div id="pictureBox">
				 	<img src="${pageContext.servletContext.contextPath}/_view/assets/mocha.png" alt="Student Image" width="250px" height="250px"> 
				 </div>
				 <div id="infoBox">
				    <table id="infoTable">
				    	<tr>
				     		<td id = "studentName">${studentName} Dennis Chism</td>
				    	 </tr>
				     	<tr>
				      		<td id = "academicInformation">${studentAcademicInformation} Major in Testing</td>
				    	 </tr>
				    	 <tr>
				     		<td id = "extraInformation">${studentExtraInformation} excels at Testology</td>
				     	</tr>
				    </table>
				    </div>
				    </div>
				     
				    <br><br>
				    
			<div id = "mediaBox">
			            <!-- Uncomment one of these options to display-->
			            
			            <!-- STATIC SLIDESHOW -->
			            
			            
					<img src = "${pageContext.servletContext.contextPath}/_view/assets/mocha2.jpg" alt = "slideshow image 1" width = 172px height = 172px> 
		            <img src = "${pageContext.servletContext.contextPath}/_view/assets/mocha1.jpg" alt = "slideshow image 2" width = 172px height = 172px> 
		            <img src = "${pageContext.servletContext.contextPath}/_view/assets/tippy.jpg" alt = "slideshow image 3" width = 172px height = 172px> 
		            <img src = "${pageContext.servletContext.contextPath}/_view/assets/marble.jpg" alt = "slideshow image 4" width = 172px height = 172px>
		       </div>
		</div>       
	   	<div style="float: right">
		   	<div id="studentBox2">
				<div id="pictureBox">
				 	<img src="${pageContext.servletContext.contextPath}/_view/assets/mocha.png" alt="Student Image" width="250px" height="250px"> 
				 </div>
				 <div id="infoBox2">
				    <table id="infoTable2">
				    	<tr> 
				     		<td id = "studentName">${studentName} Bill Abram</td>
				    	 </tr>
				     	<tr>
				      		<td id = "academicInformation">${studentAcademicInformation} Major in Testing</td>
				    	 </tr>
				    	 <tr>
				     		<td id = "extraInformation">${studentExtraInformation} excels at Testology</td>
				     	</tr>
				    </table>
				</div>
				
			</div>
		</div>
			     
	            
	            <form> 
	    		<button type="submit" formaction="${pageContext.servletContext.contextPath}/_view/PCP_AdminPage.jsp">End Event</button> 
	    		</form> 
		</form> 
	</body>
</html>		            