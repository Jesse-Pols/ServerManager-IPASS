package de.jonashackt.springbootvuejs.domain;

public class Dienst {

	private int dienstId;
	private String name, key, status, relevance;
	// Status isn't saved in the database
	// Relevance tells us which usergroup would be most interested in this Dienst
	
	public Dienst() {
		
	}
	
	public Dienst(String name, String status) {		
		this.name = name;
		this.status = status;		
	}
	
	public Dienst(int dienstId, String name, String key) {
		this.dienstId = dienstId;
		this.name = name;
		this.key = key;
	}	
	
	public int getDienstId() {
		return dienstId;
	}

	public void setDienstId(int dienstId) {
		this.dienstId = dienstId;
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
		s += "ID: " + this.dienstId;
		s += "\nName: " + this.name;
		s += "\nKey: " + this.key;
		s += "\nStatus: " + this.status;
		
		return s;
	}
	
	public boolean equals(Object object) {
		if (object instanceof Dienst) {
			Dienst dienst = (Dienst) object;
			if (dienst.getDienstId() == this.dienstId &&
					dienst.getName().equals(this.name) &&
					dienst.getKey().equals(this.key) &&
					dienst.getStatus().equals(this.status))
				return true;
		}
		return false;
	}

}
