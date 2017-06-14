
public abstract class Objeto extends Entidad
{
    protected boolean recogido;
    private static final int MARGEN_SOBRE_EL_SUELO = 20;
    protected int puntos, actualizacionesEnElSuelo, cuentaActualizacionesCambioVisibilidad;
    private static final int ACTUALIZACIONES_PARA_PARPADEAR = 400;
    private static final int ACTUALIZACIONES_PARA_CAMBIO_DE_VISIBILIDAD = 10;
    private static final int ACTUALIZACIONES_PARA_DESAPARECER = 600;
    public Objeto(float anchoDeLaEscena, float altoDeLaEscena, int distanciaAlSuelo)
    {
        super(anchoDeLaEscena, altoDeLaEscena, distanciaAlSuelo);
        recogido = false;
        puntos = 0;
        actualizacionesEnElSuelo = 0;
        actualizacionesParaDesaparecer = ACTUALIZACIONES_PARA_DESAPARECER;
    }
    
    public int getPuntos(){
        return puntos;
    }
    
    @Override
    public void actualizar(){
        if(getY() < distanciaAlSuelo-MARGEN_SOBRE_EL_SUELO){
            setY(getY()+1);
        }else{
            if(actualizacionesEnElSuelo >= ACTUALIZACIONES_PARA_PARPADEAR){
                if(ACTUALIZACIONES_PARA_CAMBIO_DE_VISIBILIDAD <= cuentaActualizacionesCambioVisibilidad){
                    cuentaActualizacionesCambioVisibilidad = 0;
                    setVisible(!isVisible());
                    if(actualizacionesEnElSuelo >= ACTUALIZACIONES_PARA_DESAPARECER){
                        setImage(null);
                    }
                }
                cuentaActualizacionesCambioVisibilidad++;
            }
            actualizacionesEnElSuelo++;
        }
    }
    
    public boolean recogidoPor(Jugador jugador){
        if(this.intersects(jugador.getBoundsInParent())){
            recogido = true;
            setImage(null);
            reproductorSonido.play();
        }
        return recogido;
    }
}