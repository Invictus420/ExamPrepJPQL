/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpqldemo;

import entity.Student;
import javax.persistence.Persistence;

/**
 *
 * @author Alex
 */
public class Jpqldemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Persistence.generateSchema("pu", null);
        
        Controller c = new Controller();
        Student s = c.createStud("nigger", "faggot");

        System.out.println(c.findStuds().toString());
        System.out.println(c.findJan().toString());
        System.out.println(c.findOlsen().toString());
        System.out.println(c.findSP(2));
        System.out.println(c.findSP(1));
        System.out.println(c.findTotScore());
        System.out.println(c.findBest());
        System.out.println(c.findWorst());
        c.addSP(s, "yolo", 42, 0);
    }
    
}
