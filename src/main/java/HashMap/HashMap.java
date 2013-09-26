package HashMap;

public final class HashMap {
	private final static int TABLE_SIZE = 128;

	Entry[] table;

	HashMap() {
		table = new Entry[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++)
			table[i] = null;
	}

	private int hash(int key, int i) {
		return key % TABLE_SIZE + i;
	}
	
	/*
	 * cases:
	 * 1) table[h(k)].getKey() == key  -- return table[key].getValue()
	 * 2) table[h(k)].getKey() != key  -- probe
	 * 3) table[h(k)] == null          -- return -1;
	 * 4) table is full but value not found 
	 */
	public int get(int key) {
		if (key < 0 || key >= TABLE_SIZE) 
			throw new IllegalStateException("hash table overflow");
		
		for (int i = 0; i < TABLE_SIZE; i++) {
			int h = hash(key, i);
			if (table[h].getKey() == key) return table[h].getValue();
			if (table[h] == null) return -1;
		}		
		return -1;
	}

	private int findTableIndex(int key) {
		if (key < 0 || key >= TABLE_SIZE) return -1;
		
		for (int i = 0; i < TABLE_SIZE; i++) {
			int h = hash(key, i);
			if (table[h] == null) return -1;
			if (table[h].getKey() == key) return h;	
		}
		
		return -1;	
	}
	
	/*
	 * cases:
	 * 1) table[h(k)].getKey = key		-- replace value 
	 * 2) table[h(k)] == null			-- table[h(k)] = HashEntry
	 * 2) table[h(k)].getKey() != h(k)	-- linear probe
	 * 3) table is full
	 * 
	 * Note: Deleted entries are not reused.  An alternative would be to
	 * 
	 */
	public void put(int key, int value) {
		if (key < 0 || key >= TABLE_SIZE)
			throw new IllegalStateException("hash table overflow");
		
		int index = findTableIndex(key);
		if (index != -1) {
			Entry entry = new HashEntry(key, value);
			table[index] = entry;
			return;
		}
		
		for (int i = 0; i < TABLE_SIZE; i++) {
			int h = hash(key, i);
			if (table[h] == null) {
				table[h] = new HashEntry(key, value);
				return;
			}
		}
		
		throw new IllegalStateException("hash table overflow");
	}

	/*
	 * cases: 
	 * 1) table[h(k)] == null			-- return null
	 * 2) table[h(k)].getKey() == key	-- table[h(k)] = DeletedEntry, return
	 *    table[h(k)]
	 * 3) i == TABLE_SIZE				-- return null
	 */
	public Entry remove(int key) {
		if (key < 0 || key >= TABLE_SIZE)
			throw new IllegalStateException("hash table overflow");;
		
		for (int i = 0; i < TABLE_SIZE; i++) {
			int h = hash(key, i);
			if (table[h] == null) return null;
			if (table[h].getKey() == key) {
				Entry entry = table[h];
				table[h] = DeletedEntry.INSTANCE;
				return entry;
			}
		}
		
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Entry e: table) {
			if (e == null) continue;
			sb.append("(" + e.getKey() + ", " + e.getValue() + ") ");
		}
		return sb.toString();
	}
}