package be.manager.impl;

import be.dao.EchipaDao;
import be.dao.UserDao;
import be.dto.AddUserToEchipaDTO;
import be.dto.ChangePasswordDTO;
import be.dto.EchipaDTO;
import be.dto.UserDTO;
import be.dtoEntityMappers.EchipaDTOEntityMapper;
import be.dtoEntityMappers.UserDTOEntityMapper;
import be.entity.Echipa;
import be.entity.User;
import be.exceptions.BusinessException;
import be.manager.remote.UserManagerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Component
public class UserManager implements UserManagerRemote {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EchipaDao echipaDao;

    public UserDao getUserDao() {
        return userDao;
    }

    private Logger logger = Logger.getLogger(UserManager.class.getName());

    @Override
    public UserDTO insertUser(UserDTO userDTO) throws BusinessException {
        User userFounded = userDao.findUserByUsername(userDTO.getUsername());
        if (userFounded != null)
            return null;
        /*if (userDTO.getUsername().isEmpty() || userDTO.getPassword().isEmpty())
            return null;*/
        User user = createUserToInsert(userDTO);
        User persistedUser = userDao.save(user);
        UserDTO dtoPersisted = UserDTOEntityMapper.getDTOFromUser(persistedUser);
        return dtoPersisted;
    }

    private User createUserToInsert(UserDTO userDTO){
        User user = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getMobileNumber(), userDTO.getEmail(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getUserType());
        if (userDTO.getEchipe() != null)
            user.setEchipe(EchipaDTOEntityMapper.getAllEchipeSetFromDTO(userDTO.getEchipe()));
        return user;
    }

    @Override
    public UserDTO findUser(Integer id) throws BusinessException {
        User user = userDao.findAllByID(id);
        if (user == null){
            throw new BusinessException("Find user error: ", "No user with this id was found!");
        }
        return UserDTOEntityMapper.getDTOCompleteFromUser(user);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userDao.findAll();
        return UserDTOEntityMapper.getUserDTOList(users);
    }

    @Override
    public UserDTO findUserByUsernameAndPassword(String username, String password) throws BusinessException {
        User user = userDao.findByUsernameAndPassword(username, password);
        if (user == null){
            throw new BusinessException("Find user error: ", "No user with this username and password was found!");
        }

        return UserDTOEntityMapper.getDTOCompleteFromUser(user);
    }

    @Override
    public UserDTO changePassword(ChangePasswordDTO changePasswordDTO) throws BusinessException {
        User user = this.userDao.findAllByID(changePasswordDTO.getUserId());
        if (user.getPassword().equals(changePasswordDTO.getOldPassword())){
            // Change password;
            user.setPassword(changePasswordDTO.getNewPassword());
            int updated = this.userDao.updatePassword(changePasswordDTO.getUserId(), changePasswordDTO.getNewPassword());
            return UserDTOEntityMapper.getDTOCompleteFromUser(user);
        }
        else throw new BusinessException("Password error", "The old password is wrong");
    }

    @Override
    public EchipaDTO addUserToEchipa(AddUserToEchipaDTO addUserToEchipaDTO) throws BusinessException {
        User user = userDao.findUserByUsername(addUserToEchipaDTO.getUsername());
        Echipa echipa = echipaDao.findEchipaByNume(addUserToEchipaDTO.getNumeEchipa());
        if (user == null || echipa == null)
            return null;
        Set<User> users = echipa.getUsers();
        users.add(user);
        echipa.setUsers(users);
        echipaDao.save(echipa);
        EchipaDTO dtoPersisted = EchipaDTOEntityMapper.getDTOFromEchipa(echipa);
        return dtoPersisted;
    }

    @Override
    public UserDTO deleteUserByUsername(String username) throws BusinessException {
        User user = userDao.findUserByUsername(username);
        if (user == null)
            return null;
        UserDTO deletedUser = UserDTOEntityMapper.getDTOFromUser(user);
        if (username.equals(user.getUsername())){
            userDao.delete(user);
        }
        return deletedUser;
    }
}
