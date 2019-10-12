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
public class Basic_per_Quadrants extends Robot {
    
    /* -PER CERT, donam un num de telefeon/correu (o envia un Whats a 608 69 13 96) per mantenir contacte
        - El robot divideix l escenari en 4 quadrants i juga al voltant de 2. Quan rep una bala canvia de quadrant (si jugava amb el q1 i q2, ara ho fa amb q2 i q3) rapidament
        - Si xoca amb un item tomba el cano fins trobar un tanc i dispara.
        - En el terreny de desplaçament del tanc, el cano i el radar es van desplaçant entre l'angle 45º i 135º (fent 90º)
            - si detecta a algu molt a prop (a melee) dispara a maxima potencia
            - si detecta algu entre distancia maxima tamany_mapa/2 i tamany_mapa/3 dispara a potencia 1
            - si detectaa algu que no esta just al costat i esta mes a prop de tamany_mapa/3 dispara a potencia 2
            - en qualsevol altre cas no dispara
        - quant quedin 2 rivals nomes jugara en el Q ja ja esta i nomes augmentara o reduira velocitatt al ser impactat
        - si fallem la bala, s augmentara en 5º l angle en funcio de la obertura i es tornara disparar
    */
    
    public void goCorner(){ //ajusta el robot en el quadrant de joc
      /* double x =getBattleFieldWidth();
       double y = getBattleFieldHeight();
       turnRight(getHeading());
       if (x/2 < getWidth() && y/2 > getHeight())//4rt quadratn
       {
           while(getHeight() < y/6){
               back(50);
           }
           turnLeft(270);
           while (getWidth() < x/6){
               ahead(50);
           }
         turnRight(getHeading());
         fire(3);
         fire(3);
       }
       else if (x/2 < getWidth() && y/2 < getHeight())//1rt quadratn
       {
           while(getHeight() < y/6){
               back(50);
           }
           turnLeft(270);
           while (getWidth() < x/6){
               ahead(50);
           }
         turnRight(0);
         turnLeft(90);
         turnLeft(0);
         fire(3);
         fire(3);
       }
       
    */    
    }
    public void run () {
      
        turnLeft(0+getHeading());
        //double y =getBattleFieldHeight();
        double y = getBattleFieldHeight()/4;
        Boolean trobat = true;
        while(true){
           
            if (y>getY()){
                trobat =true;
            }
            if(3*y <getY()) trobat = false;
            
            
            if (trobat){
                ahead(75);
            }
            else {
                back(75);
            }
        }
     
    }
    public void onHitWall(HitWallEvent e){
        //turnRight(getHeading());
        //back(350);
        //turnRight(90);
            
            /*if (getX()<getBattleFieldWidth()/2){
               if (getY()<getBattleFieldHeight()/2 ){
                   turnRight(getHeading());
                   ahead(50);
                }
            }*/
    }
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        
        /*if (e.getDistance()<5){
           // turnGunLeft (getHeading());
            fire(3);
        }
        if (e.getDistance()< 50 && e.getDistance() >5){
            fire(1);
        }
        else fire(2);*/
    }
  
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // turnLeft(180);
    }
}