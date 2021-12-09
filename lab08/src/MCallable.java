import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

public class MCallable implements Callable<BufferedImage> {
    int endHeight;
    int width;
    int startHeight;
    final int MAX_ITER = 5000;

    public MCallable(int startHeight, int height, int Width) {
        this.startHeight = startHeight;
        this.endHeight = startHeight + height;
        this.width = Width;
    }

    @Override
    public BufferedImage call() {
        BufferedImage image = new BufferedImage(this.width, this.endHeight - startHeight, BufferedImage.TYPE_INT_RGB);
        for (int y = startHeight; y < endHeight; y++) {
            for (int x = 0; x < width; x++) {
                double zy;
                double zx = zy = 0;
                double ZOOM = 150;
                double cX = (x - 400) / ZOOM;
                double cY = (y - 300) / ZOOM;
                int iter = this.MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    double tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                image.setRGB(x, y - startHeight, iter | (iter << 8));
            }
        }
        return image;
    }
}
