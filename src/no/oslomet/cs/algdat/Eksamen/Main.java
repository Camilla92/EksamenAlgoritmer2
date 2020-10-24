package no.oslomet.cs.algdat.Eksamen;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[]args){

        //innledning:
        EksamenSBinTre<String> tre= new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre.antall());

        EksamenSBinTre<Character> tre2= new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre2.antall());

        EksamenSBinTre<Integer> tre3= new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre3.antall());


        //test for oppgave 1:
        System.out.println("Opgpave 1.");

        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        EksamenSBinTre<Integer> tre4= new EksamenSBinTre<>(Comparator.naturalOrder());
        for(int verdi:a){

            tre4.leggInn(verdi);
        }
        System.out.println(tre4.antall()); //utskrift 10
        System.out.println(tre4.leggInn(4));
        System.out.println(tre4.antall()); //utskrift 11

        //test for oppgave 2
        System.out.println("Oppgave 2");

        Integer[] b={4,7,2,9,4,10,8,7,4,6};
        EksamenSBinTre<Integer> tre5 = new EksamenSBinTre<>(Comparator.naturalOrder());
        for(int verdi: b){
            tre5.leggInn(verdi);
        }

        System.out.println(tre5.antall());//10
        System.out.println(tre5.antall(5));//0

        System.out.println(tre5.antall(4));//3
        System.out.println(tre5.antall(7));//2
        System.out.println(tre5.antall(10));//1

        //test for oppgave 3
        System.out.println("Oppgave 3");

        //System.out.println(tre5.);
        System.out.println(tre5.toStringPostOrder());

        //test for oppgave 4
        System.out.println("Oppgave 4, del 1 ");
        tre5.postorden(c -> System.out.print(c + " "));

        System.out.println();
        System.out.println("Oppgave 4, del 2");
        tre5.postordenRecursive(c -> System.out.print(c + " "));
        System.out.println();

        //test for oppgave 5
        System.out.println("Oppgave 5, del 1");
        System.out.println(tre5.serialize());

        EksamenSBinTre<String> tre6 = new EksamenSBinTre<>(Comparator.naturalOrder());      // en konstruktør
        String[] verdi = "EIBGAHKLODNMCJF".split("");              // verdier i nivåorden
        for(String verdier: verdi){
            tre6.leggInn(verdier);
        }


        System.out.println(tre6.serialize());  // Utskrift: E I B G A H K L O D N M C J F

        //test for oppgave 6
        System.out.println("Oppgave 6");
        int[]siste = {4,7,2,9,4,10,8,7,4,6,1};
        EksamenSBinTre<Integer> tre7= new EksamenSBinTre<>(Comparator.naturalOrder());
        for(int verdier: siste){
            tre7.leggInn(verdier);
        }
        System.out.println(tre7.fjernAlle(4));
        System.out.println(tre7.fjernAlle(7));
        System.out.println(tre7.fjernAlle(8));
        System.out.println(tre7.fjern(2));
        System.out.println(tre7.antall());
        System.out.println(tre7+" "+tre7.serialize());
        ArrayList<Integer> liste= new ArrayList<>();
        EksamenSBinTre<Integer> tre8 = EksamenSBinTre.deserialize(liste, Comparator.naturalOrder());
        System.out.println(tre8.deserialize(liste, Comparator.naturalOrder()));
        for(int verdier: siste){
            tre8.leggInn(verdier);
        }
        System.out.println(tre8.antall()); //utskrift 11

    }
}
