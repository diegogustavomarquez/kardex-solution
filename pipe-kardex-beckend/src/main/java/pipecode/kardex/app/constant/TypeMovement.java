package pipecode.kardex.app.constant;

/**
 * Represents the types of movements
 * 
 * @author diegogustavomarquez
 *
 */
public enum TypeMovement {

	CREATED("CREATED"),ADD("ADD"), GET("GET");

	private final String type;

	TypeMovement(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

}
