Feature: Desafio 1: No site da UNIMED, buscar por médicos no Rio de Janeiro e analisar 
o resultado.

Scenario Outline: Entrar no site da Unimed, clicar em Guia Médico, preencher o campo de busca 
com o valor desejado e confirmar se a busca foi realizada de forma correta. 
dos resultados com a Especialidade e Cidade.
Given Inicializar o browser
And Entrar no site "https://www.unimed.coop.br/"
And Clicar em -Guia Médico-
When Usuário digita <busca> no campo de busca e clica em Pesquisar
And Usuário escolhe <estado> no campo Estado e <cidade> no campo Cidade, clica em UnimedRio e em Continuar
Then Verificar se os parâmetros da busca foram <busca> e <cidade>
Then Fechar o browser

Examples:
|busca |estado			|cidade		 	|
|Médico|Rio de Janeiro	|Rio de Janeiro	|

Scenario Outline: Entrar no site da Unimed, clicar em Guia Médico, preencher o campo de busca 
com o valor desejado e confirmar se, nas primeiras 3 páginas de resultado, aparece a cidade 
de São Paulo.
Given Inicializar o browser
And Entrar no site "https://www.unimed.coop.br/"
And Clicar em -Guia Médico-
When Usuário digita <busca> no campo de busca e clica em Pesquisar
And Usuário escolhe <estado> no campo Estado e <cidade> no campo Cidade, clica em UnimedRio e em Continuar
Then Verificar se a cidade <cidade2> aparece em algum resultado das <paginas> primeiras páginas
Then Fechar o browser

Examples:
|busca |estado			|cidade		 	|cidade2  |paginas |
|Médico|Rio de Janeiro	|Rio de Janeiro	|São Paulo|3       |