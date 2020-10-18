package part2Q1;

public class Employee {
	private String title;
	private String firstName;
	private String lastName;
	// private double salesTarget;
	// private double salesAchieved;
	private double baseSalary;

	public Employee (String title, String firstName, String lastName) {
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		// this.salesTarget = quota;
		// quota parameter 
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	// Below function moved to SalesPerson.java as Engineer does not require it

	// public double getSalesTarget() {
	// 	return salesTarget;
	// }

	public String getTitle() {
		return title;
	}

	// Below function moved to SalesPerson.java accordingly to Refused Bequest smell

	// public double getSalesAchieved() {
	// 	return salesAchieved;
	// }

	public double getBaseSalary() {
		return baseSalary;
	}

	public double calculateTax() {
		double tax = 0;
		double salary = baseSalary;
		if (salary > 50000) {
		    tax += 0.3*(salary - 50000);
		}
		if (salary > 30000) {
		    tax += 0.2*(salary - 30000);
		}
		return tax;
	}

	public double calculateParkingFringeBenefits() {
		return (baseSalary - 50000) / 10000;
	}


	


}
