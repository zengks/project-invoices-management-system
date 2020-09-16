/**
 * 
 */
package bcit.ca.comp1451.assignment2;

/**
 * @author CHEN ZENG
 *
 */

import java.util.Scanner;

public class Driver {

	/**
	 * @param args
	 * @throws InvalidInvoiceNumberException
	 */
	public static void main(String[] args) throws InvalidInvoiceNumberException {
		Labour labour1 = new Labour("LabourProj1", 120, 23.12, 12.22, "overtime", "experienced");
		Labour labour2 = new Labour("LabourProj2", 213, 14.56, 9.8, "regular", "inexperienced");

		LabourAndMaterial lm1 = new LabourAndMaterial("LMProj1", 120, 23.12, 12.22, "overtime", "experienced", 12.11,
				2.54, 8.9);
		LabourAndMaterial lm2 = new LabourAndMaterial("LMProj2", 213, 14.56, 9.8, "regular", "inexperienced", 15.34,
				5.54, 11.3);

		LabourAndMaterialAndEquipment lme1 = new LabourAndMaterialAndEquipment("LMEProj1", 120, 23.12, 12.22,
				"overtime", "experienced", 12.11, 2.54, 8.9, 123.21, 48);
		LabourAndMaterialAndEquipment lme2 = new LabourAndMaterialAndEquipment("LMEProj2", 213, 14.56, 9.8, "regular",
				"inexperienced", 15.34, 5.54, 11.3, 110.43, 55);

		InsuranceCompany company = new InsuranceCompany("company1");

		company.addInvoice(labour1);
		company.addInvoice(labour2);

		company.addInvoice(lm1);
		company.addInvoice(lm2);

		company.addInvoice(lme1);
		company.addInvoice(lme2);

		company.displayAllSortedProjects();

		System.out.println(
				String.format("Total insurance fees of all projects: %.2f;\n", company.calculateTotalInsuranceFees()));

		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.println("\nPlease Enter an Invoice Number (Ex. INVC_10000):");

			String invoiceNum = s.next();

			try {
				company.displayInvoice(invoiceNum);
			} catch (InvalidInvoiceNumberException e) {
				System.out.println("Invalid invoice number...Please try again...");
				continue;
			} catch (Exception e) {
				System.out.println(e);
				continue;
			}
			System.out.println("Would you like to enter another invoice number? (Y/N)");
			String answer = s.next();
			if (answer.equalsIgnoreCase("Y")) {
				continue;
			} else if (answer.equalsIgnoreCase("N")) {
				System.out.println("See you next time...");
				break;
			} else {
				System.out.println("Invalid answer. Exiting...");
				break;
			}
		}
		s.close();
	}

}
