package net.nadschlaeger.model;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeBase {
	private List<Fact> facts;
	private List<Rule> rules;

	public KnowledgeBase() {
		facts = new ArrayList<Fact>();
		rules = new ArrayList<Rule>();
	}

	public List<Fact> getAllFacts() {
		return facts;
	}

	public Fact getFact(String name) {
		for (Fact fact : facts) {
			if (fact.getName().equals(name)) {
				return fact;
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
		for (Rule rule : rules) {
			if (rule.getName().equals(name)) {
				return rule;
			}
		}
		return null;
	}

	public void addRule(Rule rule) {
		rules.add(rule);
	}

	public void removeRule(Rule rule) {
		rules.remove(rule);
	}
}
