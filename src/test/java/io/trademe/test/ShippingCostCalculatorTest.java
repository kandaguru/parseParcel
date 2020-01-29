package io.trademe.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import io.trademe.data.AppConstants;
import io.trademe.util.AppUtils;

public class ShippingCostCalculatorTest {

	private static Scanner scanner;

	@BeforeAll
	public static void loadData() {
		AppConstants.loadDataOnStartUp();
	}

	@org.junit.jupiter.api.Test
	public void validInputTest() throws Exception {
		Double input = 123.0;
		boolean bool = AppUtils.parseTheParcel("length", input);
		Assertions.assertTrue(bool);
	}

	@org.junit.jupiter.api.Test
	public void inValidInputTest() throws Exception {
		Double input = -123.0;
		boolean bool = AppUtils.parseTheParcel("length", input);
		Assertions.assertFalse(bool);
	}

	@org.junit.jupiter.api.Test
	public void validatePackageTypeTest() throws Exception {
		Double input = -123.0;
		boolean bool = AppUtils.parseTheParcel("length", input);
		AppUtils.findPackageType(bool);
	}

	@org.junit.jupiter.api.Test
	public void validateStringInputTest() throws IOException {
		String input = "invalid";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		System.out.println("value " + System.in.available());
		scanner = new Scanner(System.in);
		Assertions.assertThrows(NoSuchElementException.class, () -> AppUtils.processInput("length", scanner));
	}

	@org.junit.jupiter.api.Test
	public void validateNegativeInputTest() {
		Double input = -123.0;
		byte[] inbuf = convertDoubleToByte(input);
		InputStream in = new ByteArrayInputStream(inbuf);
		System.setIn(in);
		scanner = new Scanner(System.in);
		Assertions.assertThrows(NoSuchElementException.class, () -> AppUtils.processInput("length", scanner));
	}

	@AfterAll
	public static void tearDown() {
		scanner.close();
	}

	public byte[] convertDoubleToByte(Double input) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(Double.BYTES);
		byteBuffer.putDouble(input);
		byte[] inBuf = byteBuffer.array();
		return inBuf;
	}

}
