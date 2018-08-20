package infoGrab;

// Meta Data for the specific stock from AlphaVantage
public class MetaData {

	// Default Constructor
	public MetaData() {
		mInfo = "Undefined";
		mSymbol = "IDK";
		mRefreshed = "Unknown";
		mOutputSize = "Unknown";
		mTimeZone = "Unknown";
	}
	
	/** Full Constructor
	  @param aInfo
	  @param aSymbol
	  @param aRefreshed
	  @param aOutputSize
	  @param aTimeZone
	*/
	public MetaData(String aInfo, String aSymbol, String aRefreshed, String aOutputSize, String aTimeZone) {
		mInfo = aInfo;
		mSymbol = aSymbol;
		mRefreshed = aRefreshed;
		mOutputSize = aOutputSize;
		mTimeZone = aTimeZone;
	}
	
	public String getInfo() {
		return mInfo;
	}
	public String getSymbol() {
		return mSymbol;
	}
	public String getRefreshed() {
		return mRefreshed;
	}
	public String getOutputSize() {
		return mOutputSize;
	}
	public String getTimeZone() {
		return mTimeZone;
	}
	
	private final String mInfo;
	private final String mSymbol;
	private final String mRefreshed;
	private final String mOutputSize;
	private final String mTimeZone;
}
