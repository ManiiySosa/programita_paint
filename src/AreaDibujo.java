
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class AreaDibujo extends Pane{

    private BarraSuperior barraSuperior;
    private Shape forma;
    private boolean dibujando = false;
    private double xA, yA;
  

    public AreaDibujo(BarraSuperior barraSuperior){
        this.barraSuperior = barraSuperior;

        this.setOnMousePressed(evt->{
            int herramienta = barraSuperior.getHerramientaSeleccionada();
            if(herramienta == BarraSuperior.SELECCIONAR){
                dibujando = false;
                forma = null;
            }else if(herramienta == BarraSuperior.LINEA){
                double x = evt.getX();
                double y = evt.getY();
                forma = new Line(x,y,x,y);
                xA = x;
                yA = y;
            }
            
            if(forma!=null){
                forma.setStroke(barraSuperior.getColorBordeSeleccionado());
                getChildren().add(forma);
                dibujando = true;
            }
           
        });

        this.setOnMouseDragged(evt->{
            int herramienta = barraSuperior.getHerramientaSeleccionada();
            if(herramienta == BarraSuperior.SELECCIONAR){

            }else if(herramienta == BarraSuperior.LINEA && dibujando){
                double x = evt.getX();
                double y = evt.getY();
                Line linea = (Line) forma;
                linea.setEndX(x);
                linea.setEndY(y);
            }
        });

        this.setOnMouseReleased(evt-> {
            dibujando = false;
        });

    }
    
}
