package by.bsuir;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StoBusinessService {
    private static final String MASTER_FILE = "masters.dat";
    private static final String CLIENT_FILE = "clients.dat";

    private List<Master> masters;
    private List<Client> clients;

    public StoBusinessService() {
        loadInitialData();
    }
    private void loadInitialData() {
        try {
            masters = (List<Master>) FileManager.loadData(MASTER_FILE);
            clients = (List<Client>) FileManager.loadData(CLIENT_FILE);
        } catch (IOException | ClassNotFoundException e) {
            masters = new ArrayList<>();
            clients = new ArrayList<>();
        }
    }
    protected void saveData() {
        try {
            FileManager.saveData(MASTER_FILE, masters);
            FileManager.saveData(CLIENT_FILE, clients);
        } catch (IOException e) {
            System.out.println("Saving data error: " + e.getMessage());
        }
    }

    public void addMaster(Master master) {
        masters.add(master);
        saveData();
    }

    public void addClient(Client client) {
        clients.add(client);
        saveData();
    }

    public void showMasters() {
        if (masters.isEmpty()) {
            System.out.println("No masters available.");
        } else {
            for (int i = 0; i < masters.size(); i++) {
                System.out.println((i + 1) + ". " + masters.get(i));
            }
        }
        System.out.println("\n");
    }


    public void makeOrder() {
        try {
            if (clients.isEmpty()) {
                throw new StoException("Error: There are no available customers to create an order.");
            }

            if (masters.isEmpty()) {
                throw new StoException("Error: There are no available masters to create an order.");
            }

            Scanner scanner = new Scanner(System.in);

            System.out.println("Choose client:");
            for (int i = 0; i < clients.size(); i++) {
                System.out.println((i + 1) + ". " + clients.get(i).getName());
            }
            int clientIndex = scanner.nextInt() - 1;
            if (clientIndex < 0 || clientIndex >= clients.size()) {
                throw new StoException("Error: Invalid client selected.");
            }

            System.out.println("Choose master:");
            for (int i = 0; i < masters.size(); i++) {
                System.out.println((i + 1) + ". " + masters.get(i).getName());
            }
            int masterIndex = scanner.nextInt() - 1;
            if (masterIndex < 0 || masterIndex >= masters.size()) {
                throw new StoException("Error: Invalid master selected.");
            }

            Master selectedMaster = masters.get(masterIndex);

            if (selectedMaster.getServices().isEmpty()) {
                throw new StoException("Error: the master has no available services.");
            }

            System.out.println("Select services (enter numbers separated by spaces):");
            for (int i = 0; i < selectedMaster.getServices().size(); i++) {
                System.out.println((i + 1) + ". " + selectedMaster.getServices().get(i));
            }

            scanner.nextLine();  // Очистка буфера (почитай)
            String[] serviceIndices = scanner.nextLine().split(" ");
            List<Service> selectedServices = new ArrayList<>();
            for (String index : serviceIndices) {
                int serviceIndex = Integer.parseInt(index) - 1;
                if (serviceIndex < 0 || serviceIndex >= selectedMaster.getServices().size()) {
                    throw new StoException("Error: Invalid service index selected.");
                }
                selectedServices.add(selectedMaster.getServices().get(serviceIndex));
            }

            System.out.println("Select the type of materials: 1. Expensive 2. Cheap");
            boolean expensiveMaterials = scanner.nextInt() == 1;

            System.out.println("Enter deadline (in days):");
            int deadlineDays = scanner.nextInt();
            if (deadlineDays <= 0) {
                throw new StoException("Error: Deadline must be a positive number.");
            }

            Order order = new Order(clients.get(clientIndex), selectedMaster, selectedServices, expensiveMaterials, deadlineDays);
            System.out.println("Your order: " + order);

        } catch (StoException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) { // почитай что это такое
            System.out.println("Error: incorrect data entered.");
        }
    }
}
