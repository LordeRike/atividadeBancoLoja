import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {
   // Configurações de conexão 
   private static final String URL = "jdbc:postgresql://localhost:5432/loja";
   private static final String USER = "postgres";
   private static final String PASS = "1234"; 

   public static void main(String[] args) {
       try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            ClienteDAO dao = new ClienteDAO(conn);
            ProdutoDAO dois = new ProdutoDAO(conn);
            VendaDAO tres = new VendaDAO(conn);
            ItemDAO quatro = new ItemDAO(conn);
            Scanner teclado = new Scanner(System.in);
            int opcao = -1;

            while (opcao != 0) {
                System.out.println("\n===== MANUTENÇÃO DE CLIENTES =====");
                System.out.println("====================================");
                System.out.println("== |1| Cadastrar Cliente ==");
                System.out.println("== |2| Listar Clientes ==");
                System.out.println("== |3| Atualizar Cliente ==");
                System.out.println("== |4| Excluir Cliente ==");
                System.out.println("====================================");
                System.out.println("== |5| Cadastrar Produto ==");
                System.out.println("== |6| Listar Produto ==");
                System.out.println("== |7| Atualizar Produto ==");
                System.out.println("== |8| Excluir Produto ==");
                System.out.println("====================================");
                System.out.println("== |9| Venda ==");
                System.out.println("====================================");
                System.out.println("== |0| Sair ==");
                System.out.println("====================================");
                System.out.print("Escolha uma opção: ");
                
                opcao = teclado.nextInt();
                teclado.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.print("Nome: ");
                        String nome = teclado.nextLine();
                        System.out.print("Endereço: ");
                        String endereco = teclado.nextLine();
                        
                        Cliente novo = new Cliente(0, nome, endereco);
                        dao.inserir(novo);
                        System.out.println("✔ Cliente cadastrado com sucesso!");
                        System.out.println("====================================");
                        break;

                    case 2:
                        System.out.println("== Listar CLientes ==");
                        System.out.println("== |1| Listar todos os clientes ==");
                        System.out.println("== |2| Buscar cliente por nome ==");
                        int escBusca = teclado.nextInt();
                        teclado.nextLine();

                        if (escBusca == 1) {
                            System.out.println("\n--- Lista de Clientes ---");
                            List<Cliente> lista = dao.listarTodos();
                            for (Cliente c : lista) {
                                System.out.printf("ID: %d | Nome: %s | Endereço: %s%n", 
                                                  c.getIdCliente(), c.getNome(), c.getEndereco());
                            }
                            System.out.println("====================================");
                            break;
                        } else if (escBusca == 2) {
                            System.out.println("== Listar Cliente por nome ==");
                            System.out.print("== Digite o nome do cliente: ");
                            String nomeBusca = teclado.nextLine();
                            List<Cliente> lista = dao.listarUm(nomeBusca);
                            for (Cliente c: lista) {
                                System.out.printf("ID: %d | Nome: %s | Endereço: %s%n", 
                                                  c.getIdCliente(), c.getNome(), c.getEndereco());
                            }
                            System.out.println("====================================");
                            break;
                        } else {
                            System.out.println("== Opção invalida ==");
                        }
                        
                        break;

                    case 3:
                        System.out.print("ID do cliente que deseja atualizar: ");
                        int idUpd = teclado.nextInt();
                        teclado.nextLine(); 
                        System.out.print("Novo Nome: ");
                        String novoNome = teclado.nextLine();
                        System.out.print("Novo Endereço: ");
                        String novoEnd = teclado.nextLine();

                        Cliente cAtualizar = new Cliente(idUpd, novoNome, novoEnd);
                        dao.atualizar(cAtualizar);
                        System.out.println("✔ Dados atualizados!");
                        System.out.println("====================================");
                        break;

                    case 4:
                        System.out.print("ID do cliente que deseja excluir: ");
                        int idDel = teclado.nextInt();
                        dao.excluir(idDel);
                        System.out.println("Cliente removido!");
                        System.out.println("====================================");
                        break;
                    //cases de produtos
                    case 5:
                        System.out.print("Descrição: ");
                        String descricao = teclado.nextLine();
                        System.out.print("Quantidade: ");
                        int quantidadeEstoque = teclado.nextInt();
                        teclado.nextLine();                        
                       
                        Produto p = new Produto(0, descricao, quantidadeEstoque);
                        dois.inserir(p);
                       // dao.inserirP(p);
                        System.out.println("✔ Produto cadastrado com sucesso!");
                        System.out.println("====================================");
                        break;

                    case 6:
                        System.out.println("\n--- Lista de Produtos ---");
                        List<Produto> lista1 = dois.listarTodos();
                        for (Produto p1 : lista1 ) {
                            System.out.printf("ID: %d | Descrição: %s | Quantidade: %s%n", 
                                                p1.getIdProduto(), p1.getDescricao(), p1.getQuantidadeEstoque());
                        }
                        System.out.println("====================================");
                        break;
                    case 7:
                        System.out.print("ID do produto que deseja atualizar: ");
                        int idUpdP = teclado.nextInt();
                        teclado.nextLine(); 
                        System.out.print("Nova descricao: ");
                        String novoNomeP = teclado.nextLine();
                        System.out.print("Nova quantidade: ");
                        int novoEndP = teclado.nextInt();
                        teclado.nextLine();

                        Produto cAtualizarP = new Produto(idUpdP, novoNomeP, novoEndP);
                        //dao.atualizarP(cAtualizarP);
                        dois.atualizarP(cAtualizarP);
                        System.out.println("✔ Dados atualizados!");
                        System.out.println("====================================");
                        break;

                    case 8:
                        System.out.print("ID do produto que deseja excluir: ");
                        int idDele = teclado.nextInt();
                        dois.excluir(idDele);
                        System.out.println("Produto removido!");
                        System.out.println("====================================");

                        break;

                    case 9:
                        System.out.println("== Tela de Vendas ==");
                        List<Cliente> lista = dao.listarTodos();
                            for (Cliente c : lista) {
                                System.out.printf("ID: %d | Nome: %s | Endereço: %s%n", 
                                                  c.getIdCliente(), c.getNome(), c.getEndereco());
                            }
                        System.out.println("====================================");
                        
                        System.out.println();
                        System.out.print("== Digite o id do cliente: ");
                        int buscaIDV = teclado.nextInt();
                        teclado.nextLine();  
                        
                        
                        //Criacao da nova venda
                        Venda nova = new Venda(0, buscaIDV, 0);
                        tres.Venda(nova);

                        System.out.println();

                        int n = 1;
                        do {
                            System.out.println("== Digite a opcao desejada: ");
                            System.out.println("== |1| Adicionar novo item == ");
                            System.out.println("== |2| Finalizar venda == ");
                            n = teclado.nextInt();
                            teclado.nextLine();

                            if (n == 1) {
                                //Listar itens
                                System.out.println("\n--- Lista de Produtos ---");
                                List<Produto> lista2 = dois.listarTodos();
                                for (Produto p1 : lista2 ) {
                                    System.out.printf("ID: %d | Descrição: %s | Quantidade: %s%n", 
                                                        p1.getIdProduto(), p1.getDescricao(), p1.getQuantidadeEstoque());
                                }
                                System.out.println("====================================");

                                //Adicao dos itens
                                System.out.println("== == == ==");
                                System.out.print("== Digite o id do item: ");
                                int idItemV = teclado.nextInt();
                                teclado.nextLine();

                                System.out.print("== Digite o valor Unitario do produto: R$");
                                Double valorUni = teclado.nextDouble();
                                teclado.nextLine();

                                System.out.print("== Digite a quantidade: ");
                                int qtd = teclado.nextInt();
                                teclado.nextLine();

                                Item novoI = new Item(0 ,nova.getIdVenda() ,valorUni ,qtd ,idItemV);
                                quatro.inserirItem(novoI);
                               

                                
                            } else if (n == 2) {
                                tres.notaFiscal(nova.getIdVenda());
                                break;
                            } else {
                                System.out.println("== Opcao invalida ==");
                            }

                        } while (n !=2 );

                        break;
                    case 0:
                        System.out.println("Encerrando o sistema...");
                        teclado.close();
                        break;

                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro de conexão ou SQL: " + e.getMessage());
        }
        
    }
}