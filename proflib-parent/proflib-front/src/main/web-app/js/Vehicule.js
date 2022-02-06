export class Vehicule{
constructor(vitessesarg)
{
    this.vitesses=vitessesarg;
    this.comportementEnVille=function(){return 0}
    this.positionEnMinute=0;
    this.positionFinMinute=0;
    this.lapskmville=3
}
getVitesses(){return this.vitesses}
ajouteComportementEnVille(fonction){
    this.comportementEnVille=fonction;
}
appliqueActionEnVille(){
return this.comportementEnVille();
}
getPositionEnMinute(){
return this.positionEnMinute
}
setPositionEnMinute(minutes)
{
    this.positionEnMinute=minutes
}
setPositionFinEnMinute(minutes){
    this.positionFinMinute=minutes
}
getPositionFinMinute(){
return this.positionFinMinute
}
}