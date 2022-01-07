import '@ungap/custom-elements';
import {CarteDeVisite} from '../../web-app/js/CarteDeVisite.js'
beforeAll(async () => {
    console.log("hello");
    });
    describe('test jest', () => {
        it('nom du test', () => {
            console.log("world")
        })
    })


describe('carte de visite', () => {
    it('instancier nouvelle carte de visite', () => {
        let carteDeVisite = new CarteDeVisite();
    })
})