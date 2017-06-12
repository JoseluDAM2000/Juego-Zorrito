import javafx.scene.image.Image;

public class Jugador extends Entidad
{
    private static final int LADO_DEL_SPRITE = 32;
    
    public Jugador(float anchoDeLaEscena, float altoDeLaEscena)
    {
        super(anchoDeLaEscena, altoDeLaEscena);
        
    }
    
    @Override
    public void posicionar(){
        setY(altoDeLaEscena-LADO_DEL_SPRITE);
        setX(anchoDeLaEscena/2 - LADO_DEL_SPRITE/2);
    }
    
    @Override
    public void actualizar(){
        
    }
    
    @Override
    public void inicializarImagenes(){
        spriteIdle = new Image("Recursos/GIF/player/idle.gif");
    }
    
    public void cambiarDireccion(Direccion direccion){
        
    }
    
    public void correr(boolean enCarrera){
        
    }
    
    public void detenerMovimiento(){
        
    }
}
