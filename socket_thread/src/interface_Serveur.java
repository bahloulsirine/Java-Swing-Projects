import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class interface_Serveur extends JFrame{
 public static JTextArea txt;
	public interface_Serveur() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("serveur");
		this.setSize(400, 300);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		
		
		
		
		txt=new JTextArea();
		this.add(txt,BorderLayout.CENTER);
		txt.setEditable(false);
	}

}
