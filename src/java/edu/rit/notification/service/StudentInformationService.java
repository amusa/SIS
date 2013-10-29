/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.notification.service;

import edu.rit.notification.bean.ClassScheduleFacade;
import edu.rit.notification.bean.StudentFacade;
import edu.rit.notification.entity.ClassSchedule;
import edu.rit.notification.entity.Student;
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

    @WebMethod(operationName = "getStudentSubscribed")
    public List<Student> getStudentSubscribed(@WebParam(name = "classNumber") String classNumber) {
        ClassSchedule classSchedule = classScheduleEjbRef.find(classNumber);
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
        ClassSchedule classSchedule = classScheduleEjbRef.find(classNumber);
        List<Student> studentList;
        if (classSchedule != null) {
            studentList = classSchedule.getStudentEnrollmentList();
        } else {
            studentList = null;
        }
        return studentList;
    }

    @WebMethod(operationName = "getStudentProfile")
    public Student getStudentProfile(@WebParam(name = "uid") String uid) {
        return studentEjbRef.find(uid);
    }

    @WebMethod(operationName = "getClassScheduleForToday")
    public List<ClassSchedule> getClassScheduleForToday() {
        System.out.println("getClassScheduleForToday() called...");
        return classScheduleEjbRef.getClassScheduleForToday();

    }
}
