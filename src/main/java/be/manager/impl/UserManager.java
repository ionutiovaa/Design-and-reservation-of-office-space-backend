package be.manager.impl;

import be.dao.EchipaDao;
import be.dao.UserDao;
import be.dao.UtilizareDao;
import be.dto.AddUserToEchipaDTO;
import be.dto.ChangePasswordDTO;
import be.dto.EchipaDTO;
import be.dto.UserDTO;
import be.dtoEntityMappers.EchipaDTOEntityMapper;
import be.dtoEntityMappers.UserDTOEntityMapper;
import be.entity.Echipa;
import be.entity.Mail;
import be.entity.User;
import be.entity.types.UserType;
import be.exceptions.BusinessException;
import be.manager.remote.UserManagerRemote;
import be.service.MailService;
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

    @Autowired
    private UtilizareDao utilizareDao;

    public UserDao getUserDao() {
        return userDao;
    }

    private Logger logger = Logger.getLogger(UserManager.class.getName());

    @Override
    public UserDTO insertUser(UserDTO userDTO) throws BusinessException {
        User userFounded = userDao.findUserByUsername(userDTO.getUsername());
        if (userFounded != null)
            return null;
        User user = createUserToInsert(userDTO);
        User persistedUser = userDao.save(user);
        UserDTO dtoPersisted = UserDTOEntityMapper.getDTOFromUser(persistedUser);
        String name = userDTO.getFirstName() + " " + userDTO.getLastName();
        Mail mail = new Mail(userDTO.getEmail(), "Organizare birouri", "Hello, " + name + "\n" +
                "Welcome to Organizare birouri!\n" +
                "Thank you for choosing to use this application!\n" +
                "Enjoy it!");
        MailService.sendMail(mail);
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
        User user = userDao.findAllByID(changePasswordDTO.getUserId());
        if (user == null)
            throw new BusinessException("Not found", "This username doesn't exists.");
        if (user.getPassword().equals(changePasswordDTO.getOldPassword())){
            // Change password;
            user.setPassword(changePasswordDTO.getNewPassword());
            int updated = this.userDao.updatePassword(user.getID(), changePasswordDTO.getNewPassword());
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
    public UserDTO deleteUserById(Integer id) throws BusinessException {
        User user = userDao.findAllByID(id);
        List<Integer> utilizari = utilizareDao.getIdUtilizariByUser(user.getID());
        utilizari.forEach(integer -> utilizareDao.deleteFromLocuriUtilizariByUtilizare_id(integer));
        utilizareDao.deleteUtilizareByUser_id(user.getID());

        if (user == null)
            return null;
        UserDTO deletedUser = UserDTOEntityMapper.getDTOFromUser(user);
        if (id == user.getID()){
            userDao.delete(user);
        }
        return deletedUser;
    }

    @Override
    public UserType getUserType(String username) throws BusinessException {
        User user = userDao.findUserByUsername(username);
        return user.getUserType();
    }
}
