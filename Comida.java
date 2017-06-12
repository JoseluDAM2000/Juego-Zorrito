import javafx.scene.image.Image;

public class Comida extends Objeto
{
    private static final String RUTA_SPRITE = "Recursos/GIF/items/cherry.gif";
    
    public Comida(float anchoDeLaEscena, float altoDeLaEscena)
    {
        super(anchoDeLaEscena, altoDeLaEscena);
    }

    @Override
    public void posicionar(){
    }
    
    @Override
    public int getPuntos(){
        return 0;
    }
    
    @Override
    public void actualizar(){
        
    }
    
    @Override
    public void inicializarImagenes(){
        spriteIdle = new Image(RUTA_SPRITE);
    }
}