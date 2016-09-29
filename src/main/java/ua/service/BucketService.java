package ua.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Bucket;
import ua.entity.Goods;
import ua.entity.User;
import ua.repository.BucketRepository;

@Service
public class BucketService {

	@Autowired
	private BucketRepository bucketRepository;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private UserService userService;
	@Autowired
	private StateService stateService;

	public Bucket save(Bucket bucket) {
		return bucketRepository.save(bucket);
	}

	public Bucket findByUserLogin(String login) {
		return bucketRepository.findByUserLogin(login);
	}

	public Bucket addGoods(int id, String login) {
		Goods goods = goodsService.findOne(id);
		Bucket bucket = findByUserLogin(login);
		if(goods.getUser().getLogin().equals(goods.getUser().getLogin())) {
			bucket.getGoods().add(goods);
			return save(bucket);
		}
		return bucket;
	}
	
	@Transactional
	public void buyGoods(int goodsId, String login) {
		User user = userService.findByLogin(login);
		Goods goods = goodsService.findOne(goodsId);
		Bucket bucket = findByUserLogin(login);
		
		if(user.getMoney() >= goods.getPrice() && !user.getLogin().equals(goods.getUser().getLogin())) {
			user.setMoney(user.getMoney() - goods.getPrice());
			goods.setState(stateService.findByName("SOLD"));
			goods.setActive(false);
			bucket.getGoods().remove(goods);
			userService.save(user);
			goodsService.save(goods);
			save(bucket);
		}
	}
	
	public long getGoodsCount(Principal principal) {
		if(principal != null)
			return bucketRepository.countGoods(principal.getName());
		return 0;
	}
	
	public Bucket removeFromBucket(int goodsId, String login) {
		Bucket bucket = findByUserLogin(login);
		bucket.getGoods().remove(goodsService.findOne(goodsId));
		return save(bucket);
	}
}
