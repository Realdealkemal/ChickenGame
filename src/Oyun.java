import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Timer;

class yumurta {
	public yumurta(int x, int y) {

		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	private int x;
	private int y;
}

class yumurta2 {
	public yumurta2(int x2, int y2) {
		this.x2 = x2;
		this.y2 = y2;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	private int x2;
	private int y2;
}

public class Oyun extends JPanel implements KeyListener,ActionListener{
	private int vurulanhedef=0;
	private int Stok=20;
	private int points=0;
	private int level=1;
	private int levelsayac=0;
	private int levelsayac2=0;
	
	private boolean death=false;

	private BufferedImage image;
	private BufferedImage image2;
	private BufferedImage image3;
	private BufferedImage image4;
	private BufferedImage image5;
	
	private static Font font = new Font("Sanserif",Font.BOLD,24);


	Timer timer = new Timer(10,this);

	Random rand = new Random();

	private ArrayList<yumurta> yumurtalar = new ArrayList<yumurta>();
	private ArrayList<yumurta2> yumurtalar2 = new ArrayList<yumurta2>();
	
	private int BonusX=0;
	private int BonusY=1200;
	private int BonusXsize ;
	private int BonusYsize ;

	private int YumurtadirX=8;
	private int BoardX = 0;
	private int BoardY = 0;
	private int KediX = 0;
	private int KediY = 600;
	private int KediX1 = 600;
	private int KediY1 = 900;
	private int SolX = 600;
	private int SolY = 600;
	private int SolX1 = 600;
	private int SolY1 = 900;
	private int SolXsize ;
	private int SolYsize ;
	private int SolX1size ;
	private int SolY1size ;
	/*private int SolX2 = 600;
	private int SolY2 = 1200;*/
	private int KedisizeX = 70 ;
	private int KedisizeY = 70;
	private int KedisizeX1 = 90;
	private int KedisizeY1 = 90;
	private int TavukX= 250;
	private int TavukY= 100;

	private int dirTavukY=2;
	private int TavukdirX=20;
	private int sag_sol_kontrol = 0;
	Thread animator;
	public boolean kontrol_et(){
		for (yumurta yumurta : yumurtalar) {
			/*if((((SolX+50)>= yumurta.getX() ) &&((SolX)<=yumurta.getX()))&&(((SolY + 50)>=yumurta.getY())&&(SolY<=yumurta.getY()))){
				return true;
			}*/
			if(new Rectangle(yumurta.getX(),yumurta.getY(),10,15).intersects(new Rectangle(SolX,SolY,SolXsize-7,SolYsize-7))){
				SolY=-100;
				points+=(int)(500/(int)SolXsize);
				return true;
			}
			else if(new Rectangle(yumurta.getX(),yumurta.getY(),10,15).intersects(new Rectangle(SolX1,SolY1,SolX1size-7,SolY1size-7))){
				SolY1=-100;
				points+=(int)(500/(int)SolX1size);
				return true;
			}
		}
		for (yumurta2 yumurta2 : yumurtalar2) {
			if(new Rectangle(yumurta2.getX2(),yumurta2.getY2(),10,15).intersects(new Rectangle(SolX,SolY,SolXsize-7,SolYsize-7))){
				SolY=-100;
				points+=(int)(500/(int)SolXsize);
				return true;
			}
			else if(new Rectangle(yumurta2.getX2(),yumurta2.getY2(),10,15).intersects(new Rectangle(SolX1,SolY1,SolX1size-7,SolY1size-7))){
				SolY1=-100;
				points+=(int)(500/(int)SolX1size);
				return true;
			}
		}
		return false;
	}
	
	public boolean bonuskontrol() {
		if(new Rectangle(TavukX,TavukY,image.getWidth()/10,image.getHeight()/10).intersects(new Rectangle(BonusX,BonusY,BonusXsize-7,BonusYsize-7))){
			BonusY=-100;
			Stok+=(int)(250/(int)BonusXsize);
			return true;
		}
		return false;
		
	}
	
	/*public boolean deathcontrol() {
		if(new Rectangle(TavukX,TavukY,image.getWidth()/10,image.getHeight()/10).intersects(new Rectangle(KediX,KediY,KedisizeX,KedisizeY))){
			timer.stop();
			
			String message ="GAME OVER...\n"+
		                     "Your Level "+ level_belirle()+"\n"+
					         "Your Points "+ points;
			JOptionPane.showMessageDialog(this, message);
			System.exit(0);
			return true;
		}
		else if(new Rectangle(TavukX,TavukY,image.getWidth()/10,image.getHeight()/10).intersects(new Rectangle(KediX1,KediY1,KedisizeX1,KedisizeY1))){
			timer.stop();
			
			String message ="GAME OVER...\n"+
		                     "Your Level "+ level_belirle()+"\n"+
					         "Your Points "+ points;
			JOptionPane.showMessageDialog(this, message);
			System.exit(0);
			return true;
		}
		return false;
		
	}*/
	
	public int level_belirle() {
		if(points<70) {
			level=1;
		}
		else if(points<200) {
			level=2;
		}
		else {
			level=3;
		}
		return level;
		
	}
	
	public Oyun() {
		String messagee ="GAME RULES:\n"+
                "1- The goal is to score points by avoiding cats and shooting worms.\n"+
                "2- To shoot the worms, you have to press the 'space' key to match the eggs you throw with the worm.\n"+
                "3- You must press the 'right arrow' key to move right, and the 'left arrow' key to move left.*\n"+
                "4- The score you get will vary according to the size of the worm you hit.\n"+
                "5- The number of eggs you can throw is limited. You start the game with 20 eggs, the number of eggs \nincreases as you level up or gain bonuses.\n"+
                "6- To get the bonus, you must pass over the bonus symbol while falling.\n"+
                "7- The game consists of 3 levels.\n"+
                "8- Up to 70 points you will be promoted to level 1, up to 200 points to level 2, after 200 points \nyou will be promoted to level 3.\n"+
                "9- The higher your level, the faster you fall.\n"+ "\n" + "\n" +
		         "HAVE A GOOD LUCK !" ;
JOptionPane.showMessageDialog(this, messagee);

		try {
			image=ImageIO.read(new FileImageInputStream(new File("tavuk.png")));
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE,null,ex);
		}
		try {
			image2=ImageIO.read(new FileImageInputStream(new File("gokyuzu.png")));
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE,null,ex);
		}
		try {
			image3=ImageIO.read(new FileImageInputStream(new File("Cat.png")));
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE,null,ex);
		}
		try {
			image4=ImageIO.read(new FileImageInputStream(new File("solucan.png")));
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE,null,ex);
		}
		try {
			image5=ImageIO.read(new FileImageInputStream(new File("bonus.png")));
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE,null,ex);
		}


		setBackground(Color.BLACK);

		timer.start();



	}
	/*
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}

*/

	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
	}

	@Override
	public void paint(Graphics g) {

		
		// TODO Auto-generated method stub

		super.paint(g);
		//g.drawImage(image2, BoardX, BoardY, null);
		
		if (level_belirle()==1) {
			
			g.drawImage(image2, BoardX, BoardY, this);
			if(BoardY>= -600){
				BoardY -= 1;
			}
			else{
				BoardY = 0;
			}

			g.drawImage(image3, KediX, KediY,KedisizeX,KedisizeY, this);
			

				if (KediY >= -610) {
					KediY -= rand.nextInt(5)+2;
				} else {
					KediY = 600;
					KediX = rand.nextInt(191)+280;
					KedisizeX = rand.nextInt(61)+70;
					KedisizeY = rand.nextInt(61)+70;
				}
				g.drawImage(image3, KediX1, KediY1,KedisizeX1,KedisizeY1, this);
				if(KediY1 >= -610){
					KediY1 -= rand.nextInt(5)+2;
				}
				else {
					KediY1 = 600;
					KediX1 = rand.nextInt(290)+1;
					KedisizeX1 = rand.nextInt(61)+70;
					KedisizeY1 = rand.nextInt(61)+70;
				}
				g.drawImage(image4, SolX, SolY,SolXsize,SolYsize, this);

				if (SolY >= -610) {
					SolY -= rand.nextInt(4)+2;
				} else {
					SolY = 600;
					SolX = rand.nextInt(290)+1;
					SolXsize = rand.nextInt(41)+30;
					SolYsize = SolXsize;
				}
				g.drawImage(image4, SolX1, SolY1,SolX1size,SolY1size, this);

				if (SolY1 >= -610) {
					SolY1 -= rand.nextInt(4)+2;
				} else {
					SolY1 = 600;
					SolX1 = rand.nextInt(191)+280;
					SolX1size = rand.nextInt(41)+30;
					SolY1size = SolX1size;
				}
				g.drawImage(image5, BonusX,BonusY,BonusXsize,BonusYsize, this);
				if (BonusY >= -1400) {
					BonusY -= rand.nextInt(4)+2;
				} else {
					BonusY = 600;
					BonusX = rand.nextInt(530)+1;
					BonusXsize = rand.nextInt(41)+30;
					BonusYsize = BonusXsize;
				}
			
			
		}
		if (level_belirle()==2) {
			levelsayac++;
			if (levelsayac==1) {
				Stok=Stok+20;
			}
			g.drawImage(image2, BoardX, BoardY, this);
			if(BoardY>= -600){
				BoardY -= 1;
			}
			else{
				BoardY = 0;
			}

			g.drawImage(image3, KediX, KediY,KedisizeX,KedisizeY, this);
			

				if (KediY >= -610) {
					KediY -= rand.nextInt(6)+5;
				} else {
					KediY = 600;
					KediX = rand.nextInt(191)+280;
					KedisizeX = rand.nextInt(61)+70;
					KedisizeY = rand.nextInt(61)+70;
				}
				g.drawImage(image3, KediX1, KediY1,KedisizeX1,KedisizeY1, this);
				if(KediY1 >= -610){
					KediY1 -= rand.nextInt(6)+5;
				}
				else {
					KediY1 = 600;
					KediX1 = rand.nextInt(290)+1;
					KedisizeX1 = rand.nextInt(61)+70;
					KedisizeY1 = rand.nextInt(61)+70;
				}
				g.drawImage(image4, SolX, SolY,SolXsize,SolYsize, this);

				if (SolY >= -610) {
					SolY -= rand.nextInt(5)+3;
				} else {
					SolY = 600;
					SolX = rand.nextInt(290)+1;
					SolXsize = rand.nextInt(41)+30;
					SolYsize = SolXsize;
				}
				g.drawImage(image4, SolX1, SolY1,SolX1size,SolY1size, this);

				if (SolY1 >= -610) {
					SolY1 -= rand.nextInt(5)+3;
				} else {
					SolY1 = 600;
					SolX1 = rand.nextInt(191)+280;
					SolX1size = rand.nextInt(41)+30;
					SolY1size = SolX1size;
				}
				g.drawImage(image5, BonusX,BonusY,BonusXsize,BonusYsize, this);
				if (BonusY >= -2800) {
					BonusY -= rand.nextInt(4)+3;
				} else {
					BonusY = 600;
					BonusX = rand.nextInt(530)+1;
					BonusXsize = rand.nextInt(41)+30;
					BonusYsize = BonusXsize;
				}
			
		}
		if (level_belirle()==3) {
			levelsayac2++;
			if (levelsayac2==1) {
				Stok=Stok+20;
			}
			g.drawImage(image2, BoardX, BoardY, this);
			if(BoardY>= -600){
				BoardY -= 1;
			}
			else{
				BoardY = 0;
			}

			g.drawImage(image3, KediX, KediY,KedisizeX,KedisizeY, this);
			

				if (KediY >= -610) {
					KediY -= rand.nextInt(6)+10;
				} else {
					KediY = 600;
					KediX = rand.nextInt(191)+280;
					KedisizeX = rand.nextInt(61)+70;
					KedisizeY = rand.nextInt(61)+70;
				}
				g.drawImage(image3, KediX1, KediY1,KedisizeX1,KedisizeY1, this);
				if(KediY1 >= -610){
					KediY1 -= rand.nextInt(6)+10;
				}
				else {
					KediY1 = 600;
					KediX1 = rand.nextInt(290)+1;
					KedisizeX1 = rand.nextInt(61)+70;
					KedisizeY1 = rand.nextInt(61)+70;
				}
				g.drawImage(image4, SolX, SolY,SolXsize,SolYsize, this);

				if (SolY >= -610) {
					SolY -= rand.nextInt(5)+3;
				} else {
					SolY = 600;
					SolX = rand.nextInt(290)+1;
					SolXsize = rand.nextInt(41)+30;
					SolYsize = SolXsize;
				}
				g.drawImage(image4, SolX1, SolY1,SolX1size,SolY1size, this);

				if (SolY1 >= -610) {
					SolY1 -= rand.nextInt(5)+3;
				} else {
					SolY1 = 600;
					SolX1 = rand.nextInt(191)+280;
					SolX1size = rand.nextInt(30)+20;
					SolY1size = SolX1size;
				}
				g.drawImage(image5, BonusX,BonusY,BonusXsize,BonusYsize, this);
				if (BonusY >= -4200) {
					BonusY -= rand.nextInt(6)+3;
				} else {
					BonusY = 600;
					BonusX = rand.nextInt(530)+1;
					BonusXsize = rand.nextInt(41)+30;
					BonusYsize = BonusXsize;
				}
			
		}


		
		




		g.drawImage(image, TavukX,TavukY,image.getWidth()/10,image.getHeight()/10,this);

		g.setColor(Color.yellow);

		for ( yumurta yumurta : yumurtalar) {
			g.fillOval(yumurta.getX(), yumurta.getY(),10, 15);
		}
		for ( yumurta2 yumurta2 : yumurtalar2) {
			g.fillOval(yumurta2.getX2(), yumurta2.getY2(),10, 15);
		}
		/*if(deathcontrol()) {
			
		}*/

		if((((KediX+KedisizeX)>= TavukX) &&((KediX)<=TavukX))&&(((KediY + KedisizeY)>=TavukY)&&(KediY<=TavukY))){
			timer.stop();

			String message ="GAME OVER...\n"+
		                     "Your Level "+ level_belirle()+"\n"+
					         "Your Points "+ points;
			JOptionPane.showMessageDialog(this, message);
			System.exit(0);
		}
		else if((((KediX1+KedisizeX1)>= TavukX) &&((KediX1)<=TavukX))&&(((KediY1 + KedisizeY1)>=TavukY)&&(KediY1<=TavukY))){
			timer.stop();
			
			String message ="GAME OVER...\n"+
		                     "Your Level "+ level_belirle()+"\n"+
					         "Your Points "+ points;
			JOptionPane.showMessageDialog(this, message);
			System.exit(0);
		}

		if (kontrol_et()){
		
			//System.exit(0);
		}
		if (bonuskontrol()){
			
			//System.exit(0);
		}
		
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString("Level " + level_belirle(), 20,25);
		g.drawString("Total Points " +points, 400,25);
		if(Stok>=0) {
			g.drawString("Ammo Left " +Stok, 400,75);
			
		}
		else{
			g.drawString("Ammo Left " +0, 400,75);
		}
	
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (yumurta yumurta : yumurtalar) {
 
			
			yumurta.setX(yumurta.getX() - YumurtadirX);

		}
		for (yumurta2 yumurta2 : yumurtalar2) {
			yumurta2.setX2(yumurta2.getX2() + YumurtadirX);

		}

		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();

		if (c == KeyEvent.VK_LEFT) {
			
			sag_sol_kontrol = 0;
			try {
				image = ImageIO.read(new FileImageInputStream(new File("tavuk.png")));
			} catch (IOException ex) {
				Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
			}
			if (TavukX <= 0) {
				TavukX = 0;
			} else {
				TavukX -= TavukdirX;
			}

		} else if (c == KeyEvent.VK_RIGHT) {
			sag_sol_kontrol = 1;
			try {
				image = ImageIO.read(new FileImageInputStream(new File("tavuk2.png")));
			} catch (IOException ex) {
				Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
			}

			if (TavukX >= 520) {
				TavukX = 520;
			} else {
				TavukX += TavukdirX;


			}
		} else if (c == KeyEvent.VK_SPACE) {
			if(Stok>0) {
				Stok=Stok-1;
				
			}
			else {
				Stok=0;
			}
			
			
			if(Stok>-1) {
				if (sag_sol_kontrol == 0) { 
					
					
					yumurtalar.add(new yumurta(TavukX - 15, TavukY + 15));
			
				} else if(sag_sol_kontrol == 1){

					
					yumurtalar2.add(new yumurta2(TavukX + 15, TavukY + 15));
					
				}

			}
			
			}
                 
			
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		/*try {
			image2=ImageIO.read(new FileImageInputStream(new File("gokyuzu.png")));
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE,null,ex);
		}*/

	}
}


