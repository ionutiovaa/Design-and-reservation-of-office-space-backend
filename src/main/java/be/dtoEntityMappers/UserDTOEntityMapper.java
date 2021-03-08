package be.dtoEntityMappers;

import be.dto.UserDTO;
import be.entity.Echipa;
import be.entity.User;

import java.util.*;

public class UserDTOEntityMapper {

    private UserDTOEntityMapper(){}

    public static Set<User> getUserSetFromDTO(Set<UserDTO> userDTOS){
        Set<User> users = new HashSet<>();
        userDTOS.forEach(userDTO -> users.add(getUserFromUserDTO(userDTO)));
        return users;
    }

    public static User getUserFromUserDTO(UserDTO userDTO){
        User user = new User();
        if (userDTO != null) {
            user.setID(userDTO.getID());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setMobileNumber(userDTO.getMobileNumber());
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setUserType(userDTO.getUserType());
            //user.setEchipe(userDTO.getEchipe());
            user.setEchipe(EchipaDTOEntityMapper.getAllEchipeSetFromDTO(userDTO.getEchipe()));
        }
        return user;
    }

    public static UserDTO getDTOFromUser(User user){
        UserDTO userDTO = new UserDTO();
        if (user != null){
            userDTO.setID(user.getID());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setMobileNumber(user.getMobileNumber());
            userDTO.setEmail(user.getEmail());
            userDTO.setUsername(user.getUsername());
            byte[] decodedPass = Base64.getDecoder().decode(user.getPassword());
            //userDTO.setPassword(user.getPassword());
            userDTO.setPassword(new String(decodedPass));
            userDTO.setEchipe(EchipaDTOEntityMapper.getAllEchipeDTOSetFromEchipe(user.getEchipe()));
            userDTO.setUserType(user.getUserType());
            //userDTO.setEchipe(user.getEchipe());
        }
        return userDTO;
    }

    public static UserDTO getDTOCompleteFromUser(User user){
        UserDTO userDTO = getDTOFromUser(user);
        if (user != null){
            userDTO.setPassword(user.getPassword());
        }
        return userDTO;
    }

    public static List<UserDTO> getUserDTOList(List<User> users){
        List<UserDTO> userDTOList = new ArrayList<>();
        users.forEach(user -> userDTOList.add(getDTOWithoutPasswFromUser(user)));
        return userDTOList;
    }

    public static UserDTO getDTOWithoutPasswFromUser(User user){
        UserDTO userDTO = getDTOFromUser(user);
        userDTO.setPassword("");
        return userDTO;
    }

}
