package assignment2;

public class SolitaireCipher {
	public Deck key;

	public SolitaireCipher (Deck key) {
		this.key = new Deck(key); // deep copy of the deck
	}

	/* 
	 * TODO: Generates a keystream of the given size
	 */
	public int[] getKeystream(int size) {
		int[] stream = new int[size];

		for(int i=0; i<size; i++){
			stream[i] = this.key.generateNextKeystreamValue();
			// System.out.println(stream[i]);
		}

		return stream;
	}

	/* 
	 * TODO: Encodes the input message using the algorithm described in the pdf.
	 */
	public String encode(String msg) {
		StringBuilder ciphertext = new StringBuilder();
		msg = msg.replaceAll("[^a-zA-Z]", "").toUpperCase();
		// System.out.println(msg);
        char[] letters = msg.toCharArray();
		int[] stream = this.getKeystream(letters.length);
        for (int i = 0; i < letters.length; i++) {
            char letter = letters[i];
            int shift = stream[i];
            char encryptedChar = (char) ('A' + (letter - 'A' + shift) % 26);
            ciphertext.append(encryptedChar);
        }
        return ciphertext.toString();
	}

	/* 
	 * TODO: Decodes the input message using the algorithm described in the pdf.
	 */
	public String decode(String msg) {
		StringBuilder decryptedMessage = new StringBuilder();
		// msg = msg.replaceAll("[^a-zA-Z]", "").toUpperCase();
        char[] letters = msg.toCharArray();
		int[] stream = this.getKeystream(letters.length);
        for (int i = 0; i < letters.length; i++) {
            char letter = letters[i];
            int shift = stream[i];
            char decryptedChar = (char) ('A' + (letter - 'A' - shift + 26) % 26);
            decryptedMessage.append(decryptedChar);
        }
        return decryptedMessage.toString();
	}

}
