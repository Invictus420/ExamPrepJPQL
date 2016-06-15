/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpqldemo;

import entity.Student;
import entity.Studypoint;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Alex
 */
public class Controller {
    private EntityManagerFactory emf;
    private EntityManager em;

    public Controller() {
        this.emf = Persistence.createEntityManagerFactory("pu");
        this.em = emf.createEntityManager();
    }
    public List<Student> findStuds(){
    Query q = em.createNamedQuery("Student.findAll");
    return q.getResultList();
    }
    
    public List<Student> findJan(){
        Query q = em.createNamedQuery("Student.findByFirstname");
        q.setParameter("firstname", "Jan");
        return q.getResultList();
    }
    
    public List<Student> findOlsen(){
        Query q = em.createNamedQuery("Student.findByLastname");
        q.setParameter("lastname", "Olsen");
        return q.getResultList();
    }
    
    public int findSP(int id){
        Query q = em.createNamedQuery("Student.findById");
        q.setParameter("id", id);
        Student s = (Student)q.getSingleResult();
        int sum = 0;
        for (Studypoint sp : s.getStudypointCollection()) {
            sum = sum + sp.getScore();
        }
        
        return sum;
    }
    
    public int findTotScore(){
        Query q = em.createNamedQuery("Studypoint.findAll");
        List<Studypoint> sp1 = q.getResultList();
        int sum =0;
        for (Studypoint sp : sp1) {
            sum =  sum + sp.getScore();
        }
        return sum;
    }
    
    public List<Student> findBest(){
        List<Student> studs = findStuds();
        List<Student> best = new ArrayList();
        for (Student stud : studs) {
            if(best.isEmpty())
                best.add(stud);
            else if(findSP(stud.getId()) > findSP(best.get(0).getId())){
                best.clear();
                best.add(stud);
            }
            else if(findSP(stud.getId()) == findSP(best.get(0).getId())){
                best.add(stud);
            }
        }
        return best;
    }
    
    public List<Student> findWorst(){
        List<Student> studs = findStuds();
        List<Student> worst = new ArrayList();
        for (Student stud : studs) {
            if(worst.isEmpty())
                worst.add(stud);
            else if(findSP(stud.getId()) < findSP(worst.get(0).getId())){
                worst.clear();
                worst.add(stud);
            }
            else if(findSP(stud.getId()) == findSP(worst.get(0).getId())){
                worst.add(stud);
            }
        }
        return worst;
    }
    
    public Student createStud(String fn, String ln){
        Student s = new Student();
        s.setFirstname(fn);
        s.setLastname(ln);
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
        return s;
    }
    
    public void addSP(Student s, String des, int max, int score){
        s.addSP(des, max, score);
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
    }
}
    
