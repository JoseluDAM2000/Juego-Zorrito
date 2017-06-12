import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public abstract class Entidad extends ImageView
{
    protected Image spriteIdle;
    protected float anchoDeLaEscena;
    protected float altoDeLaEscena;
    protected int velocidad;
    public Entidad(float anchoDeLaEscena, float altoDeLaEscena)
    {
        super();
        this.anchoDeLaEscena = anchoDeLaEscena;
        this.altoDeLaEscena = altoDeLaEscena;
        try{
            inicializarImagenes();
        }catch(Exception e){
            System.out.println("No se encontro alguna de las imagenes del juego");
        }
        setImage(spriteIdle);
        posicionar();
    }
    
    public abstract void posicionar();
    public abstract void actualizar();
    public abstract void inicializarImagenes();
}