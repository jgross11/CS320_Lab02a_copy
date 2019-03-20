<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- 
TODO: Needs table generation for pending / approved graduates that navigates to 
TODO: Advisor's edit mode of graduate's pending infostate when name in table is clicked

TODO: Needs to store (approximately) a ton of hidden attributes that will be passed
TODO: back and forth between jsp and servlet

TODO: Needs
 -->
<html>
    <head>
        <title>Personalized Commencement - Advisor Page</title>
        <style><%@include file="css/advisorPageStylesheet.css"%></style>
        <style><%@include file="css/siteStylesheet.css"%></style>
    </head>
    <body>
        <div id="documentHeading">
            <b> Personalized Commencement - Advisor Page </b>
        </div>
        <div id="advisorBox">
            <div id="pictureBox">
                <img src="assets/mocha.png" alt="Advisor Image" width="150px" height="150px"> 
            </div>
            <div id="infoBox">
                <table id="infoTable">
                    <tr>
                        <td id = "advisorName">
                        	${advisorName}
                        </td>
                    </tr>
                    <tr>
                        <td id = "academicInformation">
                        	${academicInfo}
                        </td>
                    </tr>
                    <tr>
                        <td id = "eventStatus"> 
                        	<c:if test="${advisorStatus == 'true'}">
								<b style="font-size: 23px"> Status: <b style = "color: green; font-size: 18px"> All students approved </b> </b>
							</c:if>
							<c:if test="${advisorStatus == 'false'}">
								<b style="font-size: 23px"> Status: <b style = "color: red; font-size: 18px"> Student changes pending </b> </b>
							</c:if>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="studentStatusBox">
            <table style="width:100%">
                <tr>
                    <th>Approved Students</th>
                    <th>Pending Students </th>
                </tr>
                <tr><!-- STUDENT TABLE GENERATION  -->
                	
                	<!-- 
                	TODO: iterate through the Advisor's Graduate array
                	TODO: and generate a cell for every dis/approved Graduate
                	TODO: and assign a click event that loads that Graduate's
                	TODO: new InfoState information in Advisor Edit mode
                	
                	 -->
                </tr>
                
                <!-- 
                The following will be removed once table generation is functional, but
                it can be uncommented to preview what a generated table will look like
                 -->
                 
                <!-- 
                <tr>
                    <td> Dennis Chism</td>
                    <td> </td>
                </tr>
                <tr>
                    <td> Alyssa Grove</td>
                    <td> </td>
                </tr>
                -->
            </table>
        </div>
    </body>
</html>