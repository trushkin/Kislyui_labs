package by.bsuir;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Master implements Serializable {
    private String name;
    private List<Service> services;

    public Master(String name) {
        this.name = name;
        this.services = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Service> getServices() {
        return services;
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Master: " + name + ", Services: " + services;
    }
}
