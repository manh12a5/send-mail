package com.example.sendmail.repository;

import com.example.sendmail.appuser.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByToken(String token);

    @Query(value = "SELECT * FROM confirmation_token INNER JOIN app_user ON app_user.id = confirmation_token.user_id",
            nativeQuery = true)
    Optional<ConfirmationToken> findByAppUserId(Long id);
}
