package by.bsuir;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
     StoBusinessService stoBusinessService = new StoBusinessService();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add master");
            System.out.println("2. Add client");
            System.out.println("3. Show all masters");
            System.out.println("4. Make order");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter master name:");
                    scanner.nextLine();
                    String masterName = scanner.nextLine();
                    Master master = new Master(masterName);
                    stoBusinessService.addMaster(master);
                    while (true) {
                        System.out.println("Enter service name or 'exit' for to go to the menu:");
                        String serviceName = scanner.nextLine();
                        if ("exit".equalsIgnoreCase(serviceName)) break;
                        System.out.println("Enter base price:");
                        double price = scanner.nextDouble();
                        scanner.nextLine();
                        master.addService(new Service(serviceName, price));
                    }
                    stoBusinessService.saveData();
                    break;
                case 2:
                    System.out.println("Enter client name:");
                    scanner.nextLine();  
                    String clientName = scanner.nextLine();
                    stoBusinessService.addClient(new Client(clientName));
                    break;
                case 3:
                    stoBusinessService.showMasters();
                    break;
                case 4:
                    stoBusinessService.makeOrder();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong number. Try again.");
            }
        }
    }
}