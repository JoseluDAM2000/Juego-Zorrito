import javafx.scene.image.Image;

public class Gema extends Objeto
{
    private double posicionInicialX, posicionInicialY;
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
        setX(posicionInicialX);
        setY(posicionInicialY);
    }
    
    @Override
    public void actualizar(){
        setY(getY()+1);
    }
    
    @Override
    public void inicializarImagenes(){
        spriteIdle = new Image("Recursos/GIF/items/gem.gif");
    }
    
    public void fijarPosicion(double posicionX, double posicionY){
        posicionInicialX = posicionX;
        posicionInicialY = posicionY;
        posicionar();
    }
}