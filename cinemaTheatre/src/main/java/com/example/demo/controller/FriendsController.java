package com.example.demo.controller;

import com.example.demo.dao.FriendsDao;
import com.example.demo.dao.FriendsDaoImpl;
import com.example.demo.model.Friends;
import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.FriendService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.persistence.Table;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value = "/friends")
public class FriendsController {

    @Autowired
    FriendService friendService;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    FriendsDao friendsDao;

//    @RequestMapping(value = "/allFirstName/{firstName}", method = RequestMethod.GET)
//    public List<User> listFriendsAndNonFriendsByFirstName(@PathVariable("firstName") String firstName){
//        List<User> users = userService.findByFirstName(firstName);
//        System.out.println(users.size());
//        return users;
//    }
//    @RequestMapping(value = "/allLastName/{lastName}", method = RequestMethod.GET)
//    public List<User> listFriendsAndNonFriendsByLastName(@PathVariable("lastName") String lastName){
//        List<User> users = userService.findByLastName(lastName);
//        return users;
//    }

    @RequestMapping(value = "/allName/{name}", method = RequestMethod.GET)
    public List<User> listFriendsAndNonFriendsByName(@PathVariable("name") String name){
        List<User> users;
        User user = authenticationService.getLoggedInUser();
        String fm = "", lm = "";
        if(name.contains(" ")){
            fm = name.split(" ")[0];
            lm = name.split(" ")[1];
            users = userService.findUsersByFirstNameContainsOrLastNameContains(fm,lm);
        } else
            users = userService.findUsersByFirstNameContainsOrLastNameContains(name,name);
        if(users.contains(user))
            users.remove(user);
        System.out.println(users.size());
        return users;
    }
    @RequestMapping(value = "/allFriends", method = RequestMethod.GET)
    public List<User> listAllFriends(){
        User user = authenticationService.getLoggedInUser();
        List<Friends> friends = friendService.findUsersByUserOneOrUserTwo(user,user);
        List<User> ret = new ArrayList<>();
        for(Friends friend: friends){
            //obe provere da li ret sadrzi jer jos uvek moze 2 usera jedan drugog da dodaju.
            // TODO: samo jedan par prijatelja moze da postoji
            if(friend.getStatus()==1 && (!ret.contains(friend.getUserOne()))
                    && (!ret.contains(friend.getUserTwo()))){
                if(friend.getUserOne().getId()==user.getId())
                    ret.add(friend.getUserTwo());
                else if(friend.getUserTwo().getId()==user.getId())
                    ret.add(friend.getUserOne());
            }
        }
        return ret;
    }


    @RequestMapping(value = "/sortFriendsBy/{criteria}", method = RequestMethod.GET, produces = "application/json")
    public List<User> sortFriendsBy(@PathVariable("criteria") String criteria){
        List<User> friends = listAllFriends();
        switch (criteria){
            case "firstName": friends.sort(Comparator.comparing(User::getFirstName)); break;
            case "lastName":  friends.sort(Comparator.comparing(User::getFirstName)); break;
            default: friends =  null;
        }
        return friends;

    }


    @RequestMapping(value = "/friendsName/{name}", method = RequestMethod.GET)
    public List<User> listFriendsByName(@PathVariable("name") String name){
        User loggedIn = authenticationService.getLoggedInUser();
        List<User> users;
        String fm = "", lm = "";
        if(name.contains(" ")){
            lm = name.split(" ")[1];
            fm = name.split(" ")[0];
            users  = userService.findUsersByFirstNameContainsOrLastNameContains(fm,lm);
        } else
            users = userService.findUsersByFirstNameContainsOrLastNameContains(name,name);
        List<User> friends =  listAllFriends();
        List<User> ret = new ArrayList<>();
        for(User user: users){
            for(User friend: friends){
                if(user.getId()==friend.getId() && loggedIn.getId()!= friend.getId())
                    ret.add(user);
            }
        }
        return ret;
    }
//    @RequestMapping(value = "/friendsFirstName/{firstName}", method = RequestMethod.GET)
//    public List<User> listFriendsByFirstName(@PathVariable("firstName") String firstName){
//        List<User> users = userService.findByFirstName(firstName);
//        List<User> friends =  listAllFriends();
//        List<User> ret = new ArrayList<>();
//        for(User user: users){
//            for(User friend: friends){
//                if(user.getId()==friend.getId())
//                    ret.add(user);
//            }
//        }
//        return ret;
//    }
//
//    @RequestMapping(value = "/friendsLastName/{lastName}", method = RequestMethod.GET)
//    public List<User> listFriendsByLastName(@PathVariable("lastName") String lastName){
//        List<User> users = userService.findByLastName(lastName);
//        List<User> friends = listAllFriends();
//        List<User> ret = new ArrayList<>();
//        for(User user: users){
//            for(User friend: friends){
//                if(user.getId()==friend.getId())
//                    ret.add(user);
//            }
//        }
//        return ret;
//    }
    @RequestMapping(value = "/isFriends/{id}", method = RequestMethod.GET, produces = "text/plain")
    public String isFriends(@PathVariable("id") Long id){
        User user = authenticationService.getLoggedInUser();
        User friend = userService.getById(id);
        List<Friends> friends = friendService.findByUserOneAndUserTwoOrUserTwoAndUserOne(user,friend,user,friend);
        if(friends.isEmpty())
            return "no";
        for(Friends f: friends){
            if(f.getStatus()==1 || f.getStatus()==0)
                return "yes";
        }
        return "no";

    }
    @RequestMapping(value = "/addFriend/{id}", method = RequestMethod.GET, produces = "text/plain")
    public String addFriend( @PathVariable("id") Long id ){
        User user = authenticationService.getLoggedInUser();
        User receiver = userService.getById(id);
       List<Friends> friends = friendService.findByUserOneAndUserTwoOrUserTwoAndUserOne(user,receiver,user,receiver);
       if(user.getId()!=id) {
           if (friends.isEmpty()) {
               friendsDao.create(user, receiver, 0);
               return "sent";
           } else {
               for (Friends f : friends) {
                   if ((f.getUserOne().getId() == user.getId() && f.getUserTwo().getId() == receiver.getId()) ||
                           (f.getUserOne().getId() == receiver.getId() && f.getUserTwo().getId() == user.getId())) {
                       if (f.getStatus() == 0) {
                           return "already sent";
                       } else if (f.getStatus() == 1)
                           return "already friends";

                   }
               }
           }
           return "nok";
       }
        return "cannot send request to yourself";
    }



    @RequestMapping(value = "/getFriendRequests", method = RequestMethod.GET, produces = "application/json")
    public List<User> getFriendRequests(){
        User user = authenticationService.getLoggedInUser();
        List<Friends> requests = friendService.findUsersByUserTwo(user);
        List<User> accept = new ArrayList<>();
        if(requests.size()>0){
            for(Friends f: requests){
                if(f.getStatus()==0)
                    accept.add(f.getUserOne());
            }
        }
        return accept;
    }
    @RequestMapping(value = "/acceptFriendRequest/{id}", method = RequestMethod.GET, produces = "text/plain")
    public String acceptFriendRequest(@PathVariable("id") Long id){
        User loggedIn = authenticationService.getLoggedInUser();
        List<User> requests = getFriendRequests();
        User user = userService.getById(id);
        if(requests.size()>0){
           for(User u: requests){
               if(u.getId()==user.getId()){
                    Friends friends = friendService.findUserByUserOneAndUserTwo(user,loggedIn);
                    if(friends!=null) {
                        friends.setStatus(1);
                        friendsDao.update(user,loggedIn,1);
                        return "accepted";
                    }
               }
           }

        }
        return "no requests";
    }

    @RequestMapping(value = "/declineRequest/{id}", method = RequestMethod.GET, produces = "text/plain")
    public String declineRequest(@PathVariable("id") Long id){
        User loggedIn = authenticationService.getLoggedInUser();
        User user = userService.getById(id);
        if(loggedIn==null || user == null)
            return "not found";
        List<Friends> friends = friendService.findByUserOneAndUserTwoOrUserTwoAndUserOne(loggedIn,user,loggedIn,user);
        if(friends.size()>0){
            for(Friends f: friends){
                friendService.delete(f);
            }
            return "deleted";
        }
        return "nothing to delete";
    }



}
