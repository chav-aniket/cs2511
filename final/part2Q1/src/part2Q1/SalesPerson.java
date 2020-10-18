package part2Q1;

public class SalesPerson extends Employee {
	private double salesTarget;
	private double salesAchieved;

	private float commission;

	public SalesPerson(String title, String firstName, String lastName, int quota) {
		// super(title, firstName, lastName, quota);
		super(title, firstName, lastName);
		this.salesTarget = quota;
	}

	public double calculateSalary() {
		double totalSal;
		totalSal = super.getBaseSalary() + commission * getSalesAchieved()
		         + super.calculateParkingFringeBenefits()
		         - super.calculateTax();
		return totalSal;
	}

	// Below two functions are moved from Employee as the other subclass Engineer
	//	has no need for them

	public double getSalesTarget() {
		return salesTarget;
	}

	public double getSalesAchieved() {
		return salesAchieved;
	}

	// getSalesSummary is copied from SalesHistory.java as it was a lazy class,
	//	explained further within SalesHistory.java
	public String getSalesSummary() {
		return getFirstName() + getLastName() + "Sales Target: " + getSalesTarget() + "$\n" +
			    "Sales to date: " + getSalesAchieved() + "$";
	}

}
