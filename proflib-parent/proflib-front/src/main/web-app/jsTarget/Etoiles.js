export class Etoiles extends HTMLElement{
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
                justify-content:center;
                font-size:2em;
                display:flex;
                  }
                </style>
           <div>
           </div>`
        var cloneDuTemplate = monTemplate.content.cloneNode(true);
        this.shadow.appendChild(cloneDuTemplate);
        this.afficherEtoiles();
    }
    static get observedAttributes()
    {
        return ['score'];
    }
    attributeChangedCallback(nameattr,oldval,newval)
    {
            this[nameattr]=newval;
    }
    afficherEtoiles()
    {
        console.log(this.score);
        let note=this.score.substr(0,1)
        let max = this.score.substr(2,3)
        console.log(note,max)
        for(let i=0; i<max; i++)
        {
            let etoilejaune = this.createEtoileJaune();
            let etoilevide= this.createEtoileVide();
            if (i<note) this.shadow.querySelector("div").appendChild(etoilejaune)
            else this.shadow.querySelector("div").appendChild(etoilevide)
        }
    }
    createEtoileJaune()
        {
            let etoile = document.createElement("div");
            etoile.innerHTML="&#9733;";
            return etoile;
        }
    createEtoileVide()
    {
        let etoile = document.createElement("div");
        etoile.innerHTML="&#9734;";
        return etoile;
    }
}
window.customElements.define('proflib-etoiles', Etoiles);

