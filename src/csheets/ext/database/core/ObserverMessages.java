package csheets.ext.database.core;

/**
 * The observer messages for the merge errors
 * 
 * @author Andre
 * 
 */
public class ObserverMessages {
	/** the database value */
	private final String databaseValue;
	/** the application value */
	private final String applicationValue;
	/** the decision value */
	private int decision;

	/**
	 * Creates a new observer message
	 * 
	 * @param databaseValue
	 *            the database value
	 * @param applicationValue
	 *            the application value
	 */
	public ObserverMessages(String databaseValue, String applicationValue) {
		this.databaseValue = databaseValue;
		this.applicationValue = applicationValue;
		this.decision = -1;
	}

	/**
	 * Get the application value
	 * 
	 * @return the application value
	 */
	public String getApplicationValue() {
		return applicationValue;
	}

	/**
	 * Get the database value
	 * 
	 * @return the database value
	 */
	public String getDatabaseValue() {
		return databaseValue;
	}

	/**
	 * Get the decision value
	 * 
	 * @return the decision value
	 */
	public int getDecision() {
		return decision;
	}

	/**
	 * Set the decision value
	 * 
	 * @param decision
	 *            the decision value
	 */
	public void setDecision(int decision) {
		this.decision = decision;
	}

}
