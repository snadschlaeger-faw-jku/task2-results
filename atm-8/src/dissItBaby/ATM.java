package dissItBaby;

import java.util.Arrays;
import net.nadschlaeger.*;


public class ATM {

	public static void main(String[] arg)
	{	
	
		// listing1
		Fact fact = new Fact();
		fact.setName("credit-card");
		Slot cardNumberSlot = new Slot();
		
		cardNumberSlot.setName("card-Number");
		cardNumberSlot.setType ("java.lang.Integer");
		cardNumberSlot.setValue(123);
		
		Slot cardActiveSlot = new Slot();
		cardActiveSlot.setName("card-active");
		cardActiveSlot.setType("java.lang.Boolean");
		cardActiveSlot.setValue(true) ;
		fact.setSlots(Arrays.asList(cardNumberSlot, cardActiveSlot));
		
		//listing2
		Rule rule = new Rule();
		rule.setName("check-card-rule");
		Clause conditionClause = new Clause();
		conditionClause.setExpression (ClauseOperators.exists.name());
	//?	conditionClause.setParameter(createCardFact(123));
		// Creates a fact as in the previous listing.
		rule.setConditions(conditionClause);
		Clause consequenceClause = new Clause();
		consequenceClause.setExpression(ClauseOperators.print.name());
		consequenceClause.setExpression("Hallo Welt!");
		rule.setConsequences(consequenceClause);
		
		
		//listing3
		KnowledgeBase kb = new KnowledgeBase();
		kb.setRules(Arrays.asList(rule));
		kb.setFacts(Arrays.asList(fact));
		
		//listing4
		WorkingMemory wm = new WorkingMemory(kb);
		
		//listing5
		AbstractIE ie = new BruteForceIE(wm, kb);
	//	Fact startFact = createCardFact(123);
		ie.trigger(fact);
		
	}
	
	
	
}
