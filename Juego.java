import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.scene.ImageCursor;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.scene.text.TextAlignment;

public class Juego extends Application
{
    private static final String TITULO_DE_LA_VENTANA = "Juego Zorrito";
    private static final String RUTA_IMAGEN_BACKGROUND = "Recursos/background.png";
    private static final String RUTA_ICONO_VENTANA = "Recursos/ico.png";
    private static final String RUTA_IMAGEN_CURSOR = "Recursos/cursor.png";
    private static final String RUTA_MUSICA_AMBIENTE = "Recursos/Audio/Tambul.mp3";
    private static final String RUTA_SONIDO_DISPARO = "Recursos/Audio/shot.wav";
    private static final float ANCHO_DE_LA_ESCENA = 800;
    private static final float ALTO_DE_LA_ESCENA = 600;
    private static final int DISTANCIA_AL_SUELO = 390;
    private static final int CANTIDAD__DE_ENEMIGOS = 40;
    private static final int MILISEGUNDOS_DEL_KEYFRAME = 10;
    private static final int MILISEGUNDOS_POR_SEGUNDO = 1000;
    private static final ImageView BACKGROUND = new ImageView();
    private static final float DIAMETRO_DISPARO = 0.5f;
    private static final int CORRECCION_DISPARO = 16;
    private static final int TIEMPO_GENERACION_COMIDA = 2;
    private static final int TIEMPO_GENERADCION_ENEMIGOS = 1;
    private static final int POSICION_X_PUNTUACION = 150;
    private static final int POSICION_Y_PUNTUACION = 500;
    private static final String TEXTO_PUNTUACION = "Puntuacion: ";
    private static final int PUNTOS_PARA_GANAR = 20000;
    private static final int PUNTOS_PARA_PERDER = -1000;
    private static final String MENSAJE_VICTORIA = "¡Has ganado!\nPulsa Esc para salir";
    private static final String MENSAJE_GAME_OVER = "Game Over\nPulsa Esc para salir";
    private static final float ANCHO_MENSAJE_FIN_PARTIDA = 100;
    private Image iconoVentana;
    private ArrayList<Enemigo> enemigos;
    private Circle disparo;
    private ArrayList<Objeto> objetos;
    private int contadorMilisegundos, puntuacion;
    private long contadorSegundos;
    private Media musicaAmbiente;
    private Media sonidoDisparo;
    private MediaPlayer reproductorMusicaAmbiente;
    private Zarigueya zarigueya;
    
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

        // Iniciamos al jugador, las colecciones y la puntuacion:
        Jugador jugador = new Jugador(ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA, DISTANCIA_AL_SUELO);
        panel.getChildren().add(jugador);
        disparo = new Circle (DIAMETRO_DISPARO);
        objetos = new ArrayList<Objeto>();
        puntuacion = 0;
        Label labelPuntuacion = new Label(TEXTO_PUNTUACION + String.valueOf(puntuacion));
        labelPuntuacion.setLayoutX(POSICION_X_PUNTUACION);
        labelPuntuacion.setLayoutY(POSICION_Y_PUNTUACION);
        panel.getChildren().add(labelPuntuacion);
        enemigos = new ArrayList<Enemigo>();
        zarigueya = new Zarigueya(ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA, DISTANCIA_AL_SUELO);
        panel.getChildren().add(zarigueya);

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
                disparo.setCenterX(event.getX()+CORRECCION_DISPARO);
                disparo.setCenterY(event.getY()+CORRECCION_DISPARO);
                new MediaPlayer(sonidoDisparo).play();
            });

        // Creacion del timeline y el keyFrame
        contadorMilisegundos = 0;

        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(MILISEGUNDOS_DEL_KEYFRAME), (event) -> {
                    // Iteramos sobre los enemigos.
                    Iterator<Enemigo> iteradorEnemigos = enemigos.iterator();
                    while(iteradorEnemigos.hasNext()){
                        Enemigo enemigo = iteradorEnemigos.next();
                        enemigo.actualizar();
                        if(enemigo.getVida() && enemigo.disparadoPor(disparo)){
                            Gema gema = new Gema(ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA, DISTANCIA_AL_SUELO);
                            gema.fijarPosicion(enemigo.getX()+enemigo.getBoundsInParent().getWidth()/2, enemigo.getY()+enemigo.getBoundsInParent().getWidth()/2);
                            objetos.add(gema);
                            panel.getChildren().add(gema);
                        }
                        if(enemigo.getImage() == null){
                            panel.getChildren().remove(enemigo);
                            iteradorEnemigos.remove();
                        }
                    }
                    zarigueya.actualizar();
                    
                    // Iteramos sobre los objetos.
                    Iterator<Objeto> iteradorObjetos = objetos.iterator();
                    while(iteradorObjetos.hasNext()){
                        Objeto objeto = iteradorObjetos.next();
                        objeto.actualizar();
                        if(objeto.recogidoPor(jugador)){
                            puntuacion += objeto.getPuntos();
                            panel.getChildren().remove(objeto);
                            iteradorObjetos.remove();
                            labelPuntuacion.setText(TEXTO_PUNTUACION + String.valueOf(puntuacion));
                        }else if(objeto.getImage() == null){
                            iteradorObjetos.remove();
                            panel.getChildren().remove(objeto);
                        }else if(zarigueya.robar(objeto)){
                            panel.getChildren().remove(objeto);
                            iteradorObjetos.remove();
                            puntuacion -= objeto.getPuntos();
                            labelPuntuacion.setText(TEXTO_PUNTUACION + String.valueOf(puntuacion));
                        }
                    }
                    
                    // Actualizamos los elementos que controla el jugador.
                    jugador.actualizar();
                    disparo.setCenterY(-ALTO_DE_LA_ESCENA);
                    
                    // Codigo para "timear" el programa por segundos mas comodamente.
                    contadorMilisegundos += MILISEGUNDOS_DEL_KEYFRAME;
                    if(contadorMilisegundos%MILISEGUNDOS_POR_SEGUNDO == 0){
                        contadorMilisegundos = 0;
                        contadorSegundos++;
                        // Aqui se genera un objeto comida en el margen de tiempo que le indiquemos.
                        if(contadorSegundos%TIEMPO_GENERACION_COMIDA == 0){
                            Comida comida = new Comida(ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA, DISTANCIA_AL_SUELO);
                            objetos.add(comida);
                            panel.getChildren().add(comida);
                        }
                        // Aqi generamos enemigos con el margen de tiempo indicado y hasta el limite de enemigos maximo.
                        if(contadorSegundos%TIEMPO_GENERADCION_ENEMIGOS == 0 && enemigos.size() <= CANTIDAD__DE_ENEMIGOS){
                            Enemigo enemigo = new Enemigo(ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA, DISTANCIA_AL_SUELO);
                            enemigos.add(enemigo);
                            panel.getChildren().add(enemigo);
                        }
                    }
                    
                    if(puntuacion >= PUNTOS_PARA_GANAR){
                        Label etiquetaVictoria = new Label(MENSAJE_VICTORIA);
                        etiquetaVictoria.setTextAlignment(TextAlignment.CENTER);
                        etiquetaVictoria.setMaxWidth(ANCHO_MENSAJE_FIN_PARTIDA);
                        etiquetaVictoria.setLayoutX(ANCHO_DE_LA_ESCENA / 2 - ANCHO_MENSAJE_FIN_PARTIDA / 2);
                        etiquetaVictoria.setLayoutY(ALTO_DE_LA_ESCENA / 2);
                        panel.getChildren().add(etiquetaVictoria);
                        timeline.stop();
                    }else if(puntuacion <= PUNTOS_PARA_PERDER){
                        Label etiquetaGameOver = new Label(MENSAJE_GAME_OVER);
                        etiquetaGameOver.setTextAlignment(TextAlignment.CENTER);
                        etiquetaGameOver.setMaxWidth(ANCHO_MENSAJE_FIN_PARTIDA);
                        etiquetaGameOver.setLayoutX(ANCHO_DE_LA_ESCENA / 2 - ANCHO_MENSAJE_FIN_PARTIDA / 2);
                        etiquetaGameOver.setLayoutY(ALTO_DE_LA_ESCENA / 2);
                        panel.getChildren().add(etiquetaGameOver);
                        timeline.stop();
                    }
                });
        timeline.getKeyFrames().add(kf);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        try{
            BACKGROUND.setImage(new Image(RUTA_IMAGEN_BACKGROUND));
            iconoVentana = new Image(RUTA_ICONO_VENTANA);
            panel.setCursor(new ImageCursor(new Image(RUTA_IMAGEN_CURSOR)));
            musicaAmbiente = new Media(new File(RUTA_MUSICA_AMBIENTE).toURI().toString());
            sonidoDisparo = new Media(new File(RUTA_SONIDO_DISPARO).toURI().toString());
            
            primaryStage.getIcons().add(iconoVentana); 
        }catch(Exception e){
            e.printStackTrace();
        }
        
        // Inicamos todo lo que respecta a musica y efectos sonoros.
        reproductorMusicaAmbiente = new MediaPlayer(musicaAmbiente);
        reproductorMusicaAmbiente.setCycleCount(MediaPlayer.INDEFINITE);
        reproductorMusicaAmbiente.play();

        primaryStage.show();
    }
}