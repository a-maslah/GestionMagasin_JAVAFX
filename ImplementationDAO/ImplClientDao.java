package ImplementationDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DB.AbstractDAO;
import DB.SingleConnection;
import Entites.Client;
import IDAO.ClientDAO;

public class ImplClientDao extends AbstractDAO implements ClientDAO{

    Statement stm=null;
    ResultSet rs=null;

    
    

    @Override
    public void create(Client clt) {
        String sql="insert into clients(nom,prenom,telephone,email,adresse) values('"+clt.getNom()+"','"+clt.getPrenom()+"','"+clt.getTelephone()+"','"+clt.getEmail()+"','"+clt.getAdresse()+"')";
        try {
        	stm=connection.createStatement();

            stm.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

	@Override
	public void delete(Client clt) throws SQLException {
		// TODO Auto-generated method stub
		 String sql="delete from clients where id="+clt.getId();
	        try {
	        	stm=connection.createStatement();
	             stm.execute(sql);
	             System.out.println("client bien supprimer");
	        } catch (SQLException e) {
	            throw e;
	        }
	}

	@Override
	public void update(Client clt) {
		// TODO Auto-generated method stub
		String sql="update clients set nom ='"+clt.getNom()+"',prenom='"+clt.getPrenom()+"',telephone='"+clt.getTelephone()+"',email='"+clt.getEmail()+"',adresse='"+clt.getAdresse()+"'  where id='"+clt.getId()+"' ";
        try {
        	stm=connection.createStatement();
            if (stm.execute(sql))
                System.out.println("client bien modifier");
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<Client> getAll() {
		String sql="select *from clients ";
		List<Client> listClts = new ArrayList<Client>();
		try {
        	stm=connection.createStatement();

			rs=stm.executeQuery(sql);
			while(rs.next()) {
                listClts.add(new Client(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("telephone"),rs.getString("email"),rs.getString("adresse")));
            }
            return listClts;
		}catch(SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	

	 @Override
	    public int getIdClient() {
	        String sql = "select MAX(id) from categories";
	        try {
	        	stm=connection.createStatement();
	            rs = stm.executeQuery(sql);
	            if (rs.next()) {
	                return rs.getInt(1)+1;
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return 0;

	    }

	@Override
	public Client find(int id) {
		String sql="select *from clients where id="+id;
        try {
        	stm=connection.createStatement();

            rs=stm.executeQuery(sql);
            if(rs.next()) {
                return new Client(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
	}
}
