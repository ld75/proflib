import '@ungap/custom-elements';
import {RechercheUtilisateur} from '../../web-app/js/RechercheUtilisateur.js'
beforeAll(async () => {
    document.body.innerHTML="";
});

describe('affichage barre de recherche', () => {
    it('instanciation barre de recherche', () => {
        let searchbar = new RechercheUtilisateur();
        expect(searchbar).not.toBeNull()
    })
    it('instanciation barre de recherche_a un champ de recherche', () => {
        let searchbar = new RechercheUtilisateur();
        document.body.appendChild(searchbar)
        console.log(document.body.innerHTML)
        expect(searchbar.innerHTML.indexOf("textarea")).toBeGreaterThan(1)
    })
    it('instanciation barre de recherche_faitUneRechercheà la saisie', () => {
        let searchbar = new RechercheUtilisateur();
        let searchbarSAVE=searchbar.startSearch;
        let rechercheExecutee=false;
        searchbar.startSearch=function(){rechercheExecutee=true;}
        document.body.appendChild(searchbar)
        document.body.querySelector("textarea").value="recherche";
        document.body.querySelector("textarea").dispatchEvent(new Event('focus'));
        document.body.querySelector("textarea").dispatchEvent(new KeyboardEvent('keyup',{'key':'a'}));
        expect(rechercheExecutee).toBeTruthy()
        searchbar.startSearch=searchbarSAVE;
    })
})

