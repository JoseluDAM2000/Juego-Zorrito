import javafx.scene.image.Image;

public class Enemigo extends Entidad
{

    public Enemigo(float anchoDeLaEscena, float altoDeLaEscena)
    {
        super(anchoDeLaEscena, altoDeLaEscena);
    }

    @Override
    public void actualizar(){
        
    }

    @Override
    public void posicionar(){
        
    }
    
    @Override
    public void inicializarImagenes(){
        spriteIdle = new Image("Recursos/GIF/eagle/idle.gif");
    }
}