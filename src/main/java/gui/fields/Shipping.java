package gui.fields;

import java.awt.Color;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.core.Center;
import gui.core.Ownable;
import gui.util.SwingComponentFactory;
import gui.core.Player;
import gui.util.Attrs;

public final class Shipping extends Ownable {
	private static final int TOPHEIGHT = 31;
	private static final int TITLEHEIGHT = 16;
	private static final int SUBTEXTHEIGHT = 14;
	private ImageIcon icon;
	private static int picCounter = 0;
	
	public Shipping(){
	    this(PICTURE, TITLE, SUBTEXT, DESCRIPTION, RENT, BG_COLOR, FG_COLOR);
	}
	public Shipping(String picture, String title, String subText, String description, String rent, Color bgColor, Color fgColor) {
		super(bgColor, fgColor, title, subText, description, rent);

		SwingComponentFactory factory = new SwingComponentFactory();
		if("default".equalsIgnoreCase(picture)) {
			int p = (picCounter++ % 4);
			URL path = Attrs.getImagePath("Field.Image.Ferry", p);
            this.icon = factory.createIcon(path);
		} else {
			try {
				this.icon = new ImageIcon(picture);
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println(Attrs.getString("Error.BadArgument.ImagePath", picture));
			}
		}

		JLabel topLabel = makeTopLabel();
		super.titleLabel = makeRoadNameLabel(this.title);
		super.subTextLabel = makeBottomLabel(this.subText);
		this.layered.add(topLabel, factory.createGridBagConstraints(0, 0));
		this.layered.add(this.titleLabel, factory.createGridBagConstraints(0, 1));
		this.layered.add(this.subTextLabel, factory.createGridBagConstraints(0, 2));
	}
	private JLabel makeTopLabel() {
		JLabel l = makeLabel(TOPHEIGHT);
		l.setIcon(this.icon);
		return l;
	}
	private JLabel makeRoadNameLabel(String titleShipping) {
		JLabel roadnameLabel = makeLabel(TITLEHEIGHT);
		roadnameLabel.setText(titleShipping);
		return roadnameLabel;
	}
	private JLabel makeBottomLabel(String subTextShipping) {
		JLabel bottomLabel = makeLabel(SUBTEXTHEIGHT);
		bottomLabel.setText(subTextShipping);
		return bottomLabel;
	}
	@Override
	public void displayOnCenter(Player[] playerList) {
		super.displayOnCenter(playerList);
		Center.label[1].setIcon(this.icon);
		Center.label[2].setText("__________________________");
		Center.label[3].setText(this.description);
		Center.label[4].setText(this.subText);
		if(this.ownerName != null) {
		    Center.label[5].setText(getOwnableLabel() + getOwnerName());
            Center.label[6].setText(getRentLabel() + getRent());
		}
		super.displayCarOnCenter(playerList);
	}
    @Override
    public String toString() {
        return "Shipping [ownerName=" + ownerName + ", bgColor=" + bgColor + ", fgColor=" + fgColor + ", title="
            + title + ", subText=" + subText + ", description=" + description
            + "]";
    }
   
	
}
