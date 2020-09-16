/**
 * 
 */
package bcit.ca.comp1451.assignment2;

/**
 * @author CHEN ZENG
 *
 */

import java.util.ArrayList;
import java.util.Collections;

public class InsuranceCompany {
	public static final int INV_LEN = 7;
	public static final double INSUR_LABOUR_RATE = 0.05;
	public static final double INSUR_LM_RATE = 0.07;
	public static final double INSUR_LME_RATE = 0.1;

	private String companyName;
	private ArrayList<ProjectInvoice> invoiceList;

	/**
	 * @param companyName
	 * @param projectInvoice
	 */
	public InsuranceCompany(String companyName) {
		this.setCompanyName(companyName);
		invoiceList = new ArrayList<ProjectInvoice>();
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		if (companyName != null && !companyName.isEmpty()) {
			this.companyName = companyName;
		} else {
			throw new IllegalArgumentException("company name cannot be null or empty");
		}
	}

	/**
	 * add an object of project invoice into the list
	 * 
	 * @param invoice
	 */
	public void addInvoice(ProjectInvoice invoice) {
		if (invoice != null) {
			invoiceList.add(invoice);
		} else {
			System.out.println("null invoice object, try again!");
		}
	}

	/**
	 * displays all project invoice objects in the list
	 * 
	 * @param invoiceNum
	 * @throws InvalidInvoiceNumberException
	 */
	public void displayInvoice(String invoiceNum) throws InvalidInvoiceNumberException {
		int counter = 0;
		if (invoiceNum == null || invoiceNum.length() < INV_LEN) {
			throw new InvalidInvoiceNumberException("length of invoice number has to be at least 7 characters");
		} else {
			for (ProjectInvoice p : invoiceList) {
				String invoice = p.getInvoiceNumber();
				if (invoice.equalsIgnoreCase(invoiceNum)) {
					System.out.println(p);
				} else {
					counter++;
				}
			}
			if (counter == invoiceList.size()) {
				System.out.println("Requested invoice number NOT FOUND\n");
			}
		}
	}

	/**
	 * to calculate total insurance fees of all projects in the list
	 * 
	 * @return
	 */
	public double calculateTotalInsuranceFees() {
		double fees = 0.0;
		double total = 0.0;
		for (ProjectInvoice p : invoiceList) {
			if (p instanceof Labour) {
				fees = p.calculateTotalCost() * INSUR_LABOUR_RATE;
				total = total + fees;
			} else if (p instanceof LabourAndMaterial) {
				fees = p.calculateTotalCost() * INSUR_LM_RATE;
				total = total + fees;
			} else if (p instanceof LabourAndMaterialAndEquipment) {
				fees = p.calculateTotalCost() * INSUR_LME_RATE;
				total = total + fees;
			}
		}
		return total;
	}

	/**
	 * display information of all projects in the list
	 */
	public void displayAllSortedProjects() {
		Collections.sort(invoiceList);
		for (ProjectInvoice p : invoiceList) {
			System.out.println(String.format(" Project Name: %s;\nInvoice Number: %s;\nCost of this Project: %.2f;\n",
					p.getProjectName(), p.getInvoiceNumber(), p.calculateTotalCost()));
		}

	}
}
