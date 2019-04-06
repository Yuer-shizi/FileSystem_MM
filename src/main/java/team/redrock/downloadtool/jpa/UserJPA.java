package team.redrock.downloadtool.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team.redrock.downloadtool.entity.User;

import java.io.Serializable;


public interface UserJPA extends
            JpaRepository<User, Long>,
            JpaSpecificationExecutor<User>,
            Serializable {


    User findByUsername(String username);

    @Modifying
    @Query(value = "update user set password = '123456' where id = ?1", nativeQuery = true)
    int resetPassword(Long id);
}
