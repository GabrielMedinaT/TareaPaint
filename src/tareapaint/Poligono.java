package tareapaint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Poligono extends Figura {
    private int xCentro;
    private int yCentro;
    private int radio;
    private int numLados;

    public Poligono(int xCentro, int yCentro, int radio, int numLados, Color color, boolean relleno) {
        super(color, relleno);
        this.xCentro = xCentro;
        this.yCentro = yCentro;
        this.radio = radio;
        this.numLados = numLados;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        int[] puntosX = new int[numLados];
        int[] puntosY = new int[numLados];

        double angulo = 2 * Math.PI / numLados;
        for (int i = 0; i < numLados; i++) {
            puntosX[i] = (int) (xCentro + radio * Math.cos(angulo * i));
            puntosY[i] = (int) (yCentro + radio * Math.sin(angulo * i));
        }

        Polygon poligono = new Polygon(puntosX, puntosY, numLados);
        if (relleno) {
            g.fillPolygon(poligono);
        } else {
            g.drawPolygon(poligono);
        }
    }
}
