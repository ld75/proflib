import {CarteDeVisite} from "./CarteDeVisite";

export class RechercheUtilisateur extends HTMLElement{
    constructor(){
        super();
        var monTemplate =document.createElement("template");
        monTemplate.innerHTML=`
        <textarea></textarea>
            `
        this.appendChild(monTemplate.content.cloneNode(true))
    }
    connectedCallback()
    {
        this.querySelector("textarea").onkeyup=function(){this.startSearch()}.bind(this)
    }

    startSearch() {

    }
}
window.customElements.define('proflib-rechercheutilisateur', RechercheUtilisateur);
