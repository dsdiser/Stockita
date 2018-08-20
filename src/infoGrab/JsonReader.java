package infoGrab;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonReader {
	
	public static JSONObject readFromURL(String iURL) throws IOException{
		// Connect to the URL using java's native library
	    URL url = new URL(iURL);
	    URLConnection request = url.openConnection();
	    request.connect();

	    // Convert to a JSON object to print data
	    JsonParser jp = new JsonParser(); //from gson
	    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
	    JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.
	    
	    return new JSONObject(rootobj.toString());
		//Convert Gson's json object to org.json json object
	}

}
