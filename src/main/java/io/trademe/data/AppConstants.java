package io.trademe.data;

import java.util.HashMap;
import java.util.Map;

public class AppConstants {

	public static final String LENGTH = "length";
	public static final String BREADTH = "breadth";
	public static final String HEIGHT = "height";
	public static final Map<String, Double> priceMap = new HashMap<>();
	public static final Map<String, Map<Enum<PackageType>, Double>> dimensionMap = new HashMap<>();
	private static Map<Enum<PackageType>, Double> lengthMap = new HashMap<>();
	private static Map<Enum<PackageType>, Double> breadthMap = new HashMap<>();
	private static Map<Enum<PackageType>, Double> heightMap = new HashMap<>();

	/**
	 * Loads the pre requisite data on app start up
	 */
	public static void loadDataOnStartUp() {

		priceMap.put("small", 5.00);
		priceMap.put("medium", 7.50);
		priceMap.put("large", 8.50);

		lengthMap.put(PackageType.SMALL, (double) 200);
		lengthMap.put(PackageType.MEDIUM, (double) 300);
		lengthMap.put(PackageType.LARGE, (double) 400);

		breadthMap.put(PackageType.SMALL, (double) 300);
		breadthMap.put(PackageType.MEDIUM, (double) 400);
		breadthMap.put(PackageType.LARGE, (double) 600);

		heightMap.put(PackageType.SMALL, (double) 150);
		heightMap.put(PackageType.MEDIUM, (double) 200);
		heightMap.put(PackageType.LARGE, (double) 250);

		dimensionMap.put("length", lengthMap);
		dimensionMap.put("breadth", breadthMap);
		dimensionMap.put("height", heightMap);

	}

}
