up:
	docker-compose --env-file=.env up --build

down:
	docker-compose down

delete:
	docker compose down --volumes

psql-auth-db:
	psql -h localhost -U postgres -d messenger

psql-chat-db:
	psql -h localhost -U postgres -d messenger -p 5433

psql-message-db:
	psql -h localhost -U postgres -d messenger -p 5434

psql-user-db:
	psql -h localhost -U postgres -d messenger -p 5435