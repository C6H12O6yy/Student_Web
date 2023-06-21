package com.example.demo;

public class Student {
	private int id;
	private String name;
	private String dob;
	private String major;
	private boolean vaccinated;
	public Student() {
		
	}
	public Student(int id, String name, String dob, String major, boolean vaccinated) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.major = major;
		this.vaccinated = vaccinated;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public boolean isVaccinated() {
		return vaccinated;
	}
	public void setVaccinated(boolean vaccinated) {
		this.vaccinated = vaccinated;
	}
	
}
