package com.proflib.transport.deplacement;

import com.proflib.transport.MultiVehicule;

@FunctionalInterface
public interface VehiculeAction {
    public void execute(MultiVehicule multiVehicule);
}
