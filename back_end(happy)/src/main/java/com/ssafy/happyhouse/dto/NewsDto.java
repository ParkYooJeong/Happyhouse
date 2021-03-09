package com.ssafy.happyhouse.dto;

public class NewsDto {
	String title;
	String content;
	String link;
	String img;

	public NewsDto() {
		super();
	}

	public NewsDto(String title, String content, String link, String img) {
		super();
		this.title = title;
		this.content = content;
		this.link = link;
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "title=" + title + ", content=" + content + ", link=" + link + ", img=" + img + ", getTitle()="
				+ getTitle() + ", getContent()=" + getContent() + ", getLink()=" + getLink() + ", getImg()=" + getImg();
	}

}
