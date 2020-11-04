import static java.lang.Thread.sleep;

public class Inspector implements Runnable{
    Almacen almacen;
    int idProduct;
    public Inspector(Almacen almacen,int idProduct) {
        this.almacen = almacen;
        this.idProduct = idProduct;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                sleep(500);
            } catch (InterruptedException e) {
                return;
            }
            almacen.seeStock(idProduct);
        }
    }
}
