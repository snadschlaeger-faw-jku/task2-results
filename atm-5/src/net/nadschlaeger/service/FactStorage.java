package net.nadschlaeger.service;

import java.util.ArrayList;
import java.util.List;

import net.nadschlaeger.model.Fact;

public class FactStorage {

	private final List<Fact>facts=new ArrayList<>();

	private final String type;

	public FactStorage(String type) {
		super();
		this.type = type;
	}



	public void assertFact(Fact fact) {
		facts.add(fact);
		//		System.out.println(toString());

	}



	public void clear() {
		//		System.out.println("FactStorage [type=" + type + ", clear");
		facts.clear();

	}



	public List<Fact> getAllFacts() {
		return facts;
	}

	@Override
	public String toString() {
		return "FactStorage [type=" + type + ", facts=" + facts + "]";
	}




}
