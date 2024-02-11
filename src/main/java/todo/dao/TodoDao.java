package todo.dao;

import java.util.List;

import javax.persistence.EntityManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import todo.dto.TodoTask;
import todo.dto.TodoUser;

@Controller
public class TodoDao {
	@Autowired    // it will get the object of entity manager
	EntityManager manager ;

	//finding user by email
public void Saveuser(TodoUser user) {
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
	}
public void Saveuser(TodoTask user) {
	manager.getTransaction().begin();
	manager.persist(user);
	manager.getTransaction().commit();
}
	//finding user by email
	public  List<TodoUser> findByEmail(String email) {
		return manager.createQuery("select x from TodoUser x where email=?1").setParameter(1, email).getResultList();
		
		
	}
	public Object fetchAllTask(int id) {
		
		return manager.createQuery("select x from TodoTask x where user_id=?1").setParameter(1, id).getResultList();
	}
	public void update(TodoTask task) {
		manager.getTransaction().begin();
		manager.merge(task);
		manager.getTransaction().commit();
		
	}
	public TodoTask fetchTaskById(int id) {
		
		return manager.find(TodoTask.class,id);
	}
	
	public void delete(TodoTask task) {
		manager.getTransaction().begin();
		manager.remove(task);
		manager.getTransaction().commit();
	}
	

}
