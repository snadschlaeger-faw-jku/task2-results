package at.snadschlaeger.inference;

import org.junit.Test;

import net.snadschlaeger.inference.BruteForceIE;
import net.snadschlaeger.inference.ClauseOperator;
import net.snadschlaeger.inference.InferenceEngine;
import net.snadschlaeger.inference.RuleOperator;
import net.snadschlaeger.inference.memory.KnowledgeBase;
import net.snadschlaeger.inference.memory.KnowledgeBaseImpl;
import net.snadschlaeger.inference.memory.WorkingMemory;
import net.snadschlaeger.inference.memory.WorkingMemoryImpl;
import net.snadschlaeger.inference.model.Clause;
import net.snadschlaeger.inference.model.Fact;
import net.snadschlaeger.inference.model.Rule;
import net.snadschlaeger.inference.model.Slot;

public class InferenceTest {

	@Test
	public void evaluate() {
		KnowledgeBase kb = new KnowledgeBaseImpl();

		// cards
		kb.addFact(Fact.create("CreditCard1").addSlot(Slot.create("cardNumber", String.class, "123"))
				.addSlot(Slot.create("active", Boolean.class, true)));
		kb.addFact(Fact.create("CreditCard2").addSlot(Slot.create("cardNumber", String.class, "234"))
				.addSlot(Slot.create("active", Boolean.class, true)));
		kb.addFact(Fact.create("CreditCard3").addSlot(Slot.create("cardNumber", String.class, "345"))
				.addSlot(Slot.create("active", Boolean.class, false)));

		// pins
		kb.addFact(Fact.create("Pin1").addSlot(Slot.create("cardNumber", String.class, "123"))
				.addSlot(Slot.create("pinNumber", String.class, "123")));
		kb.addFact(Fact.create("Pin2").addSlot(Slot.create("cardNumber", String.class, "234"))
				.addSlot(Slot.create("pinNumber", String.class, "234")));
		kb.addFact(Fact.create("Pin3").addSlot(Slot.create("cardNumber", String.class, "345"))
				.addSlot(Slot.create("pinNumber", String.class, "345")));

		Fact pin = Fact.create("Pin1").addSlot(Slot.create("cardNumber", String.class, "123"))
				.addSlot(Slot.create("pinNumber", String.class));

		kb.addRule(Rule.create("CheckCreditCard").setOperator(RuleOperator.AND)
				.addCondition(Clause.create(ClauseOperator.exists).setParameter(kb.getFact("CreditCard1")))
				.addConsequence(Clause.create(ClauseOperator.assertFact).setParameter(pin)));

		Fact withdrawal = Fact.create("Withdrawal").addSlot(Slot.create("amount", Integer.class));

		kb.addRule(Rule.create("CheckPin").setOperator(RuleOperator.AND)
				.addCondition(Clause.create(ClauseOperator.take).setParameter(pin))
				.addCondition(Clause.create(ClauseOperator.exists).setParameter(pin))
				.addConsequence(Clause.create(ClauseOperator.assertFact).setParameter(withdrawal)));

		kb.addRule(Rule.create("PrintOut").setOperator(RuleOperator.AND)
				.addCondition(Clause.create(ClauseOperator.take).setParameter(withdrawal))
				.addConsequence(Clause.create(ClauseOperator.print).setParameter(Fact.create("Withdrawal successful!"))));

		WorkingMemory wm = new WorkingMemoryImpl(kb);
		InferenceEngine ie = new BruteForceIE(wm, kb);

		ie.trigger(kb.getFact("CreditCard1"));
	}

}
