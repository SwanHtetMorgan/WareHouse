package dev.swanhtet.godaung.services.impl;

import dev.swanhtet.godaung.dto.RoleDto;
import dev.swanhtet.godaung.model.Role;
import dev.swanhtet.godaung.repo.RoleRepository;
import dev.swanhtet.godaung.services.interfaces.RoleServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleServices {
	private  final ModelMapper modelMapper;
	private final RoleRepository roleRepository;
	@Override
	public boolean createNewRole(RoleDto roleDto) {
		try {
			Role role	= new Role();
			role.setRoleName(roleDto.getRoleName());
			roleRepository.save(role);
			return true;
		}catch (EntityNotFoundException e){
			throw  new RuntimeException(e.getMessage());
		}



	}

}
