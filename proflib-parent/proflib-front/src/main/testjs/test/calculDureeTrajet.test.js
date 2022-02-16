
it("zero_calcul_troiszeros",()=>{
expect( clacultrajet(0)).toEqual([0,0,0])
})

it("distanceNeg_calcul_illegalError",()=>{
expect(() => clacultrajet(-1)).toThrowError('illegal');
})
it("distance1km_calcul_tableau avec 1 0 0 ",()=>{
expect( clacultrajet(1)).toEqual([1,0,0])
})
it("distance7km_calcul_tableau avec 7 0 0 ",()=>{
expect( clacultrajet(7)).toEqual([7,0,0])
})
it("distance8km_calcul_tableau avec 7 0 1 ",()=>{
expect( clacultrajet(8)).toEqual([7,0,1])
})
it("distance14km_calcul_tableau avec 7 0 7 ",()=>{
expect( clacultrajet(14)).toEqual([7,0,7])
})
it("distance15km_calcul_tableau avec 7 1 7 ",()=>{
let res =  clacultrajet(15)
expect(res).toEqual([7,1,7])
})


function clacultrajet(distance){
let limite = 7
if (distance<0) throw Error("illegal")
if (distance<=limite)return [distance,0,0]
if (distance>2*limite) return [limite,distance-2*limite,limite]
return [limite,0,distance-limite]

}
