package globoMavenID;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class ParseURL {
	
	public static int space = 4;
	
	public static void main(String[] args) throws JSONException {
		
		try {
			String webPage = "https://revistaautoesporte.globo.com/rss/ultimas/feed.xml";
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			
			String resultado = sb.toString(); //converte para string
			JSONObject feed = XML.toJSONObject(resultado); //converte XML para objeto
			
			String jsonString = feed.toString(space); //indentacao
			
			System.out.println(jsonString);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}