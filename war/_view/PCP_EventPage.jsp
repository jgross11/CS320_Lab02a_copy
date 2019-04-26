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
		
		<!-- old code <div style="float: left"> 		
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
				    </div> -->
				    
		  <div id="left" style="float: left"> 
        	<h3> Left Student Information </h3>
        	<table style="border: 1px solid black">
           	  <tr id="rightName">
            
              </tr>
           	  <tr id="rightMajor">
            
              </tr>
              <tr id="rightExtra">
            
           	 </tr>
           </table>
          <p onmouseover="document.getElementById('rightName').style.color = 'red'" onmouseleave="document.getElementById('rightName').style.color = 'black'"> Hover to change left name color </p>
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
		<!-- old code </div>       
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
		</div>-->
		
		<div id="right" style="float: right"> 
        	<h3> Right Student Information </h3>
       		<table style="border: 1px solid black">
           		 <tr id="leftName">
            
           		 </tr>
           		 <tr id="leftMajor">
            
           		 </tr>
           		 <tr id="leftExtra">
            
           		 </tr>
        	</table>
       	 <p onmouseover="document.getElementById('leftName').style.fontSize = '20px'" onmouseleave="document.getElementById('leftName').style.fontSize = '18px'"> Hover to change right name font size </p>
    	</div>
		
		<script>
        function doStuff(element){
            var i = 0;
            while(i < 1000){
                element.style.borderRadius++;
                i++;
            }
            while(i > 1000){
                element.style.borderRadius--;
                i--;
            }
        }
        // student variables
        var student1 = {
            firstName: "Dennis",
            lastName: "Chism",
            major: "Computer Science",
            extraInformation: "Kobe"
        }
        var student2 = {
            firstName: "Josh",
            lastName: "Gross",
            major: "Hand Modeling",
            extraInformation: "Mocha"  
        }
        var student3 = {
            firstName: "Alyssa",
            lastName: "Grove",
            major: "Computer Science",
            extraInformation: "Anime"
        }
        // index of left and right students
        var textIndex = 1;
        var prevIndex = 0;
        main();
        function main(){
            // array containing student objects
            var studentInformation = [
                student1, student2, student3
            ]; 
            
            // sets timer that calls function after n milliseconds
            setTimeout(moveInformation, 4000, studentInformation, textIndex, prevIndex);
            
            // logic that loops students
            if(textIndex < studentInformation.length - 1){
               textIndex++;
                prevIndex = textIndex - 1;
            }
            else if(textIndex == studentInformation.length - 1){
                prevIndex = textIndex;
                textIndex = 0;
            }
            else{
                textIndex = 1;
                prevIndex = 0;
            }
        }
        
        function moveInformation(studentArray, index, prevIndex){
            var leftStudent = studentArray[index];
            var rightStudent = studentArray[prevIndex];
            document.getElementById("leftName").innerHTML = leftStudent.firstName + " " + leftStudent.lastName;
            document.getElementById("rightName").innerHTML = rightStudent.firstName + " " + rightStudent.lastName;
            document.getElementById("leftMajor").innerHTML = leftStudent.major;
            document.getElementById("rightMajor").innerHTML = rightStudent.major;
            document.getElementById("leftExtra").innerHTML = leftStudent.extraInformation;
            document.getElementById("rightExtra").innerHTML = rightStudent.extraInformation;            
            main();
        }
    </script>     
	            
	            <form> 
	    		<button type="submit" formaction="${pageContext.servletContext.contextPath}/_view/PCP_AdminPage.jsp">End Event</button> 
	    		</form> 
		</form> 
	</body>
</html>		            