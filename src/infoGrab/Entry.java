package infoGrab;

import java.util.Calendar;
import java.util.Date;

// Entry is a singular time entry from the AlphaVantage API 
public class Entry {
	// Default constructor
	public Entry() {
		mDate = Calendar.getInstance().getTime();
		mOpen = 0.00;
		mHigh = 0.00;
		mLow = 0.00;
		mClose = 0.00;
		mVolume = 0;
	}
	/**
	   Full constructor. 
	   @param aDate day of stock information (required)
	   @param aOpen opening price of stock (required)
	   @param aHigh highest price of stock in time period (required) 
	   @param aLow lowest price of stock in time period (required)
	   @param aClose closing price of stock (required)
	   @param aVolume number of trades of the stock in the given time period 
	  */
	public Entry(Date aDate, double aOpen, double aHigh, double aLow, double aClose, int aVolume) {
		mDate = aDate;
		mOpen = aOpen;
		mHigh = aHigh;
		mLow = aLow;
		mClose = aClose;
		mVolume = aVolume;
	}
	
	public Date getDate() {
		return mDate;
	}
	public double getOpen(){
		return mOpen;
	}
	public double getHigh(){
		return mHigh;
	}
	public double getLow(){
		return mLow;
	}
	public double getClose(){
		return mClose;
	}
	public double getVolume(){
		return mVolume;
	}

	// Privates
	
	private final Date mDate;
	private final double mOpen;
	private final double mHigh;
	private final double mLow;
	private final double mClose;
	private final int mVolume;
	
}
