package ua.DTO;

public class CategoryAndCount {

	private int id;
	private String name;
	private long count;

	public CategoryAndCount(int id, String name, long count) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
	}

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

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
