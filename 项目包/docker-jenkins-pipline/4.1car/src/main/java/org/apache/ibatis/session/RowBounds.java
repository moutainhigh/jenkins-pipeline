package org.apache.ibatis.session;

public class RowBounds {

	public final static int NO_ROW_OFFSET = 0;
	public final static int NO_ROW_LIMIT = Integer.MAX_VALUE;
	public final static RowBounds DEFAULT = new RowBounds();

	private int offset;
	private int limit;
	
	/**
	 * 新增的属性,方便count语句查询
	 */
	private String countBy;
	/** 过滤orderBy,"true"-过滤掉order by; "false"-不过滤order by **/
	private String filterOrderBy; 

	public RowBounds() {
		this.offset = NO_ROW_OFFSET;
		this.limit = NO_ROW_LIMIT;
	}

	public RowBounds(int offset, int limit) {
		this.offset = offset;
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}

	public String getCountBy() {
		return countBy;
	}

	public void setCountBy(String countBy) {
		this.countBy = countBy;
	}

	public String getFilterOrderBy() {
		return filterOrderBy;
	}

	public void setFilterOrderBy(String filterOrderBy) {
		this.filterOrderBy = filterOrderBy;
	}

	
}
