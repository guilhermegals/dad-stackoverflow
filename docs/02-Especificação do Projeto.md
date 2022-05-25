# Especificações do Projeto

## Personas

### Persona 1

> João, aluno de Sistemas de Informação quer organizar melhor sua vida acadêmica. Seu processo de aprendizado depende muito de como de quando suas entregas de trabalhos e provas das disciplinas que está cursando ocorrem. João tem o péssimo hábito de estudar um ou dois dias antes destas datas importantes, e é exatamente neste período que as dúvidas surgem. Por não possuir um canal de comunicação eficaz, ele faz diversas buscas na internet, mas geralmente se sente muito inseguro com o conteúdo com que tem contato.

### Persona 2

> Camila é uma professora, que ministra algumas matérias do curso de Sistemas de Informação. Mesmo com as aulas ela sente que os alunos ainda sentem muitas dúvidas e querem estudar sobre temas diversos em suas matérias, embora ela esteja sempre questionado na classe, durante as explicações se existem problemas ou questionamentos, ela entende que muitas dessas questões surjam fora das aulas, em momentos de estudo em casa, com os colegas ou diversos, mas não sabe exatamente como obter essas informações a mais para complemento das suas aulas.  

### Persona 3

> Gustavo é um aluno e monitor de várias matérias de Sistemas de Informação. Ele percebeu que as dúvidas dos alunos costumam ser bem parecidas em diversos assuntos, dessa forma, Gustavo precisa tirar a mesma dúvida muitas vezes. Ele gostaria de poder colaborar com os alunos de uma forma eficiente para que ele não precise ficar repetindo, além de alcançar também os alunos que não conseguem participar das monitorias.

## Histórias de Usuários

Com base na análise das personas forma identificadas as seguintes histórias de usuários:

| EU COMO... `PERSONA` | QUERO/PRECISO ... `FUNCIONALIDADE` | PARA ... `MOTIVO/VALOR` |
|--------------------|------------------------------------|----------------------------------------|
| Usuário do sistema | Logar com meu e-mail e senha | Acessar o sistema |
| Aluno | Criar Posts | Tirar dúvidas com monitores e colegas de curso. |
| Monitor/Aluno | Procurar posts com dúvidas de outros alunos | Tirar dúvidas com monitores e colegas de curso. |
| Professor | Ver posts de alunos | Descobrir temas com mais dúvidas entre os alunos. |
| Usuário do sistema | Dar likes em comnetários | Avaliar as melhores respostas. |

## Requisitos

As tabelas que se seguem apresentam os requisitos funcionais e não funcionais que detalham o escopo do projeto.

### Requisitos Funcionais

| ID    | Descrição do Requisito  | Prioridade |
| ------ | ----------------------------------------- | ---- |
|RF-001 | O sistema de manter registro de todas as threads criadas por usuários | MÉDIA |
|RF-002 | O sistema deve permitir aos usuários se cadastrar no ambiente | MÉDIA |
|RF-003 | O sistema deve permitir a usuários comentarem threads de outros usuários e as suas próprias | MÉDIA |
|RF-004 | O sistema deve permitir aos usuários logar no ambiente | MÉDIA |
|RF-005 | O sistema deve permitir que o usuário crie tópicos ou comentários irrestritos que serão disponibilizados a todos os outros usuários | ALTA |
|RF-006 | O sistema deve permitir que o usuário crie tópicos ou comentários irrestritos que serão disponibilizados a todos os outros usuários | ALTA |

### Requisitos não Funcionais

| ID | Descrição do Requisito | Prioridade |
| ------- | ------------------------- | ---- |
|RNF-001| O sistema será portável para ambientes mobile | BAIXA |
|RNF-002| O sistema deve estar integrado a um banco de dados | ALTA |
|RNF-003| O sistema não deve armazenar dados pessoais do usuário, com exceção na criação das suas threads | ALTA |
|RNF-004| O sistema deve possuir uma interface amigável ao usuário | MÉDIA |
|RNF-005| O usuário deve ter um retorno visual quanto ao carregamento das informações durante o uso | ALTA |
|RNF-006| O sistema deverá registrar logs a fim de registrar | ALTA |
|RNF-007| O sistema deve usar os recursos disponibilizados pelos navegadores de forma exata as suas necessidades, não desperdiçando a utilização de nenhum destes recursos, evitando o desperdício de informações e com baixo tempo de resposta | ALTA |
|RNF-008| Deve processar requisições do usuário em no máximo 3s | ALTA |

## Restrições

O projeto está restrito pelos itens apresentados na tabela a seguir.

| ID | Restrição |
| -- |------------------------------------------------------- |
| 01 | O projeto deverá ser entregue até o final do semestre |
| 02 | O Backend será desenvolvido com a tecnologia Node.JS |
| 03 | O Frontend WEB será desenvolvido com a tecnologia Androi Nativo |
| 04 | O Frontend Mobile será desenvolvido com a tecnologia Android Nativo |
| 05 | Será utilizado Mongo.db  como banco de dados não relacional |
