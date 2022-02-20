export class DureeDeplacementRDV
{
calculDureeDeplacement(vitesseKmh, distanceKm){
if (vitesseKmh<0 || distanceKm<0)throw Error("illegal args")
if (vitesseKmh==0)throw Error("infini")
return distanceKm*60/vitesseKmh;
}
creersegments(departArrivee,listeDistances){
let res=new Array()
let depart; let arrivee="initial";
 while (arrivee!=departArrivee[1]){
   depart = this.findDepart(departArrivee,arrivee)
   arrivee=this.findNextArrivee(departArrivee,depart,listeDistances)
   res.push(parseInt(this.findDepartArriveeInListDistances(depart,arrivee,listeDistances)[2]))
 }
 return res;
}
findDepartArriveeInListDistances(depart,arrivee,listeDistances)
{
    for (let i=0; i<listeDistances.length; i++)
    {
        let distance = listeDistances[i].split(" ")
        if (distance[0]==depart && distance[1]==arrivee) return distance;
    }
    throw Error("pas trouve")
}
findDepart(departArrivee,arriveeprecedpente){
    if (arriveeprecedpente=="initial") return departArrivee.split(" ")[0]
    return arriveeprecedpente;
}
findNextArrivee(departArrivee,depart,listeDistances)
{
    let arrivee=departArrivee.split(" ")[1]
    for (let i=0; i<listeDistances.length; i++)
    {
        console.log ("III "+ i +" " + listeDistances.length+" " +listeDistances)
        let distance = listeDistances[i].split(" ")
        console.log("1 distance:"+distance+" - arrivee: "+arrivee)
        if (distance[1]==arrivee){
            console.log("2 arrivee: "+arrivee+" / distance0: "+distance[0]+" - depart: "+depart )
            if (distance[0]==depart)
            {
                 console.log("3 depart: "+depart)
                 return distance[1];
            }
            console.log("4 distance0: "+distance[0])
            arrivee=distance[0]
         }
          console.log("ONRECOMMENCE ? ")
             console.log(" i:  "+i)
                console.log("listedistancelengt-1: "+(listeDistances.length-1))
                console.log("listedistancelengt: "+listeDistances.length)
         if (i==listeDistances.length-1){
          console.log("Retour a -1")
          i=-1
         }
    }
}
isDepartEgalSegmentDepart(depart,distance){
return depart==distance.split(" ")[0]
}

isArriveeEgalSegmentArrivee(arrivee,distance){
return arrivee==distance.split(" ")[1]
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