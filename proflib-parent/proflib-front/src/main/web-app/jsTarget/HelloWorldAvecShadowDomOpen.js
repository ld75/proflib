export class HelloWorldAvecShadowDomOpen extends HTMLElement{
constructor(){
        super();
            this.attachShadow({mode:'open'})
           var monTemplate =document.createElement("template");
           monTemplate.innerHTML=`<span>HelloWorld ShadowDom open</span>`
           var cloneDuTemplate = monTemplate.content.cloneNode(true);
           this.shadowRoot.appendChild(cloneDuTemplate);
    }
    testshadowdom()
    {
        console.log("Mon dom est-il accessible ?");
        console.log("je suis accessible",this.shadowRoot.querySelector("span"))
    }
}
window.customElements.define('proflib-helloworldopen', HelloWorldAvecShadowDomOpen);