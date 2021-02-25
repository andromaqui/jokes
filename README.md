### Docker

You can start the platform with H2 by issuing:
```
docker-compose up -d
```

If you need a postgres backend, you can use a different docker-compose
file. In this case the command is: ``` docker-compose -f docker-compose.postgresql.yml up -d ``