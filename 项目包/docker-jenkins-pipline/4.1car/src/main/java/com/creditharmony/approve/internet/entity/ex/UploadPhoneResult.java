package com.creditharmony.approve.internet.entity.ex;

import com.creditharmony.approve.phone.entity.DhzhDhlyxx;

/**
 * 下载意见书中的电话审核结果
 * @Class Name UploadPhoneResult
 * @author 刘燕军
 * @Create In 2016年5月25日
 */
public class UploadPhoneResult extends DhzhDhlyxx{

	private static final long serialVersionUID = 1L;

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
