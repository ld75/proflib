// exercice de TDD issu de https://osherove.com/tdd-kata-1
beforeAll(()=>{
})
it("test1",()=>{
let res = addString("");
expect(res).toEqual("");
})
it("test2",()=>{
let res = addString("1");
expect(res).toEqual("1");
})

it("test3",()=>{
let res = addString("1,2");
expect(res).toEqual("3");
})
it("test4",()=>{
let res = addString("2\n2,2,2\n2");
expect(res).toEqual("10");
})
it("test5",()=>{
let res = addString("//;\n2;2;2;2;2");
expect(res).toEqual("10");
})
it("test6",()=>{
expect(()=>addString("//;\n2;-3;2;-4;2")).toThrowError("faux! -3,-4")
})
it("test7",()=>{
let res= addString("//[;]\n2;1001;1000")
expect(res).toEqual("1002");
})
it("test8",()=>{
let res = addString("//[;;;;gdgdg;;;;]\n2;;;;gdgdg;;;;3;;;;gdgdg;;;;2\n2\n2")
expect(res).toEqual("11")
})
it("test10",()=>{
let res = addString("//[;;;;gdgdg;;;;][ddule]\n2;;;;gdgdg;;;;3ddule2\n2\n2")
expect(res).toEqual("11")
})
it("test11",()=>{
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