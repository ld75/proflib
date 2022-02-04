package com.proflib.transport;

public class Voiture extends Vehicule {
    public Voiture(double distance) {
        super("Voiture");
        this.distanceAttribuee=distance;
    }

    public Voiture() {
        super("Voiture");
    }

    @Override
    public void roule()
    {
        System.out.println(this.hashCode()+" roule");
        this.distanceParcourue= Math.floor(distanceAttribuee);
    }
    public void faitDerappageControlle(){
        System.out.println(this.hashCode()+" derappage controlle");
    }

}
