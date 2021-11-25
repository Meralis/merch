package task;

public class Student extends Human implements CSVConverter {
	private String groupName;
	private long id;

	public Student() {
		super();
	}

	public Student(String name, String lastName, Gender gender) {
		super(name, lastName, gender);
	}

	public Student(String groupName, long id, String name, String lastName, Gender gender) {
		super(name, lastName, gender);
		this.groupName = groupName;
		this.id = id;
	}

	public Student(long id, String name, String lastName, Gender gender) {
		super(name, lastName, gender);
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toCSVString() {
		return id + "," + getName() + "," + getLastName() + "," + getGender();
	}

	@Override
	public Student fromCSVString (String str) {
		String[] words = str.split(",");
		return new Student(Long.valueOf(words[0]), words[1], words[2], Gender.valueOf(words[3]));
	}

	@Override
	public String toString() {
		return "Student [groupName=" + " " + groupName + ", id=" + " " + id + super.toString() + "]";
	}
}
