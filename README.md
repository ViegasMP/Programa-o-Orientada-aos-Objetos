# Programa-o-Orientada-aos-Objetos
#Descrição do Problema
Os alunos do DEI pretendem organizar uma viagem de estudo e querem desenvolver uma aplicação de suporte ao planeamento da viagem. A viagem deve contemplar 3 locais, cada um com vários pontos de interesse.

Os locais a visitar são caraterizados pelo nome da cidade onde estão situados. Cada local pode ter um ou mais pontos de interesse, incluindo universidades, museus, parques e bares. As universidades são caracterizadas pelo nome e lista de cursos relacionados com Engenharia Informática. Os museus, além do nome, possuem a descrição da sua temática (por exemplo, história natural, arte moderna, ou aviação). Os parques podem ser culturais ou de diversões (por exemplo, parques aquáticos). Os parques aquáticos possuem várias piscinas e equipamentos (por exemplo escorregas) e podem ou não possuir espetáculos de animais. Os bares são caraterizados pela classificação média dada pelos clientes. Todos os pontos de interesse possuem um horário de funcionamento. Alguns pontos de interesse têm custos de entrada associados. Também podem existir outras despesas, como por exemplo a entrada em espetáculos ou o consumo de bebidas.

A aplicação deve solicitar as preferências do utilizador em função dos locais e pontos de interesse a visitar. Todos os utilizadores devem indicar o montante máximo que pretendem gastar. Além das preferências referidas, os alunos de licenciatura podem indicar um ponto de interesse “hot” - que não querem perder, e os alunos de mestrado podem indicar um local que querem evitar (por exemplo uma cidade com elevados níveis de poluição).

Os locais a visitar em cada viagem devem poder ser visualizados por ordem crescente e decrescente de custo. O custo de cada percurso é calculado pela soma dos seguintes fatores (que devem ser aplicados de acordo com a natureza dos pontos de interesse visitados):

- Preço de entrada (museus e parques, considerando as várias atividades que podem ser realizadas)

- Despesas no ponto de interesse (parques e bares)

- Deslocação entre locais – considera-se que as deslocações entre pontos de interesse no mesmo local têm custo zero.

Os resultados de todas as pesquisas realizadas pelos utilizadores devem ser armazenados de forma a possibilitar o tratamento de dados.
