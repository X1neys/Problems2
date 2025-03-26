import java.util.Scanner;

public class Payment {
    private int amountPaid;
    private String paymentDate;
    private String paymentMethod;
    private String checkType;
    private int checkNumber;
    private int remainingBalance;

    public Payment(int amountPaid, String paymentDate, String paymentMethod, String checkType, int checkNumber, int remainingBalance) {
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.checkType = checkType;
        this.checkNumber = checkNumber;
        this.remainingBalance = remainingBalance;   
    }

    public void PaymentDisplayInfo(){
        System.out.println("Amount Paid: " + amountPaid);
        System.out.println("Payment Date: " + paymentDate); 
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Remaining balance: " + remainingBalance);
        System.out.println("Check Type: " + checkType);
        System.out.println("Check Number: " + checkNumber);
    }
    
    public static void processPayment(Invoice invoice) {
        Scanner scanner = new Scanner(System.in);
        double remainingBalance = invoice.getAmount();
        while (remainingBalance > 0) {
            double paymentAmount = 0;
            while (true) {
                System.out.print("Enter Amount Paid: ");
                paymentAmount = scanner.nextDouble();
                scanner.nextLine();

                if (paymentAmount < 0) {
                    System.out.println("Error: payment amount must be greater than 0!.");
                } else if (paymentAmount > remainingBalance) {
                    System.out.println("Error: Payment amount exceeds remaining balance!.");
                } else {
                    break;
                }
            }

            System.out.print("Enter payment Date (YYYY-MM-DD): ");
            String paymentDate = scanner.nextLine();

            System.out.print("Enter Payment Method: (1 for cash, 2 for check): ");
            int paymentMethod = scanner.nextInt();
            scanner.nextLine(); 

            String paymentMethodStr = "";
            String checkTypeStr = "";
            int checkNumber = 0;

            if (paymentMethod == 1) {
                paymentMethodStr = "Cash";
            } else {
                System.out.print("Is the check on-date or post-dated? (O for on-date, P for post-dated): ");
                char checkType = scanner.next().charAt(0);
                scanner.nextLine(); 

                if (Character.toLowerCase(checkType) == 'o') {
                    checkTypeStr = "On-date";
                    System.out.print("Enter Check Number: ");
                    checkNumber = scanner.nextInt();
                    scanner.nextLine(); 
                } else if (Character.toLowerCase(checkType) == 'p') {
                    checkTypeStr = "Post-dated";
                    System.out.print("Enter Check Number: ");
                    checkNumber = scanner.nextInt();
                    scanner.nextLine(); 
                }
                paymentMethodStr = "Check";
            }

            System.out.println("Payment Recorded Successfully!\n");
            invoice.displayInvoiceDetails();
            System.out.println("Payment Date: " + paymentDate);
            System.out.println("Payment Method: " + paymentMethodStr);
            System.out.println("Amount Paid: " + paymentAmount);
            if (paymentMethod == 2) {
                System.out.println("Check Type: " + checkTypeStr);
                System.out.println("Check Number: " + checkNumber);
            }

            remainingBalance -= paymentAmount;
            if (remainingBalance == 0) {
                System.out.println("Payment Status: Fully paid.");
                break;
            } else {
                System.out.println("Remaining Balance: " + remainingBalance);
                System.out.print("Do you want to apply another payment? (Y/N): ");
                char anotherPayment = scanner.next().charAt(0);
                scanner.nextLine(); 
                if (Character.toLowerCase(anotherPayment) == 'n') {
                    System.out.println("Thank you kas!");
                    break;
                }
            }
        }
        scanner.close();
    }
}