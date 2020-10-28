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
førstePostorden
Går gjennom while løkken til den er false. 
Sjekker så om p sitt venstre barn ikke er null. Setter så p til å være p sitt venstre barn. 
Sjekker så om p sitt høyre barn ikke er null. Setter så å p til å være p sitt høyre barn. 
Hvis p sitt venstrebarn og p sitt høyrebarn er null så returneres p. 

nestePostorden
Kompendiet 5.1.7:
Hvis p ikke har en forelder ( p er rotnoden), så er p den siste i postorden.
Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
Hvis p er venstre barn til sin forelder f, gjelder:
Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
Hvis p ikke er enebarn (dvs. f.høyre er ikke null), så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.


### Oppgave 4:
### Oppgave 5:
### Oppgave 6: