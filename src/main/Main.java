package main;



import java.sql.Connection;
import java.sql.DriverManager;

import javax.persistence.Query;
import javax.validation.Valid;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class Main {

	
	@RequestMapping("/")
	public String mainMenu() {
		return "mainmenu";
	}
	
	@RequestMapping("/register")
	public String showForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "register";
	}
	
	@RequestMapping("/confirmation")
	public String confirmation(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "register";
		}
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		try {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			factory.close();
		}
		return "confirmation";
	}
	
	
	@RequestMapping("/status")
	public String status(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "status";
	}
	
	

	@RequestMapping("/statusResponse")
	public String statusResponse(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "status";
		}
		else {	
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		try {
			
			
			Session session = factory.getCurrentSession();
			
			session.beginTransaction();
            
            Criteria criteria = session.createCriteria(Student.class);
            criteria.add(Restrictions.eq("email", student.getEmail()));
             
            Student studentFromDB = (Student) criteria.uniqueResult();
             
            if (studentFromDB!=null) {
                System.out.println("Student found:");
                System.out.println(studentFromDB.getId() + " - " + studentFromDB.getName());
                model.addAttribute("studentFromDB", studentFromDB);
            }
            else {
            	return "noRecords";
            }
         
             
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
		}
		return "statusResponse";
	}
	
}
