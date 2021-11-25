package task;

import java.util.Comparator;

public class StudentsLastNameComparator implements Comparator {
	@Override
	public int compare(Object o1, Object o2) {

		if (o1 == null) {
			return o2 == null ? 0 : -1;
		}
		if (o2 == null) {
			return 1;
		}
		Student students1 = (Student) o1;
		Student students2 = (Student) o2;

		String lastName1 = students1.getLastName();
		String lastName2 = students2.getLastName();

		if (lastName1.compareTo(lastName2) > 0) {
			return 1;
		}
		if (lastName1.compareTo(lastName2) < 0) {
			return -1;
		}
		return 0;
	}
}