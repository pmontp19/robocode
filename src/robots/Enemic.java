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
public class Enemic {
    private double bearing;
    private double distance;
    private double energy;
    private double heading;
    private String name;
    private double velocity;

    public Enemic(ScannedRobotEvent e) {
        bearing = e.getBearing();
        distance = e.getDistance();
        energy = e.getEnergy();
        heading = e.getHeading();
        name = e.getName();
        velocity = e.getVelocity();
    }
    
    public Enemic() {
        name = null;
    }
    
    public double getBearing() {
        return bearing;
    }

    public double getDistance() {
        return distance;
    }

    public double getEnergy() {
        return energy;
    }

    public double getHeading() {
        return heading;
    }

    public String getName() {
        return name;
    }

    public double getVelocity() {
        return velocity;
    }
    
    public void update(ScannedRobotEvent e) {
        bearing = e.getBearing();
        distance = e.getDistance();
        energy = e.getEnergy();
        heading = e.getHeading();
        name = e.getName();
        velocity = e.getVelocity();
    }
}
