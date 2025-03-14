/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();

        int status;

        try {
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES(?,?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            status = prep.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar: " + ex.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        conn = new conectaDAO().connectDB();

        String sql = "SELECT * FROM produtos";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            //Vamos criar um objeto do tipo List
            //Faça a importação do ArrayList
            ArrayList<ProdutosDTO> listagem = new ArrayList<>();
            //percorrer o resultSet e salvar as informações dentro de uma variável "Empresa"
            //Depois salva esse objeto dentro da lista

            //Estrutura de repetição While
            while (rs.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                ProdutosDTO produto = new ProdutosDTO();
                //Salvar dentro do objeto empresa as informações            
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                //Adicionando os elementos na lista criada
                listagem.add(produto);

            }
            //Após finalizar o while, o retorno será a listaEmpresas, onde cada posição é um registro do banco de dados
            return listagem;

            //Se o método entrar no "Catch" quer dizer que não encontrou nenhuma empresa, então damos um "return null"
        } catch (Exception e) {
            return null;
        }

    }

}
