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




})