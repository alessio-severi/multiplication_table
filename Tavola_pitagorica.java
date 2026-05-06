
/*

 Questo programma visualizza la tavola pitagorica per valori interi compresi tra due estremi n₀ e n scelti dall’utente.

 Funzionalità principali:
    – validazione dell'input (tipo, overflow e valore intero positivo, n > n₀, massimo 5 tentativi);
    - evita l’overflow su n * n;
    – scrittura della tavola pitagorica con intestazioni e griglia;
    – gestione precisa e automatica del layout: calcolo dinamico della larghezza in base ai valori immessi;
    – allineamento preciso e automatico dei numeri e simboli (intestazioni, separatori, angolo "x") in base ai valori immessi.

 Convenzione matematica: ℕ = {1, 2, 3, ...} (secondo Bourbaki).


 Autore: Alessio Severi
 Licenza: MIT License

 MIT License

 Copyright (c) 2025 Alessio Severi

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.

*/


// File Tavola_pitagorica_v1.java


import java.util.Scanner;

public class Tavola_pitagorica {
    
    public static void main(String[] args) {

        // dichiarazione variabili nel main e inizzializzate ove necessario
        int n, n0, count_cifra, x;
        int flag= 1;
        int flag1;
        int[] count_flag = {0};                     // array di supporto per contare i tentativi (passato per riferimento)


        // creazione dell'oggetto, istanza della classe Scanner
        Scanner sc= new Scanner(System.in);         // warning: da convertire a try-with-resources (verrà trattato nelle lezioni successive)
                                                    // in tale programma la chiusura dello stream sottostante: System.in, viene gestita manualmente


        // lettura convalidata dell'estremo inferiore n0
        n0 = leggiInteroPositivo("\nInserisci l'estremo inferiore n0: ", sc, count_flag);


        // controllo che il prodotto (n0+1) × (n0+1) non superi Integer.MAX_VALUE (evita overflow)
        // poichè n>= n0+1 
        while ((long) (n0+1 ) * (n0+1) > Integer.MAX_VALUE) {

            ++ count_flag[0];
            
            System.out.println("Attenzione: il valore di n0 deve essere un valore più piccolo.");

            if(count_flag[0] == 5){

                // avviso che sono finiti il numero massimo di tentativi per validare l'input
                System.out.print("\n\n\nErrore: il numero massimo di tentativi per validare l'input sono finiti, input non validato.\n\n\n");

                //  chiusura dello stream sottostante: System.in, ossia libero la risorsa associata allo Scanner
                sc.close();

                return;
            }
            System.out.println(String.format("Hai ancora %d tentativi.", 5-count_flag[0]));
            n0 = leggiInteroPositivo("Inserisci un nuovo valore per n (maggiore di n0): ", sc, count_flag);
        }


        // azzeramento dei tentativi prima del secondo input (sono distinti)
        count_flag[0]= 0;

        // lettura convalidata dell'estremo superiore n
        n  = leggiInteroPositivo("Inserisci l'estremo superiore n: ", sc, count_flag);


        // controllo che n sia strettamente maggiore di n0 e che il prodotto n × n non superi Integer.MAX_VALUE (evita overflow)
        while (n <= n0 || (long) n * n > Integer.MAX_VALUE) {

            ++ count_flag[0];
            if(n <= n0) System.out.println("Attenzione: il secondo numero (n) deve essere maggiore del primo (n0).");
            else System.out.println("Attenzione: il prodotto tra n e n deve essere un valore più piccolo.");

            if(count_flag[0] == 5){

                // avviso che sono finiti il numero massimo di tentativi per validare l'input
                System.out.print("\n\n\nErrore: il numero massimo di tentativi per validare l'input sono finiti, input non validato.\n\n\n");

                //  chiusura dello stream sottostante: System.in, ossia libero la risorsa associata allo Scanner
                sc.close();

                return;
            }
            System.out.println(String.format("Hai ancora %d tentativi.", 5-count_flag[0]));
            n = leggiInteroPositivo("Inserisci un nuovo valore per n (maggiore di n0): ", sc, count_flag);
        }




        System.out.println(String.format("\n\nTavola pitagorica da %d a %d\n\n", n0, n));

        // calcolo del numero massimo di cifre per allineare correttamente i valori
        count_cifra= (int)Math.log10(n*n) +1;


        // stampa della tavola pitagorica con gestione dinamica per l'impaginazione di valori, intestazioni e separatori
        for(int i=n0; i<=n; ++i){

            // vengono effettuate copie della prima riga:
            // la prima riga viene "duplicata" due volte, di cui una modificata per l'intestazione e una per i caratteri separatori
            // i è l'indice di riga 
            if( i==n0+1 && flag<=2){
                i= n0;
                ++flag;
            }

            flag1= 1;

            // vengono effettuate copie della prima colonna:
            // la prima colonna viene "duplicata" due volte, di cui una modificata per l'intestazione e una per i caratteri separatori
            // j è l'indice di colonna
            for(int j=n0; j<=n; ++j){

                if( j==n0+1 && flag1<=2){
                    j= n0;
                    ++flag1;
                }

                x=0;


                // stampa angolo in alto a sinistra
                if( flag== 1 && flag1 == 1){

                    System.out.print("x");
                    for(int l=1; l<= (int)Math.log10(i*j); ++l) System.out.print(" ");

                }

                // inizio della stampa di una parte della riga di separazione:
                // al posto dei valori che verrebbero occupati in tale riga duplicata vengono inseriti "-" (step 1)
                else if(flag== 2 ) for(int l=0; l<= (int)Math.log10(i*j); ++l) System.out.print("-");

                // inizio della stampa di una parte della colonna di separazione:
                // al posto dei valori che verrebbero occupati in tale colonna duplicata vine inserito "|" e 
                // aggiunti " " in modo da compensare la differenza di lunghezza tra un valore e il carattre "|" 
                else if(flag1== 2 ) {

                    System.out.print("|");
                    for(int l=1; l<= (int)Math.log10(i*j); ++l) System.out.print(" ");

                }
                // stampa dei valori della tavola
                else if(flag== 1 || flag1== 1 ){

                        // stampa per l'intestazione della riga e della colonna rispettivamente;
                        if(flag== 1){
                            System.out.print(j);
                            for(int l=1; l<= (int)Math.log10(i*j)-(int)Math.log10(j); ++l) System.out.print(" ");
                        } 
                        // if(flag1== 1)
                        else {
                            System.out.print(i);
                            for(int l=1; l<= (int)Math.log10(i*j)-(int)Math.log10(i); ++l) System.out.print(" ");
                        }
                        
                }
                // stampa la tavola pitagorica
                else System.out.print(i*j);

                 
                if(j== n){

                    // la riga di separazione finisce dove finisce il valore più grande contenuto nell'ultima colonna (step 3)
                    if(i==n0 && flag== 2 ) for(int l=1; l<= (int)Math.log10(n*n) - (int)Math.log10(i*j); ++l) System.out.print("-");
                    
                    // vai a capo se raggiunta fine riga
                    System.out.print("\n");            

                } 

                // caloco dinamicamnte, in base ai valori che costituiscono la tavola pitagorica, gli spazi necessari affinchè
                // si abbia un allineamento orizzontale dei valori in colonna, mentre nella riga di separazione con stessa logica 
                // verranno aggiunti "-" per continuarla (step 2).
                else{

                    for(int k=0; k<=count_cifra-1; ++k) {

                        x= x+ (9* (int)Math.pow(10,k));      // restituisce un double Math.pow  

                        if(i*j<= x && (i*j>= (int)Math.pow(10,k))) for(int l=1; l<= count_cifra - k; ++l) if(i==n0 && flag== 2 ) System.out.print("-");
                                                                                                            else System.out.print(" ");

                    }   
                }    
            }         
        }

        // migliora la leggibilità e l'esperienza utente
        System.out.print("\n\n");

        //  chiusura dello stream sottostante: System.in, ossia libero la risorsa associata allo Scanner
        sc.close();

    }   



    // Metodo ausiliario: viene gestita la validazione dell'input:
    // – controllo del tipo n (input non intero o overflow);
    // – controllo del valore n (intero positivo);
    public static int leggiInteroPositivo(String messaggio, Scanner sc, int[] count_flag) {

        int valore;
        int flag_error = 0;



        // messaggio descrittivo per l'utente per l'immissione dell'input 
        System.out.print(messaggio);


        // validazione dell'input n, scelto dall'utente
        do{
            
            // gestione della validazione errata e avvisi
            while( (flag_error== 1) || (flag_error== 2)){
                
                // messaggio all'utente, ps il codice Unicode per l'insieme dei numeri naturali è 2115, mentre 2208 è il codice Unicode per il simbolo di Peano.
                // (java non supporta il LaTex)
                System.out.println("\nAttenzione: input non valido, è necessario inserire un numero naturale n \u2208 \u2115 (convenzione di Bourbaki: \u2115 = {1, 2, 3 ...}).");
                System.out.println(String.format("Hai ancora %d tentativi.", 4-count_flag[0]));

                // messaggio descrittivo per l'utente per l'immissione dell'input 
                System.out.print("Rinserisci un numero, intero positivo, n che sia valido: ");


                // svuoto il buffer, se non ho un numero negativo o nullo, riempito dall'input, poiuchè il token viene validato.
                // sc.nextLine(); restitituisce true se il buffer contiene almeno un token disponibile
                if (flag_error== 1) sc.nextLine();


                // conteggio dei tentativi falliti nella validazione dell'input
                count_flag[0]++;

                // controllo il numero di tentativi falliti (sono accettati max 5 tentativi)
                if(count_flag[0] == 5){

                    // avviso che sono finiti il numero massimo di tentativi per validare l'input
                    System.out.print("\n\n\nErrore: il numero massimo di tentativi per validare l'input sono finiti, input non validato.\n\n\n");

                    //  chiusura dello stream sottostante: System.in, ossia libero la risorsa associata allo Scanner
                    sc.close();

                    // il programma termina (con buffer svuotato)
                    System.exit(1);
                }

                flag_error= 0;          // resetto il flag che segnala "errore sull'input" prima di rivalutare l'input 

            }

            // controllo del tipo di input (parte 1)
            if(!sc.hasNextInt()){
                flag_error = 1;         // errore 1: errore di tipo (l'input non è un numero intero: per esempio è una lettera, un carattere speciale, un emoji ecc...)
                                        // oppure ho un overflow → fa eseguire il while

                continue;               // salta l'esecuzione di sc.nextInt(), poiché prima va svuotato il buffer
                                        // → lo svuotamento verrà eseguito nel blocco while, che gestisce l'errore
            }

            // immissione dell'input dall'utente
            valore= sc.nextInt();


            // controllo del tipo di input (parte 2)
            if (valore > 0)  break;     //  input valido
            else  flag_error = 2;       //  errore 2: errore di valore (intero ≤ 0) → fa eseguire il while
              
        }while (true);

        // valore convalidato
        return valore;
    }

}

