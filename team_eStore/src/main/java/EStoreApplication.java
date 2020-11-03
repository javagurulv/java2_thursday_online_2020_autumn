import java.util.Scanner;

public class EStoreApplication {
    private static final ProductRepository productRepository =
            ProductRepository.getProductRepository();

    public static void main(String[] args) {
        String name = null;
        String description = null;
        double price = 0.0;

        Utils.showMenu();
        Scanner scanner = new Scanner(System.in);
        String command;
        while (!(command = scanner.nextLine()).equals("exit")) {
            Utils.clearScreen();
            Utils.showMenu();
            switch (command){
                case "1":
                    System.out.println("Please type product name: ");
                    name = scanner.nextLine();
                    System.out.println("Please type product description: ");
                    description = scanner.nextLine();
                    System.out.println("Please type price: ");
                    price = Double.parseDouble(scanner.nextLine());
                    productRepository.addProduct(name, description, price);
                    break;
                case "2":
                    System.out.println("Please type the name of the product you want to delete: ");
                    name = scanner.nextLine();
                    productRepository.removeProduct(name);
                    break;
                case "3":
                    System.out.println("The list of the products: ");
                    productRepository.printOutAllProducts();
                    break;
                case "4":
                    System.out.println("Please type the name of the product you want to find: ");
                    name = scanner.nextLine();
                    Product product = productRepository.findProductByName(name);
                    System.out.println("Product found: ");
                    System.out.println(product);
                    break;
                case "5":
                    System.out.println("Please type the name of the product you want to update info: ");
                    name = scanner.nextLine();
                    System.out.println("Please type the new product name: ");
                    String newName = scanner.nextLine();
                    System.out.println("Please type product new description: ");
                    description = scanner.nextLine();
                    System.out.println("Please type new price: ");
                    price = Double.parseDouble(scanner.nextLine());
                    productRepository.updateProduct(name, newName, description, price);
                    break;
                default:
                    System.out.println("Please, type a valid option.");
                    break;
            }
        }
    }
}
