import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import javax.swing.JFrame;

public class Mandelbrot extends JFrame {
    BufferedImage I;

    public Mandelbrot() {
        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void paint(Graphics g) {
        long start = System.nanoTime();
        int NUM_OF_THREADS = 8;
        ExecutorService pool = Executors.newFixedThreadPool(NUM_OF_THREADS);
//        ExecutorService pool = Executors.newSingleThreadExecutor();
//        ExecutorService pool = Executors.newCachedThreadPool();
//        ExecutorService pool = Executors.newWorkStealingPool();


        List<Future<BufferedImage>> list_future = new ArrayList<>();
        Callable<BufferedImage> callable;
        int[] start_values = new int[NUM_OF_THREADS];
        int j = 0;
        for (int i = 0; i < getHeight(); i += getHeight() / NUM_OF_THREADS) {
            callable = new MCallable(i, getHeight() / NUM_OF_THREADS, getWidth());
            start_values[j] = i;
            j += 1;
            list_future.add(pool.submit(callable));
        }

        j = 0;
        for (Future<BufferedImage> f : list_future) {
            try {
                BufferedImage image = f.get();
                g.drawImage(image, 0, start_values[j], this);
                j += 1;
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
        long end = System.nanoTime();

        System.out.println((end - start) / 1000000);
    }

    public static void main(String[] args) {
        new Mandelbrot().setVisible(true);
    }
}