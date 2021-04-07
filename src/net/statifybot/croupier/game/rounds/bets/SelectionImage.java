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

				try {
					int num = Integer.valueOf(value);

					if (num >= 0 && num <= 36) {
						graphics.drawImage(ImageIO.read(new File("resources/chips/1.png")), 415, 800, 100, 100, null);
						graphics.drawImage(ImageIO.read(new File("resources/chips/2.png")), 440, 800, 100, 100, null);
						graphics.drawImage(ImageIO.read(new File("resources/chips/3.png")), 465, 800, 100, 100, null);
						graphics.drawImage(ImageIO.read(new File("resources/chips/4.png")), 490, 800, 100, 100, null);
						graphics.drawImage(ImageIO.read(new File("resources/chips/5.png")), 415, 900, 100, 100, null);
						graphics.drawImage(ImageIO.read(new File("resources/chips/6.png")), 440, 900, 100, 100, null);
						graphics.drawImage(ImageIO.read(new File("resources/chips/7.png")), 465, 900, 100, 100, null);
						graphics.drawImage(ImageIO.read(new File("resources/chips/8.png")), 490, 900, 100, 100, null);

					}

				} catch (NumberFormatException ex) {

				}

				File outputfile = new File("resources/drawedField.png");
				try {
					ImageIO.write(img, "png", outputfile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				return outputfile;

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
