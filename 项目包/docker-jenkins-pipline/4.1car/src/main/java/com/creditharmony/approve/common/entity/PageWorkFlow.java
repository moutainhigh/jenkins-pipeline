package com.creditharmony.approve.common.entity;

import com.creditharmony.bpm.frame.view.FlowPage;

public class PageWorkFlow extends FlowPage{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 默认输出当前分页标签 
	 * <div class="page">${page}</div>
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		
		if (getPageNo() == getFirst()) {// 如果是首页
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">&#171; 上一页</a></li>\n");
		} else {
			sb.append("<li><a href=\"javascript:\" onclick=\""+getFuncName()+"("+getPrev()+","+getPageSize()+",'"+getFuncParam()+"');\">&#171; 上一页</a></li>\n");
		}

		int begin = getPageNo() - (8 / 2);

		if (begin < getFirst()) {
			begin = getFirst();
		}

		int end = begin + 8 - 1;

		if (end >= getLast()) {
			end = getLast();
			begin = end - 8 + 1;
			if (begin < getFirst()) {
				begin = getFirst();
			}
		}

		if (begin > getFirst()) {
			int i = 0;
			for (i = getFirst(); i < getFirst() + 1 && i < begin; i++) {
				sb.append("<li><a href=\"javascript:\" onclick=\""+getFuncName()+"("+i+","+getPageSize()+",'"+getFuncParam()+"');\">"
						+ (i + 1 - getFirst()) + "</a></li>\n");
			}
			if (i < begin) {
				sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
			}
		}

		for (int i = begin; i <= end; i++) {
			if (i == getPageNo()) {
				sb.append("<li class=\"active\"><a href=\"javascript:\">" + (i + 1 - getFirst())
						+ "</a></li>\n");
			} else {
				sb.append("<li><a href=\"javascript:\" onclick=\""+getFuncName()+"("+i+","+getPageSize()+",'"+getFuncParam()+"');\">"
						+ (i + 1 - getFirst()) + "</a></li>\n");
			}
		}

		if (getLast() - end > 1) {
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
			end = getLast() - 1;
		}

		for (int i = end + 1; i <= getLast(); i++) {
			sb.append("<li><a href=\"javascript:\" onclick=\""+getFuncName()+"("+i+","+getPageSize()+",'"+getFuncParam()+"');\">"
					+ (i + 1 - getFirst()) + "</a></li>\n");
		}

		if (getPageNo() == getLast()) {
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">下一页 &#187;</a></li>\n");
		} else {
			sb.append("<li><a href=\"javascript:\" onclick=\""+getFuncName()+"("+getNext()+","+getPageSize()+",'"+getFuncParam()+"');\">"
					+ "下一页 &#187;</a></li>\n");
		}

		sb.append("<li class=\"disabled controls\"><a href=\"javascript:\">当前 ");
		sb.append("<input type=\"text\" style=\"width:40px;height:16px;\" value=\""+getPageNo()+"\" onkeypress=\"var e=event||window.event||this;var c=e.keyCode||e.which;if(c==13)");
		sb.append(getFuncName()+"(this.value,"+getPageSize()+",'"+getFuncParam()+"');\" onclick=\"this.select();\"/> / ");
		sb.append("<input type=\"text\" style=\"width:40px;height:16px;\" value=\""+getPageSize()+"\" onkeypress=\"var e=event||window.event||this;var c=e.keyCode||e.which;if(c==13)");
		sb.append(getFuncName()+"("+getPageNo()+",this.value,'"+getFuncParam()+"');\" onclick=\"this.select();\"/> 条，");
		sb.append("共 " + getCount() + " 条");
		sb.append(" 每页<input type=\"text\" style=\"width:40px;height:16px;\" value=\"\" onkeypress=\"var e=event||window.event||this;var c=e.keyCode||e.which;if(c==13)");
		sb.append(getFuncName()+"("+getPageNo()+",this.value,'"+getFuncParam()+"');\" onblur=\""+getFuncName()+"("+getPageNo()+",this.value,'"+getFuncParam()+"');\"/> 条");

		sb.insert(0,"<ul class=\"pagination\">\n").append("</ul>\n");
		
		sb.append("<div style=\"clear:both;\"></div>");

//		sb.insert(0,"<div class=\"page\">\n").append("</div>\n");
		
		return sb.toString();
	}
	public PageWorkFlow(){
		super.setPageSize(15);
	}
}
