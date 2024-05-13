package tareapaint;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figura {
    protected Color color;
    protected boolean relleno;
    
    public Figura(Color color, boolean relleno) {
        this.color = color;
        this.relleno = relleno;
    }
    
    public abstract void dibujar(Graphics g);
}
