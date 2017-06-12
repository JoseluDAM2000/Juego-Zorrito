
public abstract class Objeto extends Entidad
{
    public Objeto(float anchoDeLaEscena, float altoDeLaEscena)
    {
        super(anchoDeLaEscena, altoDeLaEscena);
    }
    
    public abstract int getPuntos();
}