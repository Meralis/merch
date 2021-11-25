package task;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Group group = buildGroup();
		group.setName("IT");
		try {
			for (int i = 0; i < 10; i++) {
				group.addStudent(StudentBuilder.buildStudent());
			}
		} catch (GroupOverflowException e) {
			System.out.println("Too many students in group");
		}
		System.out.println("======Searching student by last name======");
		System.out.println("Input student's last name to search");
		Scanner sc = new Scanner(System.in);
		String ln = sc.nextLine();
		try {
			Student myStudent = group.searchByLastName(ln);
			System.out.println("The student is found: " + myStudent);
		} catch (NoSuchStudentException e) {
			System.out.println("last name not found");
		}
		System.out.println("======Deleting student by id======");
		group.chooseIdToDelete();

		GroupFileStorage groupFileStorage = new GroupFileStorage();
		groupFileStorage.saveGroupToCSV(group);

		File fileCSV = new File(groupFileStorage.getBaseDirectoryName() + "\\" + group.getName() + ".csv");
		try {
			GroupFileStorage.loadGroupFromCSV(fileCSV);
		} catch (GroupOverflowException e) {
			System.out.println("Too many students in group");
		} catch (IOException e) {

			e.printStackTrace();
		}
		System.out.println("======Searching group by name======");
		System.out.println("Input the group name to search");
		String searchGroup = sc.nextLine();
		String searchDirectory = "C:\\Temp";
		File searchDir = new File(searchDirectory);
		File foundFile = GroupFileStorage.findFileByGroupName(searchGroup, searchDir);
		if (foundFile == null) {
			System.out.println("No data found for group name " + searchGroup + " in folder " + searchDir);
		} else
			System.out.println("There is the file " + foundFile.getAbsolutePath());
	}

	public static Group buildGroup() {
		return new Group();
	}
}