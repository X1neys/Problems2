import java.util.Scanner;

public class AccountsReceivable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the invoice number: ");
        String invoiceNumber = scanner.nextLine();

        System.out.print("Enter the purchase date (YYYY-MM-DD): ");
        String purchaseDate = scanner.nextLine();

        System.out.print("Enter the customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter Invoice Amount: ");
        double invoiceAmount = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.println("Invoice Created Successfully!\n");
        Invoice invoice = new Invoice(invoiceNumber, purchaseDate, customerName, invoiceAmount);
        invoice.displayInvoiceDetails();
        System.out.println("------------------------------------------\n");

        Payment.processPayment(invoice);
        // ========================================================================================

        scanner.close();
    }
}
