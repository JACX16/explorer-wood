/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import classe.Cliente;
import classe.ClienteFisico;
import classe.ClienteJuridico;
import classe.CupomFiscal;
import classe.Emitente;
import classe.Fornecedor;
import classe.FornecedorFisico;
import classe.FornecedorJuridico;
import classe.Funcionario;
import classe.NotaFiscal;
import classe.Produto;
import classe.Transportadora;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paula
 */
public class ConexaoBancoDeDados {
       private  String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
       private String DB_URL = "jdbc:mysql://localhost:3306/sicone";
       private  Connection connection;
       static java.sql.Statement sql;
       private boolean valida;
       private String  query;
       private ArrayList <Produto> consultasProduto = new <Produto> ArrayList();
       private ArrayList <Emitente> consultasEmitente = new <Emitente> ArrayList();
       private ArrayList <Funcionario> consultasFuncionario = new <Funcionario> ArrayList();
       private ArrayList <CupomFiscal> consultasCupomFiscal = new <CupomFiscal> ArrayList();
       private ArrayList <NotaFiscal> consultasNotaFiscal = new <NotaFiscal> ArrayList();
       private ArrayList <Cliente> consultasCliente = new <Cliente> ArrayList();
       private ArrayList <Fornecedor> consultasFornecedor = new <Fornecedor> ArrayList();
       private ArrayList <ClienteFisico> consultasClienteFisico = new <ClienteFisico> ArrayList();
       private ArrayList <ClienteJuridico> consultasClienteJuridico = new <ClienteJuridico> ArrayList();
       private ArrayList <FornecedorJuridico> consultasFornecedorJuridico = new <FornecedorJuridico> ArrayList();
       private ArrayList <FornecedorFisico> consultasFornecedorFisico = new <FornecedorFisico> ArrayList();
       private ArrayList <Transportadora> consultasTransportadora = new <Transportadora> ArrayList();

       private int ident;

    public ArrayList<Cliente> getConsultasCliente() {
        return consultasCliente;
    }

    public ArrayList<ClienteFisico> getConsultasClienteFisico() {
        return consultasClienteFisico;
    }

    public ArrayList<ClienteJuridico> getConsultasClienteJuridico() {
        return consultasClienteJuridico;
    }

    public ArrayList<CupomFiscal> getConsultasCupomFiscal() {
        return consultasCupomFiscal;
    }

    public ArrayList<Emitente> getConsultasEmitente() {
        return consultasEmitente;
    }

    public ArrayList<Fornecedor> getConsultasFornecedor() {
        return consultasFornecedor;
    }

    public ArrayList<FornecedorFisico> getConsultasFornecedorFisico() {
        return consultasFornecedorFisico;
    }

    public ArrayList<FornecedorJuridico> getConsultasFornecedorJuridico() {
        return consultasFornecedorJuridico;
    }

    public ArrayList<Funcionario> getConsultasFuncionario() {
        return consultasFuncionario;
    }

    public ArrayList<NotaFiscal> getConsultasNotaFiscal() {
        return consultasNotaFiscal;
    }

    public ArrayList<Produto> getConsultasProduto() {
        return consultasProduto;
    }

    public ArrayList<Transportadora> getConsultasTransportadora() {
        return consultasTransportadora;
    }
       
public void zeraConsultasProduto (){
       consultasProduto.clear();
}
public void zeraConsultasEmitente (){
       consultasEmitente.clear();
}
public void zeraConsultasTransportadora (){
    consultasTransportadora.clear();
}
public void zeraConsultasFornecedorFisico (){
    consultasFornecedorFisico.clear();
}
public void zeraConsultasFornecedorJuridico(){
    consultasFornecedorJuridico.clear();
}
public void zeraConsultasClienteJuridico(){
    consultasClienteJuridico.clear();
}
public void zeraConsultasClienteFisico(){
    consultasClienteFisico.clear();
}
public void zeraConsultasFornecedor(){
    consultasFornecedor.clear();
}
public void zeraConsultasFuncionario(){
    consultasFuncionario.clear();
}
public void zeraConsultasCupomFiscal(){
    consultasCupomFiscal.clear();
}
public void zeraConsultasNotaFiscal(){
    consultasNotaFiscal.clear();
}
public void zeraConsultasCliente(){
    consultasCliente.clear();
}
public boolean isValida() {
        return valida;
    }
public void setValida(boolean valida) {
        this.valida = valida;
    }
public void iniciaConexao() {

              try {
                 Class.forName(JDBC_DRIVER);
                System.out.println("Driver JDBC carregado com sucesso!");

                 connection =  (Connection) DriverManager.getConnection(DB_URL, "root", "pco90.");  //coersão aki
               System.out.println("Conexão estabelecida - Banco " + DB_URL);
                sql = connection.createStatement();
               
               // sql.executeUpdate("insert into dados.arquivos values (25, 'Carolina', 'Minas')");
                //sql.executeUpdate("delete from dados.arquivos where idArquivo = 2");
              
           } catch (Exception e) {
                System.err.println("Erro em "+ e.getMessage());
            }
         }
public void encerraConexao(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
           }
public void buscarCliente(String logadouro, String numero, String bairro, String complemento, String estado, String cidade, String email){
        try {
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
             query ="select * from sicone.cliente where logadouro ='"+logadouro+"' and numero = '"+numero+"' and bairro ='"+bairro+"' and complemento ='"+complemento+"'and estado ='"+estado+"'and cidade ='"+cidade+"'and email = '"+email+"';";
            ResultSet rs = sql.executeQuery(query);
       if(!rs.equals(null)){
           while(rs.next()) {
               Cliente c = new Cliente();
               c.setBairro(rs.getString("bairro"));
               c.setCidade(rs.getString("cidade"));
               c.setComplemento(rs.getString("complemento"));
               c.setEmail(rs.getString("email"));
               c.setEstado(rs.getString("estado"));
               c.setIdentificador(Integer.parseInt(rs.getString("identificador")));
               c.setLogadouro(rs.getString("logadouro"));
               c.setNumero(rs.getString("numero"));
               consultasCliente.add(c);
               ident = rs.getInt(1);
               System.out.println(ident);
                
                  
             }
           setValida(true);
                this.encerraConexao();
                
                  
             }else{
         
        
         this.encerraConexao();
        setValida(false);
        
       }
       this.encerraConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            this.encerraConexao();
          setValida(false);
        }
     
 }

private void inserirCliente(String logadouro, String numero, String bairro, String complemento, String estado, String cidade, String email){
        try{
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
            query = "insert into sicone.cliente (logadouro, numero, bairro, complemento, estado, cidade, email) values ('"+logadouro+"','"+numero+"','"+bairro+"','"+complemento+"','"+estado+"','"+cidade+"','"+email+"');" ;
       sql.executeUpdate(query);
        this.encerraConexao(); 
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
private void inserirClienteFisico( String cpf, String nome, String rg){
      try{
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
            query = "insert into sicone.clientefisico (identificadorCliente, CPF, nome, RG) values ("+ident+",'"+cpf+"','"+nome+"','"+rg+"');" ;
       sql.executeUpdate(query);
        this.encerraConexao(); 
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
public void buscarFornecedor(String logadouro, String numero, String bairro, String complemento, String estado, String cidade, String email){
        try {
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
             query ="select * from sicone.fornecedor where logadouro ='"+logadouro+"' and numero = '"+numero+"' and bairro ='"+bairro+"' and complemento ='"+complemento+"'and estado ='"+estado+"'and cidade ='"+cidade+"'and email = '"+email+"';";
            ResultSet rs = sql.executeQuery(query);
       if(!rs.equals(null)){
           while(rs.next()) {
               Fornecedor f = new Fornecedor();
               f.setBairro(rs.getString("bairro"));
               f.setCidade(rs.getString("cidade"));
               f.setComplemento(rs.getString("complemento"));
               f.setEmail(rs.getString("email"));
               f.setEstado(rs.getString("estado"));
               f.setIdentificador(Integer.parseInt(rs.getString("identificador")));
               f.setLogadouro(rs.getString("logadouro"));
               f.setNumero(rs.getString("numero"));
               consultasFornecedor.add(f);
               ident = rs.getInt(1);
               System.out.println(ident);
                setValida(true);
                  
             }
                this.encerraConexao();
                
                  
             }else{
         
        
         this.encerraConexao();
        setValida(false);
        
       }
       this.encerraConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            this.encerraConexao();
          setValida(false);
        }
     
 }
public void inserirClienteFisico(String logadouro, String numero, String bairro, String complemento, String estado, String cidade, String email, String cpf, String nome, String rg){
    inserirCliente( logadouro,  numero,  bairro, complemento, estado, cidade, email);
        buscarCliente( logadouro,  numero,  bairro, complemento, estado, cidade, email);
        if(this.isValida()){
            inserirClienteFisico(cpf,nome,rg);
            setValida(false);
        }else{
            
        }
    }
private void inserirClienteJuridico(String cnpj,  String razaoSocial, String nomeFantasia,String inscricaoEstadual, String inscricaoMunicipal){
    try{
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
            query = "insert into sicone.clientejurifico (CNPJ, idenfificadorCliente, razaoSocial, nomeFantasia, inscricaoEstadual, inscricaoMunicipal) values ('"+cnpj+"',"+ident+",'"+razaoSocial+"','"+nomeFantasia+"','"+inscricaoEstadual+",'"+inscricaoMunicipal+"');" ;
       sql.executeUpdate(query);
        this.encerraConexao(); 
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public void inserirClienteJuridico(String logadouro, String numero, String bairro, String complemento, String estado, String cidade, String email, String cnpj,  String razaoSocial, String nomeFantasia,String inscricaoEstadual, String inscricaoMunicipal){
    inserirCliente( logadouro,  numero,  bairro, complemento, estado, cidade, email);
        buscarCliente( logadouro,  numero,  bairro, complemento, estado, cidade, email);
        if(this.isValida()){
            inserirClienteJuridico(cnpj, razaoSocial, nomeFantasia, inscricaoEstadual, inscricaoMunicipal);
            setValida(false);
        }
    }
private void inserirFornecedor(String logadouro, String numero, String bairro, String complemento, String estado, String cidade, String email){
        try{
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
            query = "insert into sicone.fornecedor (logadouro, numero, bairro, complemento, estado, cidade, email) values ('"+logadouro+"','"+numero+"','"+bairro+"','"+complemento+"','"+estado+"','"+cidade+"','"+email+"');" ;
       sql.executeUpdate(query);
        this.encerraConexao(); 
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public void inserirFornecedorFisico( String cpf, String nome, String rg){
      try{
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
            query = "insert into sicone.fornecedorfisico (identificadorCliente, CPF, nome, RG) values ("+ident+",'"+cpf+"','"+nome+"','"+rg+"');" ;
       sql.executeUpdate(query);
        this.encerraConexao(); 
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public void inserirFornecedorFisico(String logadouro, String numero, String bairro, String complemento, String estado, String cidade, String email){
    inserirFornecedor( logadouro,  numero,  bairro, complemento, estado, cidade, email);
        buscarFornecedor( logadouro,  numero,  bairro, complemento, estado, cidade, email);
        if(this.isValida()){
            System.out.println("deu");
        }
    }
private void inserirFornecedorJuridico(String cnpj,  String razaoSocial, String nomeFantasia,String inscricaoEstadual, String inscricaoMunicipal){
    try{
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
            query = "insert into sicone.fornecedorjuridico (CNPJ, identificadoFornecedor, razaoSocial, nomeFantasia, inscricaoEstadual, inscricaoMunicipal) values ('"+cnpj+"',"+ident+",'"+razaoSocial+"','"+nomeFantasia+"','"+inscricaoEstadual+"','"+inscricaoMunicipal+"');" ;
       sql.executeUpdate(query);
        this.encerraConexao(); 
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public void inserirFornecedorJuridico(String logadouro, String numero, String bairro, String complemento, String estado, String cidade, String email, String cnpj,  String razaoSocial, String nomeFantasia,String inscricaoEstadual, String inscricaoMunicipal){
    inserirFornecedor( logadouro,  numero,  bairro, complemento, estado, cidade, email);
        buscarFornecedor( logadouro,  numero,  bairro, complemento, estado, cidade, email);
        if(this.isValida()){
            inserirFornecedorJuridico(cnpj, razaoSocial, nomeFantasia, inscricaoEstadual, inscricaoMunicipal);
            setValida(false);
        }
    }
public void inserirEmitente(String razaoSocial,String nomeFantasia,String CNPJ, String inscricaoEstadual, String inscricaoMunicipal, String regimeTributario, String logradouro, String numero, String complemento, String bairro, String telefone, String pais, String uf,String municipio, String cep, String email){
       try{
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
            query = "insert into sicone.emitente (razaoSocial, nomeFantasia, CNPJ, inscricaoEstadual, inscricaoMunicipal, regimeTributario, logradouro, numero,  complemento, bairro, telefone, pais, uf, municipio, cep, email) values('"+razaoSocial+"','"+nomeFantasia+"','"+CNPJ+"','"+inscricaoEstadual+"','"+inscricaoMunicipal+"','"+regimeTributario+"','"+logradouro+"','"+numero+"','"+complemento+"','"+bairro+"','"+telefone+"','"+pais+"','"+uf+"','"+municipio+"','"+cep+"','"+email+"');";
       sql.executeUpdate(query);
        this.encerraConexao(); 
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void inserirProduto(String descricao, String nome, String tipo , int quantidade, int idFor){
 try{
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
            query = "insert into sicone.produto (descricao, nome, tipo, quantidade, fornecedor) values('"+descricao+"','"+nome+"','"+tipo+"',"+quantidade+","+idFor+");";
       sql.executeUpdate(query);
        this.encerraConexao(); 
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void buscaFornecedor(int identificador){

        try {
              this.iniciaConexao();
            sql = (Statement) connection.createStatement();
              query ="select * from sicone.fornecedor where identificador ="+identificador+";";
              ResultSet rs = sql.executeQuery(query);
               if(!rs.equals(null)){
                    while(rs.next()) {
                        Fornecedor f = new Fornecedor();
                        f.setBairro(rs.getString("bairro"));
                        f.setCidade(rs.getString("cidade"));
                        f.setComplemento(rs.getString("complemento"));
                        f.setEmail(rs.getString("email"));
                        f.setLogadouro(rs.getString("logadouro"));
                        f.setNumero(rs.getString("numero"));
                        f.setIdentificador(Integer.parseInt(rs.getString("identificador")));
                        f.setBairro(rs.getString("bairro"));

                        consultasFornecedor.add(f);
                     }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }

}
public void buscarProduto(int identificador){
    try {
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
             query ="select * from sicone.produto where identificador ="+identificador+";";
            ResultSet rs = sql.executeQuery(query);
       if(!rs.equals(null)){
           while(rs.next()) {
               Produto p = new Produto();
               p.setCodigo(Integer.parseInt(rs.getString("identificador")));
               p.setDescrição(rs.getString("descricao"));
               p.setNome(rs.getString("nome"));
               p.setQuantidade(Integer.parseInt(rs.getString("quantidade")));
               zeraConsultasFornecedor();
              // buscaFornecedor(Integer.parseInt(rs.getString("fornecedor")));
             //  p.setFornecedor(consultasFornecedor.get(0));
               consultasProduto.add(p);
               ident = rs.getInt(1);
               System.out.println(ident);
                setValida(true);
                  
             }
           zeraConsultasFornecedor();
                this.encerraConexao();
                
                  
             }else{
         
        
         this.encerraConexao();
        setValida(false);
        
       }
       this.encerraConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            this.encerraConexao();
          setValida(false);
        }
}
public void inserirTransportadora(String razaoSocial, String cnpj, String inscricaoEstadual, String logadouro, String uf, String municipio, String numero, String bairro){
    try{
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
            query = "insert into sicone.transportadora (razaoSocial, cnpj, inscricaoEstadual, logadouro, uf, municipio, numero, bairro) values('"+razaoSocial+"','"+cnpj+"','"+inscricaoEstadual+"','"+logadouro+"','"+uf+"','"+municipio+"','"+numero+"','"+bairro+"');";
       sql.executeUpdate(query);
        this.encerraConexao(); 
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public void buscarTransportadora(int identificador){
      try {
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
             query ="select * from sicone.transportadora where identificador ="+identificador+";";
            ResultSet rs = sql.executeQuery(query);
       if(!rs.equals(null)){
           while(rs.next()) {
               ident = rs.getInt(1);
               Transportadora t = new Transportadora();
               t.setCNPJ(rs.getString("CNPJ"));
               t.setInscricaoEstadual(rs.getString("inscricaoEstadual"));
               t.setLogadouro(rs.getString("logadouro"));
               t.setMunicipio(rs.getString("municipio"));
               t.setRazaoSocial(rs.getString("razaoSocial"));
               t.setNumero(rs.getString("numero"));
               t.setUf(rs.getString("uf"));
               t.setIdentificador(Integer.parseInt(rs.getString("identificador")));
               consultasTransportadora.add(t);
               System.out.println(ident);
                setValida(true);
                  
             }
                this.encerraConexao();
                
                  
             }else{
         
        
         this.encerraConexao();
        setValida(false);
        
       }
       this.encerraConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            this.encerraConexao();
          setValida(false);
        } 
}
public void buscarEmitente(String cnpj){
    try {
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
             query ="select * from sicone.emitente where CNPJ ='"+cnpj+"';";
            ResultSet rs = sql.executeQuery(query);
       if(!rs.equals(null)){
           while(rs.next()) {
               Emitente e = new Emitente();
               e.setBairro(rs.getString("bairro"));
                        e.setMunicipio(rs.getString("municipio"));
                        e.setComplemento(rs.getString("complemento"));
                        e.setEmail(rs.getString("email"));
                        e.setLogradouro(rs.getString("logradouro"));
                        e.setNumero(rs.getString("numero"));
                        e.setIdentificador(Integer.parseInt(rs.getString("identificador")));
                        e.setBairro(rs.getString("bairro"));
                        e.setCNPJ(rs.getString("CNPJ"));
                        e.setCep(rs.getString("cep"));
                        e.setUf(rs.getString("uf"));
                        e.setRegimeTributario(rs.getString("regimeTributario"));
                        e.setRazaoSocial(rs.getString("razaoSocial"));
                        e.setInscricaoEstadual(rs.getString("inscricaoEstadual"));
                        e.setInscricaoMunicipal(rs.getString("inscricaoMunicipal"));
                        e.setPais(rs.getString("pais"));
                        e.setNomeFantasia(rs.getString("nomeFantasia"));
               consultasEmitente.add(e);
               ident = rs.getInt(1);
               System.out.println(ident);
                setValida(true);
                  
             }
                this.encerraConexao();
                
                  
             }else{
         
        
         this.encerraConexao();
        setValida(false);
        
       }
       this.encerraConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            this.encerraConexao();
          setValida(false);
        }
}
public void inserirCupomFiscal(int emitente, int produto, int cliente){
    try{
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
            query = "insert into sicone.cupomfiscal (emitente, produto, cliente) values("+emitente+","+produto+","+cliente+");";
       sql.executeUpdate(query);
        this.encerraConexao(); 
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public void buscaEmitente(int identificador){

        try {
              this.iniciaConexao();
            sql = (Statement) connection.createStatement();
              query ="select * from sicone.emitente where identificador ="+identificador+";";
              ResultSet rs = sql.executeQuery(query);
               if(!rs.equals(null)){
                    while(rs.next()) {
                        Emitente e = new Emitente();
                        e.setBairro(rs.getString("bairro"));
                        e.setMunicipio(rs.getString("municipio"));
                        e.setComplemento(rs.getString("complemento"));
                        e.setEmail(rs.getString("email"));
                        e.setLogradouro(rs.getString("logradouro"));
                        e.setNumero(rs.getString("numero"));
                        e.setIdentificador(Integer.parseInt(rs.getString("identificador")));
                        e.setBairro(rs.getString("bairro"));
                        e.setCNPJ(rs.getString("CNPJ"));
                        e.setCep(rs.getString("cep"));
                        e.setUf(rs.getString("uf"));
                        e.setRegimeTributario(rs.getString("regimeTributario"));
                        e.setRazaoSocial(rs.getString("razaoSocial"));
                        e.setInscricaoEstadual(rs.getString("inscricaoEstadual"));
                        e.setInscricaoMunicipal(rs.getString("inscricaoMunicipal"));
                        e.setPais(rs.getString("pais"));
                        e.setNomeFantasia(rs.getString("nomeFantasia"));

                        consultasEmitente.add(e);
                     }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }

}
public void buscaCliente(int identificador){

        try {
              this.iniciaConexao();
            sql = (Statement) connection.createStatement();
              query ="select * from sicone.cliente where identificador ="+identificador+";";
              ResultSet rs = sql.executeQuery(query);
               if(!rs.equals(null)){
                    while(rs.next()) {
                        Cliente c = new Cliente();
                        c.setBairro(rs.getString("bairro"));
                        c.setCidade(rs.getString("cidade"));
                        c.setComplemento(rs.getString("complemento"));
                        c.setEmail(rs.getString("email"));
                        c.setLogadouro(rs.getString("logadouro"));
                        c.setNumero(rs.getString("numero"));
                        c.setIdentificador(Integer.parseInt(rs.getString("identificador")));
                        c.setBairro(rs.getString("bairro"));

                        consultasCliente.add(c);
                     }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }

}
public void buscarCupomFiscal(int identificador){
    try {
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
             query ="select * from sicone.buscarCupomFiscal where identificador ="+identificador+";";
            ResultSet rs = sql.executeQuery(query);
       if(!rs.equals(null)){
           while(rs.next()) {
               CupomFiscal cf= new CupomFiscal();
               zeraConsultasCliente();
               buscaCliente(Integer.parseInt(rs.getString("cliente")));
               Cliente c = consultasCliente.get(0);
               cf.setCliente(c);
               zeraConsultasEmitente();
               buscaEmitente(Integer.parseInt(rs.getString("emitente")));
               Emitente e = consultasEmitente.get(0);
               cf.setEmitente(e);
               cf.setIdentificador(Integer.parseInt(rs.getString("identificador")));
               zeraConsultasProduto();
               buscaProduto(Integer.parseInt(rs.getString("produto")));
               Produto p = consultasProduto.get(0);
               cf.setProduto(null);
               consultasCupomFiscal.add(cf);
               ident = rs.getInt(1);
               System.out.println(ident);
                setValida(true);
                  
             }
                this.encerraConexao();
                
                  
             }else{
         consultasCliente.add(null);
        
         this.encerraConexao();
        setValida(false);
        
       }
       this.encerraConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            this.encerraConexao();
          setValida(false);
        }
}

public void inserirNotaFiscal(int idemitente, int  idtransportadora, int idproduto, int idcliente, int idfornecedor){
   try{
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
            query = "insert into sicone.notafiscal (idemitente, idtransportadora, idproduto, idcliente) values("+idemitente+","+idtransportadora+","+idproduto+","+idcliente+");";
       sql.executeUpdate(query);
        this.encerraConexao(); 
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        } 
}

public void buscarNotaFiscal(String identificador){
    try {
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
             query ="select * from sicone.buscarNotaFiscal where identificador ="+identificador+";";
            ResultSet rs = sql.executeQuery(query);
       if(!rs.equals(null)){
           while(rs.next()) {
               NotaFiscal nf = new NotaFiscal();
               zeraConsultasEmitente();
               buscaEmitente(Integer.parseInt(rs.getString("idemitente")));
               Emitente e =consultasEmitente.get(0);
               nf.setEmitente(e);
               zeraConsultasCliente();
               buscaCliente(Integer.parseInt(rs.getString("idcliente")));
               Cliente c = consultasCliente.get(0);
               zeraConsultasProduto();
               buscaProduto(Integer.parseInt(rs.getString("idproduto")));
               consultasNotaFiscal.add(nf);
               ident = rs.getInt(1);
               System.out.println(ident);
                setValida(true);
                  
             }
                this.encerraConexao();
                
                  
             }else{
         
        
         this.encerraConexao();
        setValida(false);
        
       }
       this.encerraConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            this.encerraConexao();
          setValida(false);
        }
}


public void inserirFuncionario(String senha, int nivel, String logadouro, String numero, String bairro, String complemento, String estado, String cidade, String email, String cpf, String nome, String rg){
       try{
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
            query = "insert into sicone.funcionario (logadouro, numero, bairro, complemento, estado, cidade, email, CPF, nome, RG, senha, nivel) values ('"+logadouro+"','"+numero+"','"+bairro+"','"+complemento+"','"+estado+"','"+cidade+"','"+email+"','"+cpf+"','"+nome+"','"+rg+"','"+senha+"',"+nivel+");" ;
       sql.executeUpdate(query);
        this.encerraConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public void buscarFuncionario(int identificador){
    try {
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
             query ="select * from sicone.funcionario where identificador ="+identificador+";";
            ResultSet rs = sql.executeQuery(query);
       if(!rs.equals(null)){
           while(rs.next()) {
               Funcionario f = new Funcionario();
               f.setBairro(rs.getString("bairro"));
               f.setCPF(rs.getString("CPF"));
               f.setCidade(rs.getString("cidade"));
               f.setComplemento(rs.getString("complemento"));
               f.setEmail(rs.getString("email"));
               f.setEstado(rs.getString("estado"));
               f.setIdentificador(Integer.parseInt(rs.getString("identificador")));
               f.setLogadouro(rs.getString("logadouro"));
               f.setNivel(Integer.parseInt(rs.getString("nivel")));
               f.setNome(rs.getString("nome"));
               f.setNumero(rs.getString("numero"));
               f.setRG(rs.getString("RG"));
               f.setSenha(rs.getString("senha"));
               
               consultasFuncionario.add(f);
               ident = rs.getInt(1);
               System.out.println(ident);
                setValida(true);

             }
                this.encerraConexao();


             }else{

consultasFuncionario.add(null);
         this.encerraConexao();
        setValida(false);

       }
       this.encerraConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            this.encerraConexao();
          setValida(false);
        }
}

    public void buscaProduto(int identificador) {
          try {
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
             query ="select * from sicone.produto where identificador ="+identificador+";";
            ResultSet rs = sql.executeQuery(query);
       if(!rs.equals(null)){
           while(rs.next()) {
               Produto p = new Produto();
               zeraConsultasFornecedor();
               buscaFornecedor(Integer.parseInt(rs.getString("fornecedor")));
               Fornecedor f = consultasFornecedor.get(0);
               p.setIdentificador(Integer.parseInt(rs.getString("identificador")));
               p.setNome(rs.getString("nome"));
              p.setQuantidade(Integer.parseInt(rs.getString("quantidade")));
               consultasProduto.add(p);
               ident = rs.getInt(1);
               System.out.println(ident);
                setValida(true);

             }
                this.encerraConexao();


             }else{


         this.encerraConexao();
        setValida(false);

       }
       this.encerraConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            this.encerraConexao();
          setValida(false);
        }
    }

    public void buscarProduto(String nome) {
          try {
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
             query ="select * from sicone.produto where nome ="+nome+";";
            ResultSet rs = sql.executeQuery(query);
       if(!rs.equals(null)){
           while(rs.next()) {
               Produto p = new Produto();
               zeraConsultasFornecedor();
               buscaFornecedor(Integer.parseInt(rs.getString("fornecedor")));
               Fornecedor f = consultasFornecedor.get(0);
               p.setIdentificador(Integer.parseInt(rs.getString("identificador")));
               p.setNome(rs.getString("nome"));
              p.setQuantidade(Integer.parseInt(rs.getString("quantidade")));
               consultasProduto.add(p);
               ident = rs.getInt(1);
               System.out.println(ident);
                setValida(true);

             }
                this.encerraConexao();


             }else{


         this.encerraConexao();
        setValida(false);

       }
       this.encerraConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            this.encerraConexao();
          setValida(false);
        }
    }

    public int todosFornecedores(){
           int i=1;
           try {
              this.iniciaConexao();
              
            sql = (Statement) connection.createStatement();
              query ="select * from sicone.fornecedor ;";
              ResultSet rs = sql.executeQuery(query);
               if(!rs.equals(null)){
                    while(rs.next()) {
                        Fornecedor f = new Fornecedor();
                        f.setBairro(rs.getString("bairro"));
                        f.setEstado(rs.getString("estado"));
                        f.setCidade(rs.getString("cidade"));
                        f.setComplemento(rs.getString("complemento"));
                        f.setEmail(rs.getString("email"));
                        f.setLogadouro(rs.getString("logadouro"));
                        f.setNumero(rs.getString("numero"));
                        f.setIdentificador(Integer.parseInt(rs.getString("identificador")));
                        f.setBairro(rs.getString("bairro"));

                        consultasFornecedor.add(f);
                        i++;
                     }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
           return i;
    }

    public void buscaFornecedorFisico(int identificador){

        try {
              this.iniciaConexao();
            sql = (Statement) connection.createStatement();
              query ="select * from sicone.fornecedorfisico where identificadorfornecedor ="+identificador+";";
              ResultSet rs = sql.executeQuery(query);
               if(!rs.equals(null)){
                    while(rs.next()) {
                        FornecedorFisico f = new FornecedorFisico();
                        f.setCPF(rs.getString("CPF"));
                        f.setNome(rs.getString("nome"));
                        f.setRG(rs.getString("RG"));
                        consultasFornecedorFisico.add(f);
                     }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }

}

    public void buscaClienteFisico(int identificador){

        try {
              this.iniciaConexao();
            sql = (Statement) connection.createStatement();
              query ="select * from sicone.clientefisico where identificadorCliente ="+identificador+";";
              ResultSet rs = sql.executeQuery(query);
               if(!rs.equals(null)){
                    while(rs.next()) {
                        ClienteFisico f = new ClienteFisico();
                        f.setCPF(rs.getString("CPF"));
                        f.setNome(rs.getString("nome"));
                        f.setRG(rs.getString("RG"));
                        consultasClienteFisico.add(f);
                     }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }

}

    public void buscaFornecedorJuridico(int identificador){

        try {
              this.iniciaConexao();
            sql = (Statement) connection.createStatement();
              query ="select * from sicone.fornecedorjuridico where identificadoFornecedor ="+identificador+";";
              ResultSet rs = sql.executeQuery(query);
               if(!rs.equals(null)){
                    while(rs.next()) {
                        FornecedorJuridico f = new FornecedorJuridico();
                        f.setCNPJ(rs.getString("CNPJ"));
                        f.setInscricaoEstadual(rs.getString("inscricaoEstadual"));
                        f.setInscricaoMunicipal(rs.getString("inscricaoMunicipal"));
                        f.setNomeFantasia(rs.getString("nomeFantasia"));
                        f.setRazaoSocial(rs.getString("razaoSocial"));
                        consultasFornecedorJuridico.add(f);
                                            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }

}
     public void buscaClienteJuridico(int identificador){

        try {
              this.iniciaConexao();
            sql = (Statement) connection.createStatement();
              query ="select * from sicone.clientejuridico where identificadorCliente ="+identificador+";";
              ResultSet rs = sql.executeQuery(query);
               if(!rs.equals(null)){
                    while(rs.next()) {
                        ClienteJuridico f = new ClienteJuridico();
                        f.setCNPJ(rs.getString("CNPJ"));
                        f.setInscricaoEstadual(rs.getString("inscricaoEstadual"));
                        f.setInscricaoMunicipal(rs.getString("inscricaoMunicipal"));
                        f.setNomeFantasia(rs.getString("nomeFantasia"));
                        f.setRazaoSocial(rs.getString("razaoSocial"));
                        consultasClienteJuridico.add(f);
                                            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }

}

       public int todosClientes(){
           int i=1;
           try {
              this.iniciaConexao();

            sql = (Statement) connection.createStatement();
              query ="select * from sicone.cliente ;";
              ResultSet rs = sql.executeQuery(query);
               if(!rs.equals(null)){
                    while(rs.next()) {
                        Cliente f = new Cliente();
                        f.setBairro(rs.getString("bairro"));
                        f.setEstado(rs.getString("estado"));
                        f.setCidade(rs.getString("cidade"));
                        f.setComplemento(rs.getString("complemento"));
                        f.setEmail(rs.getString("email"));
                        f.setLogadouro(rs.getString("logadouro"));
                        f.setNumero(rs.getString("numero"));
                        f.setIdentificador(Integer.parseInt(rs.getString("identificador")));
                        f.setBairro(rs.getString("bairro"));

                        consultasCliente.add(f);
                        i++;
                     }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
           return i;
    }

    public int todosProdutos() {
        int i=1;
       try {
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
             query ="select * from sicone.produto ;";
            ResultSet rs = sql.executeQuery(query);
       if( rs != null){
           while(rs.next()) {
               Produto p = new Produto();
               System.out.println("aki oo"+rs.getString("identificador"));
               p.setCodigo(Integer.parseInt(rs.getString("identificador")));
               p.setDescrição(rs.getString("descricao"));
               p.setNome(rs.getString("nome"));
               p.setQuantidade(Integer.parseInt(rs.getString("quantidade")));
              //zeraConsultasFornecedor();
              // buscaFornecedor(Integer.parseInt(rs.getString("fornecedor")));
              // p.setFornecedor(consultasFornecedor.get(0));
               consultasProduto.add(p);
               i++;
               ident = rs.getInt(1);
               System.out.println(ident);
                setValida(true);

             }
           zeraConsultasFornecedor();
                this.encerraConexao();


             }else{


         this.encerraConexao();
        setValida(false);

       }
       this.encerraConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            this.encerraConexao();
          setValida(false);
        }
        return i;
    }

    public int todosEmitente() {
         int i=1;
       try {
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
             query ="select * from sicone.emitente ;";
            ResultSet rs = sql.executeQuery(query);
       if( rs != null){
           while(rs.next()) {
               Emitente e= new Emitente();
               e.setBairro(rs.getString("bairro"));
                        e.setMunicipio(rs.getString("municipio"));
                        e.setComplemento(rs.getString("complemento"));
                        e.setEmail(rs.getString("email"));
                        e.setLogradouro(rs.getString("logradouro"));
                        e.setNumero(rs.getString("numero"));
                        e.setIdentificador(Integer.parseInt(rs.getString("identificador")));
                        e.setBairro(rs.getString("bairro"));
                        e.setCNPJ(rs.getString("CNPJ"));
                        e.setCep(rs.getString("cep"));
                        e.setUf(rs.getString("uf"));
                        e.setRegimeTributario(rs.getString("regimeTributario"));
                        e.setRazaoSocial(rs.getString("razaoSocial"));
                        e.setInscricaoEstadual(rs.getString("inscricaoEstadual"));
                        e.setInscricaoMunicipal(rs.getString("inscricaoMunicipal"));
                        e.setPais(rs.getString("pais"));
                        e.setNomeFantasia(rs.getString("nomeFantasia"));
               consultasEmitente.add(e);
               i++;
               ident = rs.getInt(1);
               System.out.println(ident);
                setValida(true);

             }
           zeraConsultasFornecedor();
                this.encerraConexao();


             }else{


         this.encerraConexao();
        setValida(false);

       }
       this.encerraConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            this.encerraConexao();
          setValida(false);
        }
        return i;


    }

    public int todosTransportadora() {
        int i=1;
        try {
            this.iniciaConexao();
            sql = (Statement) connection.createStatement();
             query ="select * from sicone.transportadora;";
            ResultSet rs = sql.executeQuery(query);
       if(!rs.equals(null)){
           while(rs.next()) {
               ident = rs.getInt(1);
               Transportadora t = new Transportadora();
               t.setCNPJ(rs.getString("CNPJ"));
               t.setInscricaoEstadual(rs.getString("inscricaoEstadual"));
               t.setLogadouro(rs.getString("logadouro"));
               t.setMunicipio(rs.getString("municipio"));
               t.setRazaoSocial(rs.getString("razaoSocial"));
               t.setNumero(rs.getString("numero"));
               t.setUf(rs.getString("uf"));
               t.setIdentificador(Integer.parseInt(rs.getString("identificador")));
               consultasTransportadora.add(t);
               System.out.println(ident);
                setValida(true);

             }
                this.encerraConexao();


             }else{


         this.encerraConexao();
        setValida(false);

       }
       this.encerraConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            this.encerraConexao();
          setValida(false);
        }
        return i;
    }
}

