package ru.mts.test.hackathon_project_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.mts.test.hackathon_project_1.model.entities.UserEntity;
import ru.mts.test.hackathon_project_1.repositories.UsersRepository;

import javax.servlet.http.HttpSession;

/**
 * Скоп контроллер, возвращающий пользователю ложный пароль в html код и перенаправляющий на другую страницу с полем <b>user</b>
 * Created by safin.v on 30.11.2016.
 */
@Controller
public class ScopeController {

    @Autowired
    UsersRepository users;


    @RequestMapping(value = "/scopeSession", method = RequestMethod.POST)
    public String scopeExample(HttpSession session) {
        System.out.println("ScopeController scopeSession() is called");
        UserEntity currentUser = users.findByMail(SecurityContextHolder.getContext().getAuthentication().getName());
        currentUser.setPassword("123654345");//ложный
        session.setAttribute("currentUser", currentUser);
        return "redirect:/smises_list" ;
        //перенаправление на стартовую страницу
    }
}
