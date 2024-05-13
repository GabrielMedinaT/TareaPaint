/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareapaint;

import java.awt.Color;
import java.awt.Graphics;

public class Recta extends Figura {
    private int x1, y1, x2, y2;
    
    public Recta(int x1, int y1, int x2, int y2, Color color, boolean relleno) {
        super(color, relleno);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
}
