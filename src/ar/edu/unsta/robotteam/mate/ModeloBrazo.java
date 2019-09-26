package ar.edu.unsta.robotteam.mate;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author gustavo
 */
public class ModeloBrazo {

    private Point.Double m_O = new Point2D.Double(0.0, 0.0);
    private double m_OB;
    private double m_OC;

    private Point.Double m_A;
    private double m_AB = 30.0;
    private Point.Double m_B;

    private double m_BC = 300.0;
    private double m_BCMax = 400.0;
    private double m_BCMin = 200.0;
    private Point.Double m_C = new Point.Double(150.0, -10.0);
    private Point.Double m_D;
    private double m_DE = 400.0;
    private double m_DF = 100.0;
    private Point.Double m_E;
    private Point.Double m_F;
    private double m_FG = 30.0;
    private Point.Double m_G;
    private double m_GH = 300.0;
    private double m_GHMax = 350.0;
    private double m_GHMin = 250.0;
    private Point.Double m_H = new Point.Double(-150.0, -10.0);
    ;
    private double m_OA;
    private double m_OD = 400.0;

    private List<Dibujable> m_dibujables;

    public ModeloBrazo() {
        setOA(300.0);
        recalcula();
    }

    public double getAB() {
        return m_AB;
    }

    public void setAB(double AB) {
        this.m_AB = AB;
        m_OB = Math.hypot(m_OA, m_AB);

    }

    public double getBC() {
        return m_BC;
    }

    public void setBC(double BC) {
        this.m_BC = BC;
    }

    /**
     * Get the value of BCMax
     *
     * @return the value of BCMax
     */
    public double getBCMax() {
        return m_BCMax;
    }

    /**
     * Set the value of BCMax
     *
     * @param p_BCMax new value of BCMax
     */
    public void setBCMax(double p_BCMax) {
        this.m_BCMax = p_BCMax;
    }

    /**
     * Get the value of BCMin
     *
     * @return the value of BCMin
     */
    public double getBCMin() {
        return m_BCMin;
    }

    /**
     * Set the value of BCMin
     *
     * @param p_BCMin new value of BCMin
     */
    public void setBCMin(double p_BCMin) {
        this.m_BCMin = p_BCMin;
    }

    public Point.Double getC() {
        return m_C;
    }

    public void setC(Point.Double C) {
        this.m_C = C;
        if (m_C != null) {
            m_OC = Math.hypot(m_C.x, m_C.y);
        } // end if
    }

    public double getDE() {
        return m_DE;
    }

    public void setDE(double DE) {
        this.m_DE = DE;
    }

    public double getDF() {
        return m_DF;
    }

    public void setDF(double DF) {
        this.m_DF = DF;
    }

    public List<Dibujable> getDibujables() {
        return m_dibujables;
    }

    public double getFG() {
        return m_FG;
    }

    public void setFG(double FG) {
        this.m_FG = FG;
    }

    public double getGH() {
        return m_GH;
    }

    public void setGH(double GH) {
        this.m_GH = GH;
    }

    /**
     * Get the value of GHMax
     *
     * @return the value of GHMax
     */
    public double getGHMax() {
        return m_GHMax;
    }

    /**
     * Set the value of GHMax
     *
     * @param p_GHMax new value of GHMax
     */
    public void setGHMax(double p_GHMax) {
        this.m_GHMax = p_GHMax;
    }

    /**
     * Get the value of GHMin
     *
     * @return the value of GHMin
     */
    public double getGHMin() {
        return m_GHMin;
    }

    /**
     * Set the value of GHMin
     *
     * @param p_GHMin new value of GHMin
     */
    public void setGHMin(double p_GHMin) {
        this.m_GHMin = p_GHMin;
    }

    public Point.Double getH() {
        return m_H;
    }

    public void setH(Point.Double H) {
        this.m_H = H;
    }

    public double getOA() {
        return m_OA;
    }

    public void setOA(double p_OA) {
        this.m_OA = p_OA;

        m_OB = Math.hypot(m_OA, m_AB);

    }

    /**
     * Get the value of OD
     *
     * @return the value of OD
     */
    public double getOD() {
        return m_OD;
    }

    /**
     * Set the value of OD
     *
     * @param p_OD new value of OD
     */
    public void setOD(double p_OD) {
        this.m_OD = p_OD;
    }

    /**
     * Reubica todos los puntos móviles
     */
    public void recalcula() {

        m_B = Herramientas.solveQuadEq(m_O, m_OB, m_C, m_BC);
        System.out.println(m_B);
        m_A = new Point.Double(0, getOA());
        m_D = new Point.Double(0, getOD());
        m_E = new Point.Double(-getDE(), getOD());
        m_F = new Point.Double(-getDF(), getOD());
        m_G = new Point.Double(m_F.x, m_F.y - getFG());

        redibuja();
    }

    private void redibuja() {
        m_dibujables = new LinkedList<>();

        // Eje X
        SegmentoLinea l_nuevaLinea = new SegmentoLinea(0, 0, 700, 0, Color.GRAY);
        m_dibujables.add(l_nuevaLinea);
        // Eje Y
        l_nuevaLinea = new SegmentoLinea(0, 0, 0, 700, Color.GRAY);
        m_dibujables.add(l_nuevaLinea);
        // Brazo
        l_nuevaLinea = new SegmentoLinea(0, 0, m_D.x, m_D.y, Color.BLUE);
        m_dibujables.add(l_nuevaLinea);
        // Antebrazo
        l_nuevaLinea
                = new SegmentoLinea(m_D.x, m_D.y, m_E.x, m_E.y, Color.GREEN);
        m_dibujables.add(l_nuevaLinea);
        // Actuador de hombro
        l_nuevaLinea = new SegmentoLinea(m_C.x, m_C.y, m_B.x, m_B.y, Color.RED);
        m_dibujables.add(l_nuevaLinea);
        // Actuador de codo
        l_nuevaLinea = new SegmentoLinea(m_H.x, m_H.y, m_G.x, m_G.y,
                Color.ORANGE);
        m_dibujables.add(l_nuevaLinea);

        m_dibujables.add(new Circunferencia(m_O, m_OB, Color.GRAY));
        m_dibujables.add(new Circunferencia(m_C, m_BC, Color.GRAY));
        
        m_dibujables.add(new Etiqueta(0.0, 0.0, Color.GRAY, "O"));
        m_dibujables.add(new Etiqueta(700.0, 0.0, Color.GRAY, "X"));
        m_dibujables.add(new Etiqueta(0.0, 700.0, Color.GRAY, "Y"));
        m_dibujables.add(new Etiqueta(m_B, Color.BLUE, "B"));
        m_dibujables.add(new Etiqueta(m_C, Color.RED, "C"));
        m_dibujables.add(new Etiqueta(m_D, Color.BLUE, "D"));
        m_dibujables.add(new Etiqueta(m_E, Color.GREEN, "E"));
        m_dibujables.add(new Etiqueta(m_G, Color.GREEN, "G"));
        m_dibujables.add(new Etiqueta(m_H, Color.ORANGE, "H"));

    }

}
