package ua.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.Expression;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import ua.DTO.FilterForms.IndexGoodsFilterForm;
import ua.DTO.FilterForms.adminFilter.GoodsFilterForm;
import ua.DTO.SaveForms.GoodsSaveForm;
import ua.entity.Goods;
import ua.repository.GoodsRepository;
import ua.service.interfaces.FileWriter;
import ua.service.interfaces.FileWriter.Folder;
import ua.service.specification.IndexGoodsFilterAdapter;
import ua.service.specification.adminFilterAdapter.GoodsFilterAdapter;

@Service
public class GoodsService {

	@Autowired
	private GoodsRepository goodsRepository;
	@Autowired
	private FileWriter fileWriter;

	public Iterable<Goods> findAll() {
		return goodsRepository.findAll();
	}

	public Goods save(GoodsSaveForm goodsSaveForm) {
		if (goodsSaveForm == null)
			return null;

		Goods good = new Goods();
		good.setId(goodsSaveForm.getId());
		good.setAmount(goodsSaveForm.getAmount());
		good.setBrand(goodsSaveForm.getBrand());
		good.setCategory(goodsSaveForm.getCategory());
		good.setDescription(goodsSaveForm.getDescription());
		good.setName(goodsSaveForm.getName());
		good.setPrice(goodsSaveForm.getPrice());
		good.setShortDescription(goodsSaveForm.getShortDescription());
		good.setState(goodsSaveForm.getState());
		good.setUser(goodsSaveForm.getUser());
		good.setTags(goodsSaveForm.getTags());

		good.setActive(true);
		if (goodsSaveForm.getBegin() == null)
			good.setBegin(LocalDate.now());
		else
			good.setBegin(goodsSaveForm.getBegin());
		good.setEnd(goodsSaveForm.getEnd());
		good = goodsRepository.saveAndFlush(good);

		String pathToFile;
		if (goodsSaveForm.getId() == 0)
			pathToFile = fileWriter.save(Folder.GOODS, goodsSaveForm.getFile(), good.getId());
		else if (goodsSaveForm.getFile().isEmpty())
			pathToFile = goodsSaveForm.getPathToFile();
		else
			pathToFile = fileWriter.update(Folder.GOODS, goodsSaveForm.getFile(), good.getId());

		good.setPathToFile(pathToFile);
		return goodsRepository.save(good);
	}

	public GoodsSaveForm findWithSaveForm(int id) {
		Goods good = goodsRepository.findOneWithTags(id);
		if (good == null)
			return null;

		GoodsSaveForm goodsSaveForm = new GoodsSaveForm();
		goodsSaveForm.setId(good.getId());
		goodsSaveForm.setAmount(good.getAmount());
		goodsSaveForm.setBrand(good.getBrand());
		goodsSaveForm.setCategory(good.getCategory());
		goodsSaveForm.setDescription(good.getDescription());
		goodsSaveForm.setName(good.getName());
		goodsSaveForm.setPrice(good.getPrice());
		goodsSaveForm.setShortDescription(good.getShortDescription());
		goodsSaveForm.setState(good.getState());
		goodsSaveForm.setUser(good.getUser());
		goodsSaveForm.setTags(good.getTags());

		goodsSaveForm.setBegin(good.getBegin());
		goodsSaveForm.setEnd(good.getEnd());
		goodsSaveForm.setActive(good.isActive());
		goodsSaveForm.setPathToFile(good.getPathToFile());
		return goodsSaveForm;
	}

	public void delete(int id) {
		File pathToFolder = fileWriter.getPathToFolder(Folder.GOODS, id);
		try {
			FileUtils.deleteDirectory(pathToFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
		goodsRepository.delete(id);
	}

	public Page<Goods> findAll(Pageable pageable) {
		return goodsRepository.findAll(pageable);
	}

	// TODO Make a hierarchy to manage these two methods in one
	public Page<Goods> findAll(Pageable pageable, GoodsFilterForm filter) {
		return goodsRepository.findAll(new GoodsFilterAdapter(filter), pageable);
	}

	// public Page<Goods> findAll(Pageable pageable, IndexGoodsFilterForm filter)
	// {
	// return goodsRepository.findAll(new IndexGoodsFilterAdapter(filter),
	// pageable);
	// }

	public Goods findOne(int id) {
		return goodsRepository.findOne(id);
	}

	public List<Goods> findByUserLogin(String login) {
		if (login == null || login.isEmpty())
			return null;
		return goodsRepository.findByUserLogin(login);
	}

	public Page<Goods> findAllNotSold(Pageable pageable, IndexGoodsFilterForm filter) {
		IndexGoodsFilterAdapter specification = new IndexGoodsFilterAdapter(filter);
		Specifications<Goods> spec = Specifications.where(specification).and((root, query, cb) -> {
			Expression<String> stateExp = root.get("state").<String> get("name");
			return cb.notEqual(stateExp, "SOLD");
		});
		return goodsRepository.findAll(spec, pageable);
	}

	public Goods save(Goods goods) {
		return goodsRepository.save(goods);
	}
}
