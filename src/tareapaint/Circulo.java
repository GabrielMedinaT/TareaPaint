/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareapaint;

import java.awt.Color;
import java.awt.Graphics;

public class Circulo extends Figura {
    private int x, y, radio;
    
    public Circulo(int x, int y, int radio, Color color, boolean relleno) {
        super(color, relleno);
        this.x = x;
        this.y = y;
        this.radio = radio;
    }
    
    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        if (relleno) {
            g.fillOval(x - radio, y - radio, 2 * radio, 2 * radio);
        } else {
            g.drawOval(x - radio, y - radio, 2 * radio, 2 * radio);
        }
    }
}
