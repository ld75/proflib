import {Vehicule} from './Vehicule.js'
export class VehiculeFactory{
constructor(){

}
buildTrain(vitessesDeSegment, pauseEntreGares,dureeDebutVoyage,findureevoyage){
    let vehicule = new Vehicule(vitessesDeSegment)
    vehicule.ajouteComportementEnVille(function(){ return pauseEntreGares})
    vehicule.setPositionEnMinute(dureeDebutVoyage);
    vehicule.setPositionFinEnMinute(findureevoyage);
    vehicule.lapskmville=7
    return vehicule;
}
}