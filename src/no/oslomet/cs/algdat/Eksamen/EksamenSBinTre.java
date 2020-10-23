package no.oslomet.cs.algdat.Eksamen;


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

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
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
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    //oppgave 3
    private static <T> Node<T> førstePostorden(Node<T> p) {
        //Førstepostorden skal returnere første node postorden med p som rot,

        //oppgave 5.1.7 (h) fra kompendiet
        while (p!=null)
        {
            //sjekker om p har venstre og høyre barn.
            if (p.venstre != null) p = p.venstre;
            else if (p.høyre != null) p = p.høyre;
            //returnerer p.
            else return p;


        }
        return p;
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

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
        // Metodene skal henholdsvis serialisere (lage et kompakt format egnet for lagring til f.eks. fil - array)
       // Selve metoden serialize skal være iterativ og må bruke en kø til å traversere treet i nivå orden.
        // Arrayet som returneres av serialize skal inneholde verdiene i alle nodene i nivå orden.
    }

    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {

        // og deserialisere (lage et nytt tre ut ifra et array).
        // Deserialize skal da ta dette arrayet, og legge inn alle verdiene (igjen i nivå orden), og dermed gjenskape treet.
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
