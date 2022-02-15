// exercice de TDD issu de https://osherove.com/tdd-kata-1
beforeAll(()=>{
})
it("vide_addString_vide",()=>{
let res = addString("");
expect(res).toEqual("");
})
it("un_addString_Un",()=>{
let res = addString("1");
expect(res).toEqual("1");
})

it("petitsChiffresSeparateurVirgule_addString_Somme",()=>{
let res = addString("1,2");
expect(res).toEqual("3");
})
it("PetitsChiffresSeparateurLigneEtVirgule_addString_Somme",()=>{
let res = addString("2\n2,2,2\n2");
expect(res).toEqual("10");
})
it("PetitsChiffresSeparateurLigneEtDefiniDansEntete_addString_Somme",()=>{
let res = addString("//;\n2;2;2;2;2");
expect(res).toEqual("10");
})
it("valeursNeg_addString_ErreursNegativesInterditesEtRemontees",()=>{
expect(()=>addString("//;\n2;-3;2;-4;2")).toThrowError("faux! -3,-4")
})
it("separateurs_addString_separateur pris en compte",()=>{
let res= addString("//[;]\n2;1001;1000")
expect(res).toEqual("1002");
})
it("separateur a plusieurs chars_addString_somme",()=>{
let res = addString("//[;;;;gdgdg;;;;]\n2;;;;gdgdg;;;;3;;;;gdgdg;;;;2\n2\n2")
expect(res).toEqual("11")
})
it("DeuxSeparateursDefinisDansHeader_addString_somme",()=>{
let res = addString("//[;;;;gdgdg;;;;][ddule]\n2;;;;gdgdg;;;;3ddule2\n2\n2")
expect(res).toEqual("11")
})
it("deuxSeparateursSpeciauxDansHeader_addString_somme",()=>{
let res = addString("//[*][%]\n1*2%3")
expect(res).toEqual("6")
})
function addString(str)
{
let delimiterstr= getDelimiter(str);
str = removeHeader(str)
let delimiters = delimiterstr.split("][")
let split = diviseLeString(str,delimiters);
if (split.length<2) return str;
checkNegatives(split)
let sum = additionner(split)
return sum.toString();
}
function checkNegatives(split)
{
let badvals = []
for (let idxsplit=0; idxsplit<split.length; idxsplit++){
    if (parseInt(split[idxsplit])<0) badvals.push(split[idxsplit])
}
if (badvals.length>0) throw Error("faux! "+badvals)
}
function diviseLeString(str,delimiters)
{
    let reservedDelimiter="#"
    for (let idxDelimiter=0; idxDelimiter<delimiters.length; idxDelimiter++)
    {
        let defautdelimiter= delimiters[idxDelimiter]
        str = str.replaceAll(defautdelimiter,reservedDelimiter)
    }
    str = str.replaceAll("\n",reservedDelimiter)
    let premierTableau = str.split(reservedDelimiter)
    return premierTableau;
}
function getDelimiter(str)
{
    if (str.indexOf("//")==0){
    let delimitercontent = str.substring(2,str.indexOf("\n"));
    if (delimitercontent.indexOf("]")>0) return delimitercontent.substring(1,delimitercontent.length-1)
     return delimitercontent;
    }
    else return ",";
}
function removeHeader(str)
{
    if (str.indexOf("//")==0) return str.substring(str.indexOf("\n")+1);
    else return str;
}
function additionner(split)
{
let sum=0
for (let idxsplit=0; idxsplit<split.length; idxsplit++){
let valeur = parseInt(split[idxsplit])
if (valeur>1000) continue
sum=sum+valeur
}
return sum
}