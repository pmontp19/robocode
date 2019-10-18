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
    
    /* 
        - Quan el combat el de 1v1,  el robot divideix l escenari en 4 quadrants i juga al voltant de 2. Quan rep una bala canvia de quadrant (si jugava amb el q1 i q2, ara ho fa amb q2 i q3 i aixi)
        - La classe te un atribut per indicar a quins quadrants ha de jugar
        - Si xoca amb un item tomba el cano fins trobar un tanc i dispara.
        - En el terreny de desplaçament del tanc, el cano i el radar es van desplaçant entre l'angle 45º i 135º (fent 90º)
            - si detecta a algu molt a prop (a melee) dispara a maxima potencia
            - si detecta algu entre distancia maxima tamany_mapa/2 i tamany_mapa/3 dispara a potencia 1
            - si detectaa algu que no esta just al costat i esta mes a prop de tamany_mapa/3 dispara a potencia 2
            - en qualsevol altre cas no dispara
        - quant quedin sigui un battle royale el robot jugara donant voltes al voltant del mapa
    */
    int Q_de_joc; 
    public void goCorner12(double x, double y){ 
        /* Pre: x i y són les coordenades del mapa
        /* Post: El robot es col.loca a la meitat superior del mapa. Un cop col.locat, 
           es mou lateralment entre el primer i segon quadrant
           Si ja ho està només es mou lateralment  
           Durant el desplaçament va girant el cano i el radar en busca d'enemics
        */
       if (y/2 >getY()&& x/2 >getX() ){ //si esta al 3r quadrant
            turnLeft(getHeading()+0);
            turnRight(90);
            while (getX() < 3*x/4) ahead(100);
            turnLeft(90);
            while (getY() < 3*y/4) ahead(100);
        }
       else if (y/2 >getY()&& x/2 <getX() ){ //si esta al 4rt quadrant
            turnLeft(getHeading()+0);
            while (getY() < 3*y/4) ahead(100);
        }
       // si ja esta a Q1 o Q2 o ja s ha colocat, que es mogui lateralment
        turnLeft(getHeading()+270);
        Boolean trobat = true;
            while ( getX() <3*x/4){
                ahead(100);
                turnGunLeft(15);
            }
            while (getX()>x/4){
                back(100);
                turnGunRight(15);
            }  
    }
    public void goCorner23(double x, double y){ //ajusta el robot en el quadrant de joc 1 i 2
        /* Pre: x i y són les coordenades del mapa
        /* Post:El robot es col.loca a la meitat esquerra del mapa. Un cop està en posicio
           es mou verticalemnt entre el segon i tercer quadrant
           Si el robot ja està en posició, només es mou verticalment
           Durant el desplaçament va girant el cano i el radar en busca d'enemics
        */
        if (y/2 <getY()&& x/2 <getX() ){ //si esta al 1r quadrant
            turnLeft(getHeading()+0);
            while (getY() > y/4) back(100);
            turnLeft(90);
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
            
        while ( getY() <3*y/4){
            ahead(100);
            turnGunLeft(15);
        }
        while (getY()>y/4){
            back(100);
            turnGunRight(15);
        }        
    }
    public void goCorner34(double x, double y){
        /* Pre: x i y són les coordenades del mapa
        /* Post: El robot es col.loca a la meitat inferior del mapa. Un cop col.locat, 
           es mou lateralment entre el tercer i quart quadrant
           Si ja ho està només es mou lateralment  
           Durant el desplaçament va girant el cano i el radar en busca d'enemics 
        */
       if (y/2 <getY()&& x/2 >getX() ){ //si esta al 2r quadrant
            turnLeft(getHeading()+0);
            while (getY() < 3*y/4) back(198);
            turnRight(90);
        }
       else if (y/2 <getY()&& x/2 <getX() ){ //si esta al 1rt quadrant
            turnLeft(getHeading()+0);
            while (getY() < 3*y/4) back(175);
            turnRight(90);
        }
       // si ja esta a Q3 o Q4 o ja s ha colocat, que es mogui lateralment
        turnLeft(getHeading());
        turnRight(90);
        Boolean trobat = true;
            while ( getX() <5*x/6){
                ahead(175);
                turnGunLeft(15);
            }
            while (getX()>x/6){
                back(175);
                turnGunRight(15);
            }  
    }
    public void goCorner14(double x, double y){ //ajusta el robot en el quadrant de joc 1 i 2
        /* Pre: x i y són les coordenades del mapa
        /* Post:El robot es col.loca a la meitat dreta del mapa. Un cop col.locat, 
           es mou verticalment entre el quart i priemr quadrant
           Si ja ho està només es mou lateralment  
           Durant el desplaçament va girant el cano i el radar en busca d'enemics
        */
        if (y/2 <getY()&& x/2 >getX() ){ //si esta al 2r quadrant
            turnLeft(getHeading()+90);
            while (getX() < 3*x/4) back(100);  
            turnLeft(getHeading()+0);        
        }
        else if (y/2 >getY()&& x/2 >getX() ){ //si esta al 3rt quadrant
            turnLeft(getHeading()+90);
            while (getX() <3*x/4) back(100);
            turnLeft(getHeading()+0);
        }
        // si ja esta a Q1 o Q4 o ja s ha colocat, que es mogui verticalment
        turnLeft(getHeading()+0);
        Boolean trobat = true;
        turnGunLeft(getHeading()+275);
            while ( getY() <3*y/4){
                ahead(100);
                turnGunLeft(15);
            }
            while (getY()>y/4){
                back(100);
                turnGunRight(15);
            }            
    }
    public void coorre(double x, double y){
        /* Pre: x i y són les coordenades del mapa i el Robot esta situat en el 1r quadrant
        /* Post: El robot dona una volta al voltant del mapa
        */
            turnLeft(getHeading()+90);
  
            while (getX()>x/5){
                ahead(168);
                turnGunRight(15);
            }
            turnLeft(90);
            while ( getY() >y/5){
                ahead(168);
                turnGunRight(15);
            }
            turnLeft(90);
            while (getX()<4*x/5){
                ahead(168);
                turnGunLeft(15);
            }
            turnLeft(90);
            while ( getY() >4*  y/5){
                ahead(168);
                turnGunRight(15);
            }
            turnLeft(90);
    }
    public void run () {
        /* Pre: Cert
        /* Post: el robot corre al voltant del mapa si el combat es un battle royale. Sino utilitza una estrategia de quadrants 
           i quan el robot li queda menys del 40% de la vida, segueix l'estrategia del battle royale
        */
        double y = getBattleFieldHeight();
        double x =getBattleFieldWidth();
        Q_de_joc =0;
        double vida;
        if(getOthers()==1)
            while(true ){
                vida= getEnergy();
                if (vida<40.0){
                    goCorner12(x,y);
                    coorre(x, y);
                }
                if (Q_de_joc==0){
                    goCorner12(x,y);}
                else if (Q_de_joc==1){
                    goCorner23(x,y);}
                else if (Q_de_joc==2){
                    goCorner34(x,y);}
                else {
                    goCorner14(x,y);}
            }
        else {
           goCorner12(x,y);
           while (true) {
                coorre(x,y);}
        }
    }
    public void onHitWall(HitWallEvent e){
        /* Pre: 
        /* Post: el robot gira 90º a l'esquerra i el cano fan una volta */
        turnLeft(90);
        turnGunLeft(getHeading()+360);
    }
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        /* Pre: 
        /* Post: el robot gira el cano 15º a l'esquerra i en funcio de la distancia del enemic dispara una bala de diferent potencia.
           Si l'enemic esta a menys de 5 pixels dispara a maxima potencia, si esta a menys de 50, amb la minima, i en cas els alters casos dispara amb potencia mitja
        */
        turnGunLeft(15);
        if (e.getDistance()<5){
            fire(3);
        }
        if (e.getDistance()< 50 && e.getDistance() >5){
            fire(1);
        }
        else fire(2);
    }
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        /* Pre:
        /* Post: canvia el valor del quadrant de joc
        */         
        if (Q_de_joc==0) this.Q_de_joc=1;
        else if (Q_de_joc==1) this.Q_de_joc=2;
        else if (Q_de_joc==2) this.Q_de_joc=3;
        else this.Q_de_joc=0;    
    }
}