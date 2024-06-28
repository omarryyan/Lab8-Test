package org.example;
import java.util.*;
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
        Image[] images = new Image[10];

        for (int i = 0; i < 10; i++) {
            images[i] = new Image("image" + i + ".jpg");
            session.save(images[i]);
            session.flush();
        }

        for (int i = 0; i < 10; i++) {
            Car car = new Car("MOO-" + random.nextInt(), 100000, 2000 + random.nextInt(24), images[i]);
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

    private static List<Car> getAllCars() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Car> query = builder.createQuery(Car.class);
        query.from(Car.class);
        return session.createQuery(query).getResultList();
    }

    private static List<Person> getAllPersons() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        query.from(Person.class);
        return session.createQuery(query).getResultList();
    }

    static List<Garage> getAllGarages() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Garage> query = builder.createQuery(Garage.class);
        query.from(Garage.class);
        return session.createQuery(query).getResultList();
    }

    private static void printAllCars() throws Exception {
        List<Car> cars = getAllCars();
        for (Car car : cars) {
            System.out.println(car);
            System.out.print("Garages address: ");
            Iterator<Garage> iterator = car.getGarages().iterator();
            while (iterator.hasNext()) {
                Garage garage = iterator.next();
                System.out.print(garage.getAddress());
                if (iterator.hasNext()) {
                    System.out.print(" / ");
                }
            }
            System.out.println("\n");
        }
    }

    private static void printAllGarages() throws Exception {
        List<Garage> garages = getAllGarages();
        for (Garage garage : garages) {
            System.out.println(garage);
            System.out.print("Cars License Plates: ");
            Iterator<Car> iterator = garage.getCars().iterator();
            while (iterator.hasNext()) {
                Car car = iterator.next();
                System.out.print(car.getLicensePlate());
                if (iterator.hasNext()) {
                    System.out.print(" / ");
                }
            }
            System.out.println("\n");
        }
    }

    private static void initializeDB() throws Exception {
        List<Car> cars = getAllCars();
        List<Person> persons = getAllPersons();
        List<Garage> garages = getAllGarages();

        cars.get(0).addGarage(garages.get(0));
        cars.get(0).addGarage(garages.get(1));
        cars.get(1).addGarage(garages.get(2));
        cars.get(1).addGarage(garages.get(1));
        cars.get(2).addGarage(garages.get(2));
        cars.get(3).addGarage(garages.get(1));
        cars.get(4).addGarage(garages.get(0));
        cars.get(6).addGarage(garages.get(0));
        cars.get(7).addGarage(garages.get(0));
        cars.get(7).addGarage(garages.get(1));
        cars.get(7).addGarage(garages.get(2));


        int i=0;
        for (Person person : persons) {
            person.addGarage(garages.get(0));
            person.addGarage(garages.get(1));
            person.addGarage(garages.get(2));
            person.addCar(cars.get(i++));
        }
        persons.getLast().addCar(cars.get(8));
        persons.getLast().addCar(cars.get(9));
    }

    public static void main( String[] args ) {

        try {
            SessionFactory sessionFactory = getSessionFactory();

            session = sessionFactory.openSession();
            session.beginTransaction();

            generatePersons();
            generateCars();
            generateGarages();

            initializeDB();

            printAllGarages();
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