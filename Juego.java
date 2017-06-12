import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class Juego extends Application
{
    private static final String TITULO_DE_LA_VENTANA = "Juego Zorrito";
    private static final float ANCHO_DE_LA_ESCENA = 800;
    private static final float ALTO_DE_LA_ESCENA = 600;
    private ArrayList<Enemigo> enemigos;
    private static final int CANTIDAD_DE_ENEMIGOS = 10;
    
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
        
        // Iniciamos al jugador:
        Jugador jugador = new Jugador(ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA);
        panel.getChildren().add(jugador);
        
        // Inicializamos a los enemigos:
        enemigos = new ArrayList<Enemigo>();
        for(int i = 0; i<= CANTIDAD_DE_ENEMIGOS; i++){
            Enemigo enemigo = new Enemigo(ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA);
            enemigos.add(enemigo);
        }
        panel.getChildren().addAll(enemigos);
        
        // Creacion del timeline y el keyFrame
        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(10), (event) -> {
                    for(Enemigo enemigo : enemigos){
                        enemigo.actualizar();
                    }
                    jugador.actualizar();
                });
        timeline.getKeyFrames().add(kf);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        primaryStage.show();
    }
}