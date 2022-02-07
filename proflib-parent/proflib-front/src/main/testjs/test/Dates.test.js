import {Dates} from '../../web-app/js/Dates.js'
it("convertminutesToHeures",()=>{
let dates = new Dates();
expect(()=>{dates.convertminutesToHeures(-1)}).toThrowError("illegal")
})
it("zero_convertminutesToHeures",()=>{
let dates = new Dates();
expect(dates.convertminutesToHeures(0)).toEqual("00:00")
})
it("unmin_convertminutesToHeures",()=>{
let dates = new Dates();
expect(dates.convertminutesToHeures(1)).toEqual("00:01")
})
it("50min_convertminutesToHeures",()=>{
let dates = new Dates();
expect(dates.convertminutesToHeures(50)).toEqual("00:50")
})
it("60min_convertminutesToHeures",()=>{
let dates = new Dates();
expect(dates.convertminutesToHeures(60)).toEqual("01:00")
})
it("61min_convertminutesToHeures",()=>{
let dates = new Dates();
expect(dates.convertminutesToHeures(61)).toEqual("01:01")
})
it("80min_convertminutesToHeures",()=>{
let dates = new Dates();
expect(dates.convertminutesToHeures(80)).toEqual("01:20")
})
it("120min_convertminutesToHeures",()=>{
let dates = new Dates();
expect(dates.convertminutesToHeures(120)).toEqual("02:00")
})
it("132min_convertminutesToHeures",()=>{
let dates = new Dates();
expect(dates.convertminutesToHeures(132)).toEqual("02:12")
})