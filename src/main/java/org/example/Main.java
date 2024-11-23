package org.example;

import jakarta.persistence.*;
import org.example.entities.Student;
import org.example.persistence.MyOwnPersistenceInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {

        Map<String, String> propertyMap = new HashMap<>();
        propertyMap.put("hibernate.show_sql", "true");

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new MyOwnPersistenceInfo(),propertyMap);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            //example 1 - retreive all values from student table
            /*

            String query1 = "select s from Student s";
            TypedQuery<Student> query = em.createQuery(query1, Student.class);
            query.getResultList().forEach(System.out::println);

             */

            //example 2 - retrieve max cgpa of all students
            /*

            String query2 = "select max(s.cgpa) from Student s";
            TypedQuery<Double> query = em.createQuery(query2, Double.class);
            System.out.println(query.getSingleResult());

             */

            //example 3 -count number of students
            /*

            String query3 = "select count(*) from Student s";
            TypedQuery<Long> query = em.createQuery(query2, Long.class);
            System.out.println(query.getSingleResult());

             */

            //example 4 : avg cgpa of students from each dept

            /*
            String query4 = "select s.department,avg(s.cgpa) from Student s group by department";
            TypedQuery<Object[]> query = em.createQuery(query4, Object[].class);
            query.getResultList().forEach(objects ->
                    System.out.println(objects[0]+ " , "+objects[1]));

             */

            //example 5 : getSungleResult() method issue
            /*

            String query5 = "select s from Student s where s.name like 'pavani' ";
            TypedQuery<Student> query = em.createQuery(query5, Student.class);
            try {
                System.out.println(query.getSingleResult());
            }catch(NoResultException e){
                System.out.println("no result found");
            }

             */

            //example 6 : query by parameters

            /*

            String query6 = """
                    select s from Student s
                    where s.department=:department and s.cgpa>:cg
                    """;
            TypedQuery<Student> query = em.createQuery(query6, Student.class);
            query.setParameter("department","cse");
            query.setParameter("cg",9);
            query.getResultList().forEach(System.out::println);

             */


            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
}