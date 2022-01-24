export class HelloWorldAvecShadowDomClosed extends HTMLElement{
constructor(){
        super();
        this.shadow = this.attachShadow({mode:'closed'})
           var monTemplate =document.createElement("template");
           monTemplate.innerHTML=`<span>HelloWorld</span>`
           var cloneDuTemplate = monTemplate.content.cloneNode(true);
           this.shadow.appendChild(cloneDuTemplate);
    }
}
window.customElements.define('proflib-helloworldclosed', HelloWorldAvecShadowDomClosed);