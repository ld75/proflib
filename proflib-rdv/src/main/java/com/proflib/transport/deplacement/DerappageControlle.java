package com.proflib.transport.deplacement;

import com.proflib.transport.MultiVehicule;

public class DerappageControlle implements VehiculeAction {
    @Override
    public void execute(MultiVehicule multiVehicule) {
        System.out.println(multiVehicule.hashCode()+" derappage controlle");
    }
}
