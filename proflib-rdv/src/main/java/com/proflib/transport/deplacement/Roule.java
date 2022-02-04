package com.proflib.transport.deplacement;

import com.proflib.transport.MultiVehicule;

public class Roule implements VehiculeAction {
    @Override
    public void execute(MultiVehicule multiVehicule) {
        System.out.println(multiVehicule.hashCode()+" roule");
    }
}
