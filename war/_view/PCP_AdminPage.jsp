<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- 
<<<<<<< HEAD
TODO: Needs to store (approximately) a ton of hidden attributes that will be passed
TODO: back and forth between JSP and servlet
 -->
<html>
    	<c:if test="${mode == 'home'}">
			<head>
		        <title>Personalized Commencement - Admin Page</title>
				<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/adminPageStylesheet.css" />
		        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/siteStylesheet.css" />
		    </head>
		    <body>
				<script>
				function setAdminChoice(choice){
				    if(choice == 1){
				    	document.getElementById("adminChoice").value = "update deadline";
				    	document.getElementById("newDate").value = document.getElementById("deadlineChange").value;
				    }
				    else{
				    	document.getElementById("adminChoice").value = "start event";
				    }
				    document.getElementById("adminForm").submit();
				}
				</script>
		    <div id="documentHeading">
		        <b> Personalized Commencement - Admin Page </b>
		    </div>
		    <form id="adminForm" name="adminForm" action="${pageContext.servletContext.contextPath}/PCP_AdminPage" method="post">
		        <div id="adminBox">
		            <div id="pictureBox">
		                <img src="${user.image}" alt="Admin Photo" width="150px" height="150px"/> 
		            </div>
		            <div id="infoBox">
		                <table id="infoTable">
		                    <tr>
		                        <td id = "adminName">
		                        	${admin.name}
		                        </td>
		                    </tr>
		                    <tr>
		                        <td id = "deadlineInformation">
		                        	 Deadline date: <b> ${admin.dateAsDate} </b>
		                        </td> 
		                    </tr>
		                    <tr>
		                        <td id = "dateChange"> 
		                        	Change deadline date: <input type="date" id="deadlineChange" value = "${admin.dateAsDate}" name = "changeDateButton"/>
		                        	<input type="button" onclick="setAdminChoice(1)">
		                        </td>
		                    </tr>
		                </table>
		            </div>
		        </div>
		        <p></p>
		    	<input type="button" name = "input" onclick="setAdminChoice(0)" value="Event page">  
		    </c:if>
		    <c:if test="${mode == 'event'}">
				<head>
			        <title>Personalized Commencement - Event Page</title>
					<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/eventPageStylesheet.css" />
			        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/siteStylesheet.css" />
			    </head>
			    <body>
			        <div id="documentHeading">
			            <b> Personalized Commencement - Event Page </b>
			        </div>
			        <script>
					    function findGraduate(){
					    	document.getElementById("adminChoice").value = "find grad";
					    	document.getElementById("gradToFind").value = document.getElementById("gradTB").value;
					    	console.log(document.getElementById("adminChoice").value);
					    	submitForm1();
					    }
					    function endEvent(){
					    	document.getElementById("adminChoice").value = "end event";
					    	console.log(document.getElementById("adminChoice").value);
					    	submitForm1();
					    }
					    
					    function submitForm1(){
					    	document.getElementById("eventForm").submit()
					    }
		    		</script>
					<form id = "eventForm" name="eventForm" action="${pageContext.servletContext.contextPath}/PCP_AdminPage" method="post"> 
					<div style="height: 800px">
					<div style="float: left; margin-right: 10px">
						<div id="studentBox">
							<div id="pictureBox">
							 	<img src="${leftGrad.pendingInfo.getContentAtIndex(leftGrad.profileIndex).getContent()}" alt="Student Image" width="250px" height="250px"> 
							 </div>
							 <div id="infoBox">
							    <table id="infoTable">
							    	<tr>
							     		<td id = "leftName">${leftGrad.name}</td>
							    	 </tr>
							     	<tr>
							      		<td id = "leftMajor">${leftGrad.major}</td>
							    	 </tr>
							    	 <tr>
							     		<td id = "leftExtra">${leftGrad.pendingInfo.getContentAtIndex(leftGrad.extraInformationIndex).getContent()}</td>
							     	</tr>
							    </table>
							    </div>
							    </div>
							     
							    <br><br>
						
						<div id = "mediaBox">
		            
		            <!-- STATIC SLIDESHOW -->
		            <c:if test="${leftGrad.pendingInfo.getLayout() == 'static'}">
						<img id = "img1" src = "${leftGrad.pendingInfo.getContentAtIndex(leftGrad.slideshow1Index).getContent()}" width = 150px height = 150px> 
						<img id = "img2" src = "${leftGrad.pendingInfo.getContentAtIndex(leftGrad.slideshow2Index).getContent()}" alt = "slideshow image 2" width = 150px height = 150px> 
			            <img id = "img3" src = "${leftGrad.pendingInfo.getContentAtIndex(leftGrad.slideshow3Index).getContent()}" alt = "slideshow image 3" width = 150px height = 150px> 
			            <img id = "img4" src = "${leftGrad.pendingInfo.getContentAtIndex(leftGrad.slideshow4Index).getContent()}" alt = "slideshow image 4" width = 150px height = 150px>
		            </c:if>
	
		            <c:if test="${leftGrad.pendingInfo.getLayout() == 'dynamic'}">
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
			           	
						<img id = "img1" src = "${leftGrad.pendingInfo.getContentAtIndex(graduate.slideshow1Index).getContent()}" width = 150px height = 150px> 
						<img id = "img2" src = "${leftGrad.pendingInfo.getContentAtIndex(graduate.slideshow2Index).getContent()}" alt = "slideshow image 2" width = 150px height = 150px> 
			            <img id = "img3" src = "${leftGrad.pendingInfo.getContentAtIndex(graduate.slideshow3Index).getContent()}" alt = "slideshow image 3" width = 150px height = 150px> 
			            <img id = "img4" src = "${leftGrad.pendingInfo.getContentAtIndex(graduate.slideshow4Index).getContent()}" alt = "slideshow image 4" width = 150px height = 150px>
		            </c:if>
		            
		            
		            <!-- VIDEO -->
		            <c:if test="${leftGrad.pendingInfo.getLayout() == 'video'}">
			            <video id = "vid1" width = 300px height = 300px controls autoplay>
			            	<source src = "${leftGrad.pendingInfo.getContentAtIndex(leftGrad.videoIndex).getContent()}" type = "video/mp4">
			            </video>
		            </c:if>
		    	</div>
										    <br><br>
					</div>
					<div style="float: right; margin-left: 10px">
						<div id="studentBox">
							<div id="pictureBox">
							 	<img src="${rightGrad.pendingInfo.getContentAtIndex(rightGrad.profileIndex).getContent()}" alt="Student Image" width="250px" height="250px"> 
							 </div>
							 <div id="infoBox">
							    <table id="infoTable">
							    	<tr>
							     		<td id = "rightName">${rightGrad.name}</td>
							    	 </tr>
							     	<tr>
							      		<td id = "rightMajor">${rightGrad.major}</td>
							    	 </tr>
							    	 <tr>
							     		<td id = "rightExtra">${rightGrad.pendingInfo.getContentAtIndex(rightGrad.extraInformationIndex).getContent()}</td>
							     	</tr>
							    </table>
							    </div>
							    </div>
							     
							    <br><br>
						
						<div id = "mediaBox">
		            
		            <!-- STATIC SLIDESHOW -->
		            <c:if test="${rightGrad.pendingInfo.getLayout() == 'static'}">
						<img id = "img1" src = "${rightGrad.pendingInfo.getContentAtIndex(rightGrad.slideshow1Index).getContent()}" width = 150px height = 150px> 
						<img id = "img2" src = "${rightGrad.pendingInfo.getContentAtIndex(rightGrad.slideshow2Index).getContent()}" alt = "slideshow image 2" width = 150px height = 150px> 
			            <img id = "img3" src = "${rightGrad.pendingInfo.getContentAtIndex(rightGrad.slideshow3Index).getContent()}" alt = "slideshow image 3" width = 150px height = 150px> 
			            <img id = "img4" src = "${rightGrad.pendingInfo.getContentAtIndex(rightGrad.slideshow4Index).getContent()}" alt = "slideshow image 4" width = 150px height = 150px>
		            </c:if>
	
		            <c:if test="${rightGrad.pendingInfo.getLayout() == 'dynamic'}">
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
			           	
						<img id = "img1" src = "${rightGrad.pendingInfo.getContentAtIndex(rightGrad.slideshow1Index).getContent()}" width = 150px height = 150px> 
						<img id = "img2" src = "${rightGrad.pendingInfo.getContentAtIndex(rightGrad.slideshow2Index).getContent()}" alt = "slideshow image 2" width = 150px height = 150px> 
			            <img id = "img3" src = "${rightGrad.pendingInfo.getContentAtIndex(rightGrad.slideshow3Index).getContent()}" alt = "slideshow image 3" width = 150px height = 150px> 
			            <img id = "img4" src = "${rightGrad.pendingInfo.getContentAtIndex(rightGrad.slideshow4Index).getContent()}" alt = "slideshow image 4" width = 150px height = 150px>
		            </c:if>
		            
		            
		            <!-- VIDEO -->
		            <c:if test="${rightGrad.pendingInfo.getLayout() == 'video'}">
			            <video id = "vid1" width = 300px height = 300px controls autoplay>
			            	<source src = "${rightGrad.pendingInfo.getContentAtIndex(rightGrad.videoIndex).getContent()}" type = "video/mp4">
			            </video>
		            </c:if>
		    	</div>
										    <br><br>
					</div>
				<input type="hidden" id = "studentArray" name="studentArray" value "${gradList}">
				<input type="hidden" id = "mode" name="mode" value ""> 
				<input type="button" onclick="endEvent()" value="End Event">
			</div>
			<input type="textbox" id="gradTB">
			<input type="button" onclick="findGraduate()" value="Enter Graduate"> 
				</body>
		    </c:if>
		    
	        <!--  sneaky references to fields accessed by the servlet--> 
	    	<!--  
	    	NOTE: *every* attribute that is submitted to the JSP needs to be represented here,
	    	NOTE: regardless of whether or not it is visible / modifiable by the user  
	    	-->
	
			<input type="hidden" name="mode" value="${mode}">
	    	<input type="hidden" name="eventDate" value="${eventDate}">
	    	<input type="hidden" name="adminUsername" value="${adminUsername}"> 
	    	<input type="hidden" name="adminName" value="${adminName}">
	    	<input type="hidden" name="academicInformation" value="${academicInformation}">
	    	<input type="hidden" name="adminUsername" value="${adminUsername}">
	    	<input type="hidden" id="newDate" name="newDate" value="-1">
	    	<input type="hidden" id="gradToFind" name="gradToFind" value="">
	    	<input type="hidden" id="adminChoice" name="adminChoice" value="${adminChoice}">
	    	</div>
	    	</form>
        </form>
</html>