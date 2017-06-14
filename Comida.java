import javafx.scene.image.Image;
import java.util.Random;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Comida extends Objeto
{
    private static final String RUTA_SPRITE = "Recursos/GIF/items/cherry.gif";
    private static final String RUTA_SONIDO_COMIDA = "Recursos/Audio/comida.wav";
    private static final int LADO_DEL_SPRITE = 21;
    private static final int VALOR_EN_PUNTOS = 50;
    
    public Comida(float anchoDeLaEscena, float altoDeLaEscena, int distanciaAlSuelo)
    {
        super(anchoDeLaEscena, altoDeLaEscena, distanciaAlSuelo);
        puntos = VALOR_EN_PUNTOS;
    }

    @Override
    public void posicionar(){
        Random rnd = new Random();
        setY(-LADO_DEL_SPRITE);
        setX(rnd.nextInt((int)anchoDeLaEscena)-LADO_DEL_SPRITE);
    }
    
    @Override
    public void inicializarMedios(){
        spriteIdle = new Image(RUTA_SPRITE);
        sonido = new Media(new File(RUTA_SONIDO_COMIDA).toURI().toString());
        reproductorSonido = new MediaPlayer(sonido);
    }
}