# CompassoProdutos
#postgress docker
docker run --name postgress-compasso -p 5433:5432 -d \
    -e POSTGRES_PASSWORD=postgres \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_DB=COMPASSO \      
    -v pgdata:/var/lib/postgresql/data \
    postgres
