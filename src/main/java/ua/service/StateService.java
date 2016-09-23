package ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.DTO.FilterForms.adminFilter.StateFilterForm;
import ua.DTO.SaveForms.StateSaveForm;
import ua.entity.State;
import ua.repository.StateRepository;
import ua.service.specification.adminFilterAdapter.StateFilterAdapter;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;

	public Iterable<State> findAll() {
		return stateRepository.findAll();
	}

	public State save(StateSaveForm stateSaveForm) {
		State state = new State();
		state.setId(stateSaveForm.getId());
		state.setName(stateSaveForm.getName());
		return stateRepository.save(state);
	}
	
	public StateSaveForm findWithSaveForm(int id) {
		State state = stateRepository.findOne(id);
		StateSaveForm stateSaveForm = new StateSaveForm();
		stateSaveForm.setId(state.getId());
		stateSaveForm.setName(state.getName());
		return stateSaveForm;
	}

	public void delete(int id) {
		stateRepository.delete(id);
	}

	public Page<State> findAll(Pageable pageable) {
		return stateRepository.findAll(pageable);
	}

	public Page<State> findAll(Pageable pageable, StateFilterForm filter) {
		return stateRepository.findAll(new StateFilterAdapter(filter), pageable);
	}

	public State findByName(String name) {
		return stateRepository.findByName(name);
	}

	public State findOne(int id) {
		return stateRepository.findOne(id);
	}
}
