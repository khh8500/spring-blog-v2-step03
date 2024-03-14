package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.board.Board;

@RequiredArgsConstructor
@Repository // new BoardRepository() -> IoC 컨테이너 등록
public class UserRepository {
    private final EntityManager em; // DI

    @Transactional
    public void join(User user){
        em.persist(user);
    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO reqDTO){
        Query query = em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class);
        query.setParameter("username", reqDTO.getUsername());
        query.setParameter("password", reqDTO.getPassword());
        return (User) query.getSingleResult();
    }
}
