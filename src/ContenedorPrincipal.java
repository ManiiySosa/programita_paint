

import javafx.scene.layout.BorderPane;

public class ContenedorPrincipal extends BorderPane {

    private AreaDibujo areaDibujo;
    private BarraSuperior barraSuperior;

    public ContenedorPrincipal(){
        
        barraSuperior = new BarraSuperior();
        areaDibujo = new AreaDibujo(barraSuperior);
       
        setTop(barraSuperior);
        setCenter(areaDibujo);
    }

   
    
}


