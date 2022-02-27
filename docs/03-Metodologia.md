
# Metodologia

## Controle de Versão

A ferramenta de Controle de Versão escolhida para ser utilizada no desenvolvimento do projeto será o Git em conjunto com o Github como repositório de código-fonte. Tal escolha se deu tendo em vista o conhecimento dos membros do grupo no uso destas ferramentas, bem como por serem padrões nos projetos de desenvolvimento de software.

O projeto segue a seguinte convenção para o nome de branches:

- `Main`: É a branch principal que contém a versão estável e em produção do código-fonte
- `Release`: Branch responsável pela preparação para a release de uma nova versão do software, desta forma, é utilizada para a criação de documentações e testes finais necessários no conjunto de novas funcionalidades que irão ser entregues na nova versão.
- `HotFix`: São um conjunto de branchs que devem ser criadas quando são encontrados algum bug em produção e que devem ser corrigidos imediatamente
- `Develop`: É a branch que possui as funcionalidades estáveis para serem colocadas em produção na próxima release

Quanto à gerência de issues, o projeto adota a seguinte convenção para
etiquetas:

- `documentation`: melhorias ou acréscimos à documentação
- `bug`: uma funcionalidade encontra-se com problemas
- `enhancement`: uma funcionalidade precisa ser melhorada
- `feature`: uma nova funcionalidade precisa ser introduzida

Nesse contexto, as nomenclaturas das branchs seguirá o padrão descrito abaixo:

{Etiqueta}/{Numero do Card}-{Nome do Card}

Ex: feature/12-login

Com todas as definições descritas acima, é possível definir o fluxo nos passos abaixo:

É criada uma branch Develop a partir da Main
São criadas as diversas branch de requisito para as mais diversas issues existentes no projeto
Após finalizado o desenvolvimento da branch de requisito, deve ser realizado um PullRequest para a Branch de develop, adicionando os demais desenvolvedores para a realização do Code Review.
Obs: Para que o merge seja realizado, o desenvolvedor deverá aguardar a aprovação de ao menos um revisor.
Quando definir que irá ser realizada a release de uma nova versão, deve ser criado uma branch de release, a partir da develop;
Com tudo pronto para ser enviado para a produção, deve-se realizar o merge da release na main.
Caso seja encontrado um bug crítico em produção deve-se ser criado um HotFix para a correção
Após realizado a correção, esta branch deve-se realizar o merge do HotFix na main e na develop.

## Fluxo de Tarefas

Foram definidos 4 status para as tarefas/issues a serem desenvolvidas:

- A Fazer: Tarefa pendente de desenvolvimento
- Em Desenvolvimento: Tarefa está sendo realizada por um dos integrantes da equipe.
- Em teste: Tarefa está sendo testada pelos integrantes da equipe
- Concluída: Tarefa se encontra concluída e mergeada na Develop

## Gerenciamento de Projeto

### Divisão de Papéis

Para o gerenciamento do projeto, a equipe se dividirá em um  Product Owner, responsável por entender o que o cliente precisa e o que é possível dentro do projeto, o Scrum Master ficará responsável por liderar a equipe durante os processos do Scrum e ajudar nos gargalos que podem surgir durante o desenvolvimento, e uma equipe de desenvolvimento transformará as necessidades do cliente em produto. Dessa forma, a equipe ficou dividida em:

- Guilherme Augusto: Arquiteto de Software / Dev Mobile
- Ana Paula de Oliveira Enock: Dev Backend
- Marcelo Maximiano: Dev Backend
- Daniel Braz: Dev Frontend / Scrum Master
- Rafael Siqueira: Dev Backend
- Maria Fernanda: Dev frontend
- Isabella Cristina de Castro Pimenta: Dev Frontend / Product Owner
- Pedro Henrique Fernandes: Dev Backend
- Vinicius Arlei Chaves: Dev Mobile

### Processo

Para controle do processo será utilizada a plataforma Azure DevOps juntamente com o framework Scrum. Para isso será feito pequenas entregas em ciclos de 1 mês, tendo as reuniões de planejamento de ciclo, de retrospectiva e de revisão. Todos os épicos, funcionalidades e histórias de usuários serão cadastrados no Azure DevOps.

### Ferramentas

As ferramentas empregadas no projeto são:

- Editor de Código: Visual Studio e VS Code
- Ferramentas de comunicação: WhatsApp e Teams
- Plataforma de gerenciamento de projeto: Azure DevOps
- Repositório de código: Github
