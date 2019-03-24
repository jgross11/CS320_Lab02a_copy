<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- 
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
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/studentPageStylesheet.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/siteStylesheet.css" />
        
    </head>
    <body>
    	<script>
	        
        </script>
        <div id="documentHeading">
            <b> Personalized Commencement - Student Page </b>
        </div>
  		<form name="studentForm" action="${pageContext.servletContext.contextPath}/PCP_StudentPage" method="post">
  		
  			<!--  STUDENT VIEW PAGE -->
  			<!--  STUDENT VIEW PAGE -->
  		
	  		<c:if test="${mode == 'graduateView'}">
			  	<div id="layoutViewSelectionBox">
			  		<c:if test="${studentStatus == 'true'}">
						<b style="font-size: 23px">Status: <b style = "color: green; font-size: 23px"> Approved </b> </b>
					</c:if>
					<c:if test="${studentStatus == 'false'}">
						<b style="font-size: 23px">Status: <b style = "color: red; font-size: 20px"> Not Approved </b> </b>
					</c:if>
					<hr>
					<input type="submit" name="editInfoButton" value="Edit Information" >
				</div>
			    <div id="studentBox">
			    	<div id="pictureBox">
			        	<img src="${pageContext.servletContext.contextPath}/_view/assets/mocha.png" alt="Student Image" width="250px" height="250px"> 
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
		            
		            
				<img src = "${pageContext.servletContext.contextPath}/_view/assets/mocha2.jpg" alt = "slideshow image 1" width = 172px height = 172px> 
	            <img src = "${pageContext.servletContext.contextPath}/_view/assets/mocha1.jpg" alt = "slideshow image 2" width = 172px height = 172px> 
	            <img src = "${pageContext.servletContext.contextPath}/_view/assets/tippy.jpg" alt = "slideshow image 3" width = 172px height = 172px> 
	            <img src = "${pageContext.servletContext.contextPath}/_view/assets/marble.jpg" alt = "slideshow image 4" width = 172px height = 172px>
		            
		            
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
		   	</c:if>
		   	
		   	<!--  STUDENT EDIT PAGE -->
	  		<!--  STUDENT EDIT PAGE -->
		   	
		   	<c:if test="${mode == 'graduateEdit'}">
		   	<div id="layoutEditSelectionBox">
				<b style="font-size: 23px">Select your layout</b><hr>
				<select name="layoutSelection">
					<option value="static" selected>Static Slideshow</option>
					<option value="dynamic">Dynamic Slideshow</option>
					<option value="video">Video</option>
				</select>
				<hr>
				<!--  
				TODO These two buttons have to be differentiated somehow in order
				TODO to determine whether possible changes are being saved and 
				TODO submitted to the advisor, or if they are being discarded
				 -->
				<input type="submit" value="Save changes">
				<hr>
				<input type="submit" value="Delete changes">
			</div>
	        <div id="studentBox">
	            <div id="pictureBox">
	                <img src="${pageContext.servletContext.contextPath}/_view/assets/mocha.png" alt="Student Image" width="250px" height="250px"> 
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
	            <!-- NOTE: ONLY ONE OF THESE WOULD BE DISPLAYED DEPENDING ON THE GRADUATE'S INFOSTATE TYPE-->
	            
				<h3> NOTE: ONLY ONE OF THESE WILL BE DISPLAYED DEPENDING ON THE GRADUATE'S INFOSTATE TYPE</h3>
	            <!-- STATIC SLIDESHOW -->
				<h3> STATIC SLIDESHOW EXAMPLE </h3>
				<img src = "${pageContext.servletContext.contextPath}/_view/assets/mocha2.jpg" alt = "slideshow image 1" width = 170px height = 170px> 
	            <img src = "${pageContext.servletContext.contextPath}/_view/assets/mocha1.jpg" alt = "slideshow image 2" width = 170px height = 170px> 
	            <img src = "${pageContext.servletContext.contextPath}/_view/assets/tippy.jpg" alt = "slideshow image 3" width = 170px height = 170px> 
	            <img src = "${pageContext.servletContext.contextPath}/_view/assets/marble.jpg" alt = "slideshow image 4" width = 170px height = 170px>
				
	            <p> Upload leftmost image - display dimensions: 170px x 170px</p>
	            <input type="file" name="file1" accept="image/*">
				<p> Upload center-left image - display dimensions: 170px x 170px</p>
				<input type="file" name="file2" accept="image/*">
				<p> Upload center-right image - display dimensions: 170px x 170px</p>
				<input type="file" name="file3" accept="image/*">
				<p> Upload rightmost image - display dimensions: 170px x 170px</p>
				<input type="file" name="file4" accept="image/*">
	            
	            <!-- 'DYNAMIC' SLIDESHOW -->
	            <h3> DYNAMIC SLIDESHOW EXAMPLE </h3>
				<img src = "${pageContext.servletContext.contextPath}/_view/assets/mocha2.jpg" alt = "slideshow image 1" width = 410px height = 410px> 
	            <img src = "${pageContext.servletContext.contextPath}/_view/assets/mocha1.jpg" alt = "slideshow image 2" width = 70px height = 70px> 
	            <img src = "${pageContext.servletContext.contextPath}/_view/assets/tippy.jpg" alt = "slideshow image 3" width = 70px height = 70px> 
	            <img src = "${pageContext.servletContext.contextPath}/_view/assets/marble.jpg" alt = "slideshow image 4" width = 70px height = 70px>
	            <p> Upload leftmost image - small display dimensions: 70px x 70px | large display dimensions: 410px x 410px</p>
	            <input type="file" name="file1" accept="image/*">
				<p> Upload center-left image - small display dimensions: 70px x 70px | large display dimensions: 410px x 410px</p>
				<input type="file" name="file2" accept="image/*">
				<p> Upload center-right image - small display dimensions: 70px x 70px | large display dimensions: 410px x 410px</p>
				<input type="file" name="file3" accept="image/*">
				<p> Upload rightmost image - small display dimensions: 70px x 70px | large display dimensions: 410px x 410px</p>
				<input type="file" name="file4" accept="image/*">
	            
	            
	            <!-- VIDEO -->
				<h3> VIDEO EXAMPLE </h3>
	            <video width = 680px height = 680px controls>       <source src = "${pageContext.servletContext.contextPath}/_view/assets/tippy.mp4" type = "video/mp4">
	            </video>
				<p> Upload video - optimal length: &lt; 6 seconds</p>
				<input type="file" name="file1" accept="video/*">
	            
	            
	        </div>
		   	</c:if>
	    	
	    	<!--  ADVISOR VIEW PAGE -->
	    	<!--  ADVISOR VIEW PAGE -->
	    	<!--  TODO: ADD ADVISOR VIEW HTML WITHIN THE IF STATEMENT -->
	    	<!--  TODO: ADD ADVISOR VIEW HTML WITHIN THE IF STATEMENT -->
	    	
	    	<c:if test="${mode=='advisorView'}">
	    	
	    	</c:if>
	    	
	    	<!--  ADVISOR EDIT PAGE -->
	    	<!--  ADVISOR EDIT PAGE -->
	    	<!--  TODO: ADD ADVISOR EDIT HTML WITHIN THE IF STATEMENT -->
	    	<!--  TODO: ADD ADVISOR EDIT HTML WITHIN THE IF STATEMENT -->
	    	<c:if test="${mode=='advisorEdit'}">
	    	
	    	</c:if>
	    	
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
	    	<input type="hidden" name="studentStatus" value="${studentStatus}">
	    	<input type="hidden" id="saveChanges "name="saveChanges" value="0">
	    	<input type="hidden" id="discardChanges" name="discardChanges" value="0">
		</form>
    </body>
</html>