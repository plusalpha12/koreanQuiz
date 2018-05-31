package server;

import java.util.Random;

public class Game {

	static public String getRandomString(int length) {
		int level = 0;
		int min = 0;
		int rand = 0;
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();

		String chars[] = "ぁ,い,ぇ,ぉ,け,げ,さ,し,じ,ず,せ,ぜ,そ,ぞ,あ,え,こ,ざ,す".split(",");
		int lev[] = 	{7,13,10,12,9,10,8,5,2,15,24,24,28,16,36,36,36,36,36};

		if(length == 2) {
			level = 35;
			while (buffer.length() < 2) {
				rand = random.nextInt(lev.length);
				min = lev[rand];
				level -= min;
				if(level < 0) {
					level += min;
					continue;
				}else
					buffer.append(chars[rand]);
			}

		}else if(length == 3) {
			level = 45;
			while (buffer.length() < 3) {
				rand = random.nextInt(lev.length);
				min = lev[rand];
				level -= min;
				if(level < 0) {
					level += min;
					continue;
				}else
					buffer.append(chars[rand]);
			}

		}

		return buffer.toString();
	}
}
