import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.Random;

public class Zarigueya extends Entidad
{
    private static final String RUTA_SPRITE_IDLE = "Recursos/GIF/opossum/walk.gif";
    private static final String RUTA_SONIDO_ZARIGUEYA = "Recursos/Audio/opossum.wav";
    private static final int ACTUALIZACIONES_PARA_DESAPARECER = 300;
    private static final int LADO_DEL_SPRITE = 28;
    public Zarigueya(float anchoDeLaEscena, float altoDeLaEscena, int distanciaAlSuelo)
    {
        super(anchoDeLaEscena, altoDeLaEscena, distanciaAlSuelo);
        velocidad = -2;
        actualizacionesParaDesaparecer = ACTUALIZACIONES_PARA_DESAPARECER;
    }
    
    @Override
    public void posicionar(){
        Random rnd = new Random();
        setX(anchoDeLaEscena+rnd.nextInt((int)anchoDeLaEscena*2));
    }
    
    @Override
    public void actualizar(){
        setX(getX()+ velocidad);
        setY(distanciaAlSuelo - LADO_DEL_SPRITE);
        if(getX() < -LADO_DEL_SPRITE){
            posicionar();
        }
    }
    
    @Override
    public void inicializarMedios(){
        spriteIdle = new Image(RUTA_SPRITE_IDLE);
        sonido = new Media(new File(RUTA_SONIDO_ZARIGUEYA).toURI().toString());
    }
    
    public boolean robar(Objeto objeto){
        boolean valorDeRetorno = false;
        if(this.intersects(objeto.getBoundsInParent())){
            valorDeRetorno = true;
            objeto.setImage(null);
            try{
                new MediaPlayer(sonido).play();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return valorDeRetorno;
    }
}
