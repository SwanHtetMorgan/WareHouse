package dev.swanhtet.godaung.services.impl;

import dev.swanhtet.godaung.Authentication.TokenSerivceImpl;
import dev.swanhtet.godaung.dto.LoginRequst;
import dev.swanhtet.godaung.dto.request.UserDto;
import dev.swanhtet.godaung.model.Role;
import dev.swanhtet.godaung.model.User;
import dev.swanhtet.godaung.repo.RoleRepository;
import dev.swanhtet.godaung.repo.UserRepository;
import dev.swanhtet.godaung.services.interfaces.UserServices;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.util.validation.metadata.DatabaseException;
import org.hibernate.exception.DataException;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices {

  private final UserRepository userRepository;
  private final ModelMapper mapper;
  private final TokenSerivceImpl tokenService;
  private final RoleRepository roleRepository;

  @Override
  public boolean createUser(UserDto userDTO) {
    Role role =
        roleRepository
            .findById(userDTO.getRoleId())
            .orElseThrow(() -> new RuntimeException("This position is not in the system"));
    User user =
        User.builder()
            .email(userDTO.getEmail())
            .password(BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt()))
            .username(userDTO.getUsername())
            .role(role)
            .build();
    try {
      userRepository.save(user);
      return true;
    } catch (EntityNotFoundException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public List<UserDto> getAllUsers() {
    return userRepository.findAll().stream().map(user -> mapper.map(user, UserDto.class)).toList();
  }

  @Override
  public UserDto getUserById(Long id) {
    Optional<User> userOptional = userRepository.findById(Integer.parseInt(String.valueOf(id)));
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      return mapper.map(user, UserDto.class);
    }
    return null;
  }

  @Override
  public boolean updateUser(Integer id, UserDto userDTO) {
    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      mapper.map(userDTO, user);
      userRepository.save(user);
      return true;
    }
    return false;
  }

  @Override
  public boolean deleteUser(Integer id) {
    try {
      userRepository.deleteById(id);
      return true;
    } catch (DataException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public String authenticateUser(LoginRequst loginRequst) {
    try {

      User user =
          userRepository
              .findByEmail(loginRequst.getEmail())
              .orElseThrow(() -> new RuntimeException("No user with this email registered"));
      if (BCrypt.checkpw(loginRequst.getPassword(), user.getPassword())) {

        return tokenService.finalTokenGeneration(
            tokenService.tokenPreparation(user.getEmail(), user.getRole().getRoleName()));
      } else {

        throw new RuntimeException("Invalid password");
      }
    } catch (DatabaseException e) {
      throw new RuntimeException(STR."Database error: \{e.getMessage()}", e);
    }
  }

  @Override
  public UserDto findById(Integer id) {
    Optional<User> userOptional = userRepository.findById(id);

    return userOptional.map(user -> mapper.map(user, UserDto.class)).orElse(null);
  }
}
