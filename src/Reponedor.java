import java.util.Random;

import static java.lang.Thread.sleep;

public class Reponedor implements Runnable {
    Almacen almacen;
    //Tools
    private Random random = new Random();

    public Reponedor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                return;
            }
            almacen.updateStock(random.nextInt(3)+1);
        }
    }
}
