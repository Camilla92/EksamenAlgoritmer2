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
- 

### Oppgave 3:
### Oppgave 4:
### Oppgave 5:
### Oppgave 6: