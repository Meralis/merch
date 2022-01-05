package task;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class GroupFileStorage {

	public static void saveGroupToCSV(Group gr) throws IOException {
		String fileName = "C:\\Temp\\" + gr.getName() + ".csv";
		try (OutputStream os = new FileOutputStream(fileName); PrintWriter groupFile = new PrintWriter(os)) {
			for (Student student : gr.getStudents()) {
					groupFile.println(student.toCSVString());
				}
			}
		}

	public static Group loadGroupFromCSV(File file) throws GroupOverflowException, IOException {
		try (FileReader fileReader = new FileReader(file); BufferedReader buffer = new BufferedReader(fileReader)) {
			Group group = new Group();
			String line = buffer.readLine();
			Student student = new Student();
			while (line != null) {
				Student st = student.fromCSVString(line);
				group.addStudent(st);
				line = buffer.readLine();
			}
			String[] parts = file.getName().split("[.]");
			group.setName(parts[0]);
			return group;
		}
	}

	public static File findFileByGroupName(String groupName, File workFolder) {
		String fullFilename = groupName + ".csv";
		for (File inFile : workFolder.listFiles()) {
			if (inFile.getName().equalsIgnoreCase(fullFilename)) {
				System.out.println("The group " + groupName + " was found");
				return inFile;
			}
		}
		return null;
	}
}
