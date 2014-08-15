# Akka & Docker

```bash
sbt docker:publishLocal
docker run -i -t --name seed akka-docker:2.3.4 --seed
docker run -i -t --name seed akka-docker:2.3.4 -Dclustering.seed-ip=<ip-of-your-seed-node>
```

# TODO

* Multiple Seed Nodes
* How to configure stuff
