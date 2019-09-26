package ar.edu.unsta.robotteam.mate;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

/**
 * Clase Dibujable que dibuja un segmento de recta.
 * Sus atributos son: punto A (inicio), punto B(fin), color y ancho del trazo.
 *
 * @author gustavo
 */
public class SegmentoLinea implements Dibujable {

    private Point.Double m_A;
    private Point.Double m_B;
    private Color m_color;
    private double m_width;

    public SegmentoLinea() {

    }

    public SegmentoLinea(double p_Ax, double p_Ay, double p_Bx, double p_By,
            Color p_color, double p_width) {

        setA(new Point.Double(p_Ax, p_Ay));
        setB(new Point.Double(p_Bx, p_By));
        setColor(p_color);
        setWidth(p_width);
    }

    @Override
    public void dibuja(Lienzo p_lienzo, Graphics2D p_gr) {

        p_gr.setPaint(m_color);
        Point l_A = p_lienzo.ajusta(getA());
        Point l_B = p_lienzo.ajusta(getB());
        int l_Ax = l_A.x;
        int l_Ay = l_A.y;
        int l_Bx = l_B.x;
        int l_By = l_B.y;

        int l_width = (int) p_lienzo.ajusta(m_width);
        Stroke l_currentStroke = p_gr.getStroke();
        p_gr.setStroke(new BasicStroke(l_width, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));

        p_gr.drawLine(l_Ax, l_Ay, l_Bx, l_By);
        p_gr.setStroke(l_currentStroke);
    }

    /**
     * @return the m_color
     */
    public Color getColor() {
        return m_color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.m_color = color;
    }

    /**
     * @return the m_A
     */
    public Point.Double getA() {
        return m_A;
    }

    /**
     * @param p_A the X1 to set
     */
    public void setA(Point.Double p_A) {
        this.m_A = p_A;
    }

    /**
     * @return the m_B
     */
    public Point.Double getB() {
        return m_B;
    }

    /**
     * @param p_B the X2 to set
     */
    public void setB(Point.Double p_B) {
        this.m_B = p_B;
    }

    public double getWidth() {
        return m_width;
    }

    public void setWidth(double width) {
        this.m_width = width;
    }

}