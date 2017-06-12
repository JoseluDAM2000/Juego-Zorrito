import javafx.scene.image.Image;

public class Gema extends Objeto
{
    public Gema(float anchoDeLaEscena, float altoDeLaEscena)
    {
        super(anchoDeLaEscena, altoDeLaEscena);
    }
    
    @Override
    public int getPuntos(){
        return 0;
    }
    
    @Override
    public void posicionar(){
        
    }
    
    @Override
    public void actualizar(){
        
    }
    
    @Override
    public void inicializarImagenes(){
        spriteIdle = new Image("Recursos/GIF/items/gem.gif");
    }
}