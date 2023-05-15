import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al programa");
        //---------------
        Scanner sc = new Scanner(System.in);
        Vector<Vector<Object>> initialVector =  new Vector<Vector<Object>>();
        Vector<Vector<Object>> finalVector = new Vector<>();
        HashMap<String,Integer> kValue = new HashMap<>();
        Graph graph = new Graph();
        boolean flag = true;
        String regex = "\\d+";
        //-----Read and create variables---------
        Reader reader = new Reader(initialVector,kValue);
        //--------------------------------------
        finalVector = graph.makeTheFistVector(initialVector,kValue);
        graph.floydAlgorithm(finalVector,kValue);
        //--------
        while(flag){
            //-------------------OPC---------------------
            System.out.println("Ingrese la opcion que quiere");
            System.out.println("0) Salir\n1) Distancia mas corta entre dos ciudades\n2) Ciudad centro del grafo" +
                    "\n3) Modificar un grafo\n4) Indicar un clima entre ciudades");
            String opc = sc.nextLine();
            //--------------------------------------------
            if(opc.equals("0")){
                flag = false;
            } else if (opc.equals("1")) {
                //------------start City----------------------
                System.out.println("Ingrese de la ciudad de la que sale");
                for(int i=0;i<kValue.size();i++){
                    System.out.println(i+") "+SupportFunctions.getKeyfromValue(kValue,i));
                }
                String sCity = sc.nextLine();
                //------------final City---------------------
                System.out.println("Ingrese a la ciudad a la que quiere llegar");
                for(int i=0;i<kValue.size();i++){
                    System.out.println(i+") "+SupportFunctions.getKeyfromValue(kValue,i));
                }
                String fCity = sc.nextLine();
                //------------------------------------------
                if(sCity.matches(regex)){
                    if(fCity.matches(regex)){
                        int sCityN = Integer.parseInt(sCity);
                        int fCityN = Integer.parseInt(fCity);
                        //------------------------------------------------
                        if((sCityN >= 0 && sCityN<kValue.size()) && (fCityN >= 0 && fCityN<kValue.size())){
                            if(graph.getFloydVector().get(sCityN).get(fCityN).equals("NE")){
                                System.out.println("No hay manera de llegar a el destino");
                            }else{
                                System.out.println("Valor de la ruta mas corta: "+graph.getFloydVector().get(sCityN).get(fCityN));
                                System.out.println("Recorrido de las ciudades");
                                System.out.println(SupportFunctions.getKeyfromValue(kValue,sCityN));
                                System.out.println(graph.arrivalRoutes.get(sCityN).get(fCityN));
                                System.out.println(SupportFunctions.getKeyfromValue(kValue,fCityN));
                            }
                        }
                        //------------------------------------------------------
                    }
                }
                //------------------------------------------
            }else if(opc.equals("2")){
                System.out.println("La ciudad centro es: "+graph.centerCity(kValue));
            }else if(opc.equals("3")){
                System.out.println("Que desea modificar?\n");
            }else if(opc.equals("4")){

            }
        }
    }
}