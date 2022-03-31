package com.gzeinnumer.mypagingstyle.base.response;

import com.google.gson.annotations.SerializedName;

public class Info{

	@SerializedName("next")
	private Object next;

	@SerializedName("total")
	private int total;

	@SerializedName("totalPage")
	private Object totalPage;

	@SerializedName("prev")
	private Object prev;

	@SerializedName("page")
	private Object page;

	public Object getNext(){
		return next;
	}

	public int getTotal(){
		return total;
	}

	public Object getTotalPage(){
		return totalPage;
	}

	public Object getPrev(){
		return prev;
	}

	public Object getPage(){
		return page;
	}
}