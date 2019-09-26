package ar.edu.unsta.robotteam.mate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Clase Dibujable que dibuja una circunferencia.
 * Sus atributos son: punto centro, radio y color.
 *
 * @author gustavo
 */
public class Circunferencia implements Dibujable {

    private Point.Double m_centro;
    private Color m_color;

    private double m_radio;

    /**
     * Get the value of radio
     *
     * @return the value of radio
     */
    public double getRadio() {
        return m_radio;
    }

    /**
     * Set the value of radio
     *
     * @param p_radio new value of radio
     */
    public void setRadio(double p_radio) {
        this.m_radio = p_radio;
    }

    public Circunferencia(double p_centrox, double p_centroy, double p_radio,
            Color p_color) {
        setCentro(new Point.Double(p_centrox, p_centroy));
        setRadio(p_radio);
        setColor(p_color);
    }

    public Circunferencia(Point.Double p_centro, double p_radio, Color p_color) {
        this(p_centro.x, p_centro.y, p_radio, p_color);
    }

    @Override
    public void dibuja(Lienzo p_lienzo, Graphics2D p_gr) {
        p_gr.setPaint(m_color);
        Point l_centro = p_lienzo.ajusta(getCentro());
        int l_radio = (int) p_lienzo.ajusta(m_radio);
        p_gr.drawOval(l_centro.x - l_radio, l_centro.y - l_radio, 2 * l_radio,
                2 * l_radio);

    }

    public Color getColor() {
        return m_color;
    }

    public void setColor(Color color) {
        this.m_color = color;

    }

    public Point.Double getCentro() {
        return m_centro;
    }

    public void setCentro(Point.Double centro) {
        this.m_centro = centro;
    }

}
