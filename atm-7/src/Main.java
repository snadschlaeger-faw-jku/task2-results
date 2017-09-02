import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.nadschlaeger.ClauseOperators;
import net.nadschlaeger.InferenceEngine;
import net.nadschlaeger.RuleOperator;
import net.nadschlaeger.model.Condition;
import net.nadschlaeger.model.Consequence;
import net.nadschlaeger.model.Fact;
import net.nadschlaeger.model.Rule;
import net.nadschlaeger.model.Slot;
import net.nadschlaeger.service.FactStorage;
import net.nadschlaeger.service.KnowledgeBase;

public class Main {

	public static void main(String[] args) {

		final List<Fact> facts = new ArrayList<>();


		final Slot cardSlot = new Slot("cardNumber", false, 123, true, "java.lang.Integer");
		final Fact cardFact = new Fact("CreditCard", Arrays.asList(cardSlot));
		facts.add(cardFact);
		facts.add(new Fact("PIN",
				Arrays.asList(cardSlot, new Slot("PinNumber", false, "1234", true, "java.lang.String"))));

		final Slot inputPin = new Slot("PinNumber", true, null, true, "java.lang.String");
		inputPin.setText("Please enter pinNumber:");
		final Fact pin = new Fact("PIN", Arrays.asList(cardSlot, inputPin));



		final Rule creditCardExists = new Rule(RuleOperator.AND,
				Arrays.asList(new Condition(ClauseOperators.take, new Fact("CreditCard", Arrays.asList())), new Condition(ClauseOperators.exists, null)),
				Arrays.asList(new Consequence(ClauseOperators.assertFact, pin)), "creditCardExists");


		final List<Rule>rules=new ArrayList<>();
		final KnowledgeBase kb=new KnowledgeBase();
		rules.add(creditCardExists);

		final Slot slot = new Slot("Money", true, null, true, "java.lang.String");
		slot.setText("Withdraw Money:");
		final Fact w = new Fact("WithDrawMoney", Arrays.asList(slot));

		final Rule r = new Rule(RuleOperator.AND, Arrays.asList(

				new Condition(ClauseOperators.exists, pin)),
				Arrays.asList(new Consequence(ClauseOperators.assertFact, w),
						new Consequence(ClauseOperators.print, new Fact("Finished", null))),
				"all");
		rules.add(r);

		kb.setFacts(facts);
		kb.setRules(rules);

		System.out.println("Starting IE");
		final InferenceEngine ie = new InferenceEngine(kb, new FactStorage("runtime"));
		ie.trigger(cardFact);
	}

}
