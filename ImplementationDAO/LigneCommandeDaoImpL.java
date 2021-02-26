package ImplementationDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DB.AbstractDAO;
import Entites.LigneCommande;
import Entites.Vente;
import IDAO.LigneCommandeDAO;

public class LigneCommandeDaoImpL extends AbstractDAO  implements LigneCommandeDAO{

	 Statement stm=null;
	    ResultSet rs;
	    
	    
	    @Override
	    public int getLastId() {
	        String sql="select MAX(id) from lignecommandes";
	        try {
	            rs=stm.executeQuery(sql);
	            if(rs.next()) {
	                return rs.getInt(1);
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return 0;
	    }
	    
	@Override
	public void createLigne(LigneCommande cmd, Vente v) {
		
        String sql="insert into lignecommandes(id_produit,qteVendu,stotal,id_vente) values('"+cmd.getProduit().getId()+"','"+cmd.getQteVendu()+"','"+cmd.getStotal()+"','"+v.getId()+"')";
        
        try {
        	stm=connection.createStatement();
        	if(!stm.execute(sql)){
                cmd.setId(this.getLastId());
            }
        }catch(SQLException e) {
        	System.out.println(e.getMessage());
        }
		
	}
	VenteDaoImpL daovente = new VenteDaoImpL();
ImplementationProduitDao daoproduit =new ImplementationProduitDao();
	 @Override
	    public List<LigneCommande> getLcmd(int id) {
		 String sql="select *from lignecommandes where id_vente="+id;
		 List<LigneCommande> lcmds = new ArrayList<>();
	        try {
	        	stm=connection.createStatement();

	            rs=stm.executeQuery(sql);
	            while(rs.next()) {
	           lcmds.add(new LigneCommande(rs.getInt("id"),daoproduit.find(rs.getInt("id_produit")),rs.getInt("qteVendu"),rs.getDouble("stotal"),daovente.find(rs.getInt("id_vente"))));
	            }
	            return lcmds;
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return null;
	    }
	
}
