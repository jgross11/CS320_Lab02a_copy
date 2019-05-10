package edu.ycp.cs320.PersonalizedCommencementProject.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import edu.ycp.cs320.PersonalizedCommencementProject.controller.GraduateController;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.DatabaseProvider;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.DerbyDatabase;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.IDatabase;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.ContentComponent;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.InfoState;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.User;


@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 512, 
		maxRequestSize = 1024 * 1024 * 5 * 5
)

public class PCP_StudentPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String uploadPath = "";
	private String photoPath = "";
	private String videoPath = "";
	private String audioPath = "";
	

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// parent directory to save files in
		// sets directory relative to os running servlet
		uploadPath =  getServletContext().getRealPath("") + "//student-media-uploads";
		System.out.println(uploadPath);
		File uploadDirectory = new File(uploadPath);
		
		// children directories
		photoPath = uploadPath + File.separator + "photos";
		File photoDirectory = new File(photoPath);
		
		videoPath = uploadPath + File.separator + "videos";
		File videoDirectory = new File(videoPath);
		
		audioPath = uploadPath + File.separator + "audios";
		File audioDirectory = new File(audioPath);
		
		// ensure directories exists 
		if (!uploadDirectory.exists()) {
			// student-media-uploads
			uploadDirectory.mkdir();
			
			// student-media-uploads\audio
			audioDirectory.mkdir();
			
			// student-media-uploads\video
			videoDirectory.mkdir();
			
			// student-media-uploads\photos
			photoDirectory.mkdir();
			System.out.println("Created " + uploadDirectory + " and its children directories");	
		}
		else {
			System.out.println("parent: " + uploadDirectory);
			System.out.println("audio: " + audioDirectory);
			System.out.println("video: " + videoDirectory);
			System.out.println("photo: " + photoDirectory);
		}
		
		System.out.println("PCP_StudentPage Servlet : doGet");
		// validate user should be allowed to access this page by checking
		// the type of user associated with the provided username
		try{
			User user = (User)req.getSession().getAttribute("user");
			// allow students to always access this page
			
			if(user.getType().equals("graduate")) {
				req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
			}
			
			// allow advisors to access this page so long
			// as they have a student's information to view
			else if(user.getType().equals("advisor") && req.getSession().getAttribute("studentToView") != null){
				req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
			}
			// only happens when admin tries to access page
			else {
				resp.sendRedirect(req.getContextPath() + "/PCP_Index");
			}
		}
		catch(NullPointerException e) {
			// no user - attempted to directly access page
			resp.sendRedirect(req.getContextPath() + "/PCP_Index");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		
		System.out.println("PCP_StudentPage Servlet: doPost");
		
		// creates controller that holds provided graduate's Graduate model
		GraduateController controller = new GraduateController();
		
		// HttpSession allows for local persistence while 
		// the user is interacting with the project
		HttpSession session = req.getSession();
		Graduate graduate = (Graduate) session.getAttribute("graduate");
		controller.setModel(graduate);
		
		
		// TODO: create reference to model
		// TODO: Graduate model = controller.getModel();
		
		// determines whether or not student wishes
		// to save changes to their media selections
		String studentSaveChanges = "";
		
		// mode the JSP should display
		String mode = "";
		
		// attempt to obtain the student's action
		// null if student was just redirected to page
		try{
			studentSaveChanges = req.getParameter("studentSaveChanges").toString();
			mode = session.getAttribute("mode").toString();
		}
		catch(NullPointerException e) {
			// only null for the first post, safe to assume
			// user wants to edit information
			mode = "graduateView";
			System.out.println("Switching graduate mode from view to edit - First time");
		}
		
		// student wishes to edit their media
		if(mode.equals("graduateView")) {
			session.setAttribute("mode", "graduateEdit");
			System.out.println("Switching graduate mode from view to edit");
			session.setAttribute("graduate", graduate);
			req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
		}
		
		// student wishes to either save or discard()); their
		// changes to their media
		else if(mode.equals("graduateEdit")){
			System.out.println(req.getParameter("graduateLayoutChange"));
			// graduate wants to change layout
			if(req.getParameter("graduateLayoutChange").toString().equals("true")){
				// TODO: set graduate's layout mode to the chosen value
				// TODO: this is temporary; will eventually change model
				session.setAttribute("graduateLayout", req.getParameter("graduateLayout"));
				session.setAttribute("graduateLayoutChange", "false");
				session.setAttribute("mode", "graduateEdit");
				System.out.println("Returning to graduate edit mode after modifying layout");
			}
			// graduate wants to view their information
			else {
				session.setAttribute("mode", "graduateView");
				System.out.println("Switching graduate mode from edit to view");
				// graduate wants to save their changes
				if(studentSaveChanges.equals("true")) {
					System.out.println("\n\nStudent wishes to save changes");
					// TODO: call function to save changes
					// TODO: call function to save changes
					// TODO: the following experiment merely checks for new media and uploads it
					
					// profile picture check
					String uniqueFileName;
					Part profilePicturePart = req.getPart("profilePictureUpload");
					if(!profilePicturePart.getSubmittedFileName().equals("")) {
						System.out.println("uploaded file name:" + graduate.getUsername() + "_" + profilePicturePart.getSubmittedFileName());
					    uniqueFileName = graduate.getUsername() + "_" + profilePicturePart.getSubmittedFileName();
					    writeFile(profilePicturePart, uniqueFileName, "image");
					    ContentComponent newContent = new ContentComponent("student-media-uploads\\photos\\" + uniqueFileName, false, InfoState.PROFILE, graduate.getUsername(), "pending");
						controller.insertGraduateMediaIntoContentComponentTable(graduate.getUsername(), "pending", newContent);
						controller.setInfoAtIndex(InfoState.PROFILE_INDEX, new ContentComponent("student-media-uploads/photos/" + uniqueFileName, newContent.getStatus(), newContent.getType(), newContent.getUsername(), newContent.getInfoStateType()));
						System.out.println("Entered new profile picture");
						for(ContentComponent content : graduate.getPendingInfo().getContents()) {
							System.out.println(content.getType() + " | " + content.getContent());
						}
					}
					
					// name pronunciation check
					Part namePronunciationPart = req.getPart("namePronunciationUpload");
					if(!namePronunciationPart.getSubmittedFileName().equals("")) {
						// TODO: insert student's username here
					    uniqueFileName = /*model.getUsername() + */ "test_" + namePronunciationPart.getSubmittedFileName();
					    writeFile(namePronunciationPart, uniqueFileName, "audio");
					    ContentComponent newContent = new ContentComponent("student-media-uploads\\audios\\" + uniqueFileName, false, InfoState.NAMEPRONUNCIATION, graduate.getUsername(), "pending");
						controller.insertGraduateMediaIntoContentComponentTable(graduate.getUsername(), "pending", newContent);
						controller.setInfoAtIndex(InfoState.NAMEPRONUNCIATION_INDEX, new ContentComponent("student-media-uploads/audios/" + uniqueFileName, newContent.getStatus(), newContent.getType(), newContent.getUsername(), newContent.getInfoStateType()));
						System.out.println("Entered new name pronunciation");
						for(ContentComponent content : graduate.getPendingInfo().getContents()) {
							System.out.println(content.getType() + " | " + content.getContent());
						}
					}
 
					String layout = session.getAttribute("graduateLayout").toString();
					if(layout.equals("static slideshow") || layout.equals("dynamic slideshow")) {
						String builder = "";
						Part imagePart;
						for(int i = 1; i < 5; i++) {
							builder = "image" + i + "Upload";
							imagePart = req.getPart(builder);
							if(!imagePart.getSubmittedFileName().equals("")) {
								uniqueFileName = graduate.getUsername() + "_" + imagePart.getSubmittedFileName();
							    writeFile(imagePart, uniqueFileName, "image");
							    ContentComponent newContent = new ContentComponent("student-media-uploads\\photos\\" + uniqueFileName, false, "slideshow" + i, graduate.getUsername(), "pending");
								controller.insertGraduateMediaIntoContentComponentTable(graduate.getUsername(), "pending", newContent);
								controller.setInfoAtIndex(2+i, new ContentComponent("student-media-uploads/photos/" + uniqueFileName, newContent.getStatus(), newContent.getType(), newContent.getUsername(), newContent.getInfoStateType()));
							}
							else {
								System.out.println("Nothing was uploaded for " + builder);
							}
							System.out.println("Entered new slideshow images");
							for(ContentComponent content : graduate.getPendingInfo().getContents()) {
								System.out.println(content.getType() + " | " + content.getContent());
							}
						}
					}
					else if(layout.equals("video")) {
						Part videoPart = req.getPart("videoUpload");
						if(!videoPart.getSubmittedFileName().equals("")) {
							uniqueFileName = graduate.getUsername() + "_" + videoPart.getSubmittedFileName();
						    writeFile(videoPart, uniqueFileName, "video");
						    ContentComponent newContent = new ContentComponent("student-media-uploads\\videos\\" + uniqueFileName, false, InfoState.VIDEO, graduate.getUsername(), "pending");
							controller.insertGraduateMediaIntoContentComponentTable(graduate.getUsername(), "pending", newContent);
							controller.setInfoAtIndex(InfoState.PROFILE_INDEX, new ContentComponent("student-media-uploads/videos/" + uniqueFileName, newContent.getStatus(), newContent.getType(), newContent.getUsername(), newContent.getInfoStateType()));
							System.out.println("Entered new video");
							for(ContentComponent content : graduate.getPendingInfo().getContents()) {
								System.out.println(content.getType() + " | " + content.getContent());
							}
						}
						else {
							System.out.println("Nothing was uploaded for videoUpload");
						}
					}
					else {
						System.out.println("Invalid student layout");
					}
				}
				// graduate wants to discard their changes
				else if(studentSaveChanges.equals("false")){
					System.out.println("\n\nStudent wishes to discard changes");
					// TODO: call function to discard changes - this might just be empty
					// TODO: call function to discard changes - this might just be empty
				}
				else {
					System.out.println("Invalid Values, save: " + studentSaveChanges);
				}
			}
			session.setAttribute("graduate", graduate);
			req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
		}
		// advisor wishes to dis/approve a student's media selections
		else if(mode.equals("advisorView")){
			try {
				// attempt to get requested values and update session values
				session.setAttribute("advisorSwitch", req.getParameter("advisorSwitch").toString());
				session.setAttribute("advisorGoBack", req.getParameter("advisorGoBack").toString());
				System.out.println(session.getAttribute("advisorGoBack").toString() + ", and " + session.getAttribute("advisorSwitch").toString());
			}
			catch(NullPointerException e) {
				// first time post case
				session.setAttribute("advisorGoBack", "true");
				session.setAttribute("advisorSwitch", "false");
				System.out.println(session.getAttribute("advisorGoBack").toString() + ", or " + session.getAttribute("advisorSwitch").toString());
			}
			if(session.getAttribute("advisorSwitch").toString().equals("true")) {
				session.setAttribute("mode", "advisorEdit");
				System.out.println("Switching advisor mode from view to edit");
				req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
			}
			else if(session.getAttribute("advisorGoBack").equals("true")) {
				System.out.println("Navigating back to advisor home page");
				resp.sendRedirect(req.getContextPath() + "/PCP_AdvisorPage");
			}
			
		}
		
		// advisor wishes to save or discard their decisions
		// regarding student's media choices
		else if(mode.equals("advisorEdit")) {
			session.setAttribute("mode", "advisorView");
			System.out.println("Switching advisor mode from edit to view");
			// TODO: add logic to save dis/approved content
			if(Boolean.valueOf(req.getParameter("advisorSaveChanges"))) {
				Graduate grad = (Graduate) session.getAttribute("studentToView");
				InfoState newState = grad.getCurrentInfo();
				InfoState pendingState = grad.getPendingInfo();
				System.out.println("Advisor saved changes");
				// contents from jsp go: extra info(0), name pronunciation(1), display picture(2), slideshows(3-6), video(7)
				Boolean[] advisorChoices = constructAdvisorChoiceArray(req.getParameter("studentNewInfo"));
				for(int i = 0; i < advisorChoices.length; i++) {
					if(advisorChoices[i]){
						switch(i) {
						case 0:
							newState.setContentAtIndex(InfoState.EXTRAINFORMATION_INDEX, pendingState.getContentAtIndex(InfoState.EXTRAINFORMATION_INDEX));
						break;
						case 1:
							newState.setContentAtIndex(InfoState.NAMEPRONUNCIATION_INDEX, pendingState.getContentAtIndex(InfoState.NAMEPRONUNCIATION_INDEX));
						break;
						case 2:
							newState.setContentAtIndex(InfoState.PROFILE_INDEX, pendingState.getContentAtIndex(InfoState.PROFILE_INDEX));
						break;
						case 3:
							newState.setContentAtIndex(InfoState.SLIDESHOW1_INDEX, pendingState.getContentAtIndex(InfoState.SLIDESHOW1_INDEX));
						break;
						case 4:
							newState.setContentAtIndex(InfoState.SLIDESHOW2_INDEX, pendingState.getContentAtIndex(InfoState.SLIDESHOW2_INDEX));
						break;
						case 5:
							newState.setContentAtIndex(InfoState.SLIDESHOW3_INDEX, pendingState.getContentAtIndex(InfoState.SLIDESHOW3_INDEX));
						break;
						case 6:
							newState.setContentAtIndex(InfoState.SLIDESHOW4_INDEX, pendingState.getContentAtIndex(InfoState.SLIDESHOW4_INDEX));
						break;
						case 7:
							newState.setContentAtIndex(InfoState.VIDEO_INDEX, pendingState.getContentAtIndex(InfoState.VIDEO_INDEX));
						break;
						default:
							System.out.println("missing");
						break;
						}
					}
				}
				// debug
				
				/*
				for(ContentComponent content : pendingState.getContents()) {
					System.out.println("pending content: " + content.getContent());
				}
				for(ContentComponent content : grad.getCurrentInfo().getContents()) {
					System.out.println("current content: " + content.getContent());
				}
				for(ContentComponent content : newState.getContents()) {
					System.out.println("new content: " + content.getContent());
				}
				*/
			}
			else {
				System.out.println("Advisor didn't save changes");
			}
			
			req.getRequestDispatcher("/_view/PCP_StudentPage.jsp").forward(req, resp);
		}
		else {
			System.out.println("Invalid mode");
		}
		
		req.setAttribute("studentSaveChanges", studentSaveChanges);
	}
	
	private Boolean[] constructAdvisorChoiceArray(String parameter) {
		int beginSpace = 0;
		int counter = 0;
		Boolean[] resultArray = new Boolean[8];
		for(int i = 0; i < parameter.length(); i++) {
			if (parameter.charAt(i) == ',' || i == parameter.length() - 1) {
				if(parameter.charAt(beginSpace) == 't') {
					resultArray[counter] = true;
				}
				else {
					resultArray[counter] = false;
				}
				counter++;
				beginSpace = i + 1;
			}
		}
		return resultArray;
	}

	// writes a given file to a specified path depending on a given file type
	// also, checks for and deletes files with same name prior to uploading
	public void writeFile(Part filePart, String fileName, String fileType) throws IOException{
		OutputStream out = null;
	    InputStream filecontent = null;
	    String path = "";
	    if(fileType.equals("image")) {
	    	path = photoPath;
	    }
	    else if(fileType.equals("video")) {
	    	path = videoPath;
	    }
	    else if(fileType.equals("audio")) {
	    	path = audioPath;
	    }
	    else {
	    	System.out.println("Invalid file type");
	    }
	    String wholePath = path + File.separator + fileName;
	    File oldFile = new File(wholePath);
	    if(oldFile.delete()) {
	    	System.out.println("deleted old file: " + wholePath);
	    }
	    else {
	    	System.out.println("file wasn't found or unable to delete");
	    }
	    try {
	        out = new FileOutputStream(oldFile);
	        filecontent = filePart.getInputStream();
	        int read = 0;
	        byte[] bytes = new byte[1024 * 1024 * 5];
	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        System.out.println("File sucessfully created");
	    }finally {
	        if (out != null) {
	            out.close();
	        }
	        if (filecontent != null) {
	            filecontent.close();
	        }
	    }
	}
}