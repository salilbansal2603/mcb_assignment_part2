package com.nagarro.student.lms.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.student.lms.entity.Groups;
import com.nagarro.student.lms.exception.RecordNotFoundException;
import com.nagarro.student.lms.repository.GroupsRepository;
import com.nagarro.student.lms.service.GroupsService;

import dto.GroupsDTO;

@Service
public class GroupsServiceImpl implements GroupsService {

	@Autowired
	private GroupsRepository groupsRepository;

	@Override
	public GroupsDTO saveOrUpdate(GroupsDTO groups) {
		Groups groupsObj = null;
		if (groups.getGroupId() != null) {
			Optional<Groups> group = groupsRepository.findById(groups.getGroupId());
			if (group.isPresent()) {
				Groups groupO = group.get();
				if (groups.getName() != null) {
					groupO.setName(groups.getName());
				}
				groupsObj = groupsRepository.save(groupO);
			} else {
				throw new RecordNotFoundException("No Groups Present with this ID");
			}
		} else {
			groupsObj = groupsRepository.save(groups.toEntity());
		}
		return groupsObj.toDTO();
	}

	@Override
	public List<GroupsDTO> getAllGroups() {
		return groupsRepository.findAll().parallelStream().map(t -> t.toDTO()).collect(Collectors.toList());
	}

	@Override
	public GroupsDTO getGroupsById(int id) throws RecordNotFoundException {
		Optional<Groups> groups = groupsRepository.findById(id);
		if (groups.isPresent()) {
			return groups.get().toDTO();
		} else {
			throw new RecordNotFoundException("No Group Present with this ID");
		}
	}

	@Override
	public void delete(int id) {
		groupsRepository.deleteById(id);

	}

}
