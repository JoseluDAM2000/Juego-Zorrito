import javafx.scene.image.Image;
import java.util.Random;
import javafx.scene.shape.Circle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Enemigo extends Entidad
{
    private static final int DISTANCIA_PARA_REINICIAR_MOVIMIENTO = 64;
    private static final int LADO_DEL_SPRITE = 40;
    private static final int ACTUALIZACIONES_PARA_DESAPARECER = 35;
    private static final String RUTA_SPRITE_IDLE = "Recursos/GIF/eagle/idle.gif";
    private static final String RUTA_SPRITE_MUERTE = "Recursos/GIF/eagle/deadth.gif";
    private static final String RUTA_SONIDO_AGUILA = "Recursos/Audio/eagle.wav";
    private Image spriteMuerte;
    private boolean disparado;
    private int actualizacionesMuerto;
    
    public Enemigo(float anchoDeLaEscena, float altoDeLaEscena, int distanciaAlSuelo)
    {
        super(anchoDeLaEscena, altoDeLaEscena, distanciaAlSuelo);
        velocidad = -1;
        disparado = false;
        actualizacionesParaDesaparecer = ACTUALIZACIONES_PARA_DESAPARECER;
    }

    @Override
    public void actualizar(){
        setX(getX()+ velocidad);
        if(getX() < -DISTANCIA_PARA_REINICIAR_MOVIMIENTO){
            posicionar();
        }
        if(disparado){
            velocidad = 0;
            actualizacionesMuerto++;
            if(actualizacionesMuerto >= ACTUALIZACIONES_PARA_DESAPARECER){
                setImage(null);
            }
        }
    }

    @Override
    public void posicionar(){
        Random rnd = new Random();
        setY(rnd.nextInt((int)altoDeLaEscena/2));
        setX(anchoDeLaEscena+rnd.nextInt((int)anchoDeLaEscena*2));
    }
    
    @Override
    public void inicializarMedios(){
        spriteIdle = new Image(RUTA_SPRITE_IDLE);
        sonido = new Media(new File(RUTA_SONIDO_AGUILA).toURI().toString());
        reproductorSonido = new MediaPlayer(sonido);
    }
    
    public boolean disparadoPor(Circle disparo){
        if(intersects(disparo.getBoundsInParent())){
            disparado = true;
            try{
                spriteMuerte = new Image(RUTA_SPRITE_MUERTE);
                reproductorSonido.play();
            }catch(Exception e){
                e.printStackTrace();
            }
            setImage(spriteMuerte);
      }
        return disparado;
    }
    
    public boolean getVida(){
        return !disparado;
    }
}