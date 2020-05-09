package be.manager.remote;

import be.dto.AddUserToEchipaDTO;
import be.dto.ChangePasswordDTO;
import be.dto.EchipaDTO;
import be.dto.UserDTO;
import be.exceptions.BusinessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserManagerRemote {

    UserDTO insertUser(UserDTO userDTO) throws BusinessException;

    UserDTO findUser(Integer id) throws BusinessException;

    //Set<NotificationDTO> getUserNotifications(String username) throws BusinessException;

    List<UserDTO> findAllUsers();

    UserDTO findUserByUsernameAndPassword(String username, String password) throws BusinessException;

    UserDTO changePassword(ChangePasswordDTO changePasswordDTO) throws BusinessException;

    EchipaDTO addUserToEchipa(AddUserToEchipaDTO addUserToEchipaDTO) throws BusinessException;

    UserDTO deleteUserByUsername(String username) throws BusinessException;

}
