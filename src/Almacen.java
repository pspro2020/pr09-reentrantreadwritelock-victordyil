import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Almacen {
    //Datos
    private ArrayList<Integer> listaproduct = new ArrayList<Integer>();

    //Tools
    private Random random = new Random();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    //Condicionales
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock writeLock = readWriteLock.writeLock();
    private Lock readLock = readWriteLock.readLock();

    public void updateStock(int idProduct) {
        writeLock.lock();
        try {
            addProduct(idProduct);
        } finally {
            writeLock.unlock();
        }

    }

    private void addProduct(int idProduct) {
        listaproduct.add(idProduct);
        System.out.printf("%s -> Se añadio el producto %d\n", LocalTime.now().format(dateTimeFormatter), idProduct);
    }

    public void seeStock(int idProduct) {
        readLock.lock();
        try {
            int stock = countStock(idProduct);
            System.out.printf("%s -> Producto %d# %d Hay %s\n",LocalTime.now().format(dateTimeFormatter),idProduct,stock,stock==0||stock>1?"Productos":"Prodcuto ");
        } finally {
            readLock.unlock();
        }

    }

    private int countStock(int idProduct) {
        int result = 0;
        for (int product : listaproduct) {
            if (product == idProduct) result++;
        }
        return result;

    }
}
