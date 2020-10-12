package anupk;

import java.lang.reflect.*;
import java.lang.reflect.InvocationHandler;
import java.util.*;


public class MatchMakingTestDrive {
	Vector <PersonBeanImpl> myDatabase = new Vector <PersonBeanImpl>(5);
	// instance variables here
	public static void main(String[] args) {
		MatchMakingTestDrive test = new MatchMakingTestDrive();
		test.drive();
	}
	public MatchMakingTestDrive() {
		initializeDatabase();
	}
	private void initializeDatabase() {
		// TODO Auto-generated method stub
		PersonBeanImpl MyPerson = new PersonBeanImpl() ;
		MyPerson.setName("Joe Javabean");
		MyPerson.setGender("Male");
		MyPerson.setInterests("Coffee");
		MyPerson.setHotOrNotRating(5);
		myDatabase.addElement(MyPerson);
		PersonBeanImpl MyPerson1 = new PersonBeanImpl() ;
		MyPerson1.setName("Lesslie");
		MyPerson1.setGender("Female");
		MyPerson1.setInterests("Coffee");
		MyPerson1.setHotOrNotRating(7);
		myDatabase.addElement(MyPerson1);
	}
	public void drive() {
		PersonBean joe = getPersonFromDatabase("Joe Javabean");
		PersonBean ownerProxy = getOwnerProxy(joe);
		System.out.println("Name is " + ownerProxy.getName());
		ownerProxy.setInterests("bowling, Go");
		System.out.println("Interests set from owner proxy as : \"" + ownerProxy.getInterests() + "\"");
		try {
			ownerProxy.setHotOrNotRating(10);
		} catch (Exception e) {
			System.out.println("Can’t set rating from owner proxy");
		}
		System.out.println("Rating is " + ownerProxy.getHotOrNotRating());
		PersonBean nonOwnerProxy = getNonOwnerProxy(joe);
		System.out.println("Name is " + nonOwnerProxy.getName());
		try {
			nonOwnerProxy.setInterests("bowling, Go");
		} catch (Exception e) {
			System.out.println("Can’t set interests from non owner proxy");
		}
		nonOwnerProxy.setHotOrNotRating(3);
		System.out.println("Rating set from non owner proxy");
		System.out.println("Rating is " + nonOwnerProxy.getHotOrNotRating());
	}
	private PersonBean getPersonFromDatabase(String string) {
		// TODO Auto-generated method stub
		// Creating an iterator 
        Iterator<PersonBeanImpl> value = myDatabase.iterator();
        while (value.hasNext()) { 
        	PersonBeanImpl temp = value.next() ;
            if (string == temp.getName())
            	return temp;
        } 
		return null;
	}
	// other methods like getOwnerProxy and getNonOwnerProxy here
	
	PersonBean getNonOwnerProxy(PersonBean person) {
		return (PersonBean) Proxy.newProxyInstance(
		person.getClass().getClassLoader(),
		person.getClass().getInterfaces(),
		(InvocationHandler) new NonOwnerInvocationHandler(person));
	}
	
	PersonBean getOwnerProxy(PersonBean person) {
		return (PersonBean) Proxy.newProxyInstance(
		person.getClass().getClassLoader(),
		person.getClass().getInterfaces(),
		(InvocationHandler) new OwnerInvocationHandler(person));
	}	
}
