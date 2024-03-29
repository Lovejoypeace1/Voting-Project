package com.VotingSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class VoterDao {
	private final static String USERNAME = "root";

	private final static String PASSWORD = "root";

	private final static String URL = "jdbc:mysql://localhost:3306/ems";
	static String path = "C:\\Users\\OneDrive\\Desktop\\votingproject\\";

	public void editVoter(Voter voter) throws IOException {
		// Find the line, store it in string builder and replace the content with new
		// employee object
		BufferedReader reader = new BufferedReader(new FileReader(path + "Voter.txt"));
		String line = reader.readLine();
		StringBuilder builder = new StringBuilder();
		while (line != null) {
			String[] values = line.split(", ");
			boolean matches = false;
			for (String val : values) {
				if (val.contains("id") && val.equals("id=" + voter.getVoterId())) {
					matches = true;
					break;
				}
			}
			if (matches) {
				builder.append(voter.toString());
			} else {
				builder.append(line);
			}
			builder.append("\n");
			line = reader.readLine();
		}
		reader.close();

		BufferedWriter bw = new BufferedWriter(new FileWriter(path + "VoterList.txt"));
		bw.write(builder.toString());
		bw.write("\n");
		bw.flush();

		bw.close();

	}

	public void viewAll() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path + "VoterList.txt"));
		String line = reader.readLine();

		while (line != null) {
			System.out.println(line);
			line = reader.readLine();
		}
	}

	Voter searchById(Integer id) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path + "VoterList.txt"));

		String line = reader.readLine();
		Voter voter = null;

		while (line != null) {
			boolean matches = false;
			String[] values = line.split(", ");

			String firstName = null;
			String lastName = null;

			for (String val : values) {
				if (val.contains("id") && val.equals("id=" + id)) {
					matches = true;
				}

				if (val.contains("id=")) {
					String vals[] = val.split("=");
					id = Integer.parseInt(vals[1]);
				} else if (val.contains("firstName=")) {
					String vals[] = val.split("=");
					firstName = vals[1];
				} else if (val.contains("lastName=")) {
					String vals[] = val.split("=");
					lastName = vals[1];
				}
			}

			if (matches) {
				voter = new Voter();
				voter.setVoterId(id);
				voter.setFirstName(firstName);
				voter.setLastName(lastName);
				return voter;
			}
		}
		return voter;
	}

}
