package com.emailserviceapp.springboot.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

class CSVReader {
	// static List<Subscriber> parseCSV(String path) throws IOException {
	static List<EmailInfo> parseCSV(File csvFile) throws IOException {

		System.out.println("Inside CSVReader....!!!");

		List<EmailInfo> emailInfoList = new ArrayList<>();

		// Reader reader = Files.newBufferedReader(Paths.get(path));

		Reader reader = new BufferedReader(new FileReader(csvFile));

		try {

			CSVParser csvParser = new CSVParser(reader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			for (CSVRecord csvRecord : csvParser) {

				emailInfoList.add(new EmailInfo(csvRecord.get("Firstname"), csvRecord.get("Lastname"), csvRecord.get("Email"),
						csvRecord.get("Phone"), csvRecord.get("Gender"), csvRecord.get("Username")));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return emailInfoList;
	}
}