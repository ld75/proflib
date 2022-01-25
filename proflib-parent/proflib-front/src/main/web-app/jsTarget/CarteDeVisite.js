import {Etoiles} from './Etoiles.js';
export class CarteDeVisite extends HTMLElement{
constructor(){
        super();
        this.shadow = this.attachShadow({mode:'closed'})
    }
    connectedCallback()
    {
        var monTemplate =document.createElement("template");
        monTemplate.innerHTML=`
            <style>
             :host>div{
                padding:20px;
                display:grid;
                grid-template-columns: 1fr 1fr;
                grid-template-rows:3fr 0.5r 1fr;
                grid-column-gap: 10px;
                grid-row-gap: 5px;
                grid-template-areas:
                  "fonction photo"
                  "nom prenom"
                  "etoiles etoiles";
                 background-color:#e4e2fa;

                  }
             :host >div >div{
                justify-content: center;
                text-align: center;
             }
             img{
                width:100px;
                border-radius:50%;
             }
                </style>
           <div>
            <div style="grid-area:nom">${this.nom}</div>
            <div style="grid-area:prenom">${this.prenom}</div>
            <div style="grid-area:fonction"><slot name='type'></slot></div>
            <div style="grid-area:photo"><img alt="${this.nom} ${this.prenom}" src="${this.imgpath}"></img></div>
            <div style="grid-area:etoiles"><proflib-etoiles score=${this.score}></proflib-etoiles></div>
            </div>`
        var cloneDuTemplate = monTemplate.content.cloneNode(true);
        this.shadow.appendChild(cloneDuTemplate);
    }
    static get observedAttributes()
    {
        return ['nom','prenom','imgpath','score'];
    }
    attributeChangedCallback(nameattr,oldval,newval)
    {
            this[nameattr]=newval;
    }
}
window.customElements.define('proflib-cartedevisite', CarteDeVisite);

