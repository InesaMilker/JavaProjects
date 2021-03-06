/****************************************************************
 * Šioje klasėje pateikamas įvadas į JavaFX grafiką
 * 
 * Pradžioje vykdykite kodą ir stebėkite atliekamus veiksmus
 * Užduotis atlikite sekdami nurodymus programinio kodo komentaruose
 * Gynimo metu atlikite dėstytojo nurodytas užduotis naujų metodų pagalba.
 *
 * @author Eimutis Karčiauskas, KTU programų inžinerijos katedra 2019 08 05
 **************************************************************************/
package demos.graphics;

import extendsFX.BaseGraphics;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Demo0_Basic extends BaseGraphics {
        
    // pradžioje brėšime horizontalias ir vertikalias linijas per centrą
    private void drawHVtoCenter() {  
        gc.setLineWidth(3);       // brėžimo linijos plotis
        gc.setStroke(Color.RED);  // ir tos linijos spalva
        gc.strokeLine(0, canvasH / 2, canvasW, canvasH / 2);
        gc.strokeLine(canvasW / 2, 0, canvasW / 2, canvasH);
    }
    // po to brėšime įstrižaines per centrą
    private void drawXtoCenter() {
        gc.setLineWidth(4);         // brėžimo linijos plotis
        gc.setStroke(Color.GREEN);  // ir tos linijos spalva
        gc.strokeLine(0, 0, canvasW, canvasH);
        gc.strokeLine(0, canvasH, canvasW, 0);
    }  
// UŽDUOTIS_1: plonomis linijomis su žingsniu step=50 nubrėžkite tinklelį -- Done
    private void drawGrid1() { 
        int step = 50;
        gc.setLineWidth(0.5);
        
        
        for (double x = 0; x < canvasW; x += step) {
            
            gc.strokeLine(x, 0, x, canvasH);
        }
        for (double y = 0; y < canvasH; y += step) {
            gc.strokeLine(0, y, canvasW, y);
        }      
    }
// https://examples.javacodegeeks.com/desktop-java/javafx/javafx-canvas-example/    
    private void drawExamples1() {
        double lw = 3.0;
        gc.setLineWidth(lw);        // brėžimo linijos plotis
        gc.setStroke(Color.BLUE);   // ir tos linijos spalva
        gc.setFill(Color.RED);      // dažymo spalva figūroms
        int x=10, y=10, w=80, h=50, 
            d=20, ax=10, ay=20; // d-tarpas tarp elementų, ax,ay-apvalinimai
        gc.strokeRoundRect(x, y, w, h, ax, ay);
        x+=w+d; // sekantis į dešinę
        gc.fillRoundRect(  x, y, w, h, ax, ay);
        gc.setLineWidth(0.5);
        gc.strokeText("Wolf and Bear", x, y+h);
        //-------------------
        gc.setLineWidth(2*lw);    // dvigubai pastoriname liniją      
        gc.setFill(Color.YELLOW);
        x = 10;    // grįžtame horizontaliai
        y += h+d;  // ir pereiname žemyn
        gc.strokeOval(x, y, w, h);
        x += w+d; // sekantis į dešinę
        gc.fillOval( x, y, h, w);
        x = 10;     // grįžtame horizontaliai
        y += h+2*d; // ir pereiname žemyn ir brėžiame lankus
        gc.strokeArc  (x, y, w, w, 30,  90, ArcType.ROUND);
        gc.fillArc(x+w+d, y, w, w, 45, 180, ArcType.OPEN);
    }  
    private void drawUnicode(){
        // išbandykite ir kitus simbolius
        // https://en.wikipedia.org/wiki/List_of_Unicode  skyrius 31
        StringBuilder sb = new StringBuilder();
        for(char ch = '\u2654'; ch <= '\u265F'; ch++)
            sb.append(ch);
        gc.setFont(Font.font("Lucida Console", 36));
        gc.setLineWidth(1);
        gc.setStroke(Color.BLACK);
        gc.strokeText(sb.toString(), 50, 350);
    }
// UŽDUOTIS_2: nubrėžkite polilinijas ir poligonus -- Done 
// https://www.tutorialspoint.com/javafx/2dshapes_polygon    
    private void drawExamples2() {      
        gc.setLineWidth(1);
        
        double[] pLineX = new double[]{
            50., 100., 150., 100., 50.
        };
        double[] pLineY = new double[]{
            100., 150., 100., 50., 100.
        };        
        gc.strokePolyline(pLineX, pLineY, 5);
        
        double[] polLineX = new double[]{
            100., 150., 200., 120., 100.
        };
        double[] polLineY = new double[]{
            100., 150., 136., 50., 100.
        };  
        
        gc.strokePolygon(polLineX, polLineY, 5);
        
    }
// UŽDUOTIS_3: nubrėžkite taisyklingus 3, 4, 5, ..., 9-kampius  -- Done
    private void drawExamples3() {   
        
        gc.setLineWidth(2);
        
        double middleX = canvasW / 2;
        double middleY = canvasH / 2;
        final int points = 4;     
        double radius = 100;
        
        double[] polX = new double[points];
        double[] polY = new double[points];
        
        createPolygonCoordinates(polX, polY, points, radius, middleX, middleY);  
        gc.strokePolygon(polX, polY, points);
        
        
        // Nurodymas: parašykite funkciją, kuri paskaičiuoja skaičių masyvus
        // kuriuose surašomos taisyklingo daugiakampio koordinatės
    }
    
    private void createPolygonCoordinates(double [] polX, double [] polY, int points, double radius, double middleX, double middleY){   
        double lineStep = 360/points;
        for (int i = 0; i < points; i++) {
            polX[i] = middleX + radius * Math.cos(Math.toRadians(lineStep * i + 1));
            polY[i] = middleY + radius * Math.sin(Math.toRadians(lineStep * i + 1));
        }
    }
    
// UŽDUOTIS_4: nubrėžkite žiedus https://en.wikipedia.org/wiki/Olympic_symbols -- Done
    private void drawOlympicRings() { 
        
        double middleX = canvasW/2;
        double middleY = canvasH/2;
        double width = 100;
        double spacing = 60;
        
        gc.setLineWidth(10);
        
        gc.setStroke(Color.BLUE); // Blue circle
        gc.strokeOval(middleX - spacing*2, middleY, width, width);
        
        gc.setStroke(Color.YELLOW); // Yellow circle
        gc.strokeOval(middleX - spacing , middleY + 50, width, width);
        
        gc.setStroke(Color.BLACK); // Black circle
        gc.strokeOval(middleX, middleY, width, width);
        
        gc.setStroke(Color.GREEN); // Green circle
        gc.strokeOval(middleX + spacing, middleY + 50, width, width);
        
        gc.setStroke(Color.RED); // Red circle
        gc.strokeOval(middleX + spacing*2, middleY, width, width);
        
        
    }
// UŽDUOTIS_5: pasirinktinai nubrėžkite savo tematiką: -- Done
// kelių valstybių sudėtingesnes vėliavas http://flagpedia.net/index
// pvz: Pietų Afrikos, Makedonijos, Norvegijos, Graikijos, Britanijos, ...
// arba futbolo, krepšinio ar ledo ritulio aikštes su žaidėjų pozicijomis  
    private void drawFreeThema() {  
                        
        drawBangladeshFlag();
        drawGuyanaFlag(0, 250, 20);
        //drawSomething(0,100,20);
       
    }    
    private void drawBangladeshFlag()
    {
    gc.setLineWidth(2);

    gc.setFill(Color.RED);
    gc.fillRect(0,0,100 * 4,60 * 4); // 100 60
    gc.setFill(Color.WHITE);
    gc.fillRect(0, 0, 100 / 4 * 4, 60 * 4);
    double[] x = new double[]{25 * 4, 40 * 4, 25 * 4};
    double[] y = new double[]{0 * 4, 6 * 4, 12 * 4};
    double[] y1 = new double[]{12 * 4, 18 * 4, 24 * 4};
    double[] y2 = new double[]{24 * 4, 30 * 4, 36 * 4};
    double[] y3 = new double[]{36 * 4, 42 * 4, 48 * 4};
    double[] y4 = new double[]{48 * 4, 54 * 4, 60 * 4};
    gc.fillPolygon(x, y, 3);
    gc.fillPolygon(x, y1, 3);
    gc.fillPolygon(x, y2, 3);
    gc.fillPolygon(x, y3, 3);
    gc.fillPolygon(x, y4, 3);
    }

    private void drawFlag()
    {
    gc.setFill(Color.GREEN);
    gc.fillRect(10, 220, 300, 170);
    gc.setFill(Color.RED);
    gc.fillOval(90, 250, 120, 120);
    }
    private void drawGuyanaFlag(double x, double y, double scale){
         gc.setFill(Color.GREEN);
        gc.fillRect(x, y, scale*12, scale*9);
        gc.setFill(Color.WHITE);
        gc.fillPolygon(new double[]{ x, x + scale*12  , x},
                       new double[]{ y, y + scale*9/2, y + scale*9}, 3);
        gc.setFill(Color.YELLOW);
        gc.fillPolygon(new double[]{ x, x + scale*12 - scale/2  , x},
                       new double[]{ y + scale/2, y + scale*9/2, y + scale*9 - scale/2}, 3);
        gc.setFill(Color.BLACK);
        gc.fillPolygon(new double[]{ x, x + scale*6  , x},
                       new double[]{ y, y + scale*9/2, y + scale*9}, 3);
        gc.setFill(Color.RED);
        gc.fillPolygon(new double[]{ x, x + scale*6 - scale/2  , x},
                       new double[]{ y + scale/2, y + scale*9/2, y + scale*9 - scale/2}, 3);
    }
//    private void drawSomething(double x, double y, double scale){
//        gc.setLineWidth(5);       // brėžimo linijos plotis
//        gc.setStroke(Color.RED);  // ir tos linijos spalva
//        gc.strokeLine(30,30,30,100);
//        gc.strokeLine(30,30,100,10);
//        //gc.strokeLine(canvasW / 2, 0, canvasW / 2, canvasH);
//        
//    }
// kontrolinės užduotys gynimo metu:
// įvairios vėliavos, tiesiog tokios sudėtinės figūros kaip namukas,
// medis, eglė, sniego senio siluetas :-) ir t.t.    
    @Override
    public void createControls(){
        addButton("clear", e -> clearCanvas()); 
        addButton("grid",  e -> baseGrid());
        addButton("HVC",   e -> drawHVtoCenter());
        addButton("XC",    e -> drawXtoCenter());
        addButton("pvz1",  e -> drawExamples1());
        addButton("UniCode",  e -> drawUnicode());
        addButton("draw grid", action -> drawGrid1());
        addButton("draw pline it pol", action -> drawExamples2());
        addButton("draw Polygons", action -> drawExamples3());
        addButton("draw rings", action -> drawOlympicRings());
        addButton("draw flags", e -> drawFreeThema());
        addNewHBox();
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Braižymai Canvas lauke (KTU IF)");        
        setCanvas(Color.CYAN.brighter(), 600, 400);
        super.start(stage);
    }       
    public static void main(String[] args) {
        launch(args);
    }    
}
