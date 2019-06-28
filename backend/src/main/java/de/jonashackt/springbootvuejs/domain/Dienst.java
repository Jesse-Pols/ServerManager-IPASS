package de.jonashackt.springbootvuejs.domain;

public class Dienst {

	private int id;
	private String name, key, status, relevance;
	// Status isn't saved in the database
	// Relevance tells us which usergroup would be most interested in this Dienst
	
	public Dienst() { }
	
	public Dienst(String name) {		
		this.name = name;
	}
	
	public Dienst(String name, String key) {
		this.name = name;
		this.key = key;
	}
	
	public Dienst(String name, String key, String relevance) {
		this.name = name;
		this.key = key;
		this.relevance = relevance;
	}
	
	public Dienst(int id, String name, String key, String relevance) {
		this.id = id;
		this.name = name;
		this.key = key;
		this.relevance = relevance;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getRelevance() {
		return relevance;
	}
	
	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}

	public String toString() {
		String s = "";
		s += "ID: " + this.id;
		s += "\nName: " + this.name;
		s += "\nKey: " + this.key;
		s += "\nStatus: " + this.status;
		
		return s;
	}
	
	public boolean equals(Object object) {
		if (object instanceof Dienst) {
			Dienst dienst = (Dienst) object;
			if (dienst.getId() == this.id &&
					dienst.getName().equals(this.name) &&
					dienst.getKey().equals(this.key) &&
					dienst.getStatus().equals(this.status))
				return true;
		}
		return false;
	}

}
