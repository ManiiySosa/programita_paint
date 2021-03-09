
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public class ContenedorPrincipal extends BorderPane {

    private MenuBar barraMenus;
    private Menu menuArchivo, menuEdicion;
    private MenuItem opcionNuevo, opcionSalir, opcionCopiar, opcionPegar, opcionCortar;
    private ToolBar barraHerramientas;
    private Pane areaDibujo;

    public ContenedorPrincipal(){
        crearBarraMenus();
        crearBarraHerramientas();

        VBox contenedorSuperior = new VBox();
        areaDibujo = new Pane();
        crearFormas(areaDibujo);
        contenedorSuperior.getChildren().addAll(barraMenus, barraHerramientas);
        setTop(contenedorSuperior);
        setCenter(areaDibujo);
    }

    public void crearBarraMenus(){
        barraMenus = new MenuBar();
        menuArchivo = new Menu("Archivo");
        menuEdicion = new Menu("Edicion");
        opcionNuevo = new MenuItem("Nuevo");
        opcionSalir = new MenuItem("Salir", new ImageView(new Image("icon_exit.png", 16, 16, false, false)));
        opcionSalir.setAccelerator(KeyCombination.keyCombination("CTRL+E"));
        opcionSalir.setOnAction(evt -> {
            salirApp();
        });
        menuArchivo.getItems().addAll(opcionNuevo, new SeparatorMenuItem(), opcionSalir);
        opcionCopiar = new MenuItem("Copiar");
        opcionCortar = new MenuItem("Cortar");
        opcionPegar = new MenuItem("Pegar");
        menuEdicion.getItems().addAll(opcionCopiar, new SeparatorMenuItem(), opcionCortar, new SeparatorMenuItem(), opcionPegar);
        barraMenus.getMenus().addAll(menuArchivo, menuEdicion);
    }

    
    public void crearBarraHerramientas(){
        barraHerramientas = new ToolBar();
        Button seleccionar = new Button("", new ImageView(new Image("select.png", 24, 24, false, false)));
        Button linea = new Button("", new ImageView(new Image("online.png", 24, 24, false, false)));
        Button cuadrado = new Button("", new ImageView(new Image("cuadrado.png", 24, 24, false, false)));
        barraHerramientas.getItems().addAll(seleccionar, linea, cuadrado);
    }

    void salirApp(){
        Alert alertaCerrarApp = new Alert(AlertType.CONFIRMATION);
        alertaCerrarApp.setTitle("Advertencia");
        alertaCerrarApp.setHeaderText("cerrar aplicacion");
        alertaCerrarApp.setContentText("¿Esta seguro que desea salir de la aplicacion?");
        Optional<ButtonType> result = alertaCerrarApp.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            System.exit(0);
        }
    }

    public void crearFormas(Pane areadibujo){
        List<Line> lineas = new ArrayList<Line>();
        for(int i=0; i<200; i++){
            Line linea = new Line(300, 100+i, 300+i, 300);
            Line linea2 = new Line(300, 100+i, 300-i, 300);
            Line linea3 = new Line(300, 500-i, 300+i, 300);
            Line linea4 = new Line(300, 500-i, 300-i, 300);


            double r, g, v;
            r = Math.random()*256;
            g = Math.random()*256;
            v = Math.random()*256;  
            Paint color = Color.rgb((int)r, (int)g, (int)v);

            linea.setStroke(color);
            linea2.setStroke(color);
            linea3.setStroke(color);
            linea4.setStroke(color);
            lineas.add(linea);
            lineas.add(linea2);
            lineas.add(linea3);
            lineas.add(linea4);

        }

        areadibujo.getChildren().addAll(lineas);
                                     
    }
}
