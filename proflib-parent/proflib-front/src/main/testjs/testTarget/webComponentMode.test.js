import '@ungap/custom-elements';
import {HelloWorldSansShadowDom} from '../../web-app/js/HelloWorldSansShadowDom.js'
import {HelloWorldAvecShadowDomOpen} from '../../web-app/js/HelloWorldAvecShadowDomOpen.js'
import {HelloWorldAvecShadowDomClosed} from '../../web-app/js/HelloWorldAvecShadowDomClosed.js'
beforeAll(async () => {
    console.log("BEFORE ALL hello world");
    });
beforeEach(async () => {
              document.body.innerHTML="";
     });
describe('instancier un web component et le mettre dans le DOM', () => {
    it.skip('Sans shadowdom', () => {
        let helloWorldSansShadowDom = new HelloWorldSansShadowDom();
        document.body.appendChild(helloWorldSansShadowDom);
        expect(document.querySelector("proflib-helloworldnoshadowdom")).toBeTruthy();
        expect (helloWorldSansShadowDom.shadowRoot).toBeFalsy();
        console.log("bodyInnerHTML sans shadow dom",document.body.innerHTML);
        console.log("querySelector dans le webcomponent sans shadowdom: ",document.body.querySelector("span"));
    })
    it.skip('Avec shadowdom Open', () => {
        let helloWorldAvecShadowDomOpen = new HelloWorldAvecShadowDomOpen();
        document.body.appendChild(helloWorldAvecShadowDomOpen);
        expect(document.querySelector("proflib-helloworldopen")).toBeTruthy();
        expect (helloWorldAvecShadowDomOpen.shadowRoot).toBeTruthy();
        console.log("bodyInnerHTML avec shadow dom open",document.body.innerHTML);
        console.log("querySelector du span depuis l'exterieur (webcomponent open): ",document.body.querySelector("span"));
        helloWorldAvecShadowDomOpen.testshadowdom();
        console.log("shadowRoot est il accessible depuis l'exterieur ? (webcomponent open): ",helloWorldAvecShadowDomOpen.shadowRoot);
    })
    it.skip('Avec shadowdom Close', () => {
        let helloWorldAvecShadowDomClosed = new HelloWorldAvecShadowDomClosed();
        document.body.appendChild(helloWorldAvecShadowDomClosed);
        expect(document.querySelector("proflib-helloworldclosed")).toBeTruthy();
        expect (helloWorldAvecShadowDomClosed.shadowRoot).toBeFalsy();
        console.log("bodyInnerHTML avec shadow dom closed",document.body.innerHTML);
        console.log("querySelector du span depuis l'exterieur (webcomponent closed): ",document.body.querySelector("span"));
        expect(()=>helloWorldAvecShadowDomClosed.testshadowdom()).toThrow(TypeError);
        console.log("shadowRooet est il accessible depuis l'exterieur ? (webcomponent closed): ",helloWorldAvecShadowDomClosed.shadowRoot);
    })
})