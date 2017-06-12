import javafx.scene.image.Image;
import java.util.Random;
import javafx.scene.shape.Circle;

public class Enemigo extends Entidad
{
    private static final int DISTANCIA_PARA_REINICIAR_MOVIMIENTO = 64;
    private static final int LADO_DEL_SPRITE = 40;
    private static final String RUTA_SPRITE_IDLE = "Recursos/GIF/eagle/idle.gif";
    private boolean disparado;
    
    public Enemigo(float anchoDeLaEscena, float altoDeLaEscena)
    {
        super(anchoDeLaEscena, altoDeLaEscena);
        velocidad = -1;
        disparado = false;
    }

    @Override
    public void actualizar(){
        setX(getX()+ velocidad);
        if(getX() < -DISTANCIA_PARA_REINICIAR_MOVIMIENTO){
            posicionar();
        }
        if(disparado){
            velocidad = 0;
        }
    }

    @Override
    public void posicionar(){
        Random rnd = new Random();
        setY(rnd.nextInt((int)altoDeLaEscena/2));
        setX(anchoDeLaEscena+rnd.nextInt((int)anchoDeLaEscena*2));
    }
    
    @Override
    public void inicializarImagenes(){
        spriteIdle = new Image(RUTA_SPRITE_IDLE);
    }
    
    public boolean disparadoPor(Circle disparo){
        if(intersects(disparo.getBoundsInParent())){
            disparado = true;
        }
        return disparado;
    }
    
    public boolean getVida(){
        return !disparado;
    }
}