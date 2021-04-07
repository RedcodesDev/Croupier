package net.statifybot.croupier.game.rounds.bets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import com.google.common.collect.Multimap;

import net.statifybot.croupier.game.rounds.Round;

public class SelectionImage {

	Round round;
	Multimap<Long, String> bets;

	public SelectionImage(Round round) {
		this.round = round;
		this.bets = this.round.getBetMap();
	}

	public File renderImage() {

		try {
			BufferedImage img = ImageIO.read(new File("resources/field.png"));

			Graphics2D graphics = img.createGraphics();

			List<String> plr1Bets = new ArrayList<String>();
			long plr1 = 0l;
			List<String> plr2Bets = new ArrayList<String>();
			long plr2 = 0l;
			List<String> plr3Bets = new ArrayList<String>();
			long plr3 = 0l;
			List<String> plr4Bets = new ArrayList<String>();
			long plr4 = 0l;
			List<String> plr5Bets = new ArrayList<String>();
			long plr5 = 0l;
			List<String> plr6Bets = new ArrayList<String>();
			long plr6 = 0l;
			List<String> plr7Bets = new ArrayList<String>();
			long plr7 = 0l;
			List<String> plr8Bets = new ArrayList<String>();
			long plr8 = 0l;

			for (Entry<Long, String> entry : this.bets.entries()) {

				if (plr1 == entry.getKey()) {
					plr1Bets.add(entry.getValue());
				} else if (plr2 == entry.getKey()) {
					plr2Bets.add(entry.getValue());
				} else if (plr3 == entry.getKey()) {
					plr3Bets.add(entry.getValue());
				} else if (plr4 == entry.getKey()) {
					plr4Bets.add(entry.getValue());
				} else if (plr5 == entry.getKey()) {
					plr5Bets.add(entry.getValue());
				} else if (plr6 == entry.getKey()) {
					plr6Bets.add(entry.getValue());
				} else if (plr7 == entry.getKey()) {
					plr7Bets.add(entry.getValue());
				} else if (plr8 == entry.getKey()) {
					plr8Bets.add(entry.getValue());
				} else {

					if (plr1 == 0l) {
						plr1 = entry.getKey();
						plr1Bets.add(entry.getValue());
					} else if (plr2 == 0l) {
						plr2 = entry.getKey();
						plr2Bets.add(entry.getValue());
					} else if (plr3 == 0l) {
						plr3 = entry.getKey();
						plr3Bets.add(entry.getValue());
					} else if (plr4 == 0l) {
						plr4 = entry.getKey();
						plr4Bets.add(entry.getValue());
					} else if (plr5 == 0l) {
						plr5 = entry.getKey();
						plr5Bets.add(entry.getValue());
					} else if (plr6 == 0l) {
						plr6 = entry.getKey();
						plr6Bets.add(entry.getValue());
					} else if (plr7 == 0l) {
						plr7 = entry.getKey();
						plr7Bets.add(entry.getValue());
					} else if (plr8 == 0l) {
						plr8 = entry.getKey();
						plr8Bets.add(entry.getValue());
					}

				}

			}

			for (String value : plr1Bets) {
				switch (value) {

				case "0":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 200, 900, 75, 75, null);
					break;

				case "1":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 415, 1200, 75, 75, null);
					break;

				case "2":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 415, 1000, 75, 75, null);
					break;

				case "3":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 415, 800, 75, 75, null);
					break;

				case "4":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 615, 1200, 75, 75, null);
					break;

				case "5":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 615, 1000, 75, 75, null);
					break;

				case "6":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 615, 800, 75, 75, null);
					break;
				case "7":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 815, 1200, 75, 75, null);
					break;
				case "8":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 815, 1000, 75, 75, null);
					break;
				case "9":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 815, 800, 75, 75, null);

					break;
				case "10":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1015, 1200, 75, 75, null);
					break;
				case "11":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1015, 1000, 75, 75, null);

					break;
				case "12":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1015, 800, 75, 75, null);
					break;
				case "13":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1215, 1200, 75, 75, null);

					break;
				case "14":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1215, 1000, 75, 75, null);

					break;
				case "15":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1215, 800, 75, 75, null);

					break;
				case "16":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1415, 1200, 75, 75, null);

					break;
				case "17":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1415, 1000, 75, 75, null);

					break;
				case "18":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1415, 800, 75, 75, null);

					break;
				case "19":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1615, 1200, 75, 75, null);

					break;
				case "20":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1615, 1000, 75, 75, null);

					break;
				case "21":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1615, 800, 75, 75, null);

					break;
				case "22":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1815, 1200, 75, 75, null);

					break;
				case "23":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1815, 1000, 75, 75, null);

					break;
				case "24":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1815, 800, 75, 75, null);

					break;
				case "25":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2015, 1200, 75, 75, null);

					break;
				case "26":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2015, 1000, 75, 75, null);

					break;
				case "27":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2015, 800, 75, 75, null);

					break;
				case "28":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2215, 1200, 75, 75, null);

					break;
				case "29":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2215, 1000, 75, 75, null);

					break;
				case "30":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2215, 800, 75, 75, null);

					break;
				case "31":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2415, 1200, 75, 75, null);

					break;
				case "32":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2415, 1000, 75, 75, null);

					break;
				case "33":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2415, 800, 75, 75, null);

					break;
				case "34":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2615, 1200, 75, 75, null);

					break;
				case "35":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2615, 1000, 75, 75, null);

					break;
				case "36":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2615, 800, 75, 75, null);

					break;

				case "red":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1215, 1600, 75, 75, null);
					break;

				case "black":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1615, 1600, 75, 75, null);
					break;

				case "even":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 815, 1600, 75, 75, null);
					break;

				case "odd":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2015, 1600, 75, 75, null);
					break;

				case "1-18":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 415, 1600, 75, 75, null);
					break;

				case "19-36":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2415, 1600, 75, 75, null);
					break;

				case "first 12":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 475, 1400, 75, 75, null);
					break;

				case "second 12":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 1275, 1400, 75, 75, null);
					break;

				case "third 12":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2075, 1400, 75, 75, null);
					break;

				case "1st column":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2815, 800, 75, 75, null);
					break;

				case "2nd column":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2815, 1000, 75, 75, null);
					break;

				case "3rd column":
					graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 2815, 1200, 75, 75, null);

					break;

				}

//				graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 415, 800, 75, 75, null);
//				graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 440, 800, 75, 75, null);
//				graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 465, 800, 75, 75, null);
//				graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 490, 800, 75, 75, null);
//				graphics.drawImage(ImageIO.read(new File("resources/chips/5.png")), 415, 900, 75, 75, null);
//				graphics.drawImage(ImageIO.read(new File("resources/chips/6.png")), 440, 900, 75, 75, null);
//				graphics.drawImage(ImageIO.read(new File("resources/chips/7.png")), 465, 900, 75, 75, null);
//				graphics.drawImage(ImageIO.read(new File("resources/chips/8.png")), 490, 900, 75, 75, null);

			}

			for (String value : plr2Bets) {
				switch (value) {

				case "0":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 300, 900, 75, 75, null);
					break;

				case "1":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 440, 1200, 75, 75, null);
					break;

				case "2":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 440, 1000, 75, 75, null);
					break;

				case "3":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 440, 800, 75, 75, null);
					break;

				case "4":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 640, 1200, 75, 75, null);
					break;

				case "5":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 640, 1000, 75, 75, null);
					break;

				case "6":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 640, 800, 75, 75, null);
					break;
				case "7":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 840, 1200, 75, 75, null);
					break;
				case "8":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 840, 1000, 75, 75, null);
					break;
				case "9":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 840, 800, 75, 75, null);

					break;
				case "10":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1040, 1200, 75, 75, null);
					break;
				case "11":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1040, 1000, 75, 75, null);

					break;
				case "12":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1040, 800, 75, 75, null);
					break;
				case "13":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1240, 1200, 75, 75, null);

					break;
				case "14":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1240, 1000, 75, 75, null);

					break;
				case "15":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1240, 800, 75, 75, null);

					break;
				case "16":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1440, 1200, 75, 75, null);

					break;
				case "17":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1440, 1000, 75, 75, null);

					break;
				case "18":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1440, 800, 75, 75, null);

					break;
				case "19":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1640, 1200, 75, 75, null);

					break;
				case "20":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1640, 1000, 75, 75, null);

					break;
				case "21":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1640, 800, 75, 75, null);

					break;
				case "22":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1840, 1200, 75, 75, null);

					break;
				case "23":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1840, 1000, 75, 75, null);

					break;
				case "24":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1840, 800, 75, 75, null);

					break;
				case "25":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2040, 1200, 75, 75, null);

					break;
				case "26":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2040, 1000, 75, 75, null);

					break;
				case "27":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2040, 800, 75, 75, null);

					break;
				case "28":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2240, 1200, 75, 75, null);

					break;
				case "29":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2240, 1000, 75, 75, null);

					break;
				case "30":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2240, 800, 75, 75, null);

					break;
				case "31":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2440, 1200, 75, 75, null);

					break;
				case "32":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2440, 1000, 75, 75, null);

					break;
				case "33":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2440, 800, 75, 75, null);

					break;
				case "34":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2640, 1200, 75, 75, null);

					break;
				case "35":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2640, 1000, 75, 75, null);

					break;
				case "36":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2640, 800, 75, 75, null);

					break;

				case "red":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1265, 1600, 75, 75, null);
					break;

				case "black":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1665, 1600, 75, 75, null);
					break;

				case "even":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 865, 1600, 75, 75, null);
					break;

				case "odd":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2065, 1600, 75, 75, null);
					break;

				case "1-18":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 465, 1600, 75, 75, null);
					break;

				case "19-36":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2465, 1600, 75, 75, null);
					break;

				case "first 12":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 575, 1400, 75, 75, null);
					break;

				case "second 12":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 1375, 1400, 75, 75, null);
					break;

				case "third 12":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2175, 1400, 75, 75, null);
					break;

				case "1st column":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2865, 800, 75, 75, null);
					break;

				case "2nd column":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2865, 1000, 75, 75, null);
					break;

				case "3rd column":
					graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 2865, 1200, 75, 75, null);

					break;

				}
			}
			
			for (String value : plr3Bets) {
				switch (value) {

				case "0":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 250, 825, 75, 75, null);
					break;

				case "1":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 465, 1200, 75, 75, null);
					break;

				case "2":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 465, 1000, 75, 75, null);
					break;

				case "3":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 465, 800, 75, 75, null);
					break;

				case "4":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 665, 1200, 75, 75, null);
					break;

				case "5":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 665, 1000, 75, 75, null);
					break;

				case "6":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 665, 800, 75, 75, null);
					break;
				case "7":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 865, 1200, 75, 75, null);
					break;
				case "8":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 865, 1000, 75, 75, null);
					break;
				case "9":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 865, 800, 75, 75, null);

					break;
				case "10":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1065, 1200, 75, 75, null);
					break;
				case "11":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1065, 1000, 75, 75, null);

					break;
				case "12":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1065, 800, 75, 75, null);
					break;
				case "13":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1265, 1200, 75, 75, null);

					break;
				case "14":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1265, 1000, 75, 75, null);

					break;
				case "15":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1265, 800, 75, 75, null);

					break;
				case "16":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1465, 1200, 75, 75, null);

					break;
				case "17":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1465, 1000, 75, 75, null);

					break;
				case "18":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1465, 800, 75, 75, null);

					break;
				case "19":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1665, 1200, 75, 75, null);

					break;
				case "20":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1665, 1000, 75, 75, null);

					break;
				case "21":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1665, 800, 75, 75, null);

					break;
				case "22":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1865, 1200, 75, 75, null);

					break;
				case "23":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1865, 1000, 75, 75, null);

					break;
				case "24":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1865, 800, 75, 75, null);

					break;
				case "25":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2065, 1200, 75, 75, null);

					break;
				case "26":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2065, 1000, 75, 75, null);

					break;
				case "27":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2065, 800, 75, 75, null);

					break;
				case "28":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2265, 1200, 75, 75, null);

					break;
				case "29":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2265, 1000, 75, 75, null);

					break;
				case "30":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2265, 800, 75, 75, null);

					break;
				case "31":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2465, 1200, 75, 75, null);

					break;
				case "32":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2465, 1000, 75, 75, null);

					break;
				case "33":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2465, 800, 75, 75, null);

					break;
				case "34":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2665, 1200, 75, 75, null);

					break;
				case "35":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2665, 1000, 75, 75, null);

					break;
				case "36":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2665, 800, 75, 75, null);

					break;

				case "red":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1315, 1600, 75, 75, null);
					break;

				case "black":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1715, 1600, 75, 75, null);
					break;

				case "even":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 915, 1600, 75, 75, null);
					break;

				case "odd":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2115, 1600, 75, 75, null);
					break;

				case "1-18":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 515, 1600, 75, 75, null);
					break;

				case "19-36":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2515, 1600, 75, 75, null);
					break;

				case "first 12":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 675, 1400, 75, 75, null);
					break;

				case "second 12":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 1475, 1400, 75, 75, null);
					break;

				case "third 12":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2275, 1400, 75, 75, null);
					break;

				case "1st column":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2915, 800, 75, 75, null);
					break;

				case "2nd column":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2915, 1000, 75, 75, null);
					break;

				case "3rd column":
					graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 2915, 1200, 75, 75, null);

					break;

				}
			}
			
			for (String value : plr4Bets) {
				switch (value) {

				case "0":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 250, 965, 75, 75, null);
					break;

				case "1":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 490, 1200, 75, 75, null);
					break;

				case "2":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 490, 1000, 75, 75, null);
					break;

				case "3":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 490, 800, 75, 75, null);
					break;

				case "4":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 690, 1200, 75, 75, null);
					break;

				case "5":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 690, 1000, 75, 75, null);
					break;

				case "6":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 690, 800, 75, 75, null);
					break;
				case "7":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 890, 1200, 75, 75, null);
					break;
				case "8":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 890, 1000, 75, 75, null);
					break;
				case "9":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 890, 800, 75, 75, null);

					break;
				case "10":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1090, 1200, 75, 75, null);
					break;
				case "11":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1090, 1000, 75, 75, null);

					break;
				case "12":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1090, 800, 75, 75, null);
					break;
				case "13":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1290, 1200, 75, 75, null);

					break;
				case "14":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1290, 1000, 75, 75, null);

					break;
				case "15":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1290, 800, 75, 75, null);

					break;
				case "16":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1490, 1200, 75, 75, null);

					break;
				case "17":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1490, 1000, 75, 75, null);

					break;
				case "18":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1490, 800, 75, 75, null);

					break;
				case "19":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1690, 1200, 75, 75, null);

					break;
				case "20":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1690, 1000, 75, 75, null);

					break;
				case "21":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1690, 800, 75, 75, null);

					break;
				case "22":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1890, 1200, 75, 75, null);

					break;
				case "23":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1890, 1000, 75, 75, null);

					break;
				case "24":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1890, 800, 75, 75, null);

					break;
				case "25":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2090, 1200, 75, 75, null);

					break;
				case "26":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2090, 1000, 75, 75, null);

					break;
				case "27":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2090, 800, 75, 75, null);

					break;
				case "28":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2290, 1200, 75, 75, null);

					break;
				case "29":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2290, 1000, 75, 75, null);

					break;
				case "30":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2290, 800, 75, 75, null);

					break;
				case "31":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2490, 1200, 75, 75, null);

					break;
				case "32":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2490, 1000, 75, 75, null);

					break;
				case "33":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2490, 800, 75, 75, null);

					break;
				case "34":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2690, 1200, 75, 75, null);

					break;
				case "35":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2690, 1000, 75, 75, null);

					break;
				case "36":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2690, 800, 75, 75, null);

					break;

				case "red":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1375, 1600, 75, 75, null);
					break;

				case "black":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1775, 1600, 75, 75, null);
					break;

				case "even":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 975, 1600, 75, 75, null);
					break;

				case "odd":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2175, 1600, 75, 75, null);
					break;

				case "1-18":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 575, 1600, 75, 75, null);
					break;

				case "19-36":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2575, 1600, 75, 75, null);
					break;

				case "first 12":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 775, 1400, 75, 75, null);
					break;

				case "second 12":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 1575, 1400, 75, 75, null);
					break;

				case "third 12":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2375, 1400, 75, 75, null);
					break;

				case "1st column":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2975, 800, 75, 75, null);
					break;

				case "2nd column":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2975, 1000, 75, 75, null);
					break;

				case "3rd column":
					graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 2975, 1200, 75, 75, null);

					break;

				}
			}


			File outputfile = new File("resources/drawedField.png");
			try {
				ImageIO.write(img, "png", outputfile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			return outputfile;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
