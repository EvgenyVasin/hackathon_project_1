package ru.mts.test.hackathon_project_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.mts.test.hackathon_project_1.model.entities.UserEntity;
import ru.mts.test.hackathon_project_1.model.entities.UserRoleEntity;
import ru.mts.test.hackathon_project_1.repositories.UserRoleRepository;
import ru.mts.test.hackathon_project_1.repositories.UsersRepository;


import java.util.*;

/**
 * Created by safin.v on 26.10.2016.
 */
@Controller
public class UsersController {
    @Autowired
    UsersRepository users;
    @Autowired
    BCryptPasswordEncoder bcryptEncoder;
    @Autowired
    UserRoleRepository roles;


//    @PreAuthorize("hasRole('ADMIN')")
//    @RequestMapping(method = RequestMethod.GET)
//    public List<UserEntity> getUsers()
//    {
//        List<UserEntity> result = new ArrayList<>();
//        users.findAll().forEach(result::add);
//        return result;
//    }



    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView addUser(String firstname, String lastname,String password, String password_confirm, String e_mail, Model model)
    {
        //no empty fields allowed
        if (firstname.isEmpty() || lastname.isEmpty() || e_mail.isEmpty()|| password.isEmpty() || password_confirm.isEmpty())
            return null;
        //passwords should match
        if (!password.equals(password_confirm))
            return null;
        UserRoleEntity userRole = roles.findByUserRoleName("ROLE_USER");
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(bcryptEncoder.encode(password));
        userEntity.setFirstName(firstname);
        userEntity.setLastName(lastname);
        userEntity.setMail(e_mail);
        userEntity.setUserRole(userRole);
        userEntity.setDateRegistration(new Date());
        userEntity.setEnabled(true);
        users.save(userEntity);
        Map<String,String> attribute = new HashMap<>();
        attribute.put("msg_info", "регистрация завершена");
        return new ModelAndView("info",attribute);
    }


//    @RequestMapping(value = "/add",method = RequestMethod.GET)
//    public ModelAndView getUserForm()
//    {
//        return new ModelAndView("add");
//    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void delete(@PathVariable("id") Long id)
//    {
//        users.delete(id);
//    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "getUser/{userId}", method = RequestMethod.GET)
    public String getUser(@PathVariable("userId") Long userId, Model model)
    {
        /*class UserMessage{
            public Boolean isIncoming;
            public UserEntity recipient;
            public String msg;

            public UserMessage(UserEntity recipient, Boolean isIncoming, String msg) {
                this.recipient = recipient;
                this.msg = msg;
                this.isIncoming = isIncoming;
            }
        }
        List<UserMessage> userMessages = new ArrayList<UserMessage>();*/
        UserEntity userEntity = users.findById(userId).get();
        /*Set<Chat> chatList  = userEntity.getChats();

        for (Chat chat:chatList) {
            ChatMessage chatMessage = msgs.findTop1ByChatOrderByDateTimeDesc(chat);
            if(chatMessage!=null)
                userMessages.add(new UserMessage(chat.getRecipient(),chatMessage.isIncoming(), chatMessage.getMsg()));

        }*/

//        List<ChatMessage> sendMsgs = msgs.findBySender(userEntity);
//        List<ChatMessage> reciveMsgs = msgs.findByRecipient(userEntity);
//        List<ChatMessage>messages = new ArrayList<>();
//        for (ChatMessage sender:senders) {
//            messages.add(msgs.findTop1BySenderAndRecipientOrderByDateTimeDesc(sender.getRecipient(), userEntity));
//        }

        model.addAttribute("user", userEntity);
//        model.addAttribute("msgList", userMessages);


        return "users/profile";
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public String editUser(Long userId, String first_name, String last_name, String email, Model model)
    {
        UserEntity user = users.findById(userId).get();
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setMail(email);
        users.save(user);

        model.addAttribute("user", user);
        return "users/profile";
    }
}