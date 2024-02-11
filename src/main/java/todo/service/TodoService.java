package todo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import todo.dto.TodoTask;
import todo.dao.TodoDao;
import todo.dto.TodoUser;
import todo.helper.Aes;

@Component
public class TodoService {
	@Autowired
	TodoDao dao;

	public String signup(TodoUser user, String date, ModelMap map) {
		user.setDob(LocalDate.parse(date));
		user.setAge(Period.between(user.getDob(), LocalDate.now()).getYears());
		user.setPassword(Aes.encrypt(user.getPassword(), "123"));

		if (user.getAge() < 18) {
			map.put("dob", "* you have to be 18+ to create Account ");
			return "Signup";
		}

		else {
			List<TodoUser> list = dao.findByEmail(user.getEmail());
			if (list.isEmpty()) {
				dao.Saveuser(user);

				map.put("pass", "Account created successfully");
				return "Login";
			} else {
				map.put("email", " * This Email already exists");
				return "Signup";

			}

		}
	}

	public String login(String email, String password, ModelMap map, HttpSession session) {
		List<TodoUser> list = dao.findByEmail(email);
		if (list.isEmpty()) {
			map.put("email", "* Incorrect Email");
			return "Login";
		} else {
			if (password.equals(Aes.decrypt(list.get(0).getPassword(), "123"))) {
				session.setAttribute("todouser", list.get(0));
				map.put("list", dao.fetchAllTask(list.get(0).getId()));
				map.put("pass", "Login Success");
				return "TodoHome";
			} else {
				map.put("password", "* Incorrect Password");
				return "Login";
			}
		}
	}

	public String loadHome(HttpSession session, ModelMap map) {
		TodoUser user = (TodoUser) session.getAttribute("todoouser");
		if (user == null) {
			map.put("fail", "invalid session");
			return "Login";
		} else {
			return "TodoHome";
		}
	}

	public String logout(HttpSession session, ModelMap map) {
		session.invalidate();
		map.put("pass", "Logout Success");
		return "Login";
	}

	public String addtask(HttpSession session, ModelMap map) {
		TodoUser user = (TodoUser) session.getAttribute("todouser");
		if (user == null) {
			map.put("fail", "invalid session");

			return "Login";
		} else {
			return "AddTask";
		}
	}

	public String addtask(TodoTask task, HttpSession session, ModelMap map) {

		TodoUser user = (TodoUser) session.getAttribute("todouser");
		if (user == null) {
			map.put("fail", "invalid session");

			return "Login";
		} else {
			task.setCreatedTime(LocalDateTime.now());
			task.setUser(user);
			dao.Saveuser(task);

			map.put("list", dao.fetchAllTask(user.getId()));
			map.put("pass", "Data Saved Success");
			return "TodoHome";
		}

	}

	public String ChangeStatus(int id, HttpSession session, ModelMap map) {
		TodoUser user = (TodoUser) session.getAttribute("todouser");
		if (user == null) {
			map.put("fail", "invalid session");

			return "Login";
		} else {
			TodoTask task = dao.fetchTaskById(id);
			task.setCreatedTime(LocalDateTime.now());
			task.setStatus(true);
			dao.update(task);

			map.put("list", dao.fetchAllTask(user.getId()));
			map.put("pass", "Data Saved Success");
			return "TodoHome";
		}
	}

	public String deleteTask(int id, HttpSession session, ModelMap map) {
		TodoUser user = (TodoUser) session.getAttribute("todouser");
		if (user == null) {
			map.put("fail", "invalid session");

			return "Login";
		} else {
			TodoTask task = dao.fetchTaskById(id);
			dao.delete(task);
			map.put("list", dao.fetchAllTask(user.getId()));
			map.put("pass", "Task Deleted Success");
			return "TodoHome";
		}
	}
	public String loadEdit(HttpSession session, ModelMap map, int id) {
		TodoUser user = (TodoUser) session.getAttribute("todouser");
		if (user == null) {
			map.put("fail", "Invalid Session");
			return "Login";
		} else {
			TodoTask task = dao.fetchTaskById(id);
			map.put("task", task);
			return "EditTask";
		}
	}
	public String updateTask(TodoTask task, HttpSession session, ModelMap map) {
		TodoUser user = (TodoUser) session.getAttribute("todouser");
		if (user == null) {
			map.put("fail", "Invalid Session");
			return "Login";
		} else {
			task.setUser(user);
			task.setCreatedTime(LocalDateTime.now());
			dao.update(task);
			map.put("list", dao.fetchAllTask(user.getId()));
			map.put("pass", "Updated Success");
			return "TodoHome";
		}
	}

	
	}


