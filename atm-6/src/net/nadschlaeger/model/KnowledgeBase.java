package net.nadschlaeger.model;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeBase {

	private List<Fact> facts;

	private List<Rule> rules;

	public KnowledgeBase() {
		initData();
	}

	private void initData() {
		facts = new ArrayList<>();

		rules = new ArrayList<>();
	}

	public void setFacts(List<Fact> facts) {
		this.facts = facts;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public List<Fact> getAllFacts() {
		return facts;
	}

	public Fact getFact(String name) {
		for (Fact f : facts) {
			if (f.getName().equals(name)) {
				return f;
			}
		}
		return null;
	}

	public void addFact(Fact fact) {
		facts.add(fact);
	}

	public void removeFact(Fact fact) {
		facts.remove(fact);
	}

	public List<Rule> getAllRules() {
		return rules;
	}

	public Rule getRule(String name) {
		for (Rule r : rules) {
			if (r.getName().equals(name)) {
				return r;
			}
		}
		return null;
	}

	public void addFact(Rule rule) {
		rules.add(rule);
	}

	public void removeFact(Rule rule) {
		rules.remove(rule);
	}

}
