import javafx.scene.image.Image;
import java.util.Random;

public class Comida extends Objeto
{
    private static final String RUTA_SPRITE = "Recursos/GIF/items/cherry.gif";
    private static final int LADO_DEL_SPRITE = 21;
    
    public Comida(float anchoDeLaEscena, float altoDeLaEscena, int distanciaAlSuelo)
    {
        super(anchoDeLaEscena, altoDeLaEscena, distanciaAlSuelo);
    }

    @Override
    public void posicionar(){
        Random rnd = new Random();
        setY(-LADO_DEL_SPRITE);
        setX(rnd.nextInt((int)anchoDeLaEscena)-LADO_DEL_SPRITE);
    }
    
    @Override
    public int getPuntos(){
        return 0;
    }
    
    @Override
    public void inicializarImagenes(){
        spriteIdle = new Image(RUTA_SPRITE);
    }
}