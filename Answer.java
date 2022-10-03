package com.example.onlineExam4;

public class Answer {
	
	int qno;
	String question;
	String submittedanswer;
	String originalanswer;
	
	public int getQno() {
		return qno;
	}
	public void setQno(int qno) {
		this.qno = qno;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getSubmittedanswer() {
		return submittedanswer;
	}
	public void setSubmittedanswer(String submittedanswer) {
		this.submittedanswer = submittedanswer;
	}
	public String getOriginalanswer() {
		return originalanswer;
	}
	public void setOriginalanswer(String originalanswer) {
		this.originalanswer = originalanswer;
	}
	@Override
	public String toString() {
		return "Answer [qno=" + qno + ", question=" + question + ", submittedanswer=" + submittedanswer
				+ ", originalanswer=" + originalanswer + "]";
	}
	

}
