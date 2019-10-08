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
public class DummyRobot extends Robot {
    
    @Override
    public void run () {
        turnLeft (getHeading());
        while (true)  {
            ahead(500);
            turnRight(90);
        }
    }
    
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        fire(1);
    }
    
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // turnLeft(180);
    }
}
