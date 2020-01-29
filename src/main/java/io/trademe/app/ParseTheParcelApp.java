package io.trademe.app;

import static io.trademe.data.AppConstants.BREADTH;
import static io.trademe.data.AppConstants.HEIGHT;
import static io.trademe.data.AppConstants.LENGTH;
import static io.trademe.data.AppConstants.priceMap;

import java.util.Scanner;

import io.trademe.data.AppConstants;
import io.trademe.util.AppUtils;

public class ParseTheParcelApp {

	public static void main(String args[]) throws Exception {

		Scanner input = new Scanner(System.in);
		AppConstants.loadDataOnStartUp();

		System.out.println("\t\t\t\t\t\t**************************************");
		System.out.println("\t\t\t\t\t\t\tShipping Cost Estimator");
		System.out.println("\t\t\t\t\t\t**************************************\n");

		try {

			while (true) {
				Double weight = AppUtils.processInput("weight", input);

				if (weight > 25) {
					System.err.println("\nUnfortunately we wouldnt be supporting for packages over 25 kgs");
					continue;
				}

				Double length = AppUtils.processInput("length", input);
				Double breadth = AppUtils.processInput("breadth", input);
				Double height = AppUtils.processInput("height", input);

				boolean isValidLength = AppUtils.parseTheParcel(LENGTH, length);
				boolean isValidBreadth = AppUtils.parseTheParcel(BREADTH, breadth);
				boolean isValidHeight = AppUtils.parseTheParcel(HEIGHT, height);

				boolean validDimensions = isValidLength && isValidBreadth && isValidHeight;
				String type = AppUtils.findPackageType(validDimensions);

				if (type != null && !type.isEmpty()) {
					System.out.println("Your Package Type  : " + type);
					System.out.println("Your Price  : $" + priceMap.get(type));
				}

				System.out.println(
						"\n***************************************\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Something went wrong");

		} finally {
			input.close();
		}

	}

}
