
public abstract class Objeto extends Entidad
{
    protected boolean recogido;
    private static final int MARGEN_SOBRE_EL_SUELO = 20;
    protected int puntos;
    public Objeto(float anchoDeLaEscena, float altoDeLaEscena, int distanciaAlSuelo)
    {
        super(anchoDeLaEscena, altoDeLaEscena, distanciaAlSuelo);
        recogido = false;
        puntos = 0;
    }
    
    public int getPuntos(){
        return puntos;
    }
    
    @Override
    public void actualizar(){
        if(getY() < distanciaAlSuelo-MARGEN_SOBRE_EL_SUELO){
            setY(getY()+1);
        }
    }
    
    public boolean recogidoPor(Jugador jugador){
        if(this.intersects(jugador.getBoundsInParent())){
            recogido = true;
            setImage(null);
        }
        return recogido;
    }
}