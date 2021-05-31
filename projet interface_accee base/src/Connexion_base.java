import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class Connexion_base {

	Connection con=null;
    Statement st=null;
 
    Connexion_base(String url,String user,String mdp)
	{
	/** etape1:chargement driver */
		
        try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver chargé...");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block<
			e.printStackTrace();
			System.out.println("erreur chargement driver"+e.getMessage());
	        	
		}
        
          /** étape 2: connecter à la base*/
        
        
        
        
        try {
			con=DriverManager.getConnection(url, user, mdp);
		    System.out.println("connexion établie...");
		    
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("erreur de connexion"+e.getMessage());
		}	
        
              /** etape 3 exécution des requetes */
        
        if(con!=null){
        	try {
				st=con.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.getMessage();}
        }
			
		
	}

	 ResultSet getallPersonne(){
		 try {
				ResultSet rs=st.executeQuery("select * from affiche");
				return rs;
     	
     	} catch (SQLException e) {
				// TODO Auto-generated catch block
			System.out.println("erreur de selection"+e.getMessage());
			return null;
			}
		 
	 }
	 
	 
	
	 
	 
	 
	 int SupprimerSelonpseudo(String pseudo){
		 String req="DELETE FROM affiche WHERE pseudo = ?";
		 PreparedStatement ps;
		 try {
				ps = con.prepareStatement(req);
				ps.setString(1, pseudo);
				int a=ps.executeUpdate();
				return a;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				return 0;
			}
	 }
	 
	 int inserer(String pseudo,String nom,String prenom)
	 {  int a=0;
		// ajout 
		 if(st!=null){
     		try {
     			
				a=st.executeUpdate("insert into affiche values('"+pseudo+"','"+nom+"','"+prenom+"')");
				System.out.println("insertion avec  succé");
				
				    
     		} catch (SQLException e) {
					// TODO Auto-generated catch block
                System.out.println("erreur insertion ");		
                
                }
     	}
		 return a;
	 }
	 
	 int Supprimertout(){
		 String req="DELETE FROM affiche WHERE pseudo LIKE '%'";
		 PreparedStatement ps;
		 try {
				ps = con.prepareStatement(req);
				int a=ps.executeUpdate();
				return a;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				return 0;
			}
	 }
	 
	 ResultSet getPersonneSelonpseudo(String pseudo)
	 {
		 String req="select * from affiche where pseudo Like ? ";
		 
		try {
			PreparedStatement ps = con.prepareStatement(req);
			 ps.setString(1, pseudo);
			
			 ResultSet rs=ps.executeQuery();
			 return rs;
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("erreur recherche");
			return null;
		}
		
		
	 }
	 int ModifierSelonNum(String pseudo1,String pseudo2){
		 String req="UPDATE affiche SET pseudo = ?  WHERE pseudo = ?";
		 PreparedStatement ps;
		 
		try {
			ps = con.prepareStatement(req);
			ps.setString(1,pseudo2);
			ps.setString(2, pseudo1);
			int a=ps.executeUpdate();
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return 0;
		}
		 
	 }
	
}

