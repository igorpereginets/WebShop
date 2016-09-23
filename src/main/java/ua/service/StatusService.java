package ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.DTO.FilterForms.adminFilter.StatusFilterForm;
import ua.DTO.SaveForms.StatusSaveForm;
import ua.entity.Status;
import ua.repository.StatusRepository;
import ua.service.specification.adminFilterAdapter.StatusFilterAdapter;

@Service
public class StatusService {

	@Autowired
	private StatusRepository statusRepository;
	
	public Iterable<Status> findAll() {
		return statusRepository.findAll();
	}

	public Status save(StatusSaveForm statusSaveForm) {
		Status status = new Status();
		status.setId(statusSaveForm.getId());
		status.setStatus(statusSaveForm.getStatus());
		return statusRepository.save(status);
	}
	
	public StatusSaveForm findWithSaveForm(int id) {
		Status status = statusRepository.findOne(id);
		StatusSaveForm statusSaveForm = new StatusSaveForm();
		statusSaveForm.setId(status.getId());
		statusSaveForm.setStatus(status.getStatus());
		return statusSaveForm;
	}

	public void delete(int id) {
		statusRepository.delete(id);
	}

	public Page<Status> findAll(Pageable pageable) {
		return statusRepository.findAll(pageable);
	}

	public Page<Status> findAll(Pageable pageable, StatusFilterForm filter) {
		return statusRepository.findAll(new StatusFilterAdapter(filter), pageable);
	}

	public Status findByStatus(String status) {
		return statusRepository.findByStatus(status);
	}

	public Status findOne(int id) {
		return statusRepository.findOne(id);
	}
}
