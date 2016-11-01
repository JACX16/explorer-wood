
import Modelo.Madeira;
import Persistencia.BancoDados;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ManterMadReg extends javax.swing.JFrame {




private void concluirActionPerformed(java.awt.event.ActionEvent evt) {

        Madeira m=new Madeira();
        m=m.Registrar(Integer.parseInt(nometexto.getText()),Integer.parseInt(codEsp.getText()),Integer.parseInt(peso2.getText()) ,Integer.parseInt(volume2.getText()));

        BancoDados bd=new BancoDados();

        try
          {
            bd.inserirMad(m);
          }
          catch(SQLException erro)
          {
             JOptionPane.showMessageDialog(null,"Não foi possível salvar os dados do registro, erro:"+erro);
          }

    }






}
