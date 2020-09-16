/**
 * 
 */
package bcit.ca.comp1451.assignment2;

/**
 * @author CHEN ZENG
 *
 */
public class Labour extends ProjectInvoice {
	public static final double SALE_TAX = 0.05;
	public static final double OT_RATE = 1.5;
	public static final double HOLIDAY_RATE = 3;
	public static final double DIS_RATE = 1.2;
	private double travelledDistanceInKM;
	private String hourlyRateCriteria;
	private String typeOfLabour;

	/**
	 * @param projectName
	 * @param numberOfWorkingHours
	 * @param hourlyRate
	 * @param travelledDistance
	 * @param houlyRate
	 * @param isExperienced
	 */
	public Labour(String projectName, int numberOfWorkingHours, double hourlyRate, double travelledDistanceInKM,
			String houlyRateCriteria, String typeOfLabour) {
		super(projectName, numberOfWorkingHours, hourlyRate);
		this.setTravelledDistanceInKM(travelledDistanceInKM);
		this.setHoulyRateCriteria(houlyRateCriteria);
		this.setTypeOfLabour(typeOfLabour);
	}

	/**
	 * @return the travelledDistance
	 */
	public double getTravelledDistanceInKM() {
		return travelledDistanceInKM;
	}

	/**
	 * @param travelledDistance the travelledDistance to set
	 */
	public void setTravelledDistanceInKM(double travelledDistanceInKM) {
		if (travelledDistanceInKM > 0) {
			this.travelledDistanceInKM = travelledDistanceInKM;
		} else {
			throw new IllegalArgumentException("travelled distance cannot be zero or negative");
		}
	}

	/**
	 * @return the houlyRate
	 */
	public String getHoulyRateCriteria() {
		return hourlyRateCriteria;
	}

	/**
	 * @param houlyRate the houlyRate to set
	 */
	public void setHoulyRateCriteria(String hourlyRateCriteria) {
		if (hourlyRateCriteria == null || hourlyRateCriteria.isEmpty()) {
			this.hourlyRateCriteria = "regular";
		} else if (!hourlyRateCriteria.equalsIgnoreCase("regular") && !hourlyRateCriteria.equalsIgnoreCase("overtime")
				&& !hourlyRateCriteria.equalsIgnoreCase("holiday")) {
			this.hourlyRateCriteria = "regular";
		} else {
			this.hourlyRateCriteria = hourlyRateCriteria;
		}
	}

	/**
	 * @return the isExperienced
	 */
	public String getTypeOfLabour() {
		return typeOfLabour;
	}

	/**
	 * @param typeOfLabour the type of labour to set
	 */
	public void setTypeOfLabour(String typeOfLabour) {
		if (typeOfLabour == null || typeOfLabour.isEmpty()) {
			this.typeOfLabour = "inexperienced";
		} else if (!typeOfLabour.equalsIgnoreCase("experienced") && !typeOfLabour.equalsIgnoreCase("inexperienced")) {
			this.typeOfLabour = "inexperienced";
		} else {
			this.typeOfLabour = typeOfLabour;
		}
	}

	/**
	 * calculate the travelled distance cost
	 * 
	 * @return
	 */
	public double travelledDistanceCost() {
		return DIS_RATE * this.getTravelledDistanceInKM();
	}

	@Override
	public double calculateTotalCost() {
		double totalCost = 0.0;
		double results = 0.0;
		if (this.getHoulyRateCriteria().equalsIgnoreCase("regular")) {
			totalCost = super.getHourlyRate() * super.getNumberOfWorkingHours();
		} else if (this.getHoulyRateCriteria().equalsIgnoreCase("overtime")) {
			totalCost = super.getNumberOfWorkingHours() * OT_RATE * super.getHourlyRate();
		} else if (this.getHoulyRateCriteria().equalsIgnoreCase("holiday")) {
			totalCost = super.getNumberOfWorkingHours() * HOLIDAY_RATE * super.getHourlyRate();
		}
		results = this.travelledDistanceCost() + (totalCost * (1 + SALE_TAX));
		return results;
	}

	@Override
	public String toString() {
		double costOfLabour = (this.calculateTotalCost() - this.travelledDistanceCost()) / (1 + SALE_TAX);
		return String.format("Invoice Number: %s;\nProject Name: %s;\n"
				+ "Number of Working Hours: %d;\nHourly Rate: %.2f;\nHourly Rate Criteria: %s;\nType of Labour: %s;\n"
				+ "Cost of Labour: %.2f;\nTravelled Distance Cost: %.2f\nTotal Cost: %.2f;\n", this.getInvoiceNumber(),
				this.getProjectName(), this.getNumberOfWorkingHours(), this.getHourlyRate(),
				this.getHoulyRateCriteria(), this.getTypeOfLabour(), costOfLabour, this.travelledDistanceCost(),
				this.calculateTotalCost());
	}

}
