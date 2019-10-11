package kr.co.jblog.vo;

public class PostVo {
	
	private Long postNo;
	private String title;
	private String contents;
	private String regDate;
	private String categoryNo;
	
	public Long getPostNo() {
		return postNo;
	}
	public void setPostNo(Long postNo) {
		this.postNo = postNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}
	
	@Override
	public String toString() {
		return "PostVo [postNo=" + postNo + ", title=" + title + ", contents=" + contents + ", regDate=" + regDate
				+ ", categoryNo=" + categoryNo + "]";
	}
}
