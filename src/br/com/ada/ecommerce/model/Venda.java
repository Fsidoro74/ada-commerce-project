// ...
import br.com.ada.ecommerce.model.StatusPedido;
// ...

public class Venda {
    private StatusPedido status;
    // ...

    public Venda() {
        this.status = StatusPedido.ABERTO;
    }

    // Método para obter o status
    public StatusPedido getStatus() {
        return status;
    }

    // Método para alterar o status
    public void setStatus(StatusPedido novoStatus) {
        this.status = novoStatus;
    }
}
