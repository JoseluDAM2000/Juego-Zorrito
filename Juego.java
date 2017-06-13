import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Juego extends Application
{
    private static final String TITULO_DE_LA_VENTANA = "Juego Zorrito";
    private static final String RUTA_IMAGEN_BACKGROUND = "Recursos/background.png";
    private static final float ANCHO_DE_LA_ESCENA = 800;
    private static final float ALTO_DE_LA_ESCENA = 600;
    private static final int DISTANCIA_AL_SUELO = 390;
    private static final int CANTIDAD_DE_ENEMIGOS = 10;
    private static final int MILISEGUNDOS_DEL_KEYFRAME = 10;
    private static final int MILISEGUNDOS_POR_SEGUNDO = 1000;
    private static final ImageView BACKGROUND = new ImageView();
    private static final int DIAMETRO_DISPARO = 5;
    private static final int TIEMPO_GENERACION_COMIDA = 5; 
    private ArrayList<Enemigo> enemigos;
    private Circle disparo;
    private ArrayList<Objeto> objetos;
    private int contadorMilisegundos;
    private long contadorSegundos;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        // Creacion y configuraciono de la escena y el escenario:
        Pane panel = new Pane();
        Scene escena = new Scene(panel, ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA);
        primaryStage.setScene(escena);
        primaryStage.setTitle(TITULO_DE_LA_VENTANA);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        panel.getChildren().add(BACKGROUND);
        
        // Iniciamos al jugador y la coleccion de objetos:
        Jugador jugador = new Jugador(ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA, DISTANCIA_AL_SUELO);
        panel.getChildren().add(jugador);
        disparo = new Circle (DIAMETRO_DISPARO);
        objetos = new ArrayList<Objeto>();
        
        // Inicializamos a los enemigos:
        enemigos = new ArrayList<Enemigo>();
        for(int i = 0; i<= CANTIDAD_DE_ENEMIGOS; i++){
            Enemigo enemigo = new Enemigo(ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA, DISTANCIA_AL_SUELO);
            enemigos.add(enemigo);
        }
        panel.getChildren().addAll(enemigos);
        
        // Controles de teclado:
        escena.setOnKeyPressed(event ->{
                if(event.getCode() == KeyCode.RIGHT){
                    jugador.cambiarDireccion(Direccion.DERECHA);
                }
                if(event.getCode() == KeyCode.LEFT){
                    jugador.cambiarDireccion(Direccion.IZQUIERDA);
                } 
                if(event.getCode() == KeyCode.SHIFT){
                    jugador.correr(true);
                }
                // Cierra la ventana del juego y detiene la maquina virtual de java
                if(event.getCode() == KeyCode.ESCAPE){
                    System.exit(0);
                }
            });
            
        escena.setOnKeyReleased(event ->{
                if(event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT){
                    jugador.detenerMovimiento();
                }
                if(event.getCode() == KeyCode.SHIFT){
                    jugador.correr(false);
                }
            });        
        
        // Control de raton:
        escena.setOnMouseClicked((MouseEvent event) -> {
                disparo.setCenterX(event.getX());
                disparo.setCenterY(event.getY());
            });
        
        // Creacion del timeline y el keyFrame
        contadorMilisegundos = 0;
        
        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(MILISEGUNDOS_DEL_KEYFRAME), (event) -> {
                    for(Enemigo enemigo : enemigos){
                        enemigo.actualizar();
                        if(enemigo.getVida() && enemigo.disparadoPor(disparo)){
                            Gema gema = new Gema(ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA, DISTANCIA_AL_SUELO);
                            gema.fijarPosicion(enemigo.getX()+enemigo.getBoundsInParent().getWidth()/2, enemigo.getY()+enemigo.getBoundsInParent().getWidth()/2);
                            objetos.add(gema);
                            panel.getChildren().add(gema);
                            enemigo.setImage(null);
                        }
                    }
                    for(Objeto objeto : objetos){
                        if(objeto.recogidoPor(jugador)){
                            //TODO: control de puntos.
                            objeto.setImage(null);
                        }
                        objeto.actualizar();
                    }
                    jugador.actualizar();
                    disparo.setCenterY(-ALTO_DE_LA_ESCENA);
                    contadorMilisegundos = contadorMilisegundos + MILISEGUNDOS_DEL_KEYFRAME;
                    if(contadorMilisegundos%MILISEGUNDOS_POR_SEGUNDO == 0){
                        contadorMilisegundos = 0;
                        contadorSegundos++;
                        System.out.println(contadorSegundos);
                        // Aqui se genera un objeto comida en el margen de tiempo que le indiquemos.
                        if(contadorSegundos%TIEMPO_GENERACION_COMIDA == 0){
                            Comida comida = new Comida(ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA, DISTANCIA_AL_SUELO);
                            objetos.add(comida);
                            panel.getChildren().add(comida);
                        }
                    }
                });
        timeline.getKeyFrames().add(kf);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        try{
            BACKGROUND.setImage(new Image(RUTA_IMAGEN_BACKGROUND));
        }catch(Exception e){
            e.printStackTrace();
        }
        primaryStage.show();
    }
}