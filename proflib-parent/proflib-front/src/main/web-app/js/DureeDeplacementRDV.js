export class DureeDeplacementRDV
{
calculDureeDeplacement(vitesseKmh, distanceKm){
if (vitesseKmh<0 || distanceKm<0)throw Error("illegal args")
if (vitesseKmh==0)throw Error("infini")
return distanceKm*60/vitesseKmh;
}

 deSegmentVersTroisSegments(vehicule,distanceKm){
    let rayonKm=vehicule.lapskmville
if (distanceKm<0) throw Error("illegal args")
if (rayonKm>=distanceKm) return [distanceKm,0,0]
if (distanceKm>rayonKm*2) return [rayonKm,distanceKm-(rayonKm*2),rayonKm]
if (distanceKm>rayonKm) return [rayonKm,0,distanceKm-rayonKm]
return [0,0,0]
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
getMinutesVehiculePracourtTroisSegments(vehicule,segment)
{
    this.checkSegment(segment);
    let durees= this.vehiculeParcourtSegment(vehicule,segment)
    return durees[0]+durees[1]+durees[2]
}
getMinutesVehiculeParcourtDistances(vehicule,distancesKm){
   let minutes=0
   minutes+=vehicule.getPositionEnMinute();
   for(let indxDistances=0; indxDistances<distancesKm.length; indxDistances++)
   {
        minutes+=this.getMinutesVehiculeSurDistancePlusArrets(vehicule,distancesKm,indxDistances)
   }
      minutes+=vehicule.getPositionFinMinute();
   return minutes;
}
getMinutesVehiculeSurDistancePlusArrets(vehicule,distancesKm,indxDistances){
    let minutes=0;
    let segmentsdistancesKm = this.deSegmentVersTroisSegments(vehicule,distancesKm[indxDistances])
   let minutesdistance =this.getMinutesVehiculePracourtTroisSegments(vehicule,segmentsdistancesKm);
    if (indxDistances>0 && indxDistances<distancesKm.length){
    let actionenvile=vehicule.appliqueActionEnVille();
     minutes+=actionenvile;
    }
    minutes+=minutesdistance
    return minutes;
    }
}
 class Vehicule {
     constructor(vitessesarg) {
         this.vitesses = vitessesarg;
         this.comportementEnVille = function () {
             return 0
         }
         this.positionEnMinute = 0;
         this.positionFinMinute = 0;
         this.lapskmville = 7
     }

     getVitesses() {
         return this.vitesses
     }

     ajouteComportementEnVille(fonction) {
         this.comportementEnVille = fonction;
     }

     appliqueActionEnVille() {
         return this.comportementEnVille();
     }

     getPositionEnMinute() {
         return this.positionEnMinute
     }

     setPositionEnMinute(minutes) {
         this.positionEnMinute = minutes
     }

     setPositionFinEnMinute(minutes) {
         this.positionFinMinute = minutes
     }

     getPositionFinMinute() {
         return this.positionFinMinute
     }
 }