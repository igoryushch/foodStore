package ua.foodstore.dao;

import java.math.BigDecimal;

/** 
* Additional auxiliary class to hold report data.
*
* @author <a href="mailto:igor_aviant@ukr.net">Igor Yushchenko</a>
* @version $ $Date: July 21, 2013 $
*/
public class ReportEntry 
{
	private String menuPosition;
	private String menuCategoryName;
	private long orderCount;
	private BigDecimal orderSum;
	
	
	
	/**
	 * @param menuPosition
	 * @param menuCategoryName
	 * @param orderCount
	 * @param orderSum
	 */
	public ReportEntry(String menuPosition, String menuCategoryName,
			long orderCount, BigDecimal orderSum) {
		this.menuPosition = menuPosition;
		this.menuCategoryName = menuCategoryName;
		this.orderCount = orderCount;
		this.orderSum = orderSum;
	}
	public ReportEntry()
	{
		
	}
	/**
	 * @return the menuPosition
	 */
	public String getMenuPosition() {
		return menuPosition;
	}
	/**
	 * @param menuPosition the menuPosition to set
	 */
	public void setMenuPosition(String menuPosition) {
		this.menuPosition = menuPosition;
	}
	/**
	 * @return the menuCategoryName
	 */
	public String getMenuCategoryName() {
		return menuCategoryName;
	}
	/**
	 * @param menuCategoryName the menuCategoryName to set
	 */
	public void setMenuCategoryName(String menuCategoryName) {
		this.menuCategoryName = menuCategoryName;
	}
	/**
	 * @return the orderCount
	 */
	public long getOrderCount() {
		return orderCount;
	}
	/**
	 * @param orderCount the orderCount to set
	 */
	public void setOrderCount(long orderCount) {
		this.orderCount = orderCount;
	}
	/**
	 * @return the orderSum
	 */
	public BigDecimal getOrderSum() {
		return orderSum;
	}
	/**
	 * @param orderSum the orderSum to set
	 */
	public void setOrderSum(BigDecimal orderSum) {
		this.orderSum = orderSum;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportEntry other = (ReportEntry) obj;
		if (menuCategoryName == null) {
			if (other.menuCategoryName != null)
				return false;
		} else if (!menuCategoryName.equals(other.menuCategoryName))
			return false;
		if (menuPosition == null) {
			if (other.menuPosition != null)
				return false;
		} else if (!menuPosition.equals(other.menuPosition))
			return false;
		if (orderCount != other.orderCount)
			return false;
		if (orderSum == null) {
			if (other.orderSum != null)
				return false;
		} else if (orderSum.compareTo(other.orderSum) != 0)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[ " + menuPosition
				+ " " + menuCategoryName + " "
				+ orderCount + " (items)   " + orderSum + " ]";
	}
	
}
