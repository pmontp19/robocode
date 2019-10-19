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
public class PropiAvancat extends AdvancedRobot {
    private Enemic enemic = new Enemic();
    
    public void run() {
        setAdjustRadarForGunTurn(true);
        setAdjustGunForRobotTurn(true);
        while (true) {
            setTurnRadarRight(25);
            execute();
        }
    }
    
    public void onScannedRobot(ScannedRobotEvent e) {
        enemic.update(e);
        setTurnGunRight(getHeading() - getGunHeading() + enemic.getBearing());  
        execute();
        fire(1);
    }
}
