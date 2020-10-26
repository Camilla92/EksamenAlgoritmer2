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
        if (verdi == null) return false;  // treet har ingen nullverdier
        //gjør endringene som skal til med forelderpeker
        Node<T> p = rot, forelder = p.forelder;   // forelder skal være forelder til p

        while (p != null)            // leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner
            if (cmp < 0) { forelder = p; p = p.venstre; }      // går til venstre
            else if (cmp > 0) { forelder = p; p = p.høyre; }   // går til høyre
            else break;    // den søkte verdien ligger i p
        }
        if (p == null) return false;   // finner ikke verdi

        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
        {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
            if (p == rot) rot = b;
            else if (p == forelder.venstre) forelder.venstre = b;
            else if(p.forelder== null){
                p=rot;
            }
            else forelder.høyre = b;
           // if (b!=null) b.forelder = forelder;
        }
        else  // Tilfelle 3)
        {
            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            while (r.venstre != null)
            {
                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p) s.venstre = r.høyre;
            else s.høyre = r.høyre;
        }

        antall--;   // det er nå én node mindre i treet
        return true;


        //kode fra kompendiet 5.2.11(6)
        /*Node<T> p = rot, forelder = p.forelder;
        if (forelder == null) throw new IllegalStateException("Fjerning er ulovlig!");

        if (forelder.høyre == null)                     // Tilfelle 1)
        {
            // hvis q har et venstre barn, vil det når q
            // fjernes få ny forelder

            if (forelder.venstre != null)
            {
                forelder.venstre.forelder = forelder.forelder;     // ny forelder
            }

            if (p == null)                         // Tilfelle 1a)
            {
                if (forelder == rot)                        // q er lik roten
                {
                    rot = forelder.venstre;                   // q fjernes
                }
                else                                 // q ligger nede i treet
                {
                    forelder.forelder.høyre = forelder.venstre;      // q fjernes
                }
            }
            else // p != null                      // Tilfelle 1b)
            {
                if (forelder == p.venstre)                  // p.venstre har ikke høyre subtre
                {
                    p.venstre = forelder.venstre;             // q fjernes
                }
                else                                 // q ligger i subtreet
                {
                    forelder.forelder.høyre = forelder.venstre;      // q fjernes
                }
            }
        }
        else // q.høyre != null                  // Tilfelle 2)
        {
            forelder.verdi = p.verdi;                     // kopierer

            // hvis p har et høyre barn, vil det når p
            // fjernes få en ny forelder

            if (p.høyre != null)
            {
                p.høyre.forelder = p.forelder;       // ny forelder
            }

            if (forelder.høyre == p)                      // q.høyre har ikke venstre barn
            {
                forelder.høyre = p.høyre;                   // fjerner p
            }
            else                                   // q.høyre har venstre barn
            {
                p.forelder.venstre = p.høyre;        // fjerner p
            }

            p = forelder;                                 // setter p tilbake til q
        }


        forelder = null;                // q settes til null
        endringer++;             // en endring i treet
        antall--;                // en verdi mindre i treet
        return true;

         */
    }

    //oppgave 6
    public int fjernAlle(T verdi) {
        //kode fra kompendiet: 5.2.8 (3)
        int verdiAntall = 0;
        while (fjern(verdi)) {
            verdiAntall++;
        }

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
        Node<T> p = rot;
        int antallVerdi = 0;

        while (p != null)
        {
            int cmp = comp.compare(verdi,p.verdi);
            if (cmp < 0) p = p.venstre;
            else
            {
                if (cmp == 0) antallVerdi++;
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

        if (!tom()) nullstill(rot);  // nullstiller
        rot = null; antall = 0;      // treet er nå tomt
    }
    //hjelpemetode oppgave 6
    //hentet fra kompendiet 5.2.8 (5)
    private void nullstill(Node<T> p)
    {

        if (p.venstre != null)
        {
            nullstill(p.venstre);      // venstre subtre
            p.venstre = null;          // nuller peker
        }
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
        //return p;
    }
    //oppgave 3
    private static <T> Node<T> nestePostorden(Node<T> p) {
        //nestePostorden skal returnere den noden som kommer etter​ p ​i​ postorden.​
       /* if(førstePostorden(p)!=null){
            if (førstePostorden(p).venstre != null) p = p.venstre;
            else if (førstePostorden(p).høyre != null) p = p.høyre;
            //returnerer p.
            else return p;
        }
        else{
            // Hvis​ p​ er den siste i postorden, skal metoden returnere n​ull
            //siste i postorden er rotnoden.
            p = null;
        }

        return p;*/
      /* Node <T> forrige= førstePostorden(p);
       int cmp = 0;
       while(p!=null) {
           //p må flyttes.

           if(forrige!=)
           if (p.høyre == p.forelder) {
               return p;
           }
           // Hvis​ p​ er den siste i postorden, skal metoden returnere n​ull
           //siste i postorden er rotnoden.
           else if (p.forelder == null) {
               p = null;
               return p;
           }

           break;

       }*/

        Node<T> forelder= p.forelder;

            // Hvis​ p​ er den siste i postorden, skal metoden returnere n​ull
            //Hvis p ikke har en forelder ( p er rotnoden), så er p den siste i postorden.
            if (p.forelder == null) {
                p = null;
                return p;
            }


            //Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
           /* else if (forelder.høyre == p) {
                return forelder;//riktig
            }
            //Hvis p er venstre barn til sin forelder f, gjelder:
            else if (p == forelder.venstre) {
                //Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
                if (forelder.høyre == null) {
                    return forelder;
                }
                //Hvis p ikke er enebarn (dvs. f.høyre er ikke null),
                // så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.
                else if(forelder.høyre!= null) {
                    return forelder.høyre;
                }
            }*/
        if (forelder.høyre == null || p == forelder.høyre) {
            p = forelder;
        }
        else
        {
            p = forelder.høyre;
            while (true)
            {
                if (p.venstre != null){
                    p = p.venstre;
                }
                else if (p.høyre != null){
                    p = p.høyre;
                }
                else break;
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
        //brukt inspirasjon fra Programkode 5.1.10 a)
        if(p ==null){
            return;
        }

        postordenRecursive(p.venstre, oppgave);
        postordenRecursive(p.høyre, oppgave);
        oppgave.utførOppgave(p.verdi);

    }

    //oppgave 5
    public ArrayList<T> serialize() {


        //kode hentet fra kompendiet: 5.1.6 (d)
        if (tom()) return null;                   // tomt tre
        //gjort om og brukt ArrayDeque istedenfor KØ
        // Selve metoden serialize skal være iterativ og må bruke en kø til å traversere treet i nivå orden.
        ArrayDeque<Node<T>> kø= new ArrayDeque<>();

        ArrayList<T> liste= new ArrayList<>();
        //Kø<Node<T>> kø = new TabellKø<>();   // Se Avsnitt 4.2.2
        kø.add(rot);                     // legger inn roten
        liste.add((T) rot.verdi);
        //Integer høyre= p.høyre;


            while (!kø.isEmpty())                    // så lenge som køen ikke er tom
            {
                //liste.add((T) rot);
                Node<T> p = kø.pop();             // tar ut fra køen
                // System.out.print(p.verdi + " ");   // skriver ut

                if (p.venstre != null) {
                    kø.add(p.venstre);
                    liste.add((T) p.venstre.verdi);
                }
                if (p.høyre != null) {
                    kø.add(p.høyre);
                    liste.add((T) p.høyre.verdi);
                }
        }

        return liste;
        // Metodene skal henholdsvis serialisere (lage et kompakt format egnet for lagring til f.eks. fil - array)
        // Selve metoden serialize skal være iterativ og må bruke en kø til å traversere treet i nivå orden.
        // Arrayet som returneres av serialize skal inneholde verdiene i alle nodene i nivå orden.
    }





    //oppgave 5
    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {


        // og deserialisere (lage et nytt tre ut ifra et array).
        // Deserialize skal da ta dette arrayet, og legge inn alle verdiene (igjen i nivå orden), og dermed gjenskape treet.
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        //kø.add(rot);                     // legger inn roten

        //oppretter et tre av instansen EksamenSBinTre
        EksamenSBinTre<K> tre = new EksamenSBinTre<K>(c);
        //går igjennom arraylisten og legger inn i treet etter leggInn metoden.
        for (K liste : data) {
            tre.leggInn(liste);
        }
        //returnerer treet.
        return tre;
        //tre.leggInn((K)rot);

       /* Node<K> p= null;


        while (!data.isEmpty())                    // så lenge som køen ikke er tom
        {


            if (p.venstre != null) {

                tre.leggInn((K) p.venstre);
            }
            if (p.høyre != null) {

                tre.leggInn((K) p.høyre);
            }
        }

        return tre;


    }*/


    }

} // ObligSBinTre
