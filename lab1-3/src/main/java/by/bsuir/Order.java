package by.bsuir;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private Client client;
    private Master master;
    private List<Service> selectedServices;
    private boolean expensiveMaterials;
    private int deadlineDays;
    private double totalPrice;

    public Order(Client client, Master master, List<Service> selectedServices, boolean expensiveMaterials, int deadlineDays) {
        this.client = client;
        this.master = master;
        this.selectedServices = selectedServices;
        this.expensiveMaterials = expensiveMaterials;
        this.deadlineDays = deadlineDays;
        this.totalPrice = calculateTotalPrice();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public List<Service> getSelectedServices() {
        return selectedServices;
    }

    public void setSelectedServices(List<Service> selectedServices) {
        this.selectedServices = selectedServices;
    }

    public boolean isExpensiveMaterials() {
        return expensiveMaterials;
    }

    public void setExpensiveMaterials(boolean expensiveMaterials) {
        this.expensiveMaterials = expensiveMaterials;
    }

    public int getDeadlineDays() {
        return deadlineDays;
    }

    public void setDeadlineDays(int deadlineDays) {
        this.deadlineDays = deadlineDays;
    }

      private double calculateTotalPrice() {
        double basePrice = 0d;
          for (Service s: selectedServices) {
              basePrice += s.getBasePrice();
          }
        double materialMultiplier = expensiveMaterials ? 1.5 : 1.0;
        double deadlineMultiplier = (deadlineDays < 3) ? 1.2 : 1.0;
        return basePrice * materialMultiplier * deadlineMultiplier;
    }

    @Override
    public String toString() {
        return "Client: " + client.getName() + ", Master: " + master.getName() + ", Services: " + selectedServices +
                ", Materials: " + (expensiveMaterials ? "Expensive" : "Cheap") +
                ", Deadline: " + deadlineDays + " days, Total price: " + totalPrice;
    }
}
