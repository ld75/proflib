export class HelloWorldAvecShadowDomClosed extends HTMLElement{
constructor(){
        super();
           this.shadow =  this.attachShadow({mode:'closed'})
           var monTemplate =document.createElement("template");
           monTemplate.innerHTML=`<span>HelloWorld ShadowDom closed</span>`
           var cloneDuTemplate = monTemplate.content.cloneNode(true);
           this.shadow.appendChild(cloneDuTemplate);
    }
    testshadowdom()
        {
            console.log("Mon dom est-il accessible de l'interieur ?",this.shadowRoot.querySelector("span"))
        }
}
window.customElements.define('proflib-helloworldclosed', HelloWorldAvecShadowDomClosed);