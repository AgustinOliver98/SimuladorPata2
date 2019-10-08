package ar.edu.unsta.robotteam.mate;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Point2D;

/**
 *
 * @author gustavo
 */
public class VectorPeso implements Dibujable {

    /**
     * Peso, en gramos
     */
    private double m_peso;
    /**
     * Punto de aplicación del vector de peso
     */
    private Point.Double m_puntoAplicacion;

    public VectorPeso(double p_peso, Point2D.Double p_puntoAplicacion) {
        this.m_peso = p_peso;
        if (p_puntoAplicacion == null) {
            throw new RuntimeException("Punto de aplicación no provisto");
        } // end if
        this.m_puntoAplicacion = p_puntoAplicacion;
    }

    
    @Override
    public void dibuja(Lienzo p_lienzo, Graphics2D p_gr) {
        // Backup
        Paint l_paint = p_gr.getPaint();
        Stroke l_stroke = p_gr.getStroke();

        int l_len = (int) (p_lienzo.ajusta(m_peso) * 0.02);
        Point l_origen = p_lienzo.ajusta(m_puntoAplicacion);
        Point l_destino = new Point(l_origen.x, l_origen.y + l_len);
        int l_flecha = 6;
        
        
        
        p_gr.setPaint(Color.LIGHT_GRAY);
        p_gr.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        p_gr.drawLine(l_origen.x, l_origen.y, l_destino.x, l_destino.y);
        p_gr.drawLine(l_destino.x, l_destino.y, l_destino.x - l_flecha,
                l_destino.y - l_flecha);
        p_gr.drawLine(l_destino.x, l_destino.y, l_destino.x + l_flecha,
                l_destino.y - l_flecha);
        p_gr.setPaint(Color.RED);
        p_gr.setStroke(new BasicStroke(0, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        p_gr.drawLine(l_origen.x, l_origen.y, l_destino.x, l_destino.y);
        p_gr.drawLine(l_destino.x, l_destino.y, l_destino.x - l_flecha,
                l_destino.y - l_flecha);
        p_gr.drawLine(l_destino.x, l_destino.y, l_destino.x + l_flecha,
                l_destino.y - l_flecha);

        // Restore
        p_gr.setPaint(l_paint);
        p_gr.setStroke(l_stroke);

    }


    /**
     * Set the value of peso
     *
     * @param p_peso new value of peso
     */
    public void setModulo(double p_peso) {
        m_peso = p_peso;
    }
    /**
     * Get the value of peso
     *
     * @return the value of peso
     */
    public double getPeso() {
        return m_peso;
    }

    /**
     * Get the value of puntoAplicacion
     *
     * @return the value of puntoAplicacion
     */
    public Point.Double getPuntoAplicacion() {
        return m_puntoAplicacion;
    }

    /**
     * Set the value of puntoAplicacion
     *
     * @param p_puntoAplicacion new value of puntoAplicacion
     */
    public void setPuntoAplicacion(Point.Double p_puntoAplicacion) {
        this.m_puntoAplicacion = p_puntoAplicacion;
    }

}
