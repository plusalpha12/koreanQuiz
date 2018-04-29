package InitialGame;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {
	
	private IGameView igame = new IGameView();
	
	static public String getRandomString(int length) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		
		String chars[] = "ぁ,い,ぇ,ぉ,け,げ,さ,し,じ,ず,せ,ぜ,そ,ぞ,あ,え,こ,す".split(",");
		
		for ( int i=0 ; i<length ; i++ ) {
			buffer.append(chars[random.nextInt(chars.length)]);
		}
		return buffer.toString();
	}

	public void GameGUI(IGameView igame) {
		this.igame = igame;
	}
	

	public static void main(String[] args) {
		String random=Game.getRandomString(2);
		System.out.println(random);
	}
}
