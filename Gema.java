import javafx.scene.image.Image;

public class Gema extends Objeto
{
    private double posicionInicialX, posicionInicialY;
    private static final String RUTA_SPRITE = "Recursos/GIF/items/gem.gif";
    private static final int VALOR_EN_PUNTOS = 500;
    public Gema(float anchoDeLaEscena, float altoDeLaEscena, int distanciaAlSuelo)
    {
        super(anchoDeLaEscena, altoDeLaEscena, distanciaAlSuelo);
        puntos = VALOR_EN_PUNTOS;
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