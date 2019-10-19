/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robots;
import robocode.*;
import robocode.util.Utils.*;

/**
 *
 * @author pere
 */
public class RobotPropi extends Robot {
    private Enemic enemic = new Enemic();
    private double angleGir;
    public void run() {
        turnLeft(getHeading());
        setAdjustGunForRobotTurn(true);
        while (true) {
            turnGunRight(10);
            //ahead(50);
        }
    }
    
    public void onHitRobot(HitRobotEvent e) {
        turnRight(e.getBearing());
        turnGunRight(getHeading()-getGunHeading());
        back(10);
        fire(1);
        back(40);
    }
    
    public void onScannedRobot(ScannedRobotEvent e) {
        enemic.update(e);
        turnRadarRight(getHeading() - getGunHeading() + enemic.getBearing());
        //turnGunRight(getHeading() - getGunHeading() + enemic.getBearing());
        
        // si esta massa lluny, acostar-se
        if (enemic.getDistance() > 200) {
            angleGir = robocode.util.Utils.normalRelativeAngleDegrees(enemic.getBearing() + (getHeading() - getRadarHeading()));
            turnGunRight(angleGir);
            turnRight(angleGir);
            ahead(enemic.getDistance() - 180);
            return;
        } else {
            turnGunRight(robocode.util.Utils.normalRelativeAngleDegrees(enemic.getBearing() + (getHeading() - getRadarHeading())));
            fire(3);
        }
        
       
    }
}
