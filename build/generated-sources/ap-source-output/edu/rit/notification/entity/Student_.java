package edu.rit.notification.entity;

import edu.rit.notification.entity.Enrollment;
import edu.rit.notification.entity.Student;
import edu.rit.notification.entity.Subscription;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-10-26T23:44:40")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile SingularAttribute<Student, String> uid;
    public static volatile ListAttribute<Student, Student> classEnrollmentList;
    public static volatile SingularAttribute<Student, String> phone;
    public static volatile ListAttribute<Student, Student> classSubscriptionList;
    public static volatile SingularAttribute<Student, String> email;
    public static volatile ListAttribute<Student, Enrollment> enrollmentList;
    public static volatile SingularAttribute<Student, String> lastname;
    public static volatile SingularAttribute<Student, String> firstname;
    public static volatile ListAttribute<Student, Subscription> subscriptionList;
    public static volatile SingularAttribute<Student, String> major;

}