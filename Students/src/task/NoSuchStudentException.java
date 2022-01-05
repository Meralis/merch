package task;

public class NoSuchStudentException extends Exception {

	public NoSuchStudentException() {
	}

	public NoSuchStudentException(String message) {
		super(message);
	}
}
