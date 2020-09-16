/**
 * 
 */
package bcit.ca.comp1451.assignment2;

/**
 * @author CHEN ZENG
 *
 */
public abstract class ProjectInvoice implements Comparable<ProjectInvoice> {
	private static int UNIQUE_NUM = 10000;

	private String invoiceNumber;
	private String projectName;
	private int numberOfWorkingHours;
	private double hourlyRate;

	/**
	 * @param invoiceNumber
	 * @param projectName
	 * @param numberOfWorkingHours
	 * @param hourlyRate
	 */
	public ProjectInvoice(String projectName, int numberOfWorkingHours, double hourlyRate) {
		this.setProjectName(projectName);
		this.setNumberOfWorkingHours(numberOfWorkingHours);
		this.setHourlyRate(hourlyRate);
		createInvoiceNumber();
	}

	/**
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		if (projectName != null && !projectName.isEmpty()) {
			this.projectName = projectName;
		} else {
			throw new IllegalArgumentException("project name cannot be null or empty");
		}
	}

	/**
	 * @return the numberOfWorkingHours
	 */
	public int getNumberOfWorkingHours() {
		return numberOfWorkingHours;
	}

	/**
	 * @param numberOfWorkingHours the numberOfWorkingHours to set
	 */
	public void setNumberOfWorkingHours(int numberOfWorkingHours) {
		if (numberOfWorkingHours > 0) {
			this.numberOfWorkingHours = numberOfWorkingHours;
		} else {
			throw new IllegalArgumentException("number of working hours cannot be zero or negative");
		}
	}

	/**
	 * @return the hourlyRate
	 */
	public double getHourlyRate() {
		return hourlyRate;
	}

	/**
	 * @param hourlyRate the hourlyRate to set
	 */
	public void setHourlyRate(double hourlyRate) {
		if (hourlyRate > 0) {
			this.hourlyRate = hourlyRate;
		} else {
			throw new IllegalArgumentException("hourly rate cannot be zero or negative");
		}
	}

	/**
	 * create unique invoice number for each project invoice
	 */
	private void createInvoiceNumber() {
		this.invoiceNumber = "INVC_" + Integer.toString(++UNIQUE_NUM);
	}

	/**
	 * abstract type of method to calculate total cost
	 * 
	 * @return
	 */
	public abstract double calculateTotalCost();

	@Override
	public int compareTo(ProjectInvoice proj) {
		return (int) (this.calculateTotalCost() - proj.calculateTotalCost());
	}

	@Override
	public String toString() {

		return String.format(
				"Invoice Number: %s;\n Project Name: %s;\n " + "Number of Working Hours: %d;\n Hourly Rate: %.2f;\n",
				this.getInvoiceNumber(), this.getProjectName(), this.getNumberOfWorkingHours(), this.getHourlyRate());
	}

}
