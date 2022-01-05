package task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Group {
	private String name;
	private List<Student> students = new ArrayList<Student>();

	public Group() {
		super();
	}

	public Group(String name, List<Student> students) {
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, students);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		return Objects.equals(name, other.name) && Objects.equals(students, other.students);
	}

	public void addStudent(Student student) throws GroupOverflowException {
		if (students.size() >= 10) {
			throw new GroupOverflowException("Too many students in group");
		} else {
			student.setGroupName(name);
			students.add(student);
		}
	}

	public void deleteStudent(long id) {
		Iterator<Student> itr = students.iterator();
		for (; itr.hasNext();) {
			Student studentToDelete = itr.next();
			if (studentToDelete.getId() == id) {
				students.remove(studentToDelete);
				return;
			}
		}
	}

	public void sortStudentsByLastName() {
		Collections.sort(students, new StudentsLastNameComparator());
	}

	public Student searchByLastName(String lastName) throws NoSuchStudentException {
		for (Student st : students) {
			if (st.getLastName().equals(lastName)) {
				return st;
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

	public boolean isStudentsUniq() {
		Set<Student> set = new HashSet<Student>(students);
		if (set.size() < students.size()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		sortStudentsByLastName();
		String print = " ";
		for (Student student : students) {
			print = "Name: " + student.getName() + "; " + "Last name: " + student.getLastName() + "; " + "Gender: "
					+ student.getGender() + "; " + "Id: " + student.getId() + "; " + "Group name: " + name + " "
					+ System.lineSeparator() + print;
		}
		return print;
	}
}