package ar.edu.unsta.robotteam.mate;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

/**
 * Modelo estructural y de estado del brazo completo.
 * La cadena cinemática comienza en el origen O del sistema bidimensional
 * El brazo completo se compone de: un brazo, un antebrazo, un actuador lineal de
 * hombro y un actuador lineal de codo.
 *
 * Todas las unidades de longitud están expresadas en milímetros.
 *
 * @author gustavo
 */
public class ModeloBrazo {

    /**
     * Punto sobre el eje brazo, ortogonal a B
     */
    private Point.Double m_A;
    /**
     * Distancia desde el eje del brazo, hasta el punto B
     */
    private double m_AB = 30.0;
    /**
     * Ángulo fijo entre OA y OB.
     * Se recalcula cuando cambia OA o AB
     */
    private double m_AOB;

    /**
     * Punto del brazo, donde aplica el extremo móvil del actuador del hombro
     */
    private Point.Double m_B;

    private double m_BCMin = 220.0;
    private double m_BCMax = m_BCMin * 1.8;
    /**
     * Actuador del hombro
     */
    private double m_BC = (m_BCMin + m_BCMax) / 2;
    /**
     * Punto fijo de la base, donde aplica el extremo fijo del actuador del hombro
     */
    private Point.Double m_C = new Point.Double(90.0, -30.0);
    /**
     * Codo
     */
    private Point.Double m_D;

    public Point.Double getO() {
        return new Point2D.Double(0.0, 0.0);
    }

    public Point.Double getD() {
        return m_D;
    }
    /**
     * Largo del antebrazo
     */
    private double m_DE = 400.0;
    private double m_DF;
    /**
     * Distancia del codo hasta la aplicación móvil del actuador de codo.
     * Se recalcula cuando cambia DF o FG.
     */
    private double m_DG;

    /**
     * Punto de la muñeca
     */
    private Point.Double m_E;
    /**
     * Punto sobre el antebrazo, perpendicular a G
     */
    private Point.Double m_F;
    /**
     * Ángulo GDF
     */
    private double m_FDG;
    private double m_FG = 30.0;
    /**
     * Punto móvil del actuador de codo
     */
    private Point.Double m_G;

    public Point2D.Double getG() {
        return m_G;
    }
    private double m_GHMin = 300.0;
    private double m_GHMax = m_GHMin * 1.8;
    private double m_GH = (m_GHMin + m_GHMax) / 2;
    private Point.Double m_H = new Point.Double(-100.0, -10.0);

    /**
     * Origen del sistema
     */
    private Point.Double m_O = new Point2D.Double(0.0, 0.0);
    private double m_OA;
    /**
     * Distancia del origen al punto de aplicación móvil del actuador del hombro.
     * Se recalcula cuando cambia OA o AB
     */
    private double m_OB;
    private double m_OD = 400.0;

    private List<Dibujable> m_dibujables;
    private ModeloFisico m_modeloFisico;
    private boolean m_recalculoValido = false;
    private List<Dibujable> m_simulaciones;

    public ModeloBrazo() {
        // Usa el setter para que se recalcule OB
        setOA(300.0);
        // Usa el setter para que se recalcule DG
        setDF(100.0);
        setSimulaciones(new LinkedList<Dibujable>());
        ModeloFisico l_fisico = new ModeloFisico(this);
        setModeloFisico(l_fisico);

        recalcula();
    }

    public double getAB() {
        return m_AB;
    }

    public void setAB(double AB) {
        this.m_AB = AB;

        recalcOB();

    }

    public Point.Double getB() {
        return m_B;
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
        recalcDG();
    }

    public List<Dibujable> getDibujables() {
        return m_dibujables;
    }

    public Point.Double getE() {
        return m_E;
    }

    public void setE(Point.Double E) {
        this.m_E = E;
    }

    public double getFG() {
        return m_FG;
    }

    public void setFG(double FG) {
        this.m_FG = FG;
        recalcDG();

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

    /**
     * Get the value of modeloFisico
     *
     * @return the value of modeloFisico
     */
    public ModeloFisico getModeloFisico() {
        return m_modeloFisico;
    }

    /**
     * Set the value of modeloFisico
     *
     * @param p_modeloFisico new value of modeloFisico
     */
    public void setModeloFisico(ModeloFisico p_modeloFisico) {
        this.m_modeloFisico = p_modeloFisico;
    }

    public double getOA() {
        return m_OA;
    }

    public void setOA(double p_OA) {
        this.m_OA = p_OA;

        recalcOB();
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
     * Get the value of simulaciones
     *
     * @return the value of simulaciones
     */
    public List<Dibujable> getSimulaciones() {
        return m_simulaciones;
    }

    /**
     * Set the value of simulaciones
     *
     * @param p_simulaciones new value of simulaciones
     */
    private void setSimulaciones(List<Dibujable> p_simulaciones) {
        this.m_simulaciones = p_simulaciones;
    }

    /**
     * Get the value of recalculoValido
     *
     * @return the value of recalculoValido
     */
    public boolean isRecalculoValido() {
        return m_recalculoValido;
    }

    /**
     * Set the value of recalculoValido
     *
     * @param p_recalculoValido new value of recalculoValido
     */
    public void setRecalculoValido(boolean p_recalculoValido) {
        this.m_recalculoValido = p_recalculoValido;
    }

    private void recalcDG() {
        m_DG = Math.hypot(m_DF, m_FG);
        m_FDG = Math.atan2(m_FG, m_DF);
    }

    private void recalcOB() {
        m_OB = Math.hypot(m_OA, m_AB);
        m_AOB = Math.atan2(m_AB, m_OA);
    }

    /**
     * Reubica todos los puntos móviles.
     * La secuencia es:
     * 1. calcula B como la intersección de las circunf. centro O y centro C
     * 2. Calcula A, rotando desde OB hacia OA
     * 3. Calcula D, extendiendo OA
     * 4. Calcula G, como la intersecc. de las circunf. centro D y centro H
     * 5. Calcula F, rotando desde DG hacia DF
     * 6. Calcula E, extendiendo DF
     *
     * Devuelve true si el recálculo generó posiciones válidas de los puntos.
     * O false si alguno de los puntos no pudo ser calculado.
     */
    public boolean recalcula() {

        Point.Double l_A, l_B, l_D, l_G;

        l_B = Herramientas.solveQuadEq(m_O, m_OB, m_C, m_BC,
                Herramientas.RootIndex.ROOT_SECOND);

        if (l_B == null) {
            // Sin solución
            setRecalculoValido(false);
            return false;
        } // end if

        // Recalcula A. Debería hacerse sin trigonometría, con una matriz de 
        // transformación lineal
        double l_XOB = Math.atan2(l_B.y, l_B.x);
        double l_XOA = l_XOB + m_AOB;

        l_A = new Point.Double(m_OA * Math.cos(l_XOA), m_OA * Math.sin(l_XOA));

        // Recalcula D.
        double l_OD_OA = m_OD / m_OA;

        l_D = new Point.Double(l_OD_OA * l_A.x, l_OD_OA * l_A.y);

        l_G = Herramientas.solveQuadEq(l_D, m_DG, m_H, m_GH,
                Herramientas.RootIndex.ROOT_FIRST);

        if (l_G == null) {
            // Sin solución
            setRecalculoValido(false);
            return false;
        } // end if

        // Commit
        m_A = l_A;
        m_B = l_B;
        m_D = l_D;
        m_G = l_G;

        double l_XDG = Math.atan2(m_G.y - m_D.y, m_G.x - m_D.x);
        double l_XDF = l_XDG - m_FDG;
        double l_DFx = m_DF * Math.cos(l_XDF);
        double l_DFy = m_DF * Math.sin(l_XDF);
        m_F = new Point.Double(l_DFx + m_D.x, l_DFy + m_D.y);

        double l_DE_DF = m_DE / m_DF;

        m_E = new Point.Double(l_DFx * l_DE_DF + m_D.x, l_DFy * l_DE_DF + m_D.y);

        if (!getModeloFisico().recalcula()) {
            setRecalculoValido(false);
            return false;
        } // end if

        redibuja();
        setRecalculoValido(true);
        return true;

    }

    private void redibuja() {
        m_dibujables = new LinkedList<>();
        m_dibujables.addAll(m_simulaciones);

        // Eje X
        SegmentoLinea l_nuevaLinea = new SegmentoLinea(0, 0, 700, 0, Color.GRAY,
                0);
        m_dibujables.add(l_nuevaLinea);
        // Eje Y
        l_nuevaLinea = new SegmentoLinea(0, 0, 0, 700, Color.GRAY, 0);
        m_dibujables.add(l_nuevaLinea);
        // Brazo
        l_nuevaLinea = new SegmentoLinea(0, 0, m_D.x, m_D.y, Color.BLUE, 50);
        m_dibujables.add(l_nuevaLinea);
        // Antebrazo
        l_nuevaLinea
                = new SegmentoLinea(m_D.x, m_D.y, m_E.x, m_E.y, Color.GREEN, 30);
        m_dibujables.add(l_nuevaLinea);
        // Actuador de hombro
        l_nuevaLinea = new SegmentoLinea(m_C.x, m_C.y, m_B.x, m_B.y, Color.RED,
                10);
        m_dibujables.add(l_nuevaLinea);
        // Actuador de codo
        l_nuevaLinea = new SegmentoLinea(m_H.x, m_H.y, m_G.x, m_G.y,
                Color.ORANGE, 10);
        m_dibujables.add(l_nuevaLinea);

        // Sistema de ecuaciones del hombro
        m_dibujables.
                add(new Circunferencia(m_O, m_OB, new Color(240, 240, 240)));
        m_dibujables.
                add(new Circunferencia(m_C, m_BC, new Color(240, 240, 240)));
        // Sistema de ecuaciones del codo
        m_dibujables.
                add(new Circunferencia(m_H, m_GH, new Color(240, 240, 240)));
        m_dibujables.
                add(new Circunferencia(m_D, m_DG, new Color(240, 240, 240)));

        m_dibujables.add(new Etiqueta(0.0, 0.0, Color.GRAY, "O"));
        m_dibujables.add(new Etiqueta(700.0, 0.0, Color.GRAY, "X"));
        m_dibujables.add(new Etiqueta(0.0, 700.0, Color.GRAY, "Y"));
        m_dibujables.add(new Etiqueta(m_A, Color.CYAN, "A"));
        m_dibujables.add(new Etiqueta(m_B, Color.BLUE, "B"));
        m_dibujables.add(new Etiqueta(m_C, Color.RED, "C"));
        m_dibujables.add(new Etiqueta(m_D, Color.BLUE, "D"));
        m_dibujables.add(new Etiqueta(m_E, Color.BLACK, "E"));
        m_dibujables.add(new Etiqueta(m_F, Color.BLACK, "F"));
        m_dibujables.add(new Etiqueta(m_G, Color.BLACK, "G"));
        m_dibujables.add(new Etiqueta(m_H, Color.ORANGE, "H"));

        if (m_modeloFisico != null) {
            m_dibujables.addAll(m_modeloFisico.getDibujables());
        } // end if

    }

}
