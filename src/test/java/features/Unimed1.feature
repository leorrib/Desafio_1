#language: pt
Funcionalidade: Usuário buscando por médicos.

Cenario: No site da Unimed, o usuário busca por médicos e analisa o resultado
Dado Usuário pesquisa por "médicos" no site da unimed
Quando Restringe a busca ao estado "Rio de Janeiro" e cidade "Rio de Janeiro"
Entao Analisa se a busca por "médicos" se restringiu a "RJ"

Cenario: No site da Unimed, usuário busca por médicos e analisa o resultado
Dado Usuário pesquisa por "médicos" no site da unimed
Quando Restringe a busca ao estado "Rio de Janeiro" e cidade "Rio de Janeiro"
Entao Analisa se a busca por "médicos" gerou resultados em "São Paulo" nas "3" primeiras páginas