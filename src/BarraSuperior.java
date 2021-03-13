
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class BarraSuperior extends VBox{

    public static final int SELECCIONAR = 0;
    public static final int LINEA = 1;
    public static final int CUADRADO= 2;
    public static final int RECTANGULO = 3;
    public static final int CIRCULO = 4;
    public static final int ELIPSE = 5;

    private int herramientaSeleccionada = SELECCIONAR;

    private MenuBar barraMenus;
    private Menu menuArchivo, menuEdicion;
    private MenuItem opcionNuevo, opcionSalir, opcionCopiar, opcionPegar, opcionCortar;
    private ToolBar barraHerramientas;
    private Paint colorBordeSeleccionado = Color.BLACK;
    private Paint colorRellenoSeleccionado = Color.WHITE;
    private ColorPicker colorBorde, colorRelleno;
    private int grosorBoderSeleccionado = 1;

    public BarraSuperior(){
        crearBarraMenus();
        crearBarraHerramientas();
        getChildren().addAll(barraMenus, barraHerramientas);
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
        seleccionar.setTooltip(new Tooltip("seleccionar"));
        seleccionar.setOnAction(evt-> {
            herramientaSeleccionada = SELECCIONAR;
        });
        Button linea = new Button("", new ImageView(new Image("online.png", 24, 24, false, false)));
        linea.setTooltip(new Tooltip("linea"));
        linea.setOnAction(evt-> {
            herramientaSeleccionada = LINEA;
        });
        Button cuadrado = new Button("", new ImageView(new Image("cuadrado.png", 24, 24, false, false)));
        cuadrado.setTooltip(new Tooltip("cuadrado"));
        cuadrado.setOnAction(evt-> {
            herramientaSeleccionada = CUADRADO;
        });
        Button rectangulo = new Button("", new ImageView(new Image("rectangulo.png", 24, 24, false, false)));
        rectangulo.setTooltip(new Tooltip("rectangulo"));
        rectangulo.setOnAction(evt-> {
            herramientaSeleccionada = RECTANGULO;
        });
        Button circulo = new Button("", new ImageView(new Image("circulo.png", 24, 24, false, false)));
        circulo.setTooltip(new Tooltip("circulo"));
        circulo.setOnAction(evt-> {
            herramientaSeleccionada = CIRCULO;
        });
        Button elipse = new Button("", new ImageView(new Image("elipse.png", 24, 24, false, false)));
        elipse.setTooltip(new Tooltip("elipse"));
        elipse.setOnAction(evt-> {
            herramientaSeleccionada = ELIPSE;
        });


        barraHerramientas.getItems().addAll(seleccionar, linea, cuadrado, rectangulo, circulo, elipse);



        Label etiColorBorde = new Label("Borde:");
        colorBorde = new ColorPicker();
        colorBorde.setOnAction(evt -> {
            colorBordeSeleccionado = colorBorde.getValue();
        });   
        colorBorde.setValue((Color)colorBordeSeleccionado);

        Label etiColorRelleno = new Label("Relleno:");
        colorRelleno = new ColorPicker();
        colorRelleno.setOnAction(evt-> {
            colorRellenoSeleccionado = colorRelleno.getValue();
        });
        colorRelleno.setValue((Color)colorRellenoSeleccionado);

        Label etiGrosor = new Label("Grosor:");
        ComboBox<Integer> cbGrosor = new ComboBox<Integer>();
        for(int i=0; i<=20; i++){
            cbGrosor.getItems().add(i);
        }
        cbGrosor.setValue(1);
        cbGrosor.setOnAction(evt-> {
            grosorBoderSeleccionado = cbGrosor.getValue();
        });

        barraHerramientas.getItems().addAll(etiColorBorde, colorBorde, etiColorRelleno, colorRelleno, etiGrosor, cbGrosor);

    }

    public int getHerramientaSeleccionada(){
        return herramientaSeleccionada;
    }

    public Paint getColorBordeSeleccionado(){
        return colorBordeSeleccionado;
    }

    public Paint getColorRellenoSeleccionado(){
        return colorRellenoSeleccionado;
    }

    public int getGrosorBordeSeleccionado(){
        return grosorBoderSeleccionado;
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
