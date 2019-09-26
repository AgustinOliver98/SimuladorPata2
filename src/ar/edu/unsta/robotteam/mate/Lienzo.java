package ar.edu.unsta.robotteam.mate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.JComponent;

/**
 * Componente Swing sobre el que puede dibujarse una lista objetos "Dibujables"
 *
 * @author gustavo
 */
public class Lienzo extends JComponent {

    private double m_modeloAPantalla = 1.0;
    private Point m_origenPantalla = new Point(0, 0);

    private List<Dibujable> m_dibujables;

    public List<Dibujable> getDibujables() {
        return m_dibujables;
    }

    public void setDibujables(List<Dibujable> p_dibujables) {
        this.m_dibujables = p_dibujables;
    }

    public Lienzo() {
        setBackground(Color.WHITE);
    }

    public double getModeloAPantalla() {
        return m_modeloAPantalla;
    }

    public void setModeloAPantalla(double p_modeloAPantalla) {
        this.m_modeloAPantalla = p_modeloAPantalla;
    }

    public Point getOrigenPantalla() {
        return m_origenPantalla;
    }

    public void setOrigenPantalla(Point origenPantalla) {
        this.m_origenPantalla = origenPantalla;
    }

    @Override
    public void paint(Graphics p_graphics) {
        super.paint(p_graphics);
        // Pinta el fondo
        Graphics2D l_graphics = (Graphics2D) p_graphics;
        Rectangle l_bounds = l_graphics.getClipBounds();
        l_graphics.setPaint(getBackground());
        l_graphics.fillRect(0, 0, l_bounds.width, l_bounds.height);
        if (m_dibujables == null) {
            // Nada que dibujar
            return;
        } // end if
        for (Dibujable l_aDibujar : m_dibujables) {
            l_aDibujar.dibuja(this, l_graphics);
        } // end for

    }

    /**
     * Convierte una coordenada del espacio de modelo, en una coordenada
     * del espacio de pantalla
     *
     * @param p_modelo
     * @return
     */
    public Point ajusta(Point.Double p_modelo) {
        Point l_pantalla = new Point();
        l_pantalla.x = (int) (p_modelo.x * getModeloAPantalla())
                + getOrigenPantalla().x;
        l_pantalla.y = (int) (-p_modelo.y * getModeloAPantalla()
                + getOrigenPantalla().y);
        return l_pantalla;
    }

    public double ajusta(double p_modelo) {
        return p_modelo * getModeloAPantalla();
    }
}
