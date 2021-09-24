package tp7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Ils {

    private HillClimbing localSearch;
    private ResultOrd solutionMin;
    private PblJSP problem;
    private int limiteIteration;

    public Ils(PblJSP problem, ResultOrd solution, int limite){
        localSearch = new HillClimbing(problem, solution);
        this.problem = problem;
        this.solutionMin = solution;
        this.limiteIteration = limite;
    }

    public ResultOrd algorithm() {

    	ResultOrd s = solutionMin; //solution initiale
    	ResultOrd resultSolution = solutionMin;
    	boolean resultFind = false;

        for (int i =0 ; i<limiteIteration; i++) {
            while (!resultFind) {
                s = localSearch.algorithme();
                int q1 = problem.getQuality(s);
                int q2 = problem.getQuality(resultSolution);
                if (q1 < q2) {
                    resultSolution = s;
                } else if(q1==q2){
                    resultFind = true;
                }
                //perturbation
                ArrayList<TacheOrd> perturbation = new ArrayList<TacheOrd>();
                perturbation.addAll(resultSolution.getTaches().subList(resultSolution.getTaches().size()/2,resultSolution.getTaches().size()));
                perturbation.addAll(perturbation.size(), resultSolution.getTaches().subList(0,resultSolution.getTaches().size()/2));
                this.localSearch.getSolution().setTaches(perturbation);
            }
        }
    	return resultSolution;
    }
}
