package ua.service.specification.adminFilterAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.DTO.FilterForms.adminFilter.UserFilterForm;
import ua.entity.User;

public class UserFilterAdapter implements Specification<User> {

	private final UserFilterForm form;
	private List<Specification<User>> filters;

	public UserFilterAdapter(UserFilterForm form) {
		if (form != null)
			this.form = form;
		else
			this.form = new UserFilterForm();
		this.filters = new ArrayList<>();
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		findByEmail();
		findByLogin();
		findByFirstName();
		findByLastName();
		findByTelephone();
		findByGender();
		findBybirthday();
		findByMoney();
		findByRate();
		findByStatus();
		if (!filters.isEmpty()) {
			Specifications<User> specifications = Specifications.where(filters.get(0));
			for (Specification<User> filter : filters) {
				specifications = specifications.and(filter);
			}
			return specifications.toPredicate(root, query, cb);
		}
		return null;
	}

	private void findByStatus() {
		String status = form.getStatusSearch();
		if (status != null && !status.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> statusExp = root.get("status").<String> get("status");
				return cb.like(cb.lower(statusExp), status.toLowerCase() + "%");
			});
		}
	}

	private void findByRate() {
		Double minRate = form.getMinRate();
		Double maxRate = form.getMaxRate();
		if (maxRate != null && minRate != null && maxRate > 0 && minRate > 0) {
			filters.add((root, query, cb) -> {
				return cb.between(root.<Double> get("rate"), minRate, maxRate);
			});
		} else if (minRate != null && minRate > 0) {
			filters.add((root, query, cb) -> {
				return cb.ge(root.<Double> get("rate"), minRate);
			});
		} else if (maxRate != null && maxRate > 0) {
			filters.add((root, query, cb) -> {
				return cb.le(root.<Double> get("rate"), maxRate);
			});
		}
	}

	private void findByMoney() {
		Double maxMoney = form.getMaxMoney();
		Double minMoney = form.getMinMoney();
		if (maxMoney != null && minMoney != null && maxMoney > 0 && minMoney > 0) {
			if (maxMoney >= minMoney) {
				filters.add((root, query, cb) -> {
					return cb.between(root.<Double> get("money"), minMoney, maxMoney);
				});
			}
		} else if (minMoney != null && minMoney > 0) {
			filters.add((root, query, cb) -> {
				return cb.ge(root.<Double> get("money"), minMoney);
			});
		} else if (maxMoney != null && maxMoney > 0) {
			filters.add((root, query, cb) -> {
				return cb.le(root.<Double> get("money"), maxMoney);
			});
		}
	}

	private void findBybirthday() {
		String birthday = form.getBirthdaySearch();
		if (birthday != null && !birthday.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> birthdayExp = root.<String> get("birthday");
				return cb.like(cb.lower(birthdayExp), birthday.toLowerCase() + "%");
			});
		}
	}

	private void findByGender() {
		String gender = form.getGenderSearch();
		if(gender != null && !gender.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<Boolean> genderExp = root.<Boolean> get("gender");
				if (gender.equals("M"))
					return cb.isTrue(genderExp);
				else
					return cb.isFalse(genderExp);
			});
		}
	}

	private void findByTelephone() {
		String telephone = form.getTelephoneSearch();
		if (telephone != null && !telephone.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> telephoneExp = root.<String> get("telephone");
				return cb.like(cb.lower(telephoneExp), telephone.toLowerCase() + "%");
			});
		}
	}

	private void findByLastName() {
		String lastName = form.getLastNameSearch();
		if (lastName != null && !lastName.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> lastNameExp = root.<String> get("lastName");
				return cb.like(cb.lower(lastNameExp), lastName.toLowerCase() + "%");
			});
		}
	}

	private void findByFirstName() {
		String firstName = form.getFirstNameSearch();
		if (firstName != null && !firstName.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> firstNameExp = root.<String> get("firstName");
				return cb.like(cb.lower(firstNameExp), firstName.toLowerCase() + "%");
			});
		}
	}

	private void findByLogin() {
		String login = form.getLoginSearch();
		if (login != null && !login.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> loginExp = root.<String> get("login");
				return cb.like(cb.lower(loginExp), login.toLowerCase() + "%");
			});
		}
	}

	private void findByEmail() {
		String email = form.getEmailSearch();
		if (email != null && !email.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> emailExp = root.<String> get("email");
				return cb.like(cb.lower(emailExp), email.toLowerCase() + "%");
			});
		}
	}

}
