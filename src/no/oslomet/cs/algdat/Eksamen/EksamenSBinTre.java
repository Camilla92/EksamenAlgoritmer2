package no.oslomet.cs.algdat.Eksamen;


import java.io.*;
import java.util.*;

public class EksamenSBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        public Node(T verdi) {
            this.verdi= verdi;
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public EksamenSBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    //oppgave 1

    public boolean leggInn(T verdi) {

        //kopi av kode 5.2.11 b

        Node<T> p = rot;    // p starter i roten
        Node<T> q = null;   // hjelpevariabel
        int cmp = 0;        // hjelpevariabel

        while (p != null)
        {
            q = p;                                // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);    // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;    // flytter p
        }

        p = new Node<>(verdi,q);  // q er forelder til ny node

        if (q == null) rot = p;
        else if (cmp < 0) q.venstre = p;
        else q.høyre = p;

        antall++;     // én verdi mer i treet
        endringer++;  // innlegging er en endring

        return true;  // vellykket innlegging
    }

    //oppgave 6
    public boolean fjern(T verdi) {
        //kode fra kompendiet: 5.2.8 d)
        //må sjekke om treet er tomt først.
        if(tom()){
            return false;
        }
        else if (verdi == null) return false;  // treet har ingen nullverdier
        //gjør endringene som skal til med forelderpeker
        Node<T> p = rot, forelder = null;   // forelder skal være forelder til p og p sin forelder har null verdi.

        while (p != null)            // leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner
            //bytter plass på forelder, slik at den er rotverdi, bytter plass på p, slik at p er venstrebarnet til p.
            if (cmp < 0) { forelder = p; p = p.venstre; }      // går til venstre
            //byter plass på forelder, slik at forelder er rotverdien, bytter plass på p, slik at p er høyrebarnet til p.
            else if (cmp > 0) { forelder = p; p = p.høyre; }   // går til høyre
            //hvis p er lik null, returnerer den false.

            else break;    // den søkte verdien ligger i p (verdi er rotnoden)
        }

        //finner ikke verdien.
        if (p == null) return false;
        //hvis p ikke har venstre barn eller høyre barn:
        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
        {
            //hvis p.venstre ikke er lik null så er b lik p sitt venstre barn.
            //hvis p sin venstre er lik null så skal b bli lik p sitt høyre barn.
            //Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
            //velger å skrive det slik, da det ser ryddigere ut og er lettere å lese.
            Node<T> b;
            if(p.venstre!= null){
                b=p.venstre;
            }
            else{
                b=p.høyre;
            }
            //forandret koden her
            //må også sjekke at b ikke har nullverdi og sette b sin forelder til å være lik forelder.
            if(b!=null){
                b.forelder = forelder;
            }
            //setter rot referansen til å være b
            if (p == rot) rot = b;
            //hvis p er lik forelder sitt venstre barn, settes forelder sitt venstre barn til å være b.
            else if (p == forelder.venstre) forelder.venstre = b;
            //hvis p sin forelder er lik null, settes p lik rot.
            else if (p.forelder == null) { p = rot;
            }
            //hvis ikke så settes forelder sitt høyrebarn til å være b.
            else forelder.høyre = b;
        }
        //p har to barn
        else  // Tilfelle 3)
        {

            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            //går i gjennom helt til r sitt venstre barn er null
            while (r.venstre != null)
            {

                s = r;    // s er forelder til r
                r = r.venstre;
            }
            //Da erstattes verdien til p med verdien til etterfølgeren til p i inorden
            p.verdi = r.verdi;   // kopierer verdien i r til p
            //sjekker at s og p er ulike.  Setter man s sitt venstrebarn peker til å være r sitt høyrebarn.
            if (s != p) s.venstre = r.høyre;
            //hvis ikke så settes s sitt høyre barn til å være r sitt høyrebarn.
            else s.høyre = r.høyre;
        }

        antall--;   // det er nå én node mindre i treet
        return true;

    }

    //oppgave 6
    public int fjernAlle(T verdi) {
        //kode fra kompendiet: 5.2.8 (3)
        //sjekker om treet er tomt.
        if(tom()){
            return 0;
        }
        //sjekker om verdi har nullverdier og returnerer 0
        if(verdi == null){
            return 0;
        }
        //setter antall til å være 0
        int verdiAntall = 0;

        //går så lenge fjern(verdi) er sann.
        while (fjern(verdi)) {
            //legger på en for hver verdi som blir fjernet.
            verdiAntall++;
        }
        //returnerer antall ganger verdien har blitt fjernet.
        return verdiAntall;
        /*Den skal fjerne alle forekomstene av ​verdi​ i treet.
         Husk at duplikater er tillatt.
         Dermed kan en og samme verdi ligge flere steder i treet.
         Metoden skal returnere antallet som ble fjernet.
         Hvis treet er tomt, skal 0 returneres.
         */

    }

    //oppgave 2
    public int antall(T verdi) {


        // Den skal returnere antall forekomster av ​verdi​ i treet.

        // Det er tillatt med duplikater og det betyr at en verdi kan forekomme flere ganger.

        // Hvis ​verdi​ ikke er i treet (​null​ er ikke i treet), skal metoden returnere 0.

        //kode fra kompendiet oppgave 5.2.6 (2)

        //setter p lik rot.
        Node<T> p = rot;
        //setter antall verdier til 0
        int antallVerdi = 0;

        //går igjennom treet så lenger p ikke er lik nulll
        while (p != null)
        {
            //sammenligner verdi med p.verdi
            int cmp = comp.compare(verdi,p.verdi);
            //hvis cmp er mindre enn 0 er p sitt venstre barn(de er ulike).
            if (cmp < 0) p = p.venstre;
            //hvis ikke så..
            else
            {
                //hvis cmp er lik 0(at de er like) så skal antall forekomsten av verdien øke.
                if (cmp == 0) antallVerdi++;
                //setter så p til å være p sitt høyre barn.
                p = p.høyre;
            }
        }
        return antallVerdi;

    }

    public void nullstill() {
        /*Den skal traversere (rekursivt eller iterativt) treet i ​en eller annen
        rekkefølge og sørge for at samtlige pekere og nodeverdier i treet blir nullet.
         Det er med andre ord ikke tilstrekkelig å sette ​rot​ til ​null​ og ​antall​ til 0.
         hentet fra kompendiet 5.2.8 (5)
         */
        //sjekker at treet ikke er tomt
        if (!tom()) {
            nullstill(rot);  // nullstiller

        }
        rot = null; antall = 0;      // treet er nå tomt

    }
    //hjelpemetode oppgave 6
    //hentet fra kompendiet 5.2.8 (5)
    private void nullstill(Node<T> p)
    {

        //sjekker om p sitt venstrebarn ikke er lik null
        if (p.venstre != null)
        {
            nullstill(p.venstre);      // venstre subtre
            p.venstre = null;          // nuller peker
        }
        //sjekker om p sitt høyrebarn er ikke lik null
        if (p.høyre != null)
        {
            nullstill(p.høyre);        // høyre subtre
            p.høyre = null;            // nuller peker
        }
        p.verdi = null;              // nuller verdien
    }

    //oppgave 3
    private static <T> Node<T> førstePostorden(Node<T> p) {
        //Førstepostorden skal returnere første node postorden med p som rot,

        //oppgave 5.1.7 (h) fra kompendiet
        while (true)
        {
            //sjekker om p har venstre og høyre barn.
            if (p.venstre != null) p = p.venstre;
            else if (p.høyre != null) p = p.høyre;
            //returnerer p.
            else return p;
        }
    }
    //oppgave 3
    private static <T> Node<T> nestePostorden(Node<T> p) {
        /*
        Postorden  5.1.7:
            Hvis p ikke har en forelder ( p er rotnoden), så er p den siste i postorden.
            Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
            Hvis p er venstre barn til sin forelder f, gjelder:
            Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
            Hvis p ikke er enebarn (dvs. f.høyre er ikke null), så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.
         */
        //hentet fra instruksene i kompendiet 5.1.7
        // Hvis p ikke har en forelder ( p er rotnoden), så er p den siste i postorden.
        Node<T> forelder= p.forelder;
        //sjekker om forelder har null verdi.
        if(forelder==null){
            //returnerer null.
            return null;
        }
        //Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
        else if(p == forelder.høyre){
            //returner=forelder;
            return forelder;
        }
        else if(p==forelder.venstre) {
            //Hvis p er venstre barn til sin forelder f, gjelder:
            //Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
            if (forelder.høyre == null) {
                //returner= forelder;
                return forelder;
            } else if (forelder.høyre != null) {
                //Hvis p ikke er enebarn (dvs. f.høyre er ikke null),
                // så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.
                return førstePostorden(forelder.høyre);
            }
        }
        return p;




    }
    //oppgave 4
    public void postorden(Oppgave<? super T> oppgave) {
        //Du skal implementere den første funksjonen uten bruk av rekursjon og uten bruk av hjelpevariabler som stack / queue.
        //Du skal bruke funksjonen nestePostorden fra forrige oppgave.
        // Start med å finne den første noden ​p​ i postorden.
        // Deretter vil (f.eks. i en while-løkke) setningen: ​p = nestePostorden(p);​ gi den neste. Osv. til​ p​ blir ​null​.

        //kode fra kompendiet 5.1.15  (1)
        if (tom()) return;  // tomt tre

        Node<T> p = rot;

        while (true) // flytter p til den første i postorden
        {
            if (p.venstre != null) p = p.venstre;
            else if (p.høyre != null) p = p.høyre;
            else break;
        }

        oppgave.utførOppgave(p.verdi);

        while (true)
        {
            if (p == rot) break;  // den siste i postorden

            Node<T> f = p.forelder;
            if (f.høyre == null || p == f.høyre) p = f;
            else
            {
                p = f.høyre;
                while (true)
                {
                    if (p.venstre != null) p = p.venstre;
                    else if (p.høyre != null) p = p.høyre;
                    else break;
                }
            }
            oppgave.utførOppgave(p.verdi);
        }

    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);

    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        //brukt inspirasjon fra Programkode 5.1.10 a og forelesningen om recursive traversering av postorden)
        //basistilfellet
        if(p ==null){
            return;
        }
        //kallesr metoden med p.venstre som parameter
        postordenRecursive(p.venstre, oppgave);
        //kaller metoden med med p.høyre som parameter.
        postordenRecursive(p.høyre, oppgave);
        //skriver ut oppgaven
        oppgave.utførOppgave(p.verdi);

    }

    //oppgave 5
    public ArrayList<T> serialize() {
        //kode hentet fra kompendiet: 5.1.6 (d)
        if (tom()) return null;                   // tomt tre
        //gjort om og brukt ArrayDeque istedenfor KØ
        //Selve metoden serialize skal være iterativ og må bruke en kø til å traversere treet i nivå orden.
        ArrayDeque<Node<T>> kø= new ArrayDeque<>();
        //oppretter en arrayList
        ArrayList<T> liste= new ArrayList<>();
        kø.add(rot);                     // legger inn roten i arraydeque
        //legger inn roten i arrayList
        liste.add((T) rot.verdi);

            while (!kø.isEmpty())                    // så lenge som køen ikke er tom
            {
                Node<T> p = kø.pop();             // tar ut fra køen

                //sjekker at p sitt venstre barn ikke er lik null
                if (p.venstre != null) {
                    //legger til p sitt venstre barn i deque
                    kø.add(p.venstre);
                    //legger til p sitt venstre barn i arraylist.
                    liste.add((T) p.venstre.verdi);
                }
                //sjekker om p sitt høyre barn ikke er lik null
                if (p.høyre != null) {
                    //legger til p sitt høyre barn i deque
                    kø.add(p.høyre);
                    //legger til p sitt høyre barn i arraylist.
                    liste.add((T) p.høyre.verdi);
                }
        }
        //returnerer arraylist
        return liste;
        // Metodene skal henholdsvis serialisere (lage et kompakt format egnet for lagring til f.eks. fil - array)
        // Selve metoden serialize skal være iterativ og må bruke en kø til å traversere treet i nivå orden.
        // Arrayet som returneres av serialize skal inneholde verdiene i alle nodene i nivå orden.
    }





    //oppgave 5
    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        // og deserialisere (lage et nytt tre ut ifra et array).
        // Deserialize skal da ta dette arrayet, og legge inn alle verdiene (igjen i nivå orden), og dermed gjenskape treet.

        //oppretter et tre av instansen EksamenSBinTre
        EksamenSBinTre<K> tre = new EksamenSBinTre<K>(c);
        //går igjennom arraylisten og legger inn i treet etter leggInn metoden.
        for (K liste : data) {
            tre.leggInn(liste);
        }
        //returnerer treet.
        return tre;

    }

} // ObligSBinTre
