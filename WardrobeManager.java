

/**
 * This class writes all the methods for the WardrobeManager
 */
public class WardrobeManager {

	/**
	 * Checks if the wardrobe contains clothing with a specific description and
	 * brand.
	 * 
	 * @param description  The description of the clothing.
	 * @param brand        The brand of the clothing.
	 * @param wardrobe     The array representing the wardrobe.
	 * @param wardrobeSize The current number of items in the wardrobe.
	 * @return true if the wardrobe contains an item matching the description and
	 *         brand, false otherwise.
	 */
	public static boolean containsClothing(String description, String brand, String[][] wardrobe, int wardrobeSize) {
		if (wardrobeSize == 0) {
			return false;
		}

		// Check for an item with the provided description and brand
		for (int i = 0; i < wardrobeSize; i++) {
			String existingDescription = wardrobe[i][0];
			String existingBrand = wardrobe[i][1];

			if (description.equalsIgnoreCase(existingDescription) && brand.equalsIgnoreCase(existingBrand)) {
				return true; // Item with matching description and brand found
			}
		}

		return false; // No matching item found

	}

	/**
	 * Adds a piece of clothing to the wardrobe if there is space and the item is
	 * not a duplicate.
	 * 
	 * @param description  The description of the clothing.
	 * @param brand        The brand of the clothing.
	 * @param wardrobe     The array representing the wardrobe.
	 * @param wardrobeSize The current number of items in the wardrobe.
	 * @return The new size of the wardrobe after attempting to add the clothing.
	 */
	public static int addClothing(String description, String brand, String[][] wardrobe, int wardrobeSize) {
		// Check if the wardrobe is already full
		if (wardrobeSize >= wardrobe.length) {
			System.out.println("Wardrobe is full. Cannot add more items.");
			return wardrobeSize; // Return the current wardrobe size without modification
		}

		// Check for duplicates based on description and brand
		for (int i = 0; i < wardrobeSize; i++) {
			String existingDescription = wardrobe[i][0];
			String existingBrand = wardrobe[i][1];

			if (description.equals(existingDescription) && brand.equals(existingBrand)) {
				System.out.println("Duplicate item found. Item not added.");
				return wardrobeSize; // Return the current wardrobe size without modification
			}
		}

		// Initialize the array slot before adding the new clothing item
		wardrobe[wardrobeSize] = new String[3]; // Assuming each inner array holds 3 elements: description, brand, and
												// last worn date
		wardrobe[wardrobeSize][0] = description;
		wardrobe[wardrobeSize][1] = brand;
		wardrobe[wardrobeSize][2] = "never"; // Set last worn date to "never"

		// Increment the wardrobe size
		wardrobeSize++;

		return wardrobeSize;
	}

	/**
	 * Finds the index of a piece of clothing in the wardrobe matching the specified
	 * description and brand.
	 * 
	 * @param description  The description of the clothing to find.
	 * @param brand        The brand of the clothing to find.
	 * @param wardrobe     The array representing the wardrobe.
	 * @param wardrobeSize The current number of items in the wardrobe.
	 * @return The index of the found clothing, or -1 if not found.
	 */
	public static int indexOfClothing(String description, String brand, String[][] wardrobe, int wardrobeSize) {
		// Iterate through the wardrobe to find the index of the item
		for (int i = 0; i < wardrobeSize; i++) {
			String existingDescription = wardrobe[i][0];
			String existingBrand = wardrobe[i][1];

			if (description.equals(existingDescription) && brand.equals(existingBrand)) {
				return i; // Item found, return its index
			}
		}

		// If item not found, return -1 to indicate it was not found
		return -1;
	}

	/**
	 * Marks a piece of clothing as worn by updating its last-worn date.
	 * 
	 * @param description  The description of the clothing to wear.
	 * @param brand        The brand of the clothing to wear.
	 * @param date         The date the clothing was worn.
	 * @param wardrobe     The array representing the wardrobe.
	 * @param wardrobeSize The current number of items in the wardrobe.
	 * @return true if the item was found and its last-worn date was updated, false
	 *         otherwise.
	 */
	public static boolean wearClothing(String description, String brand, String date, String[][] wardrobe,
			int wardrobeSize) {
		

		boolean itemFound = false; // variable to indicate if the item was found and updated

		for (int i = 0; i < wardrobeSize; i++) {
			if (wardrobe[i] == null) {
				continue; // Skip null entries in the wardrobe array
			}

			
			
			String existingDescription = wardrobe[i][0].trim();
			String existingBrand = wardrobe[i][1].trim();

			// Use equalsIgnoreCase for case-insensitive comparison
			if (description.equalsIgnoreCase(existingDescription) && brand.equalsIgnoreCase(existingBrand)) {
				wardrobe[i][2] = date; // Update the last-worn date
				itemFound = true; // Item found and date updated
				break; // Exit the loop once the item is found and updated
			}
		}

		if (!itemFound) {
			System.out.println("Item with description '" + description + "' and brand '" + brand + "' not found.");
		}

		return itemFound; // Return true if item was found and updated, false otherwise
	}

	/**
	 * Counts the number of items of a specific brand in the wardrobe.
	 * 
	 * @param brand        The brand to count in the wardrobe.
	 * @param wardrobe     The array representing the wardrobe.
	 * @param wardrobeSize The current number of items in the wardrobe.
	 * @return The count of items of the specified brand in the wardrobe.
	 */
	public static int getBrandCount(String brand, String[][] wardrobe, int wardrobeSize) {
		int count = 0; // Initialize the count to 0

		// Iterate through the wardrobe to count items of the specified brand
		for (int i = 0; i < wardrobeSize; i++) {
			String existingBrand = wardrobe[i][1];

			if (brand.equalsIgnoreCase(existingBrand)) {
				count++; // Increment count when a matching brand is found
			}
		}

		return count;
	}

	/**
	 * Counts the number of unworn clothes in the wardrobe.
	 * 
	 * @param wardrobe     The array representing the wardrobe.
	 * @param wardrobeSize The current number of items in the wardrobe.
	 * @return The number of unworn clothes in the wardrobe.
	 */
	public static int getNumUnwornClothes(String[][] wardrobe, int wardrobeSize) {
		int count = 0; // Initialize the count to 0

		// Iterate through the wardrobe to count items with last-worn date "never"
		for (int i = 0; i < wardrobeSize; i++) {
			String lastWornDate = wardrobe[i][2];

			if ("never".equals(lastWornDate)) {
				count++; // Increment count when an item with "never" last-worn date is found
			}
		}

		return count;
	}

	/**
	 * Determines the index of the most recently worn piece of clothing in the
	 * wardrobe.
	 * 
	 * @param wardrobe     The array representing the wardrobe.
	 * @param wardrobeSize The current number of items in the wardrobe.
	 * @return The index of the most recently worn clothing, or -1 if none have been
	 *         worn.
	 */
	public static int getMostRecentlyWorn(String[][] wardrobe, int wardrobeSize) {
		if (wardrobeSize == 0) {
			return -1; // Wardrobe is empty
		}

		String mostRecentDate = "0000-00-00"; // Initialize with a string that's effectively earlier than any valid date
		int indexMostRecent = -1; // Initialize to -1 to handle cases where no dates are found

		for (int i = 0; i < wardrobeSize; i++) {
			String dateStr = wardrobe[i][2];
			if (!dateStr.equals("never")) {
				// Directly compare the dates as strings since they are in YYYY-MM-DD format
				if (dateStr.compareTo(mostRecentDate) > 0) {
					mostRecentDate = dateStr;
					indexMostRecent = i;
				}
			}
		}

		// if-else statement to return the most recent index
		if (indexMostRecent == -1) {
			return 0;
		} else {
			return indexMostRecent;
		}
	}

	/**
	 * Removes a piece of clothing from the wardrobe at a specified index.
	 * 
	 * @param index        The index of the clothing to remove.
	 * @param wardrobe     The array representing the wardrobe.
	 * @param wardrobeSize The current number of items in the wardrobe.
	 * @return The new size of the wardrobe after the item has been removed.
	 */
	public static int removeClothingAtIndex(int index, String[][] wardrobe, int wardrobeSize) {
		if (index < 0 || index >= wardrobeSize) {
			// Check if the provided index is out of bounds
			System.out.println("Invalid index. Item not removed.");
			return wardrobeSize;
		}

		// Remove the item at the provided index
		for (int i = index; i < wardrobeSize - 1; i++) {
			// Shift all items after the removed item to the left
			wardrobe[i] = wardrobe[i + 1];
		}

		// Set the last entry to null
		wardrobe[wardrobeSize - 1] = null;

		// Decrement the wardrobe size
		wardrobeSize--;

		return wardrobeSize;
	}

	/**
	 * Removes all unworn clothing from the wardrobe and compacts the remaining
	 * items.
	 * 
	 * @param wardrobe     The array representing the wardrobe.
	 * @param wardrobeSize The current number of items in the wardrobe.
	 * @return The new size of the wardrobe after removing unworn items.
	 */
	public static int removeAllUnworn(String[][] wardrobe, int wardrobeSize) {
		int newSize = 0; // Initialize the new size

		// Iterate through the wardrobe to remove unworn items and shift others down
		for (int i = 0; i < wardrobeSize; i++) {
			String lastWornDate = wardrobe[i][2];

			if (!"never".equals(lastWornDate)) {
				// If the item has been worn, keep it and shift it to the new position
				wardrobe[newSize] = wardrobe[i];
				newSize++; // Increment the new size after shifting
			}
		}

		// Set the remaining entries to null in the original array
		for (int i = newSize; i < wardrobeSize; i++) {
			wardrobe[i] = null;
		}

		return newSize;
	}

}
