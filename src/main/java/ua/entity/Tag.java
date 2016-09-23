package ua.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(unique = true, nullable = false, length = 15)
  private String name;
  @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
  private List<Goods> goods;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Goods> getGoods() {
    return goods;
  }

  public void setGoods(List<Goods> goods) {
    this.goods = goods;
  }

}
