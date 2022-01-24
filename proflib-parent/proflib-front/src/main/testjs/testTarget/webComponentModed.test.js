//import '@ungap/custom-elements';
import {HelloWorldSansShadowDom} from '../../web-app/js/HelloWorldSansShadowDom.js'
import {HelloWorldAvecShadowDomOpen} from '../../web-app/js/HelloWorldAvecShadowDomOpen.js'
import {HelloWorldAvecShadowDomClosed} from '../../web-app/js/HelloWorldAvecShadowDomClosed.js'
beforeAll(async () => {
    console.log("BEFORE ALL hello world");
    });
beforeEach(async () => {
              document.body.innerHTML="";
     });
    describe('test jest', () => {
        it('nom du test', () => {
            console.log("TEST hello world")
        })
    })

describe('instancier un web component et le mettre dans le DOM', () => {
    it('Sans shadowdom', () => {
        let helloWorldSansShadowDom = new HelloWorldSansShadowDom();
        document.body.appendChild(helloWorldSansShadowDom);
        expect(document.querySelector("proflib-helloworldnoshadowdom")).toBeTruthy();
        expect (helloWorldSansShadowDom.shadowRoot).toBeFalsy();
        console.log("bodyInnerHTML sans shadow dom",document.body.innerHTML);
    })
    it('Avec shadowdom Open', () => {
        let helloWorldAvecShadowDomOpen = new HelloWorldAvecShadowDomOpen();
        document.body.appendChild(helloWorldAvecShadowDomOpen);
        expect(document.querySelector("proflib-helloworldopen")).toBeTruthy();
        expect (helloWorldAvecShadowDomOpen.shadowRoot).toBeTruthy();
        console.log("bodyInnerHTML avec shadow dom open",document.body.innerHTML);
    })
    it('Avec shadowdom Close', () => {
        let helloWorldAvecShadowDomClosed = new HelloWorldAvecShadowDomClosed();
        document.body.appendChild(helloWorldAvecShadowDomClosed);
        expect(document.querySelector("proflib-helloworldclosed")).toBeTruthy();
        expect (helloWorldAvecShadowDomClosed.shadowRoot).toBeFalsy();
        console.log("bodyInnerHTML avec shadow dom closed",document.body.innerHTML);
    })
})