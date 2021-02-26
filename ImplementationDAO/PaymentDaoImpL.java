package ImplementationDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DB.AbstractDAO;
import Entites.Payment;
import Entites.PaymentType;
import Entites.Vente;
import IDAO.PaymentDAO;

public class PaymentDaoImpL extends AbstractDAO implements PaymentDAO {

	 Statement stm=null;
	    ResultSet rs=null;

	    
	@Override
	public void createPayment(Payment p) {
		 String sql="insert into payments(id_vente,montant,date,type) values('"+p.getVente().getId()+"','"+p.getMontant()+"','"+p.getDate()+"','"+p.getType()+"')";
	        try {
	        	stm=connection.createStatement();
	            stm.execute(sql);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        } 
	}

   VenteDaoImpL daovente  = new  VenteDaoImpL();
	@Override
	public List<Payment> getPayment(Vente vente) {
		  String sql = "select *from payments where id_vente=" + vente.getId();
	        List<Payment> pay = new ArrayList<Payment>();
	        try {
	        	
	        	stm=connection.createStatement();
	            rs = stm.executeQuery(sql);
	            while (rs.next()) {
	                pay.add(new Payment(rs.getInt("id"),daovente.find(rs.getInt("id")),rs.getDouble("montant"), rs.getDate("date").toLocalDate(),PaymentType.valueOf(rs.getString("type"))));

	            }
	            return  pay;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	}

}
