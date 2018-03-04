package com.myjo.domain;

public class Oc_Info {

	private int id;
	private String status;
	
	public Oc_Info() {
		// TODO Auto-generated constructor stub
	}

	public Oc_Info(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Oc_Info [id=" + id + ", status=" + status + "]";
	}

	
}
