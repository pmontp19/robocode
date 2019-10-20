/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robots;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import robocode.*;
import robocode.util.Utils;

/**
 *
 * @author pere
 */
public class PropiAvancat extends AdvancedRobot {

    private int direccio = 1;
    private int direccioRadar = 1;
    private int bulletsMissed = 0;
    private int bulletsHit = 0;
    private double rateEncerts = 1;
    private final double marges = 20;
    private final int TrajectoriaMAXSIZE = 300;
    private List<Integer> trajectoria = new LinkedList<>();
    private boolean dummyMode = false;

    private void afegirTrajectoria(int bearing) {
        if (trajectoria.size() > TrajectoriaMAXSIZE) {
            trajectoria.remove(0);
        }
        trajectoria.add(bearing);
    }

    private boolean mateixaTrajectoria() {
        boolean heading = false;
        for (int i = 0; i < trajectoria.size() - 1; i++) {
            for (int k = i + 1; k < trajectoria.size(); k++) {
                if (trajectoria.get(i) == trajectoria.get(k)) {
                    heading = true;
                }
            }
        }
        return heading;
    }

    public void run() {
        setAdjustRadarForRobotTurn(true);
        //turnRight(getDireccio(getBattleFieldWidth() / 2, getBattleFieldHeight() / 2));
        //ahead(Math.abs(getDistancia(getX(), getY(), getBattleFieldWidth() / 2, getBattleFieldHeight() / 2)));
        setTurnRadarRight(Double.POSITIVE_INFINITY);
        setTurnGunRight(270);
    }

    // helper dona la direccio a anar donat un punt
    private double getDireccio(double x, double y) {
        // arctangent https://es.wikipedia.org/wiki/Arcotangente_de_dos_par%C3%A1metros
        double angle = Math.atan2(x - getX(), y - getY());
        return Utils.normalRelativeAngle(angle - getHeading());
    }

    private double getDistancia(double x1, double y1, double x2, double y2) {
        // distancia entre dos punts arrel quadrada quadrats
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    private double apuntarEnemic(ScannedRobotEvent e) {
        double angleGun = e.getBearing() + getHeading();
        return angleGun - getRadarHeading();
    }

    private void anarCentre() {
        setTurnRight(getDireccio(getBattleFieldWidth() / 2, getBattleFieldHeight() / 2));
        setAhead(Math.abs(getDistancia(getX(), getY(), getBattleFieldWidth() / 2, getBattleFieldHeight() / 2)));
    }

    private boolean wallsAprop() {
        if (getX() <= marges
                || getY() <= marges
                || getX() >= getBattleFieldWidth() - marges
                || getY() >= getBattleFieldHeight() - marges) {
            return true;
        } else {
            return false;
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        direccioRadar = -direccioRadar;
        double angleAbsolut = getHeading() + e.getBearing();
        afegirTrajectoria((int) e.getHeading());
        /*if (mateixaTrajectoria() && trajectoria.size() == TrajectoriaMAXSIZE && !dummyMode) {
         dummyMode = true;
         turnRight(0);
         }*/
        if (e.getName().toLowerCase().contains("Dummy".toLowerCase())) {
            dummyMode = true;
            turnRight(0);
        }
        setTurnRadarRight(Double.POSITIVE_INFINITY * direccioRadar);
        if (!dummyMode) {
            if (direccio == 1) {
                setTurnRight(e.getBearing() + 85);
            } else if (direccio == -1) {
                setTurnRight(e.getBearing() + 95);
            }
            setAhead(direccio * 100);
            double angleCano = Utils.normalRelativeAngleDegrees(angleAbsolut - getGunHeading());
            // limit cano https://robocode.sourceforge.io/docs/robocode/robocode/Rules.html#GUN_TURN_RATE
            // estalviem energia si >25
            if (Math.abs(angleCano) <= 4 && getEnergy() > 25) {
                if (e.getBearing() > 0) {
                    setTurnGunRight(angleCano + 0.5);
                } else {
                    setTurnGunRight(angleCano - 0.5);

                }
                dispara(e);
            } else {
                setTurnGunRight(angleCano);
            }
            //setTurnGunRight(apuntarEnemic(e)+90);
        } else {
            double angleCano = Utils.normalRelativeAngleDegrees(angleAbsolut - getGunHeading());
            if (e.getHeading() > 0) {
                setTurnGunRight(angleCano + 9);
            } else {
                setTurnGunRight(angleCano - 9);
            }
            if (getGunHeat() == 0) {
                fire(300 / e.getDistance());
            }
        }
    }

    public void dispara(ScannedRobotEvent e) {
        // si estem fallant molt disparem només aprop
        if (rateEncerts > .1) {
            if (e.getVelocity() == 0) {
                setFire(3);
            } else {
                setFire(300 / e.getDistance());
            }
        } else {
            if (e.getDistance() < 300) {
                setFire(2);
            }
        }
        //out.println(rateEncerts);
    }

    public void aproparse(ScannedRobotEvent e) {
        setTurnRight(apuntarEnemic(e));
        setAhead(e.getDistance() - 50);
        execute();
    }

    public void onHitWall(HitWallEvent e) {
        direccio = -direccio;
        setTurnRight(getDireccio(getBattleFieldWidth() / 2, getBattleFieldHeight() / 2));
        setBack(150 * direccio);
    }

    public void onRobotHit(HitRobotEvent e) {
        direccio = -direccio;
        setBack(150);
    }

    public void onHitBullet(HitByBulletEvent e) {
        setAhead(100 * -direccio);
    }

    public void onBulletMissed(BulletMissedEvent event) {
        bulletsMissed++;
        rateEncerts = bulletsHit / (bulletsMissed + bulletsHit);
    }

    public void onBulletHit(BulletHitEvent event) {
        bulletsHit++;
        rateEncerts = bulletsHit / (bulletsMissed + bulletsHit);
    }

}
