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
    
    /* -PER CERT, donam un num de telefeon/correu (o envia un Whats a 608 96 13 96) per mantenir contacte
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
    int Q_de_joc; 
    public void goCorner12(double x, double y){ //ajusta el robot en el quadrant de joc 1 i 2
        
        /* 
            Si esta per defecte als Q1 i Q2 nomes es mou
        */
        
       if (y/2 >getY()&& x/2 >getX() ){ //si esta al 3r quadrant
            turnLeft(getHeading()+0);
            turnRight(90);
            while (getX() < 3*x/4) ahead(100);
            turnLeft(getHeading()+0);
            while (getY() < 3*y/4) ahead(100);
        }
       else if (y/2 >getY()&& x/2 <getX() ){ //si esta al 4rt quadrant
            turnLeft(getHeading()+0);
            
            while (getY() < 3*y/4) ahead(100);
        }
       
       // si ja esta a Q1 o Q2 o ja s ha colocat, que es mogui lateralment
        turnLeft(getHeading()+270);
        Boolean trobat = true;
        //while(true){
            
            while ( getX() <3*x/4){
                ahead(100);
                turnGunLeft(15);
            }
            while (getX()>x/4){
                back(100);
                turnGunRight(15);
            }
   
        //}
           
           
           
        
    }
    public void goCorner23(double x, double y){ //ajusta el robot en el quadrant de joc 1 i 2
        
        /* 
            Si esta per defecte als Q2i Q3 nomes es mou
        */
        
        if (y/2 <getY()&& x/2 <getX() ){ //si esta al 1r quadrant
            turnLeft(getHeading()+0);
            //turnRight(90);
            while (getY() > y/4) back(100);
            
            turnLeft(90);
            //turnRight(getHeading()+270);
            while (getX() > x/4) ahead(100);
            turnRight(getHeading()+0);
        }
        else if (y/2 >getY()&& x/2 <getX() ){ //si esta al 4rt quadrant
            turnLeft(getHeading()+90);
            
            while (getX() >x/4) ahead(100);
            turnRight(90);
        }
       
        // si ja esta a Q3 o Q2 o ja s ha colocat a Q3, que es mogui verticalment
        turnLeft(getHeading()+0);
        Boolean trobat = true;
        turnGunRight(getHeading()+285);
       // while(true){
            
            while ( getY() <3*y/4){
                ahead(100);
                turnGunLeft(15);
            }
            while (getY()>y/4){
                back(100);
                turnGunRight(15);
            }
            
       // }   
                
    }
    
    public void goCorner14(double x, double y){ //ajusta el robot en el quadrant de joc 1 i 2
        
        /* 
            Si esta per defecte als Q2i Q3 nomes es mou
        */
        
        if (y/2 <getY()&& x/2 >getX() ){ //si esta al 2r quadrant
            turnLeft(getHeading()+90);
            //turnRight(90);
            while (getX() < 3*x/4) back(100);
            
            turnLeft(getHeading()+0);
            
        }
        else if (y/2 >getY()&& x/2 >getX() ){ //si esta al 3rt quadrant
            turnLeft(getHeading()+90);
            
            while (getX() <3*x/4) back(100);
            turnLeft(getHeading()+0);
        }
       
        // si ja esta a Q3 o Q2 o ja s ha colocat a Q3, que es mogui verticalment
        turnLeft(getHeading()+0);
        Boolean trobat = true;
        turnGunLeft(getHeading()+275);
       // while(true){
            
            while ( getY() <3*y/4){
                ahead(100);
                turnGunLeft(15);
            }
            while (getY()>y/4){
                back(100);
                turnGunRight(15);
            }
            
        //}   
                
    }
    public void run () {
        
        double y = getBattleFieldHeight();
        double x =getBattleFieldWidth();
        Q_de_joc =0; 
        while(true){
            if (Q_de_joc==0){
                goCorner12(x,y);}
            else if (Q_de_joc==1){
                
                goCorner23(x,y);}
            else if (Q_de_joc==2){
                goCorner23(x,y);}
            else {

                goCorner14(x,y);}
        }
        
       //goCorner12(x,y);  
        
               /* turnLeft(0+getHeading());
                //double y =getBattleFieldHeight();
                Boolean trobat = true;
                while(true){

                    if (y/4>getY()){
                        trobat =true;
                    }
                    if(3*y/4 <getY()) trobat = false;

                    if (trobat){
                        ahead(75);
                    }
                    else {
                        back(75);
                    }
                    turnGunLeft(10);
                }*/
     
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
        
        /*  if (getX()< getBattleFieldWidth()*3/4 ){
            turnGunLeft(12);
        }
        else if (getX()> getBattleFieldWidth()*3/4 ){
            turnGunRight(12);
        }
        */
        turnGunRight(15);
        if (e.getDistance()<5){
           // turnGunLeft (getHeading());
            fire(3);
        }
        if (e.getDistance()< 50 && e.getDistance() >5){
            fire(1);
        }
        else fire(3);
    }
  
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // turnLeft(180);
        double y = getBattleFieldHeight();
        double x =getBattleFieldWidth();
        
        if (Q_de_joc==0) this.Q_de_joc=1;
            
        else if (Q_de_joc==1) this.Q_de_joc=2;
        else if (Q_de_joc==2) this.Q_de_joc=3;
          
        else this.Q_de_joc=0;
            
    }
}