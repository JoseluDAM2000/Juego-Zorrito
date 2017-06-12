import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public abstract class Entidad extends ImageView
{
    protected Image spriteIdle;
    protected float anchoDeLaEscena;
    protected float altoDeLaEscena;
    public Entidad(float anchoDeLaEscena, float altoDeLaEscena)
    {
        super();
        this.anchoDeLaEscena = anchoDeLaEscena;
        this.altoDeLaEscena = altoDeLaEscena;
        inicializarImagenes();
        posicionar();
    }
    
    public abstract void posicionar();
    public abstract void actualizar();
    public abstract void inicializarImagenes();
}