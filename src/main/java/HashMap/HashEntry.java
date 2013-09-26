package HashMap;

public class HashEntry implements Entry {
    private final int key;
    private final int value;

    HashEntry(int key, int value) {
          this.key = key;
          this.value = value;
    }

    public int getValue() {
          return value;
    }

    public int getKey() {
          return key;
    }

	@Override
	public String toString() {
		return "HashEntry [key=" + key + ", value=" + value + "]";
	}
}
