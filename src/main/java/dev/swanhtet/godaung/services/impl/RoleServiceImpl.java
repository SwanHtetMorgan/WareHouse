package dev.swanhtet.godaung.services.impl;

import dev.swanhtet.godaung.dto.RoleDto;
import dev.swanhtet.godaung.model.Role;
import dev.swanhtet.godaung.model.User;
import dev.swanhtet.godaung.repo.RoleRepository;
import dev.swanhtet.godaung.repo.UserRepository;
import dev.swanhtet.godaung.services.interfaces.RoleServices;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleServices {
  private final ModelMapper modelMapper;
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;

  @Override
  public boolean createNewRole(RoleDto roleDto) {
    try {
      Role role = new Role();
      role.setRoleName(roleDto.getRoleName());
      roleRepository.save(role);
      return true;
    } catch (EntityNotFoundException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public boolean roleRemoval(Integer roleId) {

    Optional<Role> roleOptional = roleRepository.findById(roleId);

    if (roleOptional.isPresent()) {
      List<User> userList = userRepository.findByRole(roleOptional.get());
      for (User user : userList) {
        user.setRole(null);
      }
      userRepository.saveAll(userList);
      roleRepository.deleteById(roleId);
      return true;
    }

    return false;
  }
}
