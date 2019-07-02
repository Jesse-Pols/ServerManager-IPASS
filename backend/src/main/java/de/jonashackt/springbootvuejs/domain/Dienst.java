package de.jonashackt.springbootvuejs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diensten")
public class Dienst {

	// Primary Key
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name, key, status, relevance;

	// Status isn't saved in the database
	// Relevance tells us which usergroup would be most interested in this Dienst
	
	public Dienst() { }

	public Dienst(int id) {
		this.id = id;
	}

	public Dienst(int id, String name, String key) {
		this.id = id;
		this.name = name;
		this.key = key;
	}

	public Dienst(int id, String name, String key, String relevance) {
		this.id = id;
		this.name = name;
		this.key = key;
		this.relevance = relevance;
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

	@Override
	public String toString() {
		return String.format(
			"Dienst[id=%d, name='%s', key='%s', relevance='%s']",
			id, name, key, relevance
		);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getRelevance() {
		return this.relevance;
	}

	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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
