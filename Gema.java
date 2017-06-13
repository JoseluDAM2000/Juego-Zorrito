import javafx.scene.image.Image;

public class Gema extends Objeto
{
    private double posicionInicialX, posicionInicialY;
    private static final String RUTA_SPRITE = "Recursos/GIF/items/gem.gif";
    public Gema(float anchoDeLaEscena, float altoDeLaEscena, int distanciaAlSuelo)
    {
        super(anchoDeLaEscena, altoDeLaEscena, distanciaAlSuelo);
    }
    
    @Override
    public int getPuntos(){
        return 0;
    }
    
    @Override
    public void posicionar(){
        setX(posicionInicialX);
        setY(posicionInicialY);
    }
    
    @Override
    public void inicializarImagenes(){
        spriteIdle = new Image(RUTA_SPRITE);
    }
    
    public void fijarPosicion(double posicionX, double posicionY){
        posicionInicialX = posicionX;
        posicionInicialY = posicionY;
        posicionar();
    }
}