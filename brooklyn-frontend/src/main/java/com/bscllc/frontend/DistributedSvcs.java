package com.bscllc.frontend;

import java.util.List;

public class DistributedSvcs {
    private List<Service>services;
    private String description;

    public DistributedSvcs() {
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
