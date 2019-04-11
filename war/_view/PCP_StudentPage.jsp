<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- 
TODOs go here
-->

<html>
    <head>
  		<script>
	  		function setGraduateSaveOption(choice){
			    if(choice == 1){
			    	document.getElementById("studentSaveChanges").value = "true";
			    	/*
			    	TODO: set values of student images / videos (*dependent on current layout*)
			    	TODO: input containers to path of image / video after uploading to database
			    	*/
			    }
			    else{
			    	document.getElementById("studentSaveChanges").value = "false";
			    }
			    submitForm();
			}
			function setAdvisorApprovalOption(choice){
			    if(choice == 1){
			    	document.getElementById("advisorSaveChanges").value = "true";
			    }
			    else{
			    	document.getElementById("advisorSaveChanges").value = "false";
			    }
			    submitForm();
			}
			
			function navToStudentList(){
				document.getElementById("advisorGoBack").value = "true";
				submitForm();
			}
			
			function navToEditMode(){
				document.getElementById("advisorSwitch").value = "true";
				submitForm();
			}
			
			function changeGraduateLayout(mode){
				var layoutOBJ = document.getElementById("layoutSelection");
				var layoutChoice = layoutOBJ.options[layoutOBJ.selectedIndex].text.toLowerCase();
				document.getElementById("graduateLayout").value = layoutChoice;
				document.getElementById("graduateLayoutChange").value = true;
				console.log(layoutChoice);
				submitForm();
			}
			
			function submitMedia(img, index, type){ 
				    var reader = new FileReader();
				    if(type == "photo"){
					    reader.onload = function(){
					      var output = document.getElementById("img"+index);
					      output.src = reader.result;
						  console.log(output);
					    };
					    reader.readAsDataURL(event.target.files[0]);
				    }
				    else if(type == "video"){
				    	reader.onload = function(){
						      var output = document.getElementById("vid"+index);
						      output.src = reader.result;
							  console.log(output.src);
						};
						reader.readAsDataURL(event.target.files[0]);
				    }
				    else if(type == "name"){
				    	reader.onload = function(){
						      var output = document.getElementById("namePronunciation");
						      output.src = reader.result;
							  console.log(output.src);
						};
						reader.readAsDataURL(event.target.files[0]);
					}
				    else{
				    	console.log("Bad function call");
				    }
				//TODO: Ignore this part for now - may or may not be utilized later
				//document.getElementById("img"+index).src = URL.createObjectURL(event.target.files[0]);
				//console.log(document.getElementById("img"+index).src);
			}
			
			function submitForm(){
				document.getElementById("studentForm").submit();
			}
		</script>
    	<!--  Conditional Page Title -->
    	<c:if test="${mode == 'studentView'}">
    		<title>Personalized Commencement - Student View Page</title>
    	</c:if>
       	<c:if test="${mode == 'studentEdit'}">
    		<title>Personalized Commencement - Student Edit Page</title>
    	</c:if>
       	<c:if test="${mode == 'advisorView'}">
    		<title>Personalized Commencement - Advisor View Page</title>
    	</c:if>
       	<c:if test="${mode == 'advisorEdit'}">
    		<title>Personalized Commencement - Advisor Edit Page</title>
    	</c:if> 	 	 	
  
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/studentPageStylesheet.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/siteStylesheet.css" />
        
    </head>
    <body>
        <div id="documentHeading">
	        <c:if test="${mode == 'graduateView'}">
	    		 <b> Personalized Commencement -  Student View Page </b>
	    	</c:if>
	       	<c:if test="${mode == 'graduateEdit'}">
	    		 <b> Personalized Commencement - Student Edit Page </b>
	    	</c:if>
	       	<c:if test="${mode == 'advisorView'}">
	    		 <b> Personalized Commencement - Advisor View Page </b>
	    	</c:if>
	       	<c:if test="${mode == 'advisorEdit'}">
	    		 <b> Personalized Commencement - Advisor Edit Page </b>
	    	</c:if> 	 	 	
        </div>
  		<form id = "studentForm" name="studentForm" action="${pageContext.servletContext.contextPath}/PCP_StudentPage" method="post">
  		
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
			        	<img src="${pageContext.servletContext.contextPath}/_view/assets/kobe.jpg" alt="Student Image" width="250px" height="250px"> 
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
					<img src = "${pageContext.servletContext.contextPath}/_view/assets/mocha2.jpg" alt = "slideshow image 1" width = 172px height = 172px> 
		            <img src = "${pageContext.servletContext.contextPath}/_view/assets/mocha1.jpg" alt = "slideshow image 2" width = 172px height = 172px> 
		            <img src = "${pageContext.servletContext.contextPath}/_view/assets/tippy.jpg" alt = "slideshow image 3" width = 172px height = 172px> 
		            <img src = "${pageContext.servletContext.contextPath}/_view/assets/marble.jpg" alt = "slideshow image 4" width = 172px height = 172px>
		            -->
		            
		            <!-- 'DYNAMIC' SLIDESHOW -->
		            
					<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		          	<script>
		           		var index = 1;	
		           		function start(){
		           			setTimeout(changeActiveImage, 2000, index);
		           			if(index < 4){
		           				index++
		           			}
		           			else{
		           				index = 1;
		           			}
		           		}
		           		function changeActiveImage(index){
		           			var imageString = "img" + index;
		           			console.log(imageString);
		           			var image = document.getElementById(imageString)
		           			// TODO: make this more elegant
		           			// TODO: make this more elegant
		           			if(index == 1){
		           				document.getElementById("img1").width = 400;
		           				document.getElementById("img1").height = 400;
		           				document.getElementById("img2").width = 0;
		           				document.getElementById("img2").height = 0;
		           				document.getElementById("img3").width = 0;
		           				document.getElementById("img3").height = 0;
		           				document.getElementById("img4").width = 0;
		           				document.getElementById("img4").height = 0;
		           			}
		           			if(index == 2){
		           				document.getElementById("img1").width = 0;
		           				document.getElementById("img1").height = 0;
		           				document.getElementById("img2").width = 400;
		           				document.getElementById("img2").height = 400;
		           				document.getElementById("img3").width = 0;
		           				document.getElementById("img3").height = 0;
		           				document.getElementById("img4").width = 0;
		           				document.getElementById("img4").height = 0;
		           			}
		           			if(index == 3){
		           				document.getElementById("img1").width = 0;
		           				document.getElementById("img1").height = 0;
		           				document.getElementById("img2").width = 0;
		           				document.getElementById("img2").height = 0;
		           				document.getElementById("img3").width = 400;
		           				document.getElementById("img3").height = 400;
		           				document.getElementById("img4").width = 0;
		           				document.getElementById("img4").height = 0;
		           			}
		           			if(index == 4){
		           				document.getElementById("img1").width = 0;
		           				document.getElementById("img1").height = 0;
		           				document.getElementById("img2").width = 0;
		           				document.getElementById("img2").height = 0;
		           				document.getElementById("img3").width = 0;
		           				document.getElementById("img3").height = 0;
		           				document.getElementById("img4").width = 400;
		           				document.getElementById("img4").height = 400;
		           			}
		           			//image.width = 200;
		           			//image.height = 200;
		           			start();
		           		}
		           		start();
		           </script>
					<img id = "img1" src = "${pageContext.servletContext.contextPath}/_view/assets/mocha2.jpg" alt = "slideshow image 1" width = 200px height = 200px> 
		            <img id = "img2" src = "${pageContext.servletContext.contextPath}/_view/assets/mocha1.jpg" alt = "slideshow image 2" width = 50px height = 50px> 
		            <img id = "img3" src = "${pageContext.servletContext.contextPath}/_view/assets/tippy.jpg" alt = "slideshow image 3" width = 50px height = 50px> 
		            <img id = "img4" src = "${pageContext.servletContext.contextPath}/_view/assets/marble.jpg" alt = "slideshow image 4" width = 50px height = 50px>
		            
		            
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
					<select id="layoutSelection" name="layoutSelection" onchange="changeGraduateLayout(this.selectedValue)">
						<option value="static">Select an Option</option>
						<option value="static">Static Slideshow</option>
						<option value="dynamic">Dynamic Slideshow</option>
						<option value="video">Video</option>
					</select>
					<hr>

					<input type="button" value="Save changes" onclick="setGraduateSaveOption(1)">
					<hr>
					<input type="button" value="Delete changes" onclick="setGraduateSaveOption(0)">
				</div>
		        <div id="studentBox">
		            <div id="pictureBox">
		                <img id = "img0" src="${pageContext.servletContext.contextPath}/_view/assets/mocha.png" alt="Student Image" width="250px" height="250px"> 
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
		        <p> Upload student display image - display dimensions: 250px x 250px</p>
			    <input type="file" name="file0" accept="image/*" onchange="submitMedia(event, 0, 'photo')">
				<p> Upload student name pronunciation audio file</p>
			    <input type="file" name="audio0" accept="audio/*" onchange="submitMedia(event, 0, 'name')">
				<audio id = "namePronunciation"controls >
					<source>
				</audio>
		        <div id = "mediaBox">
		        
		            <!-- STATIC SLIDESHOW -->
		            <c:if test="${graduateLayout == 'static slideshow'}">
						<h3> STATIC SLIDESHOW EXAMPLE </h3>
						<img id = "img1" src = "${pageContext.servletContext.contextPath}/_view/assets/mocha2.jpg" alt = "slideshow image 1" width = 170px height = 170px> 
			            <img id = "img2" src = "${pageContext.servletContext.contextPath}/_view/assets/mocha1.jpg" alt = "slideshow image 2" width = 170px height = 170px> 
			            <img id = "img3" src = "${pageContext.servletContext.contextPath}/_view/assets/tippy.jpg" alt = "slideshow image 3" width = 170px height = 170px> 
			            <img id = "img4" src = "${pageContext.servletContext.contextPath}/_view/assets/marble.jpg" alt = "slideshow image 4" width = 170px height = 170px>
						
			            <p> Upload 1st image - display dimensions: 170px x 170px</p>
			            <input type="file" name="file1" accept="image/*" onchange="submitMedia(event, 1, 'photo')">
						<p> Upload 2nd - display dimensions: 170px x 170px</p>
						<input type="file" name="file2" accept="image/*" onchange="submitMedia(event, 2, 'photo')">
						<p> Upload 3rd image - display dimensions: 170px x 170px</p>
						<input type="file" name="file3" accept="image/*" onchange="submitMedia(event, 3, 'photo')">
						<p> Upload 4th image - display dimensions: 170px x 170px</p>
						<input type="file" name="file4" accept="image/*" onchange="submitMedia(event, 4, 'photo')">
		            </c:if>
		            
		            <!-- 'DYNAMIC' SLIDESHOW -->
		            <c:if test="${graduateLayout == 'dynamic slideshow'}">
			            <h3> DYNAMIC SLIDESHOW EXAMPLE </h3>
						<img id = "img1" src = "${pageContext.servletContext.contextPath}/_view/assets/mocha2.jpg" alt = "slideshow image 1" width = 410px height = 410px> 
			            <img id = "img2" src = "${pageContext.servletContext.contextPath}/_view/assets/mocha1.jpg" alt = "slideshow image 2" width = 70px height = 70px> 
			            <img id = "img3" src = "${pageContext.servletContext.contextPath}/_view/assets/tippy.jpg" alt = "slideshow image 3" width = 70px height = 70px> 
			            <img id = "img4" src = "${pageContext.servletContext.contextPath}/_view/assets/marble.jpg" alt = "slideshow image 4" width = 70px height = 70px>
			            <p> Upload leftmost image - small display dimensions: 70px x 70px | large display dimensions: 410px x 410px</p>
			            <input type="file" name="file1" accept="image/*" onchange="submitMedia(event, 1, 'photo')">
						<p> Upload center-left image - small display dimensions: 70px x 70px | large display dimensions: 410px x 410px</p>
						<input type="file" name="file2" accept="image/*" onchange="submitMedia(event, 2, 'photo')">
						<p> Upload center-right image - small display dimensions: 70px x 70px | large display dimensions: 410px x 410px</p>
						<input type="file" name="file3" accept="image/*" onchange="submitMedia(event, 3, 'photo')">
						<p> Upload rightmost image - small display dimensions: 70px x 70px | large display dimensions: 410px x 410px</p>
						<input type="file" name="file4" accept="image/*" onchange="submitMedia(event, 4, 'photo')">
		            </c:if>
		            
		            <!-- VIDEO -->
		            <c:if test="${graduateLayout == 'video'}">
						<h3> VIDEO EXAMPLE </h3>
			            <video id = "vid1" width = 680px height = 680px controls>
			            	<source src = "${pageContext.servletContext.contextPath}/_view/assets/tippy.mp4" type = "video/mp4">
			            </video>
						<p> Upload video - optimal length: &lt; 6 seconds</p>
						<input type="file" name="file1" accept="video/*" onchange="submitMedia(event, 1, 'video')">
		            </c:if>
		        </div>
		   	</c:if>
	    	
	    	<!--  ADVISOR VIEW PAGE -->
	    	<!--  ADVISOR VIEW PAGE -->
	    	
	    	<c:if test="${mode=='advisorView'}">
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
		            
		            <!-- 
		  		<img src = "${pageContext.servletContext.contextPath}/_view/assets/mocha2.jpg" alt = "slideshow image 1" width = 172px height = 172px> 
	            <img src = "${pageContext.servletContext.contextPath}/_view/assets/mocha1.jpg" alt = "slideshow image 2" width = 172px height = 172px> 
	            <img src = "${pageContext.servletContext.contextPath}/_view/assets/tippy.jpg" alt = "slideshow image 3" width = 172px height = 172px> 
	            <img src = "${pageContext.servletContext.contextPath}/_view/assets/marble.jpg" alt = "slideshow image 4" width = 172px height = 172px>
		            
		            -->
		            
		            <!-- 'DYNAMIC' SLIDESHOW -->
		            
		            <!--
		            <img src = "assets/mocha2.jpg" alt = "slideshow image 1" width = 200px height = 200px> 
		            <img src = "assets/mocha1.jpg" alt = "slideshow image 2" width = 50px height = 50px> 
		            <img src = "assets/tippy.jpg" alt = "slideshow image 3" width = 50px height = 50px> 
		            <img src = "assets/marble.jpg" alt = "slideshow image 4" width = 50px height = 50px>
		            --> 
		            
		            <!-- VIDEO -->
		            
		           
		            <video width = 325px height = 325px controls>       <source src = "${pageContext.servletContext.contextPath}/_view/assets/tippy.mp4" type = "video/mp4">
		            </video>
		            <br><br>
		           <input type="button" value="Back to Student List" onclick="navToStudentList()"/>
		           <input type="button" value="Confirm / Deny Student Information" onclick="navToEditMode()"/>
		    	</div>
	    	</c:if>
	    	
	    	<!--  ADVISOR EDIT PAGE -->
	    	<!--  ADVISOR EDIT PAGE -->
	    	<c:if test="${mode=='advisorEdit'}">
			  	
	    		<div id="advisorEditBox">
					<b style="font-size: 18px">Approve</b><hr>
  					<input type="checkbox" name="extraInfoCB" value="extraInfoCB"> Extra Info <hr>
  					<input type="checkbox" name="prounciationCB" value="prounciationCB"> Audio <hr>
  					<input type="checkbox" name="extraInfoCB" value="extraInfoCB"> Video <hr>
					<input type="button" value="Save Approvals" onclick="setAdvisorApprovalOption(1)">
					<input type="button" value="Discard Approvals" onclick="setAdvisorApprovalOption(0)">
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
		            
		            <!-- 
				<img src = "${pageContext.servletContext.contextPath}/_view/assets/mocha2.jpg" alt = "slideshow image 1" width = 172px height = 172px> 
	            <img src = "${pageContext.servletContext.contextPath}/_view/assets/mocha1.jpg" alt = "slideshow image 2" width = 172px height = 172px> 
	            <img src = "${pageContext.servletContext.contextPath}/_view/assets/tippy.jpg" alt = "slideshow image 3" width = 172px height = 172px> 
	            <img src = "${pageContext.servletContext.contextPath}/_view/assets/marble.jpg" alt = "slideshow image 4" width = 172px height = 172px>
		            
		            -->
		            
		            <!-- 'DYNAMIC' SLIDESHOW -->
		            
		            <!--
		            <img src = "assets/mocha2.jpg" alt = "slideshow image 1" width = 200px height = 200px> 
		            <img src = "assets/mocha1.jpg" alt = "slideshow image 2" width = 50px height = 50px> 
		            <img src = "assets/tippy.jpg" alt = "slideshow image 3" width = 50px height = 50px> 
		            <img src = "assets/marble.jpg" alt = "slideshow image 4" width = 50px height = 50px>
		            --> 
		            
		            <!-- VIDEO -->
		            
		            
		            <video width = 325px height = 325px controls>       <source src = "${pageContext.servletContext.contextPath}/_view/assets/tippy.mp4" type = "video/mp4">
		            </video>
		            
		           
		    	</div>
	    	</c:if>
	    	
	    	<!--  sneaky references to fields accessed by the servlet-->
	    	<!--  
	    	NOTE: *every* attribute that is submitted to the JSP needs to be represented here,
	    	NOTE: regardless of whether or not it is visible / modifiable by the user
	    	-->
	    	<!-- General Attributes-->
	    	<input type="hidden" name="mode" value="${mode}">
	    	
	    	<!-- Graduate Attributes -->
	    	<input type="hidden" name="studentName" value="${studentName}">
	    	<input type="hidden" name="studentAcademicInformation" value="${studentAcademicInformation}">
	    	<input type="hidden" name="studentExtraInformation" value="${studentExtraInformation}">
	    	<input type="hidden" name="toggleText" value="${toggleText}">
	    	<input type="hidden" name="studentStatus" value="${studentStatus}">
	    	<input type="hidden" id = "graduateLayout"name="graduateLayout" value="${graduateLayout}">
	    	<input type="hidden" id = "graduateLayoutChange"name="graduateLayoutChange" value="${graduateLayout}">
	    	<input type="hidden" id="studentSaveChanges"name="studentSaveChanges" value="${studentSaveChanges}">
	    	
	    	<!-- Advisor Attributes -->
	    	<input type="hidden" id="advisorUsername" name="advisorUsername" value="${advisorUsername}">
	    	<input type="hidden" id="advisorSwitch" name="advisorSwitch" value="${advisorSwitch}">
	    	<input type="hidden" id="advisorGoBack" name="advisorGoBack" value="${advisorGoBack}">
	    	<input type="hidden" id="advisorSaveChanges"name="advisorSaveChanges" value="${advisorSaveChanges}">
	    	<input type="hidden" id="navToList"name="navToList" value="${navToList}">

		</form>
    </body>
</html>