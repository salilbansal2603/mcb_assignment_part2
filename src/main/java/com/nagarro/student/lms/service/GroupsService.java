package com.nagarro.student.lms.service;

import java.util.List;

import com.nagarro.student.lms.exception.RecordNotFoundException;

import dto.GroupsDTO;

public interface GroupsService {
	public GroupsDTO saveOrUpdate(GroupsDTO groups);

	public List<GroupsDTO> getAllGroups();

	public GroupsDTO getGroupsById(int id) throws RecordNotFoundException;

	public void delete(int id);

}
