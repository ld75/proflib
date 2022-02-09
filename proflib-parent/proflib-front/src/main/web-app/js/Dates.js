export class Dates{
convertminutesToHeures(minutes)
{
    if (minutes<0) throw Error("illegal")
    if (minutes<10) return "00:0"+minutes
    if(minutes<60) return "00:"+minutes
    let heures = Math.floor(minutes/60)
    let minutesRestantes=minutes%60
     if (minutesRestantes<10) minutesRestantes="0"+minutesRestantes
    return heures+":"+minutesRestantes

}

}