import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Graph {
    Vector<Vector<Object>> arrivalRoutes = new Vector<>();
    Vector<Vector<Object>> floydVector = new Vector<>();
    Scanner sc;

    public Graph(){
        this.sc = new Scanner(System.in);
    }

    /**
     *Calculate the center of the matrix using the one created by the Floyd algorithm
     * @param kValue
     * @return
     */
    public String centerCity(HashMap<String,Integer> kValue){
        //------------------------------------
        Vector<Object> maxVector = new Vector<>();
        //------------------------------------
        for(int i=0;i<kValue.size();i++){
            Vector<Object> pos = floydVector.get(i);
            if(i==0){
                maxVector = pos;
            }else{
                for(int j=0;j<pos.size();j++){
                    if(!maxVector.get(j).equals("NE")){
                        if(pos.get(j).equals("NE")){
                            maxVector.set(j,"NE");
                        }else if((int)(maxVector.get(j))<(int)(pos.get(j))){
                            maxVector.set(j,pos.get(j));
                        }
                    }
                }
            }
        }
        //-----------------------------------
        System.out.println(maxVector);
        Object center = 0;
        int contN = 0;
        //-----------------------------------
        while(contN < maxVector.size()){
            if(contN == 0){
                center = maxVector.get(0);
            }else if(!maxVector.get(contN).equals("NE")){
                if(center.equals("NE")){
                    center = maxVector.get(contN);
                }else{
                    if((int) maxVector.get(contN) < (int) center){
                        center = maxVector.get(contN);
                    }
                }
            }
            contN++;
        }
        //-----------------------------------
        int posValue = maxVector.indexOf(center);
        return SupportFunctions.getKeyfromValue(kValue,posValue);
    }

    /**
     * After reading the file and creating the matrix where the weather times will be stored,
     * the vector is created where the user selects the times they want for each city.
     * @param initialVector
     * @param kValue
     * @return finalVector
     */
    public Vector<Vector<Object>> makeTheFistVector(Vector<Vector<Object>> initialVector, HashMap<String,Integer> kValue){
        Vector<Vector<Object>> finalVector =  new Vector<Vector<Object>>(); //Create vector
        for(int i=0;i<initialVector.size();i++){
            Vector<Object> startCity = initialVector.get(i);
            Vector<Object> NstartCity = new Vector<>();
            //Create the vector where the user will select the type of weather they want
            int cont = 0;
            for(int j=0;j<startCity.size();j++){
                if(cont == i){
                    NstartCity.add(0);
                }else{
                    Object element = startCity.get(j);
                    //If the element is a list, it means that the user needs to be asked what they want here.
                    if(element instanceof List){
                        List<String> tiposClima = (List<String>) element;
                        System.out.println("De "+SupportFunctions.getKeyfromValue(kValue,i)+" a "+SupportFunctions.getKeyfromValue(kValue,j)+" que clima hay?");
                        System.out.println("0) Clima Normal\n1) Con lluvia\n2) Con Nieve\n3) Con Tormenta");
                        String opc = sc.nextLine();
                        int clima = 0;
                        if(opc.equals("0") || opc.equals("1") || opc.equals("2") || opc.equals("3")){
                            clima = Integer.parseInt(opc);
                        }
                        NstartCity.add(Integer.parseInt(tiposClima.get(clima)));
                    }else{
                        //If it's not a list, it means there's no path
                        NstartCity.add("NE");
                    }
                }
                cont++;
            }
            //Add vector to create the new matrix
            finalVector.add(NstartCity);
        }
        for(int l = 0; l<initialVector.size();l++){
            System.out.println(SupportFunctions.getKeyfromValue(kValue,l)+": "+finalVector.get(l));
        }
        return finalVector;
    }

    /**
     * Using the Floyd algorithm, two new matrices are created.
     * One stores the distance of the shortest routes,
     * and the other stores the names of the cities that must be traversed to reach the destination
     * by the shortest distance.
     *
     * @param finalVector
     * @param kValue
     * @return
     */
    public Vector<Vector<Object>> floydAlgorithm(Vector<Vector<Object>> finalVector, HashMap<String,Integer> kValue){
        //----------------------------------------------------------------
        Vector<Vector<Object>> shortRoute =  new Vector<Vector<Object>>();
        Vector<Vector<Object>> cities =  new Vector<Vector<Object>>(); //Create the matrix for the routes between the cities.
        //----------------------------------------------------------------
        int cont = 0;
        for(int i = 0;i<finalVector.size();i++){
            //----------------------------------------------------------------
            //Create a copy to not affect the original matrix
            Vector<Object> ci = new Vector<>();
            for(int j=0;j<kValue.size();j++){
                if(cont==j){
                    ci.add(0);
                }else{
                    ci.add(SupportFunctions.getKeyfromValue(kValue,j));
                }
            }
            cont++;
            cities.add(ci);
            //----------------------------------------------------------------
        }

        //----------------------------------------------------------------
        for (int i = 0;i<finalVector.size();i++){
            Vector<Object> destino = finalVector.get(i);
            Vector<Object> copia = new Vector<>();
            for(int j=0;j<destino.size();j++){
                copia.add(destino.get(j));
            }
            shortRoute.add(copia);
        }
        //----------------------------------------------------------------

        System.out.println(shortRoute);

        System.out.println(cities);

        //----------------------------------------------------------------
        //Floyd
        for(int k=0;k<kValue.size();k++){
            for(int i=0;i<kValue.size();i++){
                if(k != i){
                    for(int j=0;j<kValue.size();j++){
                        if(k != j){
                            if(!(shortRoute.get(i).get(k).equals("NE"))){
                                int n1 = (int) shortRoute.get(i).get(k);
                                if(!(shortRoute.get(k).get(j).equals("NE"))){
                                    int n2 = (int) shortRoute.get(k).get(j);
                                    int sum = n1+n2;
                                    if((shortRoute.get(i).get(j).equals("NE"))){
                                        shortRoute.get(i).set(j,sum);
                                        cities.get(i).set(j,SupportFunctions.getKeyfromValue(kValue,k));
                                    } else if (sum < (int)(shortRoute.get(i).get(j))) {
                                        shortRoute.get(i).set(j,sum);
                                        cities.get(i).set(j,SupportFunctions.getKeyfromValue(kValue,k));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //----------------------------------------------------------------
            System.out.println("K = "+k);
            System.out.println(shortRoute);
            System.out.println(cities);
            System.out.println("------\n");
        }
        System.out.println(shortRoute);
        System.out.println(cities);
        //----------------------------------------------------------------
        arrivalRoutes = cities;
        floydVector = shortRoute;
        //----------------------------------------------------------------
        return floydVector;
    }

    public Vector<Vector<Object>> getFloydVector(){
        return this.floydVector;
    }

    public Vector<Vector<Object>> getArrivalRoutes(){
        return this.arrivalRoutes;
    }

}

