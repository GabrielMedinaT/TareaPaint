/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareapaint;

import java.awt.Color;
import java.awt.Graphics;

public class Punto extends Figura {
    private int x, y;
    
    public Punto(int x, int y, Color color, boolean relleno) {
        super(color, relleno);
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        if (relleno) {
            g.fillOval(x, y, 5, 5); // Dibuja un punto como un círculo relleno
        } else {
            g.drawOval(x, y, 1, 1); // Dibuja un punto como un círculo no relleno
        }
    }
}

