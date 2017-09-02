
public class Atm {

	public static void main(String[] args) {
		BruteForceIE atm = new BruteForceIE();

		atm.addRule("insert-card-123")
				.addCondition(ClauseOperators.exists,
						new Fact("credit-card").addSlot("card-number", 123, Integer.class.getName()))
				.addConsequence(ClauseOperators.assertFact,
						new Fact("enterPin").addSlot("pin", null, Integer.class.getName()));

		atm.addRule("check-card-123")
				.addCondition(ClauseOperators.exists,
						new Fact("credit-card").addSlot("card-number", 123, Integer.class.getName()))
				.addCondition(ClauseOperators.exists, new Fact("enterPin").addSlot("pin", 456, Integer.class.getName()))
				.addConsequence(ClauseOperators.assertFact,
						new Fact("account").addSlot("number", 123, Integer.class.getName()).addSlot("amount", 1000000,
								Integer.class.getName()));

		atm.addRule("withdraw-123")
				.addCondition(ClauseOperators.exists,
						new Fact("account").addSlot("number", 123, Integer.class.getName()).addSlot("amount", 1000000,
								Integer.class.getName()))
				.addConsequence(ClauseOperators.print, new Fact("Withdraw")).addConsequence(ClauseOperators.assertFact,
						new Fact("withdraw-123").addSlot("amount-to-withdraw", null, Integer.class.getName()));

		atm.addRule("finished")
				.addCondition(ClauseOperators.exists,
						new Fact("withdraw-123").addSlot("amount-to-withdraw", 1000, Integer.class.getName()))
				.addConsequence(ClauseOperators.print, new Fact("Finished"));

		Fact startFact = new Fact("credit-card").addSlot("card-number", 123, Integer.class.getName());
		atm.addFact(startFact);

		atm.trigger();

	}
}
