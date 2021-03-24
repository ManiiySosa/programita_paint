
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import java.util.ArrayList;
import java.util.List;

public class AreaDibujo extends Pane {

    private List<Shape> formas;
    private BarraSuperior barraSuperior;
    private boolean dibujando = false;
    private boolean moviendo = false;
    private Shape forma;
    private double xA, yA;

    public AreaDibujo(BarraSuperior barraSuperior) {
        this.barraSuperior = barraSuperior;
        this.formas = new ArrayList<Shape>();
        
        this.setOnMousePressed(evt -> {
            double x = evt.getX();
            double y = evt.getY();
            if(evt.isPrimaryButtonDown()){
                if (y >= 0) {
                    int herramienta = barraSuperior.getHerramientaSeleccionada();
                    if (herramienta == BarraSuperior.SELECCIONAR) {
                        dibujando = false;
                        forma = null;
                    } else if (herramienta == BarraSuperior.LINEA) {
                        forma = new Line(x, y, x, y);
                    } else if (herramienta == BarraSuperior.CUADRADO) {
                        forma = new Rectangle(x, y, 0, 0);
                    } else if (herramienta == BarraSuperior.RECTANGULO) {
                        forma = new Rectangle(x, y, 0, 0);
                    }else if (herramienta == BarraSuperior.CIRCULO) {
                        forma = new Circle(x, y, 0);
                    } else if (herramienta == BarraSuperior.ELIPSE) {
                        forma = new Ellipse(x, y, 0, 0);
                    } else if(herramienta == BarraSuperior.TRIANGULO){
                        forma = new Polygon(x,y,x,y,x,y);
                    }
                    xA = x;
                    yA = y;
                    if (forma != null) {
                        forma.setStroke(barraSuperior.getColorBordeSeleccionado());
                        forma.setFill(barraSuperior.getColorRellenoSeleccionado());
                        forma.setStrokeWidth(barraSuperior.getGrosorBordeSeleccionado());
                        getChildren().add(forma);
                        formas.add(forma);
                        dibujando = true;
                    }
                }
            } else if(evt.isSecondaryButtonDown()){
                xA = x;
                yA = y;
            }
        });
        this.setOnMouseDragged(evt -> {
            double x = evt.getX();
            double y = evt.getY();
            double difX = x - xA;
            double difY = y - yA;
            if(evt.isPrimaryButtonDown()){
                if (y >= 0) {
                    if (forma != null) {
                        int herramienta = barraSuperior.getHerramientaSeleccionada();
                        if (herramienta == BarraSuperior.SELECCIONAR) {
                        } else if (herramienta == BarraSuperior.LINEA && dibujando) {
                            Line linea = (Line) forma;
                            linea.setEndX(x);
                            linea.setEndY(y);
                        } else if (herramienta == BarraSuperior.CUADRADO && dibujando) {
                            Rectangle rect = (Rectangle) forma;
                            if (difX < 0 && difY < 0){ 
                                difX = xA-x;
                                difY = yA-y;
                                if (difX > difY){
                                    rect.setX(xA-difY);
                                    rect.setY(yA-difY);
                                    rect.setWidth(difY);
                                    rect.setHeight(difY);
                                } else {
                                    rect.setX(xA-difX);
                                    rect.setY(yA-difX);
                                    rect.setWidth(difX);
                                    rect.setHeight(difX);
                                }
                            } else {
                                rect.setX(xA);
                                rect.setY(yA);
                                if (difX < difY) {
                                    rect.setWidth(difY);
                                    rect.setHeight(difY);
                                } else {
                                    rect.setWidth(difX);
                                    rect.setHeight(difX);
                                }
                            }
                        } else if (herramienta == BarraSuperior.RECTANGULO && dibujando) {
                            Rectangle rect = (Rectangle) forma;
                            if (difX < 0 ) {
                                difX = xA-x;
                                rect.setX(xA-difX);
                                rect.setWidth(difX);
                            } else {
                                rect.setX(xA);
                                rect.setWidth(difX);
                            }
                            if (difY < 0) {
                                difY = yA-y;
                                rect.setY(yA-difY);
                                rect.setHeight(difY);
                            } else {
                                rect.setY(yA);
                                rect.setHeight(difY);
                            }

                        } else if (herramienta == BarraSuperior.CIRCULO && dibujando) {
                            Circle circ = (Circle) forma;
                            if (difX < 0 && difY < 0) {
                                difX = xA-x;
                                difY = yA-y;
                                if (difX < difY) {
                                    circ.setCenterX(xA-(difY/2.0));
                                    circ.setCenterY(yA-(difY/2.0));
                                    circ.setRadius(difY/2.0);
                                } else {
                                    circ.setCenterX(xA-(difX/2.0));
                                    circ.setCenterY(yA-(difX/2.0));
                                    circ.setRadius(difX/2.0);
                                }
                            } else {
                                if (difX < difY) {
                                    circ.setCenterX(xA+(difY/2.0));
                                    circ.setCenterY(yA+(difY/2.0));
                                    circ.setRadius(difY/2.0);
                                } else {
                                    circ.setCenterX(xA+(difX/2.0));
                                    circ.setCenterY(yA+(difX/2.0));
                                    circ.setRadius(difX/2.0);
                                }
                            }
                        } else if (herramienta == BarraSuperior.ELIPSE && dibujando) {
                            Ellipse elipse = (Ellipse) forma;
                            elipse.setCenterX(xA+(difX/2.0));
                            elipse.setCenterY(yA+(difY/2.0));
                            elipse.setRadiusX(Math.abs(difX)/2.0);
                            elipse.setRadiusY(Math.abs(difY)/2.0);
                        } else if(herramienta == BarraSuperior.TRIANGULO && dibujando){
                            Polygon trian = (Polygon) forma;
                            trian.getPoints().clear();
                            trian.getPoints().addAll(xA, y, xA+(difX/2), yA, x, y);
                        }
                    }
                }    
            }else if(evt.isSecondaryButtonDown() && forma != null){
                forma.setTranslateX(difX);
                forma.setTranslateY(difY);
                moviendo = true;
            }
        });
        this.setOnMouseReleased(evt -> {
            double x = evt.getX();
            double y = evt.getY();
            double difX = x - xA;
            double difY = y - yA;

            if(evt.isSecondaryButtonDown() && moviendo && forma != null){
                int herramienta = barraSuperior.getHerramientaSeleccionada();
                if(herramienta == BarraSuperior.TRIANGULO){
                    Polygon trian = (Polygon) forma;
                    double x1 = trian.getPoints().get(0)+difX;
                    double y1 = trian.getPoints().get(1)+difY;
                    double x2 = trian.getPoints().get(2)+difX;
                    double y2 = trian.getPoints().get(3)+difY;
                    double x3 = trian.getPoints().get(4)+difX;
                    double y3 = trian.getPoints().get(5)+difX;
                    trian.getPoints().clear();
                    trian.getPoints().addAll(x1,y1,x2,y2,x3,y3);
                    trian.setTranslateX(0);
                    trian.setTranslateY(0);

                }
            }

            dibujando = false;
            moviendo = false;
        });
    }
}