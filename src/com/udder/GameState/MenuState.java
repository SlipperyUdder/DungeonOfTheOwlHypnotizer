package com.udder.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;



import com.udder.TileMap.*;
import com.udder.Audio.JukeBox;
import com.udder.Entity.PlayerSave;
import com.udder.Handlers.Keys;
import com.udder.Main.GamePanel;

public class MenuState extends GameState {
	
	private BufferedImage arrow;
	
	private int currentChoice = 0;
	private String[] options = {
		"Start",
		"Quit"
	};
	private Color titleColor2;
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	private Font font2;
	private Background bg;
	
	public MenuState(GameStateManager gsm) {
		
		super(gsm);
		
		try {
			
			//load bg
			bg = new Background("/Backgrounds/menubg.gif", 1);
			bg.setVector(-0.1, 0);
			
			// load arrow
			arrow = ImageIO.read(
				getClass().getResourceAsStream("/HUD/HUD.gif")
			).getSubimage(13, 0, 13, 14);
			
			// titles and fonts
			titleColor2 = Color.YELLOW;
			titleColor = Color.RED;
			titleFont = new Font("Times New Roman", Font.PLAIN, 24);
			font = new Font("Arial", Font.BOLD, 14);
			font2 = new Font("Arial", Font.BOLD, 10);
			
			// load sound fx
			JukeBox.load("/SFX/menuoption.mp3", "menuoption");
			JukeBox.load("/SFX/menuselect.mp3", "menuselect");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void init() {}
	
	public void update() {
		
		// check keys
		handleInput();
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
				bg.draw(g);
				
	
		
		
		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Dungeon of the Owl Hypnotizer", 5, 90);
		g.setColor(titleColor2);
		g.drawString("Dungeon of the Owl Hypnotizer", 5, 89);
		
		// draw menu options
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Start", 145, 165);
		g.drawString("Quit", 145, 185);
		
		// draw floating head
		if(currentChoice == 0) g.drawImage(arrow, 125, 154, null);
		else if(currentChoice == 1) g.drawImage(arrow, 125, 174, null);
		
		// other
		g.setFont(font2);
		g.drawString("2014 SlipperyUdder", 10, 232);
		
	}
	
	private void select() {
		if(currentChoice == 0) {
			JukeBox.play("menuselect");
			PlayerSave.init();
			gsm.setState(GameStateManager.DUNGEONSTATE);
		}
		else if(currentChoice == 1) {
			System.exit(0);
		}
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER)) select();
		if(Keys.isPressed(Keys.UP)) {
			if(currentChoice > 0) {
				JukeBox.play("menuoption", 0);
				currentChoice--;
			}
		}
		if(Keys.isPressed(Keys.DOWN)) {
			if(currentChoice < options.length - 1) {
				JukeBox.play("menuoption", 0);
				currentChoice++;
			}
		}
	}
	
}










