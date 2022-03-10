package com.springboot.demo.email.model;

public class Email {
	
	private String to;
	private String subject;
	private String body;
	private String fileToAttach;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getFileToAttach() {
		return fileToAttach;
	}
	public void setFileToAttach(String fileToAttach) {
		this.fileToAttach = fileToAttach;
	}
	
	
	

}
