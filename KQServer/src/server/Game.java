package server;

import java.util.Random;

public class Game {

	static public String getRandomString(int length) {
		int level = 0;
		int min = 0;
		int rand = 0;
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();

		String chars[] = "ㄱ,ㄱ,ㄱ,ㄱ,ㄴ,ㄴ,ㄴ,ㄷ,ㄷ,ㄷ,ㄹ,ㄹ,ㅁ,ㅁ,ㅁ,ㅂ,ㅂ,ㅂ,ㅅ,ㅅ,ㅅ,ㅇ,ㅇ,ㅇ,ㅇ,ㅇ,ㅈ,ㅈ,ㅊ,ㅋ,ㅌ,ㅍ,ㅎ,ㄲ,ㄸ,ㅃ,ㅆ,ㅉ".split(",");
		int lev[] = 	{7,7,7,7,13,13,13,10,10,10,12,12,9,9,9,9,10,10,8,8,12,5,5,5,5,10,10,15,36,36,28,20,36,36,36,36,36};

		if(length == 2) {
			level = 35;
			while (buffer.length() < 2) {
				rand = random.nextInt(lev.length);
				min = lev[rand];
				level -= min;
				if(level < 0) {
					level =  35;
					buffer.delete(0, 1);
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
