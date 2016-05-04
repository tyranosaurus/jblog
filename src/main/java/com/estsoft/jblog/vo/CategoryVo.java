package com.estsoft.jblog.vo;

public class CategoryVo 
{
	private Long no;
	private Long blogNo;
	private String name;
	private String description;
	private Long postNo;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getBlogNo() {
		return blogNo;
	}
	public void setBlogNo(Long blogNo) {
		this.blogNo = blogNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPostNo() {
		return postNo;
	}
	public void setPostNo(Long postNo) {
		this.postNo = postNo;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", blogNo=" + blogNo + ", name=" + name + ", description=" + description
				+ ", postNo=" + postNo + "]";
	}
	
	
	
	
}
