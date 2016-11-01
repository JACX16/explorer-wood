/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classe;

import Persistencia.ConexaoBancoDeDados;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class Intermediaria {

 public Funcionario Funcionario(String usuario, String senha){
        ConexaoBancoDeDados cbd = new ConexaoBancoDeDados();
        cbd.zeraConsultasFuncionario();
        cbd.buscarFuncionario(Integer.parseInt(usuario));
        Funcionario f = cbd.getConsultasFuncionario().get(0);
        if(!f.equals(null)){
        if(f.getSenha().equals(senha)){
            return f;
        }else{
             return null;
        }
     }else{
            return null;
     }




 }

public boolean CadastroFuncionario(int nivel, String senha, String nome, String email, String logadouro, String numero, String complemento, String bairro,String estado, String cidade,  String CPF, String RG){
    Funcionario funcionario = new Funcionario();
    funcionario = funcionario.CadastroFuncionario(nivel, senha, nome, email, logadouro, numero, complemento, bairro,  estado, cidade, CPF, RG);
    if(funcionario== null){
        return false;
    }else{
        ConexaoBancoDeDados banco = new ConexaoBancoDeDados();
        banco.inserirFuncionario(senha, nivel, logadouro, numero, bairro, complemento, estado, cidade, email, CPF, nome, RG);
        XML xml = new XML();
            try {
                xml.gerarXmlFuncionario(funcionario);
            } catch (IOException ex) {
                Logger.getLogger(Intermediaria.class.getName()).log(Level.SEVERE, null, ex);
            }
        return true;
    }
}

public boolean CadastroEmitente(String razaoSocial,String nomeFantasia,String CNPJ, String inscricaoEstadual, String inscricaoMunicipal, String regimeTributario, String logradouro, String numero, String complemento, String bairro, String telefone, String pais, String uf,String municipio, String cep, String email){
    Emitente em = new Emitente();
    em = em.CadastroEminte(razaoSocial, nomeFantasia, CNPJ, inscricaoEstadual, inscricaoMunicipal, regimeTributario, logradouro, numero, complemento, bairro, telefone, pais, uf, municipio, cep, email);
    if(em == null){
        return false;
    }else{
        ConexaoBancoDeDados banco = new ConexaoBancoDeDados();
        banco.inserirEmitente(razaoSocial, nomeFantasia, CNPJ, inscricaoEstadual, inscricaoMunicipal, regimeTributario, logradouro, numero, complemento, bairro, telefone, pais, uf, municipio, cep, email);
        XML xml = new XML();
            try {
                xml.gerarXmlEmitente(em);
            } catch (IOException ex) {
                Logger.getLogger(Intermediaria.class.getName()).log(Level.SEVERE, null, ex);
            }
        return true;
    }
}

public void BuscarForncedores(JTable x){
            ConexaoBancoDeDados banco = new ConexaoBancoDeDados();
            int i = banco.todosFornecedores();
            DefaultTableModel modelo = (DefaultTableModel) x.getModel();
            modelo.setNumRows(i-1);

            for(int a=0;a<i-1;a++){
                Fornecedor f = banco.getConsultasFornecedor().get(a);
                 modelo.setValueAt(Integer.toString(f.getIdentificador()),a ,0);
               modelo.setValueAt(f.getEstado(),a ,3);
                banco.zeraConsultasFornecedorJuridico();
                 banco.zeraConsultasFornecedorFisico();
                FornecedorJuridico fornecedor = new FornecedorJuridico();
                FornecedorFisico fornecedor2 = new FornecedorFisico();

                   banco.buscaFornecedorJuridico(f.getIdentificador());
                   banco.buscaFornecedorFisico(f.getIdentificador());

                   int j = banco.getConsultasFornecedorJuridico().size();
                   int fi = banco.getConsultasFornecedorFisico().size();

                   System.out.println(fi);
               if(banco.getConsultasFornecedorJuridico().size()>0){
                   System.out.println("Juridico");
                    fornecedor = banco.getConsultasFornecedorJuridico().get(0);
                    modelo.setValueAt(fornecedor.getRazaoSocial(),a ,1);
                     modelo.setValueAt(fornecedor.getCNPJ(),a ,2);
                }

                       if(banco.getConsultasFornecedorFisico().size()>0){
                           System.out.println("Fisico");
                  System.out.println(banco.getConsultasFornecedorFisico().size());
                   fornecedor2 = banco.getConsultasFornecedorFisico().get(0);
                    modelo.setValueAt(fornecedor2.getNome(),a ,1);
                     modelo.setValueAt(fornecedor2.getCPF(),a ,2);


                   }




            }
}

public boolean cadastroProduto(String descricao, String nome, String tipo , int quantidade, int idFor) throws IOException{
    ConexaoBancoDeDados banco = new ConexaoBancoDeDados();
    System.out.print("nome in9ciei"+nome);
    banco.zeraConsultasFornecedor();
    banco.buscaFornecedor(idFor);

    Produto p = new Produto(descricao, nome,banco.getConsultasFornecedor().get(0) ,quantidade);
    if(p != null){
    banco.inserirProduto(p.getDescrição(), p.getNome(), tipo, p.getQuantidade(), p.getFornecedor().getIdentificador());
     System.out.print("nome"+p.getNome());
    XML xml = new XML();
    System.out.println(p.getDescrição());
   xml.gerarXmlProduto(p);
       return true;
    }else{
        return false;
    }
}

public boolean cadastroTransportadora(String razaoSocial, String cnpj, String inscricaoEstadual, String logadouro, String uf, String municipio, String numero, String bairro) throws IOException{
   Transportadora trans = new Transportadora();
   trans = trans.CadastroTransportadora(razaoSocial, uf, inscricaoEstadual, logadouro, uf, municipio, numero);
   if(trans != null){
       System.out.print("aki");
        ConexaoBancoDeDados banco = new ConexaoBancoDeDados();
        banco.inserirTransportadora(razaoSocial, cnpj, inscricaoEstadual, logadouro, uf, municipio, numero, bairro);
        XML xml = new XML();
        xml.gerarXmlTransportadora(trans);
        return true;
   }else{
       return false;
   }



}

public boolean cadastroFornecedorFisico(String logadouro, String numero, String bairro, String complemento, String estado, String cidade, String email, String CPF, String RG, String nome) throws IOException{
     FornecedorFisico fornecedor = new FornecedorFisico();
     fornecedor = fornecedor.cadastroFornecedorFisico(logadouro, numero, bairro, complemento, estado, cidade, email, CPF, RG, nome);
System.out.println("to ki");
     if(fornecedor != null){
          System.out.println("to ki");
      ConexaoBancoDeDados banco = new ConexaoBancoDeDados();
banco.inserirFornecedorFisico(logadouro, numero, bairro, complemento, estado, cidade, email);
XML xml = new XML();
   xml.gerarXmlFornecedorFisico(fornecedor);
return true;
    }else{
        return false;
    }
}
public boolean cadastroFornecedorJuridico(String logadouro, String numero, String bairro, String complemento, String estado, String cidade, String email, String razaoSocial, String nomeFantasia, String CNPJ, String inscricaoEstadual, String inscricaoMunicipal) throws IOException{
FornecedorJuridico fornecedor = new FornecedorJuridico();
fornecedor = fornecedor.cadastroFornecedorJuridico(logadouro, numero, bairro, complemento, estado, cidade, email, razaoSocial, nomeFantasia, CNPJ, inscricaoEstadual, inscricaoMunicipal);
if(fornecedor != null){
   ConexaoBancoDeDados banco = new ConexaoBancoDeDados();
banco.inserirFornecedorJuridico(logadouro, numero, bairro, complemento, estado, cidade, email, CNPJ, razaoSocial, nomeFantasia, inscricaoEstadual, inscricaoMunicipal);
XML xml = new XML();
xml.gerarXmlFornecedorJuridico(fornecedor);
return true;
    }else{
        return false;
    }
}

public boolean cadastroClienteFisico(String logadouro, String numero, String bairro, String complemento, String estado, String cidade, String email, String CPF, String RG, String nome) throws IOException{
     ClienteFisico cliente = new ClienteFisico();
     cliente = cliente.cadastroClienteFisico(logadouro, numero, bairro, complemento, estado, cidade, email, CPF, RG, nome);
System.out.println("to ki");
     if(cliente != null){
          System.out.println("to ki");
      ConexaoBancoDeDados banco = new ConexaoBancoDeDados();
banco.inserirClienteFisico(logadouro, numero, bairro, complemento, estado, cidade, email, RG, nome, RG);
XML xml = new XML();
   xml.gerarXmlClienteFisico(cliente);
return true;
    }else{
        return false;
    }
}
public boolean cadastroClienteJuridico(String logadouro, String numero, String bairro, String complemento, String estado, String cidade, String email, String razaoSocial,  String CNPJ, String inscricaoEstadual, String inscricaoMunicipal) throws IOException{
ClienteJuridico cliente = new ClienteJuridico();
cliente = cliente.cadastroClienteJuridico(logadouro,  numero,  bairro,  complemento,  estado,  cidade,  email,  razaoSocial,  CNPJ,  inscricaoEstadual,  inscricaoMunicipal);
if(cliente!= null){
   ConexaoBancoDeDados banco = new ConexaoBancoDeDados();
banco.inserirClienteJuridico(logadouro, numero, bairro, complemento, estado, cidade, email, CNPJ, razaoSocial, email, inscricaoEstadual, inscricaoMunicipal);
        XML xml = new XML();
xml.gerarXmlClienteJuridico(cliente);
return true;
    }else{
        return false;
    }
}

public void BuscarCliente(JTable x){
            ConexaoBancoDeDados banco = new ConexaoBancoDeDados();
            banco.zeraConsultasCliente();
            int i = banco.todosClientes();
            DefaultTableModel modelo = (DefaultTableModel) x.getModel();
            modelo.setNumRows(i-1);

            for(int a=0;a<i-1;a++){
                Cliente f = banco.getConsultasCliente().get(a);
                 modelo.setValueAt(Integer.toString(f.getIdentificador()),a ,0);
               modelo.setValueAt(f.getEstado(),a ,3);
                banco.zeraConsultasClienteJuridico();
                 banco.zeraConsultasClienteFisico();
                ClienteJuridico cliente = new ClienteJuridico();
                ClienteFisico cliente2 = new ClienteFisico();

                   banco.buscaClienteJuridico(f.getIdentificador());
                   banco.buscaClienteFisico(f.getIdentificador());

                   int j = banco.getConsultasClienteJuridico().size();
                   int fi = banco.getConsultasClienteFisico().size();

                   System.out.println(fi);
               if(banco.getConsultasClienteJuridico().size()>0){
                   System.out.println("Juridico");
                    cliente = banco.getConsultasClienteJuridico().get(0);
                    modelo.setValueAt(cliente.getRazaoSocial(),a ,1);
                     modelo.setValueAt(cliente.getCNPJ(),a ,2);
                }

                       if(banco.getConsultasClienteFisico().size()>0){
                           System.out.println("Fisico");
                  System.out.println(banco.getConsultasClienteFisico().size());
                   cliente2 = banco.getConsultasClienteFisico().get(0);
                    modelo.setValueAt(cliente2.getNome(),a ,1);
                     modelo.setValueAt(cliente2.getCPF(),a ,2);


                   }




            }
}




    public boolean cadastroCupomFiscal(String emitente, int cliente, int produto) throws IOException, Exception{
        CupomFiscal cf = new CupomFiscal();
        ConexaoBancoDeDados c = new ConexaoBancoDeDados();
        c.zeraConsultasCliente();
        c.zeraConsultasEmitente();
        c.zeraConsultasProduto();
        c.buscaCliente(cliente);
        c.buscarProduto(cliente);
        c.buscarEmitente(emitente);
        cf = cf.EmitirCupomFiscal(c.getConsultasEmitente().get(0),c.getConsultasProduto().get(0) , c.getConsultasCliente().get(0));

        if(cf!=null){
            XML xml = new XML();
        xml.gerarXmlNotaFiscal(cf);
        return true;
        }else{
            return false;
        }
    }

    public void BuscarEmitente(JTable x) {
       ConexaoBancoDeDados banco = new ConexaoBancoDeDados();
            banco.zeraConsultasEmitente();
            int i = banco.todosEmitente();
            DefaultTableModel modelo = (DefaultTableModel) x.getModel();
            modelo.setNumRows(i-1);
System.out.println("i = "+i);
            for(int a=0;a<i-1;a++){
                Emitente f = banco.getConsultasEmitente().get(a);
               f.setIdentificador(banco.getConsultasEmitente().get(a).getIdentificador());
                 modelo.setValueAt(Integer.toString(f.getIdentificador()),a ,0);
               modelo.setValueAt(f.getRazaoSocial(),a ,1);
               modelo.setValueAt(f.getUf(),a ,3);
            modelo.setValueAt(f.getCNPJ(),a ,2);
            }
    }
    public void BuscarProduto(JTable x) {
       ConexaoBancoDeDados banco = new ConexaoBancoDeDados();
            banco.zeraConsultasProduto();
            int i = banco.todosProdutos();
            DefaultTableModel modelo = (DefaultTableModel) x.getModel();
            modelo.setNumRows(i-1);

            for(int a=0;a<i-1;a++){
                Produto f = banco.getConsultasProduto().get(a);
                f.setIdentificador(banco.getConsultasProduto().get(a).getCodigo());
                 modelo.setValueAt(Integer.toString(f.getIdentificador()),a ,0);
               modelo.setValueAt(f.getNome(),a ,1);
               System.out.println(f.getNome());
               System.out.println(f.getIdentificador());

            }
    }

    public void BuscarTransportadora(JTable x) {
        ConexaoBancoDeDados banco = new ConexaoBancoDeDados();
            banco.zeraConsultasTransportadora();
            int i = banco.todosTransportadora();
            DefaultTableModel modelo = (DefaultTableModel) x.getModel();
            modelo.setNumRows(i-1);

            for(int a=0;a<i-1;a++){
                Transportadora f = banco.getConsultasTransportadora().get(a);
                f.setIdentificador(banco.getConsultasProduto().get(a).getCodigo());
                 modelo.setValueAt(Integer.toString(f.getIdentificador()),a ,0);
               modelo.setValueAt(f.getRazãoSocial(),a ,1);


            }
    }

    public void cadastraNotaFiscal(Emitente em, String cliente, String fornecedor, String produto, String transportadora) throws IOException, Exception {
        NotaFiscal nf = new NotaFiscal();
        ConexaoBancoDeDados c = new ConexaoBancoDeDados();
        c.zeraConsultasFornecedor();
        c.zeraConsultasProduto();
        c.zeraConsultasCliente();
        c.zeraConsultasTransportadora();
//        c.buscaCliente(Integer.parseInt(cliente));
       // c.buscaFornecedor(Integer.parseInt(fornecedor));
        //c.buscarProduto(Integer.parseInt(produto));
//        c.buscarTransportadora(Integer.parseInt(transportadora));
        nf = nf.EmitirNotaFiscal(em, null, null, null, null);
        XML xml = new XML();
        xml.gerarXmlNotaFiscal(nf);
    }

    public void cadastraNotaFiscal(Emitente em, String cliente, String fornecedor, String produto) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
