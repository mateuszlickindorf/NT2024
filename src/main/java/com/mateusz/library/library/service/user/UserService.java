package com.mateusz.library.library.service.user;

import com.mateusz.library.library.commonTypes.UserRole;
import com.mateusz.library.library.controller.dto.user.GetUserFullDto;
import com.mateusz.library.library.exceptions.ReturnPending;
import com.mateusz.library.library.exceptions.UserNotFoundForId;
import com.mateusz.library.library.exceptions.UserNotFoundForUsername;
import com.mateusz.library.library.infrastructure.entity.AuthEntity;
import com.mateusz.library.library.infrastructure.entity.LoanEntity;
import com.mateusz.library.library.infrastructure.entity.UserEntity;
import com.mateusz.library.library.infrastructure.repository.AuthRepository;
import com.mateusz.library.library.infrastructure.repository.LoanRepository;
import com.mateusz.library.library.infrastructure.repository.ReviewRepository;
import com.mateusz.library.library.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final LoanRepository loanRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public UserService(UserRepository userRepository, AuthRepository authRepository, LoanRepository loanRepository,
                       ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
        this.loanRepository = loanRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<GetUserFullDto> getAll() {
        ArrayList<UserEntity> users = (ArrayList<UserEntity>) userRepository.findAll();
        ArrayList<GetUserFullDto> getUserDTO = new ArrayList<>();
        for (UserEntity user : users) {
            AuthEntity auth = authRepository.findByUserId(user.getId()).orElseThrow(() -> UserNotFoundForId.create(user.getId()));
            getUserDTO.add(mapUser(user, auth));
        }
        return getUserDTO;
    }

    public GetUserFullDto getById(long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> UserNotFoundForId.create(id));
        AuthEntity authEntity = authRepository.findByUserId(id).orElseThrow(() -> UserNotFoundForId.create(id));
        return mapUser(userEntity, authEntity);
    }

    public GetUserFullDto getByUsername(String username) {
        AuthEntity authEntity = authRepository.findByUsername(username).orElseThrow(() -> UserNotFoundForUsername.create(username));
        UserEntity userEntity = userRepository.findById(authEntity.getId()).orElseThrow(() -> UserNotFoundForId.create(authEntity.getId()));
        return mapUser(userEntity, authEntity);
    }

    @Transactional
    public void deleteById(long id) {
        if (!userRepository.existsById(id)) {
            throw UserNotFoundForId.create(id);
        }
        boolean noPendingReturns = false;
        if (!loanRepository.findByUserId(id).isEmpty()) {
            List<LoanEntity> loans = loanRepository.findByUserId(id);
            for (LoanEntity loan : loans) {
                if (loan.getReturnDate() != null) {
                    noPendingReturns = true;
                } else {
                    throw ReturnPending.create(loan.getBook().getTitle());
                }
            }
        } else {
            authRepository.deleteByUserId(id);
            userRepository.deleteById(id);
        }
        if (noPendingReturns) {
            loanRepository.deleteByUserId(id);
            reviewRepository.deleteByUserId(id);
            authRepository.deleteByUserId(id);
            userRepository.deleteById(id);
        }
    }

    private GetUserFullDto mapUser(UserEntity user, AuthEntity auth) {
        String role;
        if (auth.getRole().equals(UserRole.ROLE_ADMIN)) {
            role = "ADMIN";
        } else {
            role = "READER";
        }
        return new GetUserFullDto(user.getId(), user.getName(), auth.getUsername(), user.getEmail(), role);
    }
}
