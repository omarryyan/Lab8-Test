package org.example;
import java.util.List;
import java.util.Random;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.example.entities.Car;
import org.example.entities.Image;
import org.example.entities.Person;
import org.example.entities.Garage;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.util.Scanner;

public class App
{

    private static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {

        Configuration configuration = new Configuration();

//        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//        System.out.println("Enter password");
//        String password = myObj.nextLine();  // Read user input
//        myObj.close();
//        configuration.setProperty("hibernate.connection.password", password);

        // Add ALL of your entities here. You can also try adding a wholepackage.
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(Image.class);
        configuration.addAnnotatedClass(Person.class);
        configuration.addAnnotatedClass(Garage.class);


        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();


        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static void generateCars() throws Exception {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Car car = new Car("MOO-" + random.nextInt(), 100000, 2000 + random.nextInt(24),5);
            session.save(car);
            session.flush();
        }

    }

    private static void generatePersons() throws Exception {
        Person[] persons = new Person[8];

        persons[0] = new Person("Ahmed", "Mansoori", "password123", "ahmed.mansoori@example.com");
        persons[1] = new Person("Fatima", "Farsi", "password234", "fatima.farsi@example.com");
        persons[2] = new Person("Hassan", "Tamimi", "password345", "hassan.tamimi@example.com");
        persons[3] = new Person("Layla", "Habibi", "password456", "layla.habibi@example.com");
        persons[4] = new Person("Omar", "Qadri", "password567", "omar.qadri@example.com");
        persons[5] = new Person("Aisha", "Sayed", "password678", "aisha.sayed@example.com");
        persons[6] = new Person("Khalid", "Aziz", "password789", "khalid.aziz@example.com");
        persons[7] = new Person("Mariam", "Hashimi", "password890", "mariam.hashimi@example.com");

        // Example of how to access the array
        for (Person person : persons) {
            session.save(person);
            session.flush();
        }
    }

    private static void generateGarages() throws Exception {
        Garage[] garages = new Garage[3];

        garages[0] = new Garage("123 Dizengoff St, Tel Aviv", "+972-3-1234567");
        garages[1] = new Garage("45 Jaffa St, Jerusalem", "+972-2-2345678");
        garages[2] = new Garage("78 Herzl Blvd, Haifa", "+972-4-3456789");

        for (Garage garage : garages) {
            session.save(garage);
            session.flush();
        }
    }

    private static void generateImages() throws Exception {
        Image[] images = new Image[10];
        for (int i = 0; i < 10; i++) {
            images[i] = new Image("image" + i + ".jpg");
            session.save(images[i]);
            session.flush();
        }


    }

    private static List<Car> getAllCars() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Car> query = builder.createQuery(Car.class);
        query.from(Car.class);
        List<Car> data = session.createQuery(query).getResultList();
        return data;
    }

    private static void printAllCars() throws Exception {
        List<Car> cars = getAllCars();
        for (Car car : cars) {
            System.out.print("Id: ");
            System.out.print(car.getId());
            System.out.print(", License plate: ");
            System.out.print(car.getLicensePlate());
            System.out.print(", Price: ");
            System.out.print(car.getPrice());
            System.out.print(", Year: ");
            System.out.print(car.getYear());
            System.out.print('\n');
        }
    }

    public static void main( String[] args ) {

        try {
            SessionFactory sessionFactory = getSessionFactory();

            session = sessionFactory.openSession();
            session.beginTransaction();

            generatePersons();
            generateCars();
            generateGarages();
            generateImages();

            printAllCars();

            session.getTransaction().commit(); // Save everything.

        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
                    exception.printStackTrace();
        } finally {
            session.close();
        }
    }
}