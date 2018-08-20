package infoGrab;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.json.*;

// Object for the current stock being viewed
public class Stock {

	// Default Constructor
	public Stock() {
		this("TIME_SERIES_DAILY", "MSFT", "compact", "OCE2OC5U5ILE52T1");
	}
	/** Full Constructor
	 * @param aFunction type of data you want pulled from AlphaVantage
	 * @param aStock Symbol for stock you want, eg. MSFT, SNAP, FB
	 * @param aSize either full or compact, full is complete data set, compact is latest 100 data points
	 * @param aAPIKey, use user's own apikey, or DEMO to use the demo key
	 * Populate metatable and entryList through AlphaVantage API
	 */
	public Stock(String aFunction, String aStock, String aSize) {
		this(aFunction, aStock, aSize, "OCE2OC5U5ILE52T1");
	}
	
	public Stock(String aFunction, String aStock, String aSize, String aAPIKey) {
		
		String url = "https://www.alphavantage.co/query?function=" + aFunction + "&symbol=" 
				+ aStock + "&outputsize=" + aSize + "&apikey=" + aAPIKey;
		try {
			JSONObject json = JsonReader.readFromURL(url);
			JSONObject meta = json.getJSONObject("Meta Data");
			// Populates the metadata member variable using information from the JSON file grabbed
			mMetaData = new MetaData(meta.getString("1. Information"), meta.getString("2. Symbol"), 
					meta.getString("3. Last Refreshed"), meta.getString("4. Output Size"), meta.getString("5. Time Zone"));
			String key = "";
			Set<String> keys = json.keySet();
			keys.remove("Meta Data");
			
			// Grabs the JSONObject of the financial data set
			JSONObject dataSet = json.getJSONObject(keys.iterator().next());

			// Uses an iterator to go through and convert JSONObjects to entry data 
			Iterator<String> it = dataSet.keys();
			mEntryList = new ArrayList<Entry>();
			// Loops through, creating entries and adding them to the entryList
			while(it.hasNext()) {
				key = it.next();
				JSONObject currEntry = dataSet.getJSONObject(key);
				Entry entry = new Entry(dateParser(key), currEntry.getDouble("1. open"),currEntry.getDouble("2. high"), 
						currEntry.getDouble("3. low"), currEntry.getDouble("4. close"), currEntry.getInt("5. volume"));
				mEntryList.add(entry);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Lambda expression to sort entryList by date
		Collections.sort(mEntryList, 
                (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
	}
	
	// Getters
	public List<Entry> getEntries() {
		return mEntryList;
	}
	public MetaData getMetaData() {
		return mMetaData;
	}
	
	// Privates
	
	// Parses date from string and returns Date object
	private Date dateParser(String input) {
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		parser.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date parsed = null;
		
		try {
			parsed = parser.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsed;
	}
	
	private MetaData mMetaData;
	private List<Entry> mEntryList;
}
