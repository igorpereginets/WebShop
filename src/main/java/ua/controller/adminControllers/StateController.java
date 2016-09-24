package ua.controller.adminControllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.DTO.FilterForms.adminFilter.StateFilterForm;
import ua.DTO.SaveForms.StateSaveForm;
import ua.entity.State;
import ua.service.StateService;
import ua.service.validators.adminValidators.StateValidator;

@Controller
public class StateController {

	@Autowired
	private StateService stateService;

	@ModelAttribute("stateSaveForm")
	public StateSaveForm getForm() {
		return new StateSaveForm();
	}

	@ModelAttribute("filter")
	public StateFilterForm getFilter() {
		return new StateFilterForm();
	}

	@InitBinder("stateSaveForm")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new StateValidator(stateService));
	}

	@RequestMapping("/admin/states")
	public String showStates(Model model, @PageableDefault(10) Pageable pageable, @ModelAttribute("filter") StateFilterForm filter) {
		Page<State> page = stateService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		return "states";
	}

	@RequestMapping(value = "/admin/states", method = RequestMethod.POST)
	public String saveState(@Valid @ModelAttribute("stateSaveForm") StateSaveForm stateSaveForm, BindingResult bindingResult, HttpServletRequest request, Model model,
			@PageableDefault(10) Pageable pageable, @ModelAttribute("filter") StateFilterForm filter) {
		if (bindingResult.hasErrors()) {
			Page<State> page = stateService.findAll(pageable, filter);
			model.addAttribute("page", page);
			model.addAttribute("countPages", page.getTotalPages());
			return "states";
		}
		stateService.save(stateSaveForm);
		return "redirect:/admin/states?" + request.getQueryString();
	}

	@RequestMapping("/admin/states/update/{id}")
	public String updateState(@PathVariable int id, Model model, Pageable pageable, @ModelAttribute("filter") StateFilterForm filter) {
		model.addAttribute("stateSaveForm", stateService.findWithSaveForm(id));
		Page<State> page = stateService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		return "states";
	}

	@RequestMapping("/admin/states/delete/{id}")
	public String deleteState(@PathVariable int id, HttpServletRequest request) {
		stateService.delete(id);
		return "redirect:/admin/states?" + request.getQueryString();
	}
}
