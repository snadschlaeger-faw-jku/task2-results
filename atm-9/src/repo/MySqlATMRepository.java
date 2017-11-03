package repo;

import ATM.Account;

import domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import util.ClauseOperators;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class MySqlATMRepository {
    private SessionFactory sessionFactory;
    private StandardServiceRegistry registry;
    private List<Rule> ruleList;

    public MySqlATMRepository(String url, String user, String passwd) throws Exception {
        setUp();
        //getFacts(Arrays.asList("Account", "CreditCard", "Pin"));
        initializeRuleList();
    }

    private void initializeRuleList() {
        ruleList = new ArrayList<>();
        ruleList.add(new Rule(null, Arrays.asList(new Condition(ClauseOperators.take.name(), new Fact("CreditCard", Arrays.asList
                (new Property("cardNumber", "class java.lang.String", "123", true))))),
                null));
        //CreditCard ( cardNumber : 1 2 3 )

        ruleList.add(new Rule(null, Arrays.asList(new Condition(ClauseOperators.exists.name(), new Fact("CreditCard", Arrays.asList
                (new Property("cardNumber", "class java.lang.String", "123", true))))), Arrays.asList
                (new Action(ClauseOperators.assertFact.name(), new Fact("Pin",
                        Arrays.asList(new Property("cardNumber", "class java.lang.String", "123", true),
                                new Property("pinNumber", "class java.lang.String", null, true)))))));
        //IF e x i s t s ( CreditCard ( cardNumber : 1 2 3 ) )
        //THEN a s s e r t ( Pin ( cardNumber : 1 2 3 , pinNumber:<ask u se r >))

        ruleList.add(new Rule(null, Arrays.asList(new Condition(ClauseOperators.exists.name(), new Fact("Pin", Arrays.asList
                (new Property("cardNumber", "class java.lang.String", "123", true))))), Arrays.asList
                (new Action(ClauseOperators.assertFact.name(), new Fact("WithdrawMoney",
                        Arrays.asList(new Property("amount", "class java.lang.String", null, true)))))));
        //IF e x i s t s ( Pin ( cardNumber : 1 2 3 , pinNumber:<The v al u e from prev a c ti o n >))
        //THEN a s s e r t (WithDrawMoney ( amount:<ask u se r >))
    }

    protected void setUp() throws Exception {
        registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }


    public void close() throws Exception {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public void SaveObject() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Account("9999", "7777"));
        session.getTransaction().commit();
        session.close();
    }

    public List<Fact> getFacts(List<String> entityNameList) throws SQLException {
        Session session = sessionFactory.openSession();
        List<Fact> factList = new ArrayList<>();

        for (String entityName : entityNameList) {
            Query query = session.createQuery("from " + entityName);
            List list = query.list();
            List<Property> entityPropertyList = getPropertyListForFact(entityName);


            for (Object obj : list) {
                List<Property> currentObjectPropertyList = new ArrayList<>();
                currentObjectPropertyList.addAll(entityPropertyList);
                try {
                    int index = 0;
                    for (Field field : Class.forName("ATM." + entityName).getDeclaredFields()) {
                        field.setAccessible(true);
                        String fieldValue = String.valueOf(field.get(obj));
                        currentObjectPropertyList.get(index).setValue(fieldValue);
                        ++index;
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                //List<Property> currentObjectPropertyListClone = new ArrayList<>(currentObjectPropertyList);
                List<Property> currentObjectPropertyListClone = cloneList(currentObjectPropertyList);
                factList.add(new Fact(entityName, currentObjectPropertyListClone));

            }
        }

        return factList;
    }

    public static List<Property> cloneList(List<Property> propertyList) {
        List<Property> clonedList = new ArrayList<Property>(propertyList.size());
        for (Property property : propertyList) {
            clonedList.add(new Property(property));
        }
        return clonedList;
    }

    private List<Property> getPropertyListForFact(String entityName) {

        List<Property> propertyList = new ArrayList<>();
        try {
            Boolean isMandatory = true; //only first field (Which is PK/FK) is mandatory
            for (Field field : Class.forName("ATM." + entityName).getDeclaredFields()) {
                Class fieldType = field.getType();
                String fieldName = field.getName();
                Annotation[] annotations = field.getDeclaredAnnotations();
                field.setAccessible(true);
                propertyList.add(new Property(fieldName, String.valueOf(fieldType), ""/*String.valueOf(field.get(Class.forName("ATM." + entityName)))*/, isMandatory));
                isMandatory = false; //every other field is not mandatory
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return propertyList;
    }

    public static List<Field> getAnnotatedFields(Class<?> clazz,
                                                 Class<? extends Annotation> annotation) {

        List<Field> annotatedFields = new ArrayList<Field>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(annotation)) {
                annotatedFields.add(field);
            }
        }

        return annotatedFields;

    }

    public List<Rule> getRules() {
        return ruleList;
    }
}
