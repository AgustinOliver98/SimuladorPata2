package ar.edu.unsta.robotteam.mate;

import java.awt.Point;

/**
 * Herramientas para el cálculo matemático y físico
 *
 * @author gustavo
 */
public class Herramientas {

    public enum RootIndex {
        ROOT_FIRST,
        ROOT_SECOND,
        ROOT_AUTO
    }

    /**
     * Encuentra la solución a la intersección entre dos circunferencias, A y B.
     * Devuelve sólo una raíz, dependiendo del parámetro p_rootIndex.
     *
     * Si la distancia entre los centros es mayor que la suma de los radios,
     * no puede haber intersección. En ese caso devuelve null;
     *
     * Si p_rootIndex == ROOT_AUTO, aplica el siguiente criterio:
     * si el centro B está a la derecha del eje Y, devuelve la raíz más alta; si
     * no, la más baja·
     *
     * @param p_centerA
     * @param p_radiusA
     * @param p_centerB
     * @param p_radiusB
     * @return
     */
    public static Point.Double solveQuadEq(Point.Double p_centerA,
            double p_radiusA, Point.Double p_centerB, double p_radiusB,
            RootIndex p_rootIndex) {

        /*
        System.out.format("solveQuadEq: A(%f,%f r=%f)   B(%f,%f r=%f)%n",
                p_centerA.x, p_centerA.y, p_radiusA, p_centerB.x,
                p_centerB.y, p_radiusB);
         */
        Point.Double l_newCenterB = new Point.Double(p_centerB.x - p_centerA.x,
                p_centerB.y - p_centerA.y);

        Point.Double l_root = solveQuadEq(p_radiusA, l_newCenterB, p_radiusB,
                p_rootIndex);
        
        if (l_root == null) {
            // Sin solución
            return null;
        } // end if
        l_root.x += p_centerA.x;
        l_root.y += p_centerA.y;

        return l_root;
    }

    /**
     * Encuentra la solución a la intersección entre dos circunferencias, A y B.
     * La circunferencia A está centrada en el origen.
     * Devuelve sólo una raíz, dependiendo del parámetro p_rootIndex.
     *
     * Si la distancia entre los centros es mayor que la suma de los radios,
     * no puede haber intersección. En ese caso devuelve null;
     *
     * Si p_rootIndex == ROOT_AUTO, aplica el siguiente criterio:
     * si el centro B está a la derecha del eje Y, devuelve la raíz más alta; si
     * no, la más baja·
     *
     *
     * @param p_radiusA
     * @param p_centerB
     * @param p_radiusB
     * @return
     */
    private static Point.Double solveQuadEq(double p_radiusA,
            Point.Double p_centerB, double p_radiusB, RootIndex p_rootIndex) {

        // Verifica que la distancia sea suficiente
        double l_distanceAB = Math.hypot(p_centerB.x, p_centerB.y);
        if (l_distanceAB > p_radiusA + p_radiusB) {
            // Radios insuficientes para la distancia entre centros.
            return null;
        } // end if

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

        switch (p_rootIndex) {
            case ROOT_AUTO: {
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
            case ROOT_FIRST: {
                return new Point.Double(l_x1, l_y1);
            }
            case ROOT_SECOND: {
                return new Point.Double(l_x2, l_y2);

            }
            default: {
                return null;
            }
        }
    }

}
