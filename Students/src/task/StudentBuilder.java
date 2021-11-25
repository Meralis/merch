package task;

import java.util.Scanner;

public class StudentBuilder {

	public static Student buildStudent() {
		Scanner sc = new Scanner(System.in);
		String studentsName;
		String studentsLastName;
		String groupName = "IT";
		long id = 0;

		System.out.println("Input student's name");
		studentsName = sc.nextLine();
		System.out.println("Input student's last name");
		studentsLastName = sc.next();
		while (id == 0) {
			System.out.println("Input student's id");
			try {
				String buffer = sc.next();
				id = Long.parseLong(buffer);
			} catch (NumberFormatException e) {
				System.out.println("Wrong input");
			}
		}
		System.out.println("Input student's gender: male - 1; female - 2");
		sc = new Scanner(System.in);
		String x = sc.next();
		Gender gender = Gender.MALE;
		for (;;) {
			if ("1".equals(x)) {
				break;
			}
			if ("2".equals(x)) {
				gender = Gender.FEMALE;
				break;
			} else {
				System.out.println("Wrong input. Please try again: male - 1; female - 2");
				x = sc.next();
			}
		}
		Student student = new Student(groupName, id, studentsName, studentsLastName, gender);
		return student;
	}
}