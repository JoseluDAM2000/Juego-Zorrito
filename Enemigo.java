import javafx.scene.image.Image;
import java.util.Random;

public class Enemigo extends Entidad
{
    private static final int DISTANCIA_PARA_REINICIAR_MOVIMIENTO = 64;
    
    public Enemigo(float anchoDeLaEscena, float altoDeLaEscena)
    {
        super(anchoDeLaEscena, altoDeLaEscena);
        velocidad = -1;
    }

    @Override
    public void actualizar(){
        setX(getX()+ velocidad);
        if(getX() < -DISTANCIA_PARA_REINICIAR_MOVIMIENTO){
            posicionar();
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
        spriteIdle = new Image("Recursos/GIF/eagle/idle.gif");
    }
}