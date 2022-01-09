export class CarteDeVisite extends HTMLElement{
constructor()
    {
        super();
        let div = document.createElement("div")
        div.innerHTML="haha"
        this.appendChild(div);

    }
}
window.customElements.define('proflib-cartedevisite', CarteDeVisite);

