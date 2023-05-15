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
        HashMap<String,Integer> kValue = new HashMap<>();
        Reader reader = new Reader(initialVector,kValue);
        boolean flag = true;
        //----------------
        while(flag){
            System.out.println("Ingrese la opcion que quiere");
            System.out.println("0) Salir\n1) Distancia mas corta entre dos ciudades\n2) Ciudad centro del grafo" +
                    "\n3) Modificar un grafo\n4) Indicar un clima entre ciudades");
            String opc = sc.nextLine();
            if(opc.equals("0")){
                flag = false;
            } else if (opc.equals("1")) {

            }else if(opc.equals("2")){

            }else if(opc.equals("3")){

            }else if(opc.equals("4")){

            }
        }
    }
}