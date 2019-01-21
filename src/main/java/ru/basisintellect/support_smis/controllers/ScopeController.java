package ru.basisintellect.support_smis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.basisintellect.support_smis.model.entities.UserEntity;
import ru.basisintellect.support_smis.repositories.UsersRepository;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by safin.v on 30.11.2016.
 */
@Controller
public class ScopeController {
    @Autowired
    UsersRepository users;

    @RequestMapping(value = "/scopeSession", method = RequestMethod.POST)
    public String scopeExample(HttpServletRequest request, HttpSession session) {
        System.out.println("ScopeController scopeSession() is called");
        UserEntity currentUserEntity = users.findByMail(SecurityContextHolder.getContext().getAuthentication().getName());
        currentUserEntity.setPassword("123654345");//ложный
        session.setAttribute("currentUser", currentUserEntity);
        return "redirect:" + request.getScheme() +":/";
    }
}
