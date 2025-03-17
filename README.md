# Bank_Gestor
CP1 para a materia de java advanced fiap. Criar uma API para gestão de um banco

Objetivos:

1.Crie um endpoint para o path “/”
que retorna uma String com o nome
do projeto e os nomes dos integrantes
da equipe.

2.Crie um endpoint para cadastrar uma
conta. A conta possui número,
agência, nome do titular, cpf do
titular, data de abertura, saldo inicial,
ativa (s/n) e tipo (corrente, poupança
ou salário)

2.1.Os dados da conta devem ser salvos
em memória.

3.Aplique as validações no cadastro das
contas de acordo com as regras a seguir:
➔ nome do titular: obrigatório
➔ cpf do titular: obrigatório
➔ data de abertura: não pode ser no
futuro
➔ saldo inicial: não pode ser negativo
➔ tipo: deve ser um tipo válido (corrente,
poupança ou salário)
Caso o usuário envie algum dado inválido,
retorne um erro 400 com a mensagem de
erro no corpo da resposta.

4.Crie um endpoint para retornar todas as
contas cadastradas.
Crie um endpoint para retornar uma conta por
id.
Crie um endpoint para retornar uma conta por
cpf do titular;

5.Crie um endpoint que recebe o id
de uma conta e encerra essa conta.
A conta encerrada deve ser
marcada como inativa no banco de
dados (em memória).

6.Crie um endpoint para realizar um depósito na conta.
Os dados do depósito devem ser enviados no corpo da requisição
(id da conta e valor do depósito)
O valor, se válido, deve ser somado ao saldo da conta.
Retorne os dados atualizados da conta.

7.Crie um endpoint para realizar um saque da conta.
Os dados do saque devem ser enviados no corpo da requisição (id
da conta e valor do saque)
O valor, se válido, deve ser subtraído do saldo da conta.
Retorne os dados atualizados da conta.

8.Crie um endpoint para realizar um pix.
Os dados do pix devem ser enviados no corpo da requisição (id
conta de origem, id da conta de destino e valor do pix)
O valor, se válido, deve ser transferido de uma conta para a outra.
Retorne os dados atualizados da conta de origem.
