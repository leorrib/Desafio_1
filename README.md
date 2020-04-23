# Desafio_1

# Como compilar
Clonar projeto e rodar a partir do arquivo testng.xml. O browser pode ser escolhido no arquivo src/main/java/data.properties. As opções são: chrome, chrome headless, firefox e edge.

# Resumo
O projeto faz a seguinte rotina: no primeiro cenário, o site da UNIMED é aberto e é feita uma busca por médicos em Rio de Janeiro/RJ. Em seguida, verifica-se se algum resultado fora do RJ foi exibido. Já no segundo cenário, o mesmo site é acessado e a mesma busca é realizada. Porém, a verificação feita é se a palavra "São Paulo" é exibida em algum resultado das três primeiras páginas.

# Observações
O uso de Assert.assertTrue(False) dentro de um if loop, que só é acessado caso os critérios não sejam atendidos, é que fará o teste falhar caso haja algum erro nos resultados. 
