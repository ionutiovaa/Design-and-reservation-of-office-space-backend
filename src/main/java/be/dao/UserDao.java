package be.dao;

import be.entity.User;
import be.entity.types.UserType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User findAllByID(Integer id);

    List<User> findAll();

    User findUserByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    User findUserByUsernameAndPassword(String username, String password);

    User findUserByFirstNameAndLastName(String firstName, String lastName);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET mobile_number = :mobileNumber, " +
            "email = :email, " +
            "password = :password, "+
            "user_type = :userType "+
            "WHERE ID = :id")
    int update(@Param("mobileNumber") String mobileNumber,
               @Param("email") String email,
               @Param("password") String password,
               @Param("userType") UserType userType);

    @Transactional
    @Modifying
    @Query("UPDATE User u " +
            "SET password = :newPassword " +
            "WHERE ID = :userId")
    int updatePassword(@Param("userId") Integer userId,
                       @Param("newPassword") String newPassword);

}
