/**@author Isaac Angle
 * this program will make a spider that looks through urls
 * and finds emails and sticks them into a set. It then prints the
 * set out
 */ 
package spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadWebsite {
	
	private Map<String,Boolean> urls = new HashMap<String,Boolean>();
	private URL url;
	private Set<String> emails = new HashSet<String>();
	private Iterator it;
	private static int counter = 0;
	private String line;
	private String tempUrl;
	private String theURL = "https://www.kaiseraluminum.com/contact-us/";
	
	ReadWebsite(){
		//setting up intial paramaters for the while loop
		urls.put(theURL, false);
		try {
			url = new URL("https://www.kaiseraluminum.com/contact-us/");
		} catch(Exception e) {
			System.out.println("unable to open url");
		}	
		//iterator to run through the map
		it = urls.entrySet().iterator();
			do {
				if(counter < 100) {
				try {
					//opens webpage
					BufferedReader rdr = new BufferedReader(new InputStreamReader(url.openStream()));
					while((line = rdr.readLine()) != null) {
						//looks for emails and urls
						Pattern email = Pattern.compile("\"mailto:(.*?)\"");
						Matcher emailMatcher = email.matcher(line);
						Pattern link = Pattern.compile("href=\"(.*?)\"");
						Matcher linkMatcher = link.matcher(line);
						//checks to see if an email or url is found
						if(emailMatcher.find()) {
							emails.add(emailMatcher.group(1));
						} else if(linkMatcher.find()) {
							urls.put(linkMatcher.group(1),false);
							tempUrl = linkMatcher.group(1);
							System.out.println(line);
						}
					}
				//catches an exception
				} catch (IOException e) {
					e.printStackTrace();
				}
				//checks to see if the urls have been visited yet
				if(!urls.get(tempUrl)) {
					try {
						urls.replace(theURL, true);
						url = new URL(tempUrl);
						theURL = tempUrl;
					} catch (MalformedURLException e) {
						
						e.printStackTrace();
					}
				}
				//increases the counter to keep the spider from going on forever
				counter++;
				} else {
					break;
				}
			}while(it.hasNext());
			//prints out the emails
		for(int i = 0; i<emails.size(); i++) 
			System.out.printf("Email: %s\n", emails.toArray()[i]);
	}
}

