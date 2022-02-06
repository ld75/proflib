export class DureeDeplacementRDV
{
calculDureeDeplacement(vitesseKmh, distanceKm){
if (vitesseKmh<0 || distanceKm<0)throw Error("illegal args")
if (vitesseKmh==0)throw Error("infini")
return distanceKm*60/vitesseKmh;
}
distanceDepartCruiseArrivee(vehicule,distanceEntreVilles)
{
    let debut=0
    let milieu=0
    let fin=0
    if (distanceEntreVilles<0)throw Error("illegal args")
    while (distanceEntreVilles>0) {
    distanceEntreVilles-=1;
    if (debut<vehicule.lapskmville)debut++;
    else if (fin<vehicule.lapskmville) fin++;
    else milieu++
    if (distanceEntreVilles==0) return [debut,milieu,fin];
    }
 }
vehiculeParcourtSegment(vehicule,segment)
{
this.checkSegment(segment);
let vitessesVehicule = vehicule.getVitesses()

return [this.calculDureeDeplacement(vitessesVehicule[0],segment[0]),this.calculDureeDeplacement(vitessesVehicule[1],segment[1]),this.calculDureeDeplacement(vitessesVehicule[2],segment[2])]
}
checkSegment(segment){
    for (let idxSegment=0;idxSegment<segment.length;idxSegment++){
    if (segment[idxSegment]<0) throw Error("illegal args")
    }
    }
getMinutesVehiculePracourtSegment(vehicule,segment)
{
    this.checkSegment(segment);
    let durees= this.vehiculeParcourtSegment(vehicule,segment)
    return durees[0]+durees[1]+durees[2]
}
getMinutesVehiculeParcourtDistances(vehicule,distances){
   let minutes=0
   minutes+=vehicule.getPositionEnMinute();
   for(let indxDistances=0; indxDistances<distances.length; indxDistances++)
   {
        minutes+=this.getMinutesVehiculeSurDistance(vehicule,distances,indxDistances)
   }
      minutes+=vehicule.getPositionFinMinute();
   return minutes;
}
getMinutesVehiculeSurDistance(vehicule,distances,indxDistances){
    let minutes=0;
   let minutesdistance =this.getMinutesVehiculePracourtSegment(vehicule,this.distanceDepartCruiseArrivee(vehicule,distances[indxDistances]));
    if (indxDistances>0 && indxDistances<distances.length-1){
    let actionenvile=vehicule.appliqueActionEnVille();
     minutes+=actionenvile;
    }
    minutes+=minutesdistance
    return minutes;
    }
}
