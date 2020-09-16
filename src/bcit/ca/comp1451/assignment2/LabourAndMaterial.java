/**
 * 
 */
package bcit.ca.comp1451.assignment2;

/**
 * @author CHEN ZENG
 *
 */
public class LabourAndMaterial extends Labour implements Transferable {
	public static final double MARKUP = 0.15;
	public static final double SALE_TAX = 0.15;
	public static final double PRE_SALE_TAX = 0.05;
	public static final int VOLUME = 10;
	public static final int CONVEY_RATE1 = 2;
	public static final double CONVEY_RATE2 = 1.5;
	private double materialPurchasePrice;
	private double materialVolumeInCubicFoot;
	private double conveyingDistanceInKilometers;

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
	 */
	public LabourAndMaterial(String projectName, int numberOfWorkingHours, double hourlyRate,
			double travelledDistanceInKM, String houlyRateCriteria, String typeOfLabour, double materialPurchasePrice,
			double materialVolumeInCubicFoot, double conveyingDistanceInKilometers) {
		super(projectName, numberOfWorkingHours, hourlyRate, travelledDistanceInKM, houlyRateCriteria, typeOfLabour);
		this.setMaterialPurchasePrice(materialPurchasePrice);
		this.setMaterialVolumeInCubicFoot(materialVolumeInCubicFoot);
		this.setConveyingDistanceInKilometers(conveyingDistanceInKilometers);
	}

	/**
	 * @return the materialPurchasePrice
	 */
	public double getMaterialPurchasePrice() {
		return materialPurchasePrice;
	}

	/**
	 * @param materialPurchasePrice the materialPurchasePrice to set
	 */
	public void setMaterialPurchasePrice(double materialPurchasePrice) {
		if (materialPurchasePrice > 0) {
			this.materialPurchasePrice = materialPurchasePrice;
		} else {
			throw new IllegalArgumentException("material purchase price cannot be zero or negative");
		}
	}

	/**
	 * @return the materialVolumeInCubicFoot
	 */
	public double getMaterialVolumeInCubicFoot() {
		return materialVolumeInCubicFoot;
	}

	/**
	 * @param materialVolumeInCubicFoot the materialVolumeInCubicFoot to set
	 */
	public void setMaterialVolumeInCubicFoot(double materialVolumeInCubicFoot) {
		if (materialVolumeInCubicFoot > 0) {
			this.materialVolumeInCubicFoot = materialVolumeInCubicFoot;
		} else {
			throw new IllegalArgumentException("material volume cannot be zero or negative");
		}
	}

	/**
	 * @return the conveyingDistanceInKilometers
	 */
	public double getConveyingDistanceInKilometers() {
		return conveyingDistanceInKilometers;
	}

	/**
	 * @param conveyingDistanceInKilometers the conveyingDistanceInKilometers to set
	 */
	public void setConveyingDistanceInKilometers(double conveyingDistanceInKilometers) {
		if (conveyingDistanceInKilometers > 0) {
			this.conveyingDistanceInKilometers = conveyingDistanceInKilometers;
		} else {
			throw new IllegalArgumentException("conveying distance cannot be zero or negative");
		}
	}

	/**
	 * calculate material cost
	 * 
	 * @return
	 */
	public double materialTotalCost() {
		double cost = 0.0;
		cost = this.getMaterialPurchasePrice() * (1 + MARKUP);
		return cost;
	}

	@Override
	public double calculateConveyingFees() {
		double fees = 0.0;
		if (this.getMaterialVolumeInCubicFoot() >= VOLUME) {
			fees = this.getConveyingDistanceInKilometers() * CONVEY_RATE1;
		} else {
			fees = this.getConveyingDistanceInKilometers() * CONVEY_RATE2;
		}
		return fees;
	}

	@Override
	public double calculateTotalCost() {
		double result = 0.0;
		double parent_cost = super.calculateTotalCost();
		result = (this.calculateConveyingFees() + this.materialTotalCost()) * (1 + SALE_TAX);
		return result + parent_cost;
	}

	@Override
	public String toString() {
		double costOfLabour = (super.calculateTotalCost() - super.travelledDistanceCost()) / (1 + PRE_SALE_TAX);
		String result = "";
		if (this.getClass() == LabourAndMaterial.class) {

			result = String.format("Invoice Number: %s;\nProject Name: %s;\n"
					+ "Number of Working Hours: %d;\nHourly Rate: %.2f;\nHourly Rate Criteria: %s;\nType of Labour: %s;\n"
					+ "Cost of Labour: %.2f;\nTravelled Distance Cost: %.2f\nTotal Cost: %.2f;\nMaterial Cost: %.2f;\n"
					+ "Conveying Cost: %.2f;\n", super.getInvoiceNumber(), super.getProjectName(),
					super.getNumberOfWorkingHours(), super.getHourlyRate(), super.getHoulyRateCriteria(),
					this.getTypeOfLabour(), costOfLabour, this.travelledDistanceCost(), this.calculateTotalCost(),
					this.materialTotalCost(), this.calculateConveyingFees());
		}
		return result;
	}

}
