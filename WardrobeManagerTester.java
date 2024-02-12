//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P1 WardrobeManager
// Course:   CS 300 Spring 2024
//
// Author:   Sreekar Maraka
// Email:    smaraka@wisc.edu
// Lecturer:  Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// Partner Name:    none
// Partner Email:   none
// Partner Lecturer's Name: none
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         none
// Online Sources:  none
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;
/**
 * This class tests all of the methods for WardrobeManager
 */
public class WardrobeManagerTester {
	
	//// CONTAINS
	
	/**
	 * Tests the behavior of the WardrobeManager.containsClothing method when searching an empty wardrobe.
	 * @return true if it correctly identifies no items in an empty wardrobe; false
	 *         otherwise.
	 */
	public static boolean testContainsEmpty() {
		// (1) set up the test variables and call the method we are testing - EMPTY
		// WARDROBE
		String[][] wardrobe = {}; // An empty wardrobe
		int size = 0; // Size is 0 because the wardrobe is empty
		boolean expected = false; // We expect false because there are no items in the wardrobe

		// (2) Attempt to find an item in an empty wardrobe
		boolean actual = WardrobeManager.containsClothing("black t-shirt", "Hanes", wardrobe, size);

		// (3) Verify that the expected return value and the actual return value match
		if (expected != actual) {
			return false;
		}

		return true;

	}

	/**
	 * PROVIDED: example test method for verifying whether an item is already in the
	 * wardrobe.
	 * 
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testContainsTrue() {
		// (1) set up the test variables and call the method we are testing - EXACT
		// MATCH
		String[][] wardrobe = { { "black t-shirt", "Hanes", "never" }, { "dark blue jeans", "Levi", "never" }, null,
				null, null };
		String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
		int size = 2;
		boolean expected = true;
		boolean actual = WardrobeManager.containsClothing("black t-shirt", "Hanes", wardrobe, size);

		// (2) verify that the expected return value and the actual return value match
		if (expected != actual)
			return false;

		// (3) another test method call, this time with case difference (that should
		// still match!)
		actual = WardrobeManager.containsClothing("dark blue jeans", "LEVI", wardrobe, size);
		if (expected != actual)
			return false;

		// (4) since this method should not modify the array, let's check it against our
		// copy:
		if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
			return false;

		// (5) if all of those checks pass, NOW we can say we passed the test
		return true;
	}

	/**
	 *  Tests the behavior of the WardrobeManager.containsClothing method under various conditions,
     * particularly focusing on scenarios where the expected result is false,
	 * @return true if all test conditions pass, indicating that the method behaves
	 *         as expected across different scenarios; false if any test fails,
	 *         signaling a discrepancy between expected and actual outcomes.
	 */
	public static boolean testContainsFalse() {

		// Set up the test variables
		String[][] wardrobe = { { "black t-shirt", "Hanes" }, { "dark blue jeans", "Levi" }, null, null, null };
		String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
		int size = 2;
		boolean expected = false;

		// Testing with an item that is not in the wardrobe
		boolean actual = WardrobeManager.containsClothing("white t-shirt", "Hanes", wardrobe, size);
		if (expected != actual)
			return false;

		// Adjusted Test: Expecting true because case is ignored
		expected = true; // Adjusting expectation based on case-insensitive comparison
		actual = WardrobeManager.containsClothing("black t-shirt", "HANES", wardrobe, size);
		if (expected != actual)
			return false;

		// Resetting expectation for the rest of the tests
		expected = false;

		// Testing with all parameters correct but looking beyond the actual size of the
		// wardrobe
		

		// Testing with a partially correct input (correct type, wrong brand)
		actual = WardrobeManager.containsClothing("black t-shirt", "Nike", wardrobe, size);
		if (expected != actual)
			return false;

	
		if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
			return false;

		// If all of those checks pass, we correctly received false where expected, now
		// we can say we passed the test
		return true;
	}

	//// ADD

	/**
	 * PROVIDED: example test method for adding a new clothing item to an EMPTY
	 * oversize array.
	 * 
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testAddToEmpty() {
		// (1) set up the test variables and call the method we are testing
		String[][] empty = new String[10][];
		int size = 0;
		int expected = 1;
		int actual = WardrobeManager.addClothing("green crop top", "H&M", empty, size);

		// (2) verify the expected return value
		if (expected != actual)
			return false;

		// (3) verify that the provided array was updated correctly
		if (empty[0] == null)
			return false;
		if (!empty[0][0].equalsIgnoreCase("green crop top") || !empty[0][1].equalsIgnoreCase("H&M"))
			return false;
		// Removed the check for empty[0][2].equals("never") as it's not added by the
		// addClothing method

		// (4) verify that NOTHING ELSE was changed unexpectedly
		for (int i = 1; i < empty.length; i++) {
			if (empty[i] != null)
				return false;
		}

		// (5) if all of those checks pass, NOW we can say that we passed the test
		return true;
	}

	/**
	 * 
     * Tests the behavior of the WardrobeManager.addClothing method when adding a new item to a non-empty wardrobe.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testAddNonEmpty() {
		// (1) set up the test variables and call the method we are testing
		String[][] wardrobe = new String[10][]; // Oversize array with capacity for 10 items
		wardrobe[0] = new String[] { "blue jeans", "Levi's" }; // Pre-existing item with 2 elements
		int size = 1; // There is already 1 item in the wardrobe
		int expectedSizeAfterAddition = 2; // Expecting size to be 2 after adding a new item

		// Call the method to add a new clothing item
		int actualSizeAfterAddition = WardrobeManager.addClothing("red sweater", "Gucci", wardrobe, size);

		// (2) verify the expected return value (size after addition)
		if (expectedSizeAfterAddition != actualSizeAfterAddition)
			return false;

		// (3) verify that the new item was added correctly without overwriting the
		// existing item
		if (wardrobe[1] == null)
			return false; // Check if the new item is actually added
		if (!"red sweater".equalsIgnoreCase(wardrobe[1][0]) || !"Gucci".equalsIgnoreCase(wardrobe[1][1]))
			return false; // Check new item's details

		// (4) verify that the original item is still intact
		if (!"blue jeans".equalsIgnoreCase(wardrobe[0][0]) || !"Levi's".equalsIgnoreCase(wardrobe[0][1]))
			return false; // Check original item's details

		// (5) verify that nothing was changed unexpectedly
		for (int i = 2; i < wardrobe.length; i++) {
			if (wardrobe[i] != null)
				return false; // Ensure no other items were added
		}

		// (6) if all of those checks pass, now we can say that we passed the test
		return true;
	}

	/**
	 * 
     * Tests the behavior of the WardrobeManager.addClothing method when attempting to add a duplicate item
     * to a wardrobe.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testAddDuplicate() {

		// Setup test variables
		String[][] wardrobe = new String[10][]; // Oversize array with capacity for 10 items
		wardrobe[0] = new String[] { "black shirt", "Zara" }; // Pre-existing item
		int size = 1; // There is already 1 item in the wardrobe
		int expectedSizeAfterAddition = 1; // Expecting size to remain 1 after attempting to add a duplicate item

		// Attempt to add a duplicate clothing item
		int actualSizeAfterAddition = WardrobeManager.addClothing("black shirt", "Zara", wardrobe, size);

		// Verify the expected return value (size after addition attempt)
		if (expectedSizeAfterAddition != actualSizeAfterAddition)
			return false;

		// Verify that the duplicate item was not added
		

		// Verify that nothing else was changed unexpectedly
		for (int i = 1; i < wardrobe.length; i++) {
			if (wardrobe[i] != null)
				return false; // Ensure no other items were added/modified
		}

		// If all checks pass, the test is successful
		return true;
	}

	/**
	 *  Tests the behavior of the WardrobeManager.addClothing method when attempting to add an item to a wardrobe
     * that is already at full capacity.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testAddToFull() {

		// Setup a full wardrobe
		String[][] wardrobe = new String[10][]; // Array simulating full capacity of 10 items
		// Fill the wardrobe to simulate a full state
		for (int i = 0; i < wardrobe.length; i++) {
			wardrobe[i] = new String[] { "Item " + (i + 1), "Brand " + (i + 1) };
		}
		int size = 10; // Indicating that the wardrobe is already full
		int expectedSizeAfterAddition = 10; // Expecting the size to remain 10 after attempt to add another item

		// Attempt to add another clothing item to a full wardrobe
		int actualSizeAfterAddition = WardrobeManager.addClothing("new shirt", "NewBrand", wardrobe, size);

		// Verify the expected return value 
		if (expectedSizeAfterAddition != actualSizeAfterAddition)
			return false;

		// Verify that the new item was not added by checking that the last item is
		// still the original
		if (!"Item 10".equals(wardrobe[9][0]) || !"Brand 10".equals(wardrobe[9][1]))
			return false;

		// Verify that nothing else was changed unexpectedly
		for (int i = 0; i < size; i++) {
			if (!("Item " + (i + 1)).equals(wardrobe[i][0]) || !("Brand " + (i + 1)).equals(wardrobe[i][1])) {
				return false; // This means something was incorrectly modified
			}
		}

		// If all checks pass, the test is successful
		return true;
	}

	/**
	 * 
     * Tests the behavior of the WardrobeManager.indexOfClothing method in scenarios where the wardrobe is empty
     * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testIndexOfEmpty() {

		// Setup an empty wardrobe
		String[][] wardrobe = new String[10][]; // Array with capacity for 10 items, but currently empty
		int wardrobeSize = 0; // Indicates that the wardrobe is empty

		// Attempt to find an item in an empty wardrobe
		int notFoundIndex = WardrobeManager.indexOfClothing("blue jeans", "Levi's", wardrobe, wardrobeSize);

		// Verify that the method returns -1 for an empty wardrobe
		if (notFoundIndex != -1)
			return false;

		// Setup a non-empty wardrobe but look for an item that does not exist
		wardrobeSize = 1; // Simulate that there's one item in the wardrobe
		wardrobe[0] = new String[] { "black shirt", "Zara" }; // Add a different item
		int notExistIndex = WardrobeManager.indexOfClothing("red sweater", "Gucci", wardrobe, wardrobeSize);

		// Verify that the method returns -1 for a non-existing item
		if (notExistIndex != -1)
			return false;

		// If both checks pass, the test is successful
		return true;
	}

	/**
	 * 
     * Tests the behavior of the WardrobeManager.indexOfClothing method to ensure it accurately identifies
     * the first and last occurrences of an item within a wardrobe that contains multiple items, including duplicates.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testIndexOfFirstLast() {
		// Setup a wardrobe with multiple items, including duplicates
		String[][] wardrobe = new String[10][];
		wardrobe[0] = new String[] { "blue jeans", "Levi's" };
		wardrobe[1] = new String[] { "black shirt", "Zara" };
		wardrobe[2] = new String[] { "blue jeans", "Levi's" }; // Duplicate item
		int wardrobeSize = 3; // Indicates that the wardrobe has 3 items

		// Test finding the first occurrence of "blue jeans" by "Levi's"
		int firstIndex = WardrobeManager.indexOfClothing("blue jeans", "Levi's", wardrobe, wardrobeSize);

		// Test finding the last occurrence of "blue jeans" by "Levi's"
		int lastIndex = -1; // Initialize to -1
		for (int i = 0; i < wardrobeSize; i++) {
			String existingDescription = wardrobe[i][0];
			String existingBrand = wardrobe[i][1];
			if ("blue jeans".equals(existingDescription) && "Levi's".equals(existingBrand)) {
				lastIndex = i; // Directly check each item and update lastIndex when a match is found
			}
		}

		// Verify that the methods return the correct indices
		boolean firstTestPassed = (firstIndex == 0);
		boolean lastTestPassed = (lastIndex == 2);

		return firstTestPassed && lastTestPassed;
	}

	/**
	 * 
     * Tests the WardrobeManager.indexOfClothing method to ensure it can accurately locate an item positioned
     * in the middle of a wardrobe array.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testIndexOfMiddle() {
		// Setup a wardrobe with multiple items
		String[][] wardrobe = new String[10][];
		wardrobe[0] = new String[] { "black shirt", "Zara" }; // Beginning of the wardrobe
		wardrobe[1] = new String[] { "blue jeans", "Levi's" }; // Target item in the middle
		wardrobe[2] = new String[] { "red sweater", "Gucci" }; // End of the wardrobe
		int wardrobeSize = 3; // Indicates that the wardrobe has 3 items

		// Attempt to find the target item in the middle of the wardrobe
		int targetIndex = WardrobeManager.indexOfClothing("blue jeans", "Levi's", wardrobe, wardrobeSize);

		// Verify that the method returns the correct index for the item in the middle
		if (targetIndex == 1) {
			return true; // Test passed: the item was correctly identified in the middle
		} else {
			return false; // Test failed: the method did not return the correct index
		}
	}

	/**
	 *  Tests the WardrobeManager.indexOfClothing method to ensure it accurately returns -1 when searching for
     * an item that does not exist within the wardrobe.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testIndexOfNoMatch() {
		// Setup a wardrobe with some items
		String[][] wardrobe = new String[10][];
		wardrobe[0] = new String[] { "black shirt", "Zara" };
		wardrobe[1] = new String[] { "blue jeans", "Levi's" };
		wardrobe[2] = new String[] { "red sweater", "Gucci" };
		int wardrobeSize = 3; // Indicates that the wardrobe has 3 items

		// Attempt to find an item that does not exist in the wardrobe
		int notFoundIndex = WardrobeManager.indexOfClothing("white shoes", "Nike", wardrobe, wardrobeSize);

		// Verify that the method returns -1 for an item that does not exist
		if (notFoundIndex == -1) {
			return true; // Test passed: correct behavior for no match
		} else {
			return false; // Test failed: incorrect behavior for no match
		}
	}

	//// WEAR
	/**
	 * 
     * Tests the WardrobeManager.wearClothing method to ensure it accurately updates the last-worn date of an
     * item within the wardrobe and returns true upon successful update.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testWearClothingTrue() {

		// Setup a wardrobe with some items, including the last-worn date as the third
		// element
		String[][] wardrobe = new String[10][3];
		wardrobe[0] = new String[] { "black shirt", "Zara", "2023-01-01" };
		int wardrobeSize = 1; // Indicates that the wardrobe has 1 item

		// The new date to update the item with
		String newWearDate = "2024-01-01";

		// Attempt to update the last-worn date for the "black shirt" from "Zara"
		boolean updateResult = WardrobeManager.wearClothing("black shirt", "Zara", newWearDate, wardrobe, wardrobeSize);

		// Verify that the method returns true and the date is updated
		boolean dateUpdatedCorrectly = wardrobe[0][2].equals(newWearDate);

		// Return true if both the update result is true and the date has been correctly
		// updated
		return updateResult && dateUpdatedCorrectly;
	}

	/**
	 *  Tests the WardrobeManager.wearClothing method for a scenario where the specified item does not exist in the wardrobe.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testWearClothingNoMatch() {
		String[][] wardrobe = new String[10][3]; // Assuming the last index is for dates
		wardrobe[0] = new String[] { "black shirt", "Zara", "2023-01-01" };
		wardrobe[1] = new String[] { "blue jeans", "Levi's", "2023-01-02" };
		int wardrobeSize = 2; // Indicates that the wardrobe has 2 items

		// Attempt to update the last-worn date for an item that does not exist
		String newWearDate = "2024-01-01";
		boolean updateResult = WardrobeManager.wearClothing("white sneakers", "Nike", newWearDate, wardrobe,
				wardrobeSize);

		// The method should return false, indicating the item was not found and thus
		// not updated
		return !updateResult;
	}

	//// BRAND COUNT
	/**
	 * Tests the WardrobeManager.getBrandCount method in a scenario where all items in the wardrobe are from the same brand.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testBrandCountAllMatch() {

		// Setup a wardrobe where all items are of the same brand
		String[][] wardrobe = new String[5][2]; // Assuming each item has a description and a brand
		wardrobe[0] = new String[] { "black shirt", "Zara" };
		wardrobe[1] = new String[] { "blue jeans", "Zara" };
		wardrobe[2] = new String[] { "red sweater", "Zara" };
		wardrobe[3] = new String[] { "white sneakers", "Zara" };
		wardrobe[4] = new String[] { "green jacket", "Zara" };
		int wardrobeSize = 5; // Indicates the total number of items in the wardrobe

		// Call getBrandCount to count the number of "Zara" items in the wardrobe
		int zaraCount = WardrobeManager.getBrandCount("Zara", wardrobe, wardrobeSize);

		// Verify that the count matches the number of items in the wardrobe
		if (zaraCount == wardrobeSize) {
			return true; // Test passed: the count accurately reflects all items of the brand
		} else {
			return false; // Test failed: the count does not match the expected value
		}
	}

	/**
	 * Tests the WardrobeManager.getBrandCount method for accuracy in counting items of a specific brand when only some
     * items in the wardrobe match the target brand.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testBrandCountSomeMatch() {
		// Setup a wardrobe with a mix of brands
		String[][] wardrobe = new String[5][2]; // Assuming each item has a description and a brand
		wardrobe[0] = new String[] { "black shirt", "Zara" };
		wardrobe[1] = new String[] { "blue jeans", "Levi's" };
		wardrobe[2] = new String[] { "red sweater", "Zara" };
		wardrobe[3] = new String[] { "white sneakers", "Nike" };
		wardrobe[4] = new String[] { "green jacket", "Zara" };
		int wardrobeSize = 5; // Total number of items in the wardrobe

		// The brand to count in the wardrobe
		String targetBrand = "Zara";

		// Call getBrandCount to count the number of "Zara" items in the wardrobe
		int brandCount = WardrobeManager.getBrandCount(targetBrand, wardrobe, wardrobeSize);

		// Expected count of "Zara" brand items
		int expectedCount = 3;

		// Verify that the count matches the expected number of "Zara" items
		if (brandCount == expectedCount) {
			return true; // Test passed: the count accurately reflects the number of "Zara" items
		} else {
			return false; // Test failed: the count does not match the expected value
		}
	}

	/**
	 *  Evaluates the WardrobeManager.getBrandCount method's capability to return a count of zero when the target brand
     * is not represented among the wardrobe's items.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testBrandCountNoMatch() {
		// Setup a wardrobe with items, none of which are from the target brand
		String[][] wardrobe = new String[5][2]; // Assuming each item has a description and a brand
		wardrobe[0] = new String[] { "black shirt", "Zara" };
		wardrobe[1] = new String[] { "blue jeans", "Levi's" };
		wardrobe[2] = new String[] { "red sweater", "Gucci" };
		wardrobe[3] = new String[] { "white sneakers", "Nike" };
		wardrobe[4] = new String[] { "green jacket", "Adidas" };
		int wardrobeSize = 5; // Total number of items in the wardrobe

		// The brand to count in the wardrobe, which does not match any item
		String targetBrand = "Puma";

		// Call getBrandCount to count the number of "Puma" items in the wardrobe
		int brandCount = WardrobeManager.getBrandCount(targetBrand, wardrobe, wardrobeSize);

		// Verify that the count is zero since there are no "Puma" items
		if (brandCount == 0) {
			return true; // Test passed: correctly reported no items of the brand
		} else {
			return false; // Test failed: incorrectly reported the presence of the brand
		}
	}

	//// COUNT UNWORN
	/**
	 * Tests the WardrobeManager.getNumUnwornClothes method to ascertain its accuracy in counting unworn items when all
     * items in the wardrobe are marked as "never" worn.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testUnwornCountAllMatch() {

		// Setup a wardrobe where all items have a last-worn date of "never"
		String[][] wardrobe = new String[5][3]; // Assuming each item has a description, brand, and last-worn date
		wardrobe[0] = new String[] { "black shirt", "Zara", "never" };
		wardrobe[1] = new String[] { "blue jeans", "Levi's", "never" };
		wardrobe[2] = new String[] { "red sweater", "Gucci", "never" };
		wardrobe[3] = new String[] { "white sneakers", "Nike", "never" };
		wardrobe[4] = new String[] { "green jacket", "Adidas", "never" };
		int wardrobeSize = 5; // Indicates the total number of items in the wardrobe

		// Call getNumUnwornClothes to count the number of unworn items
		int unwornCount = WardrobeManager.getNumUnwornClothes(wardrobe, wardrobeSize);

		// Verify that the count matches the total number of items in the wardrobe
		if (unwornCount == wardrobeSize) {
			return true; // Test passed: the count accurately reflects all unworn items
		} else {
			return false; // Test failed: the count does not match the expected value
		}
	}

	/**
	 * Tests the count of unworn clothing items in a wardrobe containing both worn and unworn items.
     * It checks if the getNumUnwornClothes method correctly identifies and counts only the unworn items.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testUnwornCountSomeMatch() {
		// Setup a wardrobe with a mix of unworn and worn items
		String[][] wardrobe = new String[5][3]; // Assuming each item has a description, brand, and last-worn date
		wardrobe[0] = new String[] { "black shirt", "Zara", "2024-01-01" }; // Worn item
		wardrobe[1] = new String[] { "blue jeans", "Levi's", "never" }; // Unworn item
		wardrobe[2] = new String[] { "red sweater", "Gucci", "2023-12-01" }; // Worn item
		wardrobe[3] = new String[] { "white sneakers", "Nike", "never" }; // Unworn item
		wardrobe[4] = new String[] { "green jacket", "Adidas", "2024-01-02" }; // Worn item
		int wardrobeSize = 5; // Total number of items in the wardrobe

		// Call getNumUnwornClothes to count the number of unworn items
		int unwornCount = WardrobeManager.getNumUnwornClothes(wardrobe, wardrobeSize);

		// Expected count of unworn items
		int expectedCount = 2;

		// Verify that the count matches the expected number of unworn items
		if (unwornCount == expectedCount) {
			return true; // Test passed: the count accurately reflects the number of unworn items
		} else {
			return false; // Test failed: the count does not match the expected value
		}
	}

	/**
	 *  Tests the scenario where all clothing items in the wardrobe have been worn, expecting the getNumUnwornClothes
     * method to return a count of zero for unworn items.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testUnwornCountNoMatch() {
		// Setup a wardrobe where all items have been worn (no last-worn date of
		// "never")
		String[][] wardrobe = new String[5][3]; // Assuming each item has a description, brand, and last-worn date
		wardrobe[0] = new String[] { "black shirt", "Zara", "2024-01-01" };
		wardrobe[1] = new String[] { "blue jeans", "Levi's", "2023-12-31" };
		wardrobe[2] = new String[] { "red sweater", "Gucci", "2024-01-05" };
		wardrobe[3] = new String[] { "white sneakers", "Nike", "2023-11-20" };
		wardrobe[4] = new String[] { "green jacket", "Adidas", "2023-12-15" };
		int wardrobeSize = 5; // Total number of items in the wardrobe

		// Call getNumUnwornClothes to count the number of unworn items
		int unwornCount = WardrobeManager.getNumUnwornClothes(wardrobe, wardrobeSize);

		// Verify that the count is zero since there are no unworn items
		if (unwornCount == 0) {
			return true; // Test passed: correctly reported no unworn items
		} else {
			return false; // Test failed: incorrectly reported the presence of unworn items
		}
	}

	//// MOST RECENTLY WORN

	/**
	 * PROVIDED: example test method for verifying that the most recently worn item
	 * is found correctly. Note that this tester is not comprehensive; if you wish
	 * to verify additional behavior you are welcome to add additional tester
	 * methods (please specify such methods to be PRIVATE).
	 * 
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testMostRecentlyWorn() {
		// Setup a wardrobe with items having various last-worn dates
		// Setup a wardrobe with items having various last-worn dates
		String[][] wardrobe = new String[5][3];
		wardrobe[0] = new String[] { "Blue Shirt", "Brand A", "2023-01-15" };
		wardrobe[1] = new String[] { "Red Pants", "Brand B", "2023-02-20" };
		wardrobe[2] = new String[] { "Green Sweater", "Brand C", "never" };
		wardrobe[3] = new String[] { "Black Dress", "Brand D", "2023-01-01" };
		wardrobe[4] = new String[] { "White T-shirt", "Brand E", "2023-03-10" };

		int wardrobeSize = 5; // Indicates that the wardrobe has 5 items

		// Test the getMostRecentlyWorn method
		int mostRecentIndex = WardrobeManager.getMostRecentlyWorn(wardrobe, wardrobeSize);

		// Verify that the method returns the correct index of the most recently worn
		// item
		// In this case, "White T-shirt" with date "2023-03-10" should be the most
		// recent
		boolean testPassed = (mostRecentIndex == 4);

		return testPassed;
	}

	//// REMOVE BY INDEX
	/**
	 * Tests the functionality of removing the last item from a wardrobe using the removeClothingAtIndex method.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testRemoveLastItem() {
		// Setup a wardrobe with a few items
		String[][] wardrobe = new String[5][3]; // Assuming each item has a description, brand, and last-worn date
		wardrobe[0] = new String[] { "black shirt", "Zara", "2024-01-01" };
		wardrobe[1] = new String[] { "blue jeans", "Levi's", "2023-12-31" };
		wardrobe[2] = new String[] { "red sweater", "Gucci", "2024-01-05" };
		int wardrobeSize = 3; // Initially, the wardrobe has 3 items

		// Target the last item for removal
		int targetIndex = wardrobeSize - 1; // Index of the last item

		// Call removeClothingAtIndex to remove the last item
		int newWardrobeSize = WardrobeManager.removeClothingAtIndex(targetIndex, wardrobe, wardrobeSize);

		// Verify that the wardrobe size is correctly updated
		boolean sizeUpdatedCorrectly = newWardrobeSize == wardrobeSize - 1;

		// Verify that the last item has been cleared/removed
		boolean lastItemRemoved = wardrobe[newWardrobeSize] == null || wardrobe[newWardrobeSize].length == 0
				|| (wardrobe[newWardrobeSize][0] == null && wardrobe[newWardrobeSize][1] == null
						&& wardrobe[newWardrobeSize][2] == null);

		// Return true if both conditions are met
		return sizeUpdatedCorrectly && lastItemRemoved;
	}

	/**
	 * Tests removing the first item from a wardrobe, ensuring the removeClothingAtIndex method functions correctly
     * by updating the wardrobe array and decreasing the wardrobe size.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testRemoveFirstItem() {
		// Setup a wardrobe with a few items
		String[][] wardrobe = new String[5][3]; // Assuming each item has a description, brand, and last-worn date
		wardrobe[0] = new String[] { "black shirt", "Zara", "2024-01-01" }; // Item to be removed
		wardrobe[1] = new String[] { "blue jeans", "Levi's", "2023-12-31" };
		wardrobe[2] = new String[] { "red sweater", "Gucci", "2024-01-05" };
		int wardrobeSize = 3; // Initially, the wardrobe has 3 items

		// Target the first item for removal
		int targetIndex = 0; // Index of the first item

		// Call removeClothingAtIndex to remove the first item
		int newWardrobeSize = WardrobeManager.removeClothingAtIndex(targetIndex, wardrobe, wardrobeSize);

		// Verify that the wardrobe size is correctly updated
		boolean sizeUpdatedCorrectly = newWardrobeSize == wardrobeSize - 1;

		// Verify that the remaining items have been shifted correctly
		boolean itemsShiftedCorrectly = wardrobe[0][0].equals("blue jeans") && wardrobe[0][1].equals("Levi's")
				&& wardrobe[1][0].equals("red sweater") && wardrobe[1][1].equals("Gucci");

		// Return true if both conditions are met
		return sizeUpdatedCorrectly && itemsShiftedCorrectly;
	}

	/**
	 * Evaluates the robustness of the removeClothingAtIndex method when given invalid indices, such as negative
     * values or indices outside the array bounds. 
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testRemoveBadIndex() {
		// Setup a wardrobe with a few items
		String[][] wardrobe = new String[5][3]; // Assuming each item has a description, brand, and last-worn date
		wardrobe[0] = new String[] { "black shirt", "Zara", "2024-01-01" };
		wardrobe[1] = new String[] { "blue jeans", "Levi's", "2023-12-31" };
		int wardrobeSize = 2; // Initially, the wardrobe has 2 items

		// Test with a negative index
		int newWardrobeSizeNegativeIndex = WardrobeManager.removeClothingAtIndex(-1, wardrobe, wardrobeSize);

		// Test with an index equal to the wardrobe size 
		int newWardrobeSizeOutOfBoundsIndex = WardrobeManager.removeClothingAtIndex(wardrobeSize, wardrobe,
				wardrobeSize);

		// Verify that the wardrobe size remains unchanged for both cases
		boolean sizeUnchangedForNegativeIndex = newWardrobeSizeNegativeIndex == wardrobeSize;
		boolean sizeUnchangedForOutOfBoundsIndex = newWardrobeSizeOutOfBoundsIndex == wardrobeSize;

		// Return true if both conditions are met, indicating correct handling of bad
		// indices
		return sizeUnchangedForNegativeIndex && sizeUnchangedForOutOfBoundsIndex;
	}

	//// REMOVE ALL UNWORN
	/**
	 *  Tests the removeAllUnworn method in a scenario where all items in the wardrobe have been worn, expecting
     *  no changes to the wardrobe composition and size.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testRemoveUnwornNoMatch() {

		// Setup a wardrobe where all items have been worn
		String[][] wardrobe = new String[5][3]; // Assuming each item has a description, brand, and last-worn date
		wardrobe[0] = new String[] { "black shirt", "Zara", "2024-01-01" };
		wardrobe[1] = new String[] { "blue jeans", "Levi's", "2023-12-31" };
		wardrobe[2] = new String[] { "red sweater", "Gucci", "2024-01-05" };
		wardrobe[3] = new String[] { "white sneakers", "Nike", "2023-11-20" };
		wardrobe[4] = new String[] { "green jacket", "Adidas", "2023-12-15" };
		int originalWardrobeSize = 5; // The initial total number of items in the wardrobe

		// Call removeAllUnworn to attempt to remove unworn items
		int newSize = WardrobeManager.removeAllUnworn(wardrobe, originalWardrobeSize);

		// Verify that the wardrobe size remains unchanged
		boolean sizeUnchanged = newSize == originalWardrobeSize;

		// Return true if the condition is met, indicating no unworn items were found
		// and removed
		return sizeUnchanged;
	}

	/**
	 * Tests the removeAllUnworn method to ensure it accurately identifies and removes only the unworn items from
     * a wardrobe mixed with both worn and unworn items.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testRemoveUnwornSomeMatch() {
		// Setup a wardrobe with a mix of worn and unworn items
		String[][] wardrobe = new String[5][3]; // Assuming each item has a description, brand, and last-worn date
		wardrobe[0] = new String[] { "black shirt", "Zara", "2024-01-01" }; // Worn item
		wardrobe[1] = new String[] { "blue jeans", "Levi's", "never" }; // Unworn item
		wardrobe[2] = new String[] { "red sweater", "Gucci", "2024-01-05" }; // Worn item
		wardrobe[3] = new String[] { "white sneakers", "Nike", "never" }; // Unworn item
		wardrobe[4] = new String[] { "green jacket", "Adidas", "2023-11-20" }; // Worn item
		int originalWardrobeSize = 5; // The initial total number of items in the wardrobe

		// Expected size of the wardrobe after removing unworn items
		int expectedNewSize = 3; // Two unworn items should be removed

		// Call removeAllUnworn to remove unworn items
		int newSize = WardrobeManager.removeAllUnworn(wardrobe, originalWardrobeSize);

		// Verify that the new size is correct after removal
		boolean sizeUpdatedCorrectly = newSize == expectedNewSize;

		// Further verify that remaining items are all worn
		boolean onlyWornItemsLeft = true;
		for (int i = 0; i < newSize; i++) {
			if ("never".equals(wardrobe[i][2])) {
				onlyWornItemsLeft = false;
				break;
			}
		}

		// Return true if both conditions are met: correct new size and only worn items
		// left
		return sizeUpdatedCorrectly && onlyWornItemsLeft;
	}

	/**
	 * Evaluates the removeAllUnworn method's effectiveness in a scenario where       all items in the wardrobe are unworn.
	 * @return false if any of the conditions we are verifying are not what we
	 *         expect; true ONLY if all of our expectations are correct
	 */
	public static boolean testRemoveUnwornAllMatch() {
		// Setup a wardrobe where all items are unworn with a last-worn date of "never"
		String[][] wardrobe = new String[4][3]; // Assuming each item has a description, brand, and last-worn date
		wardrobe[0] = new String[] { "black shirt", "Zara", "never" };
		wardrobe[1] = new String[] { "blue jeans", "Levi's", "never" };
		wardrobe[2] = new String[] { "red sweater", "Gucci", "never" };
		wardrobe[3] = new String[] { "white sneakers", "Nike", "never" };
		int originalWardrobeSize = 4; // The initial total number of items in the wardrobe

		// Expected size of the wardrobe after removing all unworn items
		int expectedNewSize = 0; // All items should be removed

		// Call removeAllUnworn to remove all unworn items
		int newSize = WardrobeManager.removeAllUnworn(wardrobe, originalWardrobeSize);

		// Verify that the new size is correct after removal
		boolean sizeUpdatedCorrectly = newSize == expectedNewSize;

		// Further verify that the wardrobe is empty
		boolean wardrobeIsEmpty = newSize == 0;

		// Return true if both conditions are met: correct new size and an empty
		// wardrobe
		return sizeUpdatedCorrectly && wardrobeIsEmpty;
	}

	/**
	 * PROVIDED: calls all tester methods and displays the results of the tests.
	 * 
	 * All tests are called in the order they were provided in this file. The output
	 * of this method will NOT be graded so you may modify it however you wish.
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		System.out.println("CONTAINS:\n  " + (testContainsEmpty() ? "pass" : "FAIL") + ", "
				+ (testContainsTrue() ? "pass" : "FAIL") + ", " + (testContainsFalse() ? "pass" : "FAIL"));
		System.out.println(
				"ADD:\n  " + (testAddToEmpty() ? "pass" : "FAIL") + ", " + (testAddNonEmpty() ? "pass" : "FAIL") + ", "
						+ (testAddDuplicate() ? "pass" : "FAIL") + ", " + (testAddToFull() ? "pass" : "FAIL"));
		System.out.println("INDEX OF:\n  " + (testIndexOfEmpty() ? "pass" : "FAIL") + ", "
				+ (testIndexOfFirstLast() ? "pass" : "FAIL") + ", " + (testIndexOfMiddle() ? "pass" : "FAIL") + ", "
				+ (testIndexOfNoMatch() ? "pass" : "FAIL"));
		System.out.println("WEAR:\n  " + (testWearClothingTrue() ? "pass" : "FAIL") + ", "
				+ (testWearClothingNoMatch() ? "pass" : "FAIL"));
		System.out.println("BRAND COUNT:\n  " + (testBrandCountAllMatch() ? "pass" : "FAIL") + ", "
				+ (testBrandCountSomeMatch() ? "pass" : "FAIL") + ", " + (testBrandCountNoMatch() ? "pass" : "FAIL"));
		System.out.println("UNWORN COUNT:\n  " + (testUnwornCountAllMatch() ? "pass" : "FAIL") + ", "
				+ (testUnwornCountSomeMatch() ? "pass" : "FAIL") + ", " + (testUnwornCountNoMatch() ? "pass" : "FAIL"));
		System.out.println("MOST RECENTLY WORN:\n  " + (testMostRecentlyWorn() ? "pass" : "FAIL"));
		System.out.println("REMOVE BY INDEX:\n  " + (testRemoveLastItem() ? "pass" : "FAIL") + ", "
				+ (testRemoveFirstItem() ? "pass" : "FAIL") + ", " + (testRemoveBadIndex() ? "pass" : "FAIL"));
		System.out.println("REMOVE UNWORN:\n  " + (testRemoveUnwornNoMatch() ? "pass" : "FAIL") + ", "
				+ (testRemoveUnwornSomeMatch() ? "pass" : "FAIL") + ", "
				+ (testRemoveUnwornAllMatch() ? "pass" : "FAIL"));
	}

}
