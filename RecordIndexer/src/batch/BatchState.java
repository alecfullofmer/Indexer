package batch;

import java.awt.Point;

import client.ClientFacade;
import client_communicator.DownloadBatch_result;


public class BatchState {


	private ClientFacade facade;
	private DownloadBatch_result downloadResult; 
	private String[][] table;
	private Point locationOnScreen;
	private int windowOriginX;
	private int windowOriginY;
	private int windowHeight;
	private int windowWidth;
	private int horizontalSplitPane;
	private int verticalSplitPane;
	private double scale;
	private int scroll;
	private boolean inverted;
	private int scrollPosition;
	private boolean highlights;
	
	public BatchState(ClientFacade facade)
	{
		this.facade = facade;
	}
	
	public boolean checkValue(int row, String value)
	{
		//check the value agains the knownvalues in that field.
		//
		return true;
	}
	
	public void setBatchResult(DownloadBatch_result result)
	{
		downloadResult = result;
		table = new String[downloadResult.getProj().getRecords_per_image()][downloadResult.getFields().size()];
	}
	
	public String[][] getTable()
	{
		return table;
	}
	
	public void addValue(String value, int row, int col)
	{
		table[row][col] = value;
		System.out.println(value);
	}
	
	public String getValue(int row, int col)
	{
		return table[row][col];
	}

	public DownloadBatch_result getDownloadResult() {
		return downloadResult;
	}

	public void setDownloadResult(DownloadBatch_result downloadResult) {
		this.downloadResult = downloadResult;
	}

	public int getWindowOriginX() {
		return windowOriginX;
	}

	public void setWindowOriginX(int windowOriginX) {
		this.windowOriginX = windowOriginX;
	}

	public int getWindowOriginY() {
		return windowOriginY;
	}

	public void setWindowOriginY(int windowOriginY) {
		this.windowOriginY = windowOriginY;
	}

	public int getWindowHeight() {
		return windowHeight;
	}

	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
	}

	public int getWindowWidth() {
		return windowWidth;
	}

	public void setWindowWidth(int windowWidth) {
		this.windowWidth = windowWidth;
	}

	public int getHorizontalSplitPane() {
		return horizontalSplitPane;
	}

	public void setHorizontalSplitPane(int horizontalSplitPane) {
		this.horizontalSplitPane = horizontalSplitPane;
	}

	public int getVerticalSplitPane() {
		return verticalSplitPane;
	}

	public void setVerticalSplitPane(int verticalSplitPane) {
		this.verticalSplitPane = verticalSplitPane;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public int getScroll() {
		return scroll;
	}

	public void setScroll(int scroll) {
		this.scroll = scroll;
	}

	public boolean isInverted() {
		return inverted;
	}

	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}

	public int getScrollPosition() {
		return scrollPosition;
	}

	public void setScrollPosition(int scrollPosition) {
		this.scrollPosition = scrollPosition;
	}

	public void setTable(String[][] table) {
		this.table = table;
	}

	public Point getLocationOnScreen() {
		return locationOnScreen;
	}

	public void setLocationOnScreen(Point locationOnScreen) {
		this.locationOnScreen = locationOnScreen;
	}

	public boolean isHighlights() {
		return highlights;
	}

	public void setHighlights(boolean highlights) {
		this.highlights = highlights;
	}
	
	
	
	
	
}
