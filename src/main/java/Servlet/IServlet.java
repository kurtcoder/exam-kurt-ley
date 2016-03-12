package Servlet;

import Bean.AlchemyConnector;
import Bean.SetOperations;
import Bean.RedisConnector;
import java.io.*;
import java.net.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.*;


@WebServlet(name = "IServlet", urlPatterns = {"/IServlet"})
public class IServlet extends HttpServlet {

	private String FACE_ENDPOINT_URL = "http://gateway-a.watsonplatform.net/calls/url/URLGetRankedImageFaceTags";

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	
		AlchemyConnector connector = new AlchemyConnector();
		JSONParser parser = new JSONParser();
		String input_url = (String) request.getParameter("gurl");
		StringBuilder sb = new StringBuilder();
		String line;
		
		URL face_url = new URL(FACE_ENDPOINT_URL+"?url="+input_url+"&apikey="+connector.getAPIKey()+"&outputMode=json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(face_url.openStream()));
		while ((line = reader.readLine()) != null){
			sb.append(line);
		}
		
		//get JSON Output for Image Extraction
		
				request.setAttribute("face",sb.toString());
		
		// Convert and Parse JSON
		
//		String results = sb.toString();
		
	//	Object jsonResult = parser.parse(results);
		//JSONObject jsObject = (JSONObject) jsonResult;
		//JSONArray finalhere = (JSONArray) jsObject.get("imageFaces");
		
			//JSONObject innerfinal = (JSONObject) finalhere.get(0);
			//String agehere = "" + innerfinal.get("age");
			//String genderhere = "" + innerfinal.get("gender");
			
		
			
		//request.setAttribute("age", agehere);
		//request.setAttribute("gender", genderhere);
		
		
		//enter parsed JSON into a redis sortedset
	
		SetOperations so = new SetOperations("entries");
		request.setAttribute("setoper", so);

		String pAge = sb.toString(); //age or gender
		so.add(0, pAge); // age
		String pGender = sb.toString(); //age or gender
		so.add(0, pGender); // gender

		request.setAttribute ("agegender", so.toString());
		request.setAttribute ("age", so.toString());
		request.setAttribute ("gender", so.toString());
		

		response.setContentType("text/html");
        response.setStatus(200);
        request.getRequestDispatcher("index.jsp").forward(request, response);
		response.sendRedirect("viewinfo.jsp");

	}

}

