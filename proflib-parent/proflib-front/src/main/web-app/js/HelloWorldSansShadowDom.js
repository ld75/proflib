export class HelloWorldSansShadowDom extends HTMLElement{
constructor(){
        super();
           var monTemplate =document.createElement("template");
           monTemplate.innerHTML=`<span>HelloWorld</span>`
           var cloneDuTemplate = monTemplate.content.cloneNode(true);
           this.appendChild(cloneDuTemplate);
    }
}
window.customElements.define('proflib-helloworldnoshadowdom', HelloWorldSansShadowDom);