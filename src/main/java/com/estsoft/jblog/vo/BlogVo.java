package com.estsoft.jblog.vo;

public class BlogVo 
{
	private Long no;
	private String id;
	private String title;
	private String image;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "BlogVo [no=" + no + ", id=" + id + ", title=" + title + ", image=" + image + "]";
	}
	
	
	
	
}
