registro de atividades:

Atividade 1: Altere o projeto feito como exemplo criando uma nova rotina que receba como parâmetro o ID do cliente e liste somente os dados deste cliente.

Ativade 2: No banco de dados loja, crie a tabela produtos com os campos id do produto, descrição e quantidade em estoque
Crie o campo id_produto na tabela venda_item e crie a FK com a tabela produtoss

No mesmo programa do exemplo (e da atividade anterior) crie um método para inserir um produto e outro para listar todos os produtos

Ativadade 3: mplemente a estrutura CRUD/DAO para a tabela de produtos criada nas atividades anteriores

Atividade 4: Dentro da classe ClienteDAO (passada como exemplo na aula 06 - 23/04/2026). Implemente uma função que receba o nome do cliente (parâmetro do tipo String) pesquise no banco (lembrando que o parâmetro passado pode ser somente parte do nome) e, se caso encontre, retorne um objeto do tipo Cliente com os dados do cliente.

Dica: Utilize o like para fazer a pesquisa no banco

Atividade 5: Já vimos o básico agora é treinar a lógica em um contexto cada vez mais perto da realidade
Dando sequencia as atividades anteriores. Incluir um novo menu chamado Vendas.
Ao acessar esse menu o usuário dever informar os dados necessário para gravar as informações na tabela vendas. 
Após isso o usuário poderá adicionar produtos a venda. Ele pode adicionar quantos produtos quiser (mas pelo menos 1 ele é obrigado a informar) 
Após terminar de inserir todos os produtos o sistema deverá apresentar uma espécie de nota fiscal da compra (data da compra, Nome do cliente e total da venda ) e uma lista discriminada dos itens (descrição do produto, quantidade, valor unitário e valor total por produto)

Atividade 6: Já vimos o básico agora é treinar a lógica em um contexto cada vez mais perto da realidade
Dando sequencia as atividades anteriores. Incluir um novo menu chamado Vendas.
Ao acessar esse menu o usuário dever informar os dados necessário para gravar as informações na tabela vendas. 
Após isso o usuário poderá adicionar produtos a venda. Ele pode adicionar quantos produtos quiser (mas pelo menos 1 ele é obrigado a informar) 
Após terminar de inserir todos os produtos o sistema deverá apresentar uma espécie de nota fiscal da compra (data da compra, Nome do cliente e total da venda ) e uma lista discriminada dos itens (descrição do produto, quantidade, valor unitário e valor total por produto)

Atividade 7: Usando o como base o programa de exemplo de como consumir uma API. Modifique o mesmo para que grave em uma tabela no banco de dados loja o ID do conselho, o Conselho, e a data em que foi feita a consulta. Para isso pode criar uma nova tabela chamada conselho.

Atividade 8: Modificar o programa da atividade 1 para perguntar ao usuário se ele  deseja receber um conselho aleatório ou um especifico. Caso ele queira receber um especifico pedir para o usuário informar o id do conselho que ele quer receber e fazer a consulta deste conselho especifico, caso contrario siga fazendo a consulta randômica.
Para os dois tipos de consulta, a mesma deve continuar sendo salva no banco de dados.
Incluir um novo campo na tabela conselho e armazenar se o conselho consultado foi específico ou aleatório.

Após consultar um conselho perguntar para o usuário se o mesmo deseja fazer uma nova consulta ou encerrar o programa. Caso ele queira continuar, deve-se repetir todo o processo.
