package todo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import todo.dto.TodoTask;
import todo.dto.TodoUser;
import todo.service.TodoService;

@Controller
public class TodoController {
	@Autowired
	TodoService service;

	@GetMapping({ "/", "/login" }) // multiple url are mapped her
	public String loadLogin() {
		return "Login";

	}

	@GetMapping("/signup")
	public String loadSignup() {
		return "Signup";

	}

	@PostMapping("/signup")

	public String signup(TodoUser user, @RequestParam String date, ModelMap map) {// create the object of todoUser class
																					// and recieved
		return service.signup(user, date, map);

	}

	@PostMapping("/login")
	public String login(@RequestParam String email, String password, ModelMap map, HttpSession session) {
		return service.login(email, password, map, session);
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, ModelMap map) {

		return service.logout(session, map);
	}

	@GetMapping("/home")
	public String loadHome(HttpSession session, ModelMap model) {
		return service.loadHome(session, model);
	}

	@GetMapping("/add-task")
	public String loadAddTask(HttpSession session, ModelMap map) {
		return service.addtask(session, map);
	}

	@PostMapping("/add-task")
	public String addTask(TodoTask task, HttpSession session, ModelMap map) {
		return service.addtask(task, session, map);
	}

	@GetMapping("/change-status")
	public String changeStatus(@RequestParam int id, HttpSession session, ModelMap map) {
		return service.ChangeStatus(id, session, map);
	}

	@GetMapping("/delete")
	public String deleteTask(@RequestParam int id, HttpSession session, ModelMap map) {
		return service.deleteTask(id, session, map);

	}
	@GetMapping("/edit")
	public String loadEdit(HttpSession session,ModelMap map,@RequestParam int id)
	{
		return service.loadEdit(session,map,id);
	}
	@PostMapping("/update-task")
	public String updateTask(HttpSession session,ModelMap map,TodoTask task)
	{
		return service.updateTask(task,session,map);
	}
	
}
