package com.emailserviceapp.springboot.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.JSONException;
import org.json.JSONObject;

class JSONReader {
	String user;
	String host;
	String port;
	String password;
	String subcsvpath;
	String subject;
	String htmlpath;

	JSONReader(Path configpath) throws JSONException, IOException {

		System.out.println("Inside JSONReader....!!!");

		// String jsontxt = new String(Files.readAllBytes(configpath));
		// JSONObject json = new JSONObject(jsontxt);

		// hard-coded for now
		// TODO Remove hardcoding
		host = "smtp.gmail.com";
		port = "587";
		// user = "umesh.lewa@gmail.com";
		// password = "";
		subcsvpath = "C:\\Users\\umesh\\Desktop\\Coding_Stuff\\klenty\\demo.csv";
		subject = "Hello There ! This is Prasasth from Klenty !";
		// htmlpath = " ";

		/*
		 * host = json.getString("host"); port = json.getString("port"); user =
		 * json.getString("user"); password = json.getString("password"); subcsvpath =
		 * json.getString("subcsvpath"); subject = json.getString("emailsubject");
		 * htmlpath = json.getString("emailhtmlpath");
		 */
	}
}