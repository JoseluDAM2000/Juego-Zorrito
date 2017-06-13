
public abstract class Objeto extends Entidad
{
    protected boolean recogido;
    private int margenSobreSuelo = 20;
    public Objeto(float anchoDeLaEscena, float altoDeLaEscena, int distanciaAlSuelo)
    {
        super(anchoDeLaEscena, altoDeLaEscena, distanciaAlSuelo);
        recogido = false;
    }
    
    public abstract int getPuntos();
    
    @Override
    public void actualizar(){
        if(getY() < distanciaAlSuelo-margenSobreSuelo){
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