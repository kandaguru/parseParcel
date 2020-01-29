package io.trademe.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import io.trademe.data.AppConstants;
import io.trademe.data.PackageType;

public class AppUtils {

	private static List<Enum<?>> enumList = new ArrayList<>();

	public static double processInput(String type, Scanner input) {
		String measurement = type == "weight" ? "kg" : "mm";

		boolean isValid = false;
		Double value = 0.0;
		System.out.print("Enter " + type + " of the Parcel (" + measurement + ") : ");
		do {

			while (!input.hasNextDouble()) {
				input.next();
				System.err.print("Enter numerical value for " + type + ":");
			}

			value = input.nextDouble();
			if (value < 0.0) {
				System.err.print("Enter a positive value for " + type + ":");
				isValid = false;
			} else {
				isValid = true;
			}

		} while (!isValid);

		return value;
	}

	public static boolean parseTheParcel(String dimensionType, Double value) throws Exception {

		Map<Enum<PackageType>, Double> dimension = AppConstants.dimensionMap.get(dimensionType);
		Double small = dimension.get(PackageType.SMALL);
		Double medium = dimension.get(PackageType.MEDIUM);
		Double large = dimension.get(PackageType.LARGE);

		if (value > 0.0 && value <= small)
			return enumList.add(PackageType.SMALL);
		else if (small < value && value <= medium)
			return enumList.add(PackageType.MEDIUM);
		else if (medium < value && value <= large)
			return enumList.add(PackageType.LARGE);
		else if (value > large)
			return false;
		else if (value < 0.0)
			return false;
		else
			return false;
	}

	public static String findPackageType(boolean bool) {

		if (bool) {
			if (enumList.contains(PackageType.LARGE))
				return "large";
			if (enumList.contains(PackageType.MEDIUM))
				return "medium";
			if (enumList.contains(PackageType.SMALL))
				return "small";

		} else {
			System.err.println("\nWe are unable to provide a packaging solution for the mentioned dimensions\n");
		}
		return null;

	}

}
