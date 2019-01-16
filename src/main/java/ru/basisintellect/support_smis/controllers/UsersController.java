package ru.basisintellect.support_smis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.basisintellect.support_smis.entities.User;
import ru.basisintellect.support_smis.entities.UserRole;
import ru.basisintellect.support_smis.repositories.UserRoleRepository;
import ru.basisintellect.support_smis.repositories.UsersRepository;


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
//    public List<User> getUsers()
//    {
//        List<User> result = new ArrayList<>();
//        users.findAll().forEach(result::add);
//        return result;
//    }



    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView addUser(String username, String password, String password_confirm, String e_mail, Model model)
    {
        //no empty fields allowed
        if (username.isEmpty() || password.isEmpty() || password_confirm.isEmpty())
            return null;
        //passwords should match
        if (!password.equals(password_confirm))
            return null;
        UserRole userRole = roles.findByUserRoleName("ROLE_USER");
        User user = new User();
        user.setUsername(username);
        user.setPassword(bcryptEncoder.encode(password));
        user.setFirstName(" ");
        user.setLastName(" ");
        user.setMail(e_mail);
        user.setUserRole(userRole);
        user.setDateRegistration(new Date());
        user.setEnabled(true);
        users.save(user);
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

//    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "getUser/{userId}", method = RequestMethod.GET)
    public String getUser(@PathVariable("userId") Long userId, Model model)
    {
        class UserMessage{
            public Boolean isIncoming;
            public User recipient;
            public String msg;

            public UserMessage(User recipient, Boolean isIncoming, String msg) {
                this.recipient = recipient;
                this.msg = msg;
                this.isIncoming = isIncoming;
            }
        }
        List<UserMessage> userMessages = new ArrayList<UserMessage>();
        User user = users.findOne(userId);
        Set<Chat> chatList  = user.getChats();

        for (Chat chat:chatList) {
            ChatMessage chatMessage = msgs.findTop1ByChatOrderByDateTimeDesc(chat);
            if(chatMessage!=null)
                userMessages.add(new UserMessage(chat.getRecipient(),chatMessage.isIncoming(), chatMessage.getMsg()));

        }

//        List<ChatMessage> sendMsgs = msgs.findBySender(user);
//        List<ChatMessage> reciveMsgs = msgs.findByRecipient(user);
//        List<ChatMessage>messages = new ArrayList<>();
//        for (ChatMessage sender:senders) {
//            messages.add(msgs.findTop1BySenderAndRecipientOrderByDateTimeDesc(sender.getRecipient(), user));
//        }

        model.addAttribute("user", user);
//        model.addAttribute("msgList", userMessages);


        return "users/profile";
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public String editUser(Long userId, @RequestParam("img")MultipartFile img, String first_name, String last_name, String email, Model model)
    {
        User user = users.findOne(userId);
        images.setImg(user, img);
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setMail(email);
        users.save(user);

        model.addAttribute("user", user);
        return "users/profile";
    }
}