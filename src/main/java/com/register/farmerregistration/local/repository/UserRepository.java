package com.register.farmerregistration.local.repository;


import com.register.farmerregistration.local.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.yuequan.jpa.soft.delete.repository.SoftDelete;

import java.util.List;

/**
 * Generated by Spring Data Generator on 14/05/2019
 */
@SoftDelete
@Repository("localUserRepository")
public interface UserRepository extends BaseRepository<User, Integer> {
    @Query("SELECT COUNT(u) FROM User u")
    Long getTotalUsers();


    List<User> findAllByUserType(String userType);

//    @Query("SELECT u FROM User u where u.isLogin = 'True'")
//    User finUserSignIn();
//
//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query("UPDATE User SET isLogin = 'True' where email =:email and password = :password")
//    void setUserAsSignIn(@Param("email") String email, @Param("password") String password);
//
//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query("UPDATE User SET isLogin = 'False' where isLogin = 'True'")
//    void setUserAsSignOut();

    User findByEmail(String email);

    //User findByPhone(String phone);

    User findByEmailAndPassword(String email, String password);

}
