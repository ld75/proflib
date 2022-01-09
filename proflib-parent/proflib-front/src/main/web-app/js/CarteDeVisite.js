export class CarteDeVisite extends HTMLElement{
constructor(){
        super();
        this.shadow = this.attachShadow({mode:'closed'})
    var monTemplate =document.createElement("template");
    monTemplate.innerHTML=`
            <style>
             :host>div{
                display:grid;
                grid-template-columns: 1fr 1fr;
                grid-template-rows: auto 1fr 3fr;
                grid-column-gap: 10px;
                grid-row-gap: 15px;
                grid-template-areas:
                  "fonction fonction"
                  "nom prenom"
                  "photo photo" 
                  "etoiles etoiles";
                  }
             :host >div >div{
                justify-content: center;
                border: 1px solid;
             }
          
    }
            </style>
           <div>
            <div style="grid-area:nom">nom</div>
            <div style="grid-area:prenom">prenomaaaaaaaaa</div>
            <div style="grid-area:fonction">type<slot name='type'></slot></div>
            <div style="grid-area:photo">photo</div>
            <div style="grid-area:etoiles">etoiles</div>
            </div>`
        var cloneDuTemplate = monTemplate.content.cloneNode(true);
        console.log(this)
        console.log(this.shadow)
        this.shadow.appendChild(cloneDuTemplate);
    }
    connectedCallback()
    {
        console.log(this.shadow)
    }
}
window.customElements.define('proflib-cartedevisite', CarteDeVisite);

