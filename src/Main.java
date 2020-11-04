public class Main {
    public static void main(String[] args) {
        Almacen almacen = new Almacen();
        Thread empleados[] = new Thread[4];
        for (int i = 1; i <= 4; i++) {
            if (i < 4) empleados[i-1] = new Thread(new Inspector(almacen, i), "Inspector " + i);
            else empleados[i-1] = new Thread(new Reponedor(almacen), "Reponedor");
        }
        for (Thread empleado : empleados) {
            empleado.start();
        }


    }
}