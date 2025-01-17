package gui.tests;

import gui.core.Field;
import gui.core.Ownable;
import gui.core.Player;
import gui.main.GUI;


/**
 * @author Daniel Kolditz Rubin-Grøn
 */
public class TestRunExampleGame{

	public static void main(String[] args){
		realExampleGame();
	}

	private static void realExampleGame(){
		// Setup game
	    Player mn = new Player("Mads", 30000), sh = new Player("Stig", 30000);
	    
	    GUI gui = new GUI();
		sleep();
		gui.addPlayer(mn);
		sleep();
		gui.addPlayer(sh);
		sleep();
		gui.getFields()[0].setCar(mn, true);
		sleep();
		gui.getFields()[0].setCar(sh, true);
		
		// Move player 1
		sleep();
		gui.setDice(1, 2);
		sleep();
		for(Field f : gui.getFields()) f.setCar(mn, false);
		gui.getFields()[1].setCar(mn, true);
		sleep();
		for(Field f : gui.getFields()) f.setCar(mn, false);
        gui.getFields()[2].setCar(mn, true);
		sleep();
		for(Field f : gui.getFields()) f.setCar(mn, false);
        gui.getFields()[3].setCar(mn, true);;
		sleep();
		mn.setBalance(28000);
		Field f = gui.getFields()[3];
        if(f instanceof Ownable){
            Ownable o = (Ownable) f;
            o.setBorder(mn.getPrimaryColor(), mn.getSecondaryColor());
        }
		sleep();
		gui.displayChanceCard("De har vundet vild med dans og skifter navn til Allan!");
	}
	public static void sleep(){
		sleep(1200);
	}
	public static void sleep(int n){
		long t0, t1;
		t0 = System.currentTimeMillis();
		do{
			t1 = System.currentTimeMillis();
		}
		while((t1 - t0) < (n));
	}
}