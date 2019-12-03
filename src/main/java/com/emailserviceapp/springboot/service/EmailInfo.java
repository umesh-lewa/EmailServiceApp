package com.emailserviceapp.springboot.service;

public class EmailInfo {
	
  private String Firstname, Lastname, Email, Phone ,Gender ,Username;
  public EmailInfo() {}
  public EmailInfo(String Firstname, String Lastname, String Email, String Phone, String Gender, String Username) {
	  System.out.println("Inside Subscriber....!!!");  
  this.Firstname = Firstname;
  this.Lastname = Lastname;
  this.Email = Email;
  this.Phone = Phone;
  this.Gender = Gender;
  this.Username = Username;
  }
  
  public String getFirstname() {
    return Firstname;
  }
  public String getLastname() {
    return Lastname;
  }
  public String getEmail() {
    return Email;
  }
  public String getPhone() {
    return Phone;
  }
  public String getGender() {
	    return Gender;
  }
  public String getUsername() {
	    return Username;
  }
  
  
  public void setFirstname(String Firstname) {
    this.Firstname = Firstname;
  }
  public void setLastname(String Lastname) {
    this.Lastname = Lastname;
  }
  public void setEmail(String Email) {
    this.Email = Email;
  }
  public void setPhone(String Phone) {
    this.Phone = Phone;
  }
  public void setGender(String Gender) {
	    this.Gender = Gender;
  }	  
  public void setUsername(String Username) {
	    this.Username = Username;
  }
  
}