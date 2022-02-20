import {DureeDeplacementRDV} from '../../web-app/js/DureeDeplacementRDV.js'
import {Vehicule} from '../../web-app/js/Vehicule.js'
import {VehiculeFactory} from '../../web-app/js/VehiculeFactory.js'

beforeAll(async () => {
});

describe('calcul duree deplacement', () => {
let dureeDeplacementRDV = new DureeDeplacementRDV();
    it.skip('vitesseNegDistanceNeg_calculDureeDeplacement_exceptionIllegalArgs', () => {
          expect(() => dureeDeplacementRDV.calculDureeDeplacement(-1, -1)).toThrowError('illegal args');
})
    it.skip('vitesseZeroDistanceZero_calculDureeDeplacement_exceptionInfini', () => {
          expect(() => dureeDeplacementRDV.calculDureeDeplacement(0, 0)).toThrowError('infini');
})

    it.skip('vitesseUnDistanceZero_calculDureeDeplacement_zerominutes', () => {
          let minutes = dureeDeplacementRDV.calculDureeDeplacement(1, 0);
          expect(minutes).toEqual(0)
})
    it.skip('vitesseUnDistanceUn_calculDureeDeplacement_soixanteminutes', () => {
        let minutes = dureeDeplacementRDV.calculDureeDeplacement(1,1);
        expect(minutes).toEqual(60)
})
    it.skip('vitesseDeuxDistanceUn_calculDureeDeplacement_soixanteminutes', () => {
        let minutes = dureeDeplacementRDV.calculDureeDeplacement(2,1);
        expect(minutes).toEqual(30)
})
//----------------------------------
    it.skip('distanceEntreVillesNeg_deSegmentVersTroisSegments_IllegalArg', () => {
        expect(() => dureeDeplacementRDV.deSegmentVersTroisSegments(new Vehicule(),-123)).toThrowError('illegal args');
})
 it.skip('distanceEntreVillesInferieureEgalATrois_ deSegmentVersTroisSegments_premiereDistanceSeulement', () => {
        let distances = dureeDeplacementRDV. deSegmentVersTroisSegments(new Vehicule(),2);
        expect(distances).toEqual([2,0,0])
})

 it.skip('distanceEntreVillesinferieurEgalASix_ deSegmentVersTroisSegments_premiereEtTroisiemeDistancesSeulements', () => {
        let distances = dureeDeplacementRDV. deSegmentVersTroisSegments(new Vehicule(),5);
        expect(distances).toEqual([3,0,2])
})
 it.skip('distanceEntreVillesEgalASix_ deSegmentVersTroisSegments_DebutEtFinRenseignees', () => {
        let distances = dureeDeplacementRDV. deSegmentVersTroisSegments(new Vehicule(),6);
        expect(distances).toEqual([3,0,3])
})

 it.skip('distanceEntreVillesSuperieurASix_ deSegmentVersTroisSegments_troisDistancesRenseignees', () => {
        let distances = dureeDeplacementRDV. deSegmentVersTroisSegments(new Vehicule(),11);
        expect(distances).toEqual([3,5,3])
})
 it.skip('unSegmentErrone_vehiculePracourtSegment_Exception', () => {
        expect(() => dureeDeplacementRDV.vehiculeParcourtSegment(new Vehicule(),[-1,-1,-1])).toThrowError('illegal args');
})
 it.skip('unSegment_vehiculePracourtSegment_passeParLesTroisDistancesDuSegment', () => {
        let durees = dureeDeplacementRDV.vehiculeParcourtSegment(new Vehicule([1,1,1]),[1,1,1]);
        expect(durees).toEqual([60,60,60])
})
 it.skip('unFauxSegmentNegatif_getMinutesVehiculePracourtSegment_dureeMinutesSegmet', () => {
        expect(() =>  dureeDeplacementRDV.getMinutesVehiculePracourtTroisSegments(new Vehicule([1,1,1]),[-1,-1,-1])).toThrowError('illegal args');
})
 it.skip('unSegmentNull_getMinutesVehiculePracourtSegment_zero', () => {
        let duree = dureeDeplacementRDV.getMinutesVehiculePracourtTroisSegments(new Vehicule([1,1,1]),[0,0,0]);
        expect(duree).toEqual(0)
})
it.skip('unSegment_getMinutesVehiculePracourtSegment_dureeMinutesSegmet', () => {
        let duree = dureeDeplacementRDV.getMinutesVehiculePracourtTroisSegments(new Vehicule([1,1,1]),[1,1,1]);
        expect(duree).toEqual(60+60+60)
})
it.skip('vitessesVehiculeEtDistancesNegatives_getMinutesVehiculeParcourtDistances_exception', () => {
        expect(() =>  dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(new Vehicule([-1,-1,-1]),[-1,-1,-1])).toThrowError('illegal args');
})
it.skip('plusieursdistances_getMinutesVehiculeParcourtDistances_dureeMinutes', () => {
        let duree = dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(new Vehicule([50,284,50]),[133,218]);
        expect(duree).toEqual(86.01971830985916)
})
it.skip('arriveeDansVille_appliquerDureeArretDansVille_dureeDePauseajoutee', () => {
        let vitessesDeSegmentsKmh=[1,1,1]
        let pauseEntreGare=100;
        let vehiculeFactory = new VehiculeFactory()
        let train = vehiculeFactory.buildTrain(vitessesDeSegmentsKmh,pauseEntreGare,0,0);
        let duree = dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(train,[1,1,1]);
        expect(duree).toEqual(60+60+60+pauseEntreGare*2)
})
it.skip('arriveeDansDeuxVilles_appliquerDureeArretDansVille_deuxPauses', () => {
        let vitessesDeSegmentsKmh=[1,1,1]
        let pauseEntreGare=100;
        let vehiculeFactory = new VehiculeFactory()
        let train = vehiculeFactory.buildTrain(vitessesDeSegmentsKmh,pauseEntreGare,0,0);
        let duree = dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(train,[1,1,1,1,1]);
        expect(duree).toEqual(60*5+pauseEntreGare*4)
})
it.skip('debutVoyage_appliquerDureeDebut_dureeDebutjoutee', () => {
        let vitessesDeSegmentsKmh=[1,1,1]
        let dureeDebutVoyage=100;
        let vehiculeFactory = new VehiculeFactory()
        let train = vehiculeFactory.buildTrain(vitessesDeSegmentsKmh,0,dureeDebutVoyage,0);
        let duree = dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(train,[1,1,1]);
        expect(duree).toEqual(180+dureeDebutVoyage)
})
it.skip('finvoyage_appliquerDureeFin_dureeFinajoutee', () => {
        let vitessesDeSegmentsKmh=[1,1,1]
        let finvoyage=100;
        let vehiculeFactory = new VehiculeFactory()
        let train = vehiculeFactory.buildTrain(vitessesDeSegmentsKmh,0,0,finvoyage);
        let duree = dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(train,[1,1,1]);
        expect(duree).toEqual(180+finvoyage)
})
it.skip('train-vehicule comparaison', () => {
        let vehiculeFactory = new VehiculeFactory()
        let train = vehiculeFactory.buildTrain([10,100,10],0,0,0);
      let dureetrain = dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(train,[200,200,200]);
        let dureeVehicle = dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(new Vehicule([10,100,10]),[200,200,200]);
        console.log(dureetrain)
        console.log(dureeVehicle)
})
it.skip('sourceEtDesttotal + source-dest-distanceIntermediares_recupererSegments_segments', () => {
        let vehiculeFactory = new VehiculeFactory()
        let train = vehiculeFactory.buildTrain([10,100,10],0,0,0);
      let dureetrain = dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(train,[200,200,200]);
        let dureeVehicle = dureeDeplacementRDV.getMinutesVehiculeParcourtDistances(new Vehicule([10,100,10]),[200,200,200]);
        console.log(dureetrain)
        console.log(dureeVehicle)
})
it.skip('1findNextArrivee', () => {
        let departArrivee="a b"
        let listeDistances=["a b 3"]
        let depart="a"
        let res = dureeDeplacementRDV.findNextArrivee(departArrivee,depart,listeDistances)
        expect(res).toEqual("b")
})

it.skip('2findNextArrivee', () => {
        let departArrivee="a c"
        let listeDistances=["a b 3","b c 6","a d 5"]
        let depart="a"
        let res = dureeDeplacementRDV.findNextArrivee(departArrivee,depart,listeDistances)
        expect(res).toEqual("b")
})

it('3findNextArrivee', () => {
        let departArrivee="b f"
        let listeDistances=["a b 3","b c 6","e f 1","b h 1","a d 5","c d 5","d e 1"]
        let depart="b"
        let res = dureeDeplacementRDV.findNextArrivee(departArrivee,depart,listeDistances)
        expect(res).toEqual("c")
})

it.skip('DepartArrivee,ListeTrajet1_creerSegments_1trajet', () => {
        let depart_arrive="a b"
        let listeDistances=["a b 3"]
        let res = dureeDeplacementRDV.creersegments(depart_arrive,listeDistances)
        expect(res).toEqual([3])
})
it.skip('DepartArrivee,ListeTrajet2DontUnNon_creerSegments_1trajet', () => {
        let depart_arrive="a b"
        let listeDistances=["a b 5","a c 3"]
        let res = dureeDeplacementRDV.creersegments(depart_arrive,listeDistances)
        expect(res).toEqual([5])
})
it.skip('3EtIntrus_creerSegments_3trajets', () => {
        let depart_arrive="a c"
        let listeDistances=["e f 10","a g 9","a b 5","b c 3","c d 8"]
        let res = dureeDeplacementRDV.creersegments(depart_arrive,listeDistances)
        expect(res).toEqual([5,3])
})

it.skip('DepartArrivee,ListeTrajet2_creerSegments_2trajets', () => {
        let depart_arrive="a c"
        let listeDistances=["a b 5","b c 3"]
        let res = dureeDeplacementRDV.creersegments(depart_arrive,listeDistances)
        expect(res).toEqual([5,3])
})
it.skip('3_creerSegments_3trajets', () => {
        let depart_arrive="a d"
        let listeDistances=["a b 5","b c 3","c d 8"]
        let res = dureeDeplacementRDV.creersegments(depart_arrive,listeDistances)
        expect(res).toEqual([5,3,8])
})
it.skip('3desordre_creerSegments_3trajets', () => {
        let depart_arrive="a d"
        let listeDistances=["a b 5","c d 8","b c 3"]
        let res = dureeDeplacementRDV.creersegments(depart_arrive,listeDistances)
        expect(res).toEqual([5,3,8])
})

it.skip('vraiesData_creerSegments_prévu', () => {
        let depart_arrive="Orleans Nantes"
        let listeDistances=["Orleans Blois 60","Tours Varennes-sur-mer 64.2","Varennes-sur-mer Saumur 11.2","Jarnac Cognac 6","Clisson Nantes 33.7","Cholet Clisson 35.9","Blois Tours 65","Angers Cholet 65","Saumur Angers 66"]
        let res = dureeDeplacementRDV.creersegments(depart_arrive,listeDistances)
        expect(res).toEqual([60,65,64.2,11.2,66,65,35.9,33.7])
})


})
