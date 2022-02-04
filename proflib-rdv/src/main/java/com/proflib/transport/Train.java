package com.proflib.transport;

public class Train extends Vehicule {
    public Train(double distance) {
        super("Train");
        this.distanceAttribuee=distance;
    }

    public Train() {
        super("Train");
    }

    @Override
    public void roule()
    {
        System.out.println(this.hashCode()+" roule");
        this.distanceParcourue=this.distanceAttribuee;
    }
}
