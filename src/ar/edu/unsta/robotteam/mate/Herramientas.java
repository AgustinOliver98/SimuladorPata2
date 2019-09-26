package ar.edu.unsta.robotteam.mate;

import java.awt.Point;

/**
 *
 * @author gustavo
 */
public class Herramientas {

    /**
     * Encuentra la solución a la intersección entre dos circunferencias, A y B.
     * Devuelve sólo una raíz: si el centro B está a la derecha del centro A,
     * devuelve la raíz más alta; si no, la más baja
     *
     * @param p_centerA
     * @param p_radiusA
     * @param p_centerB
     * @param p_radiusB
     * @return
     */
    public static Point.Double solveQuadEq(Point.Double p_centerA,
            double p_radiusA, Point.Double p_centerB, double p_radiusB) {

        System.out.format("solveQuadEq: A(%f,%f r=%f)   B(%f,%f r=%f)%n",
                p_centerA.x, p_centerA.y, p_radiusA, p_centerB.x,
                p_centerB.y, p_radiusB);

        Point.Double l_newCenterB = new Point.Double(p_centerB.x - p_centerA.x,
                p_centerB.y - p_centerA.y);

        Point.Double l_root = solveQuadEq(p_radiusA, l_newCenterB, p_radiusB);
        l_root.x += p_centerA.x;
        l_root.y += p_centerA.y;

        return l_root;
    }

    /**
     * Encuentra la solución a la intersección entre dos circunferencias, A y B.
     * La circunferencia A está centrada en el origen. Devuelve sólo una raíz:
     * si el centro B está a la derecha del eje Y, devuelve la raíz más alta; si
     * no, la más baja
     *
     * @param p_radiusA
     * @param p_centerB
     * @param p_radiusB
     * @return
     */
    private static Point.Double solveQuadEq(double p_radiusA,
            Point.Double p_centerB, double p_radiusB) {

        double l_ra2 = p_radiusA * p_radiusA;
        double l_rb2 = p_radiusB * p_radiusB;
        double l_xb2 = p_centerB.x * p_centerB.x;
        double l_yb2 = p_centerB.y * p_centerB.y;
        double l_ra_min_rb2 = (p_radiusA - p_radiusB) * (p_radiusA - p_radiusB);
        double l_ra_plus_rb2 = (p_radiusA + p_radiusB) * (p_radiusA + p_radiusB);

        double l_x1 = Double.NaN;
        double l_y1 = Double.NaN;
        double l_x2 = Double.NaN;
        double l_y2 = Double.NaN;

        double l_r = 2 * (l_xb2 + l_yb2);
        double l_u = 2 * p_centerB.y * (l_xb2 + l_yb2);

        if (l_r != 0.0) {
            double l_p = p_centerB.x * (l_ra2 - l_rb2 + l_xb2 + l_yb2);
            double l_q = Math.sqrt(-l_yb2 * (-l_ra_min_rb2 + l_xb2 + l_yb2)
                    * (-l_ra_plus_rb2 + l_xb2 + l_yb2));

            l_x1 = (l_p - l_q) / l_r;
            l_x2 = (l_p + l_q) / l_r;
        } // end if

        if (l_u != 0.0) {
            double l_s = l_ra2 * l_yb2 + l_yb2 * (-l_rb2 + l_xb2 + l_yb2);
            double l_t = p_centerB.x * Math.sqrt(-l_yb2 * (-l_ra_min_rb2 + l_xb2
                    + l_yb2) * (-l_ra_plus_rb2 + l_xb2 + l_yb2));

            l_y1 = (l_s + l_t) / l_u;
            l_y2 = (l_s - l_t) / l_u;
        } // end if

        if (p_centerB.x >= 0) {
            // El segundo círculo está a la derecha. Toma la raíz más alta
            if (l_y1 >= l_y2) {
                return new Point.Double(l_x1, l_y1);
            } else {
                return new Point.Double(l_x2, l_y2);
            } // end if
        } else // El segundo círculo está a la izquierda. Toma la raíz más baja
        {
            if (l_y1 <= l_y2) {
                return new Point.Double(l_x1, l_y1);
            } else {
                return new Point.Double(l_x2, l_y2);
            } // end if // end if
        }
    }

}
