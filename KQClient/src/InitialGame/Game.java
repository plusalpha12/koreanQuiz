package InitialGame;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {
	
	static public String getRandomString(int length) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		
		String chars[] = "��,��,��,��,��,��,��,��,��,��,��,��,��,��,��,��,��,��".split(",");
		
		for ( int i=0 ; i<length ; i++ ) {
			buffer.append(chars[random.nextInt(chars.length)]);
		}
		return buffer.toString();
	}

	public static void main(String[] args) {
	}
}
