<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- 
TODO: Develop a version of StudentPage.html (/war/_view/html) that would
TODO: represent the editing state of the page (change text to textboxes
TODO: images to image uploaders, etc). Then, change this jsp to determine  
TODO: which version of the StudentPage needs to be displayed

TODO: Develop a version of StudentPage.html (/war/_view/html) that would
TODO: represent the viewing state of the page from the advisor's standpoint.
TODO: Then, change this jsp to determine which version of the StudentPage 
TODO: needs to be displayed

TODO: Develop a version of StudentPage.html (/war/_view/html) that would
TODO: represent the editing state of the page from the advisor's standpoint
TODO: ex. add checkboxes indicating status of individual components
TODO: Then, change this jsp to determine which version of the StudentPage 
TODO: needs to be displayed

-->

<html>
    <head>
        <title>Personalized Commencement - Student Page</title>
        <!--  
        TODO figure out how to reference other files from within
        TODO a jsp file - this will be useful later on for
        TODO css styling and loading images/videos/etc.
         -->
        <style><%@include file="css/studentPageStylesheet.css"%></style>
        <style><%@include file="css/siteStylesheet.css"%></style>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}\war\_view\css\siteStylesheet.css" />
    </head>
    <body>
        <div id="documentHeading">
            <b> Personalized Commencement - Student Page </b>
        </div>
  		<form action="${pageContext.servletContext.contextPath}/PCP_StudentPage" method="post">
	       	<div id="viewToggleButton">
		       	<c:if test="${mode == 'view'}">
					<input type="Submit" name="toggle" value="Edit Information">
				</c:if>
				<c:if test="${mode == 'edit'}">
					<input type="Submit" name="toggle" value="View Information">
				</c:if>
	        </div>  		
	        <div id="studentBox">
	           	<div id="pictureBox">
	               <img src="assets/mocha.png" alt="Student Image" width="250px" height="250px"> 
	          	</div>
	           	<div id="infoBox">
	                <table id="infoTable">
	                	<tr>
	                    	<td id = "studentName">${studentName}</td>
	                    </tr>
	                    <tr>
	                        <td id = "academicInformation">${studentAcademicInformation}</td>
	                    </tr>
	                    <tr>
	                        <td id = "extraInformation">${studentExtraInformation}</td>
	                    </tr>
	                </table>
	            </div>
	        </div>
	        <br><br>
	        <div id = "mediaBox">
	            <!-- Uncomment one of these options to display-->
	            
	            <!-- STATIC SLIDESHOW -->
	            
	            <!--
	            <img src = "assets/mocha2.jpg" alt = "slideshow image 1" width = 170px height = 170px> 
	            <img src = "assets/mocha1.jpg" alt = "slideshow image 2" width = 170px height = 170px> 
	            <img src = "assets/tippy.jpg" alt = "slideshow image 3" width = 170px height = 170px> 
	            <img src = "assets/marble.jpg" alt = "slideshow image 4" width = 170px height = 170px>
	            -->
	            
	            <!-- 'DYNAMIC' SLIDESHOW -->
	            
	            <!--
	            <img src = "assets/mocha2.jpg" alt = "slideshow image 1" width = 200px height = 200px> 
	            <img src = "assets/mocha1.jpg" alt = "slideshow image 2" width = 50px height = 50px> 
	            <img src = "assets/tippy.jpg" alt = "slideshow image 3" width = 50px height = 50px> 
	            <img src = "assets/marble.jpg" alt = "slideshow image 4" width = 50px height = 50px>
	            --> 
	            
	            <!-- VIDEO -->
	            
	            <!--
	            <video width = 325px height = 325px controls>       <source src = "assets/tippy.mp4" type = "video/mp4">
	            </video>
	            
	            -->
	    	</div>
	    	
	    	<!--  sneaky references to fields accessed by the servlet-->
	    	<!--  
	    	NOTE: *every* attribute that is submitted to the JSP needs to be represented here,
	    	NOTE: regardless of whether or not it is visible / modifiable by the user
	    	-->
	    	<input type="hidden" name="mode" value="${mode}">
	    	<input type="hidden" name="studentName" value="${studentName}">
	    	<input type="hidden" name="studentAcademicInformation" value="${studentAcademicInformation}">
	    	<input type="hidden" name="studentExtraInformation" value="${studentExtraInformation}">
	    	<input type="hidden" name="toggleText" value="${toggleText}">
		</form>
    </body>
</html>