package main.java;

import main.java.model.Clause;
import main.java.model.Fact;
import main.java.model.Rule;
import main.java.model.Slot;

public class Test {

	private static BruteForceIE b = new BruteForceIE();

	private static void initData() {
		Fact fact = new Fact("creditCard");
		Slot slot = new Slot("cardNumber", "java.lang.String", "123", true);
		fact.slots.add(slot);
		Slot slot1 = new Slot("cardActive", "java.lang.Boolean", true,false);
		fact.slots.add(slot1);
		
//		Fact fact2 = new Fact("pin");
//		Slot slot2 = new Slot("cardNumber", "java.lang.String", "123", true);
//		fact2.slots.add(slot2);
//		Slot slot3 = new Slot("pinCode", "java.lang.String", "123",true);
//		fact2.slots.add(slot3);
	
		KnowledgeBase.facts.add(fact);
	//	KnowledgeBase.facts.add(fact2);

	}

	private static void initRules() {
		Rule rule = new Rule();
		rule.logicalOperator=RuleOperator.AND;
		rule.name = "checkCard";
		
		Fact card123Fact = new Fact("creditCard");
		Slot card123Slot = new Slot("cardNumber", "java.lang.String", "123", true);
		card123Fact.slots.add(card123Slot);
		Slot card123ActiveSlot = new Slot("active", "java.lang.Boolean", true, false);
		card123Fact.slots.add(card123ActiveSlot);
		Clause condition = new Clause(ClauseOperators.exists, card123Fact);
		Clause consequence = new Clause(ClauseOperators.print, "Hello world");
		rule.conditions.add(condition);
		rule.consequences.add(consequence);
		KnowledgeBase.rules.add(rule);
	}
	
	private static void initRules1() {
		Rule rule = new Rule();
		rule.logicalOperator=RuleOperator.AND;
		rule.name = "checkCard";
		
		Fact card123Fact = new Fact("creditCard");
		Slot card123Slot = new Slot("cardNumber", "java.lang.String", "123", true);
		card123Fact.slots.add(card123Slot);
		Slot card123ActiveSlot = new Slot("active", "java.lang.Boolean", true, false);
		card123Fact.slots.add(card123ActiveSlot);
		Clause condition = new Clause(ClauseOperators.exists, card123Fact);
		
		Fact card123Pin = new Fact("pin");
		//Slot card123CardNumber = new Slot("cardNumber", "java.lang.String", "123", true);
		Slot card123PinCode = new Slot("pinCode", "java.lang.String", null, true);
		card123Pin.slots.add(card123PinCode);
		//card123Pin.slots.add(card123CardNumber);
		
		Clause consequence = new Clause(ClauseOperators.assertFact, card123Pin);
		rule.conditions.add(condition);
		rule.consequences.add(consequence);
		KnowledgeBase.rules.add(rule);
	}
	
	private static void initRules2() {
		Rule rule = new Rule();
		rule.logicalOperator=RuleOperator.AND;
		rule.name = "checkCard";
		
		Fact card123Fact = new Fact("creditCard");
		Slot card123Slot = new Slot("cardNumber", "java.lang.String", "123", true);
		card123Fact.slots.add(card123Slot);
		Slot card123ActiveSlot = new Slot("active", "java.lang.Boolean", true, false);
		card123Fact.slots.add(card123ActiveSlot);
		Clause condition = new Clause(ClauseOperators.exists, card123Fact);
		
		Fact card123Pin = new Fact("pin");
		//Slot card123PinCardNumber = new Slot("cardNumber", "java.lang.String", "123", true);
		//card123Pin.slots.add(card123PinCardNumber);
		Slot card123PinCode = new Slot("pinCode", "java.lang.String", "123", true);
		card123Pin.slots.add(card123PinCode);
		Clause condition2 = new Clause(ClauseOperators.exists, card123Pin);
		rule.conditions.add(condition);
		rule.conditions.add(condition2);
		
		Clause consequence = new Clause(ClauseOperators.print, "OK");
		rule.consequences.add(consequence);
		
		KnowledgeBase.rules.add(rule);
	}

	public static void main(String[] args) {
		initData();
		initRules1();
		initRules2();
		b.checkRules();
	}

}
