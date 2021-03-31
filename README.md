# CompassoProdutos
#postgress docker

docker run --name postgress-compasso -p 5439:5432 -d \
    -e POSTGRES_PASSWORD=postgres \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_DB=COM1 \
    -e POSTGRES_DB=COMP2 \
    -v pgdata:/var/lib/postgresql/data \
    postgres
