import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/*import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Importações da biblioteca Minimal-JSON
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;*/


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
            ConselhoDAO cinco = new ConselhoDAO(conn);
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
                System.out.println("== |10| Atualizar Venda ==");
                System.out.println("====================================");
                System.out.println("== |11| Conselho ==");
                System.out.println("====================================");
                System.out.println("== |0| Sair ==");
                System.out.println("====================================");
                System.out.print("Escolha uma opção: ");
                
                opcao = teclado.nextInt();
                teclado.nextLine();

                switch (opcao) {
                    case 1:
                        conn.setAutoCommit(false);    
                        try {
                            System.out.print("Nome: ");
                            String nome = teclado.nextLine();
                            System.out.print("Endereço: ");
                            String endereco = teclado.nextLine();

                            Cliente novo = new Cliente();
                            novo.setIdCliente(0);
                            novo.setNome(nome);
                            novo.setEndereco(endereco);

                            dao.inserir(novo);
                            conn.commit();
                            System.out.println("✔ Cliente cadastrado com sucesso!");
                            

                        } catch (IllegalArgumentException e) {
                            conn.rollback();
                            System.out.println("Erro de validação: " + e.getMessage());
                        } catch (SQLException e) {
                            conn.rollback();
                            System.out.println("Erro de banco de dados: " + e.getMessage());
                        } finally {
                            conn.setAutoCommit(true);
                        }
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
                        conn.setAutoCommit(false);    
                        try {
                            System.out.print("ID do cliente que deseja atualizar: ");
                            int idUpd = teclado.nextInt();
                            teclado.nextLine(); 
                            System.out.print("Novo Nome: ");
                            String novoNome = teclado.nextLine();
                            System.out.print("Novo Endereço: ");
                            String novoEnd = teclado.nextLine();
                            

                            Cliente cAtualizar = new Cliente(idUpd, novoNome, novoEnd);
                            dao.atualizar(cAtualizar);

                            conn.commit();
                            System.out.println("✔ Dados atualizados!");
                            
                        } catch (IllegalArgumentException e) {
                            conn.rollback();
                            System.out.println("Erro de validação: " + e.getMessage());
                        } catch (SQLException e) {
                            conn.rollback();
                            System.out.println("Erro de banco de dados: " + e.getMessage());
                        } finally {
                            conn.setAutoCommit(true);
                        }
                        System.out.println("====================================");
                        break;

                    case 4:
                        conn.setAutoCommit(false);
                        try {
                            System.out.print("ID do cliente que deseja excluir: ");
                            int idDel = teclado.nextInt();
                            dao.excluir(idDel);
                            conn.commit();
                            System.out.println("Cliente removido!");
                            
                        }  catch (IllegalArgumentException e) {
                            conn.rollback();
                            System.out.println("Erro de validação: " + e.getMessage());
                        } catch (SQLException e) {
                            conn.rollback();
                            System.out.println("Erro de banco de dados: " + e.getMessage());
                        } finally {
                            conn.setAutoCommit(true);
                        }

                        
                        System.out.println("====================================");
                        break;
                    //cases de produtos
                    case 5:
                        conn.setAutoCommit(false);
                        try {
                            System.out.print("Descrição: ");
                            String descricao = teclado.nextLine();
                            System.out.print("Quantidade: ");
                            int quantidadeEstoque = teclado.nextInt();
                            teclado.nextLine();                        
                        
                            Produto p = new Produto(0, descricao, quantidadeEstoque);
                            dois.inserir(p);
                            conn.commit();
                            System.out.println("✔ Produto cadastrado com sucesso!");

                        } catch (IllegalArgumentException e) {
                            conn.rollback();
                            System.out.println("Eroo de validação: " + e.getMessage());
                        } catch (SQLException e) {
                            conn.rollback();
                            System.out.println("Erro de banco de dados: " + e.getMessage());
                        } finally {
                            conn.setAutoCommit(true);
                        }                        
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
                        conn.setAutoCommit(false);
                        try {
                            System.out.print("ID do produto que deseja atualizar: ");
                            int idUpdP = teclado.nextInt();
                            teclado.nextLine(); 
                            System.out.print("Nova descricao: ");
                            String novoNomeP = teclado.nextLine();
                            System.out.print("Nova quantidade: ");
                            int novoEndP = teclado.nextInt();
                            teclado.nextLine();

                            Produto cAtualizarP = new Produto(idUpdP, novoNomeP, novoEndP);
                            conn.commit();
                            dois.atualizarP(cAtualizarP);
                            System.out.println("✔ Dados atualizados!");
                            
                        } catch (IllegalArgumentException e) {
                            conn.rollback();
                            System.out.println("Eroo de validação: " + e.getMessage());
                        } catch (SQLException e) {
                            conn.rollback();
                            System.out.println("Erro de banco de dados: " + e.getMessage());
                        } finally {
                            conn.setAutoCommit(true);
                        }                   
                        
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
                        conn.setAutoCommit(false);
                        try {
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
    
                                    Item novoI = new Item();
                                    novoI.setIdVendaItem(0);
                                    novoI.setIdVenda(nova.getIdVenda());
                                    novoI.setValorUnitario(valorUni);
                                    novoI.setQuantidade(qtd);
                                    novoI.setIdProduto(idItemV);
                                    
                                    quatro.inserirItem(novoI);                                 
                                    
                                } else if (n == 2) {
                                    tres.notaFiscal(nova.getIdVenda());
                                    break;
                                } else {
                                    System.out.println("== Opcao invalida ==");
                                }
    
                            } while (n !=2 );
                            conn.commit();
                        } catch (IllegalArgumentException e) {
                            conn.rollback();
                            System.out.println("Erro de validação: " + e.getMessage());
                        } catch (SQLException e) {
                            conn.rollback();
                            System.out.println("Erro de banco de dados: " + e.getMessage());
                        } finally {
                            conn.setAutoCommit(true);
                        }

                        break;
                    case 10:
                        conn.setAutoCommit(false);
                        
                        try {
                            System.out.println("== Selecione a Opção desejada: ");
                            System.out.println("== |1| Atualizar Venda ==");
                            System.out.println("== |2| Excluir Venda ==");
                            int opAtualizarVenda = teclado.nextInt();
                            teclado.nextLine();

                            if (opAtualizarVenda == 1) {
                                System.out.println("== Atualizar Venda ==");
                                System.out.print("Digite o ID da venda que deseja atualizar: ");
                                int idVendaUpd = teclado.nextInt();
                                teclado.nextLine();

                                if (!tres.existeVenda(idVendaUpd)) {
                                    throw new IllegalArgumentException("Venda ID " + idVendaUpd + " não foi encontrada!");
                                }

                                int subOpcao = -1;
                                while (subOpcao != 0) {
                                    System.out.println("\n--- Gerenciar Venda #" + idVendaUpd + " ---");
                                    System.out.println("1. Alterar Cliente da Venda");
                                    System.out.println("2. Adicionar Novo Item na Venda");
                                    System.out.println("3. Editar Quantidade/Preço de um Item");
                                    System.out.println("4. Remover um Item da Venda");
                                    System.out.println("5. Visualizar Nota Fiscal da Venda");
                                    System.out.println("0. Finalizar / Salvar Alterações");
                                    System.out.print("Escolha uma opção: ");
                                    subOpcao = teclado.nextInt();
                                    teclado.nextLine();

                                    switch (subOpcao) {
                                        case 1:
                                            System.out.println("\n--- Lista de Clientes ---");
                                            List<Cliente> clientes = dao.listarTodos();
                                            for (Cliente c : clientes) {
                                                System.out.printf("ID: %d | Nome: %s%n", c.getIdCliente(), c.getNome());
                                            }
                                            System.out.print("Digite o novo ID do Cliente: ");
                                            int novoIdCliente = teclado.nextInt();
                                            teclado.nextLine();

                                            tres.atualizarVenda(idVendaUpd, novoIdCliente);
                                            System.out.println("✔ Cliente da venda atualizado!");
                                            break;

                                        case 2:
                                            System.out.println("\n--- Lista de Produtos ---");
                                            List<Produto> produtos = dois.listarTodos();
                                            for (Produto p : produtos) {
                                                System.out.printf("ID: %d | Descrição: %s | Estoque: %d%n",
                                                        p.getIdProduto(), p.getDescricao(), p.getQuantidadeEstoque());
                                            }
                                            System.out.print("Digite o ID do produto: ");
                                            int idProd = teclado.nextInt();
                                            teclado.nextLine();

                                            System.out.print("Digite o Valor Unitário: R$");
                                            double vUnit = teclado.nextDouble();

                                            System.out.print("Digite a Quantidade: ");
                                            int qtd = teclado.nextInt();
                                            teclado.nextLine();

                                            Item novoItem = new Item(0, idVendaUpd, vUnit, qtd, idProd);
                                            quatro.inserirItem(novoItem);
                                            System.out.println("✔ Item adicionado à venda!");
                                            break;

                                        case 3:
                                            List<Item> itensAtuais = quatro.listarItensPorVenda(idVendaUpd);
                                            if (itensAtuais.isEmpty()) {
                                                System.out.println("Esta venda não possui itens cadastrados.");
                                                break;
                                            }
                                            System.out.println("\n--- Itens da Venda ---");
                                            for (Item item : itensAtuais) {
                                                System.out.printf("ID Item: %d | ID Produto: %d | Qtd: %d | Valor: R$%.2f%n",
                                                        item.getIdVendaItem(), item.getIdProduto(), item.getQuantidade(), item.getValorUnitario());
                                            }
                                            System.out.print("Digite o ID do item que deseja editar: ");
                                            int idItemUpd = teclado.nextInt();

                                            System.out.print("Digite a nova quantidade: ");
                                            int novaQtd = teclado.nextInt();

                                            System.out.print("Digite o novo valor unitário: R$");
                                            double novoValor = teclado.nextDouble();
                                            teclado.nextLine();

                                            Item itemEditado = new Item(idItemUpd, idVendaUpd, novoValor, novaQtd, 0);
                                            quatro.atualizarItem(itemEditado);
                                            System.out.println("✔ Item atualizado com sucesso!");
                                            break;

                                        case 4:
                                            List<Item> itensParaDeletar = quatro.listarItensPorVenda(idVendaUpd);
                                            if (itensParaDeletar.isEmpty()) {
                                                System.out.println("Esta venda não possui itens cadastrados.");
                                                break;
                                            }
                                            System.out.println("\n--- Itens da Venda ---");
                                            for (Item item : itensParaDeletar) {
                                                System.out.printf("ID Item: %d | ID Produto: %d | Qtd: %d | Valor: R$%.2f%n",
                                                        item.getIdVendaItem(), item.getIdProduto(), item.getQuantidade(), item.getValorUnitario());
                                            }
                                            System.out.print("Digite o ID do item que deseja remover: ");
                                            int idItemDel = teclado.nextInt();
                                            teclado.nextLine();

                                            quatro.excluirItem(idItemDel);
                                            System.out.println("✔ Item removido da venda!");
                                            break;

                                        case 5:
                                            tres.notaFiscal(idVendaUpd);
                                            break;

                                        case 0:
                                            System.out.println("Finalizando atualizações da venda...");
                                            break;

                                        default:
                                            System.out.println("Opção inválida!");
                                            break;
                                    }
                                }

                                conn.commit();
                                System.out.println("✔ Alterações na venda salvas no banco de dados com sucesso!");
                            } else if (opAtualizarVenda == 2) {
                                System.out.println("== Excluir Venda ==");
                                System.out.println();
                                System.out.println("== Digite o ID da venda que seja excluir: ");
                                int idEx = teclado.nextInt();
                                teclado.nextLine();

                                System.out.print("Tem certeza que deseja excluir a venda #" + idEx + " e todos os seus itens? (S/N): ");
                                String confirmacao = teclado.nextLine();

                                if (confirmacao.equalsIgnoreCase("S")) {
                                    tres.excluirVenda(idEx);
                                    conn.commit();
                                    System.out.println("✔ Venda e seus itens foram excluídos com sucesso!");
                                } else {
                                    conn.rollback();
                                    System.out.println("Operação cancelada pelo usuário.");
                                };
                                break;
                            } else {
                                System.out.println("== Opção invalida ==");
                            }
                            
                        } catch (IllegalArgumentException e) {
                            conn.rollback();
                            System.out.println("Erro de validação: " + e.getMessage());
                        } catch (SQLException e) {
                            conn.rollback();
                            System.out.println("Erro de banco de dados: " + e.getMessage());
                        } finally {
                            conn.setAutoCommit(true);
                        }
                        System.out.println("====================================");
                        break;
                    case 11:
                        System.out.println("== Programa de Conselhos ==");
                        System.out.println("== Você deseja: ");
                        System.out.println("== |1| Conselho Aletorio ==");
                        System.out.println("== |2| Conselho Especifico ==");
                        int opCon = teclado.nextInt();
                        teclado.nextLine();

                        if (opCon == 1) {
                            System.out.println("== Conselho Aleatorio ==");
                            cinco.consultarConselho();
                            cinco.inserirConselho(null);
                            break;
                        } else if (opCon == 2) {
                            System.out.println("== Conselho Especifico ==");
                            System.out.println("== Digite o numero de ID do conselho que quer: ");
                            int opIdEsp = teclado.nextInt();
                            teclado.nextLine();
                            cinco.conselhoAleatorio(opIdEsp);
                            cinco.inserirConselho(null);
                            break;

                        } else {
                            System.out.println("== Opção Invalida ==");
                            break;
                        }
                        
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