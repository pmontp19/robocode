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
    private Enemic enemic = new Enemic();
    private int direccio = 1;
    private int direccioRadar = 1;
    
    public void run() {
        if (getRoundNum() == 0) {
            setAdjustRadarForRobotTurn(true);
        }
        turnRight(getDireccio(getBattleFieldWidth()/2, getBattleFieldHeight()/2));
        ahead(getDistancia(getX(), getY(), getBattleFieldWidth()/2, getBattleFieldHeight()/2));
        //setTurnRight(getDireccio(getBattleFieldWidth()/2, getBattleFieldHeight()/2));
        //setAhead(getDistancia(getX(), getY(), getBattleFieldWidth()/2, getBattleFieldHeight()/2));
        setTurnRadarRight(Double.POSITIVE_INFINITY);
        setTurnGunRight(270);
    }
    
    // helper dona la direccio a anar donat un punt
    public double getDireccio(double x, double y) {
        // arctangent https://es.wikipedia.org/wiki/Arcotangente_de_dos_par%C3%A1metros
        double angle = Math.atan2(x-getX(), y-getY());
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
    
    public void onScannedRobot(ScannedRobotEvent e) {
        enemic.update(e);
        direccioRadar = -direccioRadar;
        setTurnRight(e.getBearing()+90);
        apuntarEnemic(e);
        if(enemic.getVelocity() == 0) {
            setFire(3);
        } else {
            setFire(300/enemic.getDistance());
        }
        setTurnRadarRight(Double.POSITIVE_INFINITY*direccioRadar);
        setAhead(direccio * 100);
    }
    
    public void disparaAprop() {
        setFire(300/enemic.getDistance());
        execute();
    }
    
    public void correr() {
        if (getVelocity() == 0) {
            direccio = -direccio;
        }
        setTurnLeft(enemic.getBearing() - 90);
	setAhead(1000 * direccio);
    }
    
    public void onHitWall(HitWallEvent e) {
        direccio = -direccio;
        setBack(150 * direccio);
    }
    
    public void onRobotHit(HitRobotEvent e) {
        direccio = -direccio;
    }
    
    public void onHitBullet(HitByBulletEvent e) {
        direccio = -direccio;
        setAhead(100 * direccio);
    }
}
