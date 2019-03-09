<!DOCTYPE html>

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
        <br>
    </body>
</html>