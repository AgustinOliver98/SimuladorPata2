package ar.edu.unsta.robotteam.mate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import javax.swing.SwingUtilities;

/**
 * Punto de entrada de la aplicación del simulador.
 *
 * @author gustavo
 */
public class SimuladorBrazoMain extends javax.swing.JFrame {

    private ModeloBrazo m_modelo;
    private Lienzo m_lienzo;

    /**
     * Creates new form SimuladorBrazoMain
     */
    public SimuladorBrazoMain() {
        initComponents();
        m_modelo = new ModeloBrazo();

        System.out.format("%f < %f < %f; %f < %f < %f %n",
                m_modelo.getBCMin(),
                m_modelo.getBC(),
                m_modelo.getBCMax(),
                m_modelo.getGHMin(),
                m_modelo.getGH(),
                m_modelo.getGHMax()
        );
        ui_sldBC.setMaximum((int) m_modelo.getBCMax());
        ui_sldBC.setMinimum((int) m_modelo.getBCMin());

        ui_sldGH.setMaximum((int) m_modelo.getGHMax());
        ui_sldGH.setMinimum((int) m_modelo.getGHMin());

        ui_sldBC.setValue((int) m_modelo.getBC());
        ui_sldGH.setValue((int) m_modelo.getGH());

        System.out.format("#### %d %d %n", ui_sldBC.getValue(), ui_sldGH.
                getValue());
        m_lienzo = new Lienzo();
        m_lienzo.setModeloAPantalla(0.8);
        m_lienzo.setOrigenPantalla(new Point(800, 500));
        ui_ContenedorLienzo.add(m_lienzo, BorderLayout.CENTER);

        redibuja();

    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ui_sldBC = new javax.swing.JSlider();
        ui_sldGH = new javax.swing.JSlider();
        ui_cmdSimula = new javax.swing.JButton();
        ui_ContenedorLienzo = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);

        jPanel1.setPreferredSize(new java.awt.Dimension(840, 150));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 0, 153));
        jLabel1.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphics/logo_64.png"))); // NOI18N
        jLabel1.setText("      SIMULADOR DE BRAZO v2019-09-29_21-12");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 930, -1));

        jLabel2.setText("Actuador BC:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel3.setText("Actuador GH:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, -1, -1));

        ui_sldBC.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ui_sldBCStateChanged(evt);
            }
        });
        jPanel1.add(ui_sldBC, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));

        ui_sldGH.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ui_sldGHStateChanged(evt);
            }
        });
        jPanel1.add(ui_sldGH, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, -1, -1));

        ui_cmdSimula.setText("Simula");
        ui_cmdSimula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ui_cmdSimulaActionPerformed(evt);
            }
        });
        jPanel1.add(ui_cmdSimula, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 110, -1, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        ui_ContenedorLienzo.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        ui_ContenedorLienzo.setLayout(new java.awt.BorderLayout());
        getContentPane().add(ui_ContenedorLienzo, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(952, 661));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ui_sldBCStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ui_sldBCStateChanged
        if (m_lienzo == null) {
            return;
        } // end if
        double l_BC = ui_sldBC.getValue();
        m_modelo.setBC(l_BC);
        redibuja();
    }//GEN-LAST:event_ui_sldBCStateChanged

    private void ui_sldGHStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ui_sldGHStateChanged
        if (m_lienzo == null) {
            return;
        } // end if
        double l_GH = ui_sldGH.getValue();
        m_modelo.setGH(l_GH);
        redibuja();
    }//GEN-LAST:event_ui_sldGHStateChanged

    private void ui_cmdSimulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ui_cmdSimulaActionPerformed

        simula();
    }//GEN-LAST:event_ui_cmdSimulaActionPerformed

    private void simula() {

        ui_cmdSimula.setEnabled(false);
        new Thread() {
            @Override
            public void run() {
                // Limpia la simulación anterior
                m_modelo.getSimulaciones().clear();

                double l_pasos = 50.0;
                for (int l_iBC = 0; l_iBC <= l_pasos; l_iBC++) {

                    double l_BC = ui_sldBC.getMinimum() + (ui_sldBC.getMaximum()
                            - ui_sldBC.getMinimum()) * l_iBC / l_pasos;

                    m_modelo.setBC(l_BC);
                    for (int l_iGH = 0; l_iGH <= l_pasos; l_iGH++) {
                        double l_GH = ui_sldGH.getMinimum() + (ui_sldGH.
                                getMaximum() - ui_sldGH.getMinimum()) * l_iGH
                                / l_pasos;

                        m_modelo.setGH(l_GH);

                        m_modelo.recalcula();

                        if (m_modelo.isRecalculoValido()) {

                            m_modelo.getSimulaciones().add(new Marcador(
                                    m_modelo.getE(), Color.GRAY));

                            m_lienzo.setDibujables(m_modelo.getDibujables());

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    ui_ContenedorLienzo.updateUI();
                                }
                            });
                        } // end if
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                        }
                    } // end for
                } // end for

                double l_BC = ui_sldBC.getValue();
                m_modelo.setBC(l_BC);
                double l_GH = ui_sldGH.getValue();
                m_modelo.setGH(l_GH);
                m_modelo.recalcula();
                if (m_modelo.isRecalculoValido()) {
                    m_lienzo.setDibujables(m_modelo.getDibujables());

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            ui_ContenedorLienzo.updateUI();
                        }
                    });
                } // end if
                ui_cmdSimula.setEnabled(true);

            }

        }.start();
    }

    private void redibuja() {
        m_modelo.recalcula();
        if (m_lienzo == null) {
            return;
        } // end if
        Color l_bgColor = (m_modelo.isRecalculoValido()) ? Color.WHITE
                : Color.PINK;
        m_lienzo.setBackground(l_bgColor);
        m_lienzo.setDibujables(m_modelo.getDibujables());

        ui_ContenedorLienzo.updateUI();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info
                    : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SimuladorBrazoMain.class.
                    getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SimuladorBrazoMain.class.
                    getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SimuladorBrazoMain.class.
                    getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SimuladorBrazoMain.class.
                    getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SimuladorBrazoMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel ui_ContenedorLienzo;
    private javax.swing.JButton ui_cmdSimula;
    private javax.swing.JSlider ui_sldBC;
    private javax.swing.JSlider ui_sldGH;
    // End of variables declaration//GEN-END:variables
}
