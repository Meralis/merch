package task;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Group {
	private String name;
	private Student[] students = new Student[10];

	public Group() {
		super();
	}

	public Group(String name, Student[] students) {
		super();
		this.name = name;
		this.students = students;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student[] getStudents() {
		return students;
	}

	public void setStudents(Student[] students) {
		this.students = students;
	}

	public void addStudent(Student student) throws GroupOverflowException {
		for (int i = 0; i < students.length; i++) {
			if (students[i] == null) {
				students[i] = student;
				student.setGroupName(name);
				return;
			}
		}
		throw new GroupOverflowException("Too many students in group");
	}

	public void deleteStudent(long id) {
		for (int i = 0; i < students.length; i++) {
			if (students[i] != null && students[i].getId() == id) {
				students[i] = null;
				return;
			}
		}
	}

	public void sortStudentsByLastName() {
		Arrays.sort(students, new StudentsLastNameComparator());
	}

	public Student searchByLastName(String lastName) throws NoSuchStudentException {
		for (int i = 0; i < students.length; i++) {
			if (students[i] != null && students[i].getLastName().equals(lastName)) {
				return students[i];
			}
		}
		throw new NoSuchStudentException("last name not found");
	}

	public void chooseIdToDelete() {
		System.out.println(toString());
		Scanner sc = new Scanner(System.in);
		long idForDelete;
		System.out.println("Input student's id to delete from group");
		try {
			idForDelete = sc.nextLong();
			deleteStudent(idForDelete);
		} catch (InputMismatchException e) {
			System.out.println("Wrong input");
		}
		System.out.println(toString());
	}

	@Override
	public String toString() {
		sortStudentsByLastName();
		String print = " ";
		for (Student student : students) {
			if (student == null) {
				continue;
			}
			print = "Name: " + student.getName() + "; " + "Last name: " + student.getLastName() + "; " + "Gender: "
					+ student.getGender() + "; " + "Id: " + student.getId() + "; " + "Group name: " + name + " "
					+ System.lineSeparator() + print;
		}
		return print;
	}
}