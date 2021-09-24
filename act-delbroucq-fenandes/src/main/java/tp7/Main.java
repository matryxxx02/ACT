package tp7;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] arg) throws Exception {
        ArrayList<Tache> taches;
        for(int i =6 ; i< 125 ; i++) {
        	String str = "C:\\Users\\alixd\\git\\act_delbroucq_fernandes\\act-delbroucq-fenandes\\ressources\\n40/n40_"+i+".txt";
        	System.out.println(str);
        	taches = readFile(str);

            PblJSP pbl = new PblJSP(taches);
            
            //ResultOrd randRes = pbl.getRandOrd();
            
            HillClimbing hillClimbing = new HillClimbing(pbl,pbl.getOrdHeuristi());
            //Ils ils = new Ils(pbl, pbl.getOrdHeuristi(), 10);
            long debut = System.nanoTime();
            //ResultOrd heurRes = pbl.getOrdHeuristi();
            //ResultOrd ilsRes = ils.algorithm();
            ResultOrd hillRes = hillClimbing.algorithme();
            long tempsExec = (System.nanoTime() - debut);
            
            

            int quality = pbl.getQuality(hillRes);
            try {
                BufferedWriter out = new BufferedWriter(
                        new FileWriter("hill40.dat", true));
                if (tempsExec < 600000) out.write(tempsExec+" "+quality+"\n");
                out.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
            //System.out.println("qualité ordonnancement Random : "+pbl.getQuality(randRes));
            //System.out.println("qualité ordonnancement Heuristique1 : "+pbl.getQuality(pbl.getOrdHeuristi()));
            //System.out.println("qualité ordonnancement Heuristique2 : "+pbl.getQuality(pbl.getOrdHeuristi2()));
            //System.out.println("qualité ordonnancement Hill climbing : "+pbl.getQuality(hillClimbing.algorithme()));
            //System.out.println("qualité ordonnancement ILS : "+pbl.getQuality(ils.algorithm()));
        }

    }

    private static ArrayList<Tache> readFile(String filename) throws IOException {
        File fichier = new File(filename);
        FileReader reader = new FileReader(fichier);
        BufferedReader br = new BufferedReader(reader);
        ArrayList<Tache> taches = new ArrayList<Tache>();
        PblJSP pbl = new PblJSP(taches);
        int c = 0;
        int cumuleTempExec = 0;

        String line = br.readLine();
        while(line != null){
            String[] tab =  line.split(" ");
            //temps, poid, temps, temps de completion, retard
            int p = Integer.parseInt(tab[0]);
            int w = Integer.parseInt(tab[1]);
            int d = Integer.parseInt(tab[2]);
            taches.add(new Tache(p,w,d));
            cumuleTempExec += p;
            line = br.readLine();
        }

        br.close();
        System.out.println("cumule Temp exec : "+cumuleTempExec);
        return taches;
    }

    private static void printTachesResult (List<TacheOrd> result) {
        for(TacheOrd t : result){
            System.out.println("{temps d'exec : "+t.getTempsExecution()+" w*j : "+t.getPoid()*t.getRetard()+" completion : "+t.getCompletion()+" retard : "+t.getRetard()+"}");
        }
    }

    private static void randomSolution(ArrayList<Tache> taches){

    }

}
