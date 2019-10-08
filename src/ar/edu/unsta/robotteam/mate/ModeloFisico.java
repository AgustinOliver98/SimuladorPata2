package ar.edu.unsta.robotteam.mate;

import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author gustavo
 */
public class ModeloFisico {

    /**
     * Centro de masa efectivo de BC
     */
    private Point.Double m_ApBC;
    /**
     * Centro de masa efectivo de DE
     */
    private Point.Double m_ApDE;
    /**
     * Centro de masa efectivo de GH
     */
    private Point.Double m_ApGH;
    /**
     * Centro de masa efectivo de OD
     */
    private Point.Double m_ApOD;

    /**
     * Centro de masa del actuador de hombro (BC)
     */
    private Point.Double m_CMBC;
    /**
     * Centro de masa del antebrazo (DE)
     */
    private Point.Double m_CMDE;
    /**
     * Centro de masa del actuador del codo (GH)
     */
    private Point.Double m_CMGH;
    /**
     * Centro de masa del brazo (OD)
     */
    private Point.Double m_CMOD;
    /**
     * Peso del actuador del hombro (BC), en gramos
     */
    private double m_PBC;
    /**
     * Peso del antebrazo (DE), en gramos
     */
    private double m_PDE;
    /**
     * Peso en E (mano + carga útil), en gramos
     */
    private double m_PE;
    /**
     * Peso del actuador del codo (GH), en gramos
     */
    private double m_PGH;
    /**
     * Peso del brazo (OD), en gramos
     */
    private double m_POD;

    /**
     * Referencia al modelo geométrico
     */
    private ModeloBrazo m_modeloGeom;

    public ModeloFisico(ModeloBrazo p_modeloGeom) {
        this.m_modeloGeom = p_modeloGeom;
        setPBC(1200);
        setPDE(2500);
        setPE(5000);
        setPGH(1200);
        setPOD(2500);

        setCMBC(new Point.Double(100, 0));
        setCMDE(new Point.Double(200, 0));
        setCMGH(new Point.Double(100, 0));
        setCMOD(new Point.Double(200, 0));
    }

    public Point.Double getCMBC() {
        return m_CMBC;
    }

    public void setCMBC(Point.Double p_CMBC) {
        this.m_CMBC = p_CMBC;
    }

    public Point.Double getCMDE() {
        return m_CMDE;
    }

    public void setCMDE(Point.Double p_CMDE) {
        this.m_CMDE = p_CMDE;
    }

    public Point.Double getCMGH() {
        return m_CMGH;
    }

    public void setCMGH(Point.Double p_CMGH) {
        this.m_CMGH = p_CMGH;
    }

    public Point.Double getCMOD() {
        return m_CMOD;
    }

    public void setCMOD(Point.Double p_CMOD) {
        this.m_CMOD = p_CMOD;
    }

    public Collection<Dibujable> getDibujables() {
        List<Dibujable> l_toReturn = new LinkedList<>();

        l_toReturn.add(new VectorPeso(m_PBC, m_ApBC));
        l_toReturn.add(new VectorPeso(m_PDE, m_ApDE));
        l_toReturn.add(new VectorPeso(m_PGH, m_ApGH));
        l_toReturn.add(new VectorPeso(m_POD, m_ApOD));
        l_toReturn.add(new VectorPeso(m_PE, m_modeloGeom.getE()));

        return l_toReturn;
    }

    /**
     * Get the value of modeloGeom
     *
     * @return the value of modeloGeom
     */
    public ModeloBrazo getModeloGeom() {
        return m_modeloGeom;
    }

    /**
     * Set the value of modeloGeom
     *
     * @param p_modeloGeom new value of modeloGeom
     */
    public void setModeloGeom(ModeloBrazo p_modeloGeom) {
        this.m_modeloGeom = p_modeloGeom;
    }

    public double getPBC() {
        return m_PBC;
    }

    public void setPBC(double PBC) {
        this.m_PBC = PBC;
    }

    public double getPDE() {
        return m_PDE;
    }

    public void setPDE(double PDE) {
        this.m_PDE = PDE;
    }

    /**
     * Get the value of PE
     *
     * @return the value of PE
     */
    public double getPE() {
        return m_PE;
    }

    /**
     * Set the value of PE
     *
     * @param p_PE new value of PE
     */
    public void setPE(double p_PE) {
        this.m_PE = p_PE;
    }

    public double getPGH() {
        return m_PGH;
    }

    public void setPGH(double PGH) {
        this.m_PGH = PGH;
    }

    public double getPOD() {
        return m_POD;
    }

    public void setPOD(double POD) {
        this.m_POD = POD;
    }

    public boolean recalcula() {
        if (m_CMBC == null) {
            System.out.println("CMBC null");
            return false;
        } // end if
        if (m_CMDE == null) {
            System.out.println("CMDE null");
            return false;
        } // end if
        if (m_CMGH == null) {
            System.out.println("CMGH null");
            return false;
        } // end if
        if (m_CMOD == null) {
            System.out.println("CMOD null");
            return false;
        } // end if

        // Ángulo del actuador de hombro
        double l_CB = Herramientas.angle(m_modeloGeom.getC(), m_modeloGeom.
                getB());
        m_ApBC = Herramientas.rotateAndMove(m_CMBC, l_CB, m_modeloGeom.getC());

        double l_OD = Math.atan2(m_modeloGeom.getD().y, m_modeloGeom.getD().x);
        m_ApOD = Herramientas.rotateAndMove(m_CMOD, l_OD, m_modeloGeom.getO());

        double l_DE = Herramientas.angle(m_modeloGeom.getD(), m_modeloGeom.getE());
        m_ApDE = Herramientas.rotateAndMove(m_CMDE, l_DE, m_modeloGeom.getD());
        
        double l_GH = Herramientas.angle(m_modeloGeom.getH(), m_modeloGeom.getG());
        m_ApGH = Herramientas.rotateAndMove(m_CMGH, l_GH, m_modeloGeom.getH());

        return true;
    }

}
