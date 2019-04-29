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
		<div style="float: left; margin-right: 10px"> 		
			<div id="studentBox">
				<div id="pictureBox">
				 	<img src="${admin.image}" alt="Student Image" width="250px" height="250px"> 
				 </div>
				 <div id="infoBox">
				    <table id="infoTable">
				    	<tr>
				     		<td id = "leftName"></td>
				    	 </tr>
				     	<tr>
				      		<td id = "leftMajor"></td>
				    	 </tr>
				    	 <tr>
				     		<td id = "leftExtra"></td>
				     	</tr>
				    </table>
				    </div>
				    </div>
				     
				    <br><br>
				    
			<div id = "mediaBox">
			            <!-- Uncomment one of these options to display-->
			            
			            <!-- STATIC SLIDESHOW -->
			            
			            
					<img src = "${admin.image}" alt = "slideshow image 1" width = 150px height = 150px> 
		            <img src = "${admin.image}" alt = "slideshow image 2" width = 150px height = 150px> 
		            <img src = "${admin.image}" alt = "slideshow image 3" width = 150px height = 150px> 
		            <img src = "${admin.image}" alt = "slideshow image 4" width = 150px height = 150px>
		       </div>
			</div>       
	   	<div style="float: left">
		   	<div id="studentBox">
				<div id="pictureBox">
				 	<img src="${admin.image}" alt="Student Image" width="250px" height="250px"> 
				 </div>
				 <div id="infoBox">
				    <table id="infoTable">
				    	<tr> 
				     		<td id = "rightName"> </td>
				    	 </tr>
				     	<tr>
				      		<td id = "rightMajor"> </td>
				    	 </tr>
				    	 <tr>
				     		<td id = "rightExtra"> </td>
				     	</tr>
				    </table>
				</div>
			</div>
							    <br><br>
			<div id = "mediaBox2">
			            <!-- Uncomment one of these options to display-->
			            
			            <!-- STATIC SLIDESHOW -->
			            
			            
					<img src = "${admin.image}" alt = "slideshow image 1" width = 150px height = 150px> 
		            <img src = "${admin.image}" alt = "slideshow image 2" width = 150px height = 150px> 
		            <img src = "${admin.image}" alt = "slideshow image 3" width = 150px height = 150px> 
		            <img src = "${admin.image}" alt = "slideshow image 4" width = 150px height = 150px>
		       </div>
		</div>
		
		<script>
        // student variables
       // var students = document.getElementById("studentArray").value;

        // index of left and right students
        var textIndex = 1;
        var prevIndex = 0;
        var student1 = {
        		name: "Josh Gross",
        		major: "Cat-ology",
        		extraInformation: "Cats"
       	 	};
       	 	var student2 = {
        		name: "Dennis Chism",
        		major: "Motorcycle-ology",
        		extraInformation: "Motorcycles"
       	 	};
       	 	var student3 = {
        		name: "Alyssa Grove",
        		major: "The Black Death-ology",
        		extraInformation: "TBD"
       	 	};
    		var students = [student1, student2, student3];
        document.getElementById("leftName").innerHTML = students[0].name;
        document.getElementById("rightName").innerHTML = students[1].name;
        document.getElementById("leftMajor").innerHTML = students[0].major;
        document.getElementById("rightMajor").innerHTML = students[1].major;
        document.getElementById("leftExtra").innerHTML = students[0].extraInformation;
        document.getElementById("rightExtra").innerHTML = students[1].extraInformation;  
        textIndex++;
        prevIndex++;
        main();
        function main(){
            
            // sets timer that calls function after n milliseconds
            setTimeout(moveInformation, 4000, students, textIndex, prevIndex);
            
            // logic that loops students
            if(textIndex < students.length - 1){
               textIndex++;
                prevIndex = textIndex - 1;
            }
            else if(textIndex == students.length - 1){
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
            document.getElementById("leftName").innerHTML = leftStudent.name;
            document.getElementById("rightName").innerHTML = rightStudent.name;
            document.getElementById("leftMajor").innerHTML = leftStudent.major;
            document.getElementById("rightMajor").innerHTML = rightStudent.major;
            document.getElementById("leftExtra").innerHTML = leftStudent.extraInformation;
            document.getElementById("rightExtra").innerHTML = rightStudent.extraInformation;            
            main();
        }
    </script>
    <button type="submit" formaction="${pageContext.servletContext.contextPath}/_view/PCP_AdminPage.jsp">End Event</button> 
	    	<input type="hidden" id = "studentArray" name="studentArray" value "${gradList}">
		</form> 
	</body>
</html>		            