/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.notification.service;

import edu.rit.notification.bean.ClassScheduleFacade;
import edu.rit.notification.bean.StudentFacade;
import edu.rit.notification.bean.SubscriptionFacade;
import edu.rit.notification.entity.ClassSchedule;
import edu.rit.notification.entity.Student;
import edu.rit.notification.entity.Subscription;
import edu.rit.notification.entity.SubscriptionPK;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Administrator
 */
@WebService(serviceName = "StudentInformationService")
public class StudentInformationService {

    @EJB
    private ClassScheduleFacade classScheduleEjbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")
    @EJB
    private StudentFacade studentEjbRef;

    @EJB
    private SubscriptionFacade subscriptionEjbRef;

    @WebMethod(operationName = "getStudentSubscribed")
    public List<Student> getStudentSubscribed(@WebParam(name = "classNumber") String classNumber) {

        ClassSchedule classSchedule = classScheduleEjbRef.find(classNumber);
        System.out.println("getEnrollmentList: " + classSchedule.getEnrollmentList().size());
        System.out.println("getStudentEnrollmentList: " + classSchedule.getStudentEnrollmentList().size());
        System.out.println("getStudentSubscriptionList: " + classSchedule.getStudentSubscriptionList().size());
       // System.out.println("getSubscriptions: " + classSchedule.getSubscriptions());
        List<Student> studentList = classSchedule.getStudentSubscriptionList();
        return studentList;
    }

    @WebMethod(operationName = "getEnrollement")
    public List<Student> getEnrollement(@WebParam(name = "enrollmentRequest") EnrollmentRequest request) {
        System.out.println("*****************getEnrollment() called******************");
        ClassSchedule classSchedule = classScheduleEjbRef.find(request.getClassNumber());
        System.out.println("*****************classShedule: " + classSchedule + "******************");
        List<Student> studentList;
        if (classSchedule != null) {
            studentList = classSchedule.getStudentEnrollmentList();
        } else {
            studentList = null;
        }
        return studentList;
    }

    @WebMethod(operationName = "getStudentEnrolled")
    public List<Student> getStudentEnrolled(@WebParam(name = "classNumber") String classNumber) {
        System.out.println("**************getStudentEnrolled() called **************** ");
        ClassSchedule classSchedule = classScheduleEjbRef.find(classNumber);
        System.out.println("ClassSchedule: " + classNumber + " returned: " + classSchedule);

        List<Student> studentList;
        if (classSchedule != null) {
            studentList = classSchedule.getStudentEnrollmentList();
            System.out.println(studentList.size() + " Students found: ");
        } else {
            studentList = null;
            System.out.println("Students not found: ");
        }
        return studentList;
    }

    @WebMethod(operationName = "getStudentProfile")
    public Student getStudentProfile(@WebParam(name = "uid") String uid) {
        System.out.println("**************getStudentProfile() called**********************");
        return studentEjbRef.find(uid);
    }

    @WebMethod(operationName = "getClassScheduleForToday")
    public List<ClassSchedule> getClassScheduleForToday() {
        System.out.println("**************getClassScheduleForToday() called**********************");
        return classScheduleEjbRef.getClassScheduleForToday();

    }

    @WebMethod(operationName = "subscribe")
    public SubscriptionResponse subscribe(@WebParam(name = "subscriptionRequest") SubscriptionRequest request) {
        System.out.println("*****************subscribe() called******************");
        SubscriptionResponse response = new SubscriptionResponse("success");
        response.setStatus("OK");
        String uid = request.getStudentId();
        String classNumber = request.getClassNumber();
        System.out.println("SubscriptionRequest: " + request);
        Subscription subscription = new Subscription();

        SubscriptionPK subPK = new SubscriptionPK();
        subPK.setClassNumber(classNumber);
        subPK.setUid(uid);
        subscription.setSubscriptionPK(subPK);

        System.out.println("*****************calling studentEjbRef.find(uid) ******************");
        Student student = studentEjbRef.find(uid);
        System.out.println("Student: " + student);
        subscription.setStudent(student);

        System.out.println("*****************calling subscriptionEjbRef.create(subscription) ******************");
        subscriptionEjbRef.create(subscription);

        return response;
    }
}
