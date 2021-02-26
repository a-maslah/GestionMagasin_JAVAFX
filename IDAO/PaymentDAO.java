package IDAO;

import java.util.List;

import Entites.Payment;
import Entites.Vente;

public interface PaymentDAO {
public void createPayment(Payment p);
public List<Payment> getPayment(Vente vente);
}
