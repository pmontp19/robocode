/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robots;

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

    public void run() {
        if (getRoundNum() == 0) {
            setAdjustRadarForRobotTurn(true);
        }
        //turnRight(getDireccio(getBattleFieldWidth() / 2, getBattleFieldHeight() / 2));
        //ahead(Math.abs(getDistancia(getX(), getY(), getBattleFieldWidth() / 2, getBattleFieldHeight() / 2)));
        setTurnRadarRight(Double.POSITIVE_INFINITY);
        setTurnGunRight(270);
    }

    // helper dona la direccio a anar donat un punt
    public double getDireccio(double x, double y) {
        // arctangent https://es.wikipedia.org/wiki/Arcotangente_de_dos_par%C3%A1metros
        double angle = Math.atan2(x - getX(), y - getY());
        return Utils.normalRelativeAngle(angle - getHeading());
    }

    public double getDistancia(double x1, double y1, double x2, double y2) {
        // distancia entre dos punts arrel quadrada quadrats
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public double apuntarEnemic(ScannedRobotEvent e) {
        double angleGun = e.getBearing() + getHeading();
        return angleGun - getRadarHeading();
    }

    public void anarCentre() {
        turnRight(getDireccio(getBattleFieldWidth() / 2, getBattleFieldHeight() / 2));
        ahead(Math.abs(getDistancia(getX(), getY(), getBattleFieldWidth() / 2, getBattleFieldHeight() / 2)));
    }

    public boolean wallsAprop() {
        if (
                getX() <= marges
                || getY() <= marges
                || getX() >= getBattleFieldWidth() - marges
                || getY() >= getBattleFieldHeight() - marges
                ) {
            return true;
        } else {
            return false;
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        direccioRadar = -direccioRadar;
        // canviem la direccio per aproparnos i allunyar-nos
        /*if (wallsAprop()) {
            anarCentre();
            direccio = -direccio;
        }*/
        if (direccio == 1) {
            setTurnRight(e.getBearing() + 85);
        } else if (direccio == -1) {
            setTurnRight(e.getBearing() + 95);
        }
        setAhead(direccio * 100);

        // canviem la velocitat aleatoriament 
        if (Math.random() > .95) {
            setMaxVelocity((2 * Math.random()) + 10);
        }
        setTurnRadarRight(Double.POSITIVE_INFINITY * direccioRadar);
        double angleAbsolut = getHeading() + e.getBearing();
        double angleCano = Utils.normalRelativeAngleDegrees(angleAbsolut - getGunHeading());

        // limit cano https://robocode.sourceforge.io/docs/robocode/robocode/Rules.html#GUN_TURN_RATE
        if (Math.abs(angleCano) <= 4 && getEnergy() > 25) {
            setTurnGunRight(angleCano);
            dispara(e);
        } else {
            setTurnGunRight(angleCano);
        }
        //setTurnGunRight(apuntarEnemic(e)+90);
    }

    public void dispara(ScannedRobotEvent e) {
        if (rateEncerts > .5) {
            if (e.getVelocity() == 0) {
                setFire(3);
            } else {
                setFire(300 / e.getDistance());
            }
        } else {
            if (e.getDistance() > 200) {
                setFire(1);
            }
        }
    }

    public void aproparse(ScannedRobotEvent e) {
        setTurnRight(apuntarEnemic(e));
        setAhead(e.getDistance() - 50);
        execute();
    }

    public void onHitWall(HitWallEvent e) {
        direccio = -direccio;
        setBack(150 * direccio);
    }

    public void onRobotHit(HitRobotEvent e) {
        direccio = -direccio;
        setBack(150);
    }

    public void onHitBullet(HitByBulletEvent e) {
        direccio = -direccio;
        setAhead(100 * direccio);
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
