package HashMap;

public enum DeletedEntry implements Entry {
	
	INSTANCE;

	public int getValue() {
		return -1;
	}

	public int getKey() {
		return 1;
	}
}
