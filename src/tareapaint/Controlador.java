package tareapaint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Controlador {

    private Modelo modelo;
    private Vista vista;
    private int numeroLados;
    private List<Polygon> poligonos; // Lista para almacenar los polígonos dibujados
    private int startX, startY;
    private int endX, endY;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        poligonos = new ArrayList<>(); // Inicializar la lista de polígonos

        // Configurar el controlador para la vista
        vista.poligono.addActionListener(e -> {
    if (vista.isSeleccionado()) {
        agregarMouseListenerPoligono();
    } else {
        MouseListener[] mouseListeners = vista.lienzo.getMouseListeners();
        if (mouseListeners.length > 0) {
            vista.lienzo.removeMouseListener(mouseListeners[0]);
        }
        MouseMotionListener[] mouseMotionListeners = vista.lienzo.getMouseMotionListeners();
        if (mouseMotionListeners.length > 0) {
            vista.lienzo.removeMouseMotionListener(mouseMotionListeners[0]);
        }
    }
});

        vista.linea.addActionListener(e -> dibujarLinea());
        vista.circulo.addActionListener(e -> dibujarCirculo());
        vista.numLados.addActionListener(e -> actualizarNumeroLados());
        vista.guardar.addActionListener(e -> guardarPoligonosEnBaseDatos());

        // Inicializar el número de lados
        actualizarNumeroLados();
    }

    private void guardarPoligonosEnBaseDatos() {
        for (Polygon poligono : poligonos) {
            modelo.insertarFigura(poligono, vista.obtenerNombreArchivo()); // Cambia vista.obtenerNombreArchivo() al método correspondiente que obtiene el nombre de archivo
        }

    }

    private void imprimirPoligonos() {
        for (int i = 0; i < poligonos.size(); i++) {
            Polygon polygon = poligonos.get(i);
            int[] xPoints = polygon.xpoints;
            int[] yPoints = polygon.ypoints;
            int nPoints = polygon.npoints;

            System.out.println("Polígono " + i + ":");
            for (int j = 0; j < nPoints; j++) {
                System.out.println("Punto " + j + ": (" + xPoints[j] + ", " + yPoints[j] + ")");
            }
        }
    }

    private void agregarMouseListenerPoligono() {

        System.out.println("Desde controlador " + vista.isSeleccionado() + " Getter " + vista.isSeleccionado());
        if (!vista.isSeleccionado()) {
            System.out.println("NO Seleccionado");

        } else {
            System.out.println("Seleccionado");
            vista.lienzo.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    startX = e.getX();
                    startY = e.getY();
                    endX = startX;
                    endY = startY;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    // Agregar el polígono actual a la lista
                    agregarPoligono();
                    // Redibujar todos los polígonos en el lienzo
                    dibujarPoligonos();
                    imprimirPoligonos();

                }
            });

            vista.lienzo.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    // Redibujar el polígono actual en el lienzo mientras se arrastra el ratón
                    dibujarPoligonos();
                    dibujarPoligono();
                }
            });

        }

    }

    private void actualizarNumeroLados() {
        numeroLados = (int) vista.numLados.getSelectedItem();
    }

    private void agregarPoligono() {

        // Calcular las coordenadas de los puntos del polígono
        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);
        int centroX = startX + width / 2;
        int centroY = startY + height / 2;
        int radio = Math.min(width, height) / 2;

        int[] xPoints = new int[numeroLados];
        int[] yPoints = new int[numeroLados];
        for (int i = 0; i < numeroLados; i++) {
            double angulo = 2 * Math.PI * i / numeroLados;
            xPoints[i] = (int) (centroX + radio * Math.cos(angulo));
            yPoints[i] = (int) (centroY + radio * Math.sin(angulo));
        }

        // Agregar el polígono a la lista
        poligonos.add(new Polygon(xPoints, yPoints, numeroLados));
    }

    private void dibujarPoligonos() {

        // Limpiar el lienzo
        Graphics g = vista.lienzo.getGraphics();
        g.clearRect(0, 0, vista.lienzo.getWidth(), vista.lienzo.getHeight());

        // Dibujar todos los polígonos en la lista
        g.setColor(Color.BLACK);
        for (Polygon polygon : poligonos) {
            g.drawPolygon(polygon);
        }

    }

    private void dibujarPoligono() {

        // Calcular las coordenadas de los puntos del polígono
        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);
        int centroX = startX + width / 2;
        int centroY = startY + height / 2;
        int radio = Math.min(width, height) / 2;

        int[] xPoints = new int[numeroLados];
        int[] yPoints = new int[numeroLados];
        for (int i = 0; i < numeroLados; i++) {
            double angulo = 2 * Math.PI * i / numeroLados;
            xPoints[i] = (int) (centroX + radio * Math.cos(angulo));
            yPoints[i] = (int) (centroY + radio * Math.sin(angulo));
        }

        // Dibujar el polígono actual en el lienzo
        Graphics g = vista.lienzo.getGraphics();
        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, numeroLados);

    }

    private void dibujarLinea() {
        // Aquí puedes implementar la lógica para dibujar una línea en el lienzo
    }

    private void dibujarCirculo() {
        // Aquí puedes implementar la lógica para dibujar un círculo en el lienzo
    }
}
