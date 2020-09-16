/**
 * 
 */
package bcit.ca.comp1451.assignment2;

/**
 * @author CHEN ZENG
 *
 */
public class LabourAndMaterialAndEquipment extends LabourAndMaterial {
	public static final double RENT_RATE = 0.05;
	public static final double LABOUR_SALE_RATE = 1.05;
	public static final double LM_RATE = 1.15;
	public static final double TRAINING_RATE = 0.02;

	private double equipmentValInCAD;
	private int numOfHrsRented;

	/**
	 * @param projectName
	 * @param numberOfWorkingHours
	 * @param hourlyRate
	 * @param travelledDistanceInKM
	 * @param houlyRateCriteria
	 * @param typeOfLabour
	 * @param materialPurchasePrice
	 * @param materialVolumeInCubicFoot
	 * @param conveyingDistanceInKilometers
	 * @param equipmentValInCAD
	 * @param numOfHrsRented
	 */
	public LabourAndMaterialAndEquipment(String projectName, int numberOfWorkingHours, double hourlyRate,
			double travelledDistanceInKM, String houlyRateCriteria, String typeOfLabour, double materialPurchasePrice,
			double materialVolumeInCubicFoot, double conveyingDistanceInKilometers, double equipmentValInCAD,
			int numOfHrsRented) {
		super(projectName, numberOfWorkingHours, hourlyRate, travelledDistanceInKM, houlyRateCriteria, typeOfLabour,
				materialPurchasePrice, materialVolumeInCubicFoot, conveyingDistanceInKilometers);
		this.setEquipmentValInCAD(equipmentValInCAD);
		this.setNumOfHrsRented(numOfHrsRented);
	}

	/**
	 * @return the equipmentValInCAD
	 */
	public double getEquipmentValInCAD() {
		return equipmentValInCAD;
	}

	/**
	 * @param equipmentValInCAD the equipmentValInCAD to set
	 */
	public void setEquipmentValInCAD(double equipmentValInCAD) {
		if (equipmentValInCAD > 0) {
			this.equipmentValInCAD = equipmentValInCAD;
		} else {
			throw new IllegalArgumentException("equipment value cannot be zero or negative");
		}
	}

	/**
	 * @return the numOfHrsRented
	 */
	public int getNumOfHrsRented() {
		return numOfHrsRented;
	}

	/**
	 * @param numOfHrsRented the numOfHrsRented to set
	 */
	public void setNumOfHrsRented(int numOfHrsRented) {
		if (numOfHrsRented > 0) {
			this.numOfHrsRented = numOfHrsRented;
		} else {
			throw new IllegalArgumentException("number of hours rented cannot be zero or negative");
		}
	}

	/**
	 * calculate rental fees on the equipment
	 * 
	 * @return
	 */
	public double calculateRentalFees() {
		double hrlyRentRate = this.getEquipmentValInCAD() * RENT_RATE;
		return this.getNumOfHrsRented() * hrlyRentRate;
	}

	/**
	 * calculate training fees for different types of labour
	 * 
	 * @return
	 */
	public double trainingFees() {
		double fees = 0.0;
		if (this.getTypeOfLabour().equalsIgnoreCase("experienced")) {
			fees = 0.0;
		} else if (this.getTypeOfLabour().equalsIgnoreCase("inexperienced")) {
			fees = this.getEquipmentValInCAD() * (1 + TRAINING_RATE);
		}
		return fees;
	}

	@Override
	public double calculateTotalCost() {
		double cost = 0.0;
		double previousCost = super.calculateTotalCost();
		cost = (this.trainingFees() + this.calculateRentalFees()) * (1 + RENT_RATE);
		return cost + previousCost;
	}

	@Override
	public String toString() {
		double costOfLabour = ((this.calculateTotalCost()
				- ((this.trainingFees() + this.calculateRentalFees()) * LABOUR_SALE_RATE))
				- ((super.calculateConveyingFees() + super.materialTotalCost()) * LM_RATE)
				- super.travelledDistanceCost()) / LABOUR_SALE_RATE;
		String result = "";
		if (this.getClass() == LabourAndMaterialAndEquipment.class) {

			result = String.format("Invoice Number: %s;\nProject Name: %s;\n"
					+ "Number of Working Hours: %d;\nHourly Rate: %.2f;\nHourly Rate Criteria: %s;\nType of Labour: %s;\n"
					+ "Cost of Labour: %.2f;\nTravelled Distance Cost: %.2f\nTotal Cost: %.2f;\nMaterial Cost: %.2f;\n"
					+ "Conveying Cost: %.2f;\nNumber of Hours Rented: %d;\nRental Fees: %.2f;\nTraining Fees: %.2f;\n",
					super.getInvoiceNumber(), super.getProjectName(), super.getNumberOfWorkingHours(),
					super.getHourlyRate(), super.getHoulyRateCriteria(), super.getTypeOfLabour(), costOfLabour,
					super.travelledDistanceCost(), this.calculateTotalCost(), super.materialTotalCost(),
					super.calculateConveyingFees(), this.getNumOfHrsRented(), this.calculateRentalFees(),
					this.trainingFees());
		}
		return result;
	}

}
