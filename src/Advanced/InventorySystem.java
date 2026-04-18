package Advanced;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Product model
 */
class Product implements Serializable {
    private int id;
    private String name;
    private String category;
    private BigDecimal price;
    private int quantity;

    public Product(int id, String name, String category, BigDecimal price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    /// GETTERES
    public int getId() { return id; }

    /// SETTERS
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(BigDecimal price) { this.price = price; }

    @Override
    public String toString() {
        return String.format("%-5d\t\t%-15s\t\t%-10s\t\t%-10.2f\t\t%-10d", id, name, category, price, quantity);
    }
}

/**
 * Console Inventory System Program by Charles Henry M. Tinoy Jr.
 */
public class InventorySystem {
    ///  DATABASE PATH
    private static final String DATABASE_PATH = "database.bin";

    /// PRODUCTS
    private static ArrayList<Product> PRODUCTS = new ArrayList<>();

    /**
     * Reads the binary database and store it in the PRODUCTS array list.
     */
    private static void readDatabase() {
        if (!Files.exists(Paths.get(DATABASE_PATH)))
            writeDatabase();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DATABASE_PATH))) {
            PRODUCTS = (ArrayList<Product>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.printf("Something went wrong while writing the database, error: %s\n", ex.getMessage());
        }
    }

    /**
     * Writes the PRODUCTS array list to binary.
     */
    private static void writeDatabase() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATABASE_PATH))) {
            outputStream.writeObject(PRODUCTS);
        } catch (IOException ex) {
            System.err.printf("Something went wrong while reading the database, error: %s\n", ex.getMessage());
        }
    }

    /**
     * Returns the product by ID
     * @return The Product
     */
    private static Product getProductById(Scanner scanner) {
        // Clear buffer
        scanner.nextLine();

        // Get the ID
        int id;
        System.out.print("Enter the ID: ");
        do {
            String idInput = scanner.nextLine();

            if (idInput.trim().isEmpty()) {
                System.out.print("ID can't be empty, please try again: ");
                continue;
            }

            try {
                id = Integer.parseInt(idInput);
                break;
            } catch (NumberFormatException ex) {
                System.out.print("Invalid input, please try again: ");
            }
        } while (true);

        int finalId = id;
        Product result = PRODUCTS.stream()
                .filter(product -> product.getId() == finalId)
                .findFirst()
                .orElse(null);

        if (result == null) {
            System.out.printf("Unable to find a product with ID %d\n", finalId);
            return null;
        }

        return result;
    }

    static {
        readDatabase();
    }

    /**
     * Displays the border (E.g. ==========)
     * @param length The length of the border
     */
    private static void displayBorder(int length) {
        for (int i = 0; i < length; i++)
            System.out.print("=");

        System.out.println();
    }

    /**
     * Displays the border (E.g. ===== Charles Henry M. Tinoy Jr. =====)
     * @param length The length of the border
     * @param heading The heading of the border
     */
    private static void displayBorder(int length, String heading) {
        for (int i = 0; i < length; i++)
            System.out.print("=");
        System.out.printf(" %s ", heading);
        for (int i = 0; i < length; i++)
            System.out.print("=");

        System.out.println();
    }

    /**
     * Performs the add product operation
     * @param scanner Scanner buffer
     */
    private static void addProduct(Scanner scanner) {
        displayBorder(10, "ADD PRODUCT");

        // Clear buffer
        scanner.nextLine();

        // Get the product ID
        int id;
        System.out.print("Enter the ID: ");
        do {
            String idInput = scanner.nextLine();

            if (idInput.trim().isEmpty()) {
                System.out.print("ID can't be empty, please try again: ");
                continue;
            }

            try {
                id = Integer.parseInt(idInput);

                /*
                 * Check if the ID already existed.
                 */
                int finalId = id;
                Product result = PRODUCTS.stream()
                        .filter(product -> product.getId() == finalId)
                        .findFirst()
                        .orElse(null);

                if (result == null)
                    break;

                if (result.getId() == finalId)
                    System.out.print("This ID already existed, please choose another ID: ");
            } catch (NumberFormatException ex) {
                System.out.print("Invalid input, please try again: ");
            }
        } while (true);

        // Get the product name
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();

        while (name.trim().isEmpty()) {
            System.out.print("Name can't be empty, please try again: ");
            name = scanner.nextLine();
        }

        // Get the product category
        System.out.print("Enter the category: ");
        String category = scanner.nextLine();

        while (category.trim().isEmpty()) {
            System.out.print("Category can't be empty, please try again: ");
            category = scanner.nextLine();
        }

        // Get the product price
        BigDecimal price;
        System.out.print("Enter the price: ");
        do {
            String priceInput = scanner.nextLine();

            if (priceInput.trim().isEmpty()) {
                System.out.print("Price can't be empty, please try again: ");
                continue;
            }

            try {
                price = BigDecimal.valueOf(Double.parseDouble(priceInput));
                break;
            } catch (NumberFormatException ex) {
                System.out.print("Invalid input, please try again: ");
            }
        } while (true);

        // Get the product quantity
        int quantity;
        System.out.print("Enter the quantity: ");
        do {
            String quantityInput = scanner.nextLine();

            if (quantityInput.trim().isEmpty()) {
                System.out.print("Quantity can't be empty, please try again: ");
                continue;
            }

            try {
                quantity = Integer.parseInt(quantityInput);
                break;
            } catch (NumberFormatException ex) {
                System.out.print("Invalid input, please try again: ");
            }
        } while (true);

        /*
         * Add the new product to the PRODUCTS array list
         * Then write the database
         */
        Product newProduct = new Product(id, name, category, price, quantity);
        PRODUCTS.add(newProduct);
        writeDatabase();

        System.out.println("The product has successfully been added.");
    }

    /**
     * View all the products in table layout.
     */
    private static void viewProducts() {
        displayBorder(30, "ALL PRODUCTS");

        if (PRODUCTS.size() <= 0) {
            System.out.println("Looks like you haven't added any products yet.");
            return;
        }

        System.out.printf("%-5s\t\t%-15s\t\t%-10s\t\t%-10s\t\t%-10s\n", "ID", "Name", "Category", "Price", "Quantity");
        for (Product product : PRODUCTS)
            System.out.println(product.toString());
    }

    private static void updateProduct(Scanner scanner) {
        displayBorder(10, "UPDATE PRODUCT");

        Product result = getProductById(scanner);
        if (result == null) return;

        System.out.println("Press \"Enter\" key to skip the input");

        // Get the new product name
        System.out.print("Enter the new name: ");
        String name = scanner.nextLine();

        if (name.trim().isEmpty())
            System.out.println("Name skipped.");

        // Get the new product category
        System.out.print("Enter the new category: ");
        String category = scanner.nextLine();
        if (category.trim().isEmpty())
            System.out.println("Category skipped.");

        // Get the new product price
        System.out.print("Enter the new price: ");
        BigDecimal price = null;
        do {
            String priceInput = scanner.nextLine();

            if (priceInput.trim().isEmpty()) {
                System.out.println("Price skipped.");
                break;
            }

            try {
                price = BigDecimal.valueOf(Double.parseDouble(priceInput));
                break;
            } catch (NumberFormatException ex) {
                System.out.print("Invalid input, please try again: ");
            }
        } while (true);

        if (!name.trim().isEmpty())
            result.setName(name);
        if (!category.trim().isEmpty())
            result.setCategory(category);
        if (price != null)
            result.setPrice(price);

        writeDatabase();

        System.out.println("The product has successfully been updated.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        displayBorder(29);
        System.out.println("\t\tINVENTORY SYSTEM");

        do {
            displayBorder(10, "OPTIONS");
            System.out.println("Add Product\t\t\t\t\t[1]");
            System.out.println("View Products\t\t\t\t[2]");
            System.out.println("Update Product\t\t\t\t[3]");
            System.out.println("Delete Product\t\t\t\t[4]");
            System.out.println("Search Product\t\t\t\t[5]");
            System.out.println("Stock In\t\t\t\t\t[6]");
            System.out.println("Stock Out\t\t\t\t\t[7]");
            System.out.println("Stock Reports\t\t\t\t[8]");
            System.out.println("Exit\t\t\t\t\t\t[9]");
            displayBorder(29);

            // Get user option
            System.out.print("Option: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid option, please try again: ");
                scanner.next();
                scanner.nextLine();
            }
            int userOption = scanner.nextInt();

            if (userOption == 1) addProduct(scanner);
            if (userOption == 2) viewProducts();
            if (userOption == 3) updateProduct(scanner);
            if (userOption == 9)
                break;
        } while (true);

        scanner.close();
    }
}
