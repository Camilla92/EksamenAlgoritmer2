# Mappeeksamen i Algoritmer og Datastrukturer Høst 2020

# Krav til innlevering

Se oblig-tekst for alle krav, og husk spesielt på følgende:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* git bundle er levert inn
* Hovedklassen ligger i denne path'en i git: src/no/oslomet/cs/algdat/Eksamen/EksamenSBinTre.java
* Ingen debug-utskrifter
* Alle testene i test-programmet kjører og gir null feil (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet


# Beskrivelse av oppgaveløsning (4-8 linjer/setninger per oppgave)

Jeg har brukt git til å dokumentere arbeidet mitt. Jeg har 52? commits totalt, og hver logg-melding beskriver det jeg har gjort av endringer.

### Oppgave 1: 
- leggInn(T verdi): Lager to noder(p og q). Referansen P starter i roten. 
Går i gjennom treet helt til p er lik null. 
Setter Q til å være forelder til p.
Sjekker om cmp er mindre enn 0, og setter p.venstre. 
Hvis cmp er større enn 0 settes p.høyre. 
Dette gjør at p flytter seg til venstre eller høyre i treet. Setter så Q til å være forelder til ny node. 
Sjekker om Q er null, setter så rot til å være p. Sjekker så om cmp er mindre enn 0, setter så q.venstre til å være p. 
Hvis ikke settes q.høyre til p. Deretter økes antallet og endringer og returnerer true. 

### Oppgave 2:
- antall(T verdi)
Setter node p til å være lik rot. Oppretter integer variablen antallVerdi. 
Går gjennom treet så lenge p ikke er null. Oppretter en compare variabel cmp. 
Sjekker om cmp er mindre enn 0, setter så p til å være venstre barnet til p. 
Hvis ikke det, så sjekker man om cmp er lik 0, og øker antallVerdi med en. 
Deretter settes p til å være høyre barnet til p. 
Tilslutt returneres antallVerdi. 

### Oppgave 3:
- førstePostorden
Går gjennom while løkken til den er false. 
Sjekker så om p sitt venstre barn ikke er null. Setter så p til å være p sitt venstre barn. 
Sjekker så om p sitt høyre barn ikke er null. Setter så å p til å være p sitt høyre barn. 
Hvis p sitt venstrebarn og p sitt høyrebarn er null så returneres p. 

- nestePostorden
Kompendiet 5.1.7:
Hvis p ikke har en forelder ( p er rotnoden), så er p den siste i postorden.
Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
Hvis p er venstre barn til sin forelder f, gjelder:
Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
Hvis p ikke er enebarn (dvs. f.høyre er ikke null), så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.

### Oppgave 4:
- postorden
Sjekker først om treet er tomt. Oppretter node p til å være lik rot. 
Går så gjennom treet så lenge while løkken er sann, sjekker om p sitt venstre barn ikke er lik null. 
Setter så p til å være p sitt venstre barn. Sjekker så om p sitt høyre barn ikke er null. Setter så p til å være lik p sitt høyre barn.
Hvis ikke så skal løkken avsluttes. Deretter utføres det en oppgave som i dette tilfellet er en utskrift. 
Går igjennom løkken til løkken er false.Sjekker om p er lik rot, hvis den er det avsluttes løkken. 
Deretter lages det en ny node f til å være p.forelder. Sjekker som om f.høyre er null eller om p er lik f.høyre. 
Deretter skal while løkken gå så lenge den er true, og sjekk om p.venstre ikke er lik null. 
Sette p til å være p.venstre. Hvis p.høyre ikke er null settes p til p.høyre. Hvis ikke skal while løkken avsluttes og det skal utføres en oppgave som i dette tilfellet er en utskrift. 

-postordenRecursive
Sjekker først basistilfellet: hvis p er lik null så skal metoden returnere.
Dereetter kalles metoden med p.venstre som parameter
Dereetter kalles metoden med p.høyre som parameter
Deretter skrives oppgaven ut ved hjelp av oppgave. 

### Oppgave 5:
- Serialize
Oppretter først en ArrayDeque og en arraylist. Legger så til roten i begge listene. 
Går igjennom dequeListen kø så lenge den ikke er tom. Tar så en ut av ArrayQueue kø ved hjelp av pop metoden. 
Sjekker så at p sitt venstre barn ikke er lik null. Legger så inn p sitt venstre barn i ArrayDeque og i arraylist. 
Sjekker så om p sitt høyre barn ikke er lik nul. Legger så inn å sitt venstre barn i arraydeque og arraylist.
Returnerer så arraylisten. 

- Deserialize
Oppretter et tre av instansen EksamenSBinTre. Går igjennom arraylisten og legger inn i treet etter leggInn metoden.
Returnerer så treet av instansen EksamenSBinTre. 

### Oppgave 6:
- fjern(T verdi)
Sjekker om treet er tomt først, hvis det er tomt så skal den returnere false.
Deretter sjekkes det om verdien er lik null, da skal metoden returnere false. 
Deretter settes noden p til p være rot og forelder til å være null.
Går igjjenom treet helt til p er null. Sammenligner deretter verdi med p.verdi. 
Bytter så plass på forelder, slik at den er rotverdi, bytter plass på p, slik at p er venstrebarnet til p.
Bytter plass på forelder, slik at forelder er rotverdien, bytter plass på p, slik at p er høyrebarnet til p.
Hvis p er lik null, returneres det false. Hvis verdien ikke finnes returneres det false. 
Metoden sjekker deretter tilfellene 1,2 og 3
1. p har ingen barn ( p er en bladnode)
2. p har nøyaktig ett barn (venstre eller høyre barn)
3. p har to barn
Tilslutt minskes antall med en og metoden returnerer true. 

- fjernAlle(T verdi)
Sjekker først om treet er tomt.
Sjekker så om verdi har nullverdier og returnerer 0
Setter så verdiAntall til å være 0
Whileløkken skal gå så lenger fjern(verdi) returnerer true. 
Her skal verdiAntall økes med en for hver gang fjern(verdi) kjører. 
Tilslutt skal metoden returnere antall ganger verdien har blitt fjernet.
              
- nullstill()
Sjekker først at treet ikker er tomt og så lenge det ikke er tomt skal nullstill(Node<T> p) kalles. 

- private void nullstill(Node<T> p)
Er en hjelpemetode oppgave 6
Den sjekker først om p sitt venstrebarn ikke er lik null.
Deretter kalles nullstill med venstre subtre og metoden setter p.venstre lik null. 
Deretter sjekkes det om p sitt høyrebarn ikke er lik null 
Deretter kalles nullstill med høyre subtre og metoden setter p.høyre lik null.  
Tilslutt settes p.verdi til null.      
