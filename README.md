# Akka & Docker

```bash
sbt docker:publishLocal
docker run -i -t --name seed-1 akka-docker:2.3.4 --seed
docker run -i -t --name seed-2 akka-docker:2.3.4 --seed <ip-of-your-seed-1>:2551
docker run -i -t --name node-1 akka-docker:2.3.4 <ip-of-your-seed-1>:2551 <ip-of-your-seed-2>:2551
docker run -i -t --name node-2 akka-docker:2.3.4 <ip-of-your-seed-1>:2551 <ip-of-your-seed-2>:2551
```

# TODO

* Multiple Seed Nodes
* How to configure stuff

# Links and References

* [Akka Docker Cluster Example Blog](http://blog.michaelhamrah.com/2014/03/running-an-akka-cluster-with-docker-containers/)
* [Akka Docker Cluster Example Github](https://github.com/mhamrah/akka-docker-cluster-example)
* [Docker Cheat Sheet](https://github.com/wsargent/docker-cheat-sheet)
* [Docker Env Variables](http://mike-clarke.com/2013/11/docker-links-and-runtime-env-vars/)
* [Docker Ambassador Pattern Linking](http://docs.docker.com/articles/ambassador_pattern_linking/)
