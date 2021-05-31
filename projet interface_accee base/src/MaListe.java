
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JList;


public class MaListe extends JList{

	
	@Override
	public String getToolTipText(MouseEvent e) {
		// TODO Auto-generated method stub
		int ind=this.locationToIndex(new Point(e.getX(),e.getY()));
		if(ind>=0){
			Contact c=Fenetre_Tp4.data.get(ind);
			return c.nom+" "+c.prenom;
		}
		else{
			return "";
		}
		
	}
}
