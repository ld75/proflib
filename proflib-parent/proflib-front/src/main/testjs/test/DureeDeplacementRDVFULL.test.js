import {DureeDeplacementRDV} from '../../web-app/js/DureeDeplacementRDV.js'
import {Vehicule} from '../../web-app/js/Vehicule.js'
import {VehiculeFactory} from '../../web-app/js/VehiculeFactory.js'

beforeAll(async () => {
});

describe('calcul duree deplacement', () => {
let dureeDeplacementRDV = new DureeDeplacementRDV();
    it('vitesseNegDistanceNeg_calculDureeDeplacement_exceptionIllegalArgs', () => {
          expect(() => dureeDeplacementRDV.calculDureeDeplacement(-1, -1)).toThrowError('illegal args');
})
    it('vitesseZeroDistanceZero_calculDureeDeplacement_exceptionInfini', () => {
          expect(() => dureeDeplacementRDV.calculDureeDeplacement(0, 0)).toThrowError('infini');
})

    it('vitesseUnDistanceZero_calculDureeDeplacement_zerominutes', () => {
          let minutes = dureeDeplacementRDV.calculDureeDeplacement(1, 0);
          expect(minutes).toEqual(0)
})
    it('vitesseUnDistanceUn_calculDureeDeplacement_soixanteminutes', () => {
        let minutes = dureeDeplacementRDV.calculDureeDeplacement(1,1);
        expect(minutes).toEqual(60)
})
    it('vitesseDeuxDistanceUn_calculDureeDeplacement_soixanteminutes', () => {
        let minutes = dureeDeplacementRDV.calculDureeDeplacement(2,1);
        expect(minutes).toEqual(30)
})
//----------------------------------
    it('distanceEntreVillesNeg_deSegmentVersTroisSegments_IllegalArg', () => {
        expect(() => dureeDeplacementRDV.deSegmentVersTroisSegments(new Vehicule(),-123)).toThrowError('illegal args');
})
 it('distanceEntreVillesInferieureEgalATrois_ deSegmentVersTroisSegments_premiereDistanceSeulement', () => {
        let distances = dureeDeplacementRDV. deSegmentVersTroisSegments(new Vehicule(),2);
        expect(distances).toEqual([2,0,0])
})

 it('distanceEntreVillesinferieurEgalASix_ deSegmentVersTroisSegments_premiereEtTroisiemeDistancesSeulements', () => {
        let distances = dureeDeplacementRDV. deSegmentVersTroisSegments(new Vehicule(),5);
        expect(distances).toEqual([3,0,2])
})
 it('distanceEntreVillesEgalASix_ deSegmentVersTroisSegments_DebutEtFinRenseignees', () => {
        let distances = dureeDeplacementRDV. deSegmentVersTroisSegments(new Vehicule(),6);
        expect(distances).toEqual([3,0,3])
})

 it('distanceEntreVillesSuperieurASix_ deSegmentVersTroisSegments_troisDistancesRenseignees', () => {
        let distances = dureeDeplacementRDV. deSegmentVersTroisSegments(new Vehicule(),11);
        expect(distances).toEqual([3,5,3])
})
 it('unSegmentErrone_vehiculePracourtSegment_Exception', () => {
        expect(() => dureeDeplacementRDV.vehiculeParcourtSegment(new Vehicule(),[-1,-1,-1])).toThrowError('illegal args');
})
 it('unSegment_vehiculePracourtSegment_passeParLesTroisDistancesDuSegment', () => {
        let durees = dureeDeplacementRDV.vehiculeParcourtSegment(new Vehicule([1,1,1]),[1,1,1]);
        expect(durees).toEqual([60,60,60])
})
 it('unFauxSegmentNegatif_getMinutesVehiculePracourtSegment_dureeMinutesSegmet', () => {
        expect(() =>  dureeDeplacementRDV.getMinutesVehiculePracourtTroisSegments(new Vehicule([1,1,1]),[-1,-1,-1])).toThrowError('illegal args');
})
 it('unSegmentNull_getMinutesVehiculePracourtSegment_zero', () => {
        let duree = dureeDeplacementRDV.getMinutesVehiculePracourtTroisSegments(new Vehicule([1,1,1]),[0,0,0]);
        expect(duree).toEqual(0)
})
it('unSegment_getMinutesVehiculePracourtSegment_dureeMinutesSegmet', () => {
        let duree = dureeDeplacementRDV.getMinutesVehiculePracourtTroisSegments(new Vehicule([1,1,1]),[1,1,1]);
        expect(duree).toEqual(60+60+60)
})
it('vitessesVehiculeEtDistancesNegatives_getMinutesVehiculeParcourtDistances_exception', () => {
        expect(() =>  dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(new Vehicule([-1,-1,-1]),[-1,-1,-1])).toThrowError('illegal args');
})
it('plusieursdistances_getMinutesVehiculeParcourtDistances_dureeMinutes', () => {
        let duree = dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(new Vehicule([50,284,50]),[133,218]);
        expect(duree).toEqual(86.01971830985916)
})
it('arriveeDansVille_appliquerDureeArretDansVille_dureeDePauseajoutee', () => {
        let vitessesDeSegmentsKmh=[1,1,1]
        let pauseEntreGare=100;
        let vehiculeFactory = new VehiculeFactory()
        let train = vehiculeFactory.buildTrain(vitessesDeSegmentsKmh,pauseEntreGare,0,0);
        let duree = dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(train,[1,1,1]);
        expect(duree).toEqual(60+60+60+pauseEntreGare*2)
})
it('arriveeDansDeuxVilles_appliquerDureeArretDansVille_deuxPauses', () => {
        let vitessesDeSegmentsKmh=[1,1,1]
        let pauseEntreGare=100;
        let vehiculeFactory = new VehiculeFactory()
        let train = vehiculeFactory.buildTrain(vitessesDeSegmentsKmh,pauseEntreGare,0,0);
        let duree = dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(train,[1,1,1,1,1]);
        expect(duree).toEqual(60*5+pauseEntreGare*4)
})
it('debutVoyage_appliquerDureeDebut_dureeDebutjoutee', () => {
        let vitessesDeSegmentsKmh=[1,1,1]
        let dureeDebutVoyage=100;
        let vehiculeFactory = new VehiculeFactory()
        let train = vehiculeFactory.buildTrain(vitessesDeSegmentsKmh,0,dureeDebutVoyage,0);
        let duree = dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(train,[1,1,1]);
        expect(duree).toEqual(180+dureeDebutVoyage)
})
it('finvoyage_appliquerDureeFin_dureeFinajoutee', () => {
        let vitessesDeSegmentsKmh=[1,1,1]
        let finvoyage=100;
        let vehiculeFactory = new VehiculeFactory()
        let train = vehiculeFactory.buildTrain(vitessesDeSegmentsKmh,0,0,finvoyage);
        let duree = dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(train,[1,1,1]);
        expect(duree).toEqual(180+finvoyage)
})
it('train-vehicule comparaison', () => {
        let vehiculeFactory = new VehiculeFactory()
        let train = vehiculeFactory.buildTrain([10,100,10],0,0,0);
      let dureetrain = dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(train,[200,200,200]);
        let dureeVehicle = dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(new Vehicule([10,100,10]),[200,200,200]);
        console.log(dureetrain)
        console.log(dureeVehicle)
})
})
