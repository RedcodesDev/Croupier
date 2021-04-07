package net.statifybot.croupier.game.rounds.bets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
