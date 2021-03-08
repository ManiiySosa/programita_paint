
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
import javafx.scene.layout.VBox;

public class ContenedorPrincipal extends BorderPane {

    private MenuBar barraMenus;
    private Menu menuArchivo, menuEdicion;
    private MenuItem opcionNuevo, opcionSalir, opcionCopiar, opcionPegar, opcionCortar;
    private ToolBar barraHerramientas;

    public ContenedorPrincipal(){
        crearBarraMenus();
        crearBarraHerramientas();

        VBox contenedorSuperior = new VBox();
        contenedorSuperior.getChildren().addAll(barraMenus, barraHerramientas);
        setTop(contenedorSuperior);
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
}
