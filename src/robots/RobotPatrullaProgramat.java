/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robots;
import robocode.*;
/**
 *
 * @author pere
 */
public class RobotPatrullaProgramat extends AdvancedRobot {

    public void run() {
        turnLeft(getHeading());
        while (true) {
            setTurnRight(10000);
            setTurnGunRight(20);
            ahead(2000);
            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        fire(1);
    }

    public void onHitByBullet(HitByBulletEvent e) {
        turnLeft(180);
    }
}
