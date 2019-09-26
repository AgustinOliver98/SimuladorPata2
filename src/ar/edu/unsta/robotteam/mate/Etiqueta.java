package ar.edu.unsta.robotteam.mate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author gustavo
 */
public class Etiqueta implements Dibujable {

    private Point.Double m_centro;
    private String m_leyenda;
    private Color m_color;

    public Etiqueta() {

    }

    public Etiqueta(double p_centrox, double p_centroy, Color p_color,
            String p_leyenda) {
        setCentro(new Point.Double(p_centrox, p_centroy));
        setColor(p_color);
        setLeyenda(p_leyenda);
    }

    public Etiqueta(Point.Double p_centro, Color p_color, String p_leyenda) {
        this(p_centro.x, p_centro.y, p_color, p_leyenda);
    }

    @Override
    public void dibuja(Lienzo p_lienzo, Graphics2D p_gr) {
        p_gr.setPaint(m_color);
        Point l_centro = p_lienzo.ajusta(getCentro());
        int l_radio = 3;
        p_gr.fillOval(l_centro.x - l_radio, l_centro.y - l_radio, 2 * l_radio,
                2 * l_radio);

        p_gr.drawString(getLeyenda(), l_centro.x + l_radio + l_radio, l_centro.y
                + l_radio);

    }

    public Point.Double getCentro() {
        return m_centro;
    }

    public void setCentro(Point.Double centro) {
        this.m_centro = centro;
    }

    public String getLeyenda() {
        return m_leyenda;
    }

    public void setLeyenda(String leyenda) {
        this.m_leyenda = leyenda;
    }

    public Color getColor() {
        return m_color;
    }

    public void setColor(Color color) {
        this.m_color = color;
    }

}
