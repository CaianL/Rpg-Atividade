# Rpg-Atividade

Lista dos endpoint(Entre chaves é a informação):

Cadastrar Personagem:POST /personagens ;
Cadastrar Item:POST /itens;
Listar Personagem:GET /personagens;
Buscar Personagem por ID: GET personagens/{id};
Atualizar Nome de Guerreiro por ID:PUT /personagens/{id}/nome;
Remover Personagem:DELETE /personagem/{id};
Listar Itens:GET /itens;
Buscar Item por ID: GET /itens/{id};
Adicionar Item ao Personagem:PUT /itens/equipar/{idpersonagem}/{iditem};
Listar Itens do Personagem: GET /itens/personagem/{idpersonagem};
Remover Item do Personagem:DELETE /itens/remover/{idpersonagem}/{iditem};
Buscar Amuleto do Personagem:GET /itens/amuleto/{idpersonagem};
