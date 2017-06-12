import javafx.scene.image.Image;

public class Jugador extends Entidad
{
    private static final int LADO_DEL_SPRITE = 32;
    private static final int DISTANCIA_INFERIOR = 210;
    private Image spriteCorriendo;
    private float limiteDerecho, limiteIzquierdo;
    private int velocidadEnCarrera;
    public Jugador(float anchoDeLaEscena, float altoDeLaEscena)
    {
        super(anchoDeLaEscena, altoDeLaEscena);
        limiteDerecho = anchoDeLaEscena;
        limiteIzquierdo = 0;
        velocidad = 0;
        velocidadEnCarrera = 1;
    }
    
    @Override
    public void posicionar(){
        setY(altoDeLaEscena-LADO_DEL_SPRITE-DISTANCIA_INFERIOR);
        setX(anchoDeLaEscena/2 - LADO_DEL_SPRITE/2);
    }
    
    @Override
    public void actualizar(){
        setX(getX()+velocidad*velocidadEnCarrera);
        if (getBoundsInParent().getMinX() <= limiteIzquierdo || getBoundsInParent().getMaxX() >= limiteDerecho){
            velocidad = 0;
        }
    }
    
    @Override
    public void inicializarImagenes(){
        spriteIdle = new Image("Recursos/GIF/player/idle.gif");
        spriteCorriendo = new Image("Recursos/GIF/player/run.gif");
    }
    
    public void cambiarDireccion(Direccion direccion){
        switch(direccion){
            case DERECHA:
            if(!(getBoundsInParent().getMaxX() >= limiteDerecho)){
                velocidad = 1;
                setScaleX(1);
            }
            break;
            case IZQUIERDA:
            if(!(getBoundsInParent().getMinX() <= limiteIzquierdo)){
                velocidad = -1;
                setScaleX(-1);
            }
            break;
        }
        setImage(spriteCorriendo);
    }
    
    public void correr(boolean enCarrera){
        if(enCarrera){
            velocidadEnCarrera = 2;
        }else{
            velocidadEnCarrera = 1;
        }
    }
    
    public void detenerMovimiento(){
        velocidad = 0;
        setImage(spriteIdle);
    }
}
