import javax.swing.JFrame;

public class main {
	public static void main(String[] args) {
		  
		  OyunEkraný ekran = new OyunEkraný("Chicken GAME ");
		  ekran.setResizable(false);
		  
		  ekran.setFocusable(false);
		  
		  ekran.setSize(600,600);
		  
		  ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  ekran.setLocationRelativeTo(null);
		  
		  Oyun oyun =new Oyun();
		  
		  oyun.requestFocus();
		  
		  oyun.addKeyListener(oyun);
		  
		  oyun.setFocusable(true);
		  
		  oyun.setFocusTraversalKeysEnabled(false);
		  
		  ekran.add(oyun);
		  
		  ekran.setVisible(true);
		  
	  }

}
