export class DureeDeplacementRDV
{
    calculDureeDeplacement(vitesseKmh, distanceKm){
    if (vitesseKmh<0 || distanceKm<0)throw Error("illegal args")
    if (vitesseKmh==0)throw Error("infini")
    return distanceKm*60/vitesseKmh;
    }
}