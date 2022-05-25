# garden-backend
Backend para projeto de sistemas distribuidos'

## Como rodar o web service?

1 - Primeiro rode o docker-compose.yml com o comando `docker compose up` dentro do projeto (ele ira subir um servidor de banco de dados mongo padrão na sua porta 27017)
2 - Abra o projeto e execute o comando `yarn install` ou `npm install`
3 - Novamente no terminal do projeto execute o comando `yarn start` ou `npm run start`

PS: Para rodar um servidor com hotreaload (facilitando o desenvolvimento) vc pode executar o comando `yarn dev` ou `npm run dev` para rodar com o servidor nodemon.

## Como fazer deploy?

- heroku login
- git push heroku main