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
			    	submitMedia(null, null, "extraInformation");
			    }
			    else{
			    	document.getElementById("studentSaveChanges").value = "false";
			    }
			    submitForm();
			}
			function setAdvisorApprovalOption(choice){
			    if(choice == 1){
			    	document.getElementById("advisorSaveChanges").value = "true";
			    	x = 0;
			    	checkboxes = [];
			    	checkboxes.push(document.getElementsByName("extraInfoCB")[0].checked);
			    	checkboxes.push(document.getElementsByName("pronunciationCB")[0].checked);
			    	checkboxes.push(document.getElementsByName("profilePictureCB")[0].checked);
			    	checkboxes.push(document.getElementsByName("slideshow1CB")[0].checked);
			    	checkboxes.push(document.getElementsByName("slideshow2CB")[0].checked);
			    	checkboxes.push(document.getElementsByName("slideshow3CB")[0].checked);
			    	checkboxes.push(document.getElementsByName("slideshow4CB")[0].checked);
			    	checkboxes.push(document.getElementsByName("videoCB")[0].checked);
			    	document.getElementById("studentNewInfo").value = checkboxes;
			    }
			    else{
			    	document.getElementById("advisorSaveChanges").value = "false";
			    }
			    submitForm();
			}
			
			function navToStudentList(){
				document.getElementById("advisorGoBack").value = "true";
				document.getElementById("advisorSwitch").value = "false";
				submitForm();
			}
			
			function navToEditMode(){
				document.getElementById("advisorGoBack").value = "false";
				document.getElementById("advisorSwitch").value = "true";
				submitForm();
			}
			
			function changeGraduateLayout(mode){
				var layoutOBJ = document.getElementById("layoutSelection");
				var layoutChoice = layoutOBJ.options[layoutOBJ.selectedIndex].text.toLowerCase();
				document.getElementById("graduateLayout").value = layoutChoice;
				document.getElementById("graduateLayoutChange").value = true;
				submitForm();
			}
			
			function submitMedia(img, index, type){ 
				    var reader = new FileReader();
				    if(type == "photo"){
					    reader.onload = function(){
					      var output = document.getElementById("img"+index);
					      output.src = reader.result;
					    };
					    reader.readAsDataURL(event.target.files[0]);
				    }
				    else if(type == "video"){
				    	reader.onload = function(){
						      var output = document.getElementById("vid"+index);
						      output.src = reader.result;
						};
						reader.readAsDataURL(event.target.files[0]);
				    }
				    else if(type == "name"){
				    	reader.onload = function(){
						      var output = document.getElementById("namePronunciation");
						      output.src = reader.result;
						};
						reader.readAsDataURL(event.target.files[0]);
					}
					else if(type == "extraInformation"){
						document.getElementById("studentNewInformation").value = document.getElementById("extraInformationTB").value;
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
  		<form id = "studentForm" name="studentForm" action="${pageContext.servletContext.contextPath}/PCP_StudentPage" enctype="multipart/form-data" method="post">
  		
  			<!--  STUDENT VIEW PAGE -->
  			<!--  STUDENT VIEW PAGE -->
  		
	  		<c:if test="${mode == 'graduateView'}">
			  	<div id="layoutViewSelectionBox">
			  		<c:if test="${graduate.status == 'true'}">
						<b style="font-size: 23px">Status: <b style = "color: green; font-size: 23px"> Approved </b> </b>
					</c:if>
					<c:if test="${graduate.status == 'false'}">
						<b style="font-size: 23px">Status: <b style = "color: red; font-size: 20px"> Not Approved </b> </b>
					</c:if>
					<hr>
					<input type="submit" name="editInfoButton" value="Edit Information" >
				</div>
			    <div id="studentBox">
			    	<div id="pictureBox">
			    		<img id="img0" src="${graduate.pendingInfo.getContentAtIndex(graduate.profileIndex).getContent()}" alt="Student Image" width="250px" height="250px">
			        </div>
			    	<div id="infoBox">
			            <table id="infoTable">
			            	<tr>
			             		<td id = "studentName">${graduate.name}</td>
			                </tr>
			                <tr>
			                	<td id = "academicInformation">${graduate.major}</td>
			                </tr>
			                <tr>
			                    <td id = "extraInformation">${graduate.pendingInfo.getContentAtIndex(graduate.extraInformationIndex).getContent()}</td>
			                </tr>
			            </table>
			        </div>
			    </div>
			     <audio id = "namePronunciation" autoplay src = "${graduate.pendingInfo.getContentAtIndex(graduate.namePronunciationIndex).getContent()}" >
					<source>
				</audio>
			    <br><br>
			    
			    <div id = "mediaBox">
		            
		            <!-- STATIC SLIDESHOW -->
		            <c:if test="${graduate.pendingInfo.getLayout() == 'static'}">
						<h3> STATIC SLIDESHOW EXAMPLE </h3>
						<img id = "img1" src = "${graduate.pendingInfo.getContentAtIndex(graduate.slideshow1Index).getContent()}" width = 170px height = 170px> 
						<img id = "img2" src = "${graduate.pendingInfo.getContentAtIndex(graduate.slideshow2Index).getContent()}" alt = "slideshow image 2" width = 170px height = 170px> 
			            <img id = "img3" src = "${graduate.pendingInfo.getContentAtIndex(graduate.slideshow3Index).getContent()}" alt = "slideshow image 3" width = 170px height = 170px> 
			            <img id = "img4" src = "${graduate.pendingInfo.getContentAtIndex(graduate.slideshow4Index).getContent()}" alt = "slideshow image 4" width = 170px height = 170px>
		            </c:if>
	
					<!-- 'DYNAMIC' SLIDESHOW -->
		            <c:if test="${graduate.pendingInfo.getLayout() == 'dynamic'}">
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
			           	
			            <h3> DYNAMIC SLIDESHOW EXAMPLE </h3>
						<img id = "img1" src = "${graduate.pendingInfo.getContentAtIndex(graduate.slideshow1Index).getContent()}" width = 170px height = 170px> 
						<img id = "img2" src = "${graduate.pendingInfo.getContentAtIndex(graduate.slideshow2Index).getContent()}" alt = "slideshow image 2" width = 170px height = 170px> 
			            <img id = "img3" src = "${graduate.pendingInfo.getContentAtIndex(graduate.slideshow3Index).getContent()}" alt = "slideshow image 3" width = 170px height = 170px> 
			            <img id = "img4" src = "${graduate.pendingInfo.getContentAtIndex(graduate.slideshow4Index).getContent()}" alt = "slideshow image 4" width = 170px height = 170px>
		            </c:if>
		            
		            
		            <!-- VIDEO -->
		            <c:if test="${graduate.pendingInfo.getLayout() == 'video'}">
						<h3> VIDEO EXAMPLE </h3>
			            <video id = "vid1" width = 680px height = 680px controls>
			            	<source src = "${graduate.pendingInfo.getContentAtIndex(graduate.videoIndex).getContent()}" type = "video/mp4">
			            </video>
		            </c:if>
		    	</div>
		   	</c:if>
		   	
		   	<!--  STUDENT EDIT PAGE -->
	  		<!--  STUDENT EDIT PAGE -->
		   	
		   	<c:if test="${mode == 'graduateEdit'}">
			   	<div id="layoutEditSelectionBox">
					<b style="font-size: 23px">Select your layout</b><hr>
					<c:if test="${graduateLayout == 'dynamic'}">
						<select id="layoutSelection" name="layoutSelection" onchange="changeGraduateLayout(this.selectedValue)">
							<option value="static">Static</option>
							<option value="dynamic" selected>Dynamic</option>
							<option value="video">Video</option>
						</select>
					</c:if>
					<c:if test="${graduateLayout == 'video'}">
						<select id="layoutSelection" name="layoutSelection" onchange="changeGraduateLayout(this.selectedValue)">
							<option value="static">Static</option>
							<option value="dynamic">Dynamic</option>
							<option value="video" selected>Video</option>
						</select>
					</c:if>
					<c:if test="${graduateLayout != 'video' && graduateLayout != 'dynamic'}">
						<select id="layoutSelection" name="layoutSelection" onchange="changeGraduateLayout(this.selectedValue)">
							<option value="static" selected>Static</option>
							<option value="dynamic">Dynamic</option>
							<option value="video">Video</option>
						</select>
					</c:if>					
					<hr>

					<input type="button" value="Save changes" onclick="setGraduateSaveOption(1)">
					<hr>
					<input type="button" value="Delete changes" onclick="setGraduateSaveOption(0)">
				</div>
		        <div id="studentBox">
		            <div id="pictureBox">
			        	<img id = "img0" src="${graduate.pendingInfo.getContentAtIndex(graduate.profileIndex).getContent()}" alt="Student Image" width="250px" height="250px"> 
			        </div>
		            <div id="infoBox">
		                <table id="infoTable">
			            	<tr>
			             		<td id = "studentName">${graduate.name}</td>
			                </tr>
			                <tr>
			                	<td id = "academicInformation">${graduate.major}</td>
			                </tr>
			                <tr>
			                    <td id = "extraInformation">
			                    	<textarea id = "extraInformationTB" style = "width: 100%" rows="10">${graduate.pendingInfo.getContentAtIndex(graduate.extraInformationIndex).getContent()}</textarea>
			                    </td>
			                </tr>
				    	</table>
		            </div>
		        </div>
		        <br><br>
		        <p> Upload student display image - display dimensions: 250px x 250px</p>
			    <input type="file" name="profilePictureUpload" accept="image/*" onchange="submitMedia(event, 0, 'photo')">
				<p> Upload student name pronunciation audio file</p>
			    <input type="file" name="namePronunciationUpload" accept="audio/*" onchange="submitMedia(event, 0, 'name')">
				<audio id = "namePronunciation" src = "${graduate.pendingInfo.getContentAtIndex(graduate.namePronunciationIndex).getContent()}"controls >
					<source>
				</audio>
		        <div id = "mediaBox">
		        
		            <!-- STATIC SLIDESHOW -->
		            <c:if test="${graduate.pendingInfo.getLayout() == 'static'}">
						<h3> STATIC SLIDESHOW EXAMPLE </h3>
						<img id = "img1" src = "${graduate.pendingInfo.getContentAtIndex(graduate.slideshow1Index).getContent()}" width = 170px height = 170px> 
						<img id = "img2" src = "${graduate.pendingInfo.getContentAtIndex(graduate.slideshow2Index).getContent()}" alt = "slideshow image 2" width = 170px height = 170px> 
			            <img id = "img3" src = "${graduate.pendingInfo.getContentAtIndex(graduate.slideshow3Index).getContent()}" alt = "slideshow image 3" width = 170px height = 170px> 
			            <img id = "img4" src = "${graduate.pendingInfo.getContentAtIndex(graduate.slideshow4Index).getContent()}" alt = "slideshow image 4" width = 170px height = 170px>
						
			            <p> Upload 1st image - display dimensions: 170px x 170px</p>
			            <input type="file" name="image1Upload" accept="image/*" onchange="submitMedia(event, 1, 'photo')">
						<p> Upload 2nd - display dimensions: 170px x 170px</p>
						<input type="file" name="image2Upload" accept="image/*" onchange="submitMedia(event, 2, 'photo')">
						<p> Upload 3rd image - display dimensions: 170px x 170px</p>
						<input type="file" name="image3Upload" accept="image/*" onchange="submitMedia(event, 3, 'photo')">
						<p> Upload 4th image - display dimensions: 170px x 170px</p>
						<input type="file" name="image4Upload" accept="image/*" onchange="submitMedia(event, 4, 'photo')">
		            </c:if>
		            
		            <!-- 'DYNAMIC' SLIDESHOW -->
		            <c:if test="${graduate.pendingInfo.getLayout() == 'dynamic'}">
			            <h3> DYNAMIC SLIDESHOW EXAMPLE </h3>
						<img id = "img1" src = "${graduate.pendingInfo.getContentAtIndex(graduate.slideshow1Index).getContent()}" width = 170px height = 170px> 
						<img id = "img2" src = "${graduate.pendingInfo.getContentAtIndex(graduate.slideshow2Index).getContent()}" alt = "slideshow image 2" width = 170px height = 170px> 
			            <img id = "img3" src = "${graduate.pendingInfo.getContentAtIndex(graduate.slideshow3Index).getContent()}" alt = "slideshow image 3" width = 170px height = 170px> 
			            <img id = "img4" src = "${graduate.pendingInfo.getContentAtIndex(graduate.slideshow4Index).getContent()}" alt = "slideshow image 4" width = 170px height = 170px>
			            <p> Upload leftmost image - small display dimensions: 70px x 70px | large display dimensions: 410px x 410px</p>
			            <input type="file" name="image1Upload" accept="image/*" onchange="submitMedia(event, 1, 'photo')">
						<p> Upload center-left image - small display dimensions: 70px x 70px | large display dimensions: 410px x 410px</p>
						<input type="file" name="image2Upload" accept="image/*" onchange="submitMedia(event, 2, 'photo')">
						<p> Upload center-right image - small display dimensions: 70px x 70px | large display dimensions: 410px x 410px</p>
						<input type="file" name="image3Upload" accept="image/*" onchange="submitMedia(event, 3, 'photo')">
						<p> Upload rightmost image - small display dimensions: 70px x 70px | large display dimensions: 410px x 410px</p>
						<input type="file" name="image4Upload" accept="image/*" onchange="submitMedia(event, 4, 'photo')">
		            </c:if>
		            
		            <!-- VIDEO -->
		            <c:if test="${graduate.pendingInfo.getLayout() == 'video'}">
						<h3> VIDEO EXAMPLE </h3>
			            <video id = "vid1" width = 680px height = 680px controls>
			            	<source src = "${graduate.pendingInfo.getContentAtIndex(graduate.videoIndex).getContent()}" type = "video/mp4">
			            </video>
						<p> Upload video - optimal length: &lt; 6 seconds</p>
						<input type="file" name="videoUpload" accept="video/*" onchange="submitMedia(event, 1, 'video')">
		            </c:if>
		        </div>
		   	</c:if>
	    	
	    	<!--  ADVISOR VIEW PAGE -->
	    	<!--  ADVISOR VIEW PAGE -->
	    	
	    	<c:if test="${mode=='advisorView'}">
			    <div id = "advisorViewBox" style="float: right; border: 1px solid black; height: 100px">
			    	<br>
		           	<input type="button" value="Back to Student List" onclick="navToStudentList()"/><br><br>
		            <input type="button" value="Confirm / Deny Student Information" onclick="navToEditMode()"/>
		    	</div>
			    <div id="studentBox">
			    	<div id="pictureBox">
			        	<img src="${studentToView.pendingInfo.getContentAtIndex(studentToView.profileIndex).getContent()}" alt="Student Image" width="250px" height="250px">
					</div>
			    	<div id="infoBox">
			            <table id="infoTable">
			            	<tr>
			             		<td id = "studentName">${studentToView.name}</td>
			                </tr>
			                <tr>
			                	<td id = "academicInformation">${studentToView.major}</td>
			                </tr>
			                <tr>
			                    <td id = "extraInformation">${studentToView.pendingInfo.getContentAtIndex(studentToView.extraInformationIndex).getContent()}</td>
			                </tr>
			            </table>
			        </div>
			    </div>
			     
			    <br><br>
			    
			    <div id = "mediaBox">
		            <!-- Uncomment one of these options to display-->
		            
		            <!-- STATIC SLIDESHOW -->
		            
				<img id = "img1" src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.slideshow1Index).getContent()}" width = 170px height = 170px> 
				<img id = "img2" src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.slideshow2Index).getContent()}" alt = "slideshow image 2" width = 170px height = 170px> 
	            <img id = "img3" src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.slideshow3Index).getContent()}" alt = "slideshow image 3" width = 170px height = 170px> 
	            <img id = "img4" src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.slideshow4Index).getContent()}" alt = "slideshow image 4" width = 170px height = 170px>
		            
		            <!-- 'DYNAMIC' SLIDESHOW -->
		            
		            <!--
		            <img id = "img1" src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.slideshow1Index).getContent()}" alt = "slideshow image 1" width = 200px height = 200px> 
		            <img id = "img2" src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.slideshow2Index).getContent()}" alt = "slideshow image 2" width = 50px height = 50px> 
		            <img id = "img3" src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.slideshow3Index).getContent()}" alt = "slideshow image 3" width = 50px height = 50px> 
		            <img id = "img4" src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.slideshow4Index).getContent()}" alt = "slideshow image 4" width = 50px height = 50px>
		            --> 
		            
		            <!-- VIDEO -->
		            
		           
		            <video width = 325px height = 325px controls>       <source src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.videoIndex).getContent()}" type = "video/mp4">
		            </video>
		            <br><br>
		    	</div>
	    	</c:if>
	    	
	    	<!--  ADVISOR EDIT PAGE -->
	    	<!--  ADVISOR EDIT PAGE -->
	    	<c:if test="${mode=='advisorEdit'}">
			  	
	    		<div id="advisorEditBox" style = "height: 400px">
					<b style="font-size: 18px">Approve</b><hr>
  					<input type="checkbox" name="extraInfoCB" value="extraInfoCB"> Extra Info <hr>
  					<input type="checkbox" name="pronunciationCB" value="prounciationCB"> Name Pronunciation <hr>
  					<input type="checkbox" name="profilePictureCB" value="profilePictureCB"> Display picture <hr>
  					<input type="checkbox" name="slideshow1CB" value="slideshow1CB"> Slideshow Photo 1 <hr>
  					<input type="checkbox" name="slideshow2CB" value="slideshow2CB"> Slideshow Photo 2 <hr>
  					<input type="checkbox" name="slideshow3CB" value="slideshow3CB"> Slideshow Photo 3 <hr>
  					<input type="checkbox" name="slideshow4CB" value="slideshow4CB"> Slideshow Photo 4 <hr>
  					<input type="checkbox" name="videoCB" value="videoCB"> Video <hr>
					<input type="button" style="margin-bottom: 10px" value="Save Approvals" onclick="setAdvisorApprovalOption(1)">
					<input type="button" value="Discard Approvals" onclick="setAdvisorApprovalOption(0)">
				</div>
	    		<div id="studentBox">
			    	<div id="pictureBox">
			        	<img src="${studentToView.pendingInfo.getContentAtIndex(studentToView.profileIndex).getContent()}" alt="Student Image" width="250px" height="250px">
			        </div>
			    	<div id="infoBox">
			            <table id="infoTable">
			            	<tr>
			             		<td id = "studentName">${studentToView.name}</td>
			                </tr>
			                <tr>
			                	<td id = "academicInformation">${studentToView.major}</td>
			                </tr>
			                <tr>
			                    <td id = "extraInformation">${studentToView.pendingInfo.getContentAtIndex(studentToView.extraInformationIndex).getContent()}</td>
			                </tr>
			            </table>
			        </div>
			    </div>
			     
			    <br><br>
				<audio id = "namePronunciation" src = "${studentToView.pendingInfo.getContentAtIndex(graduate.namePronunciationIndex).getContent()}"controls >
					<source>
				</audio>			    
			    <div id = "mediaBox">
		            <!-- Uncomment one of these options to display-->
		            
		            <!-- STATIC SLIDESHOW -->
		            
					<img id = "img1" src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.slideshow1Index).getContent()}" width = 170px height = 170px> 
					<img id = "img2" src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.slideshow2Index).getContent()}" alt = "slideshow image 2" width = 170px height = 170px> 
		            <img id = "img3" src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.slideshow3Index).getContent()}" alt = "slideshow image 3" width = 170px height = 170px> 
		            <img id = "img4" src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.slideshow4Index).getContent()}" alt = "slideshow image 4" width = 170px height = 170px>
		            
		            <!-- 'DYNAMIC' SLIDESHOW -->
		            
		            <!--
		            <img id = "img1" src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.slideshow1Index).getContent()}" width = 170px height = 170px> 
					<img id = "img2" src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.slideshow2Index).getContent()}" alt = "slideshow image 2" width = 170px height = 170px> 
		            <img id = "img3" src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.slideshow3Index).getContent()}" alt = "slideshow image 3" width = 170px height = 170px> 
		            <img id = "img4" src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.slideshow4Index).getContent()}" alt = "slideshow image 4" width = 170px height = 170px>
		            --> 
		            
		            <!-- VIDEO -->
		            
		            
		            <video width = 325px height = 325px controls>       <source src = "${studentToView.pendingInfo.getContentAtIndex(studentToView.videoIndex).getContent()}" type = "video/mp4">
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
	    	<input type="hidden" id="studentNewInfo"name="studentNewInfo" value="0">
	    	<input type="hidden" id="studentNewInformation"name="studentNewInformation" value="${studentToView.pendingInfo.getContentAtIndex(studentToView.extraInformationIndex).getContent()}">
	    	
	    	<!-- Advisor Attributes -->
	    	<input type="hidden" id="advisorUsername" name="advisorUsername" value="${advisorUsername}">
	    	<input type="hidden" id="advisorSwitch" name="advisorSwitch" value="${advisorSwitch}">
	    	<input type="hidden" id="advisorGoBack" name="advisorGoBack" value="${advisorGoBack}">
	    	<input type="hidden" id="advisorSaveChanges"name="advisorSaveChanges" value="${advisorSaveChanges}">
	    	<input type="hidden" id="navToList"name="navToList" value="${navToList}">

		</form>
    </body>
</html>