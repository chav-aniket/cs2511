package unsw.enrolment;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Log {
	
	private Enrolment enrolment;
	
	public Log(Enrolment enrolment) {
		if (enrolment != null) {
			this.enrolment = enrolment;
			enrolment.subscribe(this);
		}
	}

	public String getFileName() {
		return enrolment.getCourse().getCourseCode() + "-" + enrolment.getTerm() + "-" + enrolment.getStudent().getZID();
	}

	public String getLocation() {
		return "src/unsw/enrolment/" + getFileName();
	}

	public void update(GradeInterface grade) {
		DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String text = d.format(now) + " " + grade.getName() + " " + grade.getMark();
		try {
			FileWriter f = new FileWriter(getLocation(), true);
			PrintWriter p = new PrintWriter(f);
			p.println(text);
			p.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.toString());
		}
	}
}